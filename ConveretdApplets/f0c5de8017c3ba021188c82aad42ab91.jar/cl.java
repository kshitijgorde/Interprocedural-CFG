// 
// Decompiled by Procyon v0.5.30
// 

public class cl extends cg
{
    public cl(final bc bc, final ax ax, final long n) {
        super(ax);
        final ci j = ax.j();
        ++j.a;
        bc.a(this.a(ax.aj().a("NAME_HTTP_AE") + "=http"), this, ax.aj().a("URI_PREFIX"), n, null);
        if (super.d) {
            this.a(bc, ax, n, this);
        }
    }
    
    public void a(final bc bc, final ax ax, final long n, final cl cl) {
        final int a = ax.aq().a(new cm(this, super.c, ax, bc, cl, n));
        if (a != 0) {
            ax.a((Exception)null, "can not spawn http update getter:" + a);
        }
    }
}
