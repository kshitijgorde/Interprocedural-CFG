// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class ey extends bx
{
    private k a;
    private bj a;
    private bj b;
    private an[] a;
    private an a;
    private String[] a;
    private at a;
    
    ey(final k a, final av av) {
        super(av, a.a.a(1716521453));
        this.a = new an[4];
        this.a = new String[4];
        this.a = new bj(a.a.a(1716521445));
        this.b = new bj(a.a.a(1716521448));
        this.a[0] = a.a.a(1716521451);
        this.a[1] = a.a.a(1716521441);
        this.a[2] = a.a.a(1716521452);
        this.a[3] = a.a.a(1716521447);
        this.a = a;
        for (int i = 0; i < this.a.length; ++i) {
            this.a(this.a[i] = new an(this.a[i], (i == 1) ? this.a[0] : null, i == 0), 17, 0, 0, 2, 1, 0, i + 1);
        }
        final String parameter;
        if ((parameter = a.a.getParameter("category")) != null && !parameter.equals("social")) {
            this.a[3].a(true);
        }
        if (a.a.g != null && a.a.g.indexOf(a.a.a(1716521441)) != -1) {
            this.a[1].a(true);
        }
        this.a(this.a = new an(a.a.a(1716524977), null, true), 17, 0, 0, 2, 1, 0, this.a.length + 1);
        if (a.a.a(32L)) {
            this.a(this.a = new at(a.a), 2, 1, 0, this.a.length + 2);
        }
        final av av2 = new av(1);
        this.a(av2, 2, 1, 0, this.a.length + 3);
        av2.a(this.a, 0, 0, 2);
        av2.a(this.b, 1, 0, 2);
        this.a();
    }
    
    public final boolean a(final Event event, final Object o) {
        if (event.target == this.a) {
            if (this.a[1].a) {
                this.a.a("su", "");
            }
            if (this.a[2].a) {
                this.a.a("ps", "");
            }
            if (this.a[3].a) {
                this.a.a("rd", "");
            }
            if (!this.a.a) {
                this.a.a("ff", "");
            }
            if (this.a != null) {
                this.a.a(this.a);
            }
            this.a.c();
            this.l();
            return true;
        }
        if (event.target == this.b) {
            this.a.b();
            return true;
        }
        return false;
    }
}
