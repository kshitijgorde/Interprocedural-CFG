// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.daysofwonder.b.a;
import java.io.IOException;
import com.daysofwonder.util.t;
import com.daysofwonder.util.UIProperties;
import java.awt.Dimension;
import com.daysofwonder.util.K;

public class q extends l
{
    protected K i;
    protected K j;
    protected Dimension k;
    
    public q(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        try {
            if (this.b != null) {
                this.i = com.daysofwonder.applet.T.a(this.b);
            }
        }
        catch (IOException ex) {
            t.a("Malformed HTML: " + ex);
        }
        this.j = null;
        this.k = new Dimension(this.G.width, this.G.height);
    }
    
    public void a() {
    }
    
    public void a(final String b) {
        this.b = b;
        try {
            if (this.b != null) {
                this.i = com.daysofwonder.applet.T.a(this.b);
            }
        }
        catch (IOException ex) {
            t.a("Malformed HTML: " + ex);
        }
        this.g = true;
    }
    
    public void a(final a a) {
        if (this.K && this.b != null) {
            final Color c = a.c();
            if (this.d != null) {
                a.a(this.d);
            }
            final Font b = a.b();
            if (this.c != null) {
                a.a(this.c);
            }
            else if (this.e != -1) {
                a.a(b.deriveFont((float)this.e));
            }
            else if (this.h != 0) {
                a.a(b.deriveFont(this.h));
            }
            else if (this.h != 0 && this.e != -1) {
                a.a(b.deriveFont(this.h, this.e));
            }
            if (this.j == null || this.g) {
                this.k = new Dimension(this.G.width, this.G.height);
                this.j = com.daysofwonder.applet.T.a(this.i, a, this.k);
                this.b(a);
            }
            this.a(a, this.k);
            if (this.j != null) {
                a.a(this.G.x, this.G.y);
                com.daysofwonder.applet.T.b(this.j, a, this.k);
                a.a(-this.G.x, -this.G.y);
            }
            if (this.c != null || this.h != 0 || this.e != -1) {
                a.a(b);
            }
            if (this.d != null) {
                a.a(c);
            }
        }
    }
    
    protected void a(final a a, final Dimension dimension) {
    }
    
    public void b(final a a) {
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
}
