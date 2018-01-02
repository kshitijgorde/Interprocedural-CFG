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

public class W extends Canvas
{
    private Image b;
    private Image a;
    private Image c;
    private boolean a;
    private boolean b;
    
    public boolean isEnabled() {
        return true;
    }
    
    public boolean a() {
        return this.b;
    }
    
    public void a(final boolean b) {
        if (this.b == b) {
            return;
        }
        this.b = b;
        this.c = (this.b ? this.a : this.b);
        this.repaint();
        this.postEvent(new Event(this, 1001, null));
    }
    
    public void a() {
        this.c = (this.b ? this.b : this.a);
        this.b = !this.b;
        this.repaint();
        this.postEvent(new Event(this, 1001, null));
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (this.a) {
                    this.c = (this.b ? this.b : this.a);
                    this.repaint();
                }
                return true;
            }
            case 505: {
                this.c = (this.b ? this.a : this.b);
                this.repaint();
                return true;
            }
            case 501: {
                this.a = true;
                this.c = (this.b ? this.b : this.a);
                this.repaint();
                return true;
            }
            case 502: {
                if (this.inside(event.x, event.y) && this.a) {
                    this.a();
                }
                this.a = false;
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
        return new Dimension(this.b.getWidth(this), this.b.getHeight(this));
    }
    
    public Dimension size() {
        return this.preferredSize();
    }
    
    public W(final Image b, final Image a) {
        this.a = false;
        this.b = false;
        this.b = b;
        this.c = this.b;
        this.a = a;
        this.resize(b.getWidth(this), b.getHeight(this));
        this.validate();
    }
}
