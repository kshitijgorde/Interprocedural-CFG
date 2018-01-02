// 
// Decompiled by Procyon v0.5.30
// 

public class e extends o
{
    private o E;
    private o F;
    private l[] G;
    private l[] H;
    private boolean[] I;
    private int[] J;
    private int[] K;
    
    public e(final o e, final o f, final int p3) {
        this.E = e;
        this.F = f;
        super.p = p3;
    }
    
    public void K() {
        ++super.q;
        for (int i = 0; i < super.j; ++i) {
            final l l = this.G[this.J[i]];
            final l j = this.H[this.K[i]];
            super.k[i].d = (l.d * (super.p - super.q) + j.d * super.q) / super.p;
            super.k[i].e = (l.e * (super.p - super.q) + j.e * super.q) / super.p;
            super.k[i].f = o.U(l.f, j.f, super.q, super.p);
        }
    }
    
    public void S() {
        super.q = 0;
        final int j = this.E.j;
        final int i = this.F.j;
        this.G = new l[j];
        this.E.J();
        for (int k = 0; k < j; ++k) {
            this.G[k] = this.E.N();
        }
        this.H = new l[i];
        this.F.J();
        for (int l = 0; l < i; ++l) {
            this.H[l] = this.F.N();
        }
        this.I = new boolean[i];
        for (int n = 0; n < i; ++n) {
            this.I[n] = false;
        }
        this.J = new int[j + i - 1];
        this.K = new int[j + i - 1];
        for (int n2 = 0; n2 < j; ++n2) {
            this.J[n2] = n2;
            this.I[this.K[n2] = (int)(Math.random() * (i - 1))] = true;
        }
        super.j = j;
        for (int n3 = 0; n3 < i; ++n3) {
            if (!this.I[n3]) {
                this.K[super.j] = n3;
                this.J[super.j++] = (int)(Math.random() * (j - 1));
            }
        }
        super.k = new l[super.j];
        for (int n4 = 0; n4 < super.j; ++n4) {
            final l m = this.G[this.J[n4]];
            super.k[n4] = new l(m.d, m.e, m.f);
        }
    }
}
