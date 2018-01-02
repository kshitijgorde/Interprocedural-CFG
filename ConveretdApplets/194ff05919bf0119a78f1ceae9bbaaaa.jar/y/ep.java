// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.util.Date;

public final class ep extends em
{
    private af a;
    private ff a;
    private an a;
    private bj a;
    private bj b;
    private bj c;
    private bj d;
    private dm a;
    private String a;
    
    ep(final dm a, final String a2, final boolean b) {
        super(a.a(1716519045) + a2 + a.a(1716519046) + new Date(System.currentTimeMillis()), a);
        this.a = new ff(320);
        this.a = new bj(a.a(1716519044));
        this.b = new bj(a.a(1716519047));
        this.c = new bj(a.a(1716519048));
        this.d = new bj(a.a(1716519049));
        final av av = new av((byte)0);
        super.a.a(av, 1, 1, 0, 0, true, 4, 4, 4, 4);
        this.a = a;
        this.a = a2;
        (this.a = new af(6, 380, 1, -1, null, false, false, true)).a();
        av.a(this.a, 4, 1, 0, 0, true);
        av.a(this.a, 4, 1, 0, 1, false);
        av.a(this.a = new an(this.a.a(1716519043), null, b), 17, 0, 2, 4, 1, 0, 2);
        av.a(this.a, 17, 0, 0, 1, 1, 0, 3);
        av.a(this.b, 17, 0, 2, 1, 1, 1, 3, 0, 4, 0, 0);
        av.a(this.c, 17, 0, 0, 1, 1, 2, 3, 0, 0, 0, 4);
        av.a(this.d, 1, 1, 3, 3);
        this.pack();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target == this.d) {
            this.a.h(this.a);
        }
        else if (event.target == this.c || event.target == this.a) {
            this.a(this.a.c, this.a.a());
            this.a.a("");
        }
        else if (event.target == this.a) {
            this.a.f(this.a);
            this.a.h(this.a);
        }
        else if (event.target == this.a) {
            this.a.d(this.a.a);
        }
        else {
            if (event.target != this.b) {
                return false;
            }
            this.a.j(this.a);
        }
        return true;
    }
    
    public final void a(final String s, final String s2) {
        this.a.a(s + ": " + s2);
    }
    
    protected final void a() {
        this.a.h(this.a);
    }
}
