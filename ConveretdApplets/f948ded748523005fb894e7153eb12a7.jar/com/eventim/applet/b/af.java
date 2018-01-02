// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class af extends MouseAdapter
{
    private final r a;
    
    af(final r a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (r.a(this.a) == r.b || (r.a(this.a) == 0 && r.b(this.a).n())) {
            r.b(this.a).a(this.a.a(mouseEvent.getPoint()));
        }
    }
}
