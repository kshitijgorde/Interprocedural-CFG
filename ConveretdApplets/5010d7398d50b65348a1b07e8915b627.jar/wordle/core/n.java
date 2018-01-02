// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.awt.print.Printable;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.awt.image.ImageObserver;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.AffineTransform;
import java.awt.Dimension;
import wordle.core.a.a.a;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.Color;
import wordle.core.a.c;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

public final class n extends JComponent
{
    private final transient Rectangle2D.Double a;
    private final transient E b;
    private final transient Timer c;
    private final Object d;
    private K e;
    private b f;
    private e g;
    private boolean h;
    private boolean i;
    private boolean j;
    private final Point2D.Double[] k;
    private final Point2D.Double l;
    private final Point2D.Double m;
    
    public n() {
        this.a = new Rectangle2D.Double();
        this.c = new c("Canvas Look Animation Timer");
        new Timer("General Animation Timer");
        this.d = new Object();
        this.e = null;
        this.f = null;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = new Point2D.Double[] { new Point2D.Double(), new Point2D.Double() };
        this.l = new Point2D.Double();
        this.m = new Point2D.Double();
        this.setOpaque(true);
        this.setDoubleBuffered(true);
        this.setBackground(Color.WHITE);
        this.addComponentListener(new h(this));
        this.addMouseListener(new i(this));
        this.b = new E(this);
    }
    
    public final synchronized void a(final J j) {
        this.g = new e(this, j);
    }
    
    public final boolean isFocusable() {
        return false;
    }
    
    final E a() {
        return this.b;
    }
    
    public final boolean b() {
        return this.j;
    }
    
    public final void a(final boolean j) {
        if (j != this.j) {
            this.j = j;
            this.repaint();
        }
    }
    
    final synchronized void a(final K e) {
        this.e();
        synchronized (this.d) {
            (this.e = e).a(this);
        }
        // monitorexit(this.d)
    }
    
    final void c() {
        synchronized (this.d) {
            if (this.e == null) {
                // monitorexit(this.d)
                return;
            }
            final Rectangle2D.Double g;
            if ((g = this.e.g()) == null) {
                // monitorexit(this.d)
                return;
            }
            if (this.f != null && this.f.b()) {
                this.f.a(g);
            }
            else {
                (this.f = new b(this, 400L, new a(), g)).a(this.c);
            }
        }
        // monitorexit(this.d)
    }
    
    final void a(final Rectangle2D rectangle2D) {
        this.a.setFrame(rectangle2D.getX(), rectangle2D.getY(), rectangle2D.getWidth(), rectangle2D.getHeight());
        this.repaint();
    }
    
    public final synchronized K d() {
        return this.e;
    }
    
    public final synchronized void e() {
        if (this.f != null) {
            this.f.d();
        }
        this.f = null;
        synchronized (this.d) {
            if (this.e != null) {
                this.e.a((n)null);
            }
            this.e = null;
        }
        // monitorexit(this.d)
        this.repaint();
    }
    
    public final Rectangle2D b(Rectangle2D rectangle2D) {
        if (rectangle2D == null) {
            rectangle2D = new Rectangle2D.Double();
        }
        rectangle2D.setFrame(this.a);
        return rectangle2D;
    }
    
    private AffineTransform a(final Dimension dimension) {
        double n;
        if (this.a.width > this.a.height) {
            if ((n = dimension.width / this.a.width) * this.a.height > dimension.height) {
                n = dimension.height / this.a.height;
            }
        }
        else if ((n = dimension.height / this.a.height) * this.a.width > dimension.width) {
            n = dimension.width / this.a.width;
        }
        final double n2 = (dimension.width - n * this.a.width) / 2.0 - n * this.a.x;
        final double n3 = (dimension.height - n * this.a.height) / 2.0 - n * this.a.y;
        final AffineTransform affineTransform;
        (affineTransform = new AffineTransform()).preConcatenate(AffineTransform.getScaleInstance(n, n));
        affineTransform.preConcatenate(AffineTransform.getTranslateInstance(n2, n3));
        return affineTransform;
    }
    
    private static AffineTransform a(final AffineTransform affineTransform) {
        try {
            return affineTransform.createInverse();
        }
        catch (NoninvertibleTransformException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private AffineTransform g() {
        return this.a(this.getSize());
    }
    
    public final void a(final wordle.core.b.c c) {
        final double n = Math.sqrt(c.j()) / 50.0;
        this.k[0].setLocation(c.a.x - n, c.a.y - n);
        this.k[1].setLocation(c.a.x + c.f(), c.a.y + c.g());
        this.g().transform(this.k, 0, this.k, 0, this.k.length);
        this.repaint((int)this.k[0].x - 5, (int)this.k[0].y - 5, (int)(this.k[1].x - this.k[0].x + 10.0), (int)(this.k[1].y - this.k[0].y + 10.0));
    }
    
    public final void paint(final Graphics graphics) {
        this.a((Graphics2D)graphics, this.getSize(), false);
    }
    
    private void a(final Graphics2D graphics2D, final Dimension dimension, final boolean b) {
        graphics2D.setColor(this.getBackground());
        final Rectangle rectangle = (graphics2D.getClipBounds() == null) ? new Rectangle(0, 0, this.getWidth(), this.getHeight()) : graphics2D.getClipBounds();
        graphics2D.fill(rectangle);
        if (this.e == null || this.e.d() == 0) {
            return;
        }
        final AffineTransform a2;
        final AffineTransform a = a(a2 = this.a(dimension));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        final wordle.core.b.c c = (this.g == null) ? null : this.g.a();
        final boolean b2 = this.g != null && this.g.b();
        synchronized (this.d) {
            if (this.e != null) {
                final Rectangle2D bounds2D = a.createTransformedShape(rectangle).getBounds2D();
                final AffineTransform transform = graphics2D.getTransform();
                try {
                    graphics2D.transform(a2);
                    wordle.core.b.c[] e;
                    for (int length = (e = this.e.e()).length, i = 0; i < length; ++i) {
                        final wordle.core.b.c c2;
                        if ((c2 = e[i]).e() && c2.a(bounds2D)) {
                            if (b) {
                                c2.a(graphics2D, this);
                            }
                            else if (c2 == c && b2) {
                                c2.d(graphics2D, this);
                            }
                            else if (c2 == c) {
                                c2.c(graphics2D, this);
                            }
                            else {
                                c2.b(graphics2D, this);
                            }
                        }
                    }
                }
                finally {
                    graphics2D.setTransform(transform);
                }
                graphics2D.setTransform(transform);
            }
        }
        // monitorexit(this.d)
        final n n;
        final E b3 = n.b;
        n.getSize();
        b3.a(graphics2D);
    }
    
    public final void a(final Graphics2D graphics2D, final Dimension dimension) {
        this.a(graphics2D, dimension, true);
    }
    
    public final void f() {
        final PrinterJob printerJob = PrinterJob.getPrinterJob();
        final PageFormat pageFormat;
        (pageFormat = new PageFormat()).setOrientation(0);
        printerJob.setPrintable(new A(this), pageFormat);
        if (printerJob.printDialog()) {
            printerJob.print();
        }
    }
    
    public final wordle.core.b.c a(final int n, final int n2) {
        final K d;
        if ((d = this.d()) == null || d.d() == 0) {
            return null;
        }
        final AffineTransform a = a(this.g());
        this.l.setLocation(n, n2);
        a.transform(this.l, this.m);
        return d.a(this.m.x, this.m.y);
    }
}
