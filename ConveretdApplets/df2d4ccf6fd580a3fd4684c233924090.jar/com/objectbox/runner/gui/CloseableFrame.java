// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.applet.Applet;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Frame;

public class CloseableFrame extends Frame implements WindowListener
{
    private boolean done;
    
    public CloseableFrame() {
        this.done = true;
        this.addWindowListener(this);
        this.done = false;
    }
    
    public CloseableFrame(final String s) {
        super(s);
        this.done = true;
        this.addWindowListener(this);
        this.done = false;
    }
    
    public boolean isDone() {
        return this.done;
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        this.done = true;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        for (int i = 0; i < this.getComponents().length; ++i) {
            if (this.getComponent(i) instanceof Applet) {
                ((Applet)this.getComponent(i)).stop();
                ((Applet)this.getComponent(i)).destroy();
            }
            this.remove(i);
        }
        this.dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
