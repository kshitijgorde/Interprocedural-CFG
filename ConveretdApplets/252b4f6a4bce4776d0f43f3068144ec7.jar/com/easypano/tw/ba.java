// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class ba extends MouseAdapter
{
    final /* synthetic */ dc a;
    
    ba(final dc a) {
        this.a = a;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.a.b(dc.a(this.a, mouseEvent.getPoint()));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a.b(-1);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a.c(dc.a(this.a, mouseEvent.getPoint()));
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a.d(dc.a(this.a, mouseEvent.getPoint()));
    }
}
