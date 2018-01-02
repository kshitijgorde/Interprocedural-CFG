// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.EOFException;
import java.util.Enumeration;
import java.util.Vector;
import java.io.IOException;
import java.util.Hashtable;
import java.io.Serializable;

public class F implements Serializable
{
    boolean G;
    int F;
    p[] A;
    Hashtable D;
    long C;
    long B;
    private static final int[] E;
    
    F() {
        this.D = new Hashtable();
        this.C = 8L;
        this.B = 0L;
    }
    
    private static boolean F(final int n) {
        return n == 18761 || n == 19789;
    }
    
    public F(final X x, final int n) throws IOException {
        this.D = new Hashtable();
        this.C = 8L;
        this.B = 0L;
        final long g = x.G();
        x.A(0L);
        final int unsignedShort = x.readUnsignedShort();
        if (!F(unsignedShort)) {
            throw new IllegalArgumentException(m.A("TIFFDirectory1"));
        }
        this.G = (unsignedShort == 19789);
        if (this.E(x) != 42) {
            throw new IllegalArgumentException(m.A("TIFFDirectory2"));
        }
        long n2 = this.C(x);
        for (int i = 0; i < n; ++i) {
            if (n2 == 0L) {
                throw new IllegalArgumentException(m.A("TIFFDirectory3"));
            }
            x.A(n2);
            x.skip(12 * this.E(x));
            n2 = this.C(x);
        }
        x.A(n2);
        this.B(x);
        x.A(g);
    }
    
    public F(final X x, long c, final int n) throws IOException {
        this.D = new Hashtable();
        this.C = 8L;
        this.B = 0L;
        final long g = x.G();
        x.A(0L);
        final int unsignedShort = x.readUnsignedShort();
        if (!F(unsignedShort)) {
            throw new IllegalArgumentException(m.A("TIFFDirectory1"));
        }
        this.G = (unsignedShort == 19789);
        x.A(c);
        for (int i = 0; i < n; ++i) {
            x.A(c + 12 * this.E(x));
            c = this.C(x);
            x.A(c);
        }
        this.B(x);
        x.A(g);
    }
    
    private void B(final X x) throws IOException {
        this.C = x.G();
        this.F = this.E(x);
        this.A = new p[this.F];
        for (int i = 0; i < this.F; ++i) {
            final int e = this.E(x);
            final int e2 = this.E(x);
            int size = (int)this.C(x);
            final long n = x.G() + 4L;
            try {
                if (size * z.B.F.E[e2] > 4) {
                    x.A((int)this.C(x));
                }
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println(e + " " + m.A("TIFFDirectory4"));
                x.A(n);
                continue;
            }
            this.D.put(new Integer(e), new Integer(i));
            Object o = null;
            switch (e2) {
                case 1:
                case 2:
                case 6:
                case 7: {
                    final byte[] array = new byte[size];
                    x.readFully(array, 0, size);
                    if (e2 == 2) {
                        int j = 0;
                        int n2 = 0;
                        final Vector vector = new Vector<String>();
                        while (j < size) {
                            while (j < size && array[j++] != 0) {}
                            vector.add(new String(array, n2, j - n2));
                            n2 = j;
                        }
                        size = vector.size();
                        final String[] array2 = new String[size];
                        for (int k = 0; k < size; ++k) {
                            array2[k] = vector.elementAt(k);
                        }
                        o = array2;
                        break;
                    }
                    o = array;
                    break;
                }
                case 3: {
                    final char[] array3 = new char[size];
                    for (int l = 0; l < size; ++l) {
                        array3[l] = (char)this.E(x);
                    }
                    o = array3;
                    break;
                }
                case 4: {
                    final long[] array4 = new long[size];
                    for (int n3 = 0; n3 < size; ++n3) {
                        array4[n3] = this.C(x);
                    }
                    o = array4;
                    break;
                }
                case 5: {
                    final long[][] array5 = new long[size][2];
                    for (int n4 = 0; n4 < size; ++n4) {
                        array5[n4][0] = this.C(x);
                        array5[n4][1] = this.C(x);
                    }
                    o = array5;
                    break;
                }
                case 8: {
                    final short[] array6 = new short[size];
                    for (int n5 = 0; n5 < size; ++n5) {
                        array6[n5] = this.F(x);
                    }
                    o = array6;
                    break;
                }
                case 9: {
                    final int[] array7 = new int[size];
                    for (int n6 = 0; n6 < size; ++n6) {
                        array7[n6] = this.I(x);
                    }
                    o = array7;
                    break;
                }
                case 10: {
                    final int[][] array8 = new int[size][2];
                    for (int n7 = 0; n7 < size; ++n7) {
                        array8[n7][0] = this.I(x);
                        array8[n7][1] = this.I(x);
                    }
                    o = array8;
                    break;
                }
                case 11: {
                    final float[] array9 = new float[size];
                    for (int n8 = 0; n8 < size; ++n8) {
                        array9[n8] = this.G(x);
                    }
                    o = array9;
                    break;
                }
                case 12: {
                    final double[] array10 = new double[size];
                    for (int n9 = 0; n9 < size; ++n9) {
                        array10[n9] = this.A(x);
                    }
                    o = array10;
                    break;
                }
                default: {
                    System.err.println(m.A("TIFFDirectory0"));
                    break;
                }
            }
            this.A[i] = new p(e, e2, size, o);
            x.A(n);
        }
        this.B = this.C(x);
    }
    
    public int D() {
        return this.F;
    }
    
    public p B(final int n) {
        final Integer n2 = this.D.get(new Integer(n));
        if (n2 == null) {
            return null;
        }
        return this.A[n2];
    }
    
    public boolean D(final int n) {
        return this.D.containsKey(new Integer(n));
    }
    
    public int[] C() {
        final int[] array = new int[this.D.size()];
        final Enumeration<Integer> keys = this.D.keys();
        int n = 0;
        while (keys.hasMoreElements()) {
            array[n++] = keys.nextElement();
        }
        return array;
    }
    
    public p[] B() {
        return this.A;
    }
    
    public byte D(final int n, final int n2) {
        return this.A[this.D.get(new Integer(n))].A()[n2];
    }
    
    public byte G(final int n) {
        return this.D(n, 0);
    }
    
    public long A(final int n, final int n2) {
        return this.A[this.D.get(new Integer(n))].D(n2);
    }
    
    public long E(final int n) {
        return this.A(n, 0);
    }
    
    public float C(final int n, final int n2) {
        return this.A[this.D.get(new Integer(n))].B(n2);
    }
    
    public float C(final int n) {
        return this.C(n, 0);
    }
    
    public double B(final int n, final int n2) {
        return this.A[this.D.get(new Integer(n))].G(n2);
    }
    
    public double A(final int n) {
        return this.B(n, 0);
    }
    
    private short F(final X x) throws IOException {
        if (this.G) {
            return x.readShort();
        }
        return x.I();
    }
    
    private int E(final X x) throws IOException {
        if (this.G) {
            return x.readUnsignedShort();
        }
        return x.C();
    }
    
    private int I(final X x) throws IOException {
        if (this.G) {
            return x.readInt();
        }
        return x.E();
    }
    
    private long C(final X x) throws IOException {
        if (this.G) {
            return x.A();
        }
        return x.F();
    }
    
    private long H(final X x) throws IOException {
        if (this.G) {
            return x.readLong();
        }
        return x.H();
    }
    
    private float G(final X x) throws IOException {
        if (this.G) {
            return x.readFloat();
        }
        return x.J();
    }
    
    private double A(final X x) throws IOException {
        if (this.G) {
            return x.readDouble();
        }
        return x.K();
    }
    
    private static int A(final X x, final boolean b) throws IOException {
        if (b) {
            return x.readUnsignedShort();
        }
        return x.C();
    }
    
    private static long B(final X x, final boolean b) throws IOException {
        if (b) {
            return x.A();
        }
        return x.F();
    }
    
    public static int D(final X x) throws IOException {
        final long g = x.G();
        x.A(0L);
        final int unsignedShort = x.readUnsignedShort();
        if (!F(unsignedShort)) {
            throw new IllegalArgumentException(m.A("TIFFDirectory1"));
        }
        final boolean b = unsignedShort == 19789;
        if (A(x, b) != 42) {
            throw new IllegalArgumentException(m.A("TIFFDirectory2"));
        }
        x.A(4L);
        long n = B(x, b);
        int n2 = 0;
        while (n != 0L) {
            ++n2;
            try {
                x.A(n);
                x.skip(12 * A(x, b));
                n = B(x, b);
                continue;
            }
            catch (EOFException ex) {
                --n2;
            }
            break;
        }
        x.A(g);
        return n2;
    }
    
    public boolean F() {
        return this.G;
    }
    
    public long A() {
        return this.C;
    }
    
    public long E() {
        return this.B;
    }
    
    static {
        E = new int[] { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8 };
    }
}
