// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class i extends MouseAdapter
{
    private final EventimApplet a;
    
    i(final EventimApplet a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.a.q();
    }
}
