// 
// Decompiled by Procyon v0.5.30
// 

class l extends f
{
    protected static int db;
    protected static String eb;
    protected static String fb;
    protected static int gb;
    protected static int hb;
    public static int _b;
    
    public l(final int n, final int n2, final int n3, final int n4, final int n5) {
        super(n, n2, n3, n4, n5);
        super.D = true;
        l.db = 1;
    }
    
    public boolean _(final g g) {
        if (super.E.intersects(g.E)) {
            ++l.db;
            if (l.db > 3) {
                l.db = 1;
            }
            this._(g);
            return true;
        }
        return false;
    }
}
