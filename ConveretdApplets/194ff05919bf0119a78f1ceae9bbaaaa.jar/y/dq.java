// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class dq extends bx
{
    private bj a;
    private dm a;
    
    dq(final av av, final dm a) {
        super(av, a.a(1716522293));
        this.a = a;
        this.a = new bj(a.a(1716522294));
        int n = 2;
        if (a.getParameter("yourastar") != null && "yahoo".equals(a.getParameter("league_id"))) {
            final an b = a.b;
            final int n2 = 17;
            final int n3 = 0;
            final int n4 = 0;
            final int n5 = 1;
            final int n6 = 1;
            final int n7 = 0;
            ++n;
            this.a(b, n2, n3, n4, n5, n6, n7, 3);
        }
        this.a(a.a, 17, 0, 0, 1, 1, 0, ++n);
        final av av2;
        (av2 = new av((byte)0)).a(new es(a.a(1716522472)), 3, 1, 0, 0, true);
        av2.a(a.c, 1, 1, 0, 1);
        av2.a(a.d, 1, 1, 1, 1);
        av2.a(a.e, 1, 1, 2, 1);
        this.a(av2, 17, 0, 0, 1, 1, 0, ++n);
        this.a(a.a, 18, 0, 0, 1, 1, 0, ++n);
        this.a(a.b, 17, 1, 1, 2, 1, 0, ++n);
        final av av3 = new av((byte)0);
        this.a(av3, 1, n - 3, 1, 3);
        av3.a(new es(a.a(1716522462)), 17, 0, 0, 1, 1, 0, 0);
        av3.a(a.a, 17, 2, 0, 1, 1, 0, 1);
        this.a(this.a, 2, 1, 0, ++n);
    }
    
    public final boolean a(final Event event, final Object o) {
        if (event.target == this.a) {
            super.b = false;
            this.l();
            this.a.b(this.a.a.a);
            return true;
        }
        return false;
    }
}
