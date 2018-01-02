// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.color.ColorSpace;
import java.awt.image.ComponentColorModel;
import java.awt.image.WritableRaster;
import java.awt.image.DataBuffer;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.util.Locale;
import java.text.MessageFormat;
import java.awt.image.SampleModel;
import java.util.zip.DataFormatException;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import java.io.IOException;
import java.io.InputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.io.ByteArrayInputStream;
import java.awt.image.Raster;
import java.util.zip.Inflater;
import com.sun.image.codec.jpeg.JPEGDecodeParam;

public class h extends a
{
    public static final int \u00e2 = 1;
    public static final int \u00cb = 2;
    public static final int x = 3;
    public static final int \u00cf = 4;
    public static final int \u00c2 = 5;
    public static final int \u00d8 = 6;
    public static final int \u00dc = 7;
    public static final int ¥ = 32773;
    public static final int \u00c5 = 32946;
    private static final int \u00e6 = -1;
    private static final int \u00d6 = 0;
    private static final int \u00ce = 1;
    private static final int ¤ = 2;
    private static final int z = 3;
    private static final int \u00e7 = 4;
    private static final int \u00df = 5;
    private static final int \u00c6 = 6;
    private static final int ¢ = 7;
    private static final int \u00c8 = 8;
    private static final int \u00c4 = 347;
    private static final int \u00cc = 530;
    X \u00e5;
    private boolean \u00c1;
    int \u00d1;
    int \u00ca;
    int \u00c9;
    long[] \u00db;
    long[] \u00d2;
    char[] \u00d9;
    int \u00c0;
    int ª;
    byte[] \u00e3;
    int \u00cd;
    int \u00d4;
    int \u00d0;
    long \u00e0;
    long y;
    int º;
    int \u00d3;
    JPEGDecodeParam \u00dd;
    boolean \u00d5;
    Inflater \u00de;
    boolean \u00e4;
    int \u00e1;
    boolean \u00c7;
    int \u00c3;
    boolean £;
    private w µ;
    private l \u00da;
    static /* synthetic */ Class array$I;
    
    private static final Raster A(final byte[] array, final JPEGDecodeParam jpegDecodeParam, final boolean b, final int n, final int n2) {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final JPEGImageDecoder jpegImageDecoder = (jpegDecodeParam == null) ? JPEGCodec.createJPEGDecoder((InputStream)byteArrayInputStream) : JPEGCodec.createJPEGDecoder((InputStream)byteArrayInputStream, jpegDecodeParam);
        Raster raster;
        try {
            raster = (b ? jpegImageDecoder.decodeAsBufferedImage().getWritableTile(0, 0) : jpegImageDecoder.decodeAsRaster());
        }
        catch (IOException ex) {
            throw new RuntimeException(m.A("TIFFImage13"));
        }
        return raster.createTranslatedChild(n, n2);
    }
    
    private final void A(final byte[] input, final byte[] array) {
        this.\u00de.setInput(input);
        try {
            this.\u00de.inflate(array);
        }
        catch (DataFormatException ex) {
            throw new RuntimeException(m.A("TIFFImage17") + ": " + ex.getMessage());
        }
        this.\u00de.reset();
    }
    
    private static final SampleModel A(final int n, final int n2, final int n3, final int n4, final int n5, final int[] array) {
        SampleModel a = null;
        if (n == 4) {
            try {
                a = (SampleModel)Class.forName("javax.media.jai.RasterFactory").getMethod("createPixelInterleavedSampleModel", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, (h.array$I == null) ? (h.array$I = class$("[I")) : h.array$I).invoke(null, new Integer(n), new Integer(n2), new Integer(n3), new Integer(n4), new Integer(n5), array);
            }
            catch (Exception ex) {}
        }
        if (n != 4 || a == null) {
            a = U.A(n, n2, n3, n4, n5, array);
        }
        return a;
    }
    
    private final long[] A(final p p) {
        long[] f;
        if (p.H() == 3) {
            final char[] l = p.L();
            f = new long[l.length];
            for (int i = 0; i < l.length; ++i) {
                f[i] = (l[i] & '\uffff');
            }
        }
        else {
            if (p.H() != 4) {
                throw new RuntimeException();
            }
            f = p.F();
        }
        return f;
    }
    
    private p A(final F f, final int n, final String s) {
        final p b = f.B(n);
        if (b == null) {
            final MessageFormat messageFormat = new MessageFormat(m.A("TIFFImage5"));
            messageFormat.setLocale(Locale.getDefault());
            throw new RuntimeException(messageFormat.format(new Object[] { s }));
        }
        return b;
    }
    
    public h(final X \u00e5, I i, final int n) throws IOException {
        this.\u00dd = null;
        this.\u00d5 = false;
        this.\u00de = null;
        this.\u00c7 = false;
        this.µ = null;
        this.\u00da = null;
        this.\u00e5 = \u00e5;
        if (i == null) {
            i = new I();
        }
        this.£ = i.C();
        final F f = (i.B() == null) ? new F(\u00e5, n) : new F(\u00e5, i.B(), n);
        this.G.put("tiff_directory", f);
        final p b = f.B(277);
        final int \u00ed = (b == null) ? 1 : ((int)b.D(0));
        final p b2 = f.B(284);
        final char[] array;
        if (b2 == null) {
            array = new char[] { '\u0001' };
        }
        else {
            b2.L();
        }
        if (array[0] != '\u0001' && \u00ed != 1) {
            throw new RuntimeException(m.A("TIFFImage0"));
        }
        final p b3 = f.B(258);
        char[] l;
        if (b3 != null) {
            l = b3.L();
        }
        else {
            l = new char[] { '\u0001' };
            for (int j = 1; j < l.length; ++j) {
                if (l[j] != l[0]) {
                    throw new RuntimeException(m.A("TIFFImage1"));
                }
            }
        }
        this.\u00c0 = l[0];
        final p b4 = f.B(339);
        char[] k;
        if (b4 != null) {
            k = b4.L();
            for (int n2 = 1; n2 < k.length; ++n2) {
                if (k[n2] != k[0]) {
                    throw new RuntimeException(m.A("TIFFImage2"));
                }
            }
        }
        else {
            k = new char[] { '\u0001' };
        }
        boolean b5 = false;
        switch (this.\u00c0) {
            case 1:
            case 4:
            case 8: {
                if (k[0] != '\u0003') {
                    this.\u00c3 = 0;
                    b5 = true;
                    break;
                }
                break;
            }
            case 16: {
                if (k[0] != '\u0003') {
                    this.\u00c3 = ((k[0] == '\u0002') ? 2 : 1);
                    b5 = true;
                    break;
                }
                break;
            }
            case 32: {
                this.\u00c3 = ((k[0] == '\u0003') ? 4 : 3);
                b5 = true;
                break;
            }
        }
        if (!b5) {
            throw new RuntimeException(m.A("TIFFImage3"));
        }
        final p b6 = f.B(259);
        this.ª = ((b6 == null) ? 1 : b6.E(0));
        final int n3 = (int)this.A(f, 262, "Photometric Interpretation").D(0);
        this.\u00e1 = -1;
        switch (n3) {
            case 0: {
                this.\u00c7 = true;
            }
            case 1: {
                if (this.\u00c0 == 1 && \u00ed == 1) {
                    this.\u00e1 = 0;
                    break;
                }
                if (this.\u00c0 == 4 && \u00ed == 1) {
                    this.\u00e1 = 1;
                    break;
                }
                if (this.\u00c0 % 8 != 0) {
                    break;
                }
                if (\u00ed == 1) {
                    this.\u00e1 = 2;
                    break;
                }
                if (\u00ed == 2) {
                    this.\u00e1 = 3;
                    break;
                }
                this.\u00e1 = 8;
                break;
            }
            case 2: {
                if (this.\u00c0 % 8 != 0) {
                    break;
                }
                if (\u00ed == 3) {
                    this.\u00e1 = 5;
                    break;
                }
                if (\u00ed == 4) {
                    this.\u00e1 = 6;
                    break;
                }
                this.\u00e1 = 8;
                break;
            }
            case 3: {
                if (\u00ed == 1 && (this.\u00c0 == 4 || this.\u00c0 == 8 || this.\u00c0 == 16)) {
                    this.\u00e1 = 4;
                    break;
                }
                break;
            }
            case 4: {
                if (this.\u00c0 == 1 && \u00ed == 1) {
                    this.\u00e1 = 0;
                    break;
                }
                break;
            }
            case 6: {
                if (this.ª == 7 && this.\u00c0 == 8 && \u00ed == 3) {
                    this.\u00d5 = i.A();
                    this.\u00e1 = (this.\u00d5 ? 5 : 8);
                    break;
                }
                final p b7 = f.B(530);
                if (b7 != null) {
                    this.\u00d4 = b7.E(0);
                    this.\u00d0 = b7.E(1);
                }
                else {
                    final int n4 = 2;
                    this.\u00d0 = n4;
                    this.\u00d4 = n4;
                }
                if (this.\u00d4 * this.\u00d0 == 1) {
                    this.\u00e1 = 8;
                }
                else if (this.\u00c0 == 8 && \u00ed == 3) {
                    this.\u00e1 = 7;
                }
                break;
            }
            default: {
                if (this.\u00c0 % 8 == 0) {
                    this.\u00e1 = 8;
                    break;
                }
                break;
            }
        }
        if (this.\u00e1 == -1) {
            throw new RuntimeException(m.A("TIFFImage4"));
        }
        final boolean b8 = false;
        this.C = (b8 ? 1 : 0);
        this.D = (b8 ? 1 : 0);
        this.B = (int)this.A(f, 256, "Image Width").D(0);
        this.L = (int)this.A(f, 257, "Image Length").D(0);
        this.\u00cd = \u00ed;
        final p b9 = f.B(338);
        final int n5 = (b9 == null) ? 0 : ((int)b9.D(0));
        if (f.B(324) != null) {
            this.\u00c1 = true;
            this.J = (int)this.A(f, 322, "Tile Width").D(0);
            this.F = (int)this.A(f, 323, "Tile Length").D(0);
            this.\u00db = this.A(f, 324, "Tile Offsets").F();
            this.\u00d2 = this.A(this.A(f, 325, "Tile Byte Counts"));
        }
        else {
            this.\u00c1 = false;
            this.J = ((f.B(322) != null) ? ((int)f.E(322)) : this.B);
            final p b10 = f.B(278);
            if (b10 == null) {
                this.F = ((f.B(323) != null) ? ((int)f.E(323)) : this.L);
            }
            else {
                final long d = b10.D(0);
                if (d == (1L << 32) - 1L || d > this.L) {
                    this.F = this.L;
                }
                else {
                    this.F = (int)d;
                }
            }
            this.\u00db = this.A(this.A(f, 273, "Strip Offsets"));
            final p b11 = f.B(279);
            if (b11 == null) {
                if (this.ª == 1) {
                    final int n6 = (this.\u00c0 + 7) / 8 * this.\u00cd * this.B * this.L;
                    final int n7 = (this.\u00c0 + 7) / 8 * this.\u00cd * this.B * this.F;
                    int n8 = 0;
                    this.\u00d2 = new long[this.\u00db.length];
                    for (int n9 = 0; n9 < this.\u00db.length; ++n9) {
                        this.\u00d2[n9] = Math.min(n6 - n8, n7);
                        n8 += n7;
                    }
                }
                else {
                    this.A(f, 279, "Strip Byte Counts");
                }
            }
            else {
                this.\u00d2 = this.A(b11);
            }
            final int n10 = this.B * this.L * this.\u00cd * ((this.\u00c0 + 7) / 8);
            if (this.\u00d2.length == 1 && this.ª == 1 && this.\u00d2[0] > n10) {
                this.\u00d2[0] = n10;
            }
        }
        this.\u00ca = (this.B + this.J - 1) / this.J;
        this.\u00c9 = (this.L + this.F - 1) / this.F;
        this.\u00d1 = this.J * this.F * this.\u00cd;
        this.\u00e4 = f.F();
        final p b12 = f.B(266);
        if (b12 != null) {
            this.º = b12.E(0);
        }
        else {
            this.º = 1;
        }
        switch (this.ª) {
            case 1:
            case 32773: {
                break;
            }
            case 32946: {
                this.\u00de = new Inflater();
                break;
            }
            case 2:
            case 3:
            case 4: {
                if (this.\u00c0 != 1) {
                    throw new RuntimeException(m.A("TIFFImage7"));
                }
                if (this.ª == 3) {
                    final p b13 = f.B(292);
                    if (b13 != null) {
                        this.\u00e0 = b13.D(0);
                    }
                    else {
                        this.\u00e0 = 0L;
                    }
                }
                if (this.ª == 4) {
                    final p b14 = f.B(293);
                    if (b14 != null) {
                        this.y = b14.D(0);
                    }
                    else {
                        this.y = 0L;
                    }
                }
                this.µ = new w(this.º, this.J, this.F);
                break;
            }
            case 5: {
                final p b15 = f.B(317);
                if (b15 == null) {
                    this.\u00d3 = 1;
                }
                else {
                    this.\u00d3 = b15.E(0);
                    if (this.\u00d3 != 1 && this.\u00d3 != 2) {
                        throw new RuntimeException(m.A("TIFFImage8"));
                    }
                    if (this.\u00d3 == 2 && this.\u00c0 != 8) {
                        throw new RuntimeException(this.\u00c0 + m.A("TIFFImage9"));
                    }
                }
                this.\u00da = new l(this.J, this.\u00d3, \u00ed);
                break;
            }
            case 6: {
                throw new RuntimeException(m.A("TIFFImage15"));
            }
            case 7: {
                if (this.\u00c0 != 8 || ((this.\u00e1 != 2 || \u00ed != 1) && (this.\u00e1 != 4 || \u00ed != 1) && (this.\u00e1 != 5 || \u00ed != 3))) {
                    throw new RuntimeException(m.A("TIFFImage16"));
                }
                if (f.D(347)) {
                    final JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder((InputStream)new ByteArrayInputStream(f.B(347).A()));
                    jpegDecoder.decodeAsRaster();
                    this.\u00dd = jpegDecoder.getJPEGDecodeParam();
                    break;
                }
                break;
            }
            default: {
                throw new RuntimeException(m.A("TIFFImage10"));
            }
        }
        switch (this.\u00e1) {
            case 0:
            case 1: {
                this.I = new MultiPixelPackedSampleModel(this.\u00c3, this.J, this.F, this.\u00c0);
                if (this.\u00e1 == 0) {
                    final byte[] array2 = { (byte)(this.\u00c7 ? 255 : 0), (byte)(this.\u00c7 ? 0 : 255) };
                    this.H = new IndexColorModel(1, 2, array2, array2, array2);
                    break;
                }
                this.H = z.B.J.A(this.I, !this.\u00c7);
                break;
            }
            case 2:
            case 3:
            case 5:
            case 6: {
                final int[] array3 = new int[this.\u00cd];
                if (this.ª == 7) {
                    for (int n11 = 0; n11 < this.\u00cd; ++n11) {
                        array3[n11] = this.\u00cd - 1 - n11;
                    }
                }
                else {
                    for (int n12 = 0; n12 < this.\u00cd; ++n12) {
                        array3[n12] = n12;
                    }
                }
                this.I = A(this.\u00c3, this.J, this.F, this.\u00cd, this.\u00cd * this.J, array3);
                if (this.\u00e1 == 2 || this.\u00e1 == 5) {
                    this.H = z.B.J.A(this.I);
                    break;
                }
                int n13 = 1;
                if (n5 == 1) {
                    n13 = 3;
                }
                else if (n5 == 2) {
                    n13 = 2;
                }
                this.H = this.A(this.\u00c3, this.\u00cd, n5 == 1, n13);
                break;
            }
            case 7:
            case 8: {
                final int[] array4 = new int[this.\u00cd];
                for (int n14 = 0; n14 < this.\u00cd; ++n14) {
                    array4[n14] = n14;
                }
                this.I = A(this.\u00c3, this.J, this.F, this.\u00cd, this.\u00cd * this.J, array4);
                this.H = null;
                break;
            }
            case 4: {
                this.\u00d9 = this.A(f, 320, "Colormap").L();
                if (this.£) {
                    this.\u00cd = 3;
                    if (this.\u00c3 == 0) {
                        this.\u00c3 = 1;
                    }
                    this.I = U.B(this.\u00c3, this.J, this.F, this.\u00cd);
                    this.H = z.B.J.A(this.I);
                    break;
                }
                this.\u00cd = 1;
                if (this.\u00c0 == 4) {
                    this.I = new MultiPixelPackedSampleModel(0, this.J, this.F, this.\u00c0);
                }
                else if (this.\u00c0 == 8) {
                    this.I = U.B(0, this.J, this.F, this.\u00cd);
                }
                else if (this.\u00c0 == 16) {
                    this.\u00c3 = 1;
                    this.I = U.B(1, this.J, this.F, this.\u00cd);
                }
                final int n15 = this.\u00d9.length / 3;
                final byte[] array5 = new byte[n15];
                final byte[] array6 = new byte[n15];
                final byte[] array7 = new byte[n15];
                final int n16 = n15;
                final int n17 = n15 * 2;
                if (this.\u00c3 == 2) {
                    for (int n18 = 0; n18 < n15; ++n18) {
                        array5[n18] = i.A((short)this.\u00d9[n18]);
                        array6[n18] = i.A((short)this.\u00d9[n16 + n18]);
                        array7[n18] = i.A((short)this.\u00d9[n17 + n18]);
                    }
                }
                else {
                    for (int n19 = 0; n19 < n15; ++n19) {
                        array5[n19] = i.A(this.\u00d9[n19] & '\uffff');
                        array6[n19] = i.A(this.\u00d9[n16 + n19] & '\uffff');
                        array7[n19] = i.A(this.\u00d9[n17 + n19] & '\uffff');
                    }
                }
                this.H = new IndexColorModel(this.\u00c0, n15, array5, array6, array7);
                break;
            }
            default: {
                throw new RuntimeException("TIFFImage4");
            }
        }
    }
    
    public F A(final long n) throws IOException {
        return new F(this.\u00e5, n, 0);
    }
    
    public synchronized Raster getTile(final int n, final int n2) {
        if (n < 0 || n >= this.\u00ca || n2 < 0 || n2 >= this.\u00c9) {
            throw new IllegalArgumentException(m.A("TIFFImage12"));
        }
        byte[] data = null;
        short[] array = null;
        int[] data2 = null;
        float[] b = null;
        final DataBuffer dataBuffer = this.I.createDataBuffer();
        final int dataType = this.I.getDataType();
        if (dataType == 0) {
            data = ((DataBufferByte)dataBuffer).getData();
        }
        else if (dataType == 1) {
            array = ((DataBufferUShort)dataBuffer).getData();
        }
        else if (dataType == 2) {
            array = ((DataBufferShort)dataBuffer).getData();
        }
        else if (dataType == 3) {
            data2 = ((DataBufferInt)dataBuffer).getData();
        }
        else if (dataType == 4) {
            if (dataBuffer instanceof C) {
                b = ((C)dataBuffer).B();
            }
            else {
                try {
                    b = (float[])((C)dataBuffer).getClass().getMethod("getData", (Class<?>[])null).invoke(dataBuffer, (Object[])null);
                }
                catch (Exception ex) {
                    throw new RuntimeException(m.A("TIFFImage18"));
                }
            }
        }
        final WritableRaster a = U.A(this.I, dataBuffer, new Point(this.B(n), this.C(n2)));
        long g;
        try {
            g = this.\u00e5.G();
            this.\u00e5.A(this.\u00db[n2 * this.\u00ca + n]);
        }
        catch (IOException ex2) {
            throw new RuntimeException(m.A("TIFFImage13"));
        }
        final int n3 = (int)this.\u00d2[n2 * this.\u00ca + n];
        final Rectangle rectangle = new Rectangle(this.B(n), this.C(n2), this.J, this.F);
        final Rectangle rectangle2 = this.\u00c1 ? rectangle : rectangle.intersection(this.A());
        final int n4 = rectangle2.width * rectangle2.height * this.\u00cd;
        final byte[] array2 = (byte[])((this.ª != 1 || this.\u00e1 == 4) ? new byte[n3] : null);
        if (this.\u00e1 == 0) {
            try {
                if (this.ª == 32773) {
                    this.\u00e5.readFully(array2, 0, n3);
                    int n5;
                    if (rectangle2.width % 8 == 0) {
                        n5 = rectangle2.width / 8 * rectangle2.height;
                    }
                    else {
                        n5 = (rectangle2.width / 8 + 1) * rectangle2.height;
                    }
                    this.A(array2, n5, data);
                }
                else if (this.ª == 5) {
                    this.\u00e5.readFully(array2, 0, n3);
                    this.\u00da.A(array2, data, rectangle2.height);
                }
                else if (this.ª == 2) {
                    this.\u00e5.readFully(array2, 0, n3);
                    this.µ.A(data, array2, 0, rectangle2.height);
                }
                else if (this.ª == 3) {
                    this.\u00e5.readFully(array2, 0, n3);
                    this.µ.A(data, array2, 0, rectangle2.height, this.\u00e0);
                }
                else if (this.ª == 4) {
                    this.\u00e5.readFully(array2, 0, n3);
                    this.µ.B(data, array2, 0, rectangle2.height, this.y);
                }
                else if (this.ª == 32946) {
                    this.\u00e5.readFully(array2, 0, n3);
                    this.A(array2, data);
                }
                else if (this.ª == 1) {
                    this.\u00e5.readFully(data, 0, n3);
                }
                this.\u00e5.A(g);
                return a;
            }
            catch (IOException ex3) {
                throw new RuntimeException(m.A("TIFFImage13"));
            }
        }
        if (this.\u00e1 == 4) {
            if (this.\u00c0 == 16) {
                if (this.£) {
                    short[] array3 = null;
                    final int n6 = n4 / 3;
                    final int n7 = n6 * 2;
                    try {
                        if (this.ª == 32773) {
                            this.\u00e5.readFully(array2, 0, n3);
                            final byte[] array4 = new byte[n7];
                            this.A(array2, n7, array4);
                            array3 = new short[n6];
                            this.A(array4, array3, n6);
                        }
                        else if (this.ª == 5) {
                            this.\u00e5.readFully(array2, 0, n3);
                            final byte[] array5 = new byte[n7];
                            this.\u00da.A(array2, array5, rectangle2.height);
                            array3 = new short[n6];
                            this.A(array5, array3, n6);
                        }
                        else if (this.ª == 32946) {
                            this.\u00e5.readFully(array2, 0, n3);
                            final byte[] array6 = new byte[n7];
                            this.A(array2, array6);
                            array3 = new short[n6];
                            this.A(array6, array3, n6);
                        }
                        else if (this.ª == 1) {
                            array3 = new short[n3 / 2];
                            this.A(n3 / 2, array3);
                        }
                        this.\u00e5.A(g);
                    }
                    catch (IOException ex4) {
                        throw new RuntimeException(m.A("TIFFImage13"));
                    }
                    if (dataType == 1) {
                        int n8 = 0;
                        final int n9 = this.\u00d9.length / 3;
                        final int n10 = n9 * 2;
                        for (int i = 0; i < n6; ++i) {
                            final int n11 = array3[i] & 0xFFFF;
                            array[n8++] = (short)(this.\u00d9[n11 + n10] & '\uffff');
                            array[n8++] = (short)(this.\u00d9[n11 + n9] & '\uffff');
                            array[n8++] = (short)(this.\u00d9[n11] & '\uffff');
                        }
                    }
                    else if (dataType == 2) {
                        int n12 = 0;
                        final int n13 = this.\u00d9.length / 3;
                        final int n14 = n13 * 2;
                        for (int j = 0; j < n6; ++j) {
                            final int n15 = array3[j] & 0xFFFF;
                            array[n12++] = (short)this.\u00d9[n15 + n14];
                            array[n12++] = (short)this.\u00d9[n15 + n13];
                            array[n12++] = (short)this.\u00d9[n15];
                        }
                    }
                    return a;
                }
                try {
                    if (this.ª == 32773) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final int n16 = n4 * 2;
                        final byte[] array7 = new byte[n16];
                        this.A(array2, n16, array7);
                        this.A(array7, array, n4);
                    }
                    else if (this.ª == 5) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final byte[] array8 = new byte[n4 * 2];
                        this.\u00da.A(array2, array8, rectangle2.height);
                        this.A(array8, array, n4);
                    }
                    else if (this.ª == 32946) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final byte[] array9 = new byte[n4 * 2];
                        this.A(array2, array9);
                        this.A(array9, array, n4);
                    }
                    else if (this.ª == 1) {
                        this.A(n3 / 2, array);
                    }
                    this.\u00e5.A(g);
                    return a;
                }
                catch (IOException ex5) {
                    throw new RuntimeException(m.A("TIFFImage13"));
                }
            }
            if (this.\u00c0 == 8) {
                if (this.£) {
                    byte[] array10 = null;
                    final int n17 = n4 / 3;
                    try {
                        if (this.ª == 32773) {
                            this.\u00e5.readFully(array2, 0, n3);
                            array10 = new byte[n17];
                            this.A(array2, n17, array10);
                        }
                        else if (this.ª == 5) {
                            this.\u00e5.readFully(array2, 0, n3);
                            array10 = new byte[n17];
                            this.\u00da.A(array2, array10, rectangle2.height);
                        }
                        else if (this.ª == 7) {
                            this.\u00e5.readFully(array2, 0, n3);
                            final Raster a2 = A(array2, this.\u00dd, this.\u00d5, a.getMinX(), a.getMinY());
                            final int[] array11 = new int[n17];
                            a2.getPixels(a.getMinX(), a.getMinY(), a.getWidth(), a.getHeight(), array11);
                            array10 = new byte[n17];
                            for (int k = 0; k < n17; ++k) {
                                array10[k] = (byte)array11[k];
                            }
                        }
                        else if (this.ª == 32946) {
                            this.\u00e5.readFully(array2, 0, n3);
                            array10 = new byte[n17];
                            this.A(array2, array10);
                        }
                        else if (this.ª == 1) {
                            array10 = new byte[n3];
                            this.\u00e5.readFully(array10, 0, n3);
                        }
                        this.\u00e5.A(g);
                    }
                    catch (IOException ex6) {
                        throw new RuntimeException(m.A("TIFFImage13"));
                    }
                    int n18 = 0;
                    final int n19 = this.\u00d9.length / 3;
                    final int n20 = n19 * 2;
                    for (int l = 0; l < n17; ++l) {
                        final int n21 = array10[l] & 0xFF;
                        array[n18++] = (short)(this.\u00d9[n21 + n20] & '\uffff');
                        array[n18++] = (short)(this.\u00d9[n21 + n19] & '\uffff');
                        array[n18++] = (short)(this.\u00d9[n21] & '\uffff');
                    }
                    return a;
                }
                try {
                    if (this.ª == 32773) {
                        this.\u00e5.readFully(array2, 0, n3);
                        this.A(array2, n4, data);
                    }
                    else if (this.ª == 5) {
                        this.\u00e5.readFully(array2, 0, n3);
                        this.\u00da.A(array2, data, rectangle2.height);
                    }
                    else if (this.ª == 7) {
                        this.\u00e5.readFully(array2, 0, n3);
                        a.setRect(A(array2, this.\u00dd, this.\u00d5, a.getMinX(), a.getMinY()));
                    }
                    else if (this.ª == 32946) {
                        this.\u00e5.readFully(array2, 0, n3);
                        this.A(array2, data);
                    }
                    else if (this.ª == 1) {
                        this.\u00e5.readFully(data, 0, n3);
                    }
                    this.\u00e5.A(g);
                    return a;
                }
                catch (IOException ex7) {
                    throw new RuntimeException(m.A("TIFFImage13"));
                }
            }
            if (this.\u00c0 == 4) {
                final int n22 = (rectangle2.width % 2 != 0) ? 1 : 0;
                final int n23 = (rectangle2.width / 2 + n22) * rectangle2.height;
                if (this.£) {
                    byte[] array12 = null;
                    try {
                        this.\u00e5.readFully(array2, 0, n3);
                        this.\u00e5.A(g);
                    }
                    catch (IOException ex8) {
                        throw new RuntimeException(m.A("TIFFImage13"));
                    }
                    if (this.ª == 32773) {
                        array12 = new byte[n23];
                        this.A(array2, n23, array12);
                    }
                    else if (this.ª == 5) {
                        array12 = new byte[n23];
                        this.\u00da.A(array2, array12, rectangle2.height);
                    }
                    else if (this.ª == 32946) {
                        array12 = new byte[n23];
                        this.A(array2, array12);
                    }
                    else if (this.ª == 1) {
                        array12 = array2;
                    }
                    final int n24 = n4 / 3;
                    final byte[] array13 = new byte[n24];
                    int n25 = 0;
                    int n26 = 0;
                    for (int n27 = 0; n27 < rectangle2.height; ++n27) {
                        for (int n28 = 0; n28 < rectangle2.width / 2; ++n28) {
                            array13[n26++] = (byte)((array12[n25] & 0xF0) >> 4);
                            array13[n26++] = (byte)(array12[n25++] & 0xF);
                        }
                        if (n22 == 1) {
                            array13[n26++] = (byte)((array12[n25++] & 0xF0) >> 4);
                        }
                    }
                    final int n29 = this.\u00d9.length / 3;
                    final int n30 = n29 * 2;
                    int n31 = 0;
                    for (int n32 = 0; n32 < n24; ++n32) {
                        final int n33 = array13[n32] & 0xFF;
                        array[n31++] = (short)(this.\u00d9[n33 + n30] & '\uffff');
                        array[n31++] = (short)(this.\u00d9[n33 + n29] & '\uffff');
                        array[n31++] = (short)(this.\u00d9[n33] & '\uffff');
                    }
                }
                else {
                    try {
                        if (this.ª == 32773) {
                            this.\u00e5.readFully(array2, 0, n3);
                            this.A(array2, n23, data);
                        }
                        else if (this.ª == 5) {
                            this.\u00e5.readFully(array2, 0, n3);
                            this.\u00da.A(array2, data, rectangle2.height);
                        }
                        else if (this.ª == 32946) {
                            this.\u00e5.readFully(array2, 0, n3);
                            this.A(array2, data);
                        }
                        else if (this.ª == 1) {
                            this.\u00e5.readFully(data, 0, n3);
                        }
                        this.\u00e5.A(g);
                    }
                    catch (IOException ex9) {
                        throw new RuntimeException(m.A("TIFFImage13"));
                    }
                }
            }
        }
        else {
            if (this.\u00e1 == 1) {
                try {
                    if (this.ª == 32773) {
                        this.\u00e5.readFully(array2, 0, n3);
                        int n34;
                        if (rectangle2.width % 8 == 0) {
                            n34 = rectangle2.width / 2 * rectangle2.height;
                        }
                        else {
                            n34 = (rectangle2.width / 2 + 1) * rectangle2.height;
                        }
                        this.A(array2, n34, data);
                    }
                    else if (this.ª == 5) {
                        this.\u00e5.readFully(array2, 0, n3);
                        this.\u00da.A(array2, data, rectangle2.height);
                    }
                    else if (this.ª == 32946) {
                        this.\u00e5.readFully(array2, 0, n3);
                        this.A(array2, data);
                    }
                    else {
                        this.\u00e5.readFully(data, 0, n3);
                    }
                    this.\u00e5.A(g);
                    return a;
                }
                catch (IOException ex10) {
                    throw new RuntimeException(m.A("TIFFImage13"));
                }
            }
            try {
                if (this.\u00c0 == 8) {
                    if (this.ª == 1) {
                        this.\u00e5.readFully(data, 0, n3);
                    }
                    else if (this.ª == 5) {
                        this.\u00e5.readFully(array2, 0, n3);
                        this.\u00da.A(array2, data, rectangle2.height);
                    }
                    else if (this.ª == 32773) {
                        this.\u00e5.readFully(array2, 0, n3);
                        this.A(array2, n4, data);
                    }
                    else if (this.ª == 7) {
                        this.\u00e5.readFully(array2, 0, n3);
                        a.setRect(A(array2, this.\u00dd, this.\u00d5, a.getMinX(), a.getMinY()));
                    }
                    else if (this.ª == 32946) {
                        this.\u00e5.readFully(array2, 0, n3);
                        this.A(array2, data);
                    }
                }
                else if (this.\u00c0 == 16) {
                    if (this.ª == 1) {
                        this.A(n3 / 2, array);
                    }
                    else if (this.ª == 5) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final byte[] array14 = new byte[n4 * 2];
                        this.\u00da.A(array2, array14, rectangle2.height);
                        this.A(array14, array, n4);
                    }
                    else if (this.ª == 32773) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final int n35 = n4 * 2;
                        final byte[] array15 = new byte[n35];
                        this.A(array2, n35, array15);
                        this.A(array15, array, n4);
                    }
                    else if (this.ª == 32946) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final byte[] array16 = new byte[n4 * 2];
                        this.A(array2, array16);
                        this.A(array16, array, n4);
                    }
                }
                else if (this.\u00c0 == 32 && dataType == 3) {
                    if (this.ª == 1) {
                        this.A(n3 / 4, data2);
                    }
                    else if (this.ª == 5) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final byte[] array17 = new byte[n4 * 4];
                        this.\u00da.A(array2, array17, rectangle2.height);
                        this.A(array17, data2, n4);
                    }
                    else if (this.ª == 32773) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final int n36 = n4 * 4;
                        final byte[] array18 = new byte[n36];
                        this.A(array2, n36, array18);
                        this.A(array18, data2, n4);
                    }
                    else if (this.ª == 32946) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final byte[] array19 = new byte[n4 * 4];
                        this.A(array2, array19);
                        this.A(array19, data2, n4);
                    }
                }
                else if (this.\u00c0 == 32 && dataType == 4) {
                    if (this.ª == 1) {
                        this.A(n3 / 4, b);
                    }
                    else if (this.ª == 5) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final byte[] array20 = new byte[n4 * 4];
                        this.\u00da.A(array2, array20, rectangle2.height);
                        this.A(array20, b, n4);
                    }
                    else if (this.ª == 32773) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final int n37 = n4 * 4;
                        final byte[] array21 = new byte[n37];
                        this.A(array2, n37, array21);
                        this.A(array21, b, n4);
                    }
                    else if (this.ª == 32946) {
                        this.\u00e5.readFully(array2, 0, n3);
                        final byte[] array22 = new byte[n4 * 4];
                        this.A(array2, array22);
                        this.A(array22, b, n4);
                    }
                }
                this.\u00e5.A(g);
            }
            catch (IOException ex11) {
                throw new RuntimeException(m.A("TIFFImage13"));
            }
            switch (this.\u00e1) {
                case 2:
                case 3: {
                    if (!this.\u00c7) {
                        break;
                    }
                    if (dataType == 0 && !(this.H instanceof IndexColorModel)) {
                        for (int n38 = 0; n38 < data.length; n38 += this.\u00cd) {
                            data[n38] = (byte)(255 - data[n38]);
                        }
                        break;
                    }
                    if (dataType == 1) {
                        final int n39 = 65535;
                        for (int n40 = 0; n40 < array.length; n40 += this.\u00cd) {
                            array[n40] = (short)(n39 - array[n40]);
                        }
                        break;
                    }
                    if (dataType == 2) {
                        for (int n41 = 0; n41 < array.length; n41 += this.\u00cd) {
                            array[n41] ^= -1;
                        }
                        break;
                    }
                    if (dataType == 3) {
                        final long n42 = -1L;
                        for (int n43 = 0; n43 < data2.length; n43 += this.\u00cd) {
                            data2[n43] = (int)(n42 - data2[n43]);
                        }
                        break;
                    }
                    break;
                }
                case 7: {
                    final int n44 = this.\u00d4 * this.\u00d0;
                    final int n45 = rectangle2.width / this.\u00d4;
                    final int n46 = rectangle2.height / this.\u00d0;
                    final byte[] array23 = new byte[n45 * n46 * (n44 + 2)];
                    System.arraycopy(data, 0, array23, 0, array23.length);
                    final int n47 = n44 * 3;
                    final int[] array24 = new int[n47];
                    int n48 = 0;
                    final int n49 = n44;
                    final int n50 = n49 + 1;
                    int y = rectangle2.y;
                    for (int n51 = 0; n51 < n46; ++n51) {
                        int x = rectangle2.x;
                        for (int n52 = 0; n52 < n45; ++n52) {
                            final byte b2 = array23[n48 + n49];
                            final byte b3 = array23[n48 + n50];
                            for (int n53 = 0; n53 < n47; array24[n53++] = array23[n48++], array24[n53++] = b2, array24[n53++] = b3) {}
                            n48 += 2;
                            a.setPixels(x, y, this.\u00d4, this.\u00d0, array24);
                            x += this.\u00d4;
                        }
                        y += this.\u00d0;
                    }
                    break;
                }
            }
        }
        return a;
    }
    
    private void A(final int n, final short[] array) {
        final int n2 = 2 * n;
        final byte[] array2 = new byte[n2];
        try {
            this.\u00e5.readFully(array2, 0, n2);
        }
        catch (IOException ex) {
            throw new RuntimeException(m.A("TIFFImage13"));
        }
        this.A(array2, array, n);
    }
    
    private void A(final int n, final int[] array) {
        final int n2 = 4 * n;
        final byte[] array2 = new byte[n2];
        try {
            this.\u00e5.readFully(array2, 0, n2);
        }
        catch (IOException ex) {
            throw new RuntimeException(m.A("TIFFImage13"));
        }
        this.A(array2, array, n);
    }
    
    private void A(final int n, final float[] array) {
        final int n2 = 4 * n;
        final byte[] array2 = new byte[n2];
        try {
            this.\u00e5.readFully(array2, 0, n2);
        }
        catch (IOException ex) {
            throw new RuntimeException(m.A("TIFFImage13"));
        }
        this.A(array2, array, n);
    }
    
    private void A(final byte[] array, final short[] array2, final int n) {
        int n2 = 0;
        if (this.\u00e4) {
            for (int i = 0; i < n; ++i) {
                array2[i] = (short)(((array[n2++] & 0xFF) << 8) + (array[n2++] & 0xFF));
            }
        }
        else {
            for (int j = 0; j < n; ++j) {
                array2[j] = (short)(((array[n2++] & 0xFF) << 8) + (array[n2++] & 0xFF));
            }
        }
    }
    
    private void A(final byte[] array, final int[] array2, final int n) {
        int n2 = 0;
        if (this.\u00e4) {
            for (int i = 0; i < n; ++i) {
                array2[i] = ((array[n2++] & 0xFF) << 24 | (array[n2++] & 0xFF) << 16 | (array[n2++] & 0xFF) << 8 | (array[n2++] & 0xFF));
            }
        }
        else {
            for (int j = 0; j < n; ++j) {
                array2[j] = ((array[n2++] & 0xFF) | (array[n2++] & 0xFF) << 8 | (array[n2++] & 0xFF) << 16 | (array[n2++] & 0xFF) << 24);
            }
        }
    }
    
    private void A(final byte[] array, final float[] array2, final int n) {
        int n2 = 0;
        if (this.\u00e4) {
            for (int i = 0; i < n; ++i) {
                array2[i] = Float.intBitsToFloat((array[n2++] & 0xFF) << 24 | (array[n2++] & 0xFF) << 16 | (array[n2++] & 0xFF) << 8 | (array[n2++] & 0xFF));
            }
        }
        else {
            for (int j = 0; j < n; ++j) {
                array2[j] = Float.intBitsToFloat((array[n2++] & 0xFF) | (array[n2++] & 0xFF) << 8 | (array[n2++] & 0xFF) << 16 | (array[n2++] & 0xFF) << 24);
            }
        }
    }
    
    private byte[] A(final byte[] array, final int n, byte[] array2) {
        if (array2 == null) {
            array2 = new byte[n];
        }
        int n2 = 0;
        int i = 0;
        try {
            while (i < n) {
                final byte b = array[n2++];
                if (b >= 0 && b <= 127) {
                    for (byte b2 = 0; b2 < b + 1; ++b2) {
                        array2[i++] = array[n2++];
                    }
                }
                else if (b <= -1 && b >= -127) {
                    final byte b3 = array[n2++];
                    for (byte b4 = 0; b4 < -b + 1; ++b4) {
                        array2[i++] = b3;
                    }
                }
                else {
                    ++n2;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new RuntimeException(m.A("TIFFImage14"));
        }
        return array2;
    }
    
    private ComponentColorModel A(final int n, final int n2, final boolean b, final int n3) {
        ColorSpace colorSpace = null;
        switch (n2) {
            case 2: {
                colorSpace = ColorSpace.getInstance(1003);
                break;
            }
            case 4: {
                colorSpace = ColorSpace.getInstance(1000);
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        ComponentColorModel componentColorModel;
        if (n == 4) {
            componentColorModel = new j(colorSpace, true, b, n3, n);
        }
        else {
            int n4 = 0;
            switch (n) {
                case 0: {
                    n4 = 8;
                    break;
                }
                case 1:
                case 2: {
                    n4 = 16;
                    break;
                }
                case 3: {
                    n4 = 32;
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
            final int[] array = new int[n2];
            for (int i = 0; i < n2; ++i) {
                array[i] = n4;
            }
            componentColorModel = new ComponentColorModel(colorSpace, array, true, b, n3, n);
        }
        return componentColorModel;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
