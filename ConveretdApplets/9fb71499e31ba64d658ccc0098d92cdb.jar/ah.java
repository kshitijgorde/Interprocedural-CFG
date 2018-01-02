import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ah extends aa
{
    public Color a;
    public Color b;
    public int c;
    public int d;
    public Rectangle e;
    public f f;
    
    public void a() {
        super.a();
        this.g();
    }
    
    public void a(final n n) {
        final int n2 = (super.c - super.c.a(super.b)) / 2;
        final int n3 = (super.d + super.c.e) / 2 - 1;
        if (this.e != null && this.f != null) {
            n.e = this.f;
            n.b(this.e.x, this.e.y, this.e.width, this.e.height);
        }
        if (super.l) {
            n.a(this.b);
        }
        else {
            n.a(this.a);
        }
        n.f = super.c;
        n.a(super.b, n2, n3);
        for (int i = 0; i < this.c; ++i) {
            n.b(n2, n3 + this.d + i, super.c.a(super.b), this.c);
        }
    }
    
    public ah(final k k, final int n, final b b) {
        super(k, n, b);
        if (super.c == null) {
            super.c = k.b.b("SansSerif", 0, 12);
        }
        this.a = new Color(255, 255, 255);
        this.b = new Color(255, 255, 255);
        super.p = true;
        this.d = 3;
        this.c = 1;
        this.e = null;
        this.f = null;
    }
    
    public void d() {
        super.d();
        this.g();
    }
}
