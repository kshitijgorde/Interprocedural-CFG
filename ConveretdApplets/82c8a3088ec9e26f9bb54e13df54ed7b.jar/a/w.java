// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public final class w extends Canvas implements aA
{
    private Image q;
    private Image w;
    private Image e;
    private boolean q;
    private boolean w;
    private boolean e;
    private boolean r;
    private Image r;
    private String q;
    private String w;
    
    public static w q(final Image image, final Image image2, final Image image3) {
        return new w(image, image2, image3, true);
    }
    
    public final void setEnabled(final boolean e) {
        if (this.e == e) {
            return;
        }
        if (!(this.e = e)) {
            this.r = ((this.e == null) ? this.q : this.e);
        }
        else {
            this.r = this.q;
            this.r = true;
        }
        super.enable(e);
        this.repaint();
    }
    
    public final boolean isEnabled() {
        return this.e;
    }
    
    public final void q() {
        if (this.e) {
            if (this.w) {
                this.r = this.q;
                this.repaint();
            }
            else {
                this.r = (this.r ? this.q : this.w);
                this.r = !this.r;
                this.repaint();
            }
            this.postEvent(new Event(this, 1001, null));
        }
    }
    
    public final boolean handleEvent(final Event event) {
        if (this.e) {
            switch (event.id) {
                case 504: {
                    if (this.q) {
                        this.r = ((!this.w && this.r) ? this.q : this.w);
                        this.repaint();
                    }
                    return true;
                }
                case 505: {
                    this.r = ((!this.w && this.r) ? this.w : this.q);
                    this.repaint();
                    return true;
                }
                case 501: {
                    this.q = true;
                    this.r = ((!this.w && this.r) ? this.q : this.w);
                    this.repaint();
                    return true;
                }
                case 502: {
                    if (this.inside(event.x, event.y) && this.q) {
                        this.q();
                    }
                    this.q = false;
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.r, 0, 0, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void q(final String q, final String s) {
        this.q = q;
        this.w = null;
    }
    
    public final String q(final Object o) {
        if (this.e) {
            return this.q;
        }
        return this.w;
    }
    
    private w(final Image q, final Image w, final Image e, final boolean b) {
        this.q = false;
        this.w = true;
        this.e = true;
        this.r = false;
        this.q = null;
        this.w = null;
        this.q = q;
        this.r = this.q;
        this.w = w;
        this.e = e;
        this.w = true;
        this.resize(q.getWidth(this), q.getHeight(this));
    }
}
