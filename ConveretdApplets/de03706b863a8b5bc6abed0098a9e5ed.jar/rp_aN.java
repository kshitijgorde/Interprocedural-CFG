import java.util.Vector;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_aN extends rp_dC
{
    private int a;
    private int b;
    private int c;
    private int d;
    private Rectangle a;
    
    public rp_aN(final int h, final Rectangle rectangle) {
        this.a = null;
        this.h = h;
        this.a(rectangle);
        this.a(1, false);
        this.a(524288, true);
    }
    
    public rp_aN(final int h, final int a, final int b, final int c, final int d) {
        this.a = null;
        this.h = h;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.a(1, false);
        this.a(524288, true);
    }
    
    public final void a(final Rectangle rectangle) {
        this.a = rectangle.x;
        this.b = rectangle.y;
        this.c = rectangle.x + rectangle.width;
        this.d = rectangle.y + rectangle.height;
        this.a = null;
    }
    
    public final Rectangle a(final int n) {
        if (n == 0 && this.a != null) {
            return new Rectangle(this.a);
        }
        final Rectangle rectangle;
        (rectangle = new Rectangle()).setLocation(this.a, this.b - 40000);
        rectangle.add(this.c + 40000, this.d);
        return rectangle;
    }
    
    public final Object clone() {
        return this = (rp_aN)super.clone();
    }
    
    public final int a() {
        return 10;
    }
    
    public final Point a() {
        return new Point((this.a + this.c) / 2, (this.b + this.d) / 2);
    }
    
    public final int b() {
        return 0;
    }
    
    public final int c() {
        return 0;
    }
    
    public final boolean a(final Frame frame) {
        return false;
    }
    
    public final int a(final int n, final int n2) {
        return 0;
    }
    
    public final int d() {
        return 115;
    }
    
    public final Cursor a(final int n, final int n2) {
        return null;
    }
    
    public final void a(final int n, final int n2, final double n3) {
    }
    
    public final void a(final int n, final int n2, final double n3, final boolean b) {
    }
    
    public final void a(final int n, final int n2) {
    }
    
    public final void b(final int n, final int n2) {
        this.a += n;
        this.b += n2;
        this.c += n;
        this.d += n2;
        this.a = null;
    }
    
    public final void a(final rp_eS rp_eS, final rp_eP rp_eP) {
        final rp_cR a2;
        final boolean a = (a2 = rp_eP.a()).a(true);
        final boolean b = a2.b(false);
        (this.a = new Rectangle()).setLocation(this.a, this.b - 20000);
        this.a.add(this.c + 20000, this.d);
        rp_eS.a(Color.red);
        rp_eS.a(this.a, this.b, this.a, this.b - 20000);
        rp_eS.a(this.c, this.b, this.c, this.b - 20000);
        rp_eS.a(this.a, this.b - 20000, this.c, this.b - 20000);
        this.a(rp_eS, a2, Math.abs(this.c - this.a), (this.a + this.c) / 2, this.b - 10000);
        rp_eS.a(Color.red);
        rp_eS.a(this.c, this.b, this.c + 20000, this.b);
        rp_eS.a(this.c, this.d, this.c + 20000, this.d);
        rp_eS.a(this.c + 20000, this.b, this.c + 20000, this.d);
        this.a(rp_eS, a2, Math.abs(this.d - this.b), this.c + 20000, (this.d + this.b) / 2);
        a2.b(b);
        a2.a(a);
    }
    
    private void a(final rp_eS rp_eS, final rp_cR rp_cR, final int n, final int n2, final int n3) {
        final String a = rp_cR.a(n);
        if (!rp_eS.a()) {
            final Rectangle a2 = rp_eS.a(a, n2, n3, 1, 2);
            rp_eS.b(Color.white);
            rp_eS.c(a2.x, a2.y, a2.width, a2.height);
            if (this.a != null) {
                this.a.add(a2);
            }
        }
        rp_eS.a(Color.red);
        rp_eS.a(a, n2, n3, 1, 2);
        rp_eS.a(Color.black);
    }
    
    public final void a(final StringBuffer sb, final rp_eP rp_eP) {
        sb.append("TYPE=DIM\n");
        sb.append("START:[");
        sb.append(this.a);
        sb.append(';');
        sb.append(this.b);
        sb.append("]\n");
        sb.append("END:[");
        sb.append(this.c);
        sb.append(';');
        sb.append(this.d);
        sb.append("]\n");
    }
    
    public final void a(final rp_eg rp_eg) {
        final Vector<String> vector;
        (vector = new Vector<String>()).addElement(rp_eg.a("id", Integer.toString(this.h)));
        vector.addElement(rp_eg.a("x1", Integer.toString(this.a)));
        vector.addElement(rp_eg.a("y1", Integer.toString(this.b)));
        vector.addElement(rp_eg.a("x2", Integer.toString(this.c)));
        vector.addElement(rp_eg.a("y2", Integer.toString(this.d)));
        rp_eg.a("dim", vector);
        rp_eg.a();
    }
    
    public final String a() {
        return "";
    }
    
    public final void a(final String s) {
    }
    
    public final String b() {
        return null;
    }
    
    public final void b(final String s) {
    }
}
