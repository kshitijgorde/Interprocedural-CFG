// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;

final class bf extends em
{
    bj a;
    es a;
    es b;
    private bj b;
    private bj c;
    private bj d;
    private an a;
    es c;
    String a;
    dm a;
    boolean a;
    long a;
    long b;
    private df a;
    
    bf(final dm a, final eu eu, boolean n, final String s) {
        super(a.a(1716519114) + eu.b, true, a);
        this.c = new es();
        this.a = new bj(a.a(1716519113));
        this.a = new es("\n");
        this.b = new bj(a.a(1716519277));
        if (!eu.a.equals(a.c)) {
            this.d = new bj(a.a(1716525264));
            this.c = new bj(a.a(1716522308));
        }
        this.a = new an(a.a(1716519276));
        new bj(a.a(1716519278));
        this.a = eu.a;
        this.a = a;
        final av av;
        (av = new av((byte)0)).a(new es(a.a(1716519114) + eu.b + ":"), 2, 1, 0, 0);
        av.a(this.b = new es(s), 2, 1, 0, 1);
        final av av2 = new av((byte)0);
        super.a.a(av2, 2, 1, 0, 4, true);
        av2.a(new es(a.a(1716519119)), 17, 0, 0, 1, 1, 0, 0);
        av2.a(this.c, 17, 2, 2, 1, 1, 1, 0);
        final av av3 = new av((byte)0);
        super.a.a(av3, 2, 1, 0, 5, true);
        av3.a(this.a, 1, 1, 0, 0);
        av3.a(this.a, 1, 1, 1, 0, true);
        final av av4 = new av(1);
        super.a.a(av4, 1, 1, 0, 6);
        this.a.a(b);
        av4.a(this.a, 0, 0, 2);
        if (this.d != null) {
            av4.a(this.d, 1, 0, 2);
        }
        av4.a(this.b, (this.d == null) ? 1 : 2, 0, 2);
        if (this.c != null) {
            av4.a(this.c, (this.d == null) ? 2 : 3, 0, 2);
        }
        final bj a2 = new bj(a.a(1716519279));
        super.a = a2;
        if (this.d == null || this.c == null) {
            av4.a(a2, (this.c == null && this.d == null) ? 2 : 3, 0, 2);
        }
        else {
            av4.a(a2, 4, 0, 2);
        }
        if (a.a != null && a.a.b == 1) {
            final u u;
            (u = new u(1, 1)).b(Color.darkGray);
            super.a.a(u, 1, 7, 2, 0, true, 8, 8, 8, 8);
            super.a.a(new h(a, this), 1, 7, 3, 0, false, 0, 8, 0, 8);
        }
        if (a.f != null) {
            n = (int)null;
            try {
                n = (int)new URL("http://" + a.getCodeBase().getHost() + "/yai" + eu.b + "_180x180.gif");
            }
            catch (MalformedURLException ex) {}
            final av av5 = new av();
            av5.a(this.a = new df(a.getImage((URL)n), a), 1, 1, 0, 0, true);
            final av av6;
            (av6 = new av((byte)0)).a(av5, 17, 3, 3, 1, 1, 0, 0, 0, 0, 0, 20);
            av6.a(av, 13, 3, 3, 1, 1, 1, 0, 0, 0, 0, 30);
            super.a.a(av6, 2, 1, 0, 1);
        }
        else {
            super.a.a(av, 2, 1, 0, 1);
        }
        this.pack();
        this.show();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target == this.b) {
            this.a.j(this.a);
            this.a.i(this.a);
        }
        else if (event.target == this.a) {
            if (this.a.a) {
                this.a.f(this.a);
            }
            else {
                this.a.g(this.a);
            }
        }
        else if (event.target == this.d) {
            this.a.b(this);
            this.a.i(this.a);
        }
        else if (event.target == this.a) {
            this.a.a(this);
        }
        else {
            if (event.target != this.c) {
                return false;
            }
            this.a.d(this.a);
            this.a.i(this.a);
        }
        return true;
    }
    
    protected final void a() {
        this.a.i(this.a);
    }
}
