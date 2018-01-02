// 
// Decompiled by Procyon v0.5.30
// 

public class h extends k
{
    public int p;
    public g[] p;
    public int d;
    public int[] p;
    public j[][] p;
    public boolean[] p;
    public boolean[] d;
    public dv p;
    
    public final void l() {
        this.p = 0;
    }
    
    public final void p(final g g) {
        if (this.p < 100) {
            this.p[this.p] = g;
            ++this.p;
        }
        else {
            System.out.println("err:pushVectOrigPolyG");
        }
    }
    
    public final void d() {
        for (int i = 0; i < this.p - 1; ++i) {
            for (int j = i + 1; j < this.p; ++j) {
                this.p[i][j].p();
            }
        }
    }
    
    public final void a() {
        for (int i = 0; i < this.p; ++i) {
            boolean b = true;
            for (int j = 0; j < i; ++j) {
                if (this.p[j][i].p) {
                    b = false;
                    break;
                }
            }
            this.d[i] = b;
        }
    }
    
    public final void n() {
        final int n = this.p - 1;
        for (int i = 0; i < this.p; ++i) {
            this.p[this.p.p[n - i]].c();
        }
    }
    
    public final void v() {
        int p = 0;
        for (int i = 0; i < this.p; ++i) {
            if (this.p[i].p) {
                if (i != p) {
                    this.p[p] = this.p[i];
                }
                ++p;
            }
            else {
                ++i;
            }
        }
        this.p = p;
    }
    
    public final void i() {
        final g g = new g(super.p);
        final z z = new z();
        final z z2 = new z();
        this.v();
        this.d();
        for (int i = 0; i < this.p - 1; ++i) {
            boolean b = true;
            for (int j = i + 1; j < this.p; ++j) {
                g.l();
                if (g.a(this.p[i], this.p[j])) {
                    this.p[i][j].p = true;
                    b = false;
                    this.p(z, z2, g.v, this.p[i], this.p[j]);
                    if (z.a > z2.a) {
                        this.p[i][j].d = true;
                    }
                    else {
                        this.p[i][j].d = false;
                    }
                }
                else {
                    this.p[i][j].p = false;
                }
            }
            this.p[i] = b;
        }
    }
    
    public final boolean d(final int n, final int n2) {
        for (int i = n - 1; i > -1; --i) {
            if (!this.p[i][n2].d()) {
                return false;
            }
        }
        return true;
    }
    
    public final boolean p(final int n, final int n2) {
        for (int i = n2 + 1; i < this.p; ++i) {
            if (!this.p[n][i].p()) {
                return false;
            }
        }
        return true;
    }
    
    public final void p(final int n, final int n2) {
        for (int i = n - 1; i > -1; --i) {
            this.p[i][n2].a = true;
        }
    }
    
    public final void d(final int n, final int n2) {
        for (int i = n2 + 1; i < this.p; ++i) {
            this.p[n][i].a = true;
        }
    }
    
    public final void p() {
        this.p.p(this.p);
        this.a();
        while (!this.p.p()) {
            boolean b = false;
            for (int i = 0; i <= this.p.d; ++i) {
                final int n = this.p.p[i];
                if (this.p[n] && this.d[n]) {
                    this.p.d(i);
                    b = true;
                }
                else if (this.d(n, n) && this.p(n, n)) {
                    this.p.d(i);
                    this.p(n, n);
                    this.d(n, n);
                    b = true;
                }
            }
            if (!b && !this.p.p()) {
                this.p(this.p.d, this.p.d);
                this.d(this.p.d, this.p.d);
                final dv p = this.p;
                --p.d;
            }
        }
    }
    
    public h(final c p) {
        this.p = 0;
        this.p = new g[100];
        this.d = 0;
        this.p = new int[100];
        this.p = new j[100][100];
        this.p = new boolean[100];
        this.d = new boolean[100];
        this.p = new dv(100);
        super.p = p;
        for (int i = 0; i < 99; ++i) {
            for (int j = i + 1; j < 100; ++j) {
                this.p[i][j] = new j();
            }
        }
    }
}
