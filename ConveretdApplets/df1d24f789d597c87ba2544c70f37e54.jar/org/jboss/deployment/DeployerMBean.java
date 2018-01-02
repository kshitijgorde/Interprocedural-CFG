// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment;

import java.net.URL;

public interface DeployerMBean
{
    void deploy(final URL p0) throws DeploymentException;
    
    void undeploy(final URL p0) throws DeploymentException;
    
    boolean isDeployed(final URL p0);
}
