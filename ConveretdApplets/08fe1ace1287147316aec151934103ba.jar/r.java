// 
// Decompiled by Procyon v0.5.30
// 

class r extends f
{
    public r(final int n, final int n2, final int n3, final int n4, final int n5) {
        super(n, n2, n3, n4, n5);
        super.D = true;
    }
    
    public boolean _(final g g) {
        if (super.E.intersects(g.E)) {
            if (g.F < 0) {
                this._(g);
            }
            return true;
        }
        return false;
    }
}
