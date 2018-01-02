// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.command;

import com.objectbox.runner.model.JBObjectWithProperties;
import com.objectbox.runner.gui.JBManagerPanel;

public class NewFolderCommand extends AbstractCommand
{
    private JBManagerPanel jbmanager;
    byte[] buffer;
    String foldername;
    JBObjectWithProperties properties;
    
    public NewFolderCommand(final JBManagerPanel jbmanager, final String foldername, final JBObjectWithProperties properties) {
        this.buffer = null;
        this.foldername = "";
        this.properties = null;
        super.name = "newfolder";
        this.jbmanager = jbmanager;
        this.properties = properties;
        this.foldername = foldername;
    }
    
    public boolean doIt() {
        try {
            this.jbmanager.newFolder(this.foldername, this.properties);
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
