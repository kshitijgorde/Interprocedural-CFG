// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.util.y;
import java.awt.FontMetrics;
import java.awt.Color;
import com.daysofwonder.b.a;
import java.awt.event.MouseEvent;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.util.K;

public class h extends am
{
    private K a;
    private int b;
    private int c;
    private int d;
    
    public h(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.a = new K();
        this.b = 20;
        this.d = 0;
        uiProperties.a(s + ".up");
        uiProperties.a(s + ".down");
        this.b = uiProperties.a(s + ".maxmsg", 20);
    }
    
    public void a() {
    }
    
    public void b_() {
        ++this.c;
        if (this.c >= this.d) {
            this.c = this.d - 1;
        }
        this.u();
    }
    
    public void c() {
        --this.c;
        if (this.c < 0) {
            this.c = 0;
        }
        this.u();
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
    
    public void a(final a a) {
        if (!this.N.H()) {
            return;
        }
        final a a2 = a.a(this.G.x, this.G.y, this.G.width, this.G.height);
        a2.a(this.N.M());
        a2.a(Color.red);
        final FontMetrics d = a2.d();
        int n = this.G.height - d.getMaxDescent() - 1;
        final int n2 = d.getMaxDescent() + d.getMaxAscent() + 1;
        int d2 = 0;
        final y e = this.a.e();
        while (e.a()) {
            final y e2 = aL.a((String)e.b(), this.G.width, d).e();
            while (e2.a()) {
                final String s = (String)e2.b();
                if (d2 >= this.c) {
                    aL.a(a2, s, 1, n, this.N.J(), this.N.L());
                    n -= n2;
                }
                ++d2;
            }
            a2.a(Color.black);
        }
        a2.g();
        this.d = d2;
    }
    
    public void a(final String s, final int n) {
        this.a(s, this.O.b("chat." + n));
    }
    
    public void a(final String s, final String s2) {
        if (s2 != null) {
            this.a.a("%b" + s + "%b: " + s2);
            if (this.a.c() > this.b) {
                this.a.b();
            }
        }
        this.v();
    }
}
