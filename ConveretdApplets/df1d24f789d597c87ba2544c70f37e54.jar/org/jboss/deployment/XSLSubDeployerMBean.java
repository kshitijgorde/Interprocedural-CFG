// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment;

import org.jboss.mx.util.ObjectNameFactory;
import javax.management.ObjectName;

public interface XSLSubDeployerMBean extends SubDeployerExtMBean
{
    public static final ObjectName OBJECT_NAME = ObjectNameFactory.create("jboss.system:service=XSLDeployer");
    
    void setXslUrl(final String p0);
    
    String getXslUrl();
    
    void setPackageSuffix(final String p0);
    
    String getPackageSuffix();
    
    void setDdSuffix(final String p0);
    
    String getDdSuffix();
    
    void setDelegateName(final ObjectName p0);
    
    ObjectName getDelegateName();
    
    void setValidateDTDs(final boolean p0);
    
    boolean getValidateDTDs();
}
