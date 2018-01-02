// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bh extends MouseMotionAdapter
{
    final /* synthetic */ f a;
    
    bh(final f a) {
        this.a = a;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final dt b = f.b(this.a);
        if (h.q == 0) {
            if (b == null) {
                return;
            }
            f.b(this.a);
        }
        b.a(this.a, this.a.b, mouseEvent.getX(), mouseEvent.getY());
    }
}
