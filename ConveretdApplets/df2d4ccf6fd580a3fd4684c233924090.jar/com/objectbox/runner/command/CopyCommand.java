// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.command;

import com.objectbox.runner.gui.JBManagerPanel;

public class CopyCommand extends AbstractCommand
{
    private JBManagerPanel jbmanager;
    byte[] buffer;
    
    public CopyCommand(final JBManagerPanel jbmanager) {
        this.buffer = null;
        super.name = "copy";
        this.jbmanager = jbmanager;
    }
    
    public boolean doIt() {
        try {
            this.jbmanager.copy();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public boolean undoIt() {
        return true;
    }
}
