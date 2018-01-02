// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bl extends MouseMotionAdapter
{
    final /* synthetic */ bu a;
    
    bl(final bu a) {
        this.a = a;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final du z = bu.z(this.a);
        if (!g.q) {
            if (z == null) {
                return;
            }
            bu.z(this.a);
        }
        z.a(this.a, this.a.Q, mouseEvent.getX(), mouseEvent.getY());
    }
}
