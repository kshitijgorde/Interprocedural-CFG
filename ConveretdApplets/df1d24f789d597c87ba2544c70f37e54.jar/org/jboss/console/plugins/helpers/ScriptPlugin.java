// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers;

import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.ManageableResource;

public interface ScriptPlugin
{
    String getVersion(final PluginContext p0);
    
    String getName(final PluginContext p0);
    
    boolean isResourceToBeManaged(final ManageableResource p0, final PluginContext p1);
    
    TreeNode getTreeForResource(final ManageableResource p0, final PluginContext p1);
}
