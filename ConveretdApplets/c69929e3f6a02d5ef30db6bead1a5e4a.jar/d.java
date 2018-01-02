// 
// Decompiled by Procyon v0.5.30
// 

public class d extends b
{
    long k;
    boolean l;
    int m;
    int n;
    int o;
    int[] p;
    int q;
    int r;
    int s;
    int t;
    int u;
    int v;
    
    public d(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.o = super.c * super.d;
        this.p = new int[this.o];
        this.t = super.c;
        this.u = super.d;
        this.v = this.t;
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        this.r = this.t / 2;
        this.s = this.u / 2;
        final short[] array = { 0, 51, 106, 171, 256, 383, 618, 1287, 9999 };
        if (this.l) {
            this.q = 0;
            this.l = false;
            System.arraycopy(super.a, 0, this.p, 0, this.o);
        }
        int n2 = this.s - 1;
        short n3 = -1;
        int r = this.r;
        int n4 = 1;
        boolean b = true;
        final int n5 = (int)n * 4 / super.f;
        Label_0225: {
            Label_0199: {
                switch (n5) {
                    case 0: {
                        if (c != 0) {
                            break Label_0199;
                        }
                        break Label_0225;
                    }
                    case 1: {
                        n3 = 1;
                        b = false;
                        ++n2;
                        if (c != 0) {
                            break Label_0199;
                        }
                        break Label_0225;
                    }
                    case 2: {
                        n4 = -1;
                        n3 = 1;
                        ++n2;
                        --r;
                        if (c != 0) {
                            break;
                        }
                        break Label_0225;
                    }
                }
            }
            b = false;
            n4 = -1;
            --r;
        }
        int n6 = (int)n * 16384 * 4 / super.f & 0x3FFF;
        if (!b) {
            n6 = 16383 - n6;
        }
        final int n7 = n6 / 2048;
        final int n8 = n6 - n7 * 2048;
        final short n9 = (short)((array[n7] * (2048 - n8) + array[n7 + 1] * n8) / 2048);
        short n10 = 0;
        while (true) {
            while (true) {
                Label_0466: {
                    if (c == 0) {
                        break Label_0466;
                    }
                    final int n11 = this.v * (n2 + n10 * n3);
                    final int n13;
                    final int q;
                    int n12 = n13 + q;
                    int r2 = 0;
                    int n14 = this.r;
                    Label_0420: {
                        if (b) {
                            n14 = n10 * n9 / 256;
                            if (n14 <= this.r) {
                                break Label_0420;
                            }
                            n14 = this.r;
                            if (c == 0) {
                                break Label_0420;
                            }
                        }
                        r2 = n10 * n9 / 256;
                        if (r2 > this.r) {
                            r2 = this.r;
                        }
                        n12 += r2 * n4;
                    }
                    int n15 = r2;
                    while (true) {
                        Label_0457: {
                            if (c == 0) {
                                break Label_0457;
                            }
                            this.p[n12] = super.b[n12++];
                            if (n4 == -1) {
                                n12 -= 2;
                            }
                            ++n15;
                        }
                        if (n15 < n14) {
                            continue;
                        }
                        break;
                    }
                    ++n10;
                }
                if (n10 < this.s) {
                    continue;
                }
                break;
            }
            final int n13 = n5;
            final int q = this.q;
            if (c == 0) {
                if (n13 > q) {
                    int n16 = 0;
                    int n17 = 0;
                    Label_0552: {
                        switch (this.q) {
                            case 0: {
                                n16 = this.r;
                                if (c != 0) {
                                    break Label_0552;
                                }
                                break;
                            }
                            case 1: {
                                n16 = this.r;
                                n17 = this.s;
                                if (c != 0) {
                                    break Label_0552;
                                }
                                break;
                            }
                            case 2: {
                                n17 = this.s;
                                break;
                            }
                        }
                    }
                    int n18 = 0;
                    while (true) {
                        Label_0606: {
                            if (c == 0) {
                                break Label_0606;
                            }
                            final int n19 = n16 + (n17 + n18) * this.t;
                            System.arraycopy(super.b, n19, this.p, n19, this.r);
                            ++n18;
                        }
                        if (n18 < this.s) {
                            continue;
                        }
                        break;
                    }
                    ++this.q;
                }
                return this.p;
            }
            continue;
        }
    }
}
