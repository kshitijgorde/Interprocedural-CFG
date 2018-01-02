// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import java.awt.Rectangle;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.b.b;
import java.util.Vector;

public class E extends am
{
    protected Vector a;
    protected Vector b;
    protected Vector c;
    protected Vector d;
    protected Vector e;
    protected int f;
    protected boolean g;
    protected b h;
    public static final Integer i;
    public static final Integer j;
    public static final Integer k;
    public static final Integer l;
    
    public E(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.a = new Vector();
        this.b = new Vector();
        this.c = new Vector();
        this.d = new Vector();
        this.e = new Vector();
        this.f = -1;
        this.g = (uiProperties.a(s + ".oneshot") != null);
        int n = 0;
        for (String s2 = uiProperties.a(s + ".subprefix." + n++); s2 != null; s2 = uiProperties.a(s + ".subprefix." + n++)) {
            final b c = ap.c(uiProperties.a(s2 + ".on"));
            final b c2 = ap.c(uiProperties.a(s2 + ".off"));
            final b c3 = ap.c(uiProperties.a(s2 + ".disabled"));
            final Rectangle a = aL.a(uiProperties, s2 + ".r");
            if (c != null && c2 != null && c3 != null) {
                this.a.addElement(c);
                this.b.addElement(c2);
                this.c.addElement(c3);
                this.d.addElement(a);
                this.e.addElement(E.i);
            }
        }
    }
    
    public void a() {
        if (this.h != null) {
            this.h.c();
        }
    }
    
    public void a(final a a) {
        if (this.K) {
            if (this.h == null) {
                this.h = this.N.c(this.G);
            }
            else {
                a.a(this.h, this.G.x, this.G.y, null);
            }
            for (int i = 0; i < this.d.size(); ++i) {
                final Rectangle rectangle = this.d.elementAt(i);
                b b = null;
                if (this.e.elementAt(i) == E.j || this.f == i) {
                    b = this.a.elementAt(i);
                }
                else if (this.e.elementAt(i) == E.i) {
                    b = this.b.elementAt(i);
                }
                else if (this.e.elementAt(i) == E.k) {
                    b = this.c.elementAt(i);
                }
                if (b != null) {
                    a.a(b, rectangle.x, rectangle.y, null);
                }
            }
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        for (int i = 0; i < this.d.size(); ++i) {
            final Rectangle rectangle = this.d.elementAt(i);
            final Integer n = this.e.elementAt(i);
            if (n != E.k && n != E.l && rectangle.contains(point)) {
                return this.N.a(new ak(this), mouseEvent);
            }
        }
        return false;
    }
    
    static {
        i = 0;
        j = 1;
        k = 2;
        l = 4;
    }
}
