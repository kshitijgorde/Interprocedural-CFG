// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.b.b;

public class Q extends am
{
    protected boolean a;
    protected String b;
    protected b c;
    
    public Q(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        if (uiProperties.a(s + ".text") != null) {
            this.b = uiProperties2.a(uiProperties.a(s + ".text"));
        }
    }
    
    public void a() {
        if (this.c != null) {
            this.c.c();
        }
    }
    
    public void a(final a a) {
        if (this.K) {
            if (this.c == null) {
                this.c = this.N.c(this.G);
            }
            else {
                a.a(this.c, this.G.x, this.G.y, null);
            }
            if (this.a) {
                a.a(Color.red);
                a.c(this.G.x + 2, this.G.y + 2, this.G.width - 2, this.G.height - 2);
            }
            a.c(this.G.x + 1, this.G.y + 1, this.G.width - 1, this.G.height - 1);
            if (this.b != null) {
                aL.a(a, this.G, this.b, this.N.I(), 1);
            }
            a.a(Color.black);
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return this.N.a(new j(this), mouseEvent);
    }
}
