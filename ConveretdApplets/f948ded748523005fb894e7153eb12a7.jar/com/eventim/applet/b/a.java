// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class a extends MouseAdapter
{
    private final r a;
    
    a(final r a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.a.b(r.b(this.a).h());
        this.a.a(0);
        this.a.repaint();
    }
}
