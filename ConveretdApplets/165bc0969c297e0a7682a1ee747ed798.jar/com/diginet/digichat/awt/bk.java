// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import com.diginet.digichat.util.s;
import java.awt.Canvas;

public class bk extends Canvas implements s
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
    
    public static bk a(final Image image, final Image image2, final Image image3) {
        return new bk(image, image2, image3, true);
    }
    
    public void setEnabled(final boolean f) {
        if (this.f == f) {
            return;
        }
        if (!(this.f = f)) {
            this.h = ((this.c == null) ? this.a : this.c);
        }
        else {
            this.h = this.a;
            this.g = true;
        }
        super.enable(f);
        this.repaint();
    }
    
    public boolean isEnabled() {
        return this.f;
    }
    
    public void a() {
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
    
    public boolean handleEvent(final Event event) {
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
                        this.a();
                    }
                    this.d = false;
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.h, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final String j, final String k) {
        this.j = j;
        this.k = k;
    }
    
    public String a(final Object o) {
        if (this.f) {
            return this.j;
        }
        return this.k;
    }
    
    private bk(final Image a, final Image b, final Image c, final boolean e) {
        this.d = false;
        this.e = true;
        this.f = true;
        this.g = false;
        this.i = null;
        this.j = null;
        this.k = null;
        this.a = a;
        this.h = this.a;
        this.b = b;
        this.c = c;
        this.e = e;
        this.resize(a.getWidth(this), a.getHeight(this));
    }
}
