// 
// Decompiled by Procyon v0.5.30
// 

public class v
{
    public float[][] a;
    public w b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public long i;
    public long j;
    public u k;
    public int l;
    public int m;
    public int n;
    public int o;
    
    public v(final u u) {
        this.a = new float[0][];
        this.b = new w();
        this.a(u);
    }
    
    public void a(final u k) {
        this.k = k;
        if (k.a != 0) {
            this.b.a();
        }
    }
    
    public int a() {
        if (this.k != null && this.k.a != 0) {
            this.b.b();
        }
        return 0;
    }
    
    public int a(final r r) {
        final s b = this.k.b;
        this.b.a(r.a, r.b, r.c);
        if (this.b.d(1) != 0) {
            return -1;
        }
        final int d = this.b.d(this.k.c);
        if (d == -1) {
            return -1;
        }
        this.g = d;
        this.d = b.p[this.g].a;
        if (this.d != 0) {
            this.c = this.b.d(1);
            this.e = this.b.d(1);
            if (this.e == -1) {
                return -1;
            }
        }
        else {
            this.c = 0;
            this.e = 0;
        }
        this.i = r.f;
        this.j = r.g - 3L;
        this.h = r.e;
        this.f = b.h[this.d];
        if (this.a.length < b.c) {
            this.a = new float[b.c][];
        }
        for (int i = 0; i < b.c; ++i) {
            if (this.a[i] == null || this.a[i].length < this.f) {
                this.a[i] = new float[this.f];
            }
            else {
                for (int j = 0; j < this.f; ++j) {
                    this.a[i][j] = 0.0f;
                }
            }
        }
        return z.a[b.q[b.p[this.g].d]].a(this, this.k.v[this.g]);
    }
}
