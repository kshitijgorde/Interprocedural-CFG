// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class f extends MouseAdapter
{
    private final u a;
    
    f(final u a) {
        this.a = a;
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.a.a();
        this.a.repaint();
    }
}