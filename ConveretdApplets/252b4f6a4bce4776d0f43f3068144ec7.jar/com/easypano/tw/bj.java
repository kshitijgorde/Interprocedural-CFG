// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bj extends MouseMotionAdapter
{
    final /* synthetic */ dc a;
    
    bj(final dc a) {
        this.a = a;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.a.b(dc.a(this.a, mouseEvent.getPoint()));
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.a.c(dc.a(this.a, mouseEvent.getPoint()));
    }
}
