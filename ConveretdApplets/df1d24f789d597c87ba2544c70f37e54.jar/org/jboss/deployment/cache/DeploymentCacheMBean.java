// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment.cache;

import org.jboss.deployment.DeploymentException;
import java.net.URL;
import javax.management.ObjectName;
import org.jboss.deployment.DeployerMBean;
import org.jboss.system.ServiceMBean;

public interface DeploymentCacheMBean extends ServiceMBean, DeployerMBean
{
    void setDeployer(final ObjectName p0);
    
    ObjectName getDeployer();
    
    void setStore(final ObjectName p0);
    
    ObjectName getStore();
    
    void deploy(final URL p0) throws DeploymentException;
    
    void undeploy(final URL p0) throws DeploymentException;
    
    boolean isDeployed(final URL p0);
}
