// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class v extends MouseAdapter
{
    final /* synthetic */ cu a;
    
    v(final cu a) {
        this.a = a;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.a.d(true);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a.d(false);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a.c(true);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a.c(false);
        final cu a = this.a;
        if (!g.q) {
            if (!cu.a(a)) {
                return;
            }
            final cu a2 = this.a;
        }
        cu.a(a, mouseEvent.getPoint());
    }
}
