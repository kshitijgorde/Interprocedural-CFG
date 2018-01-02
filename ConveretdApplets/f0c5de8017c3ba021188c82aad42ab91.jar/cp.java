// 
// Decompiled by Procyon v0.5.30
// 

public class cp extends be implements ay
{
    public String a;
    public ce b;
    public ax c;
    
    public cp() {
    }
    
    public cp(final ax c, final bc bc, final long n, final String s, final x x) {
        this.a = c.n();
        this.b = c.c();
        this.c = c;
        bc.a(x, this, s, n, null);
    }
    
    public boolean a(final bg bg, final int n, final bb bb, final du du) {
        return false;
    }
    
    public void a(final Exception ex, final int n, final String s) {
        if (ay.a.l()) {
            ay.a.h(this.c.as() + " handleCSVError in ClientResponseHandler: received exception " + ex + " with index " + n, ex);
        }
        if (ex instanceof ct) {
            switch (((ct)ex).c()) {
                case 403: {
                    if (ay.a.g()) {
                        ay.a.d(this.c.as() + " received http-error-code " + ((ct)ex).c() + ":" + ((ct)ex).d() + "; shutting down");
                    }
                    this.c.a(false);
                    break;
                }
                default: {
                    if (this.a()) {
                        if (ay.a.g()) {
                            ay.a.d(this.c.as() + " received http-error-code " + ((ct)ex).c() + "; try to reconnect");
                        }
                        this.c.a(ex, ex.toString(), this.b);
                        break;
                    }
                    if (ay.a.g()) {
                        ay.a.d(this.c.as() + " received http-error-code " + ((ct)ex).c() + "; skipping message, no comErrorOccured is called!");
                        break;
                    }
                    break;
                }
            }
        }
        else if (this.a()) {
            if (ay.a.g()) {
                ay.a.d(this.c.as() + " couldn't parse http-header in handleCSVError; try to reconnect");
            }
            this.c.a(ex, ex.toString(), this.b);
        }
        else if (ay.a.g()) {
            ay.a.d(this.c.as() + " couldn't parse (error-)http-header or skipping errors!");
        }
    }
    
    public boolean c() {
        return false;
    }
    
    public boolean a() {
        return true;
    }
    
    public void b(final Exception ex, final int n, final String s) {
    }
}
