// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import java.util.Iterator;
import java.util.Set;
import com.eventim.common.transfer.saalplan.Farbverwaltung;
import com.eventim.applet.EventimApplet;
import com.eventim.applet.k;
import java.awt.geom.Rectangle2D;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.awt.Stroke;
import java.awt.Shape;
import java.util.Map;
import java.awt.Color;
import java.awt.geom.AffineTransform;

public final class f extends a implements b
{
    private static final AffineTransform f;
    private static final Color g;
    private int[] h;
    private int[] i;
    private String j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private Map p;
    private String q;
    private String r;
    private long s;
    private long t;
    private long u;
    private String v;
    private short w;
    private long x;
    private long y;
    
    static {
        g = Color.green;
        f = new AffineTransform();
    }
    
    f(final long u, final long t, final long y, final long s, final long x, final String v, final String j, final String r, final String q, final int[] i, final Shape shape, final Stroke stroke, final int n, final Color color, final Color color2) {
        super(shape, stroke, n, color, color2);
        this.h = new int[0];
        this.p = new HashMap(1);
        this.n = true;
        this.k = true;
        this.u = u;
        this.t = t;
        this.y = y;
        this.s = s;
        this.x = x;
        this.v = v;
        this.j = j;
        this.r = r;
        this.q = q;
        this.i = i;
    }
    
    public final void a(final Graphics2D graphics2D) {
        final GeneralPath generalPath;
        (generalPath = new GeneralPath(this.d)).transform(graphics2D.getTransform());
        final Rectangle2D bounds2D;
        final double[] a = a((bounds2D = generalPath.getBounds2D()).getWidth(), bounds2D.getHeight());
        final Ellipse2D.Double double1 = new Ellipse2D.Double(bounds2D.getX(), bounds2D.getY(), a[0], a[1]);
        final AffineTransform transform = graphics2D.getTransform();
        graphics2D.setTransform(com.eventim.applet.a.f.f);
        if (this.l || this.m) {
            final double[] a2;
            final double n = (a2 = a(bounds2D.getWidth() + a[0] / 6.0 * 2.0, bounds2D.getHeight() + a[1] / 6.0 * 2.0))[0] / 2.0;
            final double n2 = a2[1] / 2.0;
            final double floor = Math.floor(bounds2D.getCenterX() - n);
            final double floor2 = Math.floor(bounds2D.getCenterY() - n2);
            final Ellipse2D.Double double2 = (a[0] <= 4.5) ? new Ellipse2D.Double(floor - 1.0, floor2 - 1.0, a2[0] + 3.0, a2[1] + 3.0) : new Ellipse2D.Double(floor, floor2, a2[0] + 1.0, a2[1] + 1.0);
            if (this.l) {
                graphics2D.setPaint(com.eventim.applet.a.f.g);
            }
            else {
                graphics2D.setPaint(Color.white);
            }
            graphics2D.fill(double2);
            if (this.m) {
                graphics2D.fill(double2);
                graphics2D.fill(double2);
                graphics2D.fill(double2);
            }
        }
        this.m = false;
        graphics2D.setStroke(this.e);
        graphics2D.setPaint(this.a);
        graphics2D.fill(double1);
        if (a[0] > 3.0) {
            graphics2D.setPaint(this.b);
            graphics2D.draw(double1);
        }
        graphics2D.setTransform(transform);
    }
    
    public final boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && this.u == ((f)o).u);
    }
    
    public final String a_() {
        return this.j;
    }
    
    public final Integer a(final Integer n) {
        return this.p.get(n);
    }
    
    public final String b_() {
        return this.q;
    }
    
    public final String c_() {
        return this.r;
    }
    
    public final long i() {
        return this.s;
    }
    
    public final long j() {
        return this.t;
    }
    
    public final long k() {
        return this.u;
    }
    
    public final String d_() {
        return this.v;
    }
    
    public final short e() {
        return this.w;
    }
    
    public final long l() {
        return this.x;
    }
    
    public final long e_() {
        return this.y;
    }
    
    public final void a(final int n) {
        final Integer n2 = new Integer(this.o);
        if (this.p.containsKey(n2)) {
            this.k = (n <= (int)this.p.get(n2));
            this.n();
        }
    }
    
    public final int hashCode() {
        return 31 + (int)(this.u ^ this.u >>> 32);
    }
    
    public final boolean b(final int n) {
        return com.eventim.applet.k.a(n, this.i);
    }
    
    public final boolean f_() {
        return true;
    }
    
    public final boolean m() {
        return this.n && this.k;
    }
    
    public final boolean h() {
        return false;
    }
    
    public final void c(final int o) {
        this.o = o;
        final Integer a = EventimApplet.a(this.y);
        this.c = ((a != null) ? new Color(Farbverwaltung.getFarbeForPkNr(a)) : Color.white);
        this.n();
    }
    
    public final void a(final Set set) {
        this.n = set.contains(new Long(this.y));
        this.n();
    }
    
    public final void b(final Set set) {
        if (set != null) {
            this.h = new int[set.size()];
            int n = 0;
            final Iterator<Integer> iterator = set.iterator();
            while (iterator.hasNext()) {
                this.h[n] = iterator.next();
                ++n;
            }
            return;
        }
        this.h = new int[0];
    }
    
    public final void a(final boolean l) {
        if (!(this.l = l)) {
            this.m = true;
        }
    }
    
    public final void a(final int n, final Integer n2) {
        this.p.put(n2, new Integer(n));
    }
    
    public final void a(final short w) {
        this.w = w;
    }
    
    private void n() {
        Stroke stroke = com.eventim.applet.a.q.l;
        Color color = this.c();
        Color color2 = this.d();
        if (com.eventim.applet.k.a(this.o, this.i)) {
            if (this.n && this.k) {
                if (this.o > 0) {
                    color2 = com.eventim.applet.a.q.h;
                }
            }
            else {
                color = com.eventim.applet.a.n.a(color);
                if (this.o > 0) {
                    color2 = com.eventim.applet.a.q.h;
                }
                color2 = com.eventim.applet.a.n.a(color2);
            }
        }
        else {
            stroke = com.eventim.applet.a.q.m;
            color = com.eventim.applet.a.q.j;
            if (com.eventim.applet.k.a(this.o, this.h) && this.o > 0) {
                color2 = com.eventim.applet.a.q.h;
            }
            else {
                color2 = com.eventim.applet.a.q.k;
            }
        }
        this.a(stroke);
        this.a(color);
        this.b(color2);
    }
    
    public final String toString() {
        return "id: " + this.u + " seat group id: " + this.t + " tdl pc id: " + this.y + " sg num: " + this.s + " seq num: " + this.x + " row: " + this.r + " place: " + this.q + " av: " + this.i + " ass prm: " + this.h + " max grp sz: " + this.p + " last upd prm: " + this.o + " is in sel pcs: " + this.n + " ok grp sz: " + this.k;
    }
    
    private static double[] a(final double n, final double n2) {
        if (n <= 3.0 && n2 <= 3.0) {
            return new double[] { n, n2 };
        }
        final int n3 = (int)Math.round(n);
        final int n4 = (int)Math.round(n);
        if (n3 % 2 == 0) {
            return new double[] { n3, n3 };
        }
        if (n4 % 2 == 0) {
            return new double[] { n4, n4 };
        }
        final int min;
        final int n5 = ((min = Math.min(n3, n4)) % 2 == 0) ? min : (min - 1);
        return new double[] { n5, n5 };
    }
}
