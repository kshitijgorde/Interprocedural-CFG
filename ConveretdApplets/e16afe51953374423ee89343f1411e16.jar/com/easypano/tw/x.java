// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class x extends MouseAdapter
{
    final /* synthetic */ k a;
    
    x(final k a) {
        this.a = a;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final k a = this.a;
        final boolean visible = this.a.m.isVisible();
        if (!h.q && !visible) {}
        a.d(visible);
    }
}
