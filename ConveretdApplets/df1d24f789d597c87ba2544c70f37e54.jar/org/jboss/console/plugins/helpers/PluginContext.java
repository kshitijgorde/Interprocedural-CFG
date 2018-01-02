// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers;

import org.jboss.console.manager.interfaces.ManageableResource;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import javax.management.ObjectInstance;
import javax.management.MBeanServer;
import org.jboss.logging.Logger;

public interface PluginContext
{
    String localizeUrl(final String p0);
    
    Logger getLogger();
    
    MBeanServer getLocalMBeanServer();
    
    ObjectInstance[] getMBeansForClass(final String p0, final String p1);
    
    TreeNode createTreeNode(final String p0, final String p1, final String p2, final String p3, final TreeNodeMenuEntry[] p4, final TreeNode[] p5, final ResourceTreeNode[] p6) throws Exception;
    
    ResourceTreeNode createResourceNode(final String p0, final String p1, final String p2, final String p3, final TreeNodeMenuEntry[] p4, final TreeNode[] p5, final ResourceTreeNode[] p6, final String p7, final String p8) throws Exception;
    
    ResourceTreeNode createResourceNode(final String p0, final String p1, final String p2, final String p3, final TreeNodeMenuEntry[] p4, final TreeNode[] p5, final ResourceTreeNode[] p6, final ManageableResource p7) throws Exception;
    
    TreeNodeMenuEntry[] createMenus(final String[] p0) throws Exception;
    
    String encode(final String p0);
}
