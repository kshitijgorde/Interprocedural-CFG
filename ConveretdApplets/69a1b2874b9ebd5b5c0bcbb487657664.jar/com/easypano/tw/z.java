// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class z extends MouseAdapter
{
    final /* synthetic */ bp a;
    
    z(final bp a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        bp.a(this.a, mouseEvent.getPoint());
        if (new Rectangle(this.a.getBounds().width - 22, 5, 16, 16).contains(bp.a(this.a))) {
            this.a.setVisible(false);
        }
    }
}
