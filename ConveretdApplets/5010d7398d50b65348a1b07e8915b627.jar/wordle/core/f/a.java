// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.f;

import java.util.ArrayList;
import java.util.Iterator;
import wordle.core.b.c;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public final class a
{
    private double a;
    private double b;
    private double c;
    private final a[] d;
    private final a e;
    private List f;
    private int g;
    private static final NumberFormat h;
    
    static {
        h = new DecimalFormat("0");
    }
    
    public a(final Rectangle2D rectangle2D, final double n) {
        this(null, rectangle2D.getCenterX(), rectangle2D.getCenterY(), rectangle2D.getWidth() / 2.0, n, 1);
    }
    
    private a(final a e, final double a, final double b, final double c, final double n, final int n2) {
        this.f = null;
        this.g = 0;
        this.e = e;
        this.a = a;
        this.b = b;
        this.c = c;
        if (n2 < 8 && c * 2.0 > n) {
            final double n3 = c / 2.0;
            this.d = new a[] { new a(this, a - n3, b - n3, n3, n, n2 + 1), new a(this, a + n3, b - n3, n3, n, n2 + 1), new a(this, a - n3, b + n3, n3, n, n2 + 1), new a(this, a + n3, b + n3, n3, n, n2 + 1) };
            return;
        }
        this.d = null;
    }
    
    private a a(final Rectangle2D rectangle2D) {
        while (this.d != null) {
            final double minX = rectangle2D.getMinX();
            final double maxX = rectangle2D.getMaxX();
            if (minX <= this.a && maxX > this.a) {
                return this;
            }
            final double minY = rectangle2D.getMinY();
            final double maxY = rectangle2D.getMaxY();
            if (minY <= this.b && maxY > this.b) {
                return this;
            }
            if (maxX <= this.a && maxY <= this.b) {
                this = this.d[0];
            }
            else if (minX > this.a && maxY <= this.b) {
                this = this.d[1];
            }
            else if (maxX <= this.a && minY > this.b) {
                this = this.d[2];
            }
            else {
                this = this.d[3];
            }
        }
        return this;
    }
    
    public final boolean a(final c c) {
        final a a;
        if ((a = this.a(c.h())).c(c)) {
            return true;
        }
        for (a e = a; e != null; e = e.e) {
            if (e.f != null) {
                for (final c c2 : e.f) {
                    ++this.g;
                    if (c.a(c2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean c(final c c) {
        if (this.f != null) {
            for (final c c2 : this.f) {
                ++this.g;
                if (c.a(c2)) {
                    return true;
                }
            }
        }
        if (this.d != null) {
            a[] d;
            for (int length = (d = this.d).length, i = 0; i < length; ++i) {
                if (d[i].c(c)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public final void b(c c) {
        final a a = this.a(c.h());
        c = c;
        this = a;
        if (a.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(c);
    }
    
    public final String toString() {
        return "[QuadTree at " + wordle.core.f.a.h.format(this.a) + "," + wordle.core.f.a.h.format(this.b) + " " + wordle.core.f.a.h.format(2.0 * this.c) + "x" + wordle.core.f.a.h.format(2.0 * this.c) + "]";
    }
}
