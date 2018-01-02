// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Frame;

class AppFrame extends Frame implements WindowListener
{
    private static final long serialVersionUID = 1L;
    
    public AppFrame(final String s) {
        super(s);
        this.addWindowListener(this);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
