import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class am extends aa
{
    public static final int[][] a;
    public int b;
    public int c;
    public boolean d;
    
    public void a() {
        super.a();
        this.g();
    }
    
    public void a(final n n) {
        n.f = super.c;
        if (!this.d) {
            if (super.k && super.l) {
                n.e = super.n[3];
            }
            else if (super.k || super.l) {
                n.e = super.n[2];
            }
            else {
                n.e = super.n[1];
            }
            n.b(0, 0, super.c, super.d);
        }
        if (super.k && super.l && super.n.length > 6) {
            n.e = super.n[6];
        }
        else if ((super.k || super.l) && super.n.length > 5) {
            n.e = super.n[5];
        }
        else {
            n.e = super.n[4];
        }
        if (super.b != null) {
            final Rectangle rectangle = new Rectangle(0, 0, super.c, super.d);
            final int a = this.a(n, super.c, super.b, this.b);
            rectangle.y = (super.d - a) / 2;
            rectangle.height = a;
            this.a(n, rectangle, super.b, this.b, 0);
        }
        if (super.n[0] != null) {
            n.e = super.n[0];
            for (int i = 0; i < this.c; ++i) {
                n.c(i, i, super.c - i * 2 - 1, super.d - i * 2 - 1);
            }
        }
    }
    
    public am(final k k, final int n, final b b) {
        super(k, n, b);
        this.b = -1;
        this.c = 2;
        this.a(am.a);
        super.c = k.b.b("SansSerif", 1, 14);
    }
    
    static {
        a = new int[][] { { 192, 192, 192 }, new int[3], { 64, 64, 64 }, { 128, 128, 128 }, { 255, 255, 255 } };
    }
    
    public void d() {
        super.d();
        this.g();
    }
}
