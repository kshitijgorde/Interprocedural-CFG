import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class dn
{
    public int p;
    public boolean p;
    public boolean d;
    public int d;
    public int[] p;
    public Color[] p;
    public int[] d;
    public int a;
    public int n;
    public int v;
    public int i;
    public int l;
    public int b;
    public int c;
    
    public final void n() {
        this.i = this.v - 1;
        this.l = this.n - 1;
        this.b = this.n / 2;
        this.c = this.v / 2;
    }
    
    public final void p(final int n, final int n2) {
        final int n3 = n * this.n + n2;
        final int n4 = (this.i - n) * this.n + this.l - n2;
        final int n5 = this.d[n3];
        this.d[n3] = this.d[n4];
        this.d[n4] = n5;
    }
    
    public final void a() {
        this.n();
        for (int i = 0; i < this.v; ++i) {
            for (int j = 0; j < this.b; ++j) {
                this.p(i, j);
            }
        }
        if (this.n - 2 * this.b == 1) {
            for (int k = 0; k < this.c; ++k) {
                this.p(k, this.b + 1);
            }
        }
    }
    
    public final void i() {
        this.d = new int[this.a + 1];
    }
    
    public final boolean d(final int n) {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean p(final int n) {
        if (this.d < 16) {
            this.p[this.d] = n;
            ++this.d;
            return true;
        }
        return this.d = false;
    }
    
    public final boolean p() {
        for (int i = 0; i < this.a; ++i) {
            if (!this.d(this.d[i]) && !this.p(this.d[i])) {
                return false;
            }
        }
        return true;
    }
    
    public final int p(final int n) {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] == n) {
                return i;
            }
        }
        return 0;
    }
    
    public final void v() {
        for (int i = 0; i < this.a; ++i) {
            this.d[i] = this.p(this.d[i]);
        }
    }
    
    public final void p() {
        for (int i = 0; i < this.d; ++i) {
            this.p[i] = new Color(this.p[i]);
        }
    }
    
    public final void d() {
        if (this.p()) {
            this.p = true;
            this.v();
            this.p();
        }
        else {
            this.p = true;
            this.d = false;
        }
    }
    
    public dn(final int p) {
        this.p = false;
        this.d = true;
        this.d = 0;
        this.p = new int[16];
        this.p = new Color[16];
        this.a = -1;
        this.n = -1;
        this.v = -1;
        this.i = 0;
        this.l = 0;
        this.b = 0;
        this.c = 0;
        this.p = p;
    }
}
