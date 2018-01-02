// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.command;

import com.objectbox.runner.gui.JBManagerPanel;

public class CutCommand extends AbstractCommand
{
    private JBManagerPanel jbmanager;
    byte[] buffer;
    
    public CutCommand(final JBManagerPanel jbmanager) {
        this.buffer = null;
        super.name = "cut";
        this.jbmanager = jbmanager;
    }
    
    public boolean doIt() {
        try {
            this.jbmanager.cut();
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
