// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment;

import javax.management.ObjectName;
import org.jboss.system.ServiceMBean;

public interface SubDeployerMBean extends ServiceMBean
{
    ObjectName getServiceName();
    
    String[] getSuffixes();
    
    int getRelativeOrder();
    
    boolean accepts(final DeploymentInfo p0);
    
    void init(final DeploymentInfo p0) throws DeploymentException;
    
    void create(final DeploymentInfo p0) throws DeploymentException;
    
    void start(final DeploymentInfo p0) throws DeploymentException;
    
    void stop(final DeploymentInfo p0) throws DeploymentException;
    
    void destroy(final DeploymentInfo p0) throws DeploymentException;
}
