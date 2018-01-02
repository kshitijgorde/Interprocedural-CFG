// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces;

import java.io.Serializable;

public interface TreeInfo extends Serializable
{
    ManageableResource[] getRootResources();
    
    void setRootResources(final ManageableResource[] p0);
    
    TreeNode[] getTreesForResource(final ManageableResource p0);
    
    void addTreeToResource(final ManageableResource p0, final TreeNode p1);
    
    TreeAction getHomeAction();
    
    void setHomeAction(final TreeAction p0);
    
    String getDescription();
    
    String getIconUrl();
    
    void setIconUrl(final String p0);
    
    void setRootMenus(final TreeNodeMenuEntry[] p0);
    
    TreeNodeMenuEntry[] getRootMenus();
    
    long getTreeVersion();
    
    void setTreeVersion(final long p0);
}
