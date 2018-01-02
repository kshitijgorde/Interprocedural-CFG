import java.net.URL;
import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ar extends l implements b
{
    public WordFallApplet a;
    public int b;
    public boolean c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public double i;
    public int j;
    public am k;
    public am l;
    public static int m;
    public static int n;
    public static final int[][] o;
    public static final int[] p;
    public j[] q;
    public String[] r;
    public static final int[] s;
    
    public void a(final k k) {
        super.a(k);
        k.f(this.k);
        k.f(this.l);
    }
    
    public void b() {
        super.b();
        if (super.q % 8 == 0) {
            this.j = (this.j + 5) % 6;
            this.j();
        }
        if (this.c) {
            if (this.b < 33) {
                ++this.b;
            }
            if (this.b == 33) {
                this.c = false;
                this.k.b(true);
                this.l.b(true);
            }
            this.j();
            return;
        }
        if (this.d < 255) {
            this.d += 15;
            if (this.d > 255) {
                this.d = 255;
            }
            this.j();
            return;
        }
        this.e -= 8;
        if (this.e <= 228) {
            this.e = 228;
        }
        this.f += 8;
        if (this.f > 10) {
            this.f = 10;
        }
        if (--this.g <= 0) {
            this.h = (this.h + 1) % 3;
            this.i = 0.0;
            this.g = 200;
        }
        if (this.i < 1.0) {
            this.i += 0.04;
            if (this.i >= 1.0) {
                this.i = 1.0;
            }
        }
        this.j();
    }
    
    public void a(final n n) {
        n.e = new f(0, 0, 0);
        n.b(0, 0, super.c, 134);
        n.e = new f(0, 0, 0);
        n.b(0, 134, super.c, 171);
        n.e = new f(123, 74, 8);
        n.b(0, -10, super.c, this.b);
        n.e = new f(123, 74, 8);
        n.b(0, 330 - (int)(this.b * 0.85), super.c, this.b);
        n.e = new f(61, 37, 4);
        n.b(0, this.b - 10, super.c, 2);
        n.e = new f(61, 37, 4);
        n.b(0, 418 - (int)(this.b * 0.85), super.c, 2);
        if (this.d > 0 && this.d < 255) {
            n.d = true;
            n.e = new f(255, 255, 255, this.d);
            n.a(this.a.f.n[0], 0, 26);
            n.d = false;
            return;
        }
        if (this.d == 255) {
            n.a(this.a.f.n[0], 0, 26);
            n.f = this.a.f.j;
            n.e = new f(247, 206, 8);
            n.a(this.r[2], 8, 16);
            final String s = this.r[3];
            n.a(s, super.c - n.f.a(s) - 8, 16);
            n.a(this.a.f.n[1], this.e, 140);
            int n2 = 0;
            do {
                n.a(this.q[ar.s[(n2 * 2 + this.j) % 6]], this.e - 18, ar.p[n2]);
            } while (++n2 < 7);
            n.e = new f(61, 37, 4);
            n.b(this.f - 4, 136, 201, 160);
            if (this.i != 0.0) {
                n.a(this.a.f.n[2 + (this.h + 2) % 3], this.f, 140);
            }
            n.d = true;
            n.e = new f(255, 255, 255, (int)(this.i * 255.0));
            n.a(this.a.f.n[2 + this.h], this.f, 140);
            n.d = false;
        }
    }
    
    public ar(final WordFallApplet a) {
        super(a.h);
        this.g = 100;
        this.i = 1.0;
        this.q = new j[4];
        this.r = new String[] { "Click to download FREE TRIAL!", "Click to return to game", "Love BookWorm?", "Play it offline, anytime!" };
        this.a = a;
        this.d = 0;
        this.b = 0;
        this.f = -250;
        this.c = true;
        this.e = 228;
        this.k = new am(super.m, ar.m, this);
        this.k.c = 2;
        this.k.p = true;
        this.k.c = this.a.f.j;
        this.k.b = this.r[0];
        this.k.a(ar.o);
        this.k.a(4, 306, 201, 21);
        this.k.b(false);
        this.l = new am(super.m, ar.n, this);
        this.l.c = 2;
        this.l.p = true;
        this.l.c = this.a.f.j;
        this.l.b = this.r[1];
        this.l.a(ar.o);
        this.l.a(207, 306, 201, 21);
        this.l.b(false);
        this.q[0] = this.a.f.n[5];
        int n = 1;
        do {
            this.q[n] = this.a.f.a(this.q[0], (8.0 - n) / 8.0);
        } while (++n < 4);
    }
    
    public void a(final int n) {
        this.a.c(21);
    }
    
    public void b(final int n) {
    }
    
    static {
        ar.m = 0;
        ar.n = 1;
        o = new int[][] { { 213, 164, 8 }, { 123, 74, 8 }, { 123, 74, 8 }, { 123, 74, 8 }, { 200, 200, 200 }, { 255, 255, 255 }, { 255, 255, 255 } };
        p = new int[] { 143, 165, 186, 209, 232, 253, 274 };
        s = new int[] { 0, 1, 2, 3, 2, 1 };
    }
    
    public void d(final int n) {
        if (n == ar.n) {
            this.a.h();
        }
        if (n == ar.m) {
            this.a.d("Ad");
            this.a.f("Clicked");
            this.a.s();
            try {
                String parameter = this.a.getParameter("adUrl");
                if (parameter == null) {
                    parameter = "http://www.popcap.com/bookworm.php";
                }
                this.a.getAppletContext().showDocument(new URL(parameter), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void b(final k k) {
        super.b(k);
        k.c(this.k);
        k.c(this.l);
    }
    
    public void f(final int n) {
    }
    
    public void g(final int n) {
    }
}
