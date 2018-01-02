// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Color;

public class bx extends ez
{
    private av c;
    private es a;
    
    public bx(final av av) {
        this(av, "");
    }
    
    public bx(final av b, final String s) {
        super(b);
        this.c = new av((byte)0);
        super.b = b;
        super.a(this.a = new es(s), 10, 1, 0, 1, 1, 0, 0, 4, 4, 0, 4, false, false);
        this.a.b(y.u.a);
        this.a.a();
        this.a.a(Color.white);
        super.a(this.c, 10, 0, 1, 1, 1, 0, 1, 4, 4, 4, 4, false, false);
        this.a(Color.black, Color.lightGray, Color.lightGray, Color.black, Color.white, Color.darkGray);
    }
    
    final void a(final u u, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final boolean b, final boolean b2) {
        this.c.a(u, n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, b, b2);
    }
    
    public final void a(final ei ei) {
        super.a(ei);
        final int e = super.e;
        final int f = super.f;
        final int n = e;
        ei.a(Color.lightGray);
        ei.a(0, 0, 0, 0, n - 2, 0, 0, 0, 0);
        ei.a(0, 0, 0, 1, 0, f - 2, 0, 0, 0);
        ei.a(Color.white);
        ei.a(0, 0, 1, 1, n - 3, 1, 0, 0, 0);
        ei.a(0, 0, 1, 2, 1, f - 3, 0, 0, 0);
        ei.a(Color.darkGray);
        ei.a(0, 0, 1, f - 2, n - 2, f - 2, 0, 0, 0);
        ei.a(0, 0, n - 2, f - 3, n - 2, 1, 0, 0, 0);
        ei.a(Color.black);
        ei.a(0, 0, 0, f - 1, n - 1, f - 1, 0, 0, 0);
        ei.a(0, 0, n - 1, f - 2, n - 1, 0, 0, 0, 0);
    }
    
    public final boolean a(final Event event, final int a, final int b) {
        if (b >= this.d() - 4 || b < 4 || a < 4 || a >= this.c() - 4 || event.target == this.a) {
            super.a = true;
            super.a = a;
            super.b = b;
        }
        return true;
    }
    
    public final boolean b(final Event event, int n, final int n2) {
        if (super.a) {
            int n3 = this.a(super.b) + n - super.a;
            n = this.b(super.b) + n2 - super.b;
            if (n3 + this.c() < 4) {
                n3 = -this.c() + 4;
            }
            if (n + this.d() < 4) {
                n = -this.d() + 4;
            }
            if (n3 + 4 >= super.b.c()) {
                n3 = super.b.c() - 4;
            }
            if (n + 4 >= super.b.d()) {
                n = super.b.d() - 4;
            }
            this.a(n3, n);
            ((u)(super.b = true)).i();
        }
        return true;
    }
    
    public final boolean c(final Event event, final int n, final int n2) {
        super.a = false;
        return true;
    }
}
