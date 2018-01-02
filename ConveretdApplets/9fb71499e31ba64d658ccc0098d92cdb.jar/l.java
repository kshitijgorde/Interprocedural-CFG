import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Insets;

// 
// Decompiled by Procyon v0.5.30
// 

public class l
{
    public int a;
    public int b;
    public int c;
    public int d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public k m;
    public f[] n;
    public Insets o;
    public boolean p;
    public int q;
    public boolean r;
    public boolean s;
    
    public void a() {
        this.l = true;
        if (this.p) {
            this.a(true);
        }
    }
    
    public boolean a(final int n, final int n2) {
        return n >= this.a && n < this.a + this.c && n2 >= this.b && n2 < this.b + this.d;
    }
    
    public void b() {
        ++this.q;
    }
    
    public int a(final n n, final Rectangle rectangle, final String s, int d, final int n2) {
        int e = n.f.e;
        if (d == -1) {
            d = n.f.d;
        }
        String s2 = "";
        int i = 0;
        while (i < s.length()) {
            int n3;
            for (n3 = i; n3 < s.length() && s.charAt(n3) == ' '; ++n3) {}
            final int index = s.indexOf("\n", n3);
            int n4 = s.indexOf(" ", n3);
            if (n4 == -1) {
                n4 = s.length();
            }
            if (index != -1 && index < n4) {
                n4 = index;
            }
            if (index == n3) {
                this.a(n, s2, rectangle.x, rectangle.y + e, rectangle.width, n2);
                s2 = s.substring(n3, n4);
                e += d;
                ++i;
            }
            else {
                final String string = s2 + s.substring(i, n4);
                if (n.f.a(string) > rectangle.width) {
                    this.a(n, s2, rectangle.x, rectangle.y + e, rectangle.width, n2);
                    e += d;
                    s2 = s.substring(n3, n4);
                }
                else {
                    s2 = string;
                }
                i = n4;
            }
        }
        if (!s2.equals("")) {
            this.a(n, s2, rectangle.x, rectangle.y + e, rectangle.width, n2);
            e += d;
        }
        return e + n.f.d - n.f.e - d;
    }
    
    public void a(final boolean b) {
        if (b) {
            this.m.b.setCursor(new Cursor(12));
            return;
        }
        this.m.b.setCursor(new Cursor(0));
    }
    
    public Rectangle c() {
        return new Rectangle(this.a, this.b, this.c, this.d);
    }
    
    public void a(final n n, final int n2, final String s) {
        n.a(s, (this.c - n.f.a(s)) / 2, n2 + n.f.e);
    }
    
    public void b(final boolean f) {
        if (this.f == f) {
            return;
        }
        this.f = f;
        if (this.f) {
            this.j();
            return;
        }
        this.g();
    }
    
    public void d() {
        this.l = false;
        if (this.p) {
            this.a(false);
        }
    }
    
    public void a(final int a, final int b, final int c, final int d) {
        this.g();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.j();
    }
    
    public boolean a(final l l) {
        return this.c().intersects(l.c());
    }
    
    public void a(final int[][] array) {
        this.n = new f[array.length];
        for (int i = 0; i < array.length; ++i) {
            if (array[i].length == 3) {
                this.n[i] = new f(array[i][0], array[i][1], array[i][2]);
            }
            else {
                this.n[i] = new f(array[i][0], array[i][1], array[i][2], array[i][3]);
            }
        }
        this.j();
    }
    
    public void a(final n n, final String s, final int n2, final int n3, final int n4, final int n5) {
        switch (n5) {
            case -1: {
                n.a(s, n2, n3);
            }
            case 0: {
                n.a(s, n2 + (n4 - n.f.a(s)) / 2, n3);
            }
            case 1: {
                n.a(s, n2 + n4 - n.f.a(s), n3);
            }
            default: {}
        }
    }
    
    public void b(final int n, final int n2) {
        this.a(n, n2, this.c, this.d);
    }
    
    public void a(final int n, final boolean b, final boolean b2) {
    }
    
    public void a(final k k) {
        this.m = null;
    }
    
    public void e() {
        this.j = false;
    }
    
    public int a(final n n, final int n2, final String s, final int n3) {
        final n n4 = new n(n);
        n4.a(0, 0, 0, 0);
        return this.a(n4, new Rectangle(0, 0, n2, 0), s, n3, -1);
    }
    
    public Rectangle f() {
        if (this.o == null) {
            return new Rectangle(this.a, this.b, this.c, this.d);
        }
        return new Rectangle(this.a + this.o.left, this.b + this.o.top, this.c - this.o.left - this.o.right, this.d - this.o.top - this.o.bottom);
    }
    
    public void a(final n n) {
    }
    
    public l(final k m) {
        this.e = true;
        this.f = true;
        this.g = true;
        this.m = m;
    }
    
    public l() {
        this.e = true;
        this.f = true;
        this.g = true;
    }
    
    public void c(final boolean b) {
        this.m.a(this, b);
    }
    
    public void a(final int n, final int n2, final int n3) {
        this.c(n, n2);
        this.k = false;
    }
    
    public void c(final int n, final int n2) {
        this.k = false;
    }
    
    public void b(final int n, final boolean b, final boolean b2) {
    }
    
    public void g() {
        if (this.m == null) {
            return;
        }
        this.m.g(this);
    }
    
    public boolean h() {
        return false;
    }
    
    public void i() {
        this.j = true;
    }
    
    public void b(final int n, final int n2, final int n3) {
        this.k = true;
    }
    
    public void b(final k m) {
        this.m = m;
    }
    
    public void j() {
        if (this.m == null) {
            return;
        }
        this.m.i(this);
    }
    
    public void d(final int n, final int n2) {
    }
    
    public void e(final int n, final int n2) {
    }
}
