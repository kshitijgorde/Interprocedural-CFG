// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class be extends MouseAdapter
{
    final /* synthetic */ o a;
    
    be(final o a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        o.a(this.a, mouseEvent.getPoint());
    }
}
