// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.FontMetrics;
import java.util.Vector;

public final class c extends av
{
    public Vector a;
    private int t;
    public int a;
    int b;
    public ff a;
    private dt a;
    FontMetrics a;
    protected bd a;
    boolean a;
    boolean b;
    private int u;
    private u a;
    private av b;
    
    public c() {
        super((byte)0);
        this.a = new Vector();
        this.t = -1;
        this.a = 0;
        this.b = -1;
        this.a = new ff();
        this.a = new dt(this);
        this.a = null;
        this.a = false;
        this.a = new u(1, 1);
        this.a(this.b = new av((byte)0), 10, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2);
        this.b.a(this.a, 17, 2, 2, 1, 1, 0, 0);
        final ff a;
        (a = this.a).a = false;
        a.h();
        this.b.a(this.a, 10, 3, 3, 1, 1, 1, 0);
        this.b.a(this.a, 17, 0, 0, 1, 1, 2, 0);
    }
    
    public final void a(final ei ei) {
        this.a.c = this.c();
        super.a(ei);
        ei.a(0, this.c(), this.d(), this.d(), this.f());
    }
    
    public final void a(final boolean b) {
        ((u)(this.a = !b)).h();
    }
    
    public final void a(final String s) {
        this.a.addElement(s);
        if (this.t == -1) {
            this.t = 0;
            this.a.a(s);
        }
        if (this.a != null) {
            this.a.h();
        }
    }
    
    public final boolean a(final Event event, int n, int a) {
        if (event.target == this.a && !this.a) {
            this.b = -1;
            this.b = true;
            this.a.h();
            if (this.a == null) {
                final int a2 = this.a();
                n = this.a.getHeight() * this.a.size();
                a = this.a(this.b());
                int n2;
                if ((n2 = this.b(this.b()) + this.b()) + n > this.b().d()) {
                    this.u = n2 - (this.b().d() - n);
                    n2 -= this.u;
                }
                else {
                    this.u = 0;
                }
                final int n3 = a2;
                final int n4 = n;
                n = n3;
                this.a = new bd(this, n, n4);
                this.b().a(this.a, a, n2);
                this.a(this.a, true);
            }
            else {
                this.a();
            }
        }
        return true;
    }
    
    final void a() {
        this.b().a(this.a);
        this.a = null;
    }
    
    public final boolean b(final Event event, final int n, int n2) {
        if (this.a != null) {
            n2 -= this.b() + 2 - this.u;
            this.b = -1;
            final int b;
            if (n2 >= 0 && (b = n2 / this.a.getHeight()) < this.a.size()) {
                this.b = b;
            }
            this.a.h();
        }
        return true;
    }
    
    final Event a() {
        if (this.a != null && this.b != -1) {
            this.a();
            this.t = this.b;
            final String s = this.a.elementAt(this.b);
            this.a.a(s);
            this.a = this.b;
            return new Event(this, 1001, s);
        }
        return null;
    }
    
    public final boolean c(final Event event, final int n, final int n2) {
        Event a = null;
        if (event.target == this.a) {
            this.b = false;
            this.a.h();
            a = this.a();
        }
        if (a != null) {
            this.a(a);
        }
        return true;
    }
    
    public final void b() {
        super.b();
        ((u)(this.a = this.a(y.u.a))).b(this.f());
    }
    
    public final int a() {
        int n = 0;
        for (int i = 0; i < this.a.size(); ++i) {
            final int stringWidth;
            if ((stringWidth = this.a.stringWidth(this.a.elementAt(i))) > n) {
                n = stringWidth;
            }
        }
        return n + super.a();
    }
}
