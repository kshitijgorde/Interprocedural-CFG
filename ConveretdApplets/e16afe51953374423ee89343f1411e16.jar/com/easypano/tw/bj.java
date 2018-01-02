// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bj extends MouseMotionAdapter
{
    final /* synthetic */ dd a;
    
    bj(final dd a) {
        this.a = a;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.a.b(dd.a(this.a, mouseEvent.getPoint()));
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.a.c(dd.a(this.a, mouseEvent.getPoint()));
    }
}
