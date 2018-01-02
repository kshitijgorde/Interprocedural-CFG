// 
// Decompiled by Procyon v0.5.30
// 

public class cf extends cg
{
    private dq a;
    
    public cf(final bc bc, final ax ax, final long n) {
        super(ax);
        this.a = null;
        final ci j = ax.j();
        ++j.a;
        bc.a(this.a(ax.aj().a("NAME_HTTP_AE") + "=push"), this, ax.aj().a("URI_PREFIX"), n, null);
    }
    
    public void a(final dq a) {
        this.a = a;
    }
    
    public void a() {
        if (this.a != null) {
            this.a.a();
        }
    }
}
