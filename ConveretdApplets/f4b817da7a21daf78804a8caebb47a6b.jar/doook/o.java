// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class o extends Canvas implements aj
{
    private Image b;
    private Image a;
    private Image c;
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private Image d;
    private String d;
    private String e;
    private String f;
    
    public static o a(final Image image, final Image image2, final Image image3) {
        return new o(image, image2, image3, true);
    }
    
    public void setEnabled(final boolean c) {
        if (this.c == c) {
            return;
        }
        if (!(this.c = c)) {
            this.d = ((this.c != null) ? this.c : this.b);
        }
        else {
            this.d = this.b;
            this.d = true;
        }
        super.enable(c);
        this.repaint();
    }
    
    public boolean isEnabled() {
        return this.c;
    }
    
    public void c() {
        if (this.c) {
            if (this.b) {
                this.d = this.b;
                this.repaint();
            }
            else {
                this.d = (this.d ? this.b : this.a);
                this.d = !this.d;
                this.repaint();
            }
            this.postEvent(new Event(this, 1001, null));
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.c) {
            switch (event.id) {
                case 504: {
                    if (this.a) {
                        this.d = (this.b ? this.a : (this.d ? this.b : this.a));
                        this.repaint();
                    }
                    return true;
                }
                case 505: {
                    this.d = (this.b ? this.b : (this.d ? this.a : this.b));
                    this.repaint();
                    return true;
                }
                case 501: {
                    this.a = true;
                    this.d = (this.b ? this.a : (this.d ? this.b : this.a));
                    this.repaint();
                    return true;
                }
                case 502: {
                    if (this.inside(event.x, event.y) && this.a) {
                        this.c();
                    }
                    this.a = false;
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.d, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final String e, final String f) {
        this.e = e;
        this.f = f;
    }
    
    public String a(final Object o) {
        if (this.c) {
            return this.e;
        }
        return this.f;
    }
    
    private o(final Image b, final Image a, final Image c, final boolean b2) {
        this.a = false;
        this.b = true;
        this.c = true;
        this.d = false;
        this.d = null;
        this.e = null;
        this.f = null;
        this.b = b;
        this.d = this.b;
        this.a = a;
        this.c = c;
        this.b = b2;
        this.resize(b.getWidth(this), b.getHeight(this));
    }
}
