// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class g extends MouseAdapter
{
    private final u a;
    
    g(final u a) {
        this.a = a;
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        u.b(this.a).d().a(u.a(this.a).getText());
        u.b(this.a).a();
    }
}
