import java.awt.Color;
import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class z extends l
{
    public WordFallApplet a;
    public w b;
    
    public void b() {
        super.b();
        if (this.b.ao > 0) {
            return;
        }
        if (super.c <= 0 || super.d <= 0) {
            final j j = this.a.f.a[18];
            this.a(super.a, super.b, j.h(), j.c());
            this.j();
        }
        ++super.q;
        if (super.i) {
            return;
        }
        this.a.h.d(this);
    }
    
    public void a(final n n) {
        n.a(this.a.f.a[18], 0, 0);
        this.b(n);
        if (this.a.k.a6 > 0.0) {
            final int n2 = (int)(this.a.k.a6 * 255.0);
            n.i = 1;
            n.e = new f(n2, n2, n2);
            n.b(0, 0, super.c, super.d);
            n.i = 0;
        }
    }
    
    public z(final WordFallApplet a) {
        super.s = true;
        this.a = a;
        this.b = this.a.k;
    }
    
    public void b(final n n) {
        final String string = "" + this.b.ak;
        n.f = this.a.f.l;
        n.a(new Color(60, 35, 15));
        n.a(string, 61 - n.f.a(string) / 2, 68);
    }
}
