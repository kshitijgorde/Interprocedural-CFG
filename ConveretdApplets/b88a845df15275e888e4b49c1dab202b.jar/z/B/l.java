// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

public class l
{
    byte[][] A;
    byte[] C;
    byte[] G;
    int O;
    int M;
    int K;
    int I;
    int D;
    int L;
    int E;
    int B;
    int J;
    int F;
    int N;
    int[] H;
    
    public l(final int l, final int b, final int j) {
        this.C = null;
        this.M = 9;
        this.F = 0;
        this.N = 0;
        this.H = new int[] { 511, 1023, 2047, 4095 };
        this.L = l;
        this.B = b;
        this.J = j;
    }
    
    public byte[] A(final byte[] c, final byte[] g, final int e) {
        if (c[0] == 0 && c[1] == 1) {
            throw new UnsupportedOperationException(m.A("TIFFLZWDecoder0"));
        }
        this.A();
        this.C = c;
        this.E = e;
        this.G = g;
        this.K = 0;
        this.I = 0;
        this.D = 0;
        this.F = 0;
        this.N = 0;
        int n = 0;
        int b;
        while ((b = this.B()) != 257 && this.D < g.length) {
            if (b == 256) {
                this.A();
                final int b2 = this.B();
                if (b2 == 257) {
                    break;
                }
                this.B(this.A[b2]);
                n = b2;
            }
            else if (b < this.O) {
                final byte[] array = this.A[b];
                this.B(array);
                this.B(this.A[n], array[0]);
                n = b;
            }
            else {
                final byte[] array2 = this.A[n];
                final byte[] a = this.A(array2, array2[0]);
                this.B(a);
                this.A(a);
                n = b;
            }
        }
        if (this.B == 2) {
            for (int i = 0; i < e; ++i) {
                int n2 = this.J * (i * this.L + 1);
                for (int j = this.J; j < this.L * this.J; ++j) {
                    final int n3 = n2;
                    g[n3] += g[n2 - this.J];
                    ++n2;
                }
            }
        }
        return g;
    }
    
    public void A() {
        this.A = new byte[4096][];
        for (int i = 0; i < 256; ++i) {
            (this.A[i] = new byte[1])[0] = (byte)i;
        }
        this.O = 258;
        this.M = 9;
    }
    
    public void B(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.G[this.D++] = array[i];
        }
    }
    
    public void B(final byte[] array, final byte b) {
        final int length = array.length;
        final byte[] array2 = new byte[length + 1];
        System.arraycopy(array, 0, array2, 0, length);
        array2[length] = b;
        this.A[this.O++] = array2;
        if (this.O == 511) {
            this.M = 10;
        }
        else if (this.O == 1023) {
            this.M = 11;
        }
        else if (this.O == 2047) {
            this.M = 12;
        }
    }
    
    public void A(final byte[] array) {
        this.A[this.O++] = array;
        if (this.O == 511) {
            this.M = 10;
        }
        else if (this.O == 1023) {
            this.M = 11;
        }
        else if (this.O == 2047) {
            this.M = 12;
        }
    }
    
    public byte[] A(final byte[] array, final byte b) {
        final int length = array.length;
        final byte[] array2 = new byte[length + 1];
        System.arraycopy(array, 0, array2, 0, length);
        array2[length] = b;
        return array2;
    }
    
    public int B() {
        try {
            this.F = (this.F << 8 | (this.C[this.K++] & 0xFF));
            this.N += 8;
            if (this.N < this.M) {
                this.F = (this.F << 8 | (this.C[this.K++] & 0xFF));
                this.N += 8;
            }
            final int n = this.F >> this.N - this.M & this.H[this.M - 9];
            this.N -= this.M;
            return n;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return 257;
        }
    }
}
