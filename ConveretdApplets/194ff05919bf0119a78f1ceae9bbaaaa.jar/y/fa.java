// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class fa extends fg
{
    private af a;
    
    fa(final af a, final boolean b) {
        super(b, (byte)0);
        this.a = a;
    }
    
    public final void a(final ei ei, final int n, final int n2, final int n3, final int n4) {
        this.a.a(ei, n, n2, n3, n4);
    }
    
    public final int h() {
        final af a = this.a;
        return a.a * a.t;
    }
    
    public final int g() {
        return this.a.b;
    }
    
    public final void a(Event event, int n, final int n2) {
        final af a = this.a;
        final Event event2 = event;
        n = n2;
        event = event2;
        final af af = a;
        Event event3 = null;
        final int n3 = (af.a == null) ? -1 : af.a(af.a);
        int n4;
        if (n < 0) {
            n4 = -1;
        }
        else {
            n4 = n / af.t;
        }
        if (n4 >= af.a.size()) {
            n4 = -1;
        }
        af.h();
        if (event.when - af.a < 500L && n3 == n4 && n3 != -1) {
            event3 = new Event(af, 1001, new Integer(n3));
            af.a = 0L;
        }
        else if (n4 != -1) {
            event3 = new Event(af, 701, new Integer(n4));
        }
        af.a = event.when;
        if (af.a) {
            if (n4 >= 0) {
                af.a = (ax)af.a.elementAt(n4);
            }
            else {
                af.a = null;
            }
        }
        if (event3 != null) {
            af.a(event3);
        }
    }
    
    public final void b(final Event event, final int n, final int n2) {
    }
    
    public final void b(final int x, int i) {
        final af a;
        (a = this.a).b(x);
        a.x = x;
        if (a.b) {
            ax ax;
            af af;
            for (i = 0; i < a.a.size(); ++i) {
                ax = a.a.elementAt(i);
                if (a.a.stringWidth(ax.a[0]) > x - 4) {
                    a.a(i);
                    af = a;
                    af.a(ax.a[0], i, af.b());
                }
            }
            a.a.n();
        }
        a.h();
        a.a.h();
    }
    
    public final void a(final int n) {
        final af a = this.a;
        a.j = (a.a.b > n + a.a.u);
    }
}
