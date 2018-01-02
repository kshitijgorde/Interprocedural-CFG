// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;

final class be extends av
{
    private aj a;
    av b;
    av c;
    es a;
    af a;
    af b;
    private ff a;
    private bj a;
    bh a;
    t a;
    es b;
    
    public be(final aj a) {
        super((byte)0);
        this.a = a;
    }
    
    final void a() {
        final boolean c = this.a.c();
        this.a = new es();
        this.a = this.a.a().a(false, 2);
        (this.b = new af(aj.a(), 200, 1, -1, null, false, false, true)).a();
        final av av = new av((byte)0);
        aj.a(this, (u)av);
        this.a.b(Color.white);
        this.b.b(Color.white);
        this.a.b(new Color(10066278));
        this.a.a(Color.white);
        this.a.a();
        (this.b = new es("", 0, 500)).a();
        af b;
        if (this.a.a().c() || !this.a.a().e) {
            final Color color = new Color(16764006);
            this.a.b(color);
            this.a.a(Color.black);
            (b = (af)new u()).b(color);
        }
        else {
            final av av2 = new av((byte)0);
            (this.a = new ff()).b(aj.a());
            this.a.a(aj.b());
            av2.a(this.a, 10, 2, 2, 1, 1, 0, 0);
            if (this.a.a().a(2L)) {
                final av av3;
                (av3 = new av((byte)0)).a(new bh(this.a.a().e), 1, 1, 0, 0);
                av3.a(new es(this.a.a().a(1716524730)), 1, 1, 1, 0);
                av2.a(this.a = new bj(av3), 1, 1, 1, 0);
            }
            aj.b(av, av2);
            aj.c(this, this.a);
            b = this.b;
        }
        aj.d(av, b);
        if (this.a.a().e) {
            final av av4 = new av((byte)0);
            if (this.a.a().d()) {
                final bh bh = new bh(this.a.a().c, 394, 64);
                this.a = new bh(null, 264, 60, true);
                this.a(av4, 3, 1, 0, 0, false, 0, 0, 0, 0);
                av4.a(bh, 17, 0, 0, 1, 1, 0, 0);
                av4.a(this.a, 13, 0, 1, 1, 1, 1, 0);
            }
            else {
                av4.b(Color.white);
                final Image b2 = this.a.a().b();
                final bh bh2 = new bh(b2, b2.getWidth(null), b2.getHeight(null), true);
                final av av5;
                (av5 = new av((byte)0)).a(av4, 11, 2, 1, 3, 1, 0, 0, 5, 0, 0, 0);
                av5.a(bh2, 16, 0, 0, 1, 1, 0, 1, 5, 5, 5, 5);
                av5.a(this.b, 15, 2, 1, 2, 1, 1, 1);
                this.a(av5, 3, 1, 0, 0, false, 0, 0, 0, 0);
            }
        }
        final av av6 = new av((byte)0);
        av6.a(this.a.a().a("yahoo.games.table_side_foreground", 0), this.a.a().a("yahoo.games.table_side_background", 6723993), this.a.a().a("yahoo.games.table_side_bbbackground", 10079436), this.a.a().a("yahoo.games.table_side_bboutlinedark", 6723993), this.a.a().a("yahoo.games.table_side_bboutlinelight", 10079436), this.a.a().a("yahoo.games.table_side_bbshadow", 6723993));
        this.a.e(this, av6);
        final av av7 = new av((byte)0);
        av6.a(av7, 10, 1, 1, 1, 1, 0, 0, c ? 4 : 8, 4, c ? 4 : 8, 4);
        int n = 0;
        final boolean b3 = !this.a.a().e;
        if (this.a.b()) {
            if (b3) {
                final av av8 = av7;
                final bj b4 = this.a.b;
                final int n2 = 10;
                final int n3 = 2;
                final int n4 = 0;
                final int n5 = 1;
                final int n6 = 1;
                ++n;
                av8.a(b4, n2, n3, n4, n5, n6, 1, 0, 2, 2, 0, 2);
            }
            else {
                final av av9 = av7;
                final bj b5 = this.a.b;
                final int n7 = 10;
                final int n8 = 2;
                final int n9 = 0;
                final int n10 = 1;
                final int n11 = 1;
                final int n12 = 0;
                ++n;
                av9.a(b5, n7, n8, n9, n10, n11, n12, 1, 2, 0, 0, 0);
            }
        }
        if (!b3 && !this.a.a().e()) {
            final bh bh3;
            (bh3 = new bh(null, 1, 1)).b(Color.white);
            av7.a(bh3, 10, 2, 0, 1, 1, 0, ++n, c ? 2 : 4, 8, c ? 2 : 4, 8);
        }
        this.b = new av((byte)0);
        if (b3) {
            av7.a(this.b, 10, 2, 0, 1, 1, ++n, 0);
        }
        else {
            av7.a(this.b, 10, 2, 0, 1, 1, 0, ++n);
        }
        if (this.a.a.size() > 0 && !b3 && !this.a.a().e()) {
            final u u;
            (u = new u(1, 1)).b(Color.white);
            av7.a(u, 10, 2, 0, 1, 1, 0, ++n, c ? 2 : 4, 8, c ? 2 : 4, 8);
        }
        final av av10 = c ? this.a.a : new cc(this.a.a().a(1716518965), this.a.a, this.a.a, this.a.b);
        if (this.a.d() > 0) {
            if (b3) {
                av7.a(this.a.c, 10, 2, 0, 1, 1, 0, 0, 0, 2, 0, 2);
            }
            else {
                av7.a(this.a.c, 10, 2, 0, 1, 1, 0, 0);
            }
            if (b3) {
                av7.a(this.a.d, 10, 2, 0, 1, 1, ++n, 0, 1, 2, 1, 2);
            }
            else {
                av7.a(this.a.d, 10, 2, 0, 1, 1, 0, ++n, 1, 0, 1, 0);
            }
            if (!this.a.a().c() && this.a.a().e) {
                av7.a(av10, 10, 2, 0, 1, 1, 0, ++n, 1, 0, 1, 0);
                if (c) {
                    final av av11 = new av((byte)0);
                    av7.a(av11, 10, 2, 2, 1, 1, 0, ++n, 1, 0, 1, 0);
                    av11.a(this.a.f, 10, 2, 2, 1, 1, 0, 0);
                    av11.a(this.a.e, 10, 2, 2, 1, 1, 1, 0);
                }
                else {
                    av7.a(this.a.f, 10, 2, 2, 1, 1, 0, ++n, 1, 0, 1, 0);
                    av7.a(this.a.e, 10, 2, 2, 1, 1, 0, ++n, 1, 0, 1, 0);
                }
            }
        }
        this.c = new av((byte)0);
        final av av12 = (c || b3) ? this.c : new cc(this.a.a().a(1716518964), this.c, this.a.a, this.a.b);
        if (this.a.b.size() > 0) {
            if (b3) {
                av7.a(av12, 10, 2, 0, 1, 1, ++n, 0, 1, 2, 1, 2);
            }
            else {
                av7.a(av12, 10, 2, 0, 1, 1, 0, ++n, 1, 0, 1, 0);
            }
        }
        final an a = this.a.a;
        if (!b3) {
            av7.a(a, 10, 2, 0, 1, 1, 0, ++n, 1, 0, 1, 0);
        }
        final av av13 = new av((byte)0);
        if (!b3) {
            av7.a(av13, 10, 1, 1, 1, 1, 0, ++n);
            final av av14 = new av(1);
            av7.a(av14, 10, 1, 2, 1, 1, 0, ++n);
            av14.a(this.a.a, 0, 0, 2);
            av14.a(this.a.g, 1, 0, 2);
        }
        if (!b3 && !this.a.a().c() && !this.a.a().d()) {
            final Image a2 = this.a.a().a();
            av7.a(new bh(a2, a2.getWidth(null), a2.getHeight(null), true), 10, 2, 0, 1, 1, 0, ++n, 1, 0, 1, 0);
        }
        final av av15 = new av((byte)0);
        if (this.a.a().c() || this.a.a().d() || b3) {
            aj.g(av15, this.a);
        }
        else {
            aj.g(av15, new u());
        }
        aj.f(av15, this.a.a);
        aj.a(this, this.a = new t(av15, this.a.a(), this.a.a().a(1716523305)));
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        if (event.target == this.a) {
            if (this.a.a != null) {
                this.a.a(this.a.a, "_blank");
            }
            return true;
        }
        return false;
    }
    
    public final boolean a(final Event event, final Object o) {
        for (int i = 0; i < this.a.d(); ++i) {
            if (event.target == this.a.a[i].a) {
                this.a.e(i);
                return true;
            }
        }
        if (event.target == this.a) {
            final String s;
            if ((s = (String)o).equals("/auto")) {
                this.a.a('A');
            }
            else if (s.length() > 0) {
                this.b.l();
                this.a.c(s);
            }
            this.a.a("");
        }
        else if (event.target == this.a.a) {
            this.a.a().c(this.a.a.a);
        }
        else if (event.target == this.a) {
            this.a.a().a("startVoice(\"" + Integer.toString(this.a.c()) + "\")");
        }
        else {
            if (event.target != this.a) {
                return false;
            }
            final ax a;
            if ((a = this.a.a) != null) {
                this.a.d(((eu)a.a).a);
            }
        }
        return true;
    }
    
    public final boolean a(final Event event) {
        if (!super.a(event)) {
            this.a.a(event);
            return false;
        }
        return true;
    }
}
