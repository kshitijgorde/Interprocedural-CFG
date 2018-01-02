// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.A;

public final class C extends G
{
    private int F;
    private int E;
    private int[] G;
    private int[] H;
    
    public C(final int n) {
        super(n);
        this.F = 0;
        this.E = 0;
        this.G = new int[this.D.length];
        this.H = new int[this.D.length];
        for (int i = 0; i < this.G.length; ++i) {
            this.G[i] = (this.H[i] = -1);
        }
    }
    
    public C() {
        this(20);
    }
    
    private void A(final int f) {
        if (this.F != f) {
            final int n = this.G[f];
            final int e = this.H[f];
            if ((this.G[e] = n) >= 0) {
                this.H[n] = e;
            }
            else {
                this.E = e;
            }
            this.H[f] = -1;
            this.G[f] = this.F;
            this.H[this.F] = f;
            this.F = f;
        }
    }
    
    public synchronized Object A(final Object o) {
        final B value = this.A.get(o);
        if (value != null) {
            final B b = value;
            this.A(b.C);
            return b.A;
        }
        return null;
    }
    
    public final synchronized void A(final Object o, final Object o2) {
        final B value = this.A.get(o);
        if (value != null) {
            final B b = value;
            b.A = o2;
            b.B = o;
            this.A(b.C);
            return;
        }
        if (!this.C()) {
            if (this.C > 0) {
                this.H[this.C] = this.E;
                this.G[this.C] = -1;
                this.A(this.C);
            }
            ++this.C;
        }
        else {
            this.A.remove(this.D[this.E].B);
            this.A(this.E);
        }
        this.D[this.F].A = o2;
        this.D[this.F].B = o;
        this.A.put(o, this.D[this.F]);
    }
}
