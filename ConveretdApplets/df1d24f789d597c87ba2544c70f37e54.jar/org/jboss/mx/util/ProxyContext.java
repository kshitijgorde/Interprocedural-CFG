// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.ObjectName;
import javax.management.MBeanServer;

public interface ProxyContext
{
    void setExceptionHandler(final ProxyExceptionHandler p0);
    
    MBeanServer getMBeanServer();
    
    ObjectName getObjectName();
}
