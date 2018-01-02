// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.util.Vector;

public final class dr extends fg
{
    private int w;
    private int x;
    public Vector a;
    private int y;
    
    public dr() {
        this(100);
    }
    
    public dr(final int y) {
        super(true, (byte)0);
        this.w = 0;
        this.x = 0;
        this.a = new Vector();
        this.y = y;
    }
    
    public final void b(final u u, int size) {
        if (size > this.a.size()) {
            size = this.a.size();
        }
        this.a.insertElementAt(u, size);
        u.a = this;
        u.i = false;
        if (super.c) {
            int l = 0;
            for (int i = 0; i < size; ++i) {
                l += ((u)this.a.elementAt(i)).b();
            }
            u.b();
            u.e = u.a();
            u.f = u.b();
            u.e();
            u.k = 0;
            this.c(u.l = l, u.b());
            this.x += u.b();
            if (u.a() > this.w) {
                this.w = u.a();
            }
            this.a();
        }
    }
    
    public final void a_(final int n) {
        if (0 > this.a.size()) {
            return;
        }
        final u u = this.a.elementAt(0);
        this.a.removeElementAt(0);
        if (super.c) {
            super.d(u.l, u.f);
            this.x -= u.b();
            u.g();
        }
        this.h();
    }
    
    public final void b() {
        super.b();
        int l = 0;
        for (int i = 0; i < this.a.size(); ++i) {
            final u u;
            (u = this.a.elementAt(i)).b();
            u.e = u.a();
            u.f = u.b();
            u.e();
            u.k = 0;
            this.c(u.l = l, u.b());
            this.x += u.b();
            l += u.b();
            if (u.a() > this.w) {
                this.w = u.a();
            }
        }
        this.a();
    }
    
    public final void g() {
        for (int i = 0; i < this.a.size(); ++i) {
            ((u)this.a.elementAt(i)).g();
        }
        this.m();
        super.g();
    }
    
    private void a() {
        int l = 0;
        for (int i = 0; i < this.a.size(); ++i) {
            final u u;
            (u = this.a.elementAt(i)).e = this.w;
            u.k = 0;
            u.l = l;
            u.e();
            l += u.f;
        }
    }
    
    public final void a(final ei ei, int n, int b, final int n2, final int n3) {
        n = 0;
        b = ei.b;
        for (int i = 0; i < this.a.size(); ++i) {
            final u u = this.a.elementAt(i);
            ei.b = b + n;
            final int b2 = u.b();
            final boolean b3 = n + b2 >= n2;
            if (n < n3 && b3) {
                u.a(ei);
            }
            n += b2;
        }
    }
    
    protected final void j() {
        for (int i = 0; i < this.a.size(); ++i) {
            ((u)this.a.elementAt(i)).j();
        }
        super.j();
    }
    
    protected final void k() {
        super.a.e = true;
        super.k();
    }
    
    public final u a(final int n, int n2) {
        if (super.a(n, n2) == super.a) {
            n2 += super.a;
            for (int i = 0; i < this.a.size(); ++i) {
                final u u;
                if ((u = this.a.elementAt(i)).l <= n2 && u.l + u.f >= n2) {
                    u.c = 0;
                    u.d = u.l - super.a;
                    return u.a(n, n2 - u.d - super.a);
                }
            }
        }
        return super.a(n, n2);
    }
    
    public final void a(final Event event, final int x, final int y) {
        System.out.println("canvasMouseDown(" + event + ", " + x + ", " + y + ")");
        event.x = x;
        event.y = y;
    }
    
    public final void b(final Event event, final int x, final int y) {
        System.out.println("canvasMouseUp(" + event + ", " + x + ", " + y + ")");
        event.x = x;
        event.y = y;
    }
    
    public final int g() {
        return this.w;
    }
    
    public final int h() {
        return this.y;
    }
}
