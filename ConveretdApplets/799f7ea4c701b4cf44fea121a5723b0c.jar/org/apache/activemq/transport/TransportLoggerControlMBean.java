// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport;

public interface TransportLoggerControlMBean
{
    void enableAllTransportLoggers();
    
    void disableAllTransportLoggers();
    
    void reloadLog4jProperties() throws Throwable;
}
