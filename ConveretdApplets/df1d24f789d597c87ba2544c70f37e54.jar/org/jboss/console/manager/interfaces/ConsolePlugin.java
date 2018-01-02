// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces;

import org.jboss.console.manager.PluginManager;
import java.io.Serializable;

public interface ConsolePlugin extends Serializable
{
    public static final String WEB_PROFILE = "WEB";
    public static final String[] PLUGIN_PROFILES = { "WEB" };
    
    String[] getSupportedProfiles();
    
    TreeNode getSubTreeForResource(final PluginManager p0, final String p1, final ManageableResource p2);
    
    String getIdentifier();
    
    String getVersion();
}
