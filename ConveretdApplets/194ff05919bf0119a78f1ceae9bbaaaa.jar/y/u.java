// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Font;
import java.awt.Color;

public class u
{
    private boolean a;
    boolean c;
    public static final Color a;
    public static Font a;
    av a;
    protected int c;
    protected int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    boolean d;
    boolean e;
    boolean f;
    Color b;
    Color c;
    Color d;
    Color e;
    Color f;
    Color g;
    boolean g;
    boolean h;
    boolean i;
    protected int r;
    protected int s;
    
    public u() {
        this(false);
    }
    
    public u(final int n, final int n2) {
        this(false, n, n2);
    }
    
    public u(final boolean b) {
        this(b, 0, 0);
    }
    
    public u(final boolean h, final int r, final int s) {
        this.c = false;
        this.e = true;
        this.f = true;
        this.i = false;
        this.r = 0;
        this.s = 0;
        this.h = h;
        this.r = r;
        this.s = s;
    }
    
    public final void b(final boolean f) {
        this.f = f;
        if (f && this.e) {
            this.h();
        }
    }
    
    public final void c() {
        this.a = true;
    }
    
    public final void a(final Color b, final Color c, final Color d, final Color g, final Color f, final Color e) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h();
    }
    
    public final void a(final Color b) {
        this.b = b;
        this.h();
    }
    
    public final void b(final Color c) {
        this.c = c;
        this.h();
    }
    
    public final Color a() {
        if (this.c != null) {
            return this.c;
        }
        if (this.a != null) {
            return this.a.a();
        }
        return null;
    }
    
    public u a() {
        if (this.c == null && this.a != null) {
            return this.a.a();
        }
        return null;
    }
    
    public final Color b() {
        if (this.b != null) {
            return this.b;
        }
        if (this.a != null) {
            return this.a.b();
        }
        return null;
    }
    
    public final Color c() {
        if (this.d != null) {
            return this.d;
        }
        if (this.a != null) {
            return this.a.c();
        }
        return null;
    }
    
    public final Color d() {
        if (this.f != null) {
            return this.f;
        }
        if (this.a != null) {
            return this.a.d();
        }
        return null;
    }
    
    public final Color e() {
        if (this.e != null) {
            return this.e;
        }
        if (this.a != null) {
            return this.a.e();
        }
        return null;
    }
    
    public final Color f() {
        if (this.g != null) {
            return this.g;
        }
        if (this.a != null) {
            return this.a.f();
        }
        return null;
    }
    
    public void b() {
        this.c = true;
    }
    
    public void d() {
        this.g = false;
        if (this.a != null) {
            this.a.d();
        }
        this.h();
    }
    
    public void a(final ei ei, final int n, final int n2, final int n3, final int n4) {
        this.a(ei);
    }
    
    public void a(final ei ei) {
        this.b(ei);
    }
    
    protected void b(final ei ei, final int n, final int n2, final int n3, final int n4) {
        if (this.i) {
            this.j();
            this.a(ei);
        }
    }
    
    protected void e() {
    }
    
    protected int a() {
        return this.r;
    }
    
    protected int b() {
        return this.s;
    }
    
    public final int c() {
        return this.e;
    }
    
    public int d() {
        return this.f;
    }
    
    final int e() {
        return this.a() + this.n + this.p;
    }
    
    final int f() {
        return this.b() + this.m + this.o;
    }
    
    public final int a(final u u) {
        if (u == this) {
            return 0;
        }
        return this.c + this.a.a(u);
    }
    
    public final int b(final u u) {
        if (u == this) {
            return 0;
        }
        return this.d + this.a.b(u);
    }
    
    public final void a(final int c, final int d, final int n, final int n2) {
        this.b(0, 0, n, n2);
        this.c = c;
        this.d = d;
        this.r = n;
        this.e = n;
        this.s = n2;
        this.f = n2;
        if (this.g) {
            this.e();
            this.h();
        }
    }
    
    public final void a(int c, int d) {
        final u u = this;
        final int n = c;
        d = d;
        c = n;
        this = u;
        u.b(0, 0, this.e, this.f);
        this.c = c;
        this.d = d;
        this.b(0, 0, this.e, this.f);
    }
    
    u a(final int n, final int n2) {
        return this;
    }
    
    final boolean a(final int n, final int n2, final int n3, final int n4) {
        return n < this.c + this.e && n2 < this.d + this.f && n3 >= this.c && n4 >= this.d;
    }
    
    public final boolean a(final int n, final int n2) {
        return n >= this.c && n < this.c + this.e && n2 >= this.d && n2 < this.d + this.f;
    }
    
    public final void a(final Event event) {
        final int c = this.c;
        final int d = this.d;
        if (!this.a(event)) {
            event.x += c;
            event.y += d;
            if (this.a != null) {
                this.a.a(event);
            }
        }
    }
    
    public FontMetrics a(final Font font) {
        return this.a.a(font);
    }
    
    public final void f() {
        this.a(this, true);
    }
    
    void a(final u u, final boolean b) {
        this.a.a(u, b);
    }
    
    public boolean a(final Event event, final int n, final int n2) {
        return false;
    }
    
    public boolean c(final Event event, final int n, final int n2) {
        return false;
    }
    
    public boolean b(final Event event, final int n, final int n2) {
        return false;
    }
    
    public boolean a(final int n) {
        return false;
    }
    
    public boolean a(final Event event, final Object o) {
        return false;
    }
    
    public void g() {
        this.c = false;
    }
    
    public final void b(ei a) {
        if (!this.a) {
            final u a2;
            if ((a2 = this.a()) == this || a2 == null) {
                a.a(this.a());
                a.b(0, 0, this.e, this.f);
                return;
            }
            final int a3 = this.a(a2.a);
            final int b = this.b(a2.a);
            if (a2.h && !this.h) {
                (a = a.a(0, this.e, this.f)).a = -a3;
                a.b = -b;
                a2.a(a, a3, b, this.e, this.f);
                a.a.dispose();
                return;
            }
            final ei ei = a;
            ei.a -= a3;
            final ei ei2 = a;
            ei2.b -= b;
            a2.a(a, a3, b, this.e, this.f);
            final ei ei3 = a;
            ei3.a += a3;
            final ei ei4 = a;
            ei4.b += b;
        }
    }
    
    public boolean a(final Event event) {
        if (event.id == 501) {
            return this.a(event, event.x, event.y);
        }
        if (event.id == 502) {
            return this.c(event, event.x, event.y);
        }
        if (event.id == 506) {
            return this.b(event, event.x, event.y);
        }
        if (event.id == 503) {
            return this.a(event.y);
        }
        if (event.id == 504) {
            return false;
        }
        if (event.id == 505) {
            return false;
        }
        if (event.id == 1001) {
            return this.a(event, event.arg);
        }
        return event.id != 65536 && event.id != 401 && event.id != 403 && (event.id == 402 || event.id == 404) && false;
    }
    
    public void h() {
        if (!this.c) {
            return;
        }
        this.b(0, 0, this.e, this.f);
        if (!this.i) {
            this.k();
        }
    }
    
    void b(final int n, final int n2, final int n3, final int n4) {
        if (this.a != null) {
            this.a.b(n + this.c, n2 + this.d, n3, n4);
        }
    }
    
    public void i() {
        if (this.a != null) {
            this.a.i();
        }
    }
    
    protected void j() {
        this.e = true;
    }
    
    public final av a() {
        return this.a;
    }
    
    public av b() {
        if (this.a != null) {
            return this.a.b();
        }
        return null;
    }
    
    void k() {
        this.e = true;
        if (this.a != null) {
            this.a.k();
        }
    }
    
    static {
        a = new Color(128);
        u.a = new Font(Toolkit.getDefaultToolkit().getFontList()[0], 0, 12);
    }
}
