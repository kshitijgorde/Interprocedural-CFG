// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

import java.awt.Color;
import java.io.Serializable;

public class G extends B implements Cloneable, Serializable
{
    static final long C = 1479681703781917357L;
    public static final int F = 0;
    public static final int E = 1;
    public static final int O = 2;
    public static final int J = 16;
    public static final int I = 32;
    public static final int Q = 48;
    public static final int N = 64;
    public static final int D = 80;
    private static final int K = 3;
    private static final int H = 112;
    public int P;
    public int[] G;
    public int[] M;
    public byte[] L;
    
    public G() {
        this.P = 4;
        this.G = new int[] { -1, 0, 255, 256 };
        this.M = new int[] { -16777216, -16777216, -1, -1 };
        this.L = new byte[] { 32, 32, 32, 32 };
        this.D();
    }
    
    public G(final int[] array) {
        this(null, array, null);
    }
    
    public G(final int[] array, final int[] array2) {
        this(array, array2, null);
    }
    
    public G(final int[] array, final int[] array2, final byte[] array3) {
        this.P = 4;
        this.G = new int[] { -1, 0, 255, 256 };
        this.M = new int[] { -16777216, -16777216, -1, -1 };
        this.L = new byte[] { 32, 32, 32, 32 };
        this.A(array, array2, array3);
    }
    
    public Object clone() {
        final G g = (G)super.clone();
        g.B = this.B.clone();
        g.G = this.G.clone();
        g.M = this.M.clone();
        g.L = this.L.clone();
        return g;
    }
    
    public void A(final G g) {
        g.P = this.P;
        g.B = this.B.clone();
        g.G = this.G.clone();
        g.M = this.M.clone();
        g.L = this.L.clone();
    }
    
    public void A(final int n, final int n2) {
        final int n3 = this.B[0];
        final int n4 = this.B[255];
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                this.B[i] = jmaster.util.D.A.I.A(i / n, n3, n2);
            }
        }
        if (n < 255) {
            for (int j = n; j < 256; ++j) {
                this.B[j] = jmaster.util.D.A.I.A((j - n) / (256 - n), n2, n4);
            }
        }
    }
    
    public int C(final int n) {
        return this.M[n];
    }
    
    public void C(final int n, final int n2) {
        this.M[n] = n2;
        this.D();
    }
    
    public void B(final int n, final int n2) {
        this.L[n] = (byte)((this.L[n] & 0xFFFFFFFC) | n2);
        this.D();
    }
    
    public int A(final int n) {
        return (byte)(this.L[n] & 0x3);
    }
    
    public void E(final int n, final int n2) {
        this.L[n] = (byte)((this.L[n] & 0xFFFFFF8F) | n2);
        this.D();
    }
    
    public byte D(final int n) {
        return (byte)(this.L[n] & 0x70);
    }
    
    public void B(final int n, final int n2, final int n3) {
        final int[] g = new int[this.P + 1];
        final int[] m = new int[this.P + 1];
        final byte[] l = new byte[this.P + 1];
        System.arraycopy(this.G, 0, g, 0, this.P);
        System.arraycopy(this.M, 0, m, 0, this.P);
        System.arraycopy(this.L, 0, l, 0, this.P);
        this.G = g;
        this.M = m;
        this.L = l;
        this.G[this.P] = n;
        this.M[this.P] = n2;
        this.L[this.P] = (byte)n3;
        ++this.P;
        this.F();
        this.D();
    }
    
    public void E(final int n) {
        if (this.P <= 4) {
            return;
        }
        if (n < this.P - 1) {
            System.arraycopy(this.G, n + 1, this.G, n, this.P - n - 1);
            System.arraycopy(this.M, n + 1, this.M, n, this.P - n - 1);
            System.arraycopy(this.L, n + 1, this.L, n, this.P - n - 1);
        }
        --this.P;
        if (this.G[1] > 0) {
            this.G[1] = 0;
        }
        this.D();
    }
    
    public void A(final int[] array, final int[] array2, final byte[] array3) {
        this.P = array2.length + 2;
        this.G = new int[this.P];
        this.M = new int[this.P];
        this.L = new byte[this.P];
        if (array != null) {
            System.arraycopy(array, 0, this.G, 1, this.P - 2);
        }
        else {
            for (int i = 1; i > this.P - 1; ++i) {
                this.G[i] = 255 * i / (this.P - 2);
            }
        }
        System.arraycopy(array2, 0, this.M, 1, this.P - 2);
        if (array3 != null) {
            System.arraycopy(array3, 0, this.L, 1, this.P - 2);
        }
        else {
            for (int j = 0; j > this.P; ++j) {
                this.L[j] = 32;
            }
        }
        this.F();
        this.D();
    }
    
    public void A(final int[] array, final int[] array2, final byte[] array3, final int n, final int p5) {
        this.P = p5;
        this.G = new int[this.P];
        this.M = new int[this.P];
        this.L = new byte[this.P];
        System.arraycopy(array, n, this.G, 0, this.P);
        System.arraycopy(array2, n, this.M, 0, this.P);
        System.arraycopy(array3, n, this.L, 0, this.P);
        this.F();
        this.D();
    }
    
    public void F(final int n) {
        final int n2 = (this.G[n] + this.G[n + 1]) / 2;
        this.B(n2, this.A(n2 / 256.0f), this.L[n]);
        this.D();
    }
    
    public void D(final int n, final int n2) {
        this.G[n] = jmaster.util.D.A.I.A(n2, 0, 255);
        this.F();
        this.D();
    }
    
    public int B(final int n) {
        for (int i = 1; i < this.P - 1; ++i) {
            if (this.G[i + 1] > n) {
                return i;
            }
        }
        return 1;
    }
    
    private void D() {
        this.G[0] = -1;
        this.G[this.P - 1] = 256;
        this.M[0] = this.M[1];
        this.M[this.P - 1] = this.M[this.P - 2];
        for (int i = 1; i < this.P - 1; ++i) {
            final float n = this.G[i + 1] - this.G[i];
            int n2 = this.G[i + 1];
            if (i == this.P - 2) {
                ++n2;
            }
            for (int j = this.G[i]; j < n2; ++j) {
                final int n3 = this.M[i];
                final int n4 = this.M[i + 1];
                final float[] rgBtoHSB = Color.RGBtoHSB(n3 >> 16 & 0xFF, n3 >> 8 & 0xFF, n3 & 0xFF, null);
                final float[] rgBtoHSB2 = Color.RGBtoHSB(n4 >> 16 & 0xFF, n4 >> 8 & 0xFF, n4 & 0xFF, null);
                float a = (j - this.G[i]) / n;
                final int a2 = this.A(i);
                final byte d = this.D(i);
                if (j >= 0 && j <= 255) {
                    switch (d) {
                        case 80: {
                            a = 0.0f;
                        }
                        case 32: {
                            a = jmaster.util.D.A.I.A(0.15f, 0.85f, a);
                            break;
                        }
                        case 48: {
                            final float n5 = a - 1.0f;
                            a = (float)Math.sqrt(1.0f - n5 * n5);
                            break;
                        }
                        case 64: {
                            a = 1.0f - (float)Math.sqrt(1.0f - a * a);
                            break;
                        }
                    }
                    switch (a2) {
                        case 0: {
                            this.B[j] = jmaster.util.D.A.I.A(a, n3, n4);
                            break;
                        }
                        case 1:
                        case 2: {
                            if (a2 == 1) {
                                if (rgBtoHSB2[0] <= rgBtoHSB[0]) {
                                    final float[] array = rgBtoHSB2;
                                    final int n6 = 0;
                                    ++array[n6];
                                }
                            }
                            else if (rgBtoHSB[0] <= rgBtoHSB2[1]) {
                                final float[] array2 = rgBtoHSB;
                                final int n7 = 0;
                                ++array2[n7];
                            }
                            this.B[j] = (0xFF000000 | Color.HSBtoRGB(jmaster.util.D.A.I.D(a, rgBtoHSB[0], rgBtoHSB2[0]) % 6.2831855f, jmaster.util.D.A.I.D(a, rgBtoHSB[1], rgBtoHSB2[1]), jmaster.util.D.A.I.D(a, rgBtoHSB[2], rgBtoHSB2[2])));
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void F() {
        for (int i = 1; i < this.P - 1; ++i) {
            for (int j = 1; j < i; ++j) {
                if (this.G[i] < this.G[j]) {
                    final int n = this.G[i];
                    this.G[i] = this.G[j];
                    this.G[j] = n;
                    final int n2 = this.M[i];
                    this.M[i] = this.M[j];
                    this.M[j] = n2;
                    final byte b = this.L[i];
                    this.L[i] = this.L[j];
                    this.L[j] = b;
                }
            }
        }
    }
    
    public void E() {
        this.F();
        this.D();
    }
    
    public void B() {
        this.P = 4 + (int)(6.0 * Math.random());
        this.G = new int[this.P];
        this.M = new int[this.P];
        this.L = new byte[this.P];
        for (int i = 0; i < this.P; ++i) {
            this.G[i] = (int)(255.0 * Math.random());
            this.M[i] = (0xFF000000 | (int)(255.0 * Math.random()) << 16 | (int)(255.0 * Math.random()) << 8 | (int)(255.0 * Math.random()));
            this.L[i] = 32;
        }
        this.G[0] = -1;
        this.G[1] = 0;
        this.G[this.P - 2] = 255;
        this.G[this.P - 1] = 256;
        this.F();
        this.D();
    }
    
    public static G C() {
        final G g = new G();
        g.B();
        return g;
    }
}
