// 
// Decompiled by Procyon v0.5.30
// 

public class g extends h
{
    private a Q;
    private a R;
    
    public g(final o o, final int n, final int n2, final int n3, final int n4, final a q, final a r, final int n5) {
        super(o, n, n2, n3, n4, n5);
        this.Q = q;
        this.R = r;
    }
    
    public l N() {
        final l n = super.W.N();
        return new l(n.d + super.l, n.e + super.m, o.U(this.Q.A(n.d, n.e), this.R.A(n.d, n.e), super.q, super.p));
    }
}
