// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class F extends WindowAdapter
{
    final /* synthetic */ PlayApplet a;
    
    F(final PlayApplet a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        System.out.println("Closing frame.");
        this.a.m_display.b(false);
    }
}
