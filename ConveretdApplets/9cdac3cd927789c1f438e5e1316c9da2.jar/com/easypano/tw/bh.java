// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bh extends MouseMotionAdapter
{
    final /* synthetic */ e a;
    
    bh(final e a) {
        this.a = a;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final du b = e.b(this.a);
        if (!g.q) {
            if (b == null) {
                return;
            }
            e.b(this.a);
        }
        b.a(this.a, this.a.b, mouseEvent.getX(), mouseEvent.getY());
    }
}
