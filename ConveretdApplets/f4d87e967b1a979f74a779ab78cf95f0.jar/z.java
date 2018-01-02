// 
// Decompiled by Procyon v0.5.30
// 

public class z extends t
{
    public i p;
    public i d;
    public g a;
    public d n;
    public j v;
    boolean i;
    boolean l;
    boolean b;
    long c;
    float e;
    int f;
    int g;
    int h;
    int j;
    int k;
    int m;
    int o;
    int q;
    int r;
    
    public z() {
        this.p = new i("source", this, 1, false);
        this.d = new i("target", this, 3, false);
        this.a = new g("blend LUT", this, 1, false);
        this.n = new d("scroll speed", this, 1, false);
        this.v = new j("interval", this, 1, false);
        this.i = false;
        this.l = false;
        this.b = true;
        this.c = System.currentTimeMillis();
    }
    
    public final boolean execute() {
        if (this.a.a == null) {
            return false;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.b || (currentTimeMillis - this.c > this.v.p && !this.i)) {
            this.l = true;
            this.i = true;
            this.b = false;
            this.c = currentTimeMillis;
        }
        if (this.i) {
            if (this.l) {
                this.l = false;
                this.e = 0.0f;
                this.f = this.d.p + this.p.p - 1;
            }
            this.g = this.d.p - (int)(this.f * this.e);
            this.j = this.d.d - this.p.d;
            this.h = this.g + this.p.p;
            this.k = this.j + this.p.d;
            this.m = 0;
            this.q = 0;
            this.o = this.p.p;
            this.r = this.p.d;
            if (this.g + this.p.p < 0 || this.h - this.p.p > this.d.p || this.j + this.p.d < 0 || this.k - this.p.d > this.d.d) {
                return true;
            }
            if (this.g < 0) {
                this.m += -this.g;
                this.g = 0;
            }
            if (this.j < 0) {
                this.q += -this.j;
                this.j = 0;
            }
            if (this.h > this.d.p) {
                this.o -= this.h - this.d.p;
                this.h = this.d.p;
            }
            if (this.k > this.d.d) {
                this.r -= this.k - this.d.d;
                this.k = this.d.d;
            }
            int n = this.j * this.d.p + this.g;
            int n2 = this.q * this.p.p + this.m;
            for (int i = this.j; i < this.k; ++i) {
                for (int j = this.g; j < this.h; ++j) {
                    final int n3 = this.d.a[n];
                    final int n4 = this.p.a[n2];
                    this.d.a[n] = ((this.a.a[((n4 >> 16 & 0xFF) << 8) + (n3 >> 16 & 0xFF)] & 0xFF) << 16 | (this.a.a[((n4 >> 8 & 0xFF) << 8) + (n3 >> 8 & 0xFF)] & 0xFF) << 8 | (this.a.a[((n4 & 0xFF) << 8) + (n3 & 0xFF)] & 0xFF));
                    ++n;
                    ++n2;
                }
                n += this.d.p - (this.h - this.g);
                n2 += this.p.p - (this.h - this.g);
            }
            this.e = (currentTimeMillis - this.c) * 0.001f * this.n.p();
            if (this.e >= 1) {
                this.i = false;
                this.c = currentTimeMillis;
            }
        }
        return true;
    }
}
