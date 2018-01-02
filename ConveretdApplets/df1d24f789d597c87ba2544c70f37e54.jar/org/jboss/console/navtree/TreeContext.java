// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

import org.jboss.console.remote.SimpleRemoteMBeanInvoker;
import java.util.Properties;
import org.jboss.console.manager.interfaces.SimpleTreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeAction;

public interface TreeContext
{
    void doAdminTreeAction(final TreeAction p0);
    
    void doPopupMenuAction(final SimpleTreeNodeMenuEntry p0);
    
    Properties getJndiProperties();
    
    String getServiceJmxName();
    
    String localizeUrl(final String p0);
    
    SimpleRemoteMBeanInvoker getRemoteMBeanInvoker();
}
