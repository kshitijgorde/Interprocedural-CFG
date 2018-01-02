import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Point;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_eV extends rp_eS
{
    public Graphics a;
    Point a;
    public int a;
    private Point b;
    private Stroke a;
    
    public rp_eV(final Point a, final int n) {
        this.b = new Point();
        this.a = null;
        this.a = a;
        this.a(n);
    }
    
    public final Graphics a() {
        return this.a;
    }
    
    boolean a(final int a) {
        if (a != this.a) {
            this.a = a;
            return true;
        }
        return false;
    }
    
    public final boolean a(final Point a) {
        if (this.a.equals(a)) {
            return false;
        }
        this.a = a;
        return true;
    }
    
    public final Point a(final Point point) {
        this.b.x = this.a.x + point.x * this.a;
        this.b.y = this.a.y + point.y * this.a;
        return this.b;
    }
    
    public final int a(final int n) {
        return Math.round((n - this.a.x) / this.a);
    }
    
    public final int b(final int n) {
        return Math.round((n - this.a.y) / this.a);
    }
    
    public final Point b(final Point point) {
        return new Point(this.a(point.x), this.b(point.y));
    }
    
    public final Point c(final Point point) {
        return new Point(this.a.x + point.x * this.a, this.a.y + point.y * this.a);
    }
    
    public final Rectangle a(final Rectangle rectangle) {
        final int a = this.a(rectangle.x);
        final int b = this.b(rectangle.y);
        return new Rectangle(a, b, this.a(rectangle.x + rectangle.width) - a, this.b(rectangle.y + rectangle.height) - b);
    }
    
    public final Rectangle a() {
        final Rectangle clipBounds;
        if ((clipBounds = this.a.getClipBounds()) == null) {
            return null;
        }
        final rp_eV rp_eV = this;
        final Rectangle rectangle = clipBounds;
        this = rp_eV;
        final int n = rp_eV.a.x + rectangle.x * this.a;
        final int n2 = this.a.y + rectangle.y * this.a;
        return new Rectangle(n, n2, this.a.x + (rectangle.x + rectangle.width) * this.a - n, this.a.y + (rectangle.y + rectangle.height) * this.a - n2);
    }
    
    public final boolean a(final Rectangle rectangle, final Dimension dimension, final Point point) {
        final int n = 1 + rectangle.width / dimension.width;
        final int n2 = 1 + rectangle.height / dimension.height;
        int a;
        int x;
        int y;
        if (n > n2) {
            a = n;
            x = rectangle.x;
            y = rectangle.y - (dimension.height * n - rectangle.height) / 2;
        }
        else {
            a = n2;
            x = rectangle.x - (dimension.width * n2 - rectangle.width) / 2;
            y = rectangle.y;
        }
        if (this.a != a || x != this.a.x || y != this.a.y) {
            ((Point)(this.a = a)).setLocation(x, y);
            point.setLocation(x, y);
            return true;
        }
        return false;
    }
    
    public final void a(final Color color) {
        if (this.d && this.a != null) {
            this.a.setColor(this.a);
            return;
        }
        this.a.setColor(color);
    }
    
    public final void b(final Color color) {
        if (this.d && this.b != null) {
            this.a.setColor(this.b);
            return;
        }
        this.a.setColor(color);
    }
    
    public final void a(final int n) {
        final Graphics2D graphics2D = (Graphics2D)this.a;
        if (n > 0.0) {
            if (this.a == null) {
                this.a = graphics2D.getStroke();
            }
            graphics2D.setStroke(new BasicStroke(n / this.a));
        }
        if (n == 0.0 && this.a != null) {
            graphics2D.setStroke(this.a);
        }
    }
    
    public final void a(int a, int b, int a2, int b2) {
        a = this.a(a);
        b = this.b(b);
        a2 = this.a(a2);
        b2 = this.b(b2);
        this.a.drawLine(a, b, a2, b2);
    }
    
    public final void a(int a, int b, int n, int n2, final int n3, double n4, double n5, final double n6) {
        a = this.a(a);
        b = this.b(b);
        if (n == 0) {
            n = Math.abs(n3 / this.a);
            n2 = Math.abs(n2 / this.a);
            n4 += 90.0;
            n5 += 90.0;
        }
        else {
            n = Math.abs(n / this.a);
            n2 = Math.abs(n3 / this.a);
        }
        final Graphics2D graphics2D;
        final AffineTransform transform = (graphics2D = (Graphics2D)(this = this).a).getTransform();
        graphics2D.rotate(n6 * 3.141592653589793 / 180.0, a, b);
        graphics2D.draw(new Arc2D.Double(a - n, b - n2, n * 2, n2 * 2, n4, n5 - n4, 0));
        graphics2D.setTransform(transform);
    }
    
    public final void b(final int n, final int n2, final int n3, final int n4) {
        this.a.fillOval(this.a(n), this.b(n2), n3 / this.a, n4 / this.a);
    }
    
    public final void a(final rp_aV rp_aV, final double n, int a, int b, int n2, int n3) {
        a = this.a(a);
        b = this.b(b);
        n2 = (int)(n2 / this.a + 0.5) + 1;
        n3 = (int)(n3 / this.a + 0.5) + 1;
        final int a2 = rp_aV.a();
        final int b2 = rp_aV.b();
        final Graphics2D graphics2D = (Graphics2D)this.a;
        try {
            AffineTransform transform;
            if ((transform = graphics2D.getTransform()) == null) {
                transform = AffineTransform.getTranslateInstance(0.0, 0.0);
            }
            final AffineTransform transform2;
            (transform2 = (AffineTransform)transform.clone()).concatenate(AffineTransform.getTranslateInstance(a, b));
            transform2.concatenate(AffineTransform.getRotateInstance(Math.toRadians(n), n2 / 2.0, n3 / 2.0));
            transform2.concatenate(AffineTransform.getScaleInstance(n2 / a2, n3 / b2));
            graphics2D.setTransform(transform2);
            rp_aV.a = false;
            rp_aV.b = null;
            rp_aV.a = null;
            if (this.c != null && this.d != null) {
                rp_aV.a = true;
                rp_aV.b = this.c;
                rp_aV.a = this.d;
            }
            if (this.d) {
                rp_aV.a = this.d;
                rp_aV.b = this.a;
                rp_aV.a = this.b;
            }
            final Graphics2D graphics2D2 = graphics2D;
            try {
                final rp_cX a3;
                (a3 = rp_aV.a()).a(true);
                a3.a(graphics2D2);
            }
            catch (Exception ex2) {
                final Exception ex = ex2;
                ex2.printStackTrace();
                throw new RuntimeException("Error drawing due to: " + ex.getMessage(), ex);
            }
            graphics2D.setTransform(transform);
        }
        catch (Exception ex4) {
            final Exception ex3 = ex4;
            ex4.printStackTrace();
            throw new RuntimeException("Error transcoding due to: " + ex3.getMessage(), ex3);
        }
    }
    
    public final void c(final int n, final int n2, final int n3, final int n4) {
        this.a.fillRect(this.a(n), this.b(n2), (int)(n3 / this.a + 0.5) + 1, (int)(n4 / this.a + 0.5) + 1);
    }
    
    public final void a(final int[] array, final int[] array2, final int n) {
        this.c(array, array2, n);
        this.a.drawPolyline(array, array2, n);
    }
    
    public final void b(final int[] array, final int[] array2, final int n) {
        this.c(array, array2, n);
        this.a.drawPolygon(array, array2, n);
    }
    
    public final void a(final int[] array, final int[] array2, final int n, final Color color) {
        this.c(array, array2, n);
        final Color color2 = this.a.getColor();
        this.b(color);
        this.a.fillPolygon(array, array2, n);
        this.a.setColor(color2);
    }
    
    public final void a(final int[] array, final int[] array2, final int n, final int n2, final Point point, final Point point2, Color b, Color b2) {
        b = b;
        b2 = b2;
        if (this.d) {
            if (b2.equals(Color.white)) {
                b = this.b();
            }
            else {
                b2 = this.b();
            }
        }
        this.c(array, array2, n);
        final Polygon polygon = new Polygon(array, array2, n);
        final Graphics2D graphics2D = (Graphics2D)this.a;
        final Point b3 = this.b(point);
        final Point b4 = this.b(point2);
        switch (n2) {
            case 3: {
                graphics2D.setPaint(new rp_en(new Point2D.Float(b3.x, b3.y), (float)(2.0 * Math.hypot(b4.x - b3.x, b4.y - b3.y)), new float[] { 0.0f, 1.0f }, new Color[] { b2, b }));
                break;
            }
            case 1: {
                graphics2D.setPaint(new GradientPaint(b3.x, b3.y, b2, b4.x, b4.y, b, false));
                break;
            }
            case 2: {
                graphics2D.setPaint(new GradientPaint(b3.x, b3.y, b2, (b3.x + b4.x) / 2, (b3.y + b4.y) / 2, b, true));
                break;
            }
            default: {
                return;
            }
        }
        graphics2D.fill(polygon);
    }
    
    private void c(final int[] array, final int[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            array[i] = this.a(array[i]);
            array2[i] = this.b(array2[i]);
        }
    }
    
    public final void a(final String s, int a, int b, final int n, final int n2) {
        a = this.a(a);
        b = this.b(b);
        final Dimension a2 = rp_eS.a(this.a.getFontMetrics(), s, n, 2);
        this.a.drawString(s, a + a2.width, b + a2.height);
    }
    
    public final Rectangle a(final String s, final int n, final int n2, final int n3, final int n4) {
        final FontMetrics fontMetrics = this.a.getFontMetrics();
        final Rectangle rectangle = new Rectangle(n, n2, this.a * fontMetrics.stringWidth(s), this.a * fontMetrics.getHeight());
        final Dimension a = rp_eS.a(fontMetrics, s, n3, 2);
        rectangle.translate(this.a * a.width, this.a * (a.height - fontMetrics.getAscent()));
        return rectangle;
    }
}
