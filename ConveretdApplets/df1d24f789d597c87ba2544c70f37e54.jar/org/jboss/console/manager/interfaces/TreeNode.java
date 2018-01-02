// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces;

import java.io.Serializable;

public interface TreeNode extends Serializable
{
    String getName();
    
    String getDescription();
    
    String getIcon();
    
    TreeAction getAction();
    
    TreeNodeMenuEntry[] getMenuEntries();
    
    TreeNode[] getSubNodes();
    
    ResourceTreeNode[] getNodeManagableResources();
    
    boolean isMasterNode();
}
