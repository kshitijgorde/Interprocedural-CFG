// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.color.ColorSpace;
import java.awt.image.IndexColorModel;
import java.awt.image.ColorModel;
import java.awt.image.SampleModel;
import java.awt.image.RenderedImage;
import java.util.Vector;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.awt.image.ComponentColorModel;
import java.util.Hashtable;

public abstract class J
{
    private static Hashtable T;
    private static final byte[][] C;
    private static final int[] G;
    private static final ComponentColorModel B;
    private static final int[] I;
    private static final ComponentColorModel D;
    private static final int[] E;
    private static final ComponentColorModel N;
    private static final int[] P;
    private static final ComponentColorModel A;
    private static final int[] Y;
    private static final ComponentColorModel K;
    private static final int[] L;
    private static final ComponentColorModel W;
    private static final int[] J;
    private static final ComponentColorModel F;
    private static final int[] Z;
    private static final ComponentColorModel H;
    private static final int[] U;
    private static final ComponentColorModel X;
    private static final int[] R;
    private static final ComponentColorModel V;
    private static final int[] O;
    private static final ComponentColorModel S;
    private static final int[] M;
    private static final ComponentColorModel Q;
    
    public static J A(final String s) {
        return z.B.J.T.get(s.toLowerCase());
    }
    
    public static void A(final J j) {
        z.B.J.T.put(j.D().toLowerCase(), j);
    }
    
    public static void B(final String s) {
        z.B.J.T.remove(s.toLowerCase());
    }
    
    public static Enumeration C() {
        return z.B.J.T.elements();
    }
    
    public static _ A(final String s, final OutputStream outputStream, final M m) {
        final J a = A(s);
        if (a == null) {
            return null;
        }
        return a.A(outputStream, m);
    }
    
    public static t A(final String s, final InputStream inputStream, final A a) {
        final J a2 = A(s);
        if (a2 == null) {
            return null;
        }
        return a2.A(inputStream, a);
    }
    
    public static t A(final String s, final File file, final A a) throws IOException {
        final J a2 = A(s);
        if (a2 == null) {
            return null;
        }
        return a2.A(file, a);
    }
    
    public static t A(final String s, final X x, final A a) {
        final J a2 = A(s);
        if (a2 == null) {
            return null;
        }
        return a2.A(x, a);
    }
    
    private static String[] A(final Vector vector) {
        final int size = vector.size();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public static String[] B(final X x) {
        if (!x.B() && !x.markSupported()) {
            throw new IllegalArgumentException(m.A("ImageCodec2"));
        }
        final Enumeration<J> elements = (Enumeration<J>)z.B.J.T.elements();
        final Vector<String> vector = new Vector<String>();
        while (elements.hasMoreElements()) {
            final J j = elements.nextElement();
            final int a = j.A();
            if (a == 0 && !x.B()) {
                continue;
            }
            try {
                if (a > 0) {
                    x.mark(a);
                    final byte[] array = new byte[a];
                    x.readFully(array);
                    x.reset();
                    if (!j.A(array)) {
                        continue;
                    }
                    vector.add(j.D());
                }
                else {
                    final long g = x.G();
                    x.A(0L);
                    if (j.A(x)) {
                        vector.add(j.D());
                    }
                    x.A(g);
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return A(vector);
    }
    
    public static String[] A(final RenderedImage renderedImage, final M m) {
        final Enumeration<J> elements = z.B.J.T.elements();
        final Vector<String> vector = new Vector<String>();
        while (elements.hasMoreElements()) {
            final J j = elements.nextElement();
            if (j.B(renderedImage, m)) {
                vector.add(j.D());
            }
        }
        return A(vector);
    }
    
    public abstract String D();
    
    public int A() {
        return 0;
    }
    
    public boolean A(final byte[] array) {
        throw new RuntimeException(m.A("ImageCodec0"));
    }
    
    public boolean A(final X x) throws IOException {
        throw new RuntimeException(m.A("ImageCodec1"));
    }
    
    protected abstract Class B();
    
    protected abstract Class E();
    
    protected abstract _ A(final OutputStream p0, final M p1);
    
    public abstract boolean B(final RenderedImage p0, final M p1);
    
    protected t A(final InputStream inputStream, final A a) {
        return this.A(z.B.X.A(inputStream, true), a);
    }
    
    protected t A(final File file, final A a) throws IOException {
        return this.A(new W(file), a);
    }
    
    protected abstract t A(final X p0, final A p1);
    
    public static ColorModel A(final SampleModel sampleModel, final boolean b) {
        if (sampleModel.getNumBands() != 1) {
            throw new IllegalArgumentException();
        }
        final int sampleSize = sampleModel.getSampleSize(0);
        byte[] array;
        if (sampleSize < 8) {
            array = z.B.J.C[sampleSize];
            if (!b) {
                final int length = array.length;
                final byte[] array2 = new byte[length];
                for (int i = 0; i < length; ++i) {
                    array2[i] = array[length - i - 1];
                }
                array = array2;
            }
        }
        else {
            array = new byte[256];
            if (b) {
                for (int j = 0; j < 256; ++j) {
                    array[j] = (byte)j;
                }
            }
            else {
                for (int k = 0; k < 256; ++k) {
                    array[k] = (byte)(255 - k);
                }
            }
        }
        return new IndexColorModel(sampleSize, array.length, array, array, array);
    }
    
    public static ColorModel A(final SampleModel sampleModel) {
        final int dataType = sampleModel.getDataType();
        final int numBands = sampleModel.getNumBands();
        ColorModel colorModel = null;
        if (dataType == 0) {
            switch (numBands) {
                case 1: {
                    colorModel = z.B.J.B;
                    break;
                }
                case 2: {
                    colorModel = z.B.J.D;
                    break;
                }
                case 3: {
                    colorModel = z.B.J.F;
                    break;
                }
                case 4: {
                    colorModel = z.B.J.H;
                    break;
                }
            }
        }
        else if (dataType == 1) {
            switch (numBands) {
                case 1: {
                    colorModel = z.B.J.N;
                    break;
                }
                case 2: {
                    colorModel = z.B.J.A;
                    break;
                }
                case 3: {
                    colorModel = z.B.J.X;
                    break;
                }
                case 4: {
                    colorModel = z.B.J.V;
                    break;
                }
            }
        }
        else if (dataType == 3) {
            switch (numBands) {
                case 1: {
                    colorModel = z.B.J.K;
                    break;
                }
                case 2: {
                    colorModel = z.B.J.W;
                    break;
                }
                case 3: {
                    colorModel = z.B.J.S;
                    break;
                }
                case 4: {
                    colorModel = z.B.J.Q;
                    break;
                }
            }
        }
        else if (dataType == 4 && numBands >= 1 && numBands <= 4) {
            final ColorSpace colorSpace = (numBands <= 2) ? ColorSpace.getInstance(1003) : ColorSpace.getInstance(1000);
            final boolean b = numBands % 2 == 0;
            colorModel = new j(colorSpace, b, false, b ? 3 : 1, 4);
        }
        return colorModel;
    }
    
    static {
        z.B.J.T = new Hashtable();
        A(new K());
        A(new V());
        C = new byte[][] { null, { 0, -1 }, { 0, 85, -86, -1 }, null, { 0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1 } };
        G = new int[] { 8 };
        B = new ComponentColorModel(ColorSpace.getInstance(1003), z.B.J.G, false, false, 1, 0);
        I = new int[] { 8, 8 };
        D = new ComponentColorModel(ColorSpace.getInstance(1003), z.B.J.I, true, false, 3, 0);
        E = new int[] { 16 };
        N = new ComponentColorModel(ColorSpace.getInstance(1003), z.B.J.E, false, false, 1, 1);
        P = new int[] { 16, 16 };
        A = new ComponentColorModel(ColorSpace.getInstance(1003), z.B.J.P, true, false, 3, 1);
        Y = new int[] { 32 };
        K = new ComponentColorModel(ColorSpace.getInstance(1003), z.B.J.Y, false, false, 1, 3);
        L = new int[] { 32, 32 };
        W = new ComponentColorModel(ColorSpace.getInstance(1003), z.B.J.L, true, false, 3, 3);
        J = new int[] { 8, 8, 8 };
        F = new ComponentColorModel(ColorSpace.getInstance(1000), z.B.J.J, false, false, 1, 0);
        Z = new int[] { 8, 8, 8, 8 };
        H = new ComponentColorModel(ColorSpace.getInstance(1000), z.B.J.Z, true, false, 3, 0);
        U = new int[] { 16, 16, 16 };
        X = new ComponentColorModel(ColorSpace.getInstance(1000), z.B.J.U, false, false, 1, 1);
        R = new int[] { 16, 16, 16, 16 };
        V = new ComponentColorModel(ColorSpace.getInstance(1000), z.B.J.R, true, false, 3, 1);
        O = new int[] { 32, 32, 32 };
        S = new ComponentColorModel(ColorSpace.getInstance(1000), z.B.J.O, false, false, 1, 3);
        M = new int[] { 32, 32, 32, 32 };
        Q = new ComponentColorModel(ColorSpace.getInstance(1000), z.B.J.M, true, false, 3, 3);
    }
}
