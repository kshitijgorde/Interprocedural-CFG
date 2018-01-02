// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.ui;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class ac extends Canvas
{
    private Image a;
    private Image b;
    private Image c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private Image h;
    
    public static ac a(final Image image, final Image image2, final Image image3) {
        return new ac(image, image2, image3, true);
    }
    
    public static ac b(final Image image, final Image image2, final Image image3) {
        return new ac(image, image2, image3, false);
    }
    
    public final void setEnabled(final boolean f) {
        if (!(this.f = f)) {
            this.h = ((this.c != null) ? this.c : this.a);
        }
        else {
            this.h = this.a;
            this.g = false;
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
    
    private ac(final Image a, final Image b, final Image c, final boolean e) {
        this.d = false;
        this.e = true;
        this.f = true;
        this.g = false;
        this.a = a;
        this.h = this.a;
        this.b = b;
        this.c = c;
        this.e = e;
        this.resize(a.getWidth(this), a.getHeight(this));
    }
}
