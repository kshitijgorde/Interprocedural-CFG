// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;
import com.diginet.digichat.util.p;
import java.awt.Canvas;

public class ba extends Canvas implements p
{
    private Image a;
    private Image b;
    private Image c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private Image h;
    private String i;
    private String j;
    private String k;
    
    public static ba a(final Image image, final Image image2, final Image image3) {
        return new ba(image, image2, image3, true);
    }
    
    public static ba b(final Image image, final Image image2, final Image image3) {
        return new ba(image, image2, image3, false);
    }
    
    public final Dimension minimumSize() {
        int width = this.h.getWidth(this);
        int height = this.h.getHeight(this);
        if (width < 0) {
            width = 2;
        }
        if (height < 0) {
            height = 2;
        }
        return new Dimension(width, height);
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void setEnabled(final boolean f) {
        if (this.f == f) {
            return;
        }
        if (!(this.f = f)) {
            this.h = ((this.c != null) ? this.c : this.a);
        }
        else {
            this.h = this.a;
            this.g = true;
        }
        super.enable(f);
        this.repaint();
    }
    
    public final boolean isEnabled() {
        return this.f;
    }
    
    public final boolean a() {
        return this.g;
    }
    
    public final void b() {
        if (this.f) {
            if (this.e) {
                this.h = this.a;
                this.repaint();
            }
            else {
                this.h = (this.g ? this.a : this.b);
                this.g = !this.g;
                this.repaint();
            }
            this.postEvent(new Event(this, 1001, null));
        }
    }
    
    public final boolean handleEvent(final Event event) {
        if (this.f) {
            switch (event.id) {
                case 504: {
                    if (this.d) {
                        this.h = (this.e ? this.b : (this.g ? this.a : this.b));
                        this.repaint();
                    }
                    return true;
                }
                case 505: {
                    this.h = (this.e ? this.a : (this.g ? this.b : this.a));
                    this.repaint();
                    return true;
                }
                case 501: {
                    this.d = true;
                    this.h = (this.e ? this.b : (this.g ? this.a : this.b));
                    this.repaint();
                    return true;
                }
                case 502: {
                    if (this.inside(event.x, event.y) && this.d) {
                        this.b();
                    }
                    this.d = false;
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.h, 0, 0, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void a(final String j, final String k) {
        this.j = j;
        this.k = k;
    }
    
    public final String a(final Object o) {
        if (this.f) {
            return this.j;
        }
        return this.k;
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.resize(this.getMinimumSize());
            return false;
        }
        return true;
    }
    
    private ba(final Image a, final Image b, final Image c, final boolean e) {
        this.d = false;
        this.e = true;
        this.f = true;
        this.g = false;
        this.i = null;
        this.j = null;
        this.k = null;
        if (a != null) {
            this.prepareImage(a, this);
        }
        if (b != null) {
            this.prepareImage(b, this);
        }
        if (c != null) {
            this.prepareImage(c, this);
        }
        this.a = a;
        this.h = this.a;
        this.b = b;
        this.c = c;
        this.e = e;
        int width = this.h.getWidth(this);
        int height = this.h.getHeight(this);
        if (width < 0) {
            width = 2;
        }
        if (height < 0) {
            height = 2;
        }
        this.resize(width, height);
    }
}
