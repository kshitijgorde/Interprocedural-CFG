// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Canvas;

public final class aN extends Canvas implements ab
{
    private Image a;
    private Image b;
    private Image c;
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private Image d;
    public String a;
    
    public static aN a(final Image image, final Image image2, final Image image3) {
        return new aN(image, image2, image3);
    }
    
    public final Dimension minimumSize() {
        int width = this.d.getWidth(this);
        int height = this.d.getHeight(this);
        if (width < 0) {
            width = 2;
        }
        if (height < 0) {
            height = 2;
        }
        return new Dimension(width, height);
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void setEnabled(final boolean c) {
        if (this.c == c) {
            return;
        }
        if (!(this.c = c)) {
            this.d = ((this.c == null) ? this.a : this.c);
        }
        else {
            this.d = this.a;
            this.d = true;
        }
        super.enable(c);
        this.repaint();
    }
    
    public final boolean isEnabled() {
        return this.c;
    }
    
    public final void a() {
        if (this.c) {
            if (this.b) {
                this.d = this.a;
                this.repaint();
            }
            else {
                this.d = (this.d ? this.a : this.b);
                this.d = !this.d;
                this.repaint();
            }
            this.postEvent(new Event(this, 1001, null));
        }
    }
    
    public final boolean handleEvent(final Event event) {
        if (this.c) {
            switch (event.id) {
                case 504: {
                    if (this.a) {
                        this.d = ((!this.b && this.d) ? this.a : this.b);
                        this.repaint();
                    }
                    return true;
                }
                case 505: {
                    this.d = ((!this.b && this.d) ? this.b : this.a);
                    this.repaint();
                    return true;
                }
                case 501: {
                    this.a = true;
                    this.d = ((!this.b && this.d) ? this.a : this.b);
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
            }
        }
        return super.handleEvent(event);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.d, 0, 0, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final String a(final Object o) {
        if (this.c) {
            return this.a;
        }
        return null;
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.resize(this.getMinimumSize());
            return false;
        }
        return true;
    }
    
    private aN(final Image a, final Image b, final Image c) {
        this.a = false;
        this.b = true;
        this.c = true;
        this.d = false;
        this.a = null;
        this.a = a;
        if (a != null) {
            this.prepareImage(a, this);
        }
        if (b != null) {
            this.prepareImage(b, this);
        }
        if (c != null) {
            this.prepareImage(c, this);
        }
        this.d = this.a;
        this.b = b;
        this.c = c;
        this.b = true;
        this.resize(a.getWidth(this), a.getHeight(this));
    }
}
