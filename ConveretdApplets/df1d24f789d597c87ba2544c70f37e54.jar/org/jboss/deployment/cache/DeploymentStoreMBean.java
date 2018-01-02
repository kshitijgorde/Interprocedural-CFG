// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment.cache;

import java.net.URL;
import org.jboss.system.ServiceMBean;

public interface DeploymentStoreMBean extends ServiceMBean
{
    URL get(final URL p0) throws Exception;
    
    URL put(final URL p0) throws Exception;
}
