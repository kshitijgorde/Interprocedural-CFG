// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.util.t;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.b.b;

public class al extends am
{
    protected boolean a;
    protected b b;
    protected b c;
    protected b d;
    protected b e;
    protected b f;
    protected boolean g;
    protected boolean h;
    
    public al(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.b = ap.c(uiProperties.a(s + ".on"));
        this.c = ap.c(uiProperties.a(s + ".off"));
        this.d = ap.c(uiProperties.a(s + ".disabled"));
        this.e = ap.c(uiProperties.a(s + ".roll"));
        if (this.b == null || this.c == null) {
            t.a("ButtonControl: NULL IMAGE: " + this.l());
        }
    }
    
    public al(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2, final boolean g) {
        this(ap, s, uiProperties, uiProperties2);
        this.g = g;
    }
    
    public boolean d_() {
        return this.e != null && this.J;
    }
    
    public void a() {
        if (this.f != null) {
            this.f.c();
        }
    }
    
    public void a(final a a) {
        if (this.K) {
            if (this.N.B()) {
                if (this.f == null) {
                    if (!this.g) {
                        this.f = this.N.c(this.G);
                    }
                    else {
                        this.f = this.N.b(this.G);
                    }
                }
                else {
                    a.a(this.f, this.G.x, this.G.y, null);
                }
            }
            a.a(this.J ? (this.a ? this.b : ((this.h && this.e != null) ? this.e : this.c)) : this.d, this.G.x, this.G.y, null);
        }
    }
    
    public void d() {
        this.a = true;
        this.u();
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException ex) {}
        this.a = false;
        this.u();
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return this.K && this.J && this.N.a(new x(this), mouseEvent);
    }
    
    public void c(final MouseEvent mouseEvent) {
        this.h = true;
    }
    
    public void d(final MouseEvent mouseEvent) {
        this.h = false;
    }
    
    public void e(final MouseEvent mouseEvent) {
        this.h = true;
    }
    
    public void b(final boolean a) {
        this.a = a;
    }
    
    public boolean e() {
        return this.a;
    }
    
    public void a(final boolean b) {
        super.a(b);
    }
    
    public void c(final boolean b) {
        super.c(b);
    }
}
