import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class v extends l
{
    public static final String[] a;
    public j[] b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public u h;
    public int i;
    public int j;
    public double k;
    public e l;
    public String m;
    public String n;
    public j o;
    public int p;
    public int[] q;
    public int[] r;
    public j s;
    public int[] t;
    public double[] u;
    
    public void b() {
        ++this.j;
        switch (this.c) {
            case 0: {
                boolean b = true;
                for (int i = 0; i < v.a.length; ++i) {
                    if (this.b[i].r) {
                        super.m.b.e("Failed to load '" + v.a[i] + "'");
                        super.m.b.b("loading");
                    }
                    if (!this.b[i].j()) {
                        b = false;
                    }
                    if (this.o != null) {
                        if (this.o.r) {
                            super.m.b.e("Failed to load '" + this.n + "'");
                            super.m.b.a("loading", "cbimage");
                        }
                        if (!this.o.j()) {
                            b = false;
                        }
                    }
                }
                if (b) {
                    this.r = new int[12840];
                    this.l.a(this.r, 0, 0, 107, 120, this.l.a(this.b[2]), 107, 120);
                    this.q = new int[12840];
                    final int[] a = this.l.a(this.b[0]);
                    final int[] a2 = this.l.a(this.b[1]);
                    this.l.a(this.q, 9, 10, 107, 120, a, 88, 59);
                    this.l.a(this.q, 20, 56, 107, 120, a2, 81, 59);
                    this.u = this.l.a(this.r);
                    final double[] array = this.u.clone();
                    int n = 0;
                    do {
                        final double[] array2 = array;
                        final int n2 = n * 3 + 2;
                        array2[n2] *= 2.0;
                    } while (++n < 12840);
                    this.t = this.l.a(array);
                    super.m.b.p();
                    this.c = 1;
                    return;
                }
                break;
            }
            case 1: {
                this.e = super.d / 2;
                this.f = super.d / 2 + 120;
                this.d = super.c;
                this.g = super.d / 2 - 100;
                this.c = 5;
                if (this.o == null) {
                    this.c = 5;
                    return;
                }
                this.p = super.d + this.o.c() / 2;
                this.c = 2;
            }
            case 2: {
                this.p -= 16;
                if (this.p < super.d / 2) {
                    this.p = super.d / 2;
                    this.i = 100;
                    this.c = 3;
                }
                this.j();
            }
            case 3: {
                if (this.i > 0) {
                    --this.i;
                    return;
                }
                this.c = 4;
            }
            case 4: {
                this.p -= 16;
                if (this.p < -this.o.c() / 2) {
                    this.c = 5;
                }
                this.j();
            }
            case 5: {
                this.j();
                this.d -= 10;
                if (this.d < 0) {
                    this.d = 0;
                    this.i = 3;
                    this.c = 6;
                    return;
                }
                break;
            }
            case 6: {
                this.j();
                if (this.i > 0) {
                    --this.i;
                    return;
                }
                this.c = 7;
            }
            case 7: {
                this.j();
                this.k += 0.08;
                if (this.k >= 1.0) {
                    this.k = 0.0;
                    this.c = 8;
                    return;
                }
                break;
            }
            case 8: {
                this.j();
                this.k += 0.05;
                if (this.k >= 1.0) {
                    this.c = 9;
                    return;
                }
                break;
            }
            case 9: {
                this.j();
                this.e = Math.max(this.e - 8, 0);
                this.f = Math.max(this.f - 10, 0);
                if (this.e == 0 && this.f == 0) {
                    this.c = 10;
                    return;
                }
                break;
            }
            case 10: {
                this.j();
                this.g -= 4;
                if (this.g < 0) {
                    this.g = 0;
                    this.i = 60;
                    this.c = 11;
                    return;
                }
                break;
            }
            case 11: {
                if (this.j % 5 == 0) {
                    this.j();
                }
                if (this.i > 0) {
                    --this.i;
                    return;
                }
                this.c = 12;
            }
            default: {
                if (this.j % 5 == 0) {
                    this.j();
                    return;
                }
                break;
            }
        }
    }
    
    public boolean k() {
        return this.c == 12;
    }
    
    public void a(final n n) {
        if (this.c == 0) {
            n.a(Color.black);
            n.b(0, 0, super.c, super.d);
            return;
        }
        if (this.c == 6) {
            n.a(Color.white);
            n.b(0, 0, super.c, super.d);
            return;
        }
        n.a(Color.black);
        n.b(0, 0, super.c, super.d);
        final int n2 = super.c / 2;
        final int n3 = super.d / 2;
        if (this.c == 2 || this.c == 3 || this.c == 4) {
            n.a(this.o, n2 - this.o.h() / 2, this.p - this.o.c() / 2);
        }
        if (this.c == 7 || this.c == 8) {
            int[] array;
            if (this.c == 7) {
                array = this.l.a(this.q, this.t, 107, 120, this.k);
            }
            else {
                final double[] array2 = this.u.clone();
                int n4 = 0;
                do {
                    final double[] array3 = array2;
                    final int n5 = n4 * 3 + 2;
                    array3[n5] *= 2.0 - this.k;
                } while (++n4 < 12840);
                array = this.l.a(array2);
            }
            System.arraycopy(array, 0, this.s.b(), 0, 12840);
            n.a(this.s, n2 - this.d - 44 - 8 - 3 - 9, n3 - 32 - 53 - 10);
        }
        else if (this.c >= 7) {
            n.a(this.b[2], n2 - this.d - 44 - 8 - 3 - 9, n3 - 32 - 53 - 10);
        }
        else if (this.c >= 5) {
            n.a(this.b[0], n2 - this.d - 44 - 8 - 3, n3 - 32 - 53);
            n.a(this.b[1], n2 + this.d - 40 - 8 + 3, n3 - 32 - 7);
        }
        if (this.c >= 9) {
            n.a(this.b[3], n2 - 8 + 50, n3 - 32 - 52 - this.e);
            n.a(this.b[4], n2 - 56 - 8, n3 - 32 + 58 + this.f);
        }
        if (this.c >= 10) {
            final int n6 = n2 - this.b[5].h() / 2;
            final int n7 = n3 - 32 + 120 + this.g;
            n.a(this.b[5], n6, n7);
            final n n8 = new n(n);
            n8.a(n6, n7, (int)(this.l.l() * this.b[5].h() / 100.0), this.b[5].c());
            n8.a(this.b[6], n6, n7);
            n.f = this.h;
            n.a(new Color(41, 239, 255));
            n.a(this.m, n2 - n.f.a(this.m) / 2, n3 - 32 + 118 + this.g);
        }
    }
    
    public v(final k k, final String s) {
        super(k);
        this.c = 0;
        this.h = k.b.b("SansSerif", 1, 14);
        (this.s = new j()).a(107, 120);
        this.l = super.m.b.d;
        this.m = "Now Loading " + s + "... Please wait...";
    }
    
    public void l() {
        this.b = new j[v.a.length];
        for (int i = 0; i < v.a.length; ++i) {
            this.b[i] = this.l.a("images/" + v.a[i]);
        }
        this.n = super.m.b.getParameter("cbimage");
        if (this.n != null) {
            this.o = this.l.a("images/" + this.n);
        }
        this.c = 0;
    }
    
    public boolean m() {
        return this.c != 0;
    }
    
    static {
        a = new String[] { "zz_pop2.gif", "zz_cap2.gif", "zz_logo3.gif", "zz_games1.gif", "zz_url1.gif", "zz_loaderbar2.gif", "zz_loaderbar1.gif" };
    }
}
