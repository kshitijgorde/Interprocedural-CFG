import java.awt.Rectangle;
import java.awt.Color;
import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ab extends l
{
    public static final int[] a;
    public WordFallApplet b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public int h;
    public int i;
    public int j;
    public boolean k;
    
    public void a(final int h) {
        this.b.ax.g = true;
        this.b.ax.h = h;
        this.b.ax.d = 400;
    }
    
    public void b() {
        super.b();
        if (this.b.k == null || this.b.k.ao > 0) {
            return;
        }
        boolean b = false;
        boolean b2 = false;
        if (this.b.k.z == 0) {
            final boolean l = this.b.x.l;
            final boolean b3 = !l && this.k();
            if (l || b3) {
                b = true;
            }
            if ((!this.g || this.h != 0) && l) {
                this.g = true;
                this.h = 0;
                this.d = 0;
                this.j();
                b2 = true;
            }
            if ((!this.g || this.h != 1) && b3) {
                this.g = true;
                this.h = 1;
                this.d = 0;
                this.j();
                b2 = true;
            }
            if (super.q % 4 == 0) {
                if (this.g && this.h == 1) {
                    this.k = true;
                }
                else {
                    this.k = false;
                }
                this.j();
            }
        }
        if (!b) {
            if (this.g) {
                if (this.d > 0) {
                    --this.d;
                    if (this.d == 0 && this.h == 1) {
                        this.f = Math.min(this.f + 1, ab.a.length - 1);
                        b2 = true;
                    }
                }
                if (this.d == 0) {
                    this.m();
                    this.j();
                }
            }
            else if (this.c == 0) {
                this.c = 2250;
            }
            if (!this.g && this.c > 0) {
                --this.c;
                if (this.c == 0) {
                    this.g = true;
                    this.h = 0;
                    this.d = 400;
                    this.j();
                }
            }
            if (!this.g && this.e > 0 && this.b.k != null && this.b.k.bf != null) {
                --this.e;
                if (this.e == 0 && this.b.k.q.k == 0) {
                    this.g = true;
                    this.h = 1;
                    this.d = 400;
                    this.j();
                }
            }
        }
        if (this.j != this.i && super.q % 2 == 0) {
            if (this.i > this.j) {
                ++this.j;
                if (this.j > this.i) {
                    this.j = this.i;
                }
            }
            else {
                --this.j;
                if (this.j < this.i) {
                    this.j = this.i;
                }
            }
            this.j();
        }
        if (b2) {
            this.e = ab.a[this.f];
        }
    }
    
    public void a(final n n) {
        if (this.b.f.w.j()) {
            n.a(this.b.f.w, 5, this.j);
        }
        if (this.b.k.bf != null) {
            final String upperCase = this.b.k.bf.toUpperCase();
            n.f = this.b.f.j;
            n.a(new Color(113, 163, 16));
            n.a(upperCase.toUpperCase(), 66 - n.f.a(upperCase) / 2, this.j + 16);
            n.a(new Color(189, 237, 16));
            n.a(upperCase.toUpperCase(), 65 - n.f.a(upperCase) / 2, this.j + 15);
            final aq s = this.b.k.s;
            if (s != null && s.a != null) {
                final String a = s.a(upperCase);
                if (a.length() > 0) {
                    n.a(new Color(255, 229, 55));
                    n.a(a.toUpperCase(), 65 - n.f.a(upperCase) / 2, this.j + 15);
                }
            }
        }
        if (this.b.k.z == 0) {
            if (this.g) {
                j x = null;
                switch (this.h) {
                    case 0: {
                        x = this.b.f.a[20];
                        break;
                    }
                    case 1: {
                        x = this.b.f.x;
                        break;
                    }
                }
                if (x != null && x.j()) {
                    n.a(x, 0, 70);
                }
            }
            if (this.k && this.j != this.b.j) {
                final double n2 = -(super.q % 48 / 48.0) * 6.28318;
                final double n3 = Math.cos(n2) * 2.5;
                final double n4 = 100.0 - Math.cos(n2) * 2.5;
                final double n5 = this.j - 38 + Math.cos(n2) * 2.5;
                if (this.b.f.y.j()) {
                    n.a(this.b.f.y, (int)n3, this.j);
                }
                if (this.b.f.z.j()) {
                    n.a(this.b.f.z, (int)n4, this.j);
                }
                if (this.b.f.aa.j()) {
                    n.a(this.b.f.aa, 48, (int)n5);
                }
            }
        }
    }
    
    public ab(final WordFallApplet b) {
        this.b = b;
        super.g = false;
        super.r = true;
    }
    
    public boolean k() {
        boolean b = false;
        final k h = this.b.h;
        final w k = this.b.k;
        if (h != null && k != null && k.bf != null) {
            final j w = this.b.f.w;
            if (w.j()) {
                b = new Rectangle(5, this.j, w.h(), w.c()).contains(h.p, h.q);
            }
            else {
                final int a = this.b.f.j.a(this.b.k.bf.toUpperCase());
                b = new Rectangle(65 - a / 2, this.j, a, this.b.f.j.d).contains(h.p, h.q);
            }
        }
        return b;
    }
    
    public void l() {
        if (this.g) {
            switch (this.h) {
                case 0: {
                    this.c = 2250;
                }
                case 1: {
                    this.e = ab.a[this.f];
                }
            }
        }
        else {
            this.c = 2250;
            this.e = ab.a[this.f];
        }
    }
    
    static {
        a = new int[] { 1500, 3000, 9000, 15000 };
    }
    
    public void m() {
        this.l();
        this.g = false;
        this.k = false;
    }
}
