// 
// Decompiled by Procyon v0.5.30
// 

package Z;

class AI
{
    int I;
    int Z;
    int[] C;
    private int abs;
    private int floor;
    private int pow;
    private int B;
    private int D;
    private int[] F;
    
    final int I(final Z z) {
        if (z.C(24) != 5653314) {
            I();
            return -1;
        }
        this.I = z.C(16);
        this.Z = z.C(24);
        if (this.Z == -1) {
            I();
            return -1;
        }
        switch (z.C(1)) {
            case 0: {
                this.C = new int[this.Z];
                if (z.C(1) != 0) {
                    for (int i = 0; i < this.Z; ++i) {
                        if (z.C(1) != 0) {
                            final int c = z.C(5);
                            if (c == -1) {
                                I();
                                return -1;
                            }
                            this.C[i] = c + 1;
                        }
                        else {
                            this.C[i] = 0;
                        }
                    }
                    break;
                }
                for (int j = 0; j < this.Z; ++j) {
                    final int c2 = z.C(5);
                    if (c2 == -1) {
                        I();
                        return -1;
                    }
                    this.C[j] = c2 + 1;
                }
                break;
            }
            case 1: {
                int n = z.C(5) + 1;
                this.C = new int[this.Z];
                int k = 0;
                while (k < this.Z) {
                    final int c3 = z.C(floor(this.Z - k));
                    if (c3 == -1) {
                        I();
                        return -1;
                    }
                    for (int l = 0; l < c3; ++l, ++k) {
                        this.C[k] = n;
                    }
                    ++n;
                }
                break;
            }
            default: {
                return -1;
            }
        }
        switch (this.abs = z.C(4)) {
            case 0: {
                break;
            }
            case 1:
            case 2: {
                this.floor = z.C(32);
                this.pow = z.C(32);
                this.B = z.C(4) + 1;
                this.D = z.C(1);
                int abs = 0;
                switch (this.abs) {
                    case 1: {
                        abs = this.abs();
                        break;
                    }
                    case 2: {
                        abs = this.Z * this.I;
                        break;
                    }
                }
                this.F = new int[abs];
                for (int n2 = 0; n2 < abs; ++n2) {
                    this.F[n2] = z.C(this.B);
                }
                if (this.F[abs - 1] == -1) {
                    I();
                    return -1;
                }
                break;
            }
            default: {
                I();
                return -1;
            }
        }
        return 0;
    }
    
    private int abs() {
        int n = (int)Math.floor(Math.pow(this.Z, 1.0 / this.I));
        while (true) {
            int n2 = 1;
            int n3 = 1;
            for (int i = 0; i < this.I; ++i) {
                n2 *= n;
                n3 *= n + 1;
            }
            if (n2 <= this.Z && n3 > this.Z) {
                break;
            }
            if (n2 > this.Z) {
                --n;
            }
            else {
                ++n;
            }
        }
        return n;
    }
    
    static final void I() {
    }
    
    final float[] Z() {
        if (this.abs == 1 || this.abs == 2) {
            final float pow = pow(this.floor);
            final float pow2 = pow(this.pow);
            final float[] array = new float[this.Z * this.I];
            switch (this.abs) {
                case 1: {
                    final int abs = this.abs();
                    for (int i = 0; i < this.Z; ++i) {
                        float n = 0.0f;
                        int n2 = 1;
                        for (int j = 0; j < this.I; ++j) {
                            final float n3 = Math.abs((float)this.F[i / n2 % abs]) * pow2 + pow + n;
                            if (this.D != 0) {
                                n = n3;
                            }
                            array[i * this.I + j] = n3;
                            n2 *= abs;
                        }
                    }
                    break;
                }
                case 2: {
                    for (int k = 0; k < this.Z; ++k) {
                        float n4 = 0.0f;
                        for (int l = 0; l < this.I; ++l) {
                            final float n5 = Math.abs((float)this.F[k * this.I + l]) * pow2 + pow + n4;
                            if (this.D != 0) {
                                n4 = n5;
                            }
                            array[k * this.I + l] = n5;
                        }
                    }
                    break;
                }
            }
            return array;
        }
        return null;
    }
    
    private static int floor(int i) {
        int n = 0;
        while (i != 0) {
            ++n;
            i >>>= 1;
        }
        return n;
    }
    
    private static float pow(final int n) {
        float n2 = n & 0x1FFFFF;
        final float n3 = (n & 0x7FE00000) >>> 21;
        if ((n & Integer.MIN_VALUE) != 0x0) {
            n2 = -n2;
        }
        return abs(n2, (int)n3 - 20 - 768);
    }
    
    private static float abs(final float n, final int n2) {
        return (float)(n * Math.pow(2.0, n2));
    }
}
