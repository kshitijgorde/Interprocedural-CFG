import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dd extends rp_cf
{
    private static final String[] a;
    private Vector a;
    private Rectangle b;
    private rp_fa[] a;
    private Polygon a;
    private rp_aJ a;
    private Point a;
    private Point b;
    private Point c;
    private boolean a;
    private boolean b;
    private int b;
    private double a;
    private double b;
    private Vector b;
    private int c;
    private int d;
    private int e;
    private Rectangle c;
    
    public static void a(final rp_aJ rp_aJ) {
        final rp_fK a = rp_aJ.a();
        for (int i = 0; i < 7; ++i) {
            final Image a2;
            if ((a2 = a.a("res/edit_" + rp_dd.a[i], true)) != null) {
                rp_aJ.a(rp_dd.a[i], a2);
            }
        }
    }
    
    public rp_dd(final rp_aJ a, final rp_dC rp_dC, final Rectangle b, final boolean b2, final Object o) {
        super(null);
        this.a = new rp_fa[7];
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = false;
        this.b = false;
        this.b = -1;
        this.b = 0.0;
        this.c = new Rectangle();
        this.a = a;
        (this.a = new Vector()).add(rp_dC);
        this.b = b;
        if (b2) {
            this.a = 1;
        }
        this.a();
    }
    
    public rp_dd(final rp_aJ a, final rp_dC[] array, final Rectangle b, final Object o) {
        super(null);
        this.a = new rp_fa[7];
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = false;
        this.b = false;
        this.b = -1;
        this.b = 0.0;
        this.c = new Rectangle();
        this.a = a;
        this.a = 1;
        this.a = new Vector(array.length);
        for (int i = 0; i < array.length; ++i) {
            this.a.add(array[i]);
        }
        this.b = b;
        this.a();
    }
    
    public final int a() {
        return this.a.size();
    }
    
    public final rp_dC a(final int n) {
        return this.a.elementAt(n);
    }
    
    public final boolean a(final rp_eV rp_eV) {
        Rectangle rectangle = null;
        for (int i = 0; i < this.a.size(); ++i) {
            final Rectangle a;
            if ((a = this.a.elementAt(i).a(1)) != null) {
                if (rectangle == null) {
                    rectangle = a;
                }
                else {
                    rectangle.add(a);
                }
            }
        }
        if (rectangle != null) {
            this.a(rp_eV.a(rectangle));
        }
        return true;
    }
    
    public final Rectangle a() {
        return new Rectangle(this.c);
    }
    
    private Point a(final int n) {
        switch (n) {
            case 6: {
                return new Point(this.b.x, this.b.y + this.b.height);
            }
            case 0: {
                return new Point(this.b.x, this.b.y);
            }
            case 1: {
                return new Point(this.b.x + this.b.width / 2, this.b.y - (int)(this.b.width * 0.1));
            }
            case 2: {
                return new Point(this.b.x + this.b.width, this.b.y);
            }
            case 3: {
                return new Point(this.b.x + this.b.width, this.b.y + this.b.height);
            }
            case 4:
            case 5: {
                return new Point(this.b.x + this.b.width / 2, this.b.y + this.b.height + (int)(this.b.width * 0.1));
            }
            default: {
                return new Point(0, 0);
            }
        }
    }
    
    public final void a(final Rectangle bounds) {
        this.b.setBounds(bounds);
        this.a();
    }
    
    private void a() {
        final int max = Math.max(Math.max(this.b.width, this.b.height), 30);
        if (this.b.width < max) {
            this.b.grow((max - this.b.width) / 2, 0);
        }
        if (this.b.height < max) {
            this.b.grow(0, (max - this.b.height) / 2);
        }
        int n = -1;
        if (this.a.size() > 1) {
            n = -57;
        }
        for (int i = 0; i < this.a.size(); ++i) {
            n &= ~((rp_dC)this.a.elementAt(i)).d();
        }
        if ((n & 0x10) != 0x0 && (n & 0x40) != 0x0) {
            n = n;
        }
        final int n2 = n;
        this.b.grow(4, 4);
        final int[] array = new int[7];
        final int[] array2 = new int[7];
        int max2 = 0;
        int max3 = 0;
        int n3 = 0;
        for (int j = 0; j < 7; ++j) {
            this.a[j] = null;
            if ((j != 5 || 0x0 != (n2 & 0x10)) && (j != 4 || 0x0 != (n2 & 0x40))) {
                final Point a = this.a(j);
                array[n3] = a.x;
                array2[n3] = a.y;
                ++n3;
                if (j == 1) {
                    final Point point = a;
                    point.y -= 8;
                }
                final int n4 = n2;
                int n5 = 0;
                switch (j) {
                    case 0: {
                        n5 = 1;
                        break;
                    }
                    case 1: {
                        n5 = 2;
                        break;
                    }
                    case 2: {
                        n5 = 4;
                        break;
                    }
                    case 3: {
                        n5 = 8;
                        break;
                    }
                    case 6: {
                        n5 = 32;
                        break;
                    }
                    case 5: {
                        n5 = 16;
                        break;
                    }
                    case 4: {
                        n5 = 64;
                        break;
                    }
                    default: {
                        n5 = 0;
                        break;
                    }
                }
                if ((n4 & n5) != 0x0) {
                    this.a[j] = new rp_fa(a, "ht_" + j, j, this.a.a(rp_dd.a[j]));
                    max2 = Math.max(max2, this.a[j].a());
                    max3 = Math.max(max3, this.a[j].b());
                }
            }
        }
        this.a = new Polygon(array, array2, n3);
        this.c.setBounds(this.a.getBounds());
        this.c.grow(max2, max3);
        Rectangle rectangle = null;
        for (int k = 0; k < this.a.size(); ++k) {
            final Rectangle a2;
            if ((a2 = this.a.elementAt(k).a(0)) != null) {
                if (rectangle == null) {
                    rectangle = new Rectangle(a2);
                }
                else {
                    rectangle.add(a2);
                }
            }
        }
        final rp_fx a3;
        if (rectangle != null && (a3 = this.a.a().a()) != null) {
            this.c.add(a3.a().a(rectangle));
        }
    }
    
    public final void a(final Graphics graphics) {
        final BasicStroke stroke = new BasicStroke(1.0f);
        final Graphics2D graphics2D;
        final Stroke stroke2 = (graphics2D = (Graphics2D)graphics).getStroke();
        graphics2D.setStroke(stroke);
        graphics2D.setColor(new Color(223, 223, 223));
        graphics.drawPolygon(this.a);
        graphics2D.setStroke(stroke2);
        graphics2D.setColor(Color.BLACK);
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                this.a[i].a(graphics);
            }
        }
        if (this.b == 5) {
            final rp_dC rp_dC = this.a.elementAt(0);
            final rp_cR a = this.a.a();
            final String string = this.a.a().a(0, "W") + ": " + a.a(rp_dC.b());
            final String string2 = this.a.a().a(0, "D") + ": " + a.a(rp_dC.c());
            final Point a2 = this.a(5);
            graphics.setColor(Color.white);
            graphics.fillRect(a2.x - 54, a2.y - 56, 108, 40);
            graphics.setColor(Color.red);
            graphics.drawString(string, a2.x - 50, a2.y - 40);
            graphics.drawString(string2, a2.x - 50, a2.y - 20);
        }
    }
    
    public final String a(final Point point) {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].a(point)) {
                return this.a[i].a;
            }
        }
        if (this.a.contains(point)) {
            return "ht_box";
        }
        return null;
    }
    
    public final boolean a(final Point point, final boolean b) {
        if (!b && this.a.contains(point)) {
            return true;
        }
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].a(point)) {
                return true;
            }
        }
        return false;
    }
    
    public final void a(final int n, final int n2) {
        this.b.translate(n, n2);
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                this.a[i].a.translate(n, n2);
            }
        }
        this.a.translate(n, n2);
        this.c.translate(n, n2);
    }
    
    public final boolean d(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].a(mouseEvent.getPoint())) {
                final rp_dd rp_dd = this;
                final int a = this.a[i].a;
                this = rp_dd;
                final rp_fx a2 = rp_dd.a.a().a();
                switch (a) {
                    case 6: {
                        this.a.elementAt(0).a(this.a.a().a());
                        a2.f();
                        break;
                    }
                    case 3: {
                        final rp_dC[] array = new rp_dC[this.a.size()];
                        for (int j = 0; j < this.a.size(); ++j) {
                            array[j] = (rp_dC)this.a.elementAt(j);
                        }
                        a2.a.a(array);
                        break;
                    }
                    case 2: {
                        final rp_dC[] array2 = new rp_dC[this.a.size()];
                        for (int k = 0; k < this.a.size(); ++k) {
                            array2[k] = (rp_dC)this.a.elementAt(k);
                        }
                        a2.a.b(array2);
                        break;
                    }
                    case 1: {
                        boolean b = false;
                        final rp_dC[] array3 = new rp_dC[this.a.size()];
                        for (int l = 0; l < this.a.size(); ++l) {
                            array3[l] = (rp_dC)this.a.elementAt(l);
                            if (array3[l].e() != -1) {
                                b = true;
                                break;
                            }
                        }
                        if (b) {
                            a2.a.d(array3);
                            break;
                        }
                        a2.a.c(array3);
                        break;
                    }
                    case 0: {
                        this.a((double)(mouseEvent.isControlDown() ? 5 : 90));
                    }
                    case 4: {
                        a2.a(this.a.elementAt(0));
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public final boolean c(final MouseEvent mouseEvent) {
        this.a = true;
        this.b = false;
        this.b = -1;
        this.a = 0.0;
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].a(mouseEvent.getPoint())) {
                final rp_ew a = this.a.a().a().a();
                this.c = a.a;
                this.b = i;
                Label_0399: {
                    if (this.b == 0) {
                        this.a = mouseEvent.getPoint();
                        int n = 0;
                        double b = 0.0;
                        while (true) {
                            for (int j = 0; j < this.a.size(); ++j) {
                                final rp_dC rp_dC;
                                if ((rp_dC = this.a.elementAt(j)) instanceof rp_eF) {
                                    final double a2 = ((rp_eF)rp_dC).a;
                                    if (n != 0) {
                                        double n2;
                                        for (n2 = a2 - b; n2 < 0.0; n2 += 360.0) {}
                                        while (n2 > 360.0) {
                                            n2 -= 360.0;
                                        }
                                        if (n2 >= 0.1 && n2 <= 359.9 && (n2 >= 90.1 || n2 <= 89.9) && (n2 >= 180.1 || n2 <= 179.9) && (n2 >= 270.1 || n2 <= 269.9)) {
                                            this.b = new Point(this.b.x + this.b.width / 2, this.b.y + this.b.height / 2);
                                            this.c = a.c(this.b);
                                            break Label_0399;
                                        }
                                    }
                                    else {
                                        b = a2;
                                        n = 1;
                                    }
                                }
                            }
                            if (n != 0) {
                                this.b = b;
                            }
                            continue;
                        }
                    }
                }
                if (this.b == 5) {
                    this.a = mouseEvent.getPoint();
                    final rp_dC rp_dC2 = this.a.elementAt(0);
                    this.d = rp_dC2.b();
                    this.e = rp_dC2.c();
                }
                return true;
            }
        }
        return false;
    }
    
    public final boolean a(final MouseEvent mouseEvent) {
        if (this.a && this.a != null) {
            if (this.b == 0) {
                if (!this.b) {
                    this.b = new Vector(this.a.size());
                    for (int i = 0; i < this.a.size(); ++i) {
                        this.b.add(new Point(((rp_dC)this.a.elementAt(i)).a()));
                    }
                    this.b = true;
                }
                final double a;
                if ((a = this.a(mouseEvent.getPoint(), true)) != this.a) {
                    for (int j = 0; j < this.a.size(); ++j) {
                        final Point point = this.b.elementAt(j);
                        this.a.elementAt(j).a(point.x, point.y, 0.0, true);
                        ((rp_dC)this.a.elementAt(j)).a(this.c.x, this.c.y, a, false);
                    }
                    this.a = a;
                    return true;
                }
            }
            if (this.b == 5) {
                this.b = true;
                final int n = mouseEvent.getPoint().x - this.a.x;
                final int n2 = mouseEvent.getPoint().y - this.a.y;
                final int n3 = this.c * n;
                final int n4 = this.c * n2;
                final rp_eF rp_eF;
                final double n5;
                final double sin = Math.sin((n5 = -(rp_eF = this.a.elementAt(0)).a) * 0.017453292519943295);
                final double cos = Math.cos(n5 * 0.017453292519943295);
                final int n6 = (int)Math.round(n3 * cos - n4 * sin);
                final int n7 = (int)Math.round(n4 * cos + n3 * sin);
                int max = Math.max(10000, this.d + n6);
                int max2 = Math.max(10000, this.e + n7);
                if (mouseEvent.isShiftDown()) {
                    final double n8 = this.e / this.d;
                    final int n9;
                    if ((n9 = (int)(max * n8 + 0.5)) > 10000) {
                        max2 = n9;
                    }
                    else {
                        max2 = 10000;
                        max = (int)(10000 / n8 + 0.5);
                    }
                    Math.min(n6, n7);
                }
                rp_eF.a(max, max2);
                final Rectangle a2;
                (a2 = this.a.a().a().a().a(rp_eF.a(0))).grow(1000, 1000);
                this.c = this.c.union(a2);
                return true;
            }
        }
        return false;
    }
    
    public final boolean b(final MouseEvent mouseEvent) {
        boolean b = false;
        if (this.a && this.b && this.a != null) {
            if (this.b == 0) {
                for (int i = 0; i < this.a.size(); ++i) {
                    final Point point = this.b.elementAt(i);
                    this.a.elementAt(i).a(point.x, point.y, 0.0, true);
                }
                this.a(this.a(mouseEvent.getPoint(), true));
                b = true;
            }
            if (this.b == 5) {
                final rp_dC rp_dC;
                final int b2 = (rp_dC = this.a.elementAt(0)).b();
                final int c = rp_dC.c();
                rp_dC.a(this.d, this.e);
                final rp_fx a;
                (a = this.a.a().a()).a.a(new rp_fR(rp_dC, b2, c));
                this.a(a.a());
                this.a();
                b = true;
            }
        }
        final boolean b3 = false;
        this.b = b3;
        this.a = b3;
        this.a = null;
        this.b = -1;
        return b;
    }
    
    private double a(final Point point, final boolean b) {
        double n;
        if ((n = (rp_C.a(this.b, point) - rp_C.a(this.b, this.a)) / 0.017453292519943295) < 0.0) {
            n += 360.0;
        }
        double n2;
        for (n2 = n + this.b; n2 > 360.0; n2 -= 360.0) {}
        if (n2 < 6.0 || n2 > 354.0) {
            return 360.0 - this.b;
        }
        if (n2 < 96.0 && n2 > 84.0) {
            return 90.0 - this.b;
        }
        if (n2 < 186.0 && n2 > 174.0) {
            return 180.0 - this.b;
        }
        if (n2 < 276.0 && n2 > 264.0) {
            return 270.0 - this.b;
        }
        return n;
    }
    
    private void a(final double n) {
        final rp_fx a = this.a.a().a();
        final rp_dC[] array = new rp_dC[this.a.size()];
        for (int i = 0; i < this.a.size(); ++i) {
            array[i] = (rp_dC)this.a.elementAt(i);
        }
        final rp_ew a2;
        a.a.a(array, n, (a2 = a.a()).c(new Point(this.b.x + this.b.width / 2, this.b.y + this.b.height / 2)), this.b);
        this.b = a2.a(this.b);
        this.a();
    }
    
    static {
        a = new String[] { "rot.png", "dim.png", "dupl.gif", "del.gif", "show.png", "resize.png", "info.gif" };
    }
}
