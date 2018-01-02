// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Window;
import java.awt.Event;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

public class a9 extends Canvas implements ImageObserver
{
    private static bm a;
    protected Image b;
    private URL c;
    private Image d;
    private Graphics e;
    private Image f;
    private Image g;
    private Dimension h;
    private Dimension pref;
    private Point i;
    private boolean j;
    private boolean fAnim;
    
    public void a(final Image f) {
        this.f = f;
        this.j = true;
    }
    
    public void b(final Image b) {
        this.b = b;
        if (b != null) {
            this.d = null;
            this.a(b.getWidth(this), b.getHeight(this));
        }
        this.repaint();
    }
    
    public void a(final URL c) {
        this.c = c;
    }
    
    public void setURL(final URL url) {
        this.a(url);
        this.setCursor(Cursor.getPredefinedCursor((url == null) ? 0 : 12));
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
            a9 a9;
            for (parent = this, a9 = this; parent != null && !(a9 instanceof Window); a9 = (a9)parent, parent = ((a9)parent).getParent()) {
                ((a9)parent).invalidate();
            }
            a9.validate();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.a(image.getWidth(null), image.getHeight(null));
            this.repaint();
            return false;
        }
        if ((n & 0x10) != 0x0) {
            this.repaint();
            return this.fAnim || CommonStyle.fAnim;
        }
        if ((n & 0x1) != 0x0 && (n & 0x2) != 0x0) {
            this.a(image.getWidth(null), image.getHeight(null));
        }
        return true;
    }
    
    public Dimension preferredSize() {
        if (this.pref == null && this.b == null) {
            return super.preferredSize();
        }
        if (this.pref == null || (this.b != null && (this.pref.width < this.b.getWidth(null) || this.pref.height < this.b.getHeight(null)))) {
            this.pref = new Dimension(this.b.getWidth(null), this.b.getHeight(null));
        }
        return this.pref;
    }
    
    public Dimension getPreferredSize() {
        return this.preferredSize();
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
        final Dimension size = this.size();
        if (size.width > 0 && size.height > 0) {
            if (this.d == null) {
                this.d = this.createImage(size.width, size.height + 4);
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
    }
    
    public a9(final boolean fAnim) {
        this.b = null;
        this.h = new Dimension();
        this.i = new Point(0, 0);
        this.j = false;
        this.fAnim = fAnim;
        this.pref = null;
        a9.a.a(this);
    }
    
    public a9() {
        this(true);
    }
    
    static {
        a9.a = new bm();
    }
}
