// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class ah extends em
{
    private bj a;
    private an a;
    private ff a;
    private ek a;
    private int a;
    private String a;
    
    ah(final fm fm, final eu eu, final ek a) {
        super(a.a(1716519036), true, a);
        this.a = new ff(240);
        this.a = new bj(a.a(1716519033));
        this.a = new an(a.a(1716519035));
        this.a = fm.e;
        this.a = eu.a;
        this.a = a;
        super.a.a(new es(eu.b + (eu.c.equals("") ? "" : ("(" + eu.c + ")")) + a.a(1716519040) + this.a + "." + ((fm.a == null) ? "" : (a.a(1716519037) + fm.a)) + a.a(1716519041)), 2, 1, 0, 0);
        super.a.a(new es(a.a(1716519038), 2), 1, 1, 0, 2);
        super.a.a(new es(a.a(1716519039)), 17, 0, 0, 1, 1, 1, 3);
        super.a.a(this.a, 10, 2, 0, 1, 1, 1, 2, 0, 0, 0, 8);
        super.a.a(this.a, 17, 0, 0, 1, 1, 1, 4, 6, 0, 6, 0);
        final av av = new av(1);
        super.a.a(av, 2, 1, 0, 5);
        av.a(this.a, 0, 0, 2);
        av.a(super.a = new bj(a.a(1716519042)), 1, 0, 2);
        this.pack();
        this.show();
    }
    
    protected final void a() {
        final String trim = this.a.a().trim();
        this.a.d(this.a, trim.equals("") ? "None" : trim);
        super.a();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.a.d(this.a);
            super.a();
            return true;
        }
        if (event.target == this.a) {
            this.a.a(this.a.a);
            this.a.a.a(this.a.a);
            return true;
        }
        return false;
    }
}
