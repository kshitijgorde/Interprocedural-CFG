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

public class n extends Canvas
{
    private Image a;
    private Image b;
    private Image c;
    private boolean h;
    private boolean d;
    
    public boolean isEnabled() {
        return true;
    }
    
    public boolean b() {
        return this.d;
    }
    
    public void a(final boolean d) {
        if (this.d == d) {
            return;
        }
        this.d = d;
        this.c = (this.d ? this.b : this.a);
        this.repaint();
        this.postEvent(new Event(this, 1001, null));
    }
    
    public void c() {
        this.c = (this.d ? this.a : this.b);
        this.d = !this.d;
        this.repaint();
        this.postEvent(new Event(this, 1001, null));
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (this.h) {
                    this.c = (this.d ? this.a : this.b);
                    this.repaint();
                }
                return true;
            }
            case 505: {
                this.c = (this.d ? this.b : this.a);
                this.repaint();
                return true;
            }
            case 501: {
                this.h = true;
                this.c = (this.d ? this.a : this.b);
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
        graphics.drawImage(this.c, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.a.getWidth(this), this.a.getHeight(this));
    }
    
    public Dimension size() {
        return this.preferredSize();
    }
    
    public n(final Image a, final Image b) {
        this.h = false;
        this.d = false;
        this.a = a;
        this.c = this.a;
        this.b = b;
        this.resize(a.getWidth(this), a.getHeight(this));
        this.validate();
    }
}
