// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces;

public interface ResourceTreeNode extends TreeNode
{
    public static final int ALWAYS_VISIBLE = 0;
    public static final int INVISIBLE_IF_SUBNODE_EXISTS = 2;
    
    ManageableResource getResource();
    
    int getVisibility();
}
