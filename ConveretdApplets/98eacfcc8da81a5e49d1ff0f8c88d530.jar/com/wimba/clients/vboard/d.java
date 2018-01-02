// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

final class d extends WindowAdapter
{
    private final a a;
    
    d(final a a) {
        this.a = a;
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        com.wimba.clients.vboard.a.a(this.a).setValue(new Integer(-1));
    }
    
    public final void windowDeactivated(final WindowEvent windowEvent) {
        this.a.toFront();
    }
}
