// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.event.WindowAdapter;

public class WindowDestroyer extends WindowAdapter
{
    private Gamelication owner;
    private Frame mainScreen;
    
    public WindowDestroyer(final Frame mainScreen, final Gamelication owner) {
        this.mainScreen = mainScreen;
        this.owner = owner;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getWindow() == this.mainScreen) {
            this.owner.stop();
            System.exit(0);
            return;
        }
        windowEvent.getWindow().dispose();
    }
}
