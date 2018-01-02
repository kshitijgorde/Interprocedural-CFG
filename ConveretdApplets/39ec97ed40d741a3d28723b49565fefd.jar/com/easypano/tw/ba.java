// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class ba extends MouseAdapter
{
    final /* synthetic */ l a;
    
    ba(final l a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a.m.a(mouseEvent.getX(), mouseEvent.getY());
    }
}
