// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.b;

import java.awt.Shape;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public final class b
{
    private static final b[] a;
    private final c b;
    private final Rectangle2D.Double c;
    private b[] d;
    private static int e;
    private static Color f;
    
    static {
        a = new b[0];
        b.e = 0;
        b.f = new Color(1.0f, 0.0f, 0.0f, 0.02f);
    }
    
    public static void a() {
        b.e = 0;
    }
    
    b(final c b, final Shape shape, final Rectangle2D rectangle2D) {
        this.d = wordle.core.b.b.a;
        this.b = b;
        this.c = new Rectangle2D.Double(0.0, 0.0, rectangle2D.getWidth(), rectangle2D.getHeight());
        final double n = 1.8 * Math.log(rectangle2D.getWidth());
        final double n2 = 1.2 + Math.log(rectangle2D.getHeight());
        this.a(shape, Math.max(25.0, rectangle2D.getWidth() / 100.0));
        this.a(n, n2);
    }
    
    private b(final c b, final double n, final double n2, final double n3, final double n4) {
        this.d = wordle.core.b.b.a;
        this.b = b;
        this.c = new Rectangle2D.Double(n, n2, n3, n4);
    }
    
    private void a(final double n, final double n2) {
        final Rectangle2D.Double c = this.c;
        c.x -= n;
        final Rectangle2D.Double c2 = this.c;
        c2.y -= n2;
        final Rectangle2D.Double c3 = this.c;
        c3.width += n * 2.0;
        final Rectangle2D.Double c4 = this.c;
        c4.height += n2 * 2.0;
        b[] d;
        for (int length = (d = this.d).length, i = 0; i < length; ++i) {
            (this = d[i]).a(n, n2);
        }
    }
    
    private void a(final Shape shape, final double n) {
        b b = null;
        b b2 = null;
        final double n2 = this.c.x + this.b.a.x;
        final double n3 = this.c.y + this.b.a.y;
        if (this.c.width >= this.c.height) {
            if (this.c.width <= n) {
                return;
            }
            if (shape.intersects(n2, n3, this.c.width / 2.0, this.c.height)) {
                b = new b(this.b, this.c.x, this.c.y, this.c.width / 2.0, this.c.height);
            }
            if (shape.intersects(n2 + this.c.width / 2.0, n3, this.c.width / 2.0, this.c.height)) {
                b2 = new b(this.b, this.c.x + this.c.width / 2.0, this.c.y, this.c.width / 2.0, this.c.height);
            }
        }
        else {
            if (this.c.height <= n) {
                return;
            }
            if (shape.intersects(n2, n3, this.c.width, this.c.height / 2.0)) {
                b = new b(this.b, this.c.x, this.c.y, this.c.width, this.c.height / 2.0);
            }
            if (shape.intersects(n2, n3 + this.c.height / 2.0, this.c.width, this.c.height / 2.0)) {
                b2 = new b(this.b, this.c.x, this.c.y + this.c.height / 2.0, this.c.width, this.c.height / 2.0);
            }
        }
        if (b == null && b2 == null) {
            return;
        }
        if (b == null) {
            this.d = new b[] { b2 };
        }
        else if (b2 == null) {
            this.d = new b[] { b };
        }
        else {
            this.d = new b[] { b, b2 };
        }
        if (b != null) {
            b.a(shape, n);
        }
        if (b2 != null) {
            b2.a(shape, n);
        }
        if (b != null && b.d.length == 0 && b2 != null && b2.d.length == 0) {
            this.d = wordle.core.b.b.a;
        }
    }
    
    public final boolean a(b b) {
        while (true) {
            ++b.e;
            final double n = b.c.x + b.b.a.x;
            final double n2 = b.c.y + b.b.a.y;
            final double width = b.c.width;
            final double height = b.c.height;
            final double n3 = this.c.x + this.b.a.x;
            final double n4 = this.c.y + this.b.a.y;
            if (n + width <= n3 || n2 + height <= n4 || n >= n3 + this.c.width || n2 >= n4 + this.c.height) {
                return false;
            }
            if (this.d.length != 0) {
                b[] d;
                for (int length = (d = this.d).length, i = 0; i < length; ++i) {
                    if ((this = d[i]).a(b)) {
                        return true;
                    }
                }
                return false;
            }
            if (b.d.length == 0) {
                return true;
            }
            final b b2 = b;
            b = this;
            this = b2;
        }
    }
}
