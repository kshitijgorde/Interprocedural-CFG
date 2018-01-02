// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class cz extends Canvas implements aB
{
    private Image q;
    private Image e;
    private Image a;
    private boolean h;
    private boolean d;
    private boolean e;
    private boolean i;
    private Image o;
    private String l;
    private String m;
    private String P;
    
    public static cz a(final Image image, final Image image2, final Image image3) {
        return new cz(image, image2, image3, true);
    }
    
    public void setEnabled(final boolean e) {
        if (this.e == e) {
            return;
        }
        if (!(this.e = e)) {
            this.o = ((this.a != null) ? this.a : this.q);
        }
        else {
            this.o = this.q;
            this.i = true;
        }
        super.enable(e);
        this.repaint();
    }
    
    public boolean isEnabled() {
        return this.e;
    }
    
    public void a() {
        if (this.e) {
            if (this.d) {
                this.o = this.q;
                this.repaint();
            }
            else {
                this.o = (this.i ? this.q : this.e);
                this.i = !this.i;
                this.repaint();
            }
            this.postEvent(new Event(this, 1001, null));
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.e) {
            switch (event.id) {
                case 504: {
                    if (this.h) {
                        this.o = (this.d ? this.e : (this.i ? this.q : this.e));
                        this.repaint();
                    }
                    return true;
                }
                case 505: {
                    this.o = (this.d ? this.q : (this.i ? this.e : this.q));
                    this.repaint();
                    return true;
                }
                case 501: {
                    this.h = true;
                    this.o = (this.d ? this.e : (this.i ? this.q : this.e));
                    this.repaint();
                    return true;
                }
                case 502: {
                    if (this.inside(event.x, event.y) && this.h) {
                        this.a();
                    }
                    this.h = false;
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.o, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final String m, final String p2) {
        this.m = m;
        this.P = p2;
    }
    
    public String a(final Object o) {
        if (this.e) {
            return this.m;
        }
        return this.P;
    }
    
    private cz(final Image q, final Image e, final Image a, final boolean d) {
        this.h = false;
        this.d = true;
        this.e = true;
        this.i = false;
        this.l = null;
        this.m = null;
        this.P = null;
        this.q = q;
        this.o = this.q;
        this.e = e;
        this.a = a;
        this.d = d;
        this.resize(q.getWidth(this), q.getHeight(this));
    }
}
