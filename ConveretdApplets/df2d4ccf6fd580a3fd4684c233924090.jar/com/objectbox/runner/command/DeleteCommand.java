// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.command;

import java.util.Vector;
import com.objectbox.runner.gui.JBManagerPanel;

public class DeleteCommand extends AbstractCommand
{
    private JBManagerPanel jbmanager;
    private Vector backupVec;
    
    public DeleteCommand(final JBManagerPanel jbmanager) {
        this.backupVec = new Vector();
        super.name = "delete";
        this.jbmanager = jbmanager;
    }
    
    public boolean doIt() {
        try {
            this.jbmanager.delete();
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
