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
        boolean m = true;
        if ((int)n >= super.f / 2) {
            m = false;
            n2 = super.c - n2;
        }
        if (this.m != m) {
            this.n = super.c / 2;
        }
        this.m = m;
        if (this.l) {
            this.l = false;
            System.arraycopy(super.a, 0, this.p, 0, this.o);
        }
        int n3 = 0;
        while (true) {
            while (true) {
                Label_0278: {
                    if (c == 0) {
                        break Label_0278;
                    }
                    Label_0275: {
                        Label_0207: {
                            if (m) {
                                if (n3 < this.n) {
                                    break Label_0275;
                                }
                                if (n3 > super.c - this.n) {
                                    break Label_0275;
                                }
                                if (n3 > n2 && n3 < super.c - n2) {
                                    break Label_0275;
                                }
                                break Label_0207;
                            }
                            if (n3 < n2) {
                                break Label_0275;
                            }
                            if (n3 > super.c - n2) {
                                break Label_0275;
                            }
                            if (n3 > this.n && n3 < super.c - this.n) {
                                break Label_0275;
                            }
                        }
                        int n4 = n3;
                        int n5 = 0;
                        while (true) {
                            Label_0266: {
                                if (c == 0) {
                                    break Label_0266;
                                }
                                Label_0254: {
                                    if (m) {
                                        this.p[n4] = super.e;
                                        if (c == 0) {
                                            break Label_0254;
                                        }
                                    }
                                    this.p[n4] = super.b[n4];
                                }
                                n4 += super.c;
                                ++n5;
                            }
                            if (n5 < super.d) {
                                continue;
                            }
                            break;
                        }
                    }
                    ++n3;
                }
                if (n3 < super.c) {
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
