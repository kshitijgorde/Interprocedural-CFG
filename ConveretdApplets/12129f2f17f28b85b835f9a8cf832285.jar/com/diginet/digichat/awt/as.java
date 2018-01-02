// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

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

public class as extends Canvas implements ImageObserver
{
    private static ba a;
    protected Image b;
    private URL c;
    private Image d;
    private Graphics e;
    private Image f;
    private Image g;
    private Dimension h;
    private Point i;
    private boolean j;
    
    public void a(final Image f) {
        this.f = f;
        this.j = true;
    }
    
    public void b(final Image b) {
        this.b = b;
        if (b != null) {
            this.a(b.getWidth(this), b.getHeight(this));
        }
        this.repaint();
    }
    
    public void a(final URL c) {
        this.c = c;
    }
    
    public URL c() {
        return this.c;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2)) {
            this.postEvent(new Event(this, 1001, this.c));
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
        if (this.g != null) {
            graphics.drawImage(this.g, 0, 0, this);
        }
        if (this.b != null) {
            graphics.drawImage(this.b, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.d == null) {
            this.d = this.createImage(this.size().width, this.size().height + 4);
            this.e = this.d.getGraphics();
        }
        if (this.f != null && this.j) {
            this.j = false;
            final Container parent = this.getParent();
            if (parent != null && !this.h.equals(parent.size()) && !this.i.equals(parent.location())) {
                this.h = parent.size();
                this.i = parent.location();
                this.g = this.createImage(new FilteredImageSource(this.f.getSource(), new CropImageFilter(this.location().x + (0 + parent.location().x), this.location().y + (0 + parent.location().y), this.size().width - 1, parent.size().height)));
            }
        }
        this.paint(this.e);
        graphics.drawImage(this.d, 0, 0, this);
    }
    
    public as() {
        this.b = null;
        this.h = new Dimension();
        this.i = new Point(0, 0);
        this.j = false;
        as.a.a(this);
    }
    
    static {
        as.a = new ba();
    }
}
