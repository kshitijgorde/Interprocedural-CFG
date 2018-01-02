// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class bp extends Canvas
{
    private Image a;
    private Image b;
    private Image c;
    private boolean d;
    private boolean e;
    
    public final boolean isEnabled() {
        return true;
    }
    
    public final boolean a() {
        return this.e;
    }
    
    public final void a(final boolean e) {
        if (this.e == e) {
            return;
        }
        this.e = e;
        this.c = (this.e ? this.b : this.a);
        this.repaint();
        this.postEvent(new Event(this, 1001, null));
    }
    
    public final void b() {
        this.c = (this.e ? this.a : this.b);
        this.e = !this.e;
        this.repaint();
        this.postEvent(new Event(this, 1001, null));
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (this.d) {
                    this.c = (this.e ? this.a : this.b);
                    this.repaint();
                }
                return true;
            }
            case 505: {
                this.c = (this.e ? this.b : this.a);
                this.repaint();
                return true;
            }
            case 501: {
                this.d = true;
                this.c = (this.e ? this.a : this.b);
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
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.c, 0, 0, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final Dimension preferredSize() {
        return new Dimension(this.a.getWidth(this), this.a.getHeight(this));
    }
    
    public final Dimension size() {
        return this.preferredSize();
    }
    
    public bp(final Image a, final Image b) {
        this.d = false;
        this.e = false;
        this.a = a;
        this.c = this.a;
        this.b = b;
        this.resize(a.getWidth(this), a.getHeight(this));
        this.validate();
    }
}
