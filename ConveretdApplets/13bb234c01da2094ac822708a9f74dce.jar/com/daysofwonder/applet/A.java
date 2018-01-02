// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import com.daysofwonder.b.a;
import com.daysofwonder.util.UIProperties;
import java.awt.Color;

public class A extends q
{
    private ap l;
    private float m;
    private int n;
    private Color o;
    private Color p;
    
    public A(final ap l, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(l, s, uiProperties, uiProperties2);
        this.m = 1.0f;
        this.l = l;
        this.o = aL.b(uiProperties, s + ".backcolor");
        this.p = aL.b(uiProperties, s + ".bordercolor");
    }
    
    public synchronized void a(final a a) {
        if (this.K) {
            a.a(3, a(0.0f, 1.0f, this.n / 10.0f));
            super.a(a);
            ++this.n;
            a.a();
        }
    }
    
    public void a() {
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
    
    private static final float a(final float n, final float n2, float n3) {
        if (n3 < 0.0f) {
            n3 = 0.0f;
        }
        else if (n3 > 1.0f) {
            n3 = 1.0f;
        }
        return n + n3 * (n2 - n);
    }
    
    protected void a(final a a, final Dimension dimension) {
        final Color c = a.c();
        a.a(this.o);
        a.d(this.G.x - 4, this.G.y, dimension.width, dimension.height + 6);
        a.a(this.p);
        a.c(this.G.x - 4, this.G.y, dimension.width, dimension.height + 6);
        a.a(c);
    }
}
