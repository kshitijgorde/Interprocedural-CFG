// 
// Decompiled by Procyon v0.5.30
// 

package a3dAPI;

import java.io.PrintStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.io.OutputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;

public final class a
{
    b a;
    private String G;
    protected float b;
    protected float c;
    protected float d;
    private float H;
    private float I;
    private float J;
    private float K;
    private float[][] L;
    private int M;
    private int N;
    protected float e;
    protected float f;
    protected float g;
    private boolean O;
    private float P;
    protected int h;
    protected int i;
    protected float j;
    protected float k;
    private float[] Q;
    private float[] R;
    float l;
    float m;
    float n;
    float o;
    float p;
    float q;
    private int[] S;
    private int T;
    private int[] U;
    private int V;
    private int[] W;
    private int X;
    private int[] Y;
    private int Z;
    private float[] aa;
    private float[][] ab;
    private int ac;
    private float[][] ad;
    private int ae;
    private float[][] af;
    private int ag;
    private float[][] ah;
    private int[][] ai;
    private float[][][] aj;
    protected float[][] r;
    private float[][] ak;
    protected int[][] s;
    protected float[][] t;
    protected int[][] u;
    protected float[][] v;
    protected int[][] w;
    protected float[][] x;
    protected int[][] y;
    protected int[][] z;
    private float[][] al;
    protected int[][] A;
    protected float[][][] B;
    protected float[][][] C;
    protected short[][][] D;
    protected float[][][] E;
    private float[][][] am;
    private float[][] an;
    private float[][] ao;
    protected static final int[] F;
    private static final char[] ap;
    private static final byte[] aq;
    private static byte[] ar;
    
    public final void a(final int n, final int[] array, final int n2, final int n3) {
        if ((n & 0xFF000000) == 0x3000000) {
            this.a.a(n & 0xFFFFFF, array, n2, n3);
        }
    }
    
    private static int a(final InputStream inputStream, final byte[] array, final int n, final int n2) throws IOException {
        int i = 0;
        while (i < n2) {
            final int read;
            if ((read = inputStream.read()) == -1) {
                if (i == 0) {
                    return -1;
                }
                return i;
            }
            else {
                array[i + n] = (byte)read;
                ++i;
            }
        }
        return n2;
    }
    
    private void c(final int n) {
        final float[] array = this.ak[n & 0xFFFFFF];
        final float[] array2 = this.al[n & 0xFFFFFF];
        final int length = array.length;
        int i = 3;
        float n3;
        float n2 = n3 = array[0];
        float n5;
        float n4 = n5 = array[1];
        float n7;
        float n6 = n7 = array[2];
        while (i < length) {
            final float n8;
            if ((n8 = array[i++]) > n3) {
                n3 = n8;
            }
            if (n8 < n2) {
                n2 = n8;
            }
            final float n9;
            if ((n9 = array[i++]) > n5) {
                n5 = n9;
            }
            if (n9 < n4) {
                n4 = n9;
            }
            final float n10;
            if ((n10 = array[i++]) > n7) {
                n7 = n10;
            }
            if (n10 < n6) {
                n6 = n10;
            }
        }
        array2[0] = n2;
        array2[1] = n3;
        array2[2] = n4;
        array2[3] = n5;
        array2[4] = n6;
        array2[5] = n7;
    }
    
    public final Component a() {
        return this.a;
    }
    
    private int k() {
        if (this.n <= -this.e) {
            this.q = this.g / this.n;
            this.o = this.l * this.q + this.j;
            this.p = -this.m * this.q + this.k;
            int n = 0;
            Label_0093: {
                int n2;
                if (this.o < 0.0f) {
                    n2 = 1;
                }
                else {
                    if (this.o <= this.h) {
                        break Label_0093;
                    }
                    n2 = 2;
                }
                n = n2;
            }
            Label_0125: {
                int n3;
                int n4;
                if (this.p < 0.0f) {
                    n3 = n;
                    n4 = 4;
                }
                else {
                    if (this.p <= this.i) {
                        break Label_0125;
                    }
                    n3 = n;
                    n4 = 8;
                }
                n = (n3 | n4);
            }
            int n5;
            int n6;
            if (this.n > -this.e) {
                n5 = n;
                n6 = 16;
            }
            else {
                if (this.n >= -this.f) {
                    return n;
                }
                n5 = n;
                n6 = 32;
            }
            n = (n5 | n6);
            return n;
        }
        return 16;
    }
    
    public final void a(final float n, final float n2, final float n3) {
        this.a.b(n, n2, n3);
    }
    
    public final int a(final int n, final int n2) {
        if ((n & 0xFF000000) != 0x1000000) {
            return -1;
        }
        for (int i = 0; i < this.ai[n & 0xFFFFFF].length; i += 2) {
            if (this.ai[n & 0xFFFFFF][i] == n2) {
                return this.ai[n & 0xFFFFFF][i + 1];
            }
        }
        final int length;
        final int[] array = new int[(length = this.ai[n & 0xFFFFFF].length) + 2];
        System.arraycopy(this.ai[n & 0xFFFFFF], 0, array, 0, length);
        array[length] = n2;
        final int[] array2 = array;
        final int n3 = length + 1;
        final int e = this.e(n2);
        array2[n3] = e;
        final int n4 = e;
        this.ai[n & 0xFFFFFF] = array;
        return n4;
    }
    
    public final void a(final int n, final float n2) {
        if ((n & 0xFF000000) != 0x2000000) {
            return;
        }
        this.r[n & 0xFFFFFF][6] = n2;
    }
    
    public final void b(final int n, final float n2) {
        if ((n & 0xFF000000) != 0x5000000) {
            return;
        }
        if (this.ao[n & 0xFFFFFF][0] != 1.0f) {
            return;
        }
        this.ao[n & 0xFFFFFF][12] = n2;
    }
    
    private int d(final int n) {
        final int f = this.f(83886080);
        final float[] array;
        (array = this.ao[f])[0] = n;
        array[1] = 1.0f;
        array[3] = (array[2] = 1.0f);
        array[5] = (array[4] = 1.0f);
        float[] array2 = null;
        int n2 = 0;
        float n3 = 0.0f;
        switch (n) {
            case 0: {
                array[7] = (array[6] = 0.0f);
                array2 = array;
                n2 = 8;
                n3 = -1.0f;
                break;
            }
            case 1: {
                array[6] = 1.0f;
                array[7] = 0.0f;
                array[9] = (array[8] = 0.0f);
                array[11] = (array[10] = 0.0f);
                array2 = array;
                n2 = 12;
                n3 = 100.0f;
                break;
            }
            default: {
                return 83886080 + f;
            }
        }
        array2[n2] = n3;
        return 83886080 + f;
    }
    
    private int e(final int n) {
        final int n2 = n & 0xFFFFFF;
        final int n3;
        if ((n3 = (n & 0xFF000000)) == 16777216) {
            int n4 = -1;
            for (int i = 0; i < this.aj[n2].length; ++i) {
                if (this.aj[n2][i] == null) {
                    n4 = i;
                    break;
                }
            }
            if (n4 == -1) {
                this.aj[n2] = a(this.aj[n2]);
                n4 = this.aj[n2].length - 1;
            }
            this.aj[n2][n4] = new float[12];
            return n4;
        }
        if (n3 == 67108864) {
            int n5 = -1;
            for (int j = 0; j < this.B[n2].length; ++j) {
                if (this.B[n2][j] == null) {
                    n5 = j;
                    break;
                }
            }
            if (n5 == -1) {
                this.B[n2] = a(this.B[n2]);
                this.C[n2] = a(this.C[n2]);
                this.D[n2] = a(this.D[n2]);
                this.E[n2] = a(this.E[n2]);
                this.am[n2] = a(this.am[n2]);
                n5 = this.B[n2].length - 1;
            }
            this.B[n2][n5] = new float[this.ak[n2].length];
            if (this.x[n2] != null) {
                this.C[n2][n5] = new float[this.x[n2].length];
            }
            this.D[n2][n5] = new short[Math.max(this.ak[n2].length / 3, this.s[n2].length / 3)];
            this.E[n2][n5] = new float[this.ak[n2].length];
            this.am[n2][n5] = new float[25];
            return n5;
        }
        return 0;
    }
    
    private static void a(final InputStream inputStream, final OutputStream outputStream, int n) throws IOException {
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        if (n < 2) {
            throw new IOException(d("1kc\u0002QGnU$\b\u0017OB}G=EDg\u0002\u001dEE \u000fSHI3\u0002\u0000\nV(\u0015SK^g\u0006\u0007E]i"));
        }
        int read;
        while ((read = inputStream.read()) != -1) {
            if (read != 10 && read != 13) {
                a.ar[0] = (byte)read;
                if (a(inputStream, a.ar, 1, n - 1) == -1) {
                    throw new IOException();
                }
                if (n > 3 && a.ar[3] == 61) {
                    n = 3;
                }
                if (n > 2 && a.ar[2] == 61) {
                    n = 2;
                }
                switch (n) {
                    case 4: {
                        n5 = a.aq[a.ar[3] & 0xFF];
                    }
                    case 3: {
                        n4 = a.aq[a.ar[2] & 0xFF];
                    }
                    case 2: {
                        n3 = a.aq[a.ar[1] & 0xFF];
                        n2 = a.aq[a.ar[0] & 0xFF];
                        break;
                    }
                }
                switch (n) {
                    case 2: {
                        outputStream.write((byte)((n2 << 2 & 0xFC) | (n3 >>> 4 & 0x3)));
                        return;
                    }
                    case 3: {
                        outputStream.write((byte)((n2 << 2 & 0xFC) | (n3 >>> 4 & 0x3)));
                        outputStream.write((byte)((n3 << 4 & 0xF0) | (n4 >>> 2 & 0xF)));
                        return;
                    }
                    case 4: {
                        outputStream.write((byte)((n2 << 2 & 0xFC) | (n3 >>> 4 & 0x3)));
                        outputStream.write((byte)((n3 << 4 & 0xF0) | (n4 >>> 2 & 0xF)));
                        outputStream.write((byte)((n4 << 6 & 0xC0) | (n5 & 0x3F)));
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
        }
        throw new IOException();
    }
    
    private void a(int n, final int n2, final float[] array, final int n3, final int n4) {
        n &= 0xFFFFFF;
        if (this.z[n][0] == 0) {
            return;
        }
        if (this.S.length == this.T) {
            final int[] s = new int[this.T << 1];
            System.arraycopy(this.S, 0, s, 0, this.T);
            this.S = s;
            final int[] u = new int[this.T];
            System.arraycopy(this.U, 0, u, 0, this.V);
            this.U = u;
            final int[] w = new int[this.T];
            System.arraycopy(this.W, 0, w, 0, this.X);
            this.W = w;
            final int[] y = new int[this.T];
            System.arraycopy(this.Y, 0, y, 0, this.Z);
            this.Y = y;
            this.aa = new float[this.T];
        }
        this.S[this.T++] = n;
        this.S[this.T++] = n2;
        this.S[this.T++] = n3;
        this.S[this.T++] = n4;
        if (!this.a(this.al[n], this.am[n][n2], array)) {
            return;
        }
        a(this.ak[n], this.B[n][n2], array, this.am[n][n2]);
        a(this.B[n][n2], this.s[n], this.x[n], this.y[n], this.D[n][n2]);
        if (this.a(this.D[n][n2], this.B[n][n2], this.E[n][n2])) {
            return;
        }
        if (this.x[n] != null) {
            a(this.D[n][n2], this.x[n], this.C[n][n2], array);
        }
        this.U[this.V++] = n;
        this.U[this.V++] = n2;
        if (this.z[n][1] != -1 && this.r[this.z[n][1] & 0xFFFFFF][6] > 0.0f) {
            this.Y[this.Z++] = n;
            this.Y[this.Z++] = n2;
            return;
        }
        this.W[this.X++] = n;
        this.W[this.X++] = n2;
    }
    
    protected static final boolean a(final String s) {
        try {
            final BigInteger bigInteger = new BigInteger(d("G\u0018\u0004uVB\u0012\u0005p"));
            final BigInteger bigInteger2 = new BigInteger(d("A\u001a\u0005sWC\u0012\tv^"));
            final byte[] c = c(s);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayInputStream byteArrayInputStream;
            for (int read = (byteArrayInputStream = new ByteArrayInputStream(c)).read(), i = 0; i < read; ++i) {
                final int read2;
                final byte[] array = new byte[read2 = byteArrayInputStream.read()];
                byteArrayInputStream.read(array, 0, read2);
                byteArrayOutputStream.write(new BigInteger(array).modPow(bigInteger, bigInteger2).toByteArray());
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final DataInputStream dataInputStream;
            final boolean b = (dataInputStream = new DataInputStream(new ByteArrayInputStream(byteArray))).readByte() == 1;
            boolean b2 = true;
            if (b) {
                dataInputStream.readByte();
                dataInputStream.readByte();
                dataInputStream.readByte();
                final long n = dataInputStream.readInt() & 0xFFFFFF;
                final long n2 = dataInputStream.readInt() & 0xFFFFFF;
                final long n3 = System.currentTimeMillis() / 86400000L;
                if (n > n3 || n2 < n3) {
                    b2 = false;
                }
            }
            String string = "";
            String s2;
            while (true) {
                s2 = string;
                final char c2;
                if ((c2 = (char)dataInputStream.readByte()) == '\0') {
                    break;
                }
                string = s2 + c2;
            }
            PrintStream printStream;
            StringBuffer sb;
            String s3;
            if (b) {
                printStream = System.out;
                sb = new StringBuffer();
                s3 = "\u0007OC3J\u001fCS\"\t\u0000O\u00103\bI\n";
            }
            else {
                printStream = System.out;
                sb = new StringBuffer();
                s3 = "\u001fCS\"\t\u0000OTg\u0013\u001c\u0010\u0010";
            }
            printStream.println(sb.append(d(s3)).append(s2).toString());
            byte b3 = 0;
            int n4 = byteArray.length - 1;
            while (--n4 >= 0) {
                b3 += byteArray[n4];
            }
            return byteArray[byteArray.length - 1] == b3 & b2;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final int b() {
        final int f = this.f(33554432);
        this.r[f][0] = 0.0f;
        this.r[f][1] = 0.0f;
        this.r[f][2] = 0.0f;
        this.r[f][3] = 0.0f;
        this.r[f][4] = 0.0f;
        this.r[f][5] = 0.0f;
        this.r[f][6] = 0.0f;
        return 33554432 + f;
    }
    
    public final int c() {
        return this.d(1);
    }
    
    public final void d() {
        this.ah = new float[0][];
        this.ai = new int[0][];
        this.aj = new float[0][][];
        this.r = new float[0][];
        this.ak = new float[0][];
        this.s = new int[0][];
        this.t = new float[0][];
        this.u = new int[0][];
        this.v = new float[0][];
        this.w = new int[0][];
        this.x = new float[0][];
        this.y = new int[0][];
        this.z = new int[0][];
        this.al = new float[0][];
        this.A = new int[0][];
        this.B = new float[0][][];
        this.C = new float[0][][];
        this.D = new short[0][][];
        this.E = new float[0][][];
        this.am = new float[0][][];
        this.an = new float[0][];
        this.ao = new float[0][];
        this.ab = new float[10][7];
        this.ad = new float[10][11];
        this.S = new int[2000];
        this.U = new int[1000];
        this.W = new int[1000];
        this.Y = new int[1000];
        this.aa = new float[1000];
        this.M = -1;
        this.N = -1;
        this.V = 0;
        this.ac = 0;
        this.ae = 0;
        this.T = 0;
        this.X = 0;
        this.Z = 0;
        System.gc();
    }
    
    public final void a(final int n, final float[] array) {
        if ((n & 0xFF000000) != 0x1000000) {
            return;
        }
        System.arraycopy(array, 0, this.ah[n & 0xFFFFFF], 0, 12);
    }
    
    public final int e() {
        final int f = this.f(16777216);
        final float[] array2;
        final float[] array = array2 = this.ah[f];
        final int n = 0;
        final float[] array3 = array2;
        final int n2 = 4;
        final float[] array4 = array2;
        final int n3 = 8;
        final float n4 = 1.0f;
        array4[n3] = n4;
        array[n] = (array3[n2] = n4);
        final float[] array5 = array2;
        final int n5 = 1;
        final float[] array6 = array2;
        final int n6 = 2;
        final float[] array7 = array2;
        final int n7 = 3;
        final float[] array8 = array2;
        final int n8 = 5;
        final float[] array9 = array2;
        final int n9 = 6;
        final float[] array10 = array2;
        final int n10 = 7;
        final float[] array11 = array2;
        final int n11 = 9;
        final float[] array12 = array2;
        final int n12 = 10;
        final float[] array13 = array2;
        final int n13 = 11;
        final float n14 = 0.0f;
        array13[n13] = n14;
        array11[n11] = (array12[n12] = n14);
        array9[n9] = (array10[n10] = n14);
        array7[n7] = (array8[n8] = n14);
        array5[n5] = (array6[n6] = n14);
        this.ai[f] = new int[0];
        return 16777216 + f;
    }
    
    public final void b(final int n, final float[] array) {
        if ((n & 0xFF000000) != 0x6000000) {
            return;
        }
        System.arraycopy(array, 0, this.an[n & 0xFFFFFF], 0, 12);
    }
    
    private static void a(final float[] array, final float[] array2) {
        final float n = array[0];
        final float n2 = array[3];
        final float n3 = array[6];
        final float n4 = array[9];
        final float n5 = array[1];
        final float n6 = array[4];
        final float n7 = array[7];
        final float n8 = array[10];
        final float n9 = array[2];
        final float n10 = array[5];
        final float n11 = array[8];
        final float n12 = array[11];
        final float n13 = n6 * n11 - n7 * n10;
        final float n14 = n7 * n9 - n5 * n11;
        final float n15 = n5 * n10 - n6 * n9;
        final float n16;
        if ((n16 = n * n13 + n2 * n14 + n3 * n15) == 0.0f) {
            final int n17 = 0;
            final int n18 = 4;
            final int n19 = 8;
            final float n20 = 1.0f;
            array2[n19] = n20;
            array2[n17] = (array2[n18] = n20);
            final int n21 = 1;
            final int n22 = 2;
            final int n23 = 3;
            final int n24 = 5;
            final int n25 = 6;
            final int n26 = 7;
            final int n27 = 9;
            final int n28 = 10;
            final int n29 = 11;
            final float n30 = 0.0f;
            array2[n29] = n30;
            array2[n27] = (array2[n28] = n30);
            array2[n25] = (array2[n26] = n30);
            array2[n23] = (array2[n24] = n30);
            array2[n21] = (array2[n22] = n30);
            return;
        }
        final float n31 = 1.0f / n16;
        array2[0] = n13 * n31;
        array2[1] = n14 * n31;
        array2[2] = n15 * n31;
        final float n32 = n * n31;
        final float n33 = n2 * n31;
        final float n34 = n3 * n31;
        final float n35 = n4 * n31;
        array2[3] = n34 * n10 - n33 * n11;
        array2[4] = n32 * n11 - n34 * n9;
        array2[5] = n33 * n9 - n32 * n10;
        final float n36 = n32 * n6 - n5 * n33;
        final float n37 = n32 * n7 - n5 * n34;
        final float n38 = n33 * n7 - n6 * n34;
        final float n39 = n33 * n8 - n6 * n35;
        final float n40 = n34 * n8 - n7 * n35;
        final float n41 = n35 * n5 - n8 * n32;
        array2[6] = n38;
        array2[7] = -n37;
        array2[8] = n36;
        array2[9] = -(n10 * n40 - n11 * n39 + n12 * n38);
        array2[10] = n9 * n40 + n11 * n41 + n12 * n37;
        array2[11] = -(n9 * n39 + n10 * n41 + n12 * n36);
    }
    
    private static void a(final short[] array, final float[] array2, final float[] array3, final float[] array4) {
        final float n = array4[0];
        final float n2 = array4[1];
        final float n3 = array4[2];
        final float n4 = array4[3];
        final float n5 = array4[4];
        final float n6 = array4[5];
        final float n7 = array4[6];
        final float n8 = array4[7];
        final float n9 = array4[8];
        final int n10 = array2.length / 3;
        int n11 = 0;
        for (int i = 0; i < n10; ++i) {
            if ((array[i] & 0x100) != 0x0) {
                final float n12 = array2[n11];
                final float n13 = array2[n11 + 1];
                final float n14 = array2[n11 + 2];
                float n15 = n12 * n + n13 * n4 + n14 * n7;
                float n16 = n12 * n2 + n13 * n5 + n14 * n8;
                float n17 = n12 * n3 + n13 * n6 + n14 * n9;
                final double sqrt;
                if ((sqrt = Math.sqrt(n15 * n15 + n16 * n16 + n17 * n17)) > 0.0) {
                    final double n18 = 1.0 / sqrt;
                    n15 *= (float)n18;
                    n16 *= (float)n18;
                    n17 *= (float)n18;
                }
                array3[n11] = n15;
                ++n11;
                array3[n11] = n16;
                ++n11;
                array3[n11] = n17;
                ++n11;
            }
            else {
                n11 += 3;
            }
        }
    }
    
    public final void f() {
        this.h = this.a.getWidth();
        this.i = this.a.getHeight();
        this.j = this.h >> 1;
        this.k = this.i >> 1;
        if (this.M == -1 || this.N == -1) {
            return;
        }
        this.a(this.M, 0, this.ah[this.M & 0xFFFFFF]);
        synchronized (this) {
            this.V = 0;
            this.T = 0;
            this.X = 0;
            this.Z = 0;
            this.ac = 0;
            this.ae = 0;
            this.g(this.M, 0);
        }
        int i = 0;
        while (i < this.X) {
            this.f(this.W[i++], this.W[i++]);
        }
        if (this.Z != 0) {
            this.l();
            int j = 0;
            while (j < this.Z) {
                this.f(this.Y[j++], this.Y[j++]);
            }
        }
    }
    
    public final void a(final int n, final float n2, final float n3, final float n4) {
        if ((n & 0xFF000000) != 0x5000000) {
            return;
        }
        if (this.ao[n & 0xFFFFFF][0] != 1.0f) {
            return;
        }
        this.ao[n & 0xFFFFFF][9] = n2;
        this.ao[n & 0xFFFFFF][10] = n3;
        this.ao[n & 0xFFFFFF][11] = n4;
    }
    
    private void d(final int n, final float[] array) {
        final float[] array2;
        if ((int)(array2 = this.ao[n & 0xFFFFFF])[1] == 0) {
            return;
        }
        switch ((int)array2[0]) {
            case 0: {
                if (this.ab.length == this.ac) {
                    final float[][] ab = new float[this.ac << 1][7];
                    System.arraycopy(this.ab, 0, ab, 0, this.ac);
                    this.ab = ab;
                }
                final float[] array3;
                (array3 = this.ab[this.ac++])[0] = array2[2];
                array3[1] = array2[3];
                array3[2] = array2[4];
                array3[3] = array2[5];
                this.b(array2[6], array2[7], array2[8], array);
                array3[4] = this.l;
                array3[5] = this.m;
                array3[6] = this.n;
            }
            case 1: {
                if (this.ad.length == this.ae) {
                    final float[][] ad = new float[this.ae << 1][11];
                    System.arraycopy(this.ad, 0, ad, 0, this.ae);
                    this.ad = ad;
                }
                final float[] array4;
                (array4 = this.ad[this.ae++])[0] = array2[2];
                array4[1] = array2[3];
                array4[2] = array2[4];
                array4[3] = array2[5];
                array4[4] = array2[6];
                array4[5] = array2[7];
                array4[6] = array2[8];
                this.a(array2[9], array2[10], array2[11], array);
                array4[7] = this.l;
                array4[8] = this.m;
                array4[9] = this.n;
                array4[10] = array2[12] * array2[12];
            }
            default: {}
        }
    }
    
    private static void a(final float[] array, final float[] array2, final float[] array3, final float[] array4) {
        array4[24] = 1.0f;
        final float n = array3[0];
        final float n2 = array3[1];
        final float n3 = array3[2];
        final float n4 = array3[3];
        final float n5 = array3[4];
        final float n6 = array3[5];
        final float n7 = array3[6];
        final float n8 = array3[7];
        final float n9 = array3[8];
        final float n10 = array3[9];
        final float n11 = array3[10];
        final float n12 = array3[11];
        for (int length = array.length, i = 0; i < length; i += 3) {
            final float n13 = array[i];
            final float n14 = array[i + 1];
            final float n15 = array[i + 2];
            array2[i] = n13 * n + n14 * n4 + n15 * n7 + n10;
            array2[i + 1] = n13 * n2 + n14 * n5 + n15 * n8 + n11;
            array2[i + 2] = n13 * n3 + n14 * n6 + n15 * n9 + n12;
        }
    }
    
    private static float[][][] a(final float[][][] array) {
        final float[][][] array2 = new float[array.length + 1][][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private static float[][] a(final float[][] array) {
        final float[][] array2 = new float[array.length + 1][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private static int[][] a(final int[][] array) {
        final int[][] array2 = new int[array.length + 1][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private static short[][][] a(final short[][][] array) {
        final short[][][] array2 = new short[array.length + 1][][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private static short[][] a(final short[][] array) {
        final short[][] array2 = new short[array.length + 1][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private void e(final int n, final int n2) {
        final int n3 = n & 0xFFFFFF;
        final int n4;
        if ((n4 = (n & 0xFF000000)) == 16777216) {
            this.aj[n3][n2] = null;
            return;
        }
        if (n4 == 67108864) {
            this.B[n3][n2] = null;
            this.C[n3][n2] = null;
            this.D[n3][n2] = null;
            this.E[n3][n2] = null;
            this.am[n3][n2] = null;
        }
    }
    
    private final void l() {
        float[] array;
        for (int n = 0, i = 0; i < this.Z; this.aa[n++] = ((array = this.am[this.Y[i++]][this.Y[i++]])[2] + array[5] + array[8] + array[11] + array[14] + array[17] + array[20] + array[23]) / 8.0f) {}
        final int n3;
        int n2 = n3 = this.Z >> 1;
        int n4;
        while ((n4 = n2 >> 1) > 0) {
            for (int j = n4; j < n3; ++j) {
                int n5 = j;
                int n6;
                while ((n6 = n5 - n4) >= 0 && this.aa[n6] > this.aa[n6 + n4]) {
                    final float n7 = this.aa[n6];
                    this.aa[n6] = this.aa[n6 + n4];
                    this.aa[n6 + n4] = n7;
                    final int n8 = n6 << 1;
                    final int n9 = n4 << 1;
                    final int n10 = this.Y[n8];
                    this.Y[n8] = this.Y[n8 + n9];
                    this.Y[n8 + n9] = n10;
                    final int n11 = this.Y[n8 + 1];
                    this.Y[n8 + 1] = this.Y[n8 + n9 + 1];
                    this.Y[n8 + n9 + 1] = n11;
                    n5 = n6;
                }
            }
            n2 = n4;
        }
    }
    
    private final void a(final float[] array, final int n, final float[] array2) {
        float b = this.b;
        float c = this.c;
        float d = this.d;
        final int n2 = n + (n << 1);
        final float n3 = array[n2];
        final float n4 = array[n2 + 1];
        final float n5 = array[n2 + 2];
        int ac = this.ac;
        while (--ac >= 0) {
            final float[] array3;
            final float n6;
            if ((n6 = (array3 = this.ab[ac])[3] * (n3 * array3[4] + n4 * array3[5] + n5 * array3[6])) > 0.0f) {
                b += array3[0] * n6;
                c += array3[1] * n6;
                d += array3[2] * n6;
            }
        }
        int ag = this.ag;
        while (--ag >= 0) {
            final float[] array4 = this.af[ag];
            final float n7 = array2[0] - array4[7];
            final float n8 = array2[1] - array4[8];
            final float n9 = array2[2] - array4[9];
            final float n10;
            final float n11;
            if ((n10 = array4[3] * (n3 * n7 + n4 * n8 + n5 * n9)) > 0.0f && (n11 = n7 * n7 + n8 * n8 + n9 * n9) != 0.0f && n11 <= array4[10]) {
                final float n12 = (float)Math.sqrt(n11);
                final float n13;
                float n14;
                float n15;
                if ((n13 = array4[4] + array4[5] * n12 + array4[6] * n11) > 1.0f) {
                    n14 = n10;
                    n15 = n13 * n12;
                }
                else {
                    n14 = n10;
                    n15 = n12;
                }
                final float n16 = n14 / n15;
                b += array4[0] * n16;
                c += array4[1] * n16;
                d += array4[2] * n16;
            }
        }
        final int n17 = 6;
        array2[n17] *= b;
        final int n18 = 7;
        array2[n18] *= c;
        final int n19 = 8;
        array2[n19] *= d;
    }
    
    public final void b(final int n, final int n2) {
        if ((n & 0xFF000000) != 0x4000000) {
            return;
        }
        if ((n2 & 0xFF000000) != 0x3000000 && n2 != -1) {
            return;
        }
        this.z[n & 0xFFFFFF][2] = n2;
    }
    
    private static void a(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        try {
            while (true) {
                int n;
                for (n = 0; n + 4 < 72; n += 4) {
                    a(inputStream, outputStream, 4);
                }
                if (n + 4 == 72) {
                    a(inputStream, outputStream, 4);
                }
                else {
                    a(inputStream, outputStream, 72 - n);
                }
            }
        }
        catch (IOException ex) {}
    }
    
    private static byte[] c(final String s) throws IOException {
        final byte[] array = new byte[s.length()];
        s.getBytes(0, s.length(), array, 0);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(byteArrayInputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    private int f(final int n) {
        if (n == 16777216) {
            int n2 = -1;
            for (int i = 0; i < this.ah.length; ++i) {
                if (this.ah[i] == null) {
                    n2 = i;
                    break;
                }
            }
            if (n2 == -1) {
                this.ah = a(this.ah);
                this.ai = a(this.ai);
                this.aj = a(this.aj);
                n2 = this.ah.length - 1;
            }
            this.aj[n2] = new float[0][];
            this.ah[n2] = new float[12];
            this.ai[n2] = new int[0];
            return n2;
        }
        if (n == 33554432) {
            int n3 = -1;
            for (int j = 0; j < this.r.length; ++j) {
                if (this.r[j] == null) {
                    n3 = j;
                    break;
                }
            }
            if (n3 == -1) {
                this.r = a(this.r);
                n3 = this.r.length - 1;
            }
            this.r[n3] = new float[7];
            return n3;
        }
        if (n == 67108864) {
            int n4 = -1;
            for (int k = 0; k < this.ak.length; ++k) {
                if (this.ak[k] == null) {
                    n4 = k;
                    break;
                }
            }
            if (n4 == -1) {
                this.ak = a(this.ak);
                this.s = a(this.s);
                this.t = a(this.t);
                this.u = a(this.u);
                this.v = a(this.v);
                this.w = a(this.w);
                this.x = a(this.x);
                this.y = a(this.y);
                this.z = a(this.z);
                this.al = a(this.al);
                this.A = a(this.A);
                this.B = a(this.B);
                this.C = a(this.C);
                this.D = a(this.D);
                this.E = a(this.E);
                this.am = a(this.am);
                n4 = this.ak.length - 1;
            }
            this.z[n4] = new int[3];
            this.al[n4] = new float[6];
            this.B[n4] = new float[0][];
            this.C[n4] = new float[0][];
            this.D[n4] = new short[0][];
            this.E[n4] = new float[0][];
            this.am[n4] = new float[0][];
            return n4;
        }
        if (n == 100663296) {
            int n5 = -1;
            for (int l = 0; l < this.an.length; ++l) {
                if (this.an[l] == null) {
                    n5 = l;
                    break;
                }
            }
            if (n5 == -1) {
                this.an = a(this.an);
                n5 = this.an.length - 1;
            }
            this.an[n5] = new float[18];
            return n5;
        }
        if (n == 83886080) {
            int n6 = -1;
            for (int n7 = 0; n7 < this.ao.length; ++n7) {
                if (this.ao[n7] == null) {
                    n6 = n7;
                    break;
                }
            }
            if (n6 == -1) {
                this.ao = a(this.ao);
                n6 = this.ao.length - 1;
            }
            this.ao[n6] = new float[13];
            return n6;
        }
        return -1;
    }
    
    public final void g() {
        this.a.b();
    }
    
    private void g(final int n) {
        if (n == -1) {
            this.H = 0.0f;
            this.I = 0.0f;
            this.J = 0.0f;
            return;
        }
        final float[] array = this.r[n & 0xFFFFFF];
        this.H = array[0];
        this.I = array[1];
        this.J = array[2];
        this.K = array[6];
    }
    
    private boolean a(final float[] array, final float[] array2, final float[] array3) {
        array2[24] = 0.0f;
        this.a(array[0], array[2], array[4], array3);
        array2[0] = this.l;
        array2[1] = this.m;
        array2[2] = this.n;
        final int k = this.k();
        this.a(array[1], array[2], array[4], array3);
        array2[3] = this.l;
        array2[4] = this.m;
        array2[5] = this.n;
        final int n = k & this.k();
        this.a(array[0], array[3], array[4], array3);
        array2[6] = this.l;
        array2[7] = this.m;
        array2[8] = this.n;
        final int n2 = n & this.k();
        this.a(array[1], array[3], array[4], array3);
        array2[9] = this.l;
        array2[10] = this.m;
        array2[11] = this.n;
        final int n3 = n2 & this.k();
        this.a(array[0], array[2], array[5], array3);
        array2[12] = this.l;
        array2[13] = this.m;
        array2[14] = this.n;
        final int n4 = n3 & this.k();
        this.a(array[1], array[2], array[5], array3);
        array2[15] = this.l;
        array2[16] = this.m;
        array2[17] = this.n;
        final int n5 = n4 & this.k();
        this.a(array[0], array[3], array[5], array3);
        array2[18] = this.l;
        array2[19] = this.m;
        array2[20] = this.n;
        final int n6 = n5 & this.k();
        this.a(array[1], array[3], array[5], array3);
        array2[21] = this.l;
        array2[22] = this.m;
        array2[23] = this.n;
        return (n6 & this.k()) == 0x0;
    }
    
    public final void a(final int n, final boolean b) {
        if ((n & 0xFF000000) != 0x4000000) {
            return;
        }
        this.z[n & 0xFFFFFF][0] = (b ? 1 : 0);
    }
    
    public final void b(final int n, final boolean b) {
        if ((n & 0xFF000000) != 0x5000000) {
            return;
        }
        this.ao[n & 0xFFFFFF][1] = (b ? 1 : 0);
    }
    
    private static void a(final float[] array, final int[] array2, final float[] array3, final int[] array4, final short[] array5) {
        int length = array5.length;
        while (--length >= 0) {
            array5[length] = 0;
        }
        int length2;
        int n = (length2 = array2.length) / 3;
        while (--n >= 0) {
            final int n2 = array2[--length2];
            final int n3 = array4[length2];
            final int n4 = array2[--length2];
            final int n5 = array4[length2];
            final int n6 = array2[--length2];
            final int n7 = array4[length2];
            int n8 = (n6 << 1) + n6;
            int n9 = (n4 << 1) + n4;
            int n10 = (n2 << 1) + n2;
            final float n11 = array[n8++];
            final float n12 = array[n8++];
            final float n13 = array[n8];
            final float n14 = array[n9++] - n11;
            final float n15 = array[n9++] - n12;
            final float n16 = array[n9] - n13;
            final float n17 = array[n10++] - n11;
            final float n18 = array[n10++] - n12;
            final float n19 = array[n10] - n13;
            if (n11 * (n15 * n19 - n16 * n18) + n12 * (n16 * n17 - n14 * n19) + n13 * (n14 * n18 - n15 * n17) < 0.0f) {
                final int n20 = n6;
                array5[n20] |= 0x1;
                final int n21 = n4;
                array5[n21] |= 0x1;
                final int n22 = n2;
                array5[n22] |= 0x1;
                final int n23 = n;
                array5[n23] |= 0x80;
                if (array3 == null) {
                    continue;
                }
                final int n24 = n7;
                array5[n24] |= 0x100;
                final int n25 = n5;
                array5[n25] |= 0x100;
                final int n26 = n3;
                array5[n26] |= 0x100;
            }
        }
    }
    
    public final int h() {
        final int f = this.f(100663296);
        this.an[f][0] = 1.0f;
        this.an[f][4] = 1.0f;
        this.an[f][8] = 1.0f;
        this.an[f][11] = -10.0f;
        this.an[f][12] = 48.0f;
        this.an[f][13] = 0.1f;
        this.an[f][14] = -1.0f;
        this.an[f][15] = 0.0f;
        this.an[f][16] = 0.0f;
        this.an[f][17] = 10.0f;
        return 100663296 + f;
    }
    
    public final void b(final String s) {
        this.a.a(s);
    }
    
    public final int i() {
        return this.a.c() | 0x3000000;
    }
    
    public final void c(final int n, final float[] array) {
        if ((n & 0xFF000000) != 0x4000000) {
            return;
        }
        if (array.length == this.ak[n & 0xFFFFFF].length) {
            System.arraycopy(array, 0, this.ak[n & 0xFFFFFF], 0, array.length);
            this.c(n);
        }
    }
    
    public final void b(final int n, final float n2, final float n3, final float n4) {
        if ((n & 0xFF000000) != 0x5000000) {
            return;
        }
        this.ao[n & 0xFFFFFF][2] = n2;
        this.ao[n & 0xFFFFFF][3] = n3;
        this.ao[n & 0xFFFFFF][4] = n4;
    }
    
    public final void c(final int n, final int n2) {
        if ((n & 0xFF000000) != 0x1000000) {
            return;
        }
        boolean b = false;
        for (int i = 0; i < this.ai[n & 0xFFFFFF].length; i += 2) {
            if (this.ai[n & 0xFFFFFF][i] == n2) {
                b = true;
            }
        }
        if (!b) {
            return;
        }
        final int length;
        final int[] array = new int[(length = this.ai[n & 0xFFFFFF].length) - 2];
        int n3 = 0;
        for (int j = 0; j < length; j += 2) {
            if (this.ai[n & 0xFFFFFF][j] == n2) {
                this.e(n2, this.ai[n & 0xFFFFFF][j + 1]);
            }
            else {
                array[n3++] = this.ai[n & 0xFFFFFF][j];
                array[n3++] = this.ai[n & 0xFFFFFF][j + 1];
            }
        }
        this.ai[n & 0xFFFFFF] = array;
    }
    
    public a(String d) {
        d("2DT5\u0002\u0012\nv&\u0014\u0010O\u0010o\u0006\u001dNB\"\u0006\u0015jQ)\u0001\n^U&\n]I_*N");
        this.b = 0.0f;
        this.c = 0.0f;
        this.d = 0.0f;
        this.L = new float[3][17];
        this.O = false;
        this.P = -1.0f;
        this.h = -1;
        this.i = -1;
        this.Q = new float[12];
        this.R = new float[12];
        this.af = new float[10][];
        Label_0230: {
            if (d.equals(d("\u0000EV3\u0010\u0012XUj\u0014\u0004P_(\n"))) {
                this.a = new b(1);
            }
            else if (d.equals(d("\u0000EV3\u0010\u0012XUj\r\tE_*"))) {
                this.a = new b(2);
            }
            else if (d.equals(d("\u0000EV3\u0010\u0012XUj\u0006\u001d^Y&\u000b\u001aKC"))) {
                this.a = new b(3);
            }
            else {
                a a = null;
                b a2 = null;
                Label_0227: {
                    if (d.equals(d("\u001bKB#\u0010\u0012XUj\u0000\u001f\u001eZ"))) {
                        try {
                            this.a = (b)Class.forName(d("\u0012\u0019T\u00067:\u0004Qt\u00034f\u0004\r")).newInstance();
                            break Label_0230;
                        }
                        catch (Throwable t) {
                            d = d("\u0000EV3\u0010\u0012XU");
                            a = this;
                            a2 = new b(0);
                            break Label_0227;
                        }
                    }
                    a = this;
                    a2 = new b(0);
                }
                a.a = a2;
            }
        }
        this.G = d("\u0012DV>T\u0017k`\u000eG\u0005\u0004") + 1 + "." + 23;
        System.out.println(d("Y\u0000\u0010") + this.G + d("SHIg") + d("2DT5\u0002\u0012\nv&\u0014\u0010O\u0010o\u0006\u001dNB\"\u0006\u0015jQ)\u0001\n^U&\n]I_*N"));
        System.out.println(d("Y\u0000\u0010#\u0015\u001a\\U5]S") + d);
        this.d();
    }
    
    private final void a(final int n, final int n2, final float[] array) {
        final int n3 = n & 0xFFFFFF;
        b(this.ah[n3], array, this.aj[n3][n2]);
        int[] array2;
        for (int length = (array2 = this.ai[n3]).length, i = 0; i < length; ++i, ++i) {
            switch (array2[i] & 0xFF000000) {
                case 16777216: {
                    this.a(array2[i], array2[i + 1], this.aj[n3][n2]);
                    break;
                }
                case 100663296: {
                    if (array2[i] == this.N) {
                        this.e(this.N, this.aj[n3][n2]);
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    private final boolean a(final short[] array, final float[] array2, final float[] array3) {
        final float g = this.g;
        final float j = this.j;
        final float k = this.k;
        final float n = this.h;
        final float n2 = this.i;
        final float n3 = -this.e;
        final float n4 = -this.f;
        int n5 = 0;
        short n6 = -1;
        for (int n7 = array2.length / 3, i = 0; i < n7; ++i) {
            if ((array[i] & 0x1) != 0x0) {
                final float n8 = array2[n5];
                final float n9 = array2[n5 + 1];
                short n14 = 0;
                Label_0228: {
                    final float n10;
                    short n18;
                    if ((n10 = array2[n5 + 2]) <= n3) {
                        final float n11 = g / n10;
                        final float n12 = n8 * n11 + j;
                        final float n13 = -n9 * n11 + k;
                        array3[n5] = n12;
                        array3[n5 + 1] = n13;
                        array3[n5 + 2] = n11;
                        n14 = 0;
                        Label_0177: {
                            short n15;
                            if (n12 < 0.0f) {
                                n15 = 2;
                            }
                            else {
                                if (n12 <= n) {
                                    break Label_0177;
                                }
                                n15 = 4;
                            }
                            n14 = n15;
                        }
                        Label_0207: {
                            short n16;
                            short n17;
                            if (n13 < 0.0f) {
                                n16 = n14;
                                n17 = 8;
                            }
                            else {
                                if (n13 <= n2) {
                                    break Label_0207;
                                }
                                n16 = n14;
                                n17 = 16;
                            }
                            n14 = (byte)(n16 | n17);
                        }
                        if (n10 >= n4) {
                            break Label_0228;
                        }
                        n18 = (byte)(n14 | 0x40);
                    }
                    else {
                        n18 = 32;
                    }
                    n14 = n18;
                }
                n6 = (byte)(n6 & n14);
                final int n19 = i;
                array[n19] |= n14;
            }
            n5 += 3;
        }
        return n6 != 0;
    }
    
    private void a(final float n, final float n2, final float n3, final float[] array) {
        this.l = n * array[0] + n2 * array[3] + n3 * array[6] + array[9];
        this.m = n * array[1] + n2 * array[4] + n3 * array[7] + array[10];
        this.n = n * array[2] + n2 * array[5] + n3 * array[8] + array[11];
    }
    
    public final void c(final int n, final float n2) {
        if ((n & 0xFF000000) != 0x5000000) {
            return;
        }
        this.ao[n & 0xFFFFFF][5] = n2;
    }
    
    private void f(int n, final int n2) {
        n &= 0xFFFFFF;
        final float[] array = this.B[n][n2];
        final int[] array2 = this.s[n];
        final float[] array3 = this.v[n];
        final int[] array4 = this.w[n];
        final float[] array5 = this.t[n];
        final int[] array6 = this.u[n];
        final float[] array7 = this.C[n][n2];
        final int[] array8 = this.y[n];
        final float[] array9 = this.E[n][n2];
        final short[] array10 = this.D[n][n2];
        final int[] array11 = this.A[n];
        final int n3 = this.s[n].length / 3;
        this.a(this.am[n][n2]);
        int n4 = (this.z[n][2] == -1 || array3 == null) ? -1 : (this.z[n][2] & 0xFFFFFF);
        int n5 = this.z[n][1];
        this.g(n5);
        final boolean b = array5 != null;
        boolean b2 = n5 != -1 | b;
        this.a.a(n4, b2, this.K);
        int n6 = -3;
        for (int i = 0; i < n3; ++i) {
            n6 += 3;
            if ((array10[i] & 0x80) != 0x0) {
                final int n7 = array2[n6];
                final int n8 = array2[n6 + 1];
                final int n9 = array2[n6 + 2];
                if ((array10[n7] & array10[n8] & array10[n9] & 0x3E) == 0x0) {
                    final short n10 = (short)(array10[n7] | array10[n8] | array10[n9]);
                    final int n11 = (n7 << 1) + n7;
                    final int n12 = (n8 << 1) + n8;
                    final int n13 = (n9 << 1) + n9;
                    if (array11 != null) {
                        final int n14;
                        n5 = array11[n14 = i << 1];
                        if ((n4 = array11[n14 + 1]) != -1) {
                            n4 &= 0xFFFFFF;
                        }
                        b2 = (n5 != -1 | b);
                        this.g(n5);
                        this.a.a(n4, b2, this.K);
                    }
                    final float[] array12 = this.L[0];
                    final float[] array13 = this.L[1];
                    final float[] array14 = this.L[2];
                    System.arraycopy(array, n11, array12, 0, 3);
                    System.arraycopy(array, n12, array13, 0, 3);
                    System.arraycopy(array, n13, array14, 0, 3);
                    System.arraycopy(array9, n11, array12, 3, 3);
                    System.arraycopy(array9, n12, array13, 3, 3);
                    System.arraycopy(array9, n13, array14, 3, 3);
                    if (n4 != -1) {
                        System.arraycopy(array3, array4[n6] << 1, array12, 9, 2);
                        System.arraycopy(array3, array4[n6 + 1] << 1, array13, 9, 2);
                        System.arraycopy(array3, array4[n6 + 2] << 1, array14, 9, 2);
                    }
                    if (b2) {
                        if (b) {
                            final float[] array15 = array5;
                            final int n15 = array6[n6];
                            System.arraycopy(array15, n15 + (n15 << 1), array12, 6, 3);
                            final float[] array16 = array5;
                            final int n16 = array6[n6 + 1];
                            System.arraycopy(array16, n16 + (n16 << 1), array13, 6, 3);
                            final float[] array17 = array5;
                            final int n17 = array6[n6 + 2];
                            System.arraycopy(array17, n17 + (n17 << 1), array14, 6, 3);
                        }
                        else {
                            float[] array18;
                            int n18;
                            float j;
                            if (n5 != -1) {
                                array12[6] = this.H;
                                array13[6] = this.H;
                                array14[6] = this.H;
                                array12[7] = this.I;
                                array13[7] = this.I;
                                array14[7] = this.I;
                                array12[8] = this.J;
                                array13[8] = this.J;
                                array18 = array14;
                                n18 = 8;
                                j = this.J;
                            }
                            else {
                                final float[] array19 = array12;
                                final int n19 = 6;
                                final float[] array20 = array13;
                                final int n20 = 6;
                                final float[] array21 = array14;
                                final int n21 = 6;
                                final float n22 = 1.0f;
                                array21[n21] = n22;
                                array19[n19] = (array20[n20] = n22);
                                final float[] array22 = array12;
                                final int n23 = 7;
                                final float[] array23 = array13;
                                final int n24 = 7;
                                final float[] array24 = array14;
                                final int n25 = 7;
                                final float n26 = 1.0f;
                                array24[n25] = n26;
                                array22[n23] = (array23[n24] = n26);
                                array18 = array12;
                                n18 = 8;
                                array13[8] = (array14[8] = (j = 1.0f));
                            }
                            array18[n18] = j;
                        }
                        if (array7 != null) {
                            this.a(array7, array8[n6], array12);
                            this.a(array7, array8[n6 + 1], array13);
                            this.a(array7, array8[n6 + 2], array14);
                        }
                        final float n27 = 0.009f;
                        final float[] array25;
                        int n29;
                        float n28;
                        if (array12[6] < 0.99f) {
                            array25 = array12;
                            n28 = array25[n29 = 6] + n27;
                        }
                        else {
                            n29 = 6;
                            n28 = 1.0f;
                        }
                        array25[n29] = n28;
                        final float[] array26;
                        int n31;
                        float n30;
                        if (array12[7] < 0.99f) {
                            array26 = array12;
                            n30 = array26[n31 = 7] + n27;
                        }
                        else {
                            n31 = 7;
                            n30 = 1.0f;
                        }
                        array26[n31] = n30;
                        final float[] array27;
                        int n33;
                        float n32;
                        if (array12[8] < 0.99f) {
                            array27 = array12;
                            n32 = array27[n33 = 8] + n27;
                        }
                        else {
                            n33 = 8;
                            n32 = 1.0f;
                        }
                        array27[n33] = n32;
                        final float[] array28;
                        int n35;
                        float n34;
                        if (array13[6] < 0.99f) {
                            array28 = array13;
                            n34 = array28[n35 = 6] + n27;
                        }
                        else {
                            n35 = 6;
                            n34 = 1.0f;
                        }
                        array28[n35] = n34;
                        final float[] array29;
                        int n37;
                        float n36;
                        if (array13[7] < 0.99f) {
                            array29 = array13;
                            n36 = array29[n37 = 7] + n27;
                        }
                        else {
                            n37 = 7;
                            n36 = 1.0f;
                        }
                        array29[n37] = n36;
                        final float[] array30;
                        int n39;
                        float n38;
                        if (array13[8] < 0.99f) {
                            array30 = array13;
                            n38 = array30[n39 = 8] + n27;
                        }
                        else {
                            n39 = 8;
                            n38 = 1.0f;
                        }
                        array30[n39] = n38;
                        final float[] array31;
                        int n41;
                        float n40;
                        if (array14[6] < 0.99f) {
                            array31 = array14;
                            n40 = array31[n41 = 6] + n27;
                        }
                        else {
                            n41 = 6;
                            n40 = 1.0f;
                        }
                        array31[n41] = n40;
                        final float[] array32;
                        int n43;
                        float n42;
                        if (array14[7] < 0.99f) {
                            array32 = array14;
                            n42 = array32[n43 = 7] + n27;
                        }
                        else {
                            n43 = 7;
                            n42 = 1.0f;
                        }
                        array32[n43] = n42;
                        final float[] array33;
                        int n45;
                        float n44;
                        if (array14[8] < 0.99f) {
                            array33 = array14;
                            n44 = array33[n45 = 8] + n27;
                        }
                        else {
                            n45 = 8;
                            n44 = 1.0f;
                        }
                        array33[n45] = n44;
                    }
                    this.a.a(array12, array13, array14, n10);
                }
            }
        }
    }
    
    public final void c(final int n, final float n2, final float n3, final float n4) {
        if ((n & 0xFF000000) != 0x2000000) {
            return;
        }
        final int n5 = n & 0xFFFFFF;
        this.r[n5][0] = n2;
        this.r[n5][1] = n3;
        this.r[n5][2] = n4;
    }
    
    public final void a(final int n) {
        if ((n & 0xFF000000) != 0x6000000) {
            return;
        }
        this.N = n;
    }
    
    private void e(final int n, final float[] array) {
        final float[] array2 = this.an[n & 0xFFFFFF];
        this.e = array2[13];
        final float n2;
        a a;
        float f;
        if ((n2 = array2[14]) == -1.0f) {
            a = this;
            f = 10000.0f;
        }
        else {
            a = this;
            f = n2;
        }
        a.f = f;
        if (this.O) {
            this.f = this.P;
        }
        this.g = -(this.i >> 1) / (float)Math.tan(array2[12] * 3.141592653589793 / 360.0);
        this.a.a(this.g, this.e, this.f);
        System.arraycopy(array2, 0, this.Q, 0, 12);
        a(array, this.R);
        b(this.R, this.Q, this.Q);
        a(this.Q, this.R);
    }
    
    static {
        F = new int[] { 1195984440, 962679808, 201368064, 3158064, -218959134, -488472191, -2117942590, 1077952767, -65536, 2226436, 0, 2883584, 21504, 201326595, -8865060, -21987000, -1413613558, 870585925, 1669618025, 1721971460, -1071609628, -921640496, 218398168, 2067722556, 92299280, 1954950082, -2128076684, 1979957316, 273687635, 194507216, 172269886, 1657239856, 1416879074, -770353809, -1377926655, 547844580, -1238761285, -1182726568, 125334021, 1292174383, 73294464, 757072163, 1584143227, 87567987, -2124384506, 174336291, 772507436, 1870739467, -1789103260, -1469774469, 1312398769, 944013830, 1313690969, 1906089501, 691559217, 480653625, -1452037241, 927641351, 462842577, -1581708656, 769098347, -1425837376, -797783095, 1915570816, 557132875, -2059966514, 180532252, 1893660283, 501248514, 2111888764, -1642757516, -2071284609, 1141030080, -1264200397, 1295129676, -1342128117, -2088131808, 1214320675, 279070474, 358579228, 1236419083, -1943721180, 15104 };
        ap = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        aq = new byte[256];
        a.ar = new byte[4];
        int n = 0;
        do {
            a.aq[n] = -1;
        } while (++n < 255);
        for (int i = 0; i < a.ap.length; ++i) {
            a.aq[a.ap[i]] = (byte)i;
        }
    }
    
    private static void b(final float[] array, final float[] array2, final float[] array3) {
        final float n = array[0] * array2[0] + array[1] * array2[3] + array[2] * array2[6];
        final float n2 = array[0] * array2[1] + array[1] * array2[4] + array[2] * array2[7];
        final float n3 = array[0] * array2[2] + array[1] * array2[5] + array[2] * array2[8];
        final float n4 = array[3] * array2[0] + array[4] * array2[3] + array[5] * array2[6];
        final float n5 = array[3] * array2[1] + array[4] * array2[4] + array[5] * array2[7];
        final float n6 = array[3] * array2[2] + array[4] * array2[5] + array[5] * array2[8];
        final float n7 = array[6] * array2[0] + array[7] * array2[3] + array[8] * array2[6];
        final float n8 = array[6] * array2[1] + array[7] * array2[4] + array[8] * array2[7];
        final float n9 = array[6] * array2[2] + array[7] * array2[5] + array[8] * array2[8];
        final float n10 = array[9] * array2[0] + array[10] * array2[3] + array[11] * array2[6] + array2[9];
        final float n11 = array[9] * array2[1] + array[10] * array2[4] + array[11] * array2[7] + array2[10];
        final float n12 = array[9] * array2[2] + array[10] * array2[5] + array[11] * array2[8] + array2[11];
        array3[0] = n;
        array3[1] = n2;
        array3[2] = n3;
        array3[3] = n4;
        array3[4] = n5;
        array3[5] = n6;
        array3[6] = n7;
        array3[7] = n8;
        array3[8] = n9;
        array3[9] = n10;
        array3[10] = n11;
        array3[11] = n12;
    }
    
    public final int a(final float[] array, final int[] array2, final float[] array3, int[] array4, final float[] array5, int[] array6, final float[] array7, int[] array8, final int[] array9, final int[] array10) {
        final int f = this.f(67108864);
        if (array4 == null) {
            array4 = array2;
        }
        if (array6 == null) {
            array6 = array2;
        }
        if (array8 == null) {
            array8 = array2;
        }
        System.arraycopy(array, 0, this.ak[f] = new float[array.length], 0, array.length);
        System.arraycopy(array2, 0, this.s[f] = new int[array2.length], 0, array2.length);
        if (array3 != null) {
            System.arraycopy(array3, 0, this.t[f] = new float[array3.length], 0, array3.length);
            System.arraycopy(array4, 0, this.u[f] = new int[array4.length], 0, array4.length);
        }
        else {
            this.t[f] = null;
            this.u[f] = this.s[f];
        }
        if (array5 != null) {
            System.arraycopy(array5, 0, this.v[f] = new float[array5.length], 0, array5.length);
            System.arraycopy(array6, 0, this.w[f] = new int[array6.length], 0, array6.length);
        }
        else {
            this.v[f] = null;
            this.w[f] = this.s[f];
        }
        if (array7 != null) {
            System.arraycopy(array7, 0, this.x[f] = new float[array7.length], 0, array7.length);
            System.arraycopy(array8, 0, this.y[f] = new int[array8.length], 0, array8.length);
        }
        else {
            this.x[f] = null;
            this.y[f] = this.s[f];
        }
        int[][] array12;
        int n2;
        int[] array13;
        if (array9 != null || array10 != null) {
            final int n;
            final int[] array11;
            int length = (array11 = new int[(n = array2.length / 3) << 1]).length;
            while (--length >= 0) {
                array11[length] = -1;
            }
            if (array9 != null) {
                for (int i = 0; i < n; ++i) {
                    array11[i << 1] = array9[i];
                }
            }
            if (array10 != null) {
                for (int j = 0; j < n; ++j) {
                    array11[(j << 1) + 1] = array10[j];
                }
            }
            array12 = this.A;
            n2 = f;
            array13 = array11;
        }
        else {
            array12 = this.A;
            n2 = f;
            array13 = null;
        }
        array12[n2] = array13;
        this.z[f][0] = 1;
        this.z[f][1] = -1;
        this.z[f][2] = -1;
        this.c(f);
        return 67108864 + f;
    }
    
    public final void j() {
        this.a.d();
    }
    
    private final void a(final float[] array) {
        if (this.af.length < this.ae) {
            this.af = new float[this.ae][];
        }
        this.ag = 0;
        int ae = this.ae;
        while (--ae >= 0) {
            final float[] array2;
            final float n = (array2 = this.ad[ae])[7];
            final float n2 = array2[8];
            final float n3 = array2[9];
            final float n4 = array2[10];
            final float n5 = array[0] - n;
            final float n6 = array[1] - n2;
            final float n7 = array[2] - n3;
            if (n5 * n5 + n6 * n6 + n7 * n7 >= n4) {
                final float n8 = array[3] - n;
                final float n9 = array[4] - n2;
                final float n10 = array[5] - n3;
                if (n8 * n8 + n9 * n9 + n10 * n10 >= n4) {
                    final float n11 = array[6] - n;
                    final float n12 = array[7] - n2;
                    final float n13 = array[8] - n3;
                    if (n11 * n11 + n12 * n12 + n13 * n13 >= n4) {
                        final float n14 = array[9] - n;
                        final float n15 = array[10] - n2;
                        final float n16 = array[11] - n3;
                        if (n14 * n14 + n15 * n15 + n16 * n16 >= n4) {
                            final float n17 = array[12] - n;
                            final float n18 = array[13] - n2;
                            final float n19 = array[14] - n3;
                            if (n17 * n17 + n18 * n18 + n19 * n19 >= n4) {
                                final float n20 = array[15] - n;
                                final float n21 = array[16] - n2;
                                final float n22 = array[17] - n3;
                                if (n20 * n20 + n21 * n21 + n22 * n22 >= n4) {
                                    final float n23 = array[18] - n;
                                    final float n24 = array[19] - n2;
                                    final float n25 = array[20] - n3;
                                    if (n23 * n23 + n24 * n24 + n25 * n25 >= n4) {
                                        final float n26 = array[21] - n;
                                        final float n27 = array[22] - n2;
                                        final float n28 = array[23] - n3;
                                        if (n26 * n26 + n27 * n27 + n28 * n28 >= n4) {
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            this.af[this.ag++] = array2;
        }
    }
    
    private void g(final int n, final int n2) {
        final int n3 = n & 0xFFFFFF;
        final float[] array;
        b(array = this.aj[n3][n2], this.Q, array);
        final int[] array2;
        final int length = (array2 = this.ai[n3]).length;
        int i = 0;
        while (i < length) {
            final int n4 = array2[i++];
            final int n5 = array2[i++];
            switch (n4 & 0xFF000000) {
                default: {
                    continue;
                }
                case 16777216: {
                    this.g(n4, n5);
                    continue;
                }
                case 67108864: {
                    this.a(n4, n5, array, n, n2);
                    continue;
                }
                case 83886080: {
                    this.d(n4, array);
                    continue;
                }
            }
        }
    }
    
    public final void d(final int n, final int n2) {
        if ((n & 0xFF000000) != 0x4000000) {
            return;
        }
        if ((n2 & 0xFF000000) != 0x2000000 && n2 != -1) {
            return;
        }
        this.z[n & 0xFFFFFF][1] = n2;
    }
    
    private void b(final float n, final float n2, final float n3, final float[] array) {
        this.l = n * array[0] + n2 * array[3] + n3 * array[6];
        this.m = n * array[1] + n2 * array[4] + n3 * array[7];
        this.n = n * array[2] + n2 * array[5] + n3 * array[8];
        final double sqrt;
        if ((sqrt = Math.sqrt(this.l * this.l + this.m * this.m + this.n * this.n)) > 0.0) {
            final double n4 = 1.0 / sqrt;
            this.l *= (float)n4;
            this.m *= (float)n4;
            this.n *= (float)n4;
        }
    }
    
    public final void b(final int m) {
        if ((m & 0xFF000000) != 0x1000000) {
            return;
        }
        this.e(this.M = m);
    }
    
    private static String d(final String s) {
        char[] charArray;
        for (int length = (charArray = s.toCharArray()).length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 's';
                    break;
                }
                case 1: {
                    c2 = '*';
                    break;
                }
                case 2: {
                    c2 = '0';
                    break;
                }
                case 3: {
                    c2 = 'G';
                    break;
                }
                default: {
                    c2 = 'g';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
