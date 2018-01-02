// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.util.UIProperties;
import java.util.Vector;
import java.awt.Rectangle;
import com.daysofwonder.b.b;

public class aB extends am implements Y
{
    protected b a;
    protected b b;
    protected b c;
    protected Rectangle d;
    protected Rectangle e;
    protected Vector f;
    protected boolean g;
    protected int h;
    protected L i;
    protected boolean j;
    
    public aB(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.f = new Vector();
        this.h = -1;
        this.j = true;
        this.c = ap.c(uiProperties.a(s + ".track"));
        this.a = ap.c(uiProperties.a(s + ".arrowon"));
        this.b = ap.c(uiProperties.a(s + ".arrowoff"));
        if (uiProperties.a(s + ".below") != null) {
            this.j = !uiProperties.a(s + ".below").equals("false");
        }
        this.d = aL.c(this.G, aL.a(uiProperties, s + ".arrow.r"));
        this.e = aL.c(this.G, aL.a(uiProperties, s + ".text.r"));
        for (int n = 0; uiProperties.a(s + ".default." + n) != null; ++n) {
            if (uiProperties2.b(uiProperties.a(s + ".default." + n)) != null) {
                this.a(uiProperties2.b(uiProperties.a(s + ".default." + n)));
            }
        }
        if (uiProperties.a(s + ".selected") != null) {
            this.h = Integer.parseInt(uiProperties.a(s + ".selected"));
        }
        this.h = ((this.f.size() == 0) ? -1 : Math.min(this.h, this.f.size()));
    }
    
    public void a() {
    }
    
    public void a(final a a) {
        if (this.K) {
            a.a(this.c, this.G.x, this.G.y, null);
            a.a(this.g ? this.a : this.b, this.d.x, this.d.y, null);
            final a a2 = a.a(this.e.x, this.e.y, this.e.width, this.e.height);
            String c = "";
            if (this.h > -1 && this.h < this.f.size()) {
                c = this.f.elementAt(this.h).c();
            }
            final FontMetrics d = a2.d();
            a2.a(Color.black);
            a2.a(c, 4, d.getMaxAscent());
            a2.g();
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        (this.i = this.N.Y()).b(this.P);
        this.i.a(this, null);
        if (this.j) {
            this.N.a(this.i, this.G.x, this.G.y + this.G.height, this.G.width, 0);
        }
        else {
            this.N.a(this.i, this.G.x, this.G.y, this.G.width, 1);
        }
        this.g = true;
        return this.i.a(mouseEvent);
    }
    
    public void a(final String s) {
        this.f.addElement(new H(s));
        if (this.h < 0) {
            this.h = 0;
        }
    }
    
    public int b() {
        return this.h;
    }
    
    public Vector a(final Object o) {
        return this.f;
    }
    
    public void b(final int n) {
        this.a(n);
    }
    
    public void a(final int h) {
        if (h != -1) {
            this.h = h;
        }
    }
    
    public void c() {
        this.g = false;
    }
}
