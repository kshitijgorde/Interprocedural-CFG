// 
// Decompiled by Procyon v0.5.30
// 

public class d extends o
{
    private o[] D;
    
    public d(final o[] d) {
        this.D = d;
        super.p = d.length;
        super.n = d[0].n;
        super.o = d[0].o;
    }
    
    public void O(final int l, final int m) {
        super.l = l;
        super.m = m;
    }
    
    public void J() {
        this.D[super.q].J();
    }
    
    public l N() {
        final l n = this.D[super.q].N();
        return new l(n.d + super.l, n.e + super.m, n.f);
    }
    
    public boolean P() {
        return this.D[super.q].P();
    }
    
    public void K() {
        ++super.q;
        if (super.q == super.p) {
            super.q = 0;
        }
        super.j = this.D[super.q].j;
    }
    
    public void S() {
        final o[] d = this.D;
        final int q = 0;
        super.q = q;
        super.j = d[q].j;
    }
    
    public void Q(final a a) {
    }
}
