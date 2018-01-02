// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.monitor.MonitorNotification;
import javax.management.ObjectName;
import javax.management.MBeanAttributeInfo;

public interface MonitorCallback
{
    void monitorCallback(final ObservedObject p0, final MBeanAttributeInfo p1, final Object p2) throws Exception;
    
    MonitorNotification createNotification(final String p0, final Object p1, final long p2, final String p3, final Object p4, final String p5, final ObjectName p6, final Object p7);
}
