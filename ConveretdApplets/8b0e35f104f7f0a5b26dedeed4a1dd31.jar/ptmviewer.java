import java.util.Hashtable;
import java.util.Vector;
import java.util.Properties;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.applet.AppletStub;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptmviewer extends Applet
{
    String PTViewer;
    ptviewer pv;
    String filename;
    String order;
    int[] y_LU;
    int[] w0;
    int[] w1;
    boolean hasCube;
    boolean hasPano;
    boolean hasHotspots;
    boolean hasJpeg;
    boolean hasTiff;
    boolean antialias;
    double max_oversampling;
    static final int UNSIGNED = 1;
    static final int SIGNED = 2;
    static final int FLOATING_POINT = 3;
    static final int SHORT = 3;
    static final int LONG = 4;
    public static final int IMAGE_WIDTH = 256;
    public static final int IMAGE_LENGTH = 257;
    public static final int BITS_PER_SAMPLE = 258;
    public static final int COMPRESSION = 259;
    public static final int PHOTO_INTERP = 262;
    public static final int STRIP_OFFSETS = 273;
    public static final int ROWS_PER_STRIP = 278;
    public static final int STRIP_BYTE_COUNT = 279;
    public static final int X_RESOLUTION = 282;
    public static final int Y_RESOLUTION = 283;
    public static final int RESOLUTION_UNIT = 296;
    public static final int COLOR_MAP = 320;
    public static final int SAMPLES_PER_PIXEL = 277;
    private boolean f1;
    private int f2;
    private int f3;
    private int f4;
    private int f5;
    private int f6;
    private int[] f7;
    private int[] f8;
    
    public ptmviewer() {
        this.PTViewer = "ptviewer";
        this.pv = null;
        this.filename = "image";
        this.order = null;
        this.y_LU = null;
        this.w0 = null;
        this.w1 = null;
        this.hasCube = false;
        this.hasPano = false;
        this.hasHotspots = false;
        this.hasJpeg = false;
        this.hasTiff = false;
        this.antialias = false;
        this.max_oversampling = 1.5;
    }
    
    public ptmviewer(final ptviewer pv, final String s) {
        this.PTViewer = "ptviewer";
        this.pv = null;
        this.filename = "image";
        this.order = null;
        this.y_LU = null;
        this.w0 = null;
        this.w1 = null;
        this.hasCube = false;
        this.hasPano = false;
        this.hasHotspots = false;
        this.hasJpeg = false;
        this.hasTiff = false;
        this.antialias = false;
        this.max_oversampling = 1.5;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public void init() {
        final String parameter;
        if ((parameter = this.getParameter("file")) != null) {
            this.filename = parameter;
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("order")) != null) {
            this.order = parameter2;
        }
        if (this.getParameter("antialias") != null) {
            this.antialias = true;
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("oversampling")) != null) {
            this.max_oversampling = Double.valueOf(parameter3);
        }
    }
    
    public void start() {
        if (this.pv != null) {
            this.loadQTVR();
        }
    }
    
    void loadQTVR() {
        this.pv.percent[0] = 0;
        this.pv.repaint();
        final byte[] file_read;
        if ((file_read = this.pv.file_read(this.filename, this.pv.percent)) == null) {
            this.pv.fatal = true;
            return;
        }
        if (!this.check_qtvr(file_read)) {
            System.out.println("This doesn't seem to be a Quicktime VR-file");
            this.pv.fatal = true;
            return;
        }
        if (!this.hasJpeg) {
            System.out.println("Images in QTVR-file must use Photo-JPEG format.");
            this.pv.fatal = true;
            return;
        }
        this.set_qtvr_properties(this.get_qtvr_properties(file_read));
        if (!this.hasPano) {
            this.pv.show_pdata = false;
            this.displayObjectQTVR(file_read);
            return;
        }
        if (this.hasCube) {
            this.pv.show_pdata = false;
            this.displayCubicQTVR(file_read);
            return;
        }
        this.displayCylindricalQTVR(file_read);
    }
    
    void displayObjectQTVR(final byte[] array) {
        if (array != null) {
            final Image[] get_jpeg_images;
            if ((get_jpeg_images = this.get_jpeg_images(array)) == null || get_jpeg_images.length == 0) {
                System.out.println("Could not read QTVR-file.");
                this.pv.fatal = true;
                return;
            }
            final String string = "{code=ptobject.class}{nhor=" + get_jpeg_images.length + "}";
            final ptobject ptobject = new ptobject(this.pv, get_jpeg_images, string);
            this.pv.app_properties.addElement(string);
            this.pv.applets.put(string, ptobject);
            ptobject.init();
            ptobject.start();
        }
    }
    
    void displayCylindricalQTVR(final byte[] array) {
        if (array != null) {
            final Image[] get_jpeg_images;
            if ((get_jpeg_images = this.get_jpeg_images(array)) == null || get_jpeg_images.length == 0) {
                System.out.println("Could not read QTVR-file.");
                this.pv.fatal = true;
                return;
            }
            final int n = get_jpeg_images[0].getHeight(null) * get_jpeg_images.length;
            final int width = get_jpeg_images[0].getWidth(null);
            final int n2 = (int)(n / 3.141592653589793 * Math.atan(width / n * 3.141592653589793) + 0.5);
            int[][] pdata = null;
            try {
                pdata = new int[n2][n];
            }
            catch (Exception ex) {}
            if (pdata == null) {
                System.out.println("Not enough memory to load panorama");
                this.pv.fatal = true;
                return;
            }
            final int[] array2 = new int[get_jpeg_images[0].getHeight(null) * width];
            byte[][] get_tiff_hsimages = new byte[get_jpeg_images.length][];
            for (int i = 0; i < get_jpeg_images.length; ++i) {
                get_tiff_hsimages[i] = null;
            }
            if (this.hasHotspots && this.hasTiff) {
                get_tiff_hsimages = this.get_tiff_hsimages(array, get_jpeg_images.length);
            }
            for (int j = 0; j < get_jpeg_images.length; ++j) {
                this.pv.percent[0] = 80 + (j + 1) * 20 / get_jpeg_images.length;
                this.pv.repaint();
                final Image image = get_jpeg_images[j];
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, image.getWidth(null), image.getHeight(null), array2, 0, image.getWidth(null));
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex2) {
                    return;
                }
                this.insert_cyltile(j, array2, width, pdata, get_tiff_hsimages[j]);
            }
            this.y_LU = null;
            this.w0 = null;
            this.w1 = null;
            this.pv.pdata = pdata;
        }
    }
    
    void insert_cyltile(final int n, final int[] array, final int n2, final int[][] array2, final byte[] array3) {
        if (array == null || array2 == null) {
            return;
        }
        final int length = array2.length;
        final int length2 = array2[0].length;
        final int n3 = array.length / n2;
        if (this.y_LU == null) {
            this.y_LU = new int[length];
            this.w0 = new int[length];
            this.w1 = new int[length];
            final double n5;
            final double n4 = (n5 = length2 / 6.283185307179586) * 256.0;
            final double n6 = length / 2.0 + 0.5;
            final int n7 = n2 << 7;
            for (int i = 0; i < length; ++i) {
                final int n8 = (int)(n4 * Math.tan((i - n6) / n5)) + n7;
                this.w1[i] = (n8 & 0xFF);
                this.w0[i] = 255 - this.w1[i];
                this.y_LU[i] = n8 >> 8;
                if (this.y_LU[i] < 0) {
                    this.y_LU[i] = 0;
                }
                if (this.y_LU[i] >= n2 - 1) {
                    this.y_LU[i] = n2 - 2;
                }
            }
        }
        final int n9 = length2 - 1 - n * n3;
        for (int j = 0; j < length; ++j) {
            for (int k = 0, n10 = 0; k < n3; ++k, n10 += n2) {
                final int n11 = n10 + this.y_LU[j];
                final int n12 = array[n11];
                final int n13 = array[n11 + 1];
                final int n14 = (array3 == null) ? 0 : (array3[n11] << 24);
                int n15;
                if ((n15 = (n12 >> 16 & 0xFF) * this.w0[j] + (n13 >> 16 & 0xFF) * this.w1[j] >> 8) > 255) {
                    n15 = 255;
                }
                if (n15 < 0) {
                    n15 = 0;
                }
                int n16;
                if ((n16 = (n12 >> 8 & 0xFF) * this.w0[j] + (n13 >> 8 & 0xFF) * this.w1[j] >> 8) > 255) {
                    n16 = 255;
                }
                if (n16 < 0) {
                    n16 = 0;
                }
                int n17;
                if ((n17 = (n12 & 0xFF) * this.w0[j] + (n13 & 0xFF) * this.w1[j] >> 8) > 255) {
                    n17 = 255;
                }
                if (n17 < 0) {
                    n17 = 0;
                }
                array2[j][n9 - k] = n14 + (n15 << 16) + (n16 << 8) + n17;
            }
        }
    }
    
    void displayCubicQTVR(final byte[] array) {
        if (array != null) {
            final Image[] get_jpeg_images;
            if ((get_jpeg_images = this.get_jpeg_images(array)) == null || get_jpeg_images.length != 6) {
                System.out.println("Could not read QTVR-file.");
                this.pv.fatal = true;
                return;
            }
            final String[] array2 = new String[6];
            for (int i = 0; i < 6; ++i) {
                array2[i] = "{code=ptzoom.class}{fov=90.2}";
            }
            final StringBuffer sb = new StringBuffer();
            final String[] array3 = array2;
            final int n = 0;
            array3[n] = sb.append(array3[n]).append("{pan=180}").toString();
            final StringBuffer sb2 = new StringBuffer();
            final String[] array4 = array2;
            final int n2 = 1;
            array4[n2] = sb2.append(array4[n2]).append("{pan=-90}").toString();
            final StringBuffer sb3 = new StringBuffer();
            final String[] array5 = array2;
            final int n3 = 2;
            array5[n3] = sb3.append(array5[n3]).append("{pan=0}").toString();
            final StringBuffer sb4 = new StringBuffer();
            final String[] array6 = array2;
            final int n4 = 3;
            array6[n4] = sb4.append(array6[n4]).append("{pan=90}").toString();
            final StringBuffer sb5 = new StringBuffer();
            final String[] array7 = array2;
            final int n5 = 4;
            array7[n5] = sb5.append(array7[n5]).append("{pan=180}{tilt=90}").toString();
            final StringBuffer sb6 = new StringBuffer();
            final String[] array8 = array2;
            final int n6 = 5;
            array8[n6] = sb6.append(array8[n6]).append("{pan=180}{tilt=-90}").toString();
            if (this.antialias) {
                for (int j = 0; j < 6; ++j) {
                    final StringBuffer sb7 = new StringBuffer();
                    final String[] array9 = array2;
                    final int n7 = j;
                    array9[n7] = sb7.append(array9[n7]).append("{antialias=true}{oversampling=").append(this.max_oversampling).append("}").toString();
                }
            }
            byte[][] get_tiff_hsimages = new byte[6][];
            for (int k = 0; k < 6; ++k) {
                get_tiff_hsimages[k] = null;
            }
            if (this.hasHotspots && this.hasTiff) {
                get_tiff_hsimages = this.get_tiff_hsimages(array, 6);
            }
            for (int l = 0; l < 6; ++l) {
                this.pv.percent[0] = 80 + (l << 2);
                this.pv.repaint();
                final ptzoom ptzoom = new ptzoom(this.pv, get_jpeg_images[l], get_tiff_hsimages[l], array2[l]);
                get_tiff_hsimages[l] = null;
                this.pv.app_properties.addElement(array2[l]);
                this.pv.applets.put(array2[l], ptzoom);
                ptzoom.init();
                ptzoom.start();
            }
        }
    }
    
    Properties get_qtvr_properties(final byte[] array) {
        final Properties properties = new Properties();
        final byte[] array2 = { 112, 108, 117, 103 };
        for (int n = array.length - array2.length, i = 0; i < n; ++i) {
            if (array[i] == array2[0] && array[i + 1] == array2[1] && array[i + 2] == array2[2] && array[i + 3] == array2[3]) {
                i += 4;
                int n2;
                for (n2 = i; n2 < array.length && array[n2] != 0; ++n2) {}
                if (n2 > i) {
                    final byte[] array3 = new byte[n2 - i];
                    System.arraycopy(array, i, array3, 0, n2 - i);
                    final String s;
                    final int index;
                    if ((index = (s = new String(array3)).indexOf(61)) > 0 && index < s.length() - 1) {
                        ((Hashtable<String, String>)properties).put(s.substring(0, index).toLowerCase(), s.substring(index + 1));
                    }
                }
            }
        }
        return properties;
    }
    
    Image[] get_jpeg_images(final byte[] array) {
        final byte[] array2 = { -1, -40, -1, -32, 0, 16, 74, 70, 73, 70, 0 };
        final Vector vector = new Vector<Integer>();
        for (int n = array.length - array2.length, i = 0; i < n; ++i) {
            if (array[i] == array2[0] && array[i + 1] == array2[1] && array[i + 2] == array2[2] && array[i + 3] == array2[3] && array[i + 4] == array2[4] && array[i + 5] == array2[5] && array[i + 6] == array2[6] && array[i + 7] == array2[7] && array[i + 8] == array2[8] && array[i + 9] == array2[9] && array[i + 10] == array2[10]) {
                vector.addElement(new Integer(i));
            }
        }
        if (vector.size() == 0) {
            return null;
        }
        final Vector vector2 = new Vector<Integer>();
        for (int j = 0; j < vector.size(); ++j) {
            final int n2 = vector.elementAt(j) + array2.length;
            int n3;
            if (j < vector.size() - 1) {
                n3 = vector.elementAt(j + 1) - 2;
            }
            else {
                n3 = array.length - 2;
            }
            for (int k = n2; k <= n3; ++k) {
                if (array[k] == -1 && array[k + 1] == -39) {
                    vector2.addElement(new Integer(k + 2));
                    break;
                }
            }
            if (vector2.size() != j + 1) {
                System.out.println("Could not read QTVR file");
                return null;
            }
        }
        final Vector vector3 = new Vector<Image>();
        for (int l = 0; l < vector.size(); ++l) {
            final int intValue = vector.elementAt(l);
            final int n4;
            final byte[] array3 = new byte[n4 = vector2.elementAt(l) - intValue];
            System.arraycopy(array, intValue, array3, 0, n4);
            vector3.addElement(this.pv.bufferToImage(array3));
        }
        int n5 = 0;
        for (int n6 = 0; n6 < vector3.size(); ++n6) {
            final Image image = vector3.elementAt(n6);
            final int n7;
            if ((n7 = image.getWidth(null) * image.getHeight(null)) > n5) {
                n5 = n7;
            }
        }
        for (int n8 = 0; n8 < vector3.size(); ++n8) {
            final Image image2 = vector3.elementAt(n8);
            if (image2.getWidth(null) * image2.getHeight(null) < n5) {
                vector3.removeElementAt(n8--);
            }
        }
        if (this.order != null) {
            this.ordertiles(vector3, this.order);
        }
        final Image[] array4 = new Image[vector3.size()];
        vector3.copyInto(array4);
        return array4;
    }
    
    void ordertiles(final Vector vector, final String s) {
        final Vector vector2 = (Vector)vector.clone();
        if (this.pv != null) {
            for (int i = 0; i < vector2.size(); ++i) {
                final String arg;
                if ((arg = this.pv.getArg(i, s)) != null) {
                    vector.setElementAt(vector2.elementAt(Integer.parseInt(arg)), i);
                }
            }
        }
    }
    
    byte[][] get_tiff_hsimages(final byte[] array, final int n) {
        final byte[][] array2 = new byte[n][];
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            array2[i] = null;
        }
        for (int j = 0; j < array.length; ++j) {
            final int readtiffHeader;
            if ((readtiffHeader = this.readtiffHeader(array, j)) > 0) {
                if (this.readtiffdirectory(array, j + readtiffHeader)) {
                    array2[n2] = this.readtiffdata(array);
                    if (array2[n2] != null) {
                        ++n2;
                    }
                    if (n2 >= n) {
                        break;
                    }
                }
                j += readtiffHeader;
            }
        }
        return array2;
    }
    
    final int getInt(final byte[] array) throws IndexOutOfBoundsException {
        final int n = array[this.f2++] & 0xFF;
        final int n2 = array[this.f2++] & 0xFF;
        final int n3 = array[this.f2++] & 0xFF;
        final int n4 = array[this.f2++] & 0xFF;
        if (this.f1) {
            return (n4 << 24) + (n3 << 16) + (n2 << 8) + (n << 0);
        }
        return (n << 24) + (n2 << 16) + (n3 << 8) + n4;
    }
    
    final int getShort(final byte[] array) throws IndexOutOfBoundsException {
        final int n = array[this.f2++] & 0xFF;
        final int n2 = array[this.f2++] & 0xFF;
        if (this.f1) {
            return (n2 << 8) + n;
        }
        return (n << 8) + n2;
    }
    
    int getValue(final int n, final int n2, final byte[] array) throws IndexOutOfBoundsException {
        int n3;
        if (n == 3 && n2 == 1) {
            n3 = this.getShort(array);
            this.getShort(array);
        }
        else {
            n3 = this.getInt(array);
        }
        return n3;
    }
    
    int readtiffHeader(final byte[] array, final int n) {
        this.f3 = n;
        this.f2 = n;
        int int1;
        try {
            final int short1;
            if ((short1 = this.getShort(array)) == 18761) {
                this.f1 = true;
            }
            else {
                if (short1 != 19789) {
                    return -1;
                }
                this.f1 = false;
            }
            if (this.getShort(array) != 42) {
                return -1;
            }
            int1 = this.getInt(array);
        }
        catch (IndexOutOfBoundsException ex) {
            return -1;
        }
        this.f4 = 0;
        this.f5 = 0;
        this.f6 = 0;
        this.f7 = null;
        this.f8 = null;
        return int1;
    }
    
    boolean readtiffdirectory(final byte[] array, final int f2) {
        this.f2 = f2;
        int short1;
        try {
            short1 = this.getShort(array);
        }
        catch (IndexOutOfBoundsException ex) {
            return false;
        }
        if (short1 < 1) {
            return false;
        }
        for (int i = 0; i < short1; ++i) {
            int short2;
            int int1;
            int value;
            try {
                short2 = this.getShort(array);
                final int short3 = this.getShort(array);
                int1 = this.getInt(array);
                value = this.getValue(short3, int1, array);
            }
            catch (IndexOutOfBoundsException ex2) {
                return false;
            }
            switch (short2) {
                case 256: {
                    this.f4 = value;
                    break;
                }
                case 257: {
                    this.f5 = value;
                    break;
                }
                case 278: {
                    this.f6 = value;
                    break;
                }
                case 279: {
                    this.f8 = new int[int1];
                    if (int1 == 1) {
                        this.f8[0] = value;
                        break;
                    }
                    final int f3 = this.f2;
                    this.f2 = this.f3 + value;
                    for (int j = 0; j < int1; ++j) {
                        try {
                            this.f8[j] = this.getInt(array);
                        }
                        catch (IndexOutOfBoundsException ex3) {
                            return false;
                        }
                    }
                    this.f2 = f3;
                    break;
                }
                case 273: {
                    this.f7 = new int[int1];
                    if (int1 == 1) {
                        this.f7[0] = value;
                        break;
                    }
                    final int f4 = this.f2;
                    this.f2 = this.f3 + value;
                    for (int k = 0; k < int1; ++k) {
                        try {
                            this.f7[k] = this.getInt(array);
                        }
                        catch (IndexOutOfBoundsException ex4) {
                            return false;
                        }
                    }
                    this.f2 = f4;
                    break;
                }
                case 258: {
                    if (value != 8) {
                        return false;
                    }
                    break;
                }
                case 259: {
                    if (value != 32773) {
                        return false;
                    }
                    break;
                }
            }
        }
        return this.f4 > 0 && this.f5 > 0 && this.f6 > 0 && this.f7 != null && this.f8 != null && this.f7.length == this.f8.length;
    }
    
    byte[] readtiffdata(final byte[] array) {
        final byte[] array2 = new byte[this.f4 * this.f5];
        final int n = this.f6 * this.f4;
        for (int i = 0; i < this.f7.length; ++i) {
            final int n2;
            if ((n2 = i * n) >= array2.length) {
                return null;
            }
            int length;
            if ((length = n2 + n) > array2.length) {
                length = array2.length;
            }
            final int n3;
            if ((n3 = this.f3 + this.f7[i]) >= array.length) {
                return null;
            }
            final int n4;
            if ((n4 = n3 + this.f8[i]) > array.length) {
                return null;
            }
            if (!this.unpackstripe(array, n3, n4, array2, n2, length)) {
                System.out.println("Error unpacking strip " + i);
                return null;
            }
        }
        this.f7 = null;
        this.f8 = null;
        return array2;
    }
    
    boolean unpackstripe(final byte[] array, int i, final int n, final byte[] array2, int j, final int n2) {
        while (j < n2) {
            if (i >= n) {
                return false;
            }
            final byte b;
            if ((b = array[i++]) >= 0) {
                final int n3;
                if ((n3 = i + b + 1) > n || j + b + 1 > n2) {
                    return false;
                }
                while (i < n3) {
                    array2[j++] = array[i++];
                }
            }
            else {
                if (b == -128) {
                    continue;
                }
                final int n4;
                if ((n4 = j - b + 1) > n2) {
                    return false;
                }
                if (i >= n) {
                    return false;
                }
                for (byte b2 = array[i++]; j < n4; array2[j++] = b2) {}
            }
        }
        return true;
    }
    
    void set_qtvr_properties(final Properties properties) {
        if (properties == null || this.pv == null) {
            return;
        }
        double doubleValue = 480.0;
        double doubleValue2 = 336.0;
        this.pv.yaw = 180.0 - this.pv.yaw;
        final String property;
        if ((property = properties.getProperty("width")) != null) {
            doubleValue = Double.valueOf(property);
        }
        final String property2;
        if ((property2 = properties.getProperty("height")) != null) {
            doubleValue2 = Double.valueOf(property2);
        }
        final String property3;
        if ((property3 = properties.getProperty("fov")) != null) {
            this.pv.hfov = 114.59155902616465 * Math.atan(doubleValue / doubleValue2 * Math.tan(Double.valueOf(property3) * 3.141592653589793 / 360.0));
        }
        final String property4;
        if ((property4 = properties.getProperty("pan")) != null) {
            this.pv.yaw = 180.0 - Double.valueOf(property4);
        }
        final String property5;
        if ((property5 = properties.getProperty("tilt")) != null) {
            this.pv.pitch = Double.valueOf(property5);
        }
        for (int i = 0; i <= this.pv.hotspots.size(); ++i) {
            final String property6;
            if ((property6 = properties.getProperty("hotspot" + (i + 1))) != null && i >= this.pv.hotspots.size()) {
                final String property7;
                if ((property7 = properties.getProperty("target" + (i + 1))) != null) {
                    if (property7.equalsIgnoreCase("myself")) {
                        this.pv.hotspots.addElement("x" + i + " u'ptviewer:newPano({file=" + property6 + "})'");
                    }
                    else {
                        this.pv.hotspots.addElement("x" + i + " u'" + property6 + "' t'" + property7 + "'");
                    }
                }
                else {
                    this.pv.hotspots.addElement("x" + i + " u'" + property6 + "'");
                }
            }
        }
    }
    
    boolean check_qtvr(final byte[] array) {
        final byte[] array2 = { -1, -40, -1, -32, 0, 16, 74, 70, 73, 70, 0 };
        final boolean b = true;
        for (int n = array.length - array2.length, i = 0; i < n; ++i) {
            if (array[i] == array2[0] && array[i + 1] == array2[1] && array[i + 2] == array2[2] && array[i + 3] == array2[3] && array[i + 4] == array2[4] && array[i + 5] == array2[5] && array[i + 6] == array2[6] && array[i + 7] == array2[7] && array[i + 8] == array2[8] && array[i + 9] == array2[9] && array[i + 10] == array2[10]) {
                return b;
            }
            if (array[i] == 99 && array[i + 1] == 117 && array[i + 2] == 98 && array[i + 3] == 101) {
                this.hasCube = true;
            }
            else if (array[i] == 104 && array[i + 1] == 111 && array[i + 2] == 116 && array[i + 3] == 116) {
                this.hasHotspots = true;
            }
            else if (array[i] == 112 && array[i + 1] == 97 && array[i + 2] == 110 && array[i + 3] == 111) {
                this.hasPano = true;
            }
            else if (array[i] == 106 && array[i + 1] == 112 && array[i + 2] == 101 && array[i + 3] == 103) {
                this.hasJpeg = true;
            }
            else if (array[i] == 116 && array[i + 1] == 105 && array[i + 2] == 102 && array[i + 3] == 102) {
                this.hasTiff = true;
            }
        }
        return b;
    }
}
