// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class bb extends MouseAdapter
{
    final /* synthetic */ m a;
    
    bb(final m a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a.m.a(mouseEvent.getX(), mouseEvent.getY());
    }
}
