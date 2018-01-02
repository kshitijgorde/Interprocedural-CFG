// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class w extends MouseAdapter
{
    final /* synthetic */ j a;
    
    w(final j a) {
        this.a = a;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final j a = this.a;
        final boolean visible = this.a.m.isVisible();
        if (!g.q && !visible) {}
        a.d(visible);
    }
}
