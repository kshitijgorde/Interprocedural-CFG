// 
// Decompiled by Procyon v0.5.30
// 

public abstract class be implements bf, ak
{
    private b5 a;
    
    public be() {
        this(b5.a);
    }
    
    public be(final b5 a) {
        this.a = a;
    }
    
    public boolean a(final du du, final int n, final bb bb) {
        if (ak.a.l()) {
            ak.a.j("CSVResponseHandler.handleReponse " + ak.a.a(du, new Integer(n), bb));
        }
        try {
            return this.a(new bg(du.b(), this.a), n, bb, du);
        }
        catch (Exception ex) {
            if (ak.a.g()) {
                ak.a.b("couldn't handle csv response", ex);
            }
            return false;
        }
    }
    
    public void c(final Exception ex, final int n, final String s) {
        if (ak.a.l()) {
            ak.a.j("CSVResponseHandler.handleError " + ak.a.a(ex, new Integer(n), (n < 0) ? new Integer(n + Integer.MAX_VALUE) : new Integer(n)));
        }
        this.a(ex, n, s);
    }
    
    public void a(final dq dq) {
    }
    
    public abstract void b(final Exception p0, final int p1, final String p2);
    
    public abstract void a(final Exception p0, final int p1, final String p2);
    
    public abstract boolean a(final bg p0, final int p1, final bb p2, final du p3);
    
    public abstract boolean c();
}
