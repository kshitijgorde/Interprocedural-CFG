// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.Image;
import java.awt.Color;
import java.awt.Panel;

public class scroll extends Panel
{
    public boolean a;
    Color b;
    Color c;
    Color d;
    Color e;
    Color f;
    int g;
    menu_item[] h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    boolean p;
    boolean q;
    Image r;
    Image s;
    Component t;
    Container u;
    
    public scroll(final Component t, final Container u, final Image s, final menu_item menu_item, final menu_item menu_item2) {
        this.t = t;
        this.u = u;
        this.s = s;
        this.q = false;
        this.g = -1;
        this.m = 16;
        this.n = 16;
        this.o = 32;
        this.p = false;
        (this.h = new menu_item[2])[0] = menu_item;
        this.h[1] = menu_item2;
        this.b = new Color(216, 215, 200);
        this.c = new Color(195, 195, 195);
        this.d = new Color(255, 255, 255);
        this.e = new Color(129, 129, 129);
        this.f = new Color(0, 0, 0);
    }
    
    public boolean a(final scroll scroll) {
        if (this.h.length != scroll.h.length) {
            return false;
        }
        for (int i = 0; i < this.h.length; ++i) {
            if (!this.h[i].a(scroll.h[i])) {
                return false;
            }
        }
        return true;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.k + this.m + this.l, this.i + this.o + this.j);
    }
    
    public void setSize(final int n, final int o) {
        this.o = o;
        final int n2 = (n - this.m) / 2;
        this.l = n2;
        this.k = n2;
        this.j = n2;
        this.i = n2;
        super.setSize(n, o);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.r = utils.a(this, this.size().width, this.size().height);
        if (this.r != null) {
            this.a(this.r.getGraphics());
            graphics.drawImage(this.r, 0, 0, this);
        }
    }
    
    void a(final Graphics graphics, final int n, int n2, final int n3) {
        graphics.setColor(this.f);
        graphics.drawLine(n, n2, n, n2);
        n2 += n3;
        graphics.drawLine(n - 1, n2, n + 1, n2);
        n2 += n3;
        graphics.drawLine(n - 2, n2, n + 2, n2);
        n2 += n3;
        graphics.drawLine(n - 3, n2, n + 3, n2);
    }
    
    void a(final Graphics graphics) {
        utils.a(graphics, this.b, 0, 0, this.m, this.o);
        if (this.s != null) {
            final Point location = this.getLocation();
            graphics.drawImage(this.s, -location.x, -location.y, this);
        }
        this.a(graphics, this.k, this.i, this.p && this.g == 0, 1);
        this.a(graphics, this.k, this.o - this.n - this.j, this.p && this.g == 1, -1);
    }
    
    void a(final Graphics graphics, final int n, final int n2, final boolean b, final int n3) {
        final int n4 = n + this.m - 1;
        final int n5 = n2 + this.n - 1;
        final int n6 = n4;
        final int n7 = n5;
        int n8;
        int n9;
        if (n3 == 1) {
            n8 = (n4 - n + 1) / 2;
            n9 = n2 + 3;
        }
        else {
            n8 = (n6 - n + 1) / 2;
            n9 = n7 - 3;
        }
        utils.a(graphics, this.c, n, n2, n4 - n + 1, n5 - n2 + 1);
        if (b) {
            graphics.setColor(this.e);
        }
        else {
            graphics.setColor(this.c);
        }
        graphics.drawLine(n, n2, n4 - 1, n2);
        graphics.drawLine(n, n2, n, n5 - 1);
        if (!b) {
            graphics.setColor(this.d);
            graphics.drawLine(n + 1, n2 + 1, n4 - 2, n2 + 1);
            graphics.drawLine(n + 1, n2 + 1, n + 1, n5 - 2);
            graphics.setColor(this.e);
            graphics.drawLine(n4 - 1, n2 + 1, n6 - 1, n7 - 1);
            graphics.drawLine(n + 1, n5 - 1, n6 - 1, n7 - 1);
        }
        if (b) {
            graphics.setColor(this.e);
        }
        else {
            graphics.setColor(this.f);
        }
        graphics.drawLine(n4, n2, n6, n7);
        graphics.drawLine(n, n5, n6, n7);
        this.a(graphics, n8, n9, n3);
        this.a(graphics, n8, n9 + n3 * 4, n3);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 || event.id == 1005 || event.id == 203 || event.id == 505) {
            this.a((Event)null);
            this.repaint();
            return true;
        }
        if (event.id == 1004 || event.id == 503 || event.id == 506 || event.id == 504) {
            this.a(event);
            this.repaint();
            return true;
        }
        if (event.id == 501) {
            this.p = true;
            this.a(event);
            this.repaint();
            return true;
        }
        if (event.id == 502) {
            this.p = false;
            this.a(event);
            this.repaint();
            if (this.g >= 0) {
                event.arg = this.h[this.g];
                if (this.a) {
                    this.setCursor(new Cursor(3));
                }
                final boolean handleEvent = this.t.handleEvent(event);
                this.setCursor(new Cursor(0));
                return handleEvent;
            }
        }
        return super.handleEvent(event);
    }
    
    void a(final Event event) {
        this.g = -1;
        if (event != null) {
            final int x = event.x;
            final int y = event.y;
            if (x >= this.k && x < this.k + this.m) {
                if (y >= this.i && y < this.i + this.n) {
                    this.g = 0;
                }
                else if (y >= this.o - this.j - this.n && y <= this.o - this.j) {
                    this.g = 1;
                }
            }
        }
        if (this.g >= 0) {
            if (!this.q) {
                this.q = true;
                this.setCursor(new Cursor(12));
            }
        }
        else if (this.q) {
            this.q = false;
            this.setCursor(new Cursor(0));
        }
    }
}
