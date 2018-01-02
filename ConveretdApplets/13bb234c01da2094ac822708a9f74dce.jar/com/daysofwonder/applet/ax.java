// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.MouseEvent;
import com.daysofwonder.b.a;
import com.daysofwonder.util.UIProperties;
import java.awt.Color;

public class ax extends am
{
    protected Color a;
    
    public ax(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.a = aL.b(uiProperties, s + ".color");
    }
    
    public void a() {
    }
    
    public void a(final a a) {
        if (this.K && this.a != null) {
            final Color c = a.c();
            a.a(this.a);
            a.d(this.G.x, this.G.y, this.G.width, this.G.height);
            a.a(c);
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
}
