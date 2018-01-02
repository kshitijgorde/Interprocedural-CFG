// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager;

import org.jboss.console.manager.interfaces.ManageableResource;
import javax.management.MBeanServer;
import org.jboss.console.manager.interfaces.TreeInfo;
import org.jboss.console.manager.interfaces.ConsolePlugin;
import org.jboss.system.ServiceMBean;

public interface PluginManagerMBean extends ServiceMBean
{
    void registerPlugin(final String p0) throws Exception;
    
    void registerPlugin(final ConsolePlugin p0);
    
    void unregisterPlugin(final ConsolePlugin p0);
    
    void regenerateAdminTree();
    
    void regenerateAdminTreeForProfile(final String p0);
    
    TreeInfo getTreeForProfile(final String p0);
    
    TreeInfo getUpdateTreeForProfile(final String p0, final long p1);
    
    MBeanServer getMBeanServer();
    
    ManageableResource getBootstrapResource();
    
    String getJndiName();
    
    void setJndiName(final String p0);
    
    boolean isEnableShutdown();
    
    void setEnableShutdown(final boolean p0);
    
    String getMainLinkUrl();
    
    void setMainLinkUrl(final String p0);
    
    String getMainLogoUrl();
    
    void setMainLogoUrl(final String p0);
}
