// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.command;

public interface Endpoint
{
    String getName();
    
    BrokerId getBrokerId();
    
    BrokerInfo getBrokerInfo();
    
    void setBrokerInfo(final BrokerInfo p0);
}
