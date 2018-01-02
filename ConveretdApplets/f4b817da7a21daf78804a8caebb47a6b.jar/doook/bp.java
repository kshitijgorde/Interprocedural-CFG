// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.Window;
import java.awt.Event;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

public class bp extends Canvas implements ImageObserver
{
    private static T a;
    protected Image b;
    private URL a;
    private Image j;
    private Graphics b;
    private Image f;
    private Image p;
    private Dimension c;
    private Point b;
    private boolean f;
    
    public void a(final Image f) {
        this.f = f;
        this.f = true;
    }
    
    public void b(final Image b) {
        this.b = b;
        if (b != null) {
            this.b(b.getWidth(this), b.getHeight(this));
        }
        this.repaint();
    }
    
    public void a(final URL a) {
        this.a = a;
    }
    
    public URL a() {
        return this.a;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2)) {
            this.postEvent(new Event(this, 1001, this.a));
        }
        return true;
    }
    
    public void b(final int n, final int n2) {
        final Dimension size = this.size();
        if ((size.height != n2 || size.width != n) && n > 0 && n2 > 0) {
            this.resize(n, n2);
            ImageObserver parent;
            Component component;
            for (parent = this, component = this; parent != null && !(component instanceof Window); component = (Component)parent, parent = ((Component)parent).getParent()) {
                ((Component)parent).invalidate();
            }
            component.validate();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x1) != 0x0 && (n & 0x2) != 0x0) {
            this.b(image.getWidth(null), image.getHeight(null));
        }
        if ((n & 0x20) != 0x0) {
            this.b(image.getWidth(null), image.getHeight(null));
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.p != null) {
            graphics.drawImage(this.p, 0, 0, this);
        }
        if (this.b != null) {
            graphics.drawImage(this.b, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.j == null) {
            this.j = this.createImage(this.size().width, this.size().height + 4);
            this.b = this.j.getGraphics();
        }
        if (this.f != null && this.f) {
            this.f = false;
            final Container parent = this.getParent();
            if (parent != null && !this.c.equals(parent.size()) && !this.b.equals(parent.location())) {
                this.c = parent.size();
                this.b = parent.location();
                this.p = this.createImage(new FilteredImageSource(this.f.getSource(), new CropImageFilter(this.location().x + (0 + parent.location().x), this.location().y + (0 + parent.location().y), this.size().width - 1, parent.size().height)));
            }
        }
        this.paint(this.b);
        graphics.drawImage(this.j, 0, 0, this);
    }
    
    public bp() {
        this.b = null;
        this.c = new Dimension();
        this.b = new Point(0, 0);
        this.f = false;
        bp.a.a(this);
    }
    
    static {
        bp.a = new T();
    }
}
