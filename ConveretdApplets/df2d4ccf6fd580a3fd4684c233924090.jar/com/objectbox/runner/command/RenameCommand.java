// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.command;

import com.objectbox.runner.gui.JBManagerPanel;
import com.objectbox.runner.gui.tree.TreeBase;

public class RenameCommand extends AbstractCommand
{
    int altered_node_index;
    String old_text;
    TreeBase treebase;
    JBManagerPanel jbman;
    
    public RenameCommand(final TreeBase treebase) {
        this.jbman = null;
        super.name = "rename";
        this.altered_node_index = treebase.nodeIndex(treebase.getSelectedNode());
        this.old_text = treebase.getChangedItemName();
        this.treebase = treebase;
    }
    
    public boolean doIt() {
        return true;
    }
    
    public boolean undoIt() {
        try {
            this.treebase.getNodeAt(this.altered_node_index).setText(this.old_text);
            this.treebase.validate();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
}
