import java.util.Vector;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cw extends rp_dC
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    private int f;
    private int g;
    private int i;
    private int j;
    private int k;
    private Rectangle a;
    private Rectangle b;
    private double a;
    private int l;
    private int m;
    private int n;
    private int o;
    
    public rp_cw(final int h, final int a, final int b, final int c, final int d) {
        this.e = 0;
        this.f = 0;
        this.a = new Rectangle();
        this.b = new Rectangle();
        this.h = h;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = 2;
        this.a(1, true);
        this.a(262144, true);
        this.a(16, true);
        this.a.setBounds((a + c) / 2, (b + d) / 2, 9000, 9000);
        this.b.setBounds((a + c) / 2, (b + d) / 2, 9000, 9000);
    }
    
    public rp_cw() {
        this.e = 0;
        this.f = 0;
        this.a = new Rectangle();
        this.b = new Rectangle();
        final boolean b = false;
        this.d = (b ? 1 : 0);
        this.c = (b ? 1 : 0);
        this.b = (b ? 1 : 0);
        this.a = (b ? 1 : 0);
        this.a(1, true);
        this.a(262144, true);
        this.a(16, true);
    }
    
    public final int d() {
        return 115;
    }
    
    public final void a(final rp_fx rp_fx, final Point point) {
        if (this.e == 0) {
            return;
        }
        this.c = point.x;
        this.d = point.y;
        final Rectangle rectangle;
        (rectangle = new Rectangle(this.a(0))).add(this.a, this.b);
        rectangle.add(this.c, this.d);
        rp_fx.a(rectangle);
    }
    
    public final Object clone() {
        final rp_cw rp_cw;
        (rp_cw = (rp_cw)super.clone()).a = new Rectangle(this.a);
        rp_cw.b = new Rectangle(this.b);
        return rp_cw;
    }
    
    public final int a() {
        return 10;
    }
    
    public final void a(final int n, final int n2, final int n3) {
        if (n == 1 || n == 3) {
            this.a += n2;
            this.b += n3;
        }
        if (n == 2 || n == 3) {
            this.c += n2;
            this.d += n3;
        }
    }
    
    public final Rectangle a(final int n) {
        if (n == 0) {
            return new Rectangle(this.b);
        }
        return new Rectangle(this.a);
    }
    
    public final Point a() {
        return new Point((this.a + this.c) / 2, (this.b + this.d) / 2);
    }
    
    public final int b() {
        return 10;
    }
    
    public final int c() {
        if (this.a == this.c) {
            return Math.abs(this.d - this.b);
        }
        return Math.abs(this.c - this.a);
    }
    
    public final boolean a(final Frame frame) {
        return false;
    }
    
    public final int a(final int n, final int n2) {
        if (rp_C.a(this.a, this.b, n, n2) < 15000.0) {
            return 4;
        }
        if (rp_C.a(this.c, this.d, n, n2) < 15000.0) {
            return 4;
        }
        if (this.a.contains(n, n2)) {
            return 2;
        }
        return 0;
    }
    
    public final Cursor a(final int n, final int n2) {
        final int a;
        if (((a = this.a(n, n2)) & 0x2) != 0x0 || (a & 0x4) != 0x0) {
            return Cursor.getPredefinedCursor(12);
        }
        return Cursor.getPredefinedCursor(1);
    }
    
    public final void a(final int n, final int n2, final double n3) {
    }
    
    public final void a(final int n, final int n2, final double n3, final boolean b) {
    }
    
    public final void a(final int n, final int n2) {
        if (this.f == 1 || this.f == 2) {
            this.j = this.g + n;
            this.k = this.i + n2;
            return;
        }
        this.b(n, n2);
    }
    
    public final void b(final int n, final int n2) {
        this.a += n;
        this.b += n2;
        this.c += n;
        this.d += n2;
        this.a.add(this.a, this.b);
        this.a.add(this.c, this.d);
        this.b.add(this.a, this.b);
        this.b.add(this.c, this.d);
    }
    
    public final void a(final rp_N rp_N, final int n, final int n2) {
        final boolean b = false;
        this.k = (b ? 1 : 0);
        this.j = (b ? 1 : 0);
        final boolean b2 = false;
        this.i = (b2 ? 1 : 0);
        this.g = (b2 ? 1 : 0);
        if (rp_C.a(this.a, this.b, n, n2) < 15000.0) {
            this.f = 1;
            return;
        }
        if (rp_C.a(this.c, this.d, n, n2) < 15000.0) {
            this.f = 2;
            return;
        }
        this.f = 3;
    }
    
    public final rp_dt a(final boolean b) {
        rp_dt rp_dt = null;
        if (b) {
            rp_dt = new rp_dN(this, this.f, this.j, this.k);
        }
        this.f = 0;
        return rp_dt;
    }
    
    public final void a(final int n, final int n2, final Dimension dimension, final boolean b) {
        this.g += n;
        this.i += n2;
    }
    
    public final void a(final rp_eS rp_eS, final rp_eP rp_eP) {
        if (this.e == 0) {
            return;
        }
        ((Graphics2D)rp_eS.a()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.a(2) && !rp_eS.a) {
            rp_eS.a(rp_aJ.j);
        }
        else {
            rp_eS.a(Color.black);
        }
        int a = this.a;
        int b = this.b;
        int c = this.c;
        int d = this.d;
        if (this.f == 1 || this.f == 3) {
            a += this.j;
            b += this.k;
        }
        if (this.f == 2 || this.f == 3) {
            c += this.j;
            d += this.k;
        }
        rp_eS.a(a, b, c, d);
        if (rp_eS.a()) {
            return;
        }
        final int n = a;
        final int n2 = b;
        final int n3 = c;
        final int n4 = d;
        final int n5 = n3;
        final int n6 = n2;
        final double n7 = n5 - n;
        final double n8 = n4 - n6;
        this.a = rp_C.a(n7, n8);
        this.n = (int)(0.5 + n7 * 27000.0 / this.a);
        this.o = (int)(0.5 + n8 * 27000.0 / this.a);
        this.l = -(int)(0.5 + n8 * 9000.0 / this.a);
        this.m = (int)(0.5 + n7 * 9000.0 / this.a);
        if (this.a > 54000.0) {
            rp_eS.a(c - this.n + this.l, d - this.o + this.m, c, d);
            rp_eS.a(c - this.n - this.l, d - this.o - this.m, c, d);
            rp_eS.a(a + this.n + this.l, b + this.o + this.m, a, b);
            rp_eS.a(a + this.n - this.l, b + this.o - this.m, a, b);
            final rp_cR a3;
            final boolean a2 = (a3 = rp_eP.a()).a(true);
            final boolean b2 = a3.b(false);
            final String a4 = a3.a((int)this.a);
            a3.b(b2);
            a3.a(a2);
            final Rectangle a5 = rp_eS.a(a4, (a + c) / 2, (b + d) / 2, 1, 2);
            if (this.a(2) && !rp_eS.a) {
                rp_eS.b(rp_aJ.k);
            }
            else {
                rp_eS.b(Color.white);
            }
            rp_eS.c(a5.x, a5.y, a5.width, a5.height);
            if (this.a(2) && !rp_eS.a) {
                rp_eS.a(rp_aJ.j);
            }
            else {
                rp_eS.a(Color.black);
            }
            rp_eS.a(a4, (a + c) / 2, (b + d) / 2, 1, 2);
            rp_eS.a(Color.black);
            this.a.setBounds(a5);
            this.b.setBounds(a5);
            this.b.add(a, b);
            this.b.add(c, d);
            this.b.add(c - this.n + this.l, d - this.o + this.m);
            this.b.add(c - this.n - this.l, d - this.o - this.m);
            this.b.add(a + this.n + this.l, b + this.o + this.m);
            this.b.add(a + this.n - this.l, b + this.o - this.m);
            return;
        }
        this.a.setBounds((a + c) / 2, (b + d) / 2, 9000, 9000);
        this.b.setBounds(this.a);
    }
    
    public final void a(final StringBuffer sb, final rp_eP rp_eP) {
        sb.append("TYPE=TAPE DIM\n");
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
        rp_eg.a("tape-dim", vector);
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
