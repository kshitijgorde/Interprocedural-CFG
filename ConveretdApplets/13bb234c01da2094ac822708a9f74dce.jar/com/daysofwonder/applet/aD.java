// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.b.b;

public class aD extends am
{
    protected b a;
    
    public aD(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.a = ap.c(uiProperties.a(s + ".back"));
    }
    
    public void a() {
    }
    
    public void a(final a a) {
        if (this.K) {
            a.a(this.a, this.G.x, this.G.y, null);
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
}
