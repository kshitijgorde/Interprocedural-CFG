// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class cm extends Canvas
{
    private Image q;
    private Image e;
    private Image a;
    private boolean h;
    private boolean d;
    
    public boolean isEnabled() {
        return true;
    }
    
    public boolean a() {
        return this.d;
    }
    
    public void a(final boolean d) {
        if (this.d == d) {
            return;
        }
        this.d = d;
        this.a = (this.d ? this.e : this.q);
        this.repaint();
        this.postEvent(new Event(this, 1001, null));
    }
    
    public void c() {
        this.a = (this.d ? this.q : this.e);
        this.d = !this.d;
        this.repaint();
        this.postEvent(new Event(this, 1001, null));
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (this.h) {
                    this.a = (this.d ? this.q : this.e);
                    this.repaint();
                }
                return true;
            }
            case 505: {
                this.a = (this.d ? this.e : this.q);
                this.repaint();
                return true;
            }
            case 501: {
                this.h = true;
                this.a = (this.d ? this.q : this.e);
                this.repaint();
                return true;
            }
            case 502: {
                if (this.inside(event.x, event.y) && this.h) {
                    this.c();
                }
                this.h = false;
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.a, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.q.getWidth(this), this.q.getHeight(this));
    }
    
    public Dimension size() {
        return this.preferredSize();
    }
    
    public cm(final Image q, final Image e) {
        this.h = false;
        this.d = false;
        this.q = q;
        this.a = this.q;
        this.e = e;
        this.resize(q.getWidth(this), q.getHeight(this));
        this.validate();
    }
}
