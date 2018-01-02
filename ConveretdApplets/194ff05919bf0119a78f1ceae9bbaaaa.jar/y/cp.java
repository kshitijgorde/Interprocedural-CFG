// 
// Decompiled by Procyon v0.5.30
// 

package y;

public final class cp extends av
{
    public ed a;
    private es a;
    
    public cp(final int n, final boolean b, final ex ex) {
        super((byte)0);
        this.a = new ed(ex, !b, Math.max(13, 13));
        final fc a = ex.a(n);
        this.a = new es();
        this.a(a, 10, 2, 0, 2, 1, 1, 1);
        this.a(this.a, 4, 1, 0, (n == 0) ? 0 : 2);
        this.a(this.a, 10, 2, 2, b ? 1 : 4, 1, b ? 3 : 0, b ? 1 : 0);
        if (b) {
            this.a(new u(), 10, 2, 2, 1, 1, 0, 1);
        }
    }
}
