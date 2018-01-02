// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Frame;

class AppFrame extends Frame implements WindowListener
{
    public AppFrame(final String title) {
        super(title);
        this.addWindowListener(this);
    }
    
    public void windowClosing(final WindowEvent e) {
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }
    
    public void windowClosed(final WindowEvent e) {
    }
    
    public void windowDeactivated(final WindowEvent e) {
    }
    
    public void windowActivated(final WindowEvent e) {
    }
    
    public void windowDeiconified(final WindowEvent e) {
    }
    
    public void windowIconified(final WindowEvent e) {
    }
    
    public void windowOpened(final WindowEvent e) {
    }
}
