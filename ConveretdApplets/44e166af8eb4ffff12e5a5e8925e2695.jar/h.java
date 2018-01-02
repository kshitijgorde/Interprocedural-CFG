// 
// Decompiled by Procyon v0.5.30
// 

public class h extends o
{
    protected int S;
    protected int T;
    protected int U;
    protected int V;
    protected o W;
    
    public h(final o w, final int s, final int t, final int u, final int v, final int p6) {
        this.W = w;
        this.S = s;
        this.T = t;
        this.U = u;
        this.V = v;
        super.p = p6;
    }
    
    public void J() {
        this.W.J();
    }
    
    public l N() {
        final l n = this.W.N();
        return new l(n.d + super.l, n.e + super.m, n.f);
    }
    
    public boolean P() {
        return this.W.P();
    }
    
    public void K() {
        ++super.q;
        super.l = (this.S * (super.p - super.q) + this.U * super.q) / super.p;
        super.m = (this.T * (super.p - super.q) + this.V * super.q) / super.p;
        this.W.K();
        super.j = this.W.j;
    }
    
    public void S() {
        super.q = 0;
        super.l = this.S;
        super.m = this.T;
        this.W.S();
        super.j = this.W.j;
    }
    
    public o M(final int n, final int n2) {
        return this.W.M(n - super.l, n2 - super.m);
    }
}
