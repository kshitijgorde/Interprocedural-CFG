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

public class B extends Canvas implements ImageObserver
{
    private static cx a;
    protected Image e;
    private URL f;
    private Image f;
    private Graphics a;
    private Image g;
    private Image h;
    private Dimension a;
    private Point a;
    private boolean l;
    
    public void a(final Image g) {
        this.g = g;
        this.l = true;
    }
    
    public void b(final Image e) {
        this.e = e;
        if (e != null) {
            this.a(e.getWidth(this), e.getHeight(this));
        }
        this.repaint();
    }
    
    public void a(final URL f) {
        this.f = f;
    }
    
    public URL a() {
        return this.f;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2)) {
            this.postEvent(new Event(this, 1001, this.f));
        }
        return true;
    }
    
    public void a(final int n, final int n2) {
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
        if (this.h != null) {
            graphics.drawImage(this.h, 0, 0, this);
        }
        if (this.e != null) {
            graphics.drawImage(this.e, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.f == null) {
            this.f = this.createImage(this.size().width, this.size().height + 4);
            this.a = this.f.getGraphics();
        }
        if (this.g != null && this.l) {
            this.l = false;
            final Container parent = this.getParent();
            if (parent != null && !this.a.equals(parent.size()) && !this.a.equals(parent.location())) {
                this.a = parent.size();
                this.a = parent.location();
                this.h = this.createImage(new FilteredImageSource(this.g.getSource(), new CropImageFilter(this.location().x + (0 + parent.location().x), this.location().y + (0 + parent.location().y), this.size().width - 1, parent.size().height)));
            }
        }
        this.paint(this.a);
        graphics.drawImage(this.f, 0, 0, this);
    }
    
    public B() {
        this.e = null;
        this.a = new Dimension();
        this.a = new Point(0, 0);
        this.l = false;
        B.a.a(this);
    }
    
    static {
        B.a = new cx();
    }
}
