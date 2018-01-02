// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Connection;

public interface JmsProvider
{
    void setThreadPriority(final int p0);
    
    Connection createConnection() throws JMSException;
    
    Connection getConnection() throws JMSException;
    
    void close();
    
    Destination getDestination(final String p0) throws JMSException;
    
    int[] getPorts();
}
