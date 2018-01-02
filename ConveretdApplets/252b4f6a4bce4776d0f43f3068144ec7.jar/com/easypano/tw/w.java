// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class w extends MouseAdapter
{
    final /* synthetic */ ct a;
    
    w(final ct a) {
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
        final ct a = this.a;
        if (h.q == 0) {
            if (!ct.a(a)) {
                return;
            }
            final ct a2 = this.a;
        }
        ct.a(a, mouseEvent.getPoint());
    }
}
