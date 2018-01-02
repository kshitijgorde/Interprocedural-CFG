// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.Event;
import java.awt.Point;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

public class bl extends Canvas implements ImageObserver
{
    private static ag q;
    protected Image q;
    private URL q;
    private Image w;
    private Image e;
    private Image r;
    private Dimension q;
    private Point q;
    private boolean q;
    
    public final void q(final Image e) {
        this.e = e;
        this.q = true;
    }
    
    public final void w(final Image q) {
        this.q = q;
        if (q != null) {
            this.q(q.getWidth(this), q.getHeight(this));
        }
        this.repaint();
    }
    
    public final void q(final URL q) {
        this.q = q;
    }
    
    public final URL q() {
        return this.q;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2)) {
            this.postEvent(new Event(this, 1001, this.q));
        }
        return true;
    }
    
    public final void q(final int n, final int n2) {
        final Dimension size;
        if (((size = this.size()).height != n2 || size.width != n) && n > 0 && n2 > 0) {
            this.resize(n, n2);
            ImageObserver parent;
            bl bl;
            for (parent = this, bl = this; parent != null && !(bl instanceof Window); bl = (bl)parent, parent = ((bl)parent).getParent()) {
                ((bl)parent).invalidate();
            }
            bl.validate();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x1) != 0x0 && (n & 0x2) != 0x0) {
            this.q(image.getWidth(null), image.getHeight(null));
        }
        if ((n & 0x20) != 0x0) {
            this.q(image.getWidth(null), image.getHeight(null));
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (graphics != null) {
            if (this.r != null) {
                graphics.drawImage(this.r, 0, 0, this);
            }
            if (this.q != null) {
                graphics.drawImage(this.q, 0, 0, this);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.w == null) {
            int width = this.getSize().width;
            final int height = this.getSize().height;
            if (width == 0) {
                ++width;
            }
            this.w = dN.q(this, width, height + 4, "bp.u-d");
        }
        final Graphics graphics2 = this.w.getGraphics();
        if (this.e != null && this.q) {
            this.q = false;
            final Container parent;
            if ((parent = this.getParent()) != null && !this.q.equals(parent.size()) && !this.q.equals(parent.location())) {
                this.q = parent.size();
                this.q = parent.location();
                this.r = this.createImage(new FilteredImageSource(this.e.getSource(), new CropImageFilter(this.location().x + (0 + parent.location().x), this.location().y + (0 + parent.location().y), this.size().width - 1, parent.size().height)));
                System.out.println("Created image, bp.u-g");
            }
        }
        this.paint(graphics2);
        graphics2.dispose();
        graphics.drawImage(this.w, 0, 0, this);
    }
    
    public bl() {
        this.q = null;
        this.q = new Dimension();
        this.q = new Point(0, 0);
        this.q = false;
        final ag q;
        final ag ag = q = bl.q;
        synchronized (ag.q) {
            q.q.addElement(this);
        }
    }
    
    static {
        bl.q = new ag();
    }
}
