// 
// Decompiled by Procyon v0.5.30
// 

public class f extends b
{
    long k;
    boolean l;
    boolean m;
    int n;
    int o;
    int[] p;
    
    public f(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.m = true;
        this.n = 0;
        this.o = super.c * super.d;
        this.p = new int[this.o];
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        int n2 = super.c * (int)n / super.f;
        int m = 1;
        final int n3 = (int)n;
        final int n4 = super.f / 2;
        int i = 0;
        int l = 0;
        Label_0081: {
            if (c == 0) {
                if (n3 >= n4) {
                    m = 0;
                    n2 = super.c - n2;
                }
                final int n5;
                l = (n5 = (i = (this.m ? 1 : 0)));
                if (c != 0) {
                    break Label_0081;
                }
            }
            if (n3 != n4) {
                this.n = super.c / 2;
            }
            this.m = (m != 0);
            i = (l = (this.l ? 1 : 0));
        }
        if (c == 0) {
            if (l != 0) {
                this.l = false;
                System.arraycopy(super.a, 0, this.p, 0, this.o);
            }
            i = 0;
        }
        int n6 = i;
        while (true) {
            while (true) {
                Label_0338: {
                    if (c == 0) {
                        break Label_0338;
                    }
                    int n9;
                    int n8;
                    final int n7 = n8 = (n9 = m);
                    Label_0335: {
                        final int n16;
                        Label_0267: {
                            Label_0200: {
                                if (c != 0) {
                                    break Label_0200;
                                }
                                if (n7 != 0) {
                                    int n11;
                                    final int n10 = n11 = n6;
                                    int n13;
                                    final int n12 = n13 = this.n;
                                    if (c == 0) {
                                        if (n10 < n12) {
                                            break Label_0335;
                                        }
                                        final int n14;
                                        n11 = (n14 = n6);
                                        final int n15;
                                        n13 = (n15 = super.c - this.n);
                                    }
                                    if (c == 0) {
                                        if (n10 > n12) {
                                            break Label_0335;
                                        }
                                        n11 = n6;
                                        if (c != 0) {
                                            break Label_0267;
                                        }
                                        n13 = n2;
                                    }
                                    if (n11 <= n13) {
                                        break Label_0267;
                                    }
                                    n16 = n6;
                                    if (c != 0) {
                                        break Label_0267;
                                    }
                                    if (n16 < super.c - n2) {
                                        break Label_0335;
                                    }
                                    break Label_0267;
                                }
                                final int n17;
                                n8 = (n17 = (n9 = n6));
                            }
                            int n19;
                            final int n18 = n19 = n2;
                            if (c == 0) {
                                if (n7 < n18) {
                                    break Label_0335;
                                }
                                n9 = (n8 = n6);
                                final int n20;
                                n19 = (n20 = super.c - n2);
                            }
                            if (c == 0) {
                                if (n8 > n18) {
                                    break Label_0335;
                                }
                                n9 = n6;
                                if (c != 0) {
                                    break Label_0267;
                                }
                                n19 = this.n;
                            }
                            if (n9 > n19) {
                                final int n21 = n6;
                                if (c == 0) {
                                    if (n21 < super.c - this.n) {
                                        break Label_0335;
                                    }
                                }
                            }
                        }
                        int n22 = n16;
                        int n23 = 0;
                        while (true) {
                            Label_0326: {
                                if (c == 0) {
                                    break Label_0326;
                                }
                                Label_0314: {
                                    if (m != 0) {
                                        this.p[n22] = super.e;
                                        if (c == 0) {
                                            break Label_0314;
                                        }
                                    }
                                    this.p[n22] = super.b[n22];
                                }
                                n22 += super.c;
                                ++n23;
                            }
                            if (n23 < super.d) {
                                continue;
                            }
                            break;
                        }
                    }
                    ++n6;
                }
                if (n6 < super.c) {
                    continue;
                }
                break;
            }
            this.n = n2;
            if (c == 0) {
                return this.p;
            }
            continue;
        }
    }
}
