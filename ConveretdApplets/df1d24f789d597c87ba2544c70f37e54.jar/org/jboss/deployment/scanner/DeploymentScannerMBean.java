// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment.scanner;

import javax.management.ObjectName;
import org.jboss.system.ServiceMBean;

public interface DeploymentScannerMBean extends ServiceMBean
{
    void setDeployer(final ObjectName p0);
    
    ObjectName getDeployer();
    
    void setScanPeriod(final long p0);
    
    void setScanEnabled(final boolean p0);
    
    boolean isScanEnabled();
    
    long getStopTimeOut();
    
    void setStopTimeOut(final long p0);
    
    void scan() throws Exception;
}
