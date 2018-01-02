// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public final class am extends Canvas
{
    public Image q;
    public Image w;
    public Image e;
    private boolean w;
    public boolean q;
    
    public final boolean isEnabled() {
        return true;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (this.w) {
                    this.e = (this.q ? this.q : this.w);
                    this.repaint();
                }
                return true;
            }
            case 505: {
                this.e = (this.q ? this.w : this.q);
                this.repaint();
                return true;
            }
            case 501: {
                this.w = true;
                this.e = (this.q ? this.q : this.w);
                this.repaint();
                return true;
            }
            case 502: {
                if (this.inside(event.x, event.y) && this.w) {
                    this.e = (this.q ? this.q : this.w);
                    this.q = !this.q;
                    this.repaint();
                    this.postEvent(new Event(this, 1001, null));
                }
                this.w = false;
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.e, 0, 0, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final Dimension preferredSize() {
        return new Dimension(this.q.getWidth(this), this.q.getHeight(this));
    }
    
    public final Dimension size() {
        return this.preferredSize();
    }
    
    public am(final Image q, final Image w) {
        this.w = false;
        this.q = false;
        this.q = q;
        this.e = this.q;
        this.w = w;
        this.resize(q.getWidth(this), q.getHeight(this));
        this.validate();
    }
}
