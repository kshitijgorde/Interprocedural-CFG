// 
// Decompiled by Procyon v0.5.30
// 

public class cm implements e
{
    private final /* synthetic */ ce a;
    private final /* synthetic */ ax b;
    private final /* synthetic */ bc c;
    private final /* synthetic */ cl d;
    private final /* synthetic */ long e;
    private final /* synthetic */ cl f;
    
    public cm(final cl f, final ce a, final ax b, final bc c, final cl d, final long e) {
        this.f = f;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public void produce() {
        while (this.a == this.b.c()) {
            this.c.a(this.b.af(), this.d, this.b.aj().a("URI_PREFIX"), this.e, null);
            final ci j = this.b.j();
            ++j.d;
            try {
                Thread.currentThread();
                Thread.sleep(this.b.aj().d("HTTP_POLL_INTERVAL"));
            }
            catch (Exception ex) {}
        }
    }
}
