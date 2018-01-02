// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class be extends MouseAdapter
{
    final /* synthetic */ n a;
    
    be(final n a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        n.a(this.a, mouseEvent.getPoint());
    }
}
