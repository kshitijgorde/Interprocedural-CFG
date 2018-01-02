// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;

class c extends Panel
{
    protected String a;
    protected MediaTracker b;
    protected Image c;
    protected Graphics d;
    
    public c(final a a) {
        this.a = "";
        this.b = null;
        this.c = null;
        this.d = null;
        this.setLayout(null);
        this.setBackground(Color.white);
        this.b = new MediaTracker(a);
    }
    
    protected void a(final String a) {
        final int a2 = RotationImageFilter.a;
        String s = a;
        Label_0049: {
            if (a2 == 0) {
                if (a == null) {
                    break Label_0049;
                }
                s = a;
            }
            if (s.length() > 0) {
                this.a = a;
                this.a = String.valueOf(this.a).concat(String.valueOf('/'));
                if (a2 == 0) {
                    return;
                }
            }
        }
        this.a = "";
    }
    
    public void setSize(final int n, final int n2) {
        this.d = null;
        this.c = null;
        super.setSize(n, n2);
    }
    
    public void doLayout() {
        super.doLayout();
        this.d = null;
        this.c = null;
        this.getBounds();
    }
    
    protected void a(final Graphics graphics) {
        final int a = RotationImageFilter.a;
        c c = this;
        c c2 = null;
        Label_0061: {
            Label_0060: {
                if (a == 0) {
                    if (this.c != null) {
                        c2 = this;
                        if (a != 0) {
                            break Label_0061;
                        }
                        if (this.d != null) {
                            break Label_0060;
                        }
                    }
                    c = this;
                }
                final Dimension size = c.getSize();
                this.c = this.createImage(size.width, size.height);
                this.d = this.c.getGraphics();
            }
            c2 = this;
        }
        final Rectangle bounds = c2.getBounds();
        c c3 = this;
        c c4 = this;
        if (a == 0) {
            if (this.d == null) {
                return;
            }
            c3 = this;
            c4 = this;
        }
        final Graphics d;
        Label_0145: {
            if (a == 0) {
                if (c4.getBackground() == null) {
                    return;
                }
                this.d.setColor(this.getBackground());
                d = this.d;
                if (a != 0) {
                    break Label_0145;
                }
                d.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                this.b(this.d);
                c3 = this;
            }
            if (c3.c == null) {
                return;
            }
        }
        d.drawImage(this.c, 0, 0, this);
    }
    
    protected void b(final Graphics graphics) {
    }
    
    public void update(final Graphics graphics) {
        this.a(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.a(graphics);
    }
}
