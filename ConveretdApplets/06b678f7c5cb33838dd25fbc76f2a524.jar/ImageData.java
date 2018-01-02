import java.util.zip.Inflater;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageData
{
    int[][] data;
    byte[] exp;
    ptviewer pv;
    boolean isfloat;
    int width;
    int height;
    byte[] lugamma;
    int[] ldtab;
    double gamma;
    
    byte[] initgamma(final double n) {
        final double n2 = 1.0 / n;
        final int n3 = (int)Math.pow(255.0, n);
        final byte[] array = new byte[n3];
        array[0] = 0;
        for (int i = 1; i < n3; ++i) {
            int n4 = (int)(Math.pow(i, n2) + 0.5);
            if (n4 > 255) {
                n4 = 255;
            }
            array[i] = (byte)n4;
        }
        return array;
    }
    
    void setLut(double pow) {
        if (this.ldtab == null) {
            return;
        }
        pow = Math.pow(pow, this.gamma);
        this.ldtab[0] = 0;
        for (int i = 0; i < this.ldtab.length; ++i) {
            final double n = pow * Math.pow(2.0, i - 128.0) + 0.5;
            if (n >= 2.147483647E9) {
                this.ldtab[i] = Integer.MAX_VALUE;
            }
            else {
                this.ldtab[i] = (int)n;
            }
        }
    }
    
    public ImageData(final int[][] data, final ptviewer pv) {
        this.data = null;
        this.exp = null;
        this.pv = null;
        this.isfloat = false;
        this.width = 0;
        this.height = 0;
        this.lugamma = null;
        this.ldtab = null;
        this.gamma = 1.0;
        this.pv = pv;
        this.width = data[0].length;
        this.height = data.length;
        this.data = data;
    }
    
    public ImageData(final Image image, final ptviewer pv) {
        this.data = null;
        this.exp = null;
        this.pv = null;
        this.isfloat = false;
        this.width = 0;
        this.height = 0;
        this.lugamma = null;
        this.ldtab = null;
        this.gamma = 1.0;
        this.pv = pv;
        this.width = image.getWidth(pv);
        this.height = image.getHeight(pv);
        int[][] data;
        try {
            data = new int[image.getHeight(pv)][image.getWidth(pv)];
        }
        catch (Exception ex) {
            this.pv.fatal = true;
            return;
        }
        this.pv.ptImageTo2DArray(data, image);
        if (data == null) {
            this.pv.fatal = true;
        }
        this.data = data;
    }
    
    public ImageData(final String s, final ptviewer pv, final boolean b, final double n, final double gamma) {
        this.data = null;
        this.exp = null;
        this.pv = null;
        this.isfloat = false;
        this.width = 0;
        this.height = 0;
        this.lugamma = null;
        this.ldtab = null;
        this.gamma = 1.0;
        this.gamma = gamma;
        this.lugamma = this.initgamma(gamma);
        this.ldtab = new int[256];
        this.pv = pv;
        byte[] array;
        if (b) {
            this.pv.percent[0] = 0;
            array = this.pv.file_read(s, this.pv.percent);
        }
        else {
            array = this.pv.file_read(s, null);
        }
        if (array == null) {
            this.pv.fatal = true;
            return;
        }
        if (s.toLowerCase().endsWith(".fjpg")) {
            this.isfloat = true;
            this.decodeFJPG(array);
        }
        else if (s.toLowerCase().endsWith(".pic") || s.toLowerCase().endsWith(".hdr") || s.toLowerCase().endsWith(".img")) {
            this.isfloat = true;
            this.decodeRADIANCE(array);
        }
        else {
            final Image image = Toolkit.getDefaultToolkit().createImage(array);
            final MediaTracker mediaTracker = new MediaTracker(this.pv);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {
                this.pv.fatal = true;
                return;
            }
            this.width = image.getWidth(this.pv);
            this.height = image.getHeight(this.pv);
            int[][] data;
            try {
                data = new int[this.height][this.width];
            }
            catch (Exception ex2) {
                this.pv.fatal = true;
                return;
            }
            this.pv.ptImageTo2DArray(data, image);
            if (data == null) {
                this.pv.fatal = true;
            }
            this.data = data;
        }
        if (this.data == null) {
            this.pv.fatal = true;
        }
        if (b) {
            this.pv.percent[0] = 100;
            this.pv.repaint();
        }
    }
    
    void decodeRADIANCE(final byte[] array) {
        if (array == null) {
            return;
        }
        int n = this.decodeRADIANCE_header(array);
        if (n < 0 || n >= array.length || this.width <= 0 || this.height <= 0) {
            return;
        }
        final int[][] data = new int[this.height][this.width];
        if (data == null) {
            return;
        }
        for (int n2 = 0; n2 < this.height && n >= 0; n = this.RGBE_ReadPixels_RLE(array, n, data[n2], this.width), ++n2) {}
        if (n != array.length) {
            return;
        }
        this.exp = new byte[this.width * this.height];
        for (int i = 0; i < this.height; ++i) {
            final int n3 = i * this.width;
            for (int j = 0; j < this.width; ++j) {
                this.exp[n3 + j] = (byte)(data[i][j] & 0xFF);
                data[i][j] = (data[i][j] >> 8 | 0xFF000000);
            }
        }
        this.data = data;
    }
    
    int decodeRADIANCE_header(final byte[] array) {
        int n;
        for (n = 3; n < array.length && (array[n - 3] != 10 || array[n - 2] != 45 || array[n - 1] != 89 || array[n] != 32); ++n) {}
        if (++n >= array.length) {
            return -1;
        }
        int n2;
        for (n2 = n + 1; n2 < array.length && array[n2] != 10; ++n2) {}
        if (n2 == array.length) {
            return -1;
        }
        final String s = new String(array, n, n2 - n);
        final int index = s.indexOf(88);
        if (index == -1) {
            return -1;
        }
        try {
            this.height = (int)(Object)Double.valueOf(s.substring(0, index - 2));
            this.width = (int)(Object)Double.valueOf(s.substring(index + 2));
        }
        catch (Exception ex) {
            return -1;
        }
        return n2 + 1;
    }
    
    int RGBE_ReadPixels_RLE(final byte[] array, int n, final int[] array2, final int n2) {
        if (n + 4 > array.length) {
            return -1;
        }
        if (array[n] != 2 || array[n + 1] != 2 || (array[n + 2] & 0x80) != 0x0) {
            System.arraycopy(array, n, array2, 0, n2 * 4);
            return n + n2 * 4;
        }
        if ((array[n + 2] << 8 | array[n + 3]) != n2) {
            System.out.println("wrong scanline width");
            return -1;
        }
        n += 4;
        final byte[][] array3 = new byte[4][n2];
        final byte[] array4 = new byte[2];
        for (int i = 0; i < 4; ++i) {
            int j = 0;
            while (j < n2) {
                array4[0] = array[n++];
                array4[1] = array[n++];
                if ((array4[0] & 0xFF) > 128) {
                    int n3 = (array4[0] & 0xFF) - 128;
                    if (n3 == 0 || n3 > n2 - j) {
                        System.out.println("bad scanline data");
                        return -1;
                    }
                    while (n3-- > 0) {
                        array3[i][j++] = array4[1];
                    }
                }
                else {
                    int n4 = array4[0] & 0xFF;
                    if (n4 == 0 || n4 > n2 - j) {
                        System.out.println("bad scanline data");
                        return -1;
                    }
                    array3[i][j++] = array4[1];
                    if (--n4 <= 0) {
                        continue;
                    }
                    System.arraycopy(array, n, array3[i], j, n4);
                    n += n4;
                    j += n4;
                }
            }
        }
        for (int k = 0; k < n2; ++k) {
            array2[k] = ((array3[0][k] & 0xFF) << 24) + ((array3[1][k] & 0xFF) << 16) + ((array3[2][k] & 0xFF) << 8) + (array3[3][k] & 0xFF);
        }
        return n;
    }
    
    void decodeFJPG(final byte[] array) {
        if (array == null) {
            return;
        }
        int n = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i - 1] == -1 && array[i] == -39) {
                n = i + 1;
                break;
            }
        }
        if (n == 0) {
            return;
        }
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, n);
        final Image image = Toolkit.getDefaultToolkit().createImage(array2);
        final MediaTracker mediaTracker = new MediaTracker(this.pv);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            return;
        }
        this.width = image.getWidth(this.pv);
        this.height = image.getHeight(this.pv);
        int[][] data;
        try {
            data = new int[this.height][this.width];
        }
        catch (Exception ex2) {
            return;
        }
        this.pv.ptImageTo2DArray(data, image);
        final int n2 = (this.width + 7) / 8;
        final byte[] array3 = new byte[n2 * ((this.height + 7) / 8)];
        try {
            final Inflater inflater = new Inflater();
            inflater.setInput(array, n, array.length - n);
            inflater.inflate(array3);
            inflater.end();
        }
        catch (Exception ex3) {}
        this.exp = new byte[this.height * this.width];
        for (int j = 0; j < this.height; ++j) {
            for (int k = 0; k < this.width; ++k) {
                this.exp[j * this.width + k] = array3[(j >> 3) * n2 + (k >> 3)];
            }
        }
        this.data = data;
    }
    
    final int getPixel(final int n, final int n2) {
        if (this.isfloat) {
            final int n3 = this.ldtab[this.exp[n2 * this.width + n] & 0xFF];
            final int n4 = this.data[n2][n];
            final int n5 = (n4 >> 16 & 0xFF) * n3 >>> 8;
            final int n6 = (n5 >= this.lugamma.length) ? 255 : (this.lugamma[n5] & 0xFF);
            final int n7 = (n4 >> 8 & 0xFF) * n3 >>> 8;
            final int n8 = (n7 >= this.lugamma.length) ? 255 : (this.lugamma[n7] & 0xFF);
            final int n9 = (n4 & 0xFF) * n3 >>> 8;
            return (n4 & 0xFF000000) | (n6 << 16) + (n8 << 8) + ((n9 >= this.lugamma.length) ? 255 : (this.lugamma[n9] & 0xFF));
        }
        return this.data[n2][n];
    }
    
    final int getPixel(final int n, final int n2, final int n3, final int n4) {
        final int n5 = 256 - n3;
        final int n6 = 256 - n4;
        final int n7 = n5 * n6;
        final int n8 = n4 * n5;
        final int n9 = n3 * n4;
        final int n10 = n3 * n6;
        if (this.isfloat) {
            final int n11 = n2 * this.width + n;
            final int n12 = this.data[n2][n + 1];
            final int n13 = this.ldtab[this.exp[n11 + 1] & 0xFF];
            final int n14 = (n12 >> 16 & 0xFF) * n13 >>> 8;
            final int n15 = (n14 >= this.lugamma.length) ? 255 : (this.lugamma[n14] & 0xFF);
            final int n16 = (n12 >> 8 & 0xFF) * n13 >>> 8;
            final int n17 = (n16 >= this.lugamma.length) ? 255 : (this.lugamma[n16] & 0xFF);
            final int n18 = (n12 & 0xFF) * n13 >>> 8;
            final int n19 = (n18 >= this.lugamma.length) ? 255 : (this.lugamma[n18] & 0xFF);
            final int n20 = this.data[n2 + 1][n];
            final int n21 = this.ldtab[this.exp[n11 + this.width] & 0xFF];
            final int n22 = (n20 >> 16 & 0xFF) * n21 >>> 8;
            final int n23 = (n22 >= this.lugamma.length) ? 255 : (this.lugamma[n22] & 0xFF);
            final int n24 = (n20 >> 8 & 0xFF) * n21 >>> 8;
            final int n25 = (n24 >= this.lugamma.length) ? 255 : (this.lugamma[n24] & 0xFF);
            final int n26 = (n20 & 0xFF) * n21 >>> 8;
            final int n27 = (n26 >= this.lugamma.length) ? 255 : (this.lugamma[n26] & 0xFF);
            final int n28 = this.data[n2 + 1][n + 1];
            final int n29 = this.ldtab[this.exp[n11 + this.width + 1] & 0xFF];
            final int n30 = (n28 >> 16 & 0xFF) * n29 >>> 8;
            final int n31 = (n30 >= this.lugamma.length) ? 255 : (this.lugamma[n30] & 0xFF);
            final int n32 = (n28 >> 8 & 0xFF) * n29 >>> 8;
            final int n33 = (n32 >= this.lugamma.length) ? 255 : (this.lugamma[n32] & 0xFF);
            final int n34 = (n28 & 0xFF) * n29 >>> 8;
            final int n35 = (n34 >= this.lugamma.length) ? 255 : (this.lugamma[n34] & 0xFF);
            final int n36 = this.data[n2][n];
            final int n37 = this.ldtab[this.exp[n11] & 0xFF];
            final int n38 = (n36 >> 16 & 0xFF) * n37 >>> 8;
            final int n39 = (n38 >= this.lugamma.length) ? 255 : (this.lugamma[n38] & 0xFF);
            final int n40 = (n36 >> 8 & 0xFF) * n37 >>> 8;
            final int n41 = (n40 >= this.lugamma.length) ? 255 : (this.lugamma[n40] & 0xFF);
            final int n42 = (n36 & 0xFF) * n37 >>> 8;
            return (n7 * n39 + n10 * n15 + n8 * n23 + n9 * n31 & 0xFF0000) + (n7 * n41 + n10 * n17 + n8 * n25 + n9 * n33 >> 16 << 8) + (n7 * ((n42 >= this.lugamma.length) ? 255 : (this.lugamma[n42] & 0xFF)) + n10 * n19 + n8 * n27 + n9 * n35 >> 16) | (n36 & 0xFF000000);
        }
        final int n43 = this.data[n2][n];
        final int n44 = this.data[n2][n + 1];
        final int n45 = this.data[n2 + 1][n];
        final int n46 = this.data[n2 + 1][n + 1];
        return (n7 * (n43 >> 16 & 0xFF) + n10 * (n44 >> 16 & 0xFF) + n8 * (n45 >> 16 & 0xFF) + n9 * (n46 >> 16 & 0xFF) & 0xFF0000) + (n7 * (n43 >> 8 & 0xFF) + n10 * (n44 >> 8 & 0xFF) + n8 * (n45 >> 8 & 0xFF) + n9 * (n46 >> 8 & 0xFF) >> 16 << 8) + (n7 * (n43 & 0xFF) + n10 * (n44 & 0xFF) + n8 * (n45 & 0xFF) + n9 * (n46 & 0xFF) >> 16) | (n43 & 0xFF000000);
    }
}
