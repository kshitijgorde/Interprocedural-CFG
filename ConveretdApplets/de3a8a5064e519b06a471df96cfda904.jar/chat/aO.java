// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public final class aO extends Canvas
{
    public Image a;
    public Image b;
    public Image c;
    private boolean b;
    public boolean a;
    
    public final boolean isEnabled() {
        return true;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (this.b) {
                    this.c = (this.a ? this.a : this.b);
                    this.repaint();
                }
                return true;
            }
            case 505: {
                this.c = (this.a ? this.b : this.a);
                this.repaint();
                return true;
            }
            case 501: {
                this.b = true;
                this.c = (this.a ? this.a : this.b);
                this.repaint();
                return true;
            }
            case 502: {
                if (this.inside(event.x, event.y) && this.b) {
                    this.c = (this.a ? this.a : this.b);
                    this.a = !this.a;
                    this.repaint();
                    this.postEvent(new Event(this, 1001, null));
                }
                this.b = false;
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
    
    public aO(final Image a, final Image b) {
        this.b = false;
        this.a = false;
        this.a = a;
        this.c = this.a;
        this.b = b;
        this.resize(a.getWidth(this), a.getHeight(this));
        this.validate();
    }
}
