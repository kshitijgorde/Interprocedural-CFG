import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class al extends l
{
    public static final int[][] a;
    public int b;
    public String c;
    public String d;
    public String e;
    public u f;
    public u g;
    public aa h;
    public aa i;
    public boolean j;
    public int k;
    public int l;
    private b m;
    public Vector n;
    public Vector o;
    
    public void a(final k k) {
        super.a(k);
        if (this.h != null) {
            k.f(this.h);
        }
        if (this.i != null) {
            k.f(this.i);
        }
    }
    
    public int a(final int n) {
        final int n2 = 72;
        final j j = new j();
        j.a(1, 1);
        final n n3 = new n(j);
        n3.f = this.g;
        int n4 = n2 + this.a(n3, n - this.k - 16, this.e, this.g.d + this.l);
        if (this.d != null) {
            n4 += 20;
        }
        if (this.b == 1) {
            n4 += 28;
        }
        return n4 + this.k;
    }
    
    public void b(final int[][] array) {
        if (this.h != null) {
            this.h.a(array);
        }
        if (this.i != null) {
            this.i.a(array);
        }
    }
    
    public al(final k k, final b m, final String s, final String s2, final String s3, final int n) {
        super(k);
        this.n = new Vector();
        this.o = new Vector();
        int n2 = 1;
        for (int i = 0; i < s2.length(); ++i) {
            if (s2.charAt(i) == '\n') {
                ++n2;
            }
        }
        final String[] array = new String[n2];
        this.a(k, m, s, s2, s3, n);
        this.m = m;
    }
    
    public void a(final n n) {
        if (super.n.length > 4) {
            n.e = super.n[4];
            n.b(0, 0, super.c - this.k, super.d - this.k);
        }
        if (!this.j) {
            n.e = super.n[0];
            n.c(0, 0, super.c - 1 - this.k, super.d - 1 - this.k);
            n.c(1, 1, super.c - 3 - this.k, super.d - 3 - this.k);
        }
        n.f = this.f;
        n.e = super.n[1];
        if (this.c != null) {
            n.a(this.c, (super.c - this.k - n.f.a(this.c)) / 2, 10 + n.f.e);
        }
        n.f = this.g;
        n.e = super.n[2];
        final int a = this.a(n, new Rectangle(8, 40, super.c - this.k - 16, super.d - 40), this.e, this.g.d + this.l, 0);
        if (this.b != 2 && this.d != null) {
            n.f = this.f;
            n.e = super.n[3];
            n.a(this.d, (super.c - this.k - n.f.a(this.d)) / 2, a + 50 + n.f.e);
        }
        if (this.k > 0) {
            if (super.n.length > 5) {
                n.e = super.n[5];
            }
            else {
                n.a(Color.black);
            }
            n.b(super.c - this.k, this.k, this.k, super.d - this.k);
            n.b(this.k, super.d - this.k, super.c - this.k * 2, this.k);
        }
    }
    
    static {
        a = new int[][] { { 192, 192, 192 }, { 255, 255, 255 }, { 192, 192, 192 }, { 255, 255, 255 }, { 0, 0, 0, 220 }, { 0, 0, 0, 128 } };
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        super.a(n, n2, n3, n4);
        if (this.b == 1) {
            this.h.a(super.a + (super.c - this.k) / 9, super.b + super.d - this.k - 40, (super.c - this.k) / 3, 20);
            this.i.a(super.a + (super.c - this.k) * 5 / 9, super.b + super.d - this.k - 40, (super.c - this.k) / 3, 20);
            return;
        }
        if (this.b == 2) {
            this.h.a(super.a + (super.c - this.k) / 9, super.b + super.d - this.k - 40, (super.c - this.k) * 7 / 9, 20);
        }
    }
    
    public void b(final k k) {
        super.b(k);
        if (this.h != null) {
            k.c(this.h);
        }
        if (this.i != null) {
            k.c(this.i);
        }
    }
    
    public void a(final k k, final b b, final String c, final String e, final String d, final int b2) {
        this.a(al.a);
        this.f = k.b.b("SansSerif", 1, 18);
        this.g = k.b.b("SansSerif", 1, 14);
        this.c = c;
        this.e = e;
        this.d = d;
        this.b = b2;
        if (this.b == 1) {
            this.h = new am(super.m, 20, b);
            this.h.b = k.b.a("DIALOG_BUTTON_YES");
            this.h.p = true;
            this.i = new am(super.m, 21, b);
            this.i.b = k.b.a("DIALOG_BUTTON_NO");
            this.i.p = true;
        }
        else if (this.b == 2) {
            this.h = new am(super.m, 20, b);
            this.h.b = this.d;
            this.h.p = true;
        }
        super.s = true;
    }
    
    public void a(final int n, final boolean b, final boolean b2) {
        if (this.n != null) {
            for (int i = 0; i < this.n.size(); ++i) {
                if ((char)this.n.elementAt(i) == n) {
                    this.m.d(20);
                    break;
                }
            }
        }
        if (this.o != null) {
            for (int j = 0; j < this.o.size(); ++j) {
                if ((char)this.o.elementAt(j) == n) {
                    this.m.d(21);
                    return;
                }
            }
        }
    }
}
