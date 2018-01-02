// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

public class do extends av
{
    public fl a;
    public ex a;
    int a;
    public cp[] a;
    public av b;
    public av c;
    bj a;
    bj b;
    bj c;
    
    public do(final ex a) {
        super((byte)0);
        this.a = a;
        this.a = new bj(a.a().a(1716519101));
        this.b = new bj(a.a().a(1716519099));
        this.c = new bj(a.a().a(1716519100));
        this.a = a.d();
        this.a = new cp[this.a];
        for (int i = 0; i < this.a; ++i) {
            this.a[i] = new cp(i, (this.a % 2 == 1) ? (i == 0) : (i % 2 == 0), a);
        }
        final av av = new av(3);
        av.a(this.b = new av((byte)0), 1, 1, 0, 0, true);
        av.a(this.c = new av((byte)0), 1, 1, 2, 0, true);
        this.a(av, 3, 1, 0, 2, false);
        av.a(this.a[0], 1, 1, 1, 0);
        final av av2;
        (av2 = new av(1)).a(this.a, 0, 0, 2);
        av2.a(this.b, 0, 1, 2);
        av2.a(this.c, 0, 2, 2);
        if (this.a == 4) {
            final av av3 = new av(3);
            this.a(av3, 3, 1, 0, 0, false);
            av3.a(this.a[2], 1, 1, 1, 0);
            av3.a(new u(), 10, 0, 1, 1, 1, 0, 0);
            av3.a(av2, 10, 0, 1, 1, 1, 2, 0);
        }
        else {
            this.c.a(av2, 1, 1, 0, 0);
            if (this.a == 5) {
                this.a(this.a[2], (this.a == 5) ? 1 : 3, 1, 0, 0, false);
            }
        }
        if (this.a == 5) {
            this.a(this.a[3], 1, 1, 2, 0, false);
        }
        this.a(this.a[this.a - 1], 1, 1, 2, 1);
        this.a(this.a[1], 1, 1, 0, 1);
        this.a(this.a = new fl(((ef)a.a()).a, a.c(), this.a, a.a()), (this.a == 5) ? 2 : 1, 1, (this.a != 5) ? 1 : 0, 4, 4);
        this.a.a(false);
        this.b.a(false);
        this.c.a(false);
    }
    
    public boolean a(final Event event, final Object o) {
        if (event.target == this.a[0].a.a && o != null) {
            this.a.a((en)o);
        }
        else if (event.target == this.b) {
            this.a.a('J');
        }
        else if (event.target == this.c) {
            this.a.a('Z');
        }
        else {
            if (event.target != this.a) {
                return false;
            }
            this.a.q();
        }
        return true;
    }
    
    public final void a(final bl bl, final int n) {
        this.a[n].a.a.a(bl);
        this.a[n].a.a.a(bl);
    }
}
