// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bm extends MouseMotionAdapter
{
    final /* synthetic */ TWViewer a;
    
    bm(final TWViewer a) {
        this.a = a;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final du c = TWViewer.c(this.a);
        if (!h.q) {
            if (c == null) {
                return;
            }
            TWViewer.c(this.a);
        }
        c.a(this.a, null, mouseEvent.getX(), mouseEvent.getY());
    }
}
