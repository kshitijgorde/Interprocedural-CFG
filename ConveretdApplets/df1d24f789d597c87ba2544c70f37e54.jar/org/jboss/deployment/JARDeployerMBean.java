// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment;

import org.jboss.mx.util.ObjectNameFactory;
import javax.management.ObjectName;

public interface JARDeployerMBean extends SubDeployerMBean
{
    public static final ObjectName OBJECT_NAME = ObjectNameFactory.create("jboss.system:service=JARDeployer");
    
    void setDescriptorNames(final String[] p0);
    
    String[] getDescriptorNames();
}
