// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.b;

import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import wordle.core.z;
import java.awt.Shape;
import java.awt.BasicStroke;
import java.util.Comparator;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.regex.Pattern;
import wordle.core.n;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import wordle.core.C;
import java.awt.Color;

public final class c
{
    private static final Color d;
    private static final i e;
    private final C f;
    private Font g;
    private double h;
    private final double i;
    private final double j;
    public final Rectangle2D.Double a;
    private b k;
    private boolean l;
    private static final String m;
    private Polygon n;
    private Color o;
    private boolean p;
    private n q;
    private static final Pattern r;
    private c s;
    private volatile boolean t;
    private volatile Color u;
    private final Point2D.Float v;
    private static final Stroke w;
    public static final Comparator b;
    public static final Comparator c;
    
    static {
        d = Color.GRAY;
        e = new i(750);
        m = null;
        r = Pattern.compile("^\\d+,\\d+,\\d+$");
        w = new BasicStroke(5.0f);
        b = new g();
        c = new f();
        new e();
    }
    
    private c(final C c, final Font font, final Color color, final double n, final Point2D.Double double1) {
        this(c, font, color, n);
        this.b(double1.x, double1.y);
        this.l = true;
    }
    
    public final String a() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append(this.o.getRed()).append(",").append(this.o.getGreen()).append(",").append(this.o.getBlue());
        sb.append("/").append(wordle.core.e.c.a(this.h));
        sb.append("/").append(wordle.core.e.c.a(this.a.x));
        sb.append("/").append(wordle.core.e.c.a(this.a.y));
        sb.append("/").append(this.f.a());
        return sb.toString();
    }
    
    public static c a(String s, Font font) {
        final String[] split = s.split("/", 5);
        if (!wordle.core.b.c.r.matcher(split[0]).matches()) {
            final String s2 = s;
            font = font;
            s = s2;
            final String[] split2;
            if ((split2 = s2.split("/")).length > 5) {
                final String[] split4;
                final String[] split3 = (split4 = s.split(":", 2))[1].split("/");
                split2[0] = String.valueOf(split4[0]) + ":" + split3[0];
                System.arraycopy(split3, 1, split2, 1, split3.length - 1);
            }
            return new c(C.a(split2[0]), font, a(split2[1]), wordle.core.e.c.a(split2[2]), new Point2D.Double(wordle.core.e.c.a(split2[3]), wordle.core.e.c.a(split2[4])));
        }
        return new c(C.a(split[4]), font, a(split[0]), wordle.core.e.c.a(split[1]), new Point2D.Double(wordle.core.e.c.a(split[2]), wordle.core.e.c.a(split[3])));
    }
    
    private static Color a(final String s) {
        final String[] split = s.split(",");
        return new Color(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }
    
    public c(final C f, final Font g, final Color o, final double h) {
        this.n = null;
        this.p = true;
        this.s = null;
        this.t = false;
        this.u = null;
        this.v = new Point2D.Float();
        this.g = g;
        this.h = h;
        this.f = f;
        this.o = o;
        (this.a = new Rectangle2D.Double()).setFrame(this.l().getBounds2D());
        this.i = -this.a.x;
        this.j = -this.a.y;
        this.l = false;
        if (f.b.equalsIgnoreCase(null)) {
            this.n = new Polygon();
        }
    }
    
    private c(final C f, final Font g, final Color o, final double h, final Rectangle2D.Double double1, final double i, final double j, final boolean l) {
        this.n = null;
        this.p = true;
        this.s = null;
        this.t = false;
        this.u = null;
        this.v = new Point2D.Float();
        this.f = f;
        this.g = g;
        this.h = h;
        this.o = o;
        this.a = (Rectangle2D.Double)double1.clone();
        this.i = i;
        this.j = j;
        this.l = l;
        if (f.b.equalsIgnoreCase(null)) {
            this.n = new Polygon();
        }
    }
    
    public final void a(final n q) {
        this.q = q;
    }
    
    private Shape l() {
        return wordle.core.b.c.e.a(this.g, this.f.a, this.f.b, this.h);
    }
    
    public final void b() {
        if (this.q != null) {
            final n q = this.q;
        }
        this.k = null;
    }
    
    public final void c() {
        this.k = new b(this, this.l(), this.a);
    }
    
    public final c a(final Font font) {
        return new c(this.f, font, this.o, this.h);
    }
    
    public final c a(final double n) {
        return new c(this.f.a(n), this.g, this.o, this.h);
    }
    
    public final c d() {
        return new c(this.f, this.g, this.o, this.h);
    }
    
    public final c a(final z z) {
        return new c(this.f, this.g, this.o, z.a(this.f));
    }
    
    public final c a(final Color color) {
        return new c(this.f, this.g, color, this.h, this.a, this.i, this.j, this.l);
    }
    
    public final c b(final double n) {
        return new c(this.f, this.g, this.o, n);
    }
    
    public final boolean e() {
        return this.l;
    }
    
    public final void a(final boolean b) {
        this.l = true;
    }
    
    public final double f() {
        return this.a.width;
    }
    
    public final double g() {
        return this.a.height;
    }
    
    public final Rectangle2D h() {
        return this.a;
    }
    
    public final boolean a(final c s) {
        final boolean a;
        if (a = this.k.a(s.k)) {
            this.s = s;
        }
        return a;
    }
    
    public final c i() {
        return this.s;
    }
    
    public final boolean a(final Rectangle2D rectangle2D) {
        return this.a.intersects(rectangle2D);
    }
    
    public final boolean a(final double n, final double n2) {
        return this.a.contains(n, n2);
    }
    
    public final void b(final double n, final double n2) {
        if (this.n != null) {
            this.n.addPoint((int)n, (int)n2);
        }
        this.a.setFrame(n, n2, this.a.width, this.a.height);
    }
    
    public final void a(final Graphics2D graphics2D, final ImageObserver imageObserver) {
        this.a(graphics2D, true, false, false);
    }
    
    public final void b(final Graphics2D graphics2D, final ImageObserver imageObserver) {
        this.a(graphics2D, false, false, false);
    }
    
    public final void c(final Graphics2D graphics2D, final ImageObserver imageObserver) {
        this.a(graphics2D, false, false, true);
    }
    
    public final void d(final Graphics2D graphics2D, final ImageObserver imageObserver) {
        this.a(graphics2D, true, false, false);
    }
    
    private void a(final Graphics2D graphics2D, final boolean b, boolean transform, final boolean b2) {
        final Shape l = this.l();
        Point2D.Float v = this.v;
        final float n = (float)(this.a.x + this.i);
        final float n2 = (float)(this.a.y + this.j);
        if (v == null) {
            v = new Point2D.Float();
        }
        v.setLocation(n, n2);
        transform = (int)graphics2D.getTransform();
        graphics2D.translate(this.v.x, this.v.y);
        try {
            if (b2) {
                final n q = this.q;
            }
            graphics2D.setColor(this.o);
            if (!this.p || (this.q != null && this.q.b())) {
                final Stroke stroke = graphics2D.getStroke();
                graphics2D.setStroke(wordle.core.b.c.w);
                graphics2D.draw(l);
                graphics2D.setStroke(stroke);
            }
            else {
                graphics2D.fill(l);
            }
        }
        finally {
            graphics2D.setTransform((AffineTransform)transform);
        }
        graphics2D.setTransform((AffineTransform)transform);
        final c c;
        if (c.n != null) {
            graphics2D.setColor(Color.BLUE);
            final Stroke stroke2 = graphics2D.getStroke();
            graphics2D.setStroke(new BasicStroke(20.0f));
            graphics2D.drawPolyline(c.n.xpoints, c.n.ypoints, c.n.npoints);
            graphics2D.setColor(Color.RED);
            graphics2D.draw(c.a);
            graphics2D.setStroke(stroke2);
        }
        if (c.k != null) {
            final n q2 = c.q;
        }
    }
    
    public final double j() {
        return this.a.width * this.a.height;
    }
    
    public final C k() {
        return this.f;
    }
    
    public final String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("Word (").append(super.toString()).append(", ").append("layedOut=").append(this.l).append(", ").append("data=").append(this.f).append(", ").append("font=").append(this.g).append(", ").append("color=").append(this.o).append(", ").append("orientation=").append(this.h).append(", ").append("xOffset=").append(this.i).append(", ").append("yOffset=").append(this.j).append(", ").append("bigBounds=").append(this.a).append(", ").append("hbb=").append(this.k).append(", ").append("pos=").append(this.v).append(", ").append(")");
        return sb.toString();
    }
}
