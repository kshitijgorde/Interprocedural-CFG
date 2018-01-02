// 
// Decompiled by Procyon v0.5.30
// 

public class ac
{
    public int a;
    public int b;
    public int[] c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int[] i;
    
    public int a(final w w) {
        if (w.d(24) != 5653314) {
            this.a();
            return -1;
        }
        this.a = w.d(16);
        this.b = w.d(24);
        if (this.b == -1) {
            this.a();
            return -1;
        }
        switch (w.d(1)) {
            case 0: {
                this.c = new int[this.b];
                if (w.d(1) != 0) {
                    for (int i = 0; i < this.b; ++i) {
                        if (w.d(1) != 0) {
                            final int d = w.d(5);
                            if (d == -1) {
                                this.a();
                                return -1;
                            }
                            this.c[i] = d + 1;
                        }
                        else {
                            this.c[i] = 0;
                        }
                    }
                    break;
                }
                for (int j = 0; j < this.b; ++j) {
                    final int d2 = w.d(5);
                    if (d2 == -1) {
                        this.a();
                        return -1;
                    }
                    this.c[j] = d2 + 1;
                }
                break;
            }
            case 1: {
                int n = w.d(5) + 1;
                this.c = new int[this.b];
                int k = 0;
                while (k < this.b) {
                    final int d3 = w.d(ae.a(this.b - k, 0));
                    if (d3 == -1) {
                        this.a();
                        return -1;
                    }
                    for (int l = 0; l < d3; ++l, ++k) {
                        this.c[k] = n;
                    }
                    ++n;
                }
                break;
            }
            default: {
                return -1;
            }
        }
        switch (this.d = w.d(4)) {
            case 0: {
                break;
            }
            case 1:
            case 2: {
                this.e = w.d(32);
                this.f = w.d(32);
                this.g = w.d(4) + 1;
                this.h = w.d(1);
                int c = 0;
                switch (this.d) {
                    case 1: {
                        c = this.c();
                        break;
                    }
                    case 2: {
                        c = this.b * this.a;
                        break;
                    }
                }
                this.i = new int[c];
                for (int n2 = 0; n2 < c; ++n2) {
                    this.i[n2] = w.d(this.g);
                }
                if (this.i[c - 1] == -1) {
                    this.a();
                    return -1;
                }
                break;
            }
            default: {
                this.a();
                return -1;
            }
        }
        return 0;
    }
    
    private int c() {
        int n = (int)Math.floor(Math.pow(this.b, 1.0 / this.a));
        while (true) {
            int n2 = 1;
            int n3 = 1;
            for (int i = 0; i < this.a; ++i) {
                n2 *= n;
                n3 *= n + 1;
            }
            if (n2 <= this.b && n3 > this.b) {
                break;
            }
            if (n2 > this.b) {
                --n;
            }
            else {
                ++n;
            }
        }
        return n;
    }
    
    public void a() {
    }
    
    public float[] b() {
        if (this.d == 1 || this.d == 2) {
            final float a = a(this.e);
            final float a2 = a(this.f);
            final float[] array = new float[this.b * this.a];
            switch (this.d) {
                case 1: {
                    final int c = this.c();
                    for (int i = 0; i < this.b; ++i) {
                        float n = 0.0f;
                        int n2 = 1;
                        for (int j = 0; j < this.a; ++j) {
                            final float n3 = Math.abs((float)this.i[i / n2 % c]) * a2 + a + n;
                            if (this.h != 0) {
                                n = n3;
                            }
                            array[i * this.a + j] = n3;
                            n2 *= c;
                        }
                    }
                    break;
                }
                case 2: {
                    for (int k = 0; k < this.b; ++k) {
                        float n4 = 0.0f;
                        for (int l = 0; l < this.a; ++l) {
                            final float n5 = Math.abs((float)this.i[k * this.a + l]) * a2 + a + n4;
                            if (this.h != 0) {
                                n4 = n5;
                            }
                            array[k * this.a + l] = n5;
                        }
                    }
                    break;
                }
            }
            return array;
        }
        return null;
    }
    
    public static float a(final int n) {
        float n2 = n & 0x1FFFFF;
        final float n3 = n & Integer.MIN_VALUE;
        final float n4 = (n & 0x7FE00000) >>> 21;
        if ((n & Integer.MIN_VALUE) != 0x0) {
            n2 = -n2;
        }
        return ae.a(n2, (int)n4 - 20 - 768);
    }
}
