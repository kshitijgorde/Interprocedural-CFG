// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.MouseEvent;
import com.daysofwonder.util.UIProperties;

public class r extends al
{
    public r(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
    }
    
    public void a() {
        if (this.f != null) {
            this.f.c();
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return this.N.a(new t(this, null), mouseEvent);
    }
}
