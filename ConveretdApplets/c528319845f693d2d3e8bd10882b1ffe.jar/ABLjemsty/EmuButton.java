// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.awt.Point;
import java.awt.Color;
import java.awt.image.ImageObserver;
import ABLwidgets.backgroundable;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class EmuButton extends Canvas
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public Image g;
    public Image h;
    public int i;
    public int j;
    public Event k;
    public Component l;
    public boolean m;
    private boolean n;
    
    public EmuButton(final Component l, final int a, final int b, final Event k, final Image g, final Image h) {
        this.m = false;
        this.n = false;
        this.hide();
        this.a = a;
        this.b = b;
        this.k = k;
        this.l = l;
        this.g = g;
        this.h = h;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
    }
    
    public void a(final int n, final int n2) {
        this.a += n;
        this.b += n2;
        if (this.i > 0) {
            this.i += n;
        }
        if (this.j > 0) {
            this.j += n2;
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 504) {
            this.n = true;
            this.repaint();
        }
        if (event.id == 505) {
            this.n = false;
            this.repaint();
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        try {
            final Image n = ((backgroundable)this.getParent()).n();
            if (n != null) {
                final Point location = this.getLocation();
                graphics.drawImage(n, -location.x, -location.y, this);
            }
        }
        catch (Throwable t) {}
        Image image = this.g;
        if (this.n && this.h != null) {
            image = this.h;
        }
        if (this.e <= 0) {
            return;
        }
        if (image != null && image.getWidth(null) > 0) {
            graphics.drawImage(image, 0, 0, this.e, this.f, this);
            return;
        }
        for (int i = 0; i < this.e; ++i) {
            graphics.setColor(new Color(i % 2 * 255, i % 3 * 127, i % 5 * 63));
            graphics.drawLine(i, 0, i, this.f);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.clickCount > 1) {
            return true;
        }
        if (this.i > 0 && this.j > 0 && this.k != null && this.k.arg != null && this.k.arg instanceof StyleEventArg) {
            ((StyleEventArg)this.k.arg).b = this.i;
            ((StyleEventArg)this.k.arg).c = this.j;
        }
        this.l.handleEvent(this.k);
        return true;
    }
}
