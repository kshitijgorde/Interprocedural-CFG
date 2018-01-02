// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class aa extends MouseAdapter
{
    private final r a;
    
    aa(final r a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (r.b(this.a).m() && r.c(this.a) != null) {
            this.a.a(r.b(this.a).g());
            this.a.a(r.b);
            this.a.repaint();
        }
    }
}
