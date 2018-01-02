// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class aB extends Canvas implements aO
{
    private Image a;
    private Image b;
    private Image c;
    private boolean h;
    private boolean d;
    private boolean e;
    private boolean b;
    private Image j;
    private String p;
    private String q;
    private String I;
    
    public static aB a(final Image image, final Image image2, final Image image3) {
        return new aB(image, image2, image3, true);
    }
    
    public void setEnabled(final boolean e) {
        if (this.e == e) {
            return;
        }
        if (!(this.e = e)) {
            this.j = ((this.c != null) ? this.c : this.a);
        }
        else {
            this.j = this.a;
            this.b = true;
        }
        super.enable(e);
        this.repaint();
    }
    
    public boolean isEnabled() {
        return this.e;
    }
    
    public void f() {
        if (this.e) {
            if (this.d) {
                this.j = this.a;
                this.repaint();
            }
            else {
                this.j = (this.b ? this.a : this.b);
                this.b = !this.b;
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
                        this.j = (this.d ? this.b : (this.b ? this.a : this.b));
                        this.repaint();
                    }
                    return true;
                }
                case 505: {
                    this.j = (this.d ? this.a : (this.b ? this.b : this.a));
                    this.repaint();
                    return true;
                }
                case 501: {
                    this.h = true;
                    this.j = (this.d ? this.b : (this.b ? this.a : this.b));
                    this.repaint();
                    return true;
                }
                case 502: {
                    if (this.inside(event.x, event.y) && this.h) {
                        this.f();
                    }
                    this.h = false;
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.j, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final String q, final String i) {
        this.q = q;
        this.I = i;
    }
    
    public String a(final Object o) {
        if (this.e) {
            return this.q;
        }
        return this.I;
    }
    
    private aB(final Image a, final Image b, final Image c, final boolean d) {
        this.h = false;
        this.d = true;
        this.e = true;
        this.b = false;
        this.p = null;
        this.q = null;
        this.I = null;
        this.a = a;
        this.j = this.a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.resize(a.getWidth(this), a.getHeight(this));
    }
}
