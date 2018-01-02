// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public interface MBeanProxyInstance
{
    ObjectName getMBeanProxyObjectName();
    
    MBeanServer getMBeanProxyMBeanServer();
}
