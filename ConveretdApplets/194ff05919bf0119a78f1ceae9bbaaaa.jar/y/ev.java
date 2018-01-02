// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class ev extends do
{
    bj d;
    bj[] a;
    bj e;
    bj f;
    bj g;
    av d;
    av e;
    
    ev(final eb eb) {
        super(eb);
        this.a = new bj[13];
        this.d = new av(2);
        this.e = new av(2);
        this.d = new bj(eb.a().a(1716521481));
        this.e = new bj(eb.a().a(1716521480));
        this.f = new bj(eb.a().a(1716521478));
        this.g = new bj(eb.a().a(1716521482));
        super.c.a(this.d, 1, 1, 0, 0, false);
        final av av = new av((byte)0);
        this.d.a(av, 0);
        this.d.a(false);
        if (eb.a.b) {
            av.a(this.d, 1, 1, 0, 0);
        }
        final av av2 = new av((byte)0);
        this.d.a(av2, 1);
        this.e.a(false);
        this.f.a(false);
        this.d.c(0);
        if (eb.c()) {
            av2.a(this.e, 10, 2, 0, 1, 1, 0, 0);
            av2.a(this.f, 10, 2, 0, 1, 1, 0, 1);
        }
        super.b.a(this.e, 1, 1, 0, 0, false);
        this.e.a(new u(), 0);
        final av av3 = new av((byte)0);
        this.e.a(av3, 1);
        for (int i = 0; i < 13; ++i) {
            (this.a[i] = new bj(Integer.toString(i + 1))).a(false);
            av3.a(this.a[i], 10, 2, 2, 1, 1, i % 5, i / 5);
        }
        this.g.a(false);
        av3.a(this.g, 10, 2, 2, 2, 1, 3, 2);
        if (!eb.c()) {
            final av av4 = new av((byte)0);
            av3.a(av4, 10, 2, 1, 5, 1, 0, 3);
            av4.a(this.e, 1, 1, 0, 0);
            av4.a(this.f, 1, 1, 1, 0);
        }
        this.e.c(0);
    }
    
    public final boolean a(final Event event, final Object o) {
        if (event.target == this.d) {
            final en[] a;
            if ((a = super.a[0].a.a.a()).length != 2) {
                new fi(super.a.b(), super.a.a().a(1716521479), super.a.a());
                return true;
            }
            super.a.a(a[0], a[1]);
            return true;
        }
        else {
            if (event.target == this.e) {
                super.a.a('M', (byte)(-1));
                return true;
            }
            if (event.target == this.f) {
                super.a.a('V');
                return true;
            }
            if (event.target == this.g) {
                super.a.a('M', (byte)0);
                return true;
            }
            for (int i = 0; i < 13; ++i) {
                if (event.target == this.a[i]) {
                    super.a.a('M', (byte)(i + 1));
                    return true;
                }
            }
            return super.a(event, o);
        }
    }
}
