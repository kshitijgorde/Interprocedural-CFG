import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class aa extends l
{
    public int a;
    public String b;
    public u c;
    public j d;
    public j e;
    public j f;
    public j g;
    public boolean h;
    public boolean i;
    public int j;
    public b k;
    
    public void a() {
        if (!super.i) {
            this.k.b(this.a);
        }
        super.a();
        if (super.k || this.e != null) {
            this.g();
        }
    }
    
    public void b() {
        if (super.k && super.l && !super.i) {
            this.k.f(this.a);
        }
    }
    
    public void a(final n n) {
        if (this.i) {
            return;
        }
        final boolean b = (super.k && super.l && !super.i) ^ this.h;
        if (this.d == null && this.f == null) {
            n.a(new Color(212, 212, 212));
            n.b(0, 0, super.c, super.d);
            n.f = this.c;
            final int n2 = (super.c - n.f.a(this.b)) / 2;
            final int n3 = (super.d - n.f.d) / 2 + n.f.e;
            if (b) {
                n.a(Color.black);
                n.b(0, 0, super.c - 1, 1);
                n.b(0, 0, 1, super.d - 1);
                n.a(new Color(255, 255, 255));
                n.b(0, super.d - 1, super.c, 1);
                n.b(super.c - 1, 0, 1, super.d);
                n.a(new Color(132, 132, 132));
                n.b(1, 1, super.c - 3, 1);
                n.b(1, 1, 1, super.d - 3);
                if (this.b != null) {
                    n.a(Color.black);
                    n.a(this.b, n2 + 1, n3 + 1);
                }
            }
            else {
                n.a(new Color(255, 255, 255));
                n.b(0, 0, super.c - 1, 1);
                n.b(0, 0, 1, super.d - 1);
                n.a(Color.black);
                n.b(0, super.d - 1, super.c, 1);
                n.b(super.c - 1, 0, 1, super.d);
                n.a(new Color(132, 132, 132));
                n.b(1, super.d - 2, super.c - 2, 1);
                n.b(super.c - 2, 1, 1, super.d - 2);
                if (this.b != null) {
                    n.a(Color.black);
                    n.a(this.b, n2, n3);
                }
            }
        }
        else {
            n.d = true;
            n.e = new f(255, 255, 255, this.j);
            if (!b) {
                if (super.i && this.g != null) {
                    n.a(this.g, 0, 0);
                }
                else if ((super.l || super.k) && this.e != null) {
                    n.a(this.e, 0, 0);
                }
                else if (this.d != null) {
                    n.a(this.d, 0, 0);
                }
            }
            else if (this.f != null) {
                n.a(this.f, 0, 0);
            }
            else if (this.e != null) {
                n.a(this.e, 1, 1);
            }
            else {
                n.a(this.d, 1, 1);
            }
            n.d = false;
        }
    }
    
    public aa(final k k, final int a, final b i) {
        super(k);
        this.b = "";
        this.j = 255;
        this.a = a;
        this.k = i;
        this.c = k.b.b("SansSerif", 0, 12);
    }
    
    public void c(final boolean b) {
        super.c(b);
        if (this.g != null) {
            this.j();
        }
    }
    
    public void c(final int n, final int n2) {
        super.c(n, n2);
        if (super.l && !super.i) {
            this.k.d(this.a);
        }
        this.g();
    }
    
    public void d() {
        if (!super.i) {
            this.k.g(this.a);
        }
        super.d();
        if (super.k || this.e != null) {
            this.g();
        }
    }
    
    public void b(final int n, final int n2, final int n3) {
        super.b(n, n2, n3);
        if (!super.i) {
            this.k.a(this.a);
        }
        this.g();
    }
}
