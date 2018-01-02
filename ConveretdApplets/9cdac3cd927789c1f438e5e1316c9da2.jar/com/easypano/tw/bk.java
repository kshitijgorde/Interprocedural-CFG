// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bk extends MouseMotionAdapter
{
    final /* synthetic */ l a;
    
    bk(final l a) {
        this.a = a;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.a.m.a(mouseEvent.getX(), mouseEvent.getY());
    }
}
