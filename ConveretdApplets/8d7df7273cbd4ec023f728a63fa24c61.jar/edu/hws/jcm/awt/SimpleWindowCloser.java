// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class SimpleWindowCloser extends WindowAdapter
{
    public boolean exitSystem;
    
    public SimpleWindowCloser() {
        this(false);
    }
    
    public SimpleWindowCloser(final boolean exitSystem) {
        this.exitSystem = exitSystem;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        ((Window)windowEvent.getSource()).dispose();
        if (this.exitSystem) {
            System.exit(0);
        }
    }
}
