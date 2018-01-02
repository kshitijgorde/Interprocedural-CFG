import java.awt.image.ImageProducer;
import java.awt.MediaTracker;
import java.util.Vector;
import java.net.URL;
import java.io.EOFException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Component;
import java.awt.image.ColorModel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class b
{
    private static final String \u00dc = "Data";
    private static final String \u00dd = "Header";
    private ColorModel \u00de;
    private Component \u00df;
    private byte[] \u00e0;
    private int \u00e1;
    private int \u00e2;
    private int \u00e3;
    private int \u00e4;
    private boolean \u00e5;
    private int \u00e6;
    byte[] \u00e7;
    byte \u00e8;
    private int \u00e9;
    private int \u00ea;
    private int \u00eb;
    boolean \u00ec;
    private int \u00ed;
    private int \u00ee;
    private int \u00ef;
    
    public b(final Component \u00df) {
        this.\u00e5 = false;
        this.\u00ec = false;
        this.\u00ec = false;
        this.\u00df = \u00df;
    }
    
    private MemoryImageSource Y(final byte[] array) throws Exception {
        int n = -1;
        int n2 = 0;
        this.\u00e4 = 0;
        final byte[] array2 = new byte[768];
        if (this.e(array, array2, 13) == -1) {
            throw new Exception("GIF header error !");
        }
        final int n3 = array2[10] & 0xFF;
        if ((n3 & 0x80) == 0x0) {
            throw new Exception("GIF header error !");
        }
        this.\u00eb = 1 << (n3 & 0x7) + 1;
        if (this.e(array, array2, this.\u00eb * 3) == -1) {
            throw new Exception("GIF header error !");
        }
        final byte[] array3 = new byte[this.\u00eb];
        final byte[] array4 = new byte[this.\u00eb];
        final byte[] array5 = new byte[this.\u00eb];
        for (int i = 0; i < this.\u00eb; ++i) {
            array3[i] = array2[i * 3];
            array4[i] = array2[i * 3 + 1];
            array5[i] = array2[i * 3 + 2];
        }
    Block_7:
        while (true) {
            switch (array[this.\u00e4++] & 0xFF) {
                case 44: {
                    try {
                        if (this.\u00e4 < array.length) {
                            final int n4 = array.length - this.\u00e4;
                            this.\u00e0 = new byte[n4];
                            System.arraycopy(array, this.\u00e4, this.\u00e0, 0, n4);
                            this.\u00ed = ((this.\u00e0[4] & 0xFF) | this.\u00e0[5] << 8);
                            this.\u00e3 = ((this.\u00e0[6] & 0xFF) | this.\u00e0[7] << 8);
                            if (n2 == 0) {
                                this.\u00de = new IndexColorModel(8, this.\u00eb, array3, array4, array5);
                            }
                            else {
                                this.\u00de = new IndexColorModel(8, this.\u00eb, array3, array4, array5, n);
                            }
                            this.\u00e5 = ((this.\u00e0[8] & 0x40) != 0x0);
                            final byte[] array6 = new byte[this.\u00ed * this.\u00e3];
                            try {
                                this.f(array6);
                            }
                            catch (Exception ex) {}
                            return new MemoryImageSource(this.\u00ed, this.\u00e3, this.\u00de, array6, 0, this.\u00ed);
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        throw new Exception("GIF data error !");
                    }
                    break;
                }
            }
            switch (array[this.\u00e4++] & 0xFF) {
                default: {
                    final int n5 = array[this.\u00e4++] & 0xFF;
                    if (n5 != 0 && this.e(array, new byte[n5], n5) == -1) {
                        continue;
                    }
                    continue;
                }
                case 249: {
                    final byte[] array7 = new byte[6];
                    if (this.e(array, array7, 6) == -1) {
                        break Block_7;
                    }
                    n2 = (((array7[1] & 0x1) == 0x1) ? 1 : 0);
                    n = (array7[4] & 0xFF);
                    continue;
                }
            }
        }
        throw new Exception("GIF header error !");
    }
    
    public static byte[] d(final File file) throws IOException {
        return d(new DataInputStream(new FileInputStream(file)));
    }
    
    public static byte[] d(final DataInputStream dataInputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            while (true) {
                byteArrayOutputStream.write(dataInputStream.readByte());
            }
        }
        catch (IOException ex) {
            if (!(ex instanceof EOFException)) {
                throw ex;
            }
            dataInputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
    }
    
    public static byte[] d(final URL url) throws IOException {
        return d(new DataInputStream(url.openStream()));
    }
    
    public int h() {
        return this.\u00e6;
    }
    
    public int W() {
        return this.\u00e9;
    }
    
    public int g() {
        return this.\u00ea;
    }
    
    public void f(final byte[] array) throws Exception {
        final short[] array2 = new short[4096];
        final byte[] array3 = new byte[4096];
        final byte[] array4 = new byte[1025];
        int n = 10;
        final int n2 = this.\u00e0[9] & 0xFF;
        final int n3 = 1 << n2;
        final int n4 = n3 + 1;
        final int n5 = this.\u00eb - 1;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = n2 + 1;
        int n10 = 1 << n9;
        int n11 = n10 - 1;
        int n12 = n3 + 2;
        int n13 = 0;
        byte b = 0;
        int n14 = this.\u00ed;
        int n15 = 0;
        int n16 = 0;
        int n17 = 0;
        int n18 = 0;
        while (true) {
            if (n7 < n9) {
                for (int i = 0; i < 2; ++i) {
                    if (n6 <= 0) {
                        if (this.\u00e0.length < 1 + n) {
                            return;
                        }
                        final int n19 = this.\u00e0[n++] & 0xFF;
                        if (n19 == 0) {}
                        n6 += n19;
                    }
                    if (this.\u00e0.length < 1 + n) {
                        return;
                    }
                    if (this.\u00e0.length < 1 + n) {
                        return;
                    }
                    n8 += (this.\u00e0[n++] & 0xFF & 0xFF) << n7;
                    n7 += 8;
                    --n6;
                }
            }
            int n20 = n8 & n11;
            n8 >>= n9;
            n7 -= n9;
            if (n20 == n3) {
                n12 = n3 + 1;
                n9 = n2 + 1;
                n10 = 1 << n9;
                n11 = n10 - 1;
                if (n17 < array.length) {
                    continue;
                }
                n20 = n4;
            }
            if (n20 == n4) {
                return;
            }
            int j = n20;
            int length = array4.length;
            if (j >= n12) {
                j = n13;
                array4[--length] = b;
            }
            while (j > n5) {
                array4[--length] = array3[j];
                j = array2[j];
            }
            b = (byte)j;
            array4[--length] = b;
            int n21 = array4.length - length;
            if (n21 > 2 && n21 < n14) {
                n14 -= n21;
                System.arraycopy(array4, length, array, n17, n21);
                n17 += n21;
            }
            else {
                while (--n21 >= 0) {
                    final byte b2 = array4[length++];
                    if (n17 >= array.length) {
                        return;
                    }
                    array[n17++] = b2;
                    if (--n14 != 0) {
                        continue;
                    }
                    n14 = this.\u00ed;
                    ++n16;
                    if (!this.\u00e5) {
                        continue;
                    }
                    switch (n18) {
                        case 0: {
                            n15 += 8;
                            if (n15 >= this.\u00e3) {
                                ++n18;
                                n15 = 4;
                                break;
                            }
                            break;
                        }
                        case 1: {
                            n15 += 8;
                            if (n15 >= this.\u00e3) {
                                ++n18;
                                n15 = 2;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            n15 += 4;
                            if (n15 >= this.\u00e3) {
                                ++n18;
                                n15 = 1;
                                break;
                            }
                            break;
                        }
                        case 3: {
                            n15 += 2;
                            break;
                        }
                    }
                    n17 = n15 * this.\u00ed;
                }
            }
            array2[n12] = (short)n13;
            array3[n12] = b;
            n13 = n20;
            if (++n12 < n10 || n9 >= 12) {
                continue;
            }
            ++n9;
            n10 <<= 1;
            n11 = n10 - 1;
        }
    }
    
    public Vector X(final byte[] array) throws Exception {
        int n = 0;
        byte[] array2 = null;
        Object o = null;
        final Vector vector = new Vector<a>();
        final MediaTracker mediaTracker = new MediaTracker(this.\u00df);
        if (!new String(array, 0, 0, 6).startsWith("GIF8")) {
            throw new Exception("GIF error : Not valid file !");
        }
        this.\u00ea = (array[6] & 0xFF) + (array[7] << 8 & 0xFF00);
        this.\u00e9 = (array[8] & 0xFF) + (array[9] << 8 & 0xFF00);
        final boolean b = (array[10] & 0x80) == 0x80;
        final int n2 = (int)Math.pow(2.0, (array[10] & 0x7) + 1);
        int n3 = 13;
        if (b) {
            array2 = new byte[n2 * 3];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = array[n3++];
            }
        }
        final byte[] array3 = new byte[13];
        for (int j = 0; j < 13; ++j) {
            array3[j] = array[j];
        }
        while ((array[n3] & 0xFF) != 0x3B) {
            int n4 = n3;
            if (array[n3] == 33) {
                ++n3;
                final int n5 = array[n3++] & 0xFF;
                if (n5 == 255) {
                    n3 = this.e(array, n3);
                }
                else if (n5 == 249) {
                    n3 = this.c(array, n3);
                    o = new byte[n3 - n4];
                    for (int k = 0; k < o.length; ++k, ++n4) {
                        o[k] = array[n4];
                    }
                }
                else if (n5 == 254) {
                    int n6;
                    for (n6 = n3; array[n6] != 0; n6 += (array[n6] & 0xFF), ++n6) {}
                    n3 = ++n6;
                }
                else {
                    if (n5 != 1) {
                        continue;
                    }
                    int n7;
                    for (n7 = n3, n7 += 14; array[n7] != 0; n7 += (array[n7] & 0xFF), ++n7) {}
                    n3 = ++n7;
                }
            }
            else if (array[n3] == 44) {
                ++n;
                final int a;
                n3 = (a = this.a(array, n3));
                byte[] array4;
                if (this.\u00ec) {
                    array4 = new byte[a - n4 - this.\u00e7.length];
                    for (int l = 0; l < 9; ++l) {
                        array4[l] = array[n4++];
                    }
                    array4[9] = 0;
                    int n8 = ++n4 + this.\u00e7.length;
                    for (int n9 = 10; n9 < array4.length; ++n9) {
                        array4[n9] = array[n8++];
                    }
                }
                else {
                    array4 = new byte[a - n4];
                    for (int n10 = 0; n10 < array4.length; ++n10, ++n4) {
                        array4[n10] = array[n4];
                    }
                }
                int n11;
                byte[] array5;
                if (this.\u00ec) {
                    n11 = array3.length + this.\u00e7.length;
                    array5 = new byte[n11];
                    for (int n12 = 0; n12 < array3.length; ++n12) {
                        array5[n12] = array3[n12];
                    }
                    array5[10] = this.\u00e8;
                    for (int n13 = 0, length = array3.length; n13 < this.\u00e7.length; array5[length] = this.\u00e7[n13++], ++length) {}
                }
                else {
                    n11 = array3.length + array2.length;
                    array5 = new byte[n11];
                    for (int n14 = 0; n14 < array3.length; ++n14) {
                        array5[n14] = array3[n14];
                    }
                    int length2 = array3.length;
                    for (int n15 = 0; n15 < array2.length; ++n15) {
                        array5[length2++] = array2[n15];
                    }
                }
                if (o != null) {
                    n11 += o.length;
                }
                final byte[] array6 = new byte[n11 + array4.length + 1];
                for (int n16 = 0; n16 < array5.length; ++n16) {
                    array6[n16] = array5[n16];
                }
                if (o != null) {
                    System.arraycopy(o, 0, array6, array5.length, o.length);
                }
                for (int n17 = 0, n18 = (o != null) ? (array5.length + o.length) : array5.length; n17 < array4.length; ++n17, ++n18) {
                    array6[n18] = array4[n17];
                }
                array6[array6.length - 1] = 59;
                o = null;
                final a a2 = new a();
                a2.C(this.\u00df.getToolkit().createImage(this.Y(array6)));
                mediaTracker.addImage(a2.F(), 0);
                a2.H(this.\u00ee);
                a2.D(this.\u00ef);
                a2.G(this.\u00e1);
                a2.J(this.\u00e2);
                vector.addElement(a2);
                final boolean b2 = false;
                this.\u00e2 = (b2 ? 1 : 0);
                this.\u00e1 = (b2 ? 1 : 0);
                this.\u00ef = (b2 ? 1 : 0);
                this.\u00ee = (b2 ? 1 : 0);
            }
            else {
                if (++n3 <= array.length) {
                    continue;
                }
                break;
            }
        }
        if (vector.size() == 0) {
            throw new Exception("GIF error : No frames found !");
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        return vector;
    }
    
    private int e(final byte[] array, int n) {
        ++n;
        final String s = new String(array, 0, n, 8);
        n += 8;
        n += 3;
        final byte b = array[n++];
        if (s.toLowerCase().equals("netscape")) {
            ++n;
            this.\u00e6 = (array[n] & 0xFF) + (array[n + 1] << 8 & 0xFF00);
            n += 2;
        }
        else {
            n += b;
        }
        return ++n;
    }
    
    private int c(final byte[] array, int n) {
        ++n;
        this.\u00e2 = (array[n] & 0x1C) >> 2;
        ++n;
        this.\u00e1 = (array[n] & 0xFF) + (array[n + 1] << 8 & 0xFF00);
        n += 2;
        ++n;
        return ++n;
    }
    
    private int c(final byte[] array) {
        if (array.length < 1 + this.\u00e4) {
            return -1;
        }
        return array[this.\u00e4++] & 0xFF;
    }
    
    private int e(final byte[] array, final byte[] array2, final int n) {
        if (array.length < n + this.\u00e4) {
            return -1;
        }
        System.arraycopy(array, this.\u00e4, array2, 0, n);
        this.\u00e4 += n;
        return n;
    }
    
    private int b(final byte[] array, int n) {
        while (array[n] != 0) {
            n += (array[n] & 0xFF);
            ++n;
        }
        return ++n;
    }
    
    private int a(final byte[] array, int n) {
        this.\u00ee = (array[n + 1] & 0xFF) + (array[n + 2] << 8 & 0xFF00);
        this.\u00ef = (array[n + 3] & 0xFF) + (array[n + 4] << 8 & 0xFF00);
        n += 9;
        this.\u00ec = ((array[n] & 0x80) == 0x80);
        if (this.\u00ec) {
            this.\u00e8 = array[n];
            final int n2 = (int)Math.pow(2.0, (array[n] & 0x7) + 1);
            ++n;
            this.\u00e7 = new byte[n2 * 3];
            for (int i = 0; i < n2 * 3; ++i) {
                this.\u00e7[i] = array[n++];
            }
            ++n;
        }
        else {
            ++n;
            ++n;
            this.\u00e7 = null;
        }
        while (array[n] != 0) {
            n += (array[n] & 0xFF);
            ++n;
        }
        return ++n;
    }
    
    private int Z(final byte[] array, int n) {
        for (n += 14; array[n] != 0; n += (array[n] & 0xFF), ++n) {}
        return ++n;
    }
}
