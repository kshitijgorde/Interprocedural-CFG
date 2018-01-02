// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment;

import org.jboss.mx.util.ObjectNameFactory;
import javax.management.ObjectName;
import org.jboss.system.ServiceMBean;

public interface ClasspathExtensionMBean extends ServiceMBean
{
    public static final ObjectName OBJECT_NAME = ObjectNameFactory.create("jboss:type=Service,service=ClasspathExtension");
    
    String getMetadataURL();
    
    void setMetadataURL(final String p0);
    
    ObjectName getLoaderRepository();
    
    void setLoaderRepository(final ObjectName p0);
}
