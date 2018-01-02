// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

final class l extends WindowAdapter
{
    private final r a;
    
    l(final r a) {
        this.a = a;
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        r.a(this.a).setValue(new Integer(-1));
    }
    
    public final void windowDeactivated(final WindowEvent windowEvent) {
        this.a.toFront();
    }
}
