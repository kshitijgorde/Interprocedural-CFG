// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.currcon;

import java.util.Enumeration;
import java.applet.AppletContext;
import javax.swing.SwingUtilities;
import javax.swing.JApplet;

class Broadcaster implements Runnable
{
    private final JApplet self;
    private final int currentCurrIndex;
    
    public void run() {
        final AppletContext ac = this.self.getAppletContext();
        if (ac != null) {
            final Enumeration otherApplets = ac.getApplets();
            if (otherApplets != null) {
                final Enumeration other = otherApplets;
                while (other.hasMoreElements()) {
                    final Object otherApplet = other.nextElement();
                    if (otherApplet instanceof CurrCon && otherApplet != this.self) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                ((CurrCon)otherApplet).currencyChangeListener(Broadcaster.this.currentCurrIndex);
                            }
                        });
                    }
                }
            }
        }
    }
    
    Broadcaster(final JApplet self, final int currentCurrIndex) {
        this.self = self;
        this.currentCurrIndex = currentCurrIndex;
    }
}
