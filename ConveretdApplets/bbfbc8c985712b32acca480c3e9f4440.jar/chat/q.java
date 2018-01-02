// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Window;
import java.awt.Event;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

public class q extends Canvas implements ImageObserver
{
    private static bE a;
    protected Image a;
    private URL a;
    private Image b;
    private Graphics a;
    private Image c;
    private Image d;
    private Dimension a;
    private Point a;
    private boolean a;
    
    public final void a(final Image c) {
        this.c = c;
        this.a = true;
    }
    
    public final void b(final Image a) {
        this.a = a;
        if (a != null) {
            this.a(a.getWidth(this), a.getHeight(this));
        }
        this.repaint();
    }
    
    public final void a(final URL a) {
        this.a = a;
    }
    
    public final URL a() {
        return this.a;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2)) {
            this.postEvent(new Event(this, 1001, this.a));
        }
        return true;
    }
    
    public final void a(final int n, final int n2) {
        final Dimension size;
        if (((size = this.size()).height != n2 || size.width != n) && n > 0 && n2 > 0) {
            this.resize(n, n2);
            q q = null;
            for (ImageObserver parent = this; parent != null && !(q instanceof Window); q = (q)parent, parent = ((q)parent).getParent()) {
                ((q)parent).invalidate();
            }
            q.validate();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x1) != 0x0 && (n & 0x2) != 0x0) {
            this.a(image.getWidth(null), image.getHeight(null));
        }
        if ((n & 0x20) != 0x0) {
            this.a(image.getWidth(null), image.getHeight(null));
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.d != null) {
            graphics.drawImage(this.d, 0, 0, this);
        }
        if (this.a != null) {
            graphics.drawImage(this.a, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.b == null) {
            this.b = this.createImage(this.size().width, this.size().height + 4);
            this.a = this.b.getGraphics();
        }
        if (this.c != null && this.a) {
            this.a = false;
            final Container parent;
            if ((parent = this.getParent()) != null && !this.a.equals(parent.size()) && !this.a.equals(parent.location())) {
                this.a = parent.size();
                this.a = parent.location();
                this.d = this.createImage(new FilteredImageSource(this.c.getSource(), new CropImageFilter(this.location().x + (0 + parent.location().x), this.location().y + (0 + parent.location().y), this.size().width - 1, parent.size().height)));
            }
        }
        this.paint(this.a);
        graphics.drawImage(this.b, 0, 0, this);
    }
    
    public q() {
        this.a = null;
        this.a = new Dimension();
        this.a = new Point(0, 0);
        this.a = false;
        final bE a;
        final bE be = a = q.a;
        synchronized (be.a) {
            a.a.addElement(this);
        }
    }
    
    static {
        q.a = new bE();
    }
}
