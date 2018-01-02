// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.awt.Dimension;
import java.awt.Component;

class H implements D
{
    final /* synthetic */ PlayApplet a;
    
    H(final PlayApplet a) {
        this.a = a;
    }
    
    public void a(final boolean b) {
        System.out.println("Toggle FullScreen: " + b);
        if (b) {
            this.a.m_popout.add(this.a.m_display, "Center");
            final Dimension g = this.a.m_display.g();
            this.a.m_display.setPreferredSize(g);
            this.a.m_display.setMinimumSize(g);
            this.a.m_popout.pack();
            this.a.m_popout.setVisible(true);
        }
        else {
            this.a.add(this.a.m_display, "Center");
            this.a.validate();
            this.a.m_popout.setVisible(false);
        }
        this.a.repaint();
    }
    
    public String a(final String s) {
        return this.a.getParameter(s);
    }
}
