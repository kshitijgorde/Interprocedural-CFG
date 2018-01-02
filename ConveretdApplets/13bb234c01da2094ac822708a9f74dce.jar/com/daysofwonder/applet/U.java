// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.util.y;
import java.awt.FontMetrics;
import com.daysofwonder.b.a;
import java.awt.event.MouseEvent;
import com.daysofwonder.util.t;
import com.daysofwonder.util.UIProperties;
import java.awt.Color;
import com.daysofwonder.util.K;

public class U extends am
{
    private K a;
    private int b;
    private int c;
    private boolean d;
    private Color e;
    private Color f;
    private Color g;
    
    public U(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.a = new K();
        this.c = 0;
        this.e = Color.white;
        this.f = Color.black;
        this.g = Color.red;
        if (uiProperties.a(s + ".bgcolor") != null) {
            this.e = aL.b(uiProperties, s + ".bgcolor");
        }
        if (uiProperties.a(s + ".fgcolor") != null) {
            this.f = aL.b(uiProperties, s + ".fgcolor");
        }
        if (uiProperties.a(s + ".selcolor") != null) {
            this.g = aL.b(uiProperties, s + ".selcolor");
            t.a("HELP: " + s + " selcolor: " + this.g);
        }
        if (uiProperties.a(s + ".size") != null) {
            this.d = uiProperties.a(s + ".size").equals("small");
        }
        else {
            this.d = true;
        }
    }
    
    public void b() {
        ++this.b;
        if (this.b >= this.c) {
            this.b = this.c - 1;
        }
        this.u();
    }
    
    public void d() {
        --this.b;
        if (this.b < 0) {
            this.b = 0;
        }
        this.u();
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
    
    public void a() {
    }
    
    public void a(final String s) {
        if (s != null) {
            this.a.a(s);
            if (this.a.c() > 20) {
                this.a.b();
            }
            this.v();
        }
    }
    
    public void a(final a a) {
        if (!this.N.H()) {
            return;
        }
        final a a2 = a.a(this.G.x, this.G.y, this.G.width, this.G.height);
        a2.a(this.N.M());
        final Color c = a.c();
        a2.a(this.g);
        final FontMetrics d = a2.d();
        int n = this.G.height - d.getMaxDescent() - 1;
        final int n2 = d.getMaxDescent() + d.getMaxAscent() + 1;
        int c2 = 0;
        final y e = this.a.e();
        while (e.a()) {
            boolean a3 = false;
            final y e2 = aL.a(a2, (String)e.b(), this.G.width, true, this.d ? this.N.K() : this.N.J(), this.d ? this.N.M() : this.N.L()).e();
            while (e2.a()) {
                final String s = (String)e2.b();
                if (c2 >= this.b) {
                    a3 = aL.a(a2, s, 1, n, this.d ? this.N.K() : this.N.J(), this.d ? this.N.M() : this.N.L(), a3);
                    n -= n2;
                }
                ++c2;
            }
            a2.a(this.f);
        }
        a2.g();
        this.c = c2;
        a.a(c);
    }
}
