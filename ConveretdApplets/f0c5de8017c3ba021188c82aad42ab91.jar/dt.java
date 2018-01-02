import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class dt implements e
{
    private final /* synthetic */ dr a;
    private final /* synthetic */ ch b;
    private final /* synthetic */ bb c;
    private final /* synthetic */ aj d;
    private final /* synthetic */ Vector e;
    private final /* synthetic */ j f;
    private final /* synthetic */ ds g;
    
    public dt(final ds g, final dr a, final ch b, final bb c, final aj d, final Vector e, final j f) {
        this.g = g;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public void produce() {
        final l l = new l(1);
        while (l.b() != 0) {
            final int b = this.a.b();
            final long d = this.b.d();
            final int a = this.c.b.a(new el(this, l, b), this.b.b(), "InnerPushProducer(" + this.b.b() + ")");
            if (ak.a.l()) {
                ak.a.j("InputStreamMsgReader:out of produce with retState2: " + j.a(a));
            }
            if (a == 1) {
                this.d.d();
            }
            boolean b2 = false;
            if (this.b.d() != d) {
                b2 = true;
            }
            if (a != 0) {
                if (!b2) {
                    dj.a(this.b, this.c.b, new cu(a), b);
                    l.a(0);
                }
                else {
                    if (!ak.a.i()) {
                        continue;
                    }
                    ak.a.g("skipping error-state of request-produce because of changed request timeout");
                }
            }
        }
    }
}
