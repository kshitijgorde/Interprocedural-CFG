// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.command;

import com.objectbox.runner.gui.JBManagerPanel;

public class PasteCommand extends AbstractCommand
{
    private JBManagerPanel jbmanager;
    byte[] buffer;
    
    public PasteCommand(final JBManagerPanel jbmanager) {
        this.buffer = null;
        super.name = "paste";
        this.jbmanager = jbmanager;
    }
    
    public boolean doIt() {
        try {
            this.jbmanager.paste();
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
