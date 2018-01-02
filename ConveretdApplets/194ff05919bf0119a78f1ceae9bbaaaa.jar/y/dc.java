// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class dc extends bx
{
    private bj a;
    private bj b;
    private bj c;
    private aj a;
    private es a;
    private es b;
    private es c;
    private int t;
    
    dc(final aj a, final String s, final int t) {
        super(a.b());
        this.a = new bj(a.a().a(1716519222));
        this.b = new bj(a.a().a(1716519215));
        this.c = new bj(a.a().a(1716519217));
        this.a = a;
        this.t = t;
        this.a(this.a = new es(a.a().a(1716522075) + s + a.a().a(1716519219)), 2, 1, 0, 0);
        this.a(new es(a.a().a(1716519216), 2), 12, 2, 1, 1, 1, 0, 1);
        this.a(this.c = new es(a.a().a(1716519212) + s + a.a().a(1716519209)), 18, 2, 2, 1, 1, 1, 1);
        this.a(new es(a.a().a(1716519214), 2), 12, 2, 1, 1, 1, 0, 2);
        this.a(new es(a.a().a(1716519220)), 18, 1, 2, 1, 1, 1, 2);
        this.a(new es(a.a().a(1716519223), 2), 12, 2, 1, 1, 1, 0, 4);
        this.b = new es(a.a().a(1716519211) + s + a.a().a(1716519210));
        new es();
        this.a(this.b, 18, 1, 2, 1, 1, 1, 4);
        final av av = new av(1);
        this.a(av, 3, 1, 0, 5);
        av.a(this.a, 0, 0, 2);
        av.a(this.b, 1, 0, 2);
        av.a(this.c, 2, 0, 2);
        this.a();
    }
    
    public final boolean a(final Event event, final Object o) {
        if (event.target == this.a) {
            this.a.d(this.t);
        }
        else if (event.target == this.b) {
            this.a.a((byte)1, (byte)0);
        }
        else if (event.target == null) {
            this.a.a((byte)1, (byte)1);
        }
        else if (event.target != this.c) {
            return false;
        }
        this.l();
        return true;
    }
}
