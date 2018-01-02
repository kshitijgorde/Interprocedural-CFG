// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import com.daysofwonder.util.t;
import com.daysofwonder.b.a;
import com.daysofwonder.util.UIProperties;
import java.util.Vector;

public class L extends am
{
    private Y a;
    private int b;
    private int c;
    private Vector d;
    
    public L(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.d = new Vector();
    }
    
    public void a() {
    }
    
    public void a(final Y a, final Object o) {
        this.a = a;
        if (a != null) {
            this.a(a.a(o));
        }
    }
    
    public void a(final int x, final int y, final int width, final int c) {
        this.G.x = x;
        this.G.y = y;
        this.c = c;
        this.G.width = width;
        this.G.height = -1;
    }
    
    public void a(final Vector d) {
        this.d = d;
        this.G.width = -1;
    }
    
    public void b(final a a) {
        final FontMetrics d = a.d();
        this.G.height = 0;
        final int height = d.getHeight();
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.d.size(); ++i) {
            final P p = this.d.elementAt(i);
            if (p instanceof H) {
                final int n3 = d.stringWidth(((H)p).c()) + 4;
                if (n3 > n2) {
                    n2 = n3;
                }
            }
            n += height;
        }
        this.G.height = n + 4;
        this.b = d.getHeight();
        if (this.c == 1) {
            final Rectangle g = this.G;
            g.y -= this.G.height + 4;
        }
        if (n2 > this.G.width) {
            this.G.width = n2 + 4;
        }
        if (this.G.x + this.G.width > this.N.p().getWidth()) {
            this.G.x = this.N.p().getWidth() - this.G.width - 4;
        }
        t.a("popup size: " + this.G.width + ", " + this.G.height + " fLineHeight: " + this.b);
    }
    
    public void a(final a a) {
        if (this.K) {
            if (this.G.height == -1) {
                this.b(a);
            }
            a.a(Color.white);
            a.d(this.G.x, this.G.y, this.G.width, this.G.height);
            final FontMetrics d = a.d();
            final int b = this.b;
            int n = this.G.y + 2;
            final int n2 = this.G.x + 2;
            for (int i = 0; i < this.d.size(); ++i) {
                ((P)this.d.elementAt(i)).a(i, n2, n, this.G.width, b, d, a, this.a);
                n += b;
            }
            a.a(Color.black);
            a.c(this.G.x, this.G.y, this.G.width, this.G.height);
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return this.N.a(new N(this), mouseEvent);
    }
    
    public int f(final Point point) {
        final Rectangle rectangle = new Rectangle();
        rectangle.y = this.G.y + 2;
        rectangle.x = this.G.x + 2;
        rectangle.width = this.G.width - 4;
        rectangle.height = this.b;
        for (int i = 0; i < this.d.size(); ++i) {
            final P p = this.d.elementAt(i);
            if (rectangle.contains(point) && p instanceof H && p.d()) {
                return i;
            }
            final Rectangle rectangle2 = rectangle;
            rectangle2.y += this.b;
        }
        return -1;
    }
}
