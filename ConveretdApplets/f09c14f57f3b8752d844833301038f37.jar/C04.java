import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class C04 extends C47
{
    int h;
    double i;
    double l;
    C20 m;
    public static int n;
    double o;
    int p;
    boolean r;
    boolean s;
    int[] v;
    int w;
    int A;
    int B;
    int C;
    String D;
    int E;
    int F;
    boolean H;
    int I;
    int J;
    int L;
    double M;
    double N;
    int O;
    String Q;
    int R;
    double S;
    double T;
    String W;
    int X;
    boolean Z;
    boolean ba;
    int bc;
    int[] bd;
    
    public C04(final int[] array, final int[] array2) {
        super(array, array2);
        this.T = 1.2;
        this.ba = true;
        this.v = new int[4];
        this.bd = new int[4];
        this.r = false;
        this.R = 0;
        this.F = 0;
        this.Z = false;
        if (array.length > C47.g.length) {
            synchronized (this.getClass()) {
                C47.g = new int[(int)(array.length * 1.5)];
                C47.h = new int[(int)(array.length * 1.5)];
            }
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5, final double n6, final double n7, final double n8, final double n9) {
        this.f(new int[] { n4 + (int)(n * n7), n5 + (int)(n * n6), n4 + (int)(n * n9), n5 + (int)(n * this.N), n2 + (int)(n * n9), n3 + (int)(n * this.N), n2 + (int)(n * n7), n3 + (int)(n * n6), 0, 0 }, 8);
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        synchronized (this.getClass()) {
            for (int n8 = 0; n8 < super.d.length && n8 < super.f.length; ++n8) {
                C47.g[n8] = (short)((super.d[n8] - n) * n2 + n3);
                C47.h[n8] = (short)(n7 - ((super.f[n8] - n4) * n5 + n6));
            }
            this.Q = System.getProperty("java.version");
            final Color k = c15.k();
            if (this instanceof C28 && this.e() != null) {
                c15.n(true);
                this.D = this.e().c();
                this.W = this.e().h();
                if (this.e().q && c15.bi) {
                    if (Double.valueOf(this.Q.trim().substring(0, 3)) >= this.T) {
                        c15.B(new Color(255, 255, 0, 100));
                        c15.v(C47.g, C47.h, super.d.length);
                    }
                    else {
                        c15.B(new Color(255, 255, 0));
                    }
                }
                else if (this.e().o && c15.P) {
                    if (Double.valueOf(this.Q.trim().substring(0, 3)) >= this.T) {
                        c15.B(c15.T);
                        c15.v(C47.g, C47.h, super.d.length);
                    }
                    else {
                        c15.B(c15.T);
                    }
                }
                else if (!this.D.equals("") && this.D != null && c15.Q) {
                    if (Double.valueOf(this.Q.trim().substring(0, 3)) >= this.T) {
                        c15.B(new Color(255, 0, 0, 100));
                        c15.v(C47.g, C47.h, super.d.length);
                    }
                    else {
                        c15.B(new Color(255, 0, 0));
                    }
                }
                else if (!this.W.equals("") && this.W != null && (this.D.equals("") || this.D == null) && c15.bt) {
                    if (Double.valueOf(this.Q.trim().substring(0, 3)) >= this.T) {
                        c15.B(new Color(0, 0, 255, 100));
                        c15.v(C47.g, C47.h, super.d.length);
                    }
                    else {
                        c15.B(new Color(0, 0, 255));
                    }
                }
            }
            if (super.D == 0 && super.A == null) {
                c15.c(C47.g, C47.h, super.d.length);
            }
            else {
                c15.F(C47.g, C47.h, super.d.length, super.A, (int)(super.D * n2));
            }
            c15.n(false);
            c15.B(k);
        }
    }
    
    public C04(final int n, final int n2, final int n3, final int n4) {
        super(n, n2, n3, n4);
        this.T = 1.2;
        this.ba = true;
        this.v = new int[4];
        this.bd = new int[4];
        this.r = false;
        this.R = 0;
        this.F = 0;
        this.Z = false;
    }
    
    public Object clone() {
        return new C04(super.d, super.f);
    }
    
    public int e(final int l, final int a, final int n, final int n2, int[] array, final int n3, int n4, final boolean b) {
        this.B = n - l;
        this.p = n2 - a;
        this.L = l;
        this.A = a;
        this.l = 1.0;
        this.M = 0.0;
        this.S = 0.0;
        this.o = 1.0;
        this.i = -1.0;
        this.N = 0.0;
        if (this.B != 0) {
            final double atan = Math.atan(this.p / this.B);
            this.M = Math.cos(atan);
            this.l = Math.sin(atan);
            this.S = Math.sin(atan + 1.5707963267948966);
            this.o = Math.cos(atan + 1.5707963267948966);
            this.N = Math.sin(atan - 1.5707963267948966);
            this.i = Math.cos(atan - 1.5707963267948966);
        }
        else if (n2 < a) {
            this.l = -1.0;
        }
        if (this.B > 0) {
            this.X = 1;
        }
        else {
            this.X = -1;
        }
        this.O = 1;
        this.J = l;
        this.w = a;
        this.E = 0;
        this.C = (int)Math.sqrt(Math.pow(this.B, 2.0) + Math.pow(this.p, 2.0));
        this.I = n3 / 2;
        if (array == null) {
            array = new int[] { this.C, 0 };
        }
        if (this.bc > 0 && !this.s) {
            this.L = l;
            this.A = a;
            this.v[3] = this.L + (int)(this.I * this.o);
            this.bd[3] = this.A + (int)(this.I * this.S);
            this.v[2] = this.L + (int)(this.I * this.i);
            this.bd[2] = this.A + (int)(this.I * this.N);
            System.out.println("g.fillPolygon(patternPolyX,patternPolyY,patternPolyX.length);");
        }
        if (this.bc > 0 && this.s) {
            this.E += this.bc;
            if (this.E > this.C) {
                this.E = this.C;
                this.bc -= this.E;
                return n4;
            }
            this.bc -= this.E;
        }
        while (this.E < this.C) {
            if (this.bc > 0 && !this.s) {
                this.E += this.bc;
                this.bc -= this.E;
            }
            else {
                this.E += array[n4];
                if (++n4 >= array.length) {
                    n4 = 0;
                }
                if (array[n4] == 0) {
                    this.E = this.C;
                }
            }
            if (this.E > this.C) {
                this.bc = this.E - this.C;
                this.s = false;
                this.E = this.C;
            }
            this.L = l + (int)(this.X * this.E * this.M);
            if (this.B >= 0) {
                this.A = a + (int)(this.O * this.E * this.l);
            }
            else {
                this.A = a - (int)(this.E * this.l);
            }
            this.a(this.I, this.J, this.w, this.L, this.A, this.S, this.o, this.N, this.i);
            if (this.E < this.C) {
                this.E += array[n4];
                if (this.E > this.C) {
                    this.bc = this.E - this.C;
                    this.s = true;
                    this.E = this.C;
                }
                if (++n4 >= array.length) {
                    n4 = 0;
                }
                this.J = l + (int)(this.E * this.M);
                this.w = a + (int)(this.E * this.l);
                this.J = l + (int)(this.X * this.E * this.M);
                if (this.B >= 0) {
                    this.w = a + (int)(this.O * this.E * this.l);
                }
                else {
                    this.w = a - (int)(this.E * this.l);
                }
            }
        }
        return n4;
    }
    
    static {
        C04.n = 0;
    }
    
    public void f(final int[] array, final int n) {
        this.m.Polygon(array, n);
    }
    
    public void a(final C20 m, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        this.m = m;
        final int length = super.f.length;
        final int[] array = new int[length * 3];
        for (int n8 = 0; n8 < super.d.length && n8 < super.f.length; ++n8) {
            final short n9 = (short)((super.d[n8] - n) * n2 + n3);
            final short n10 = (short)(n7 - ((super.f[n8] - n4) * n5 + n6));
            array[n8 * 2] = n9;
            array[n8 * 2 + 1] = n10;
        }
        if (super.D == 0 && super.A == null) {
            m.Polyline(array, length);
        }
        else {
            m.Polyline(array, length);
        }
    }
}
