// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class bf extends MouseAdapter
{
    final /* synthetic */ bu a;
    
    bf(final bu a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a.a(false, 0, 0, null);
    }
}
