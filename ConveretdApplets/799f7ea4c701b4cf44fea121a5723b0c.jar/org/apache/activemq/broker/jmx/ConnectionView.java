// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.broker.Connection;

public class ConnectionView implements ConnectionViewMBean
{
    private final Connection connection;
    
    public ConnectionView(final Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void start() throws Exception {
        this.connection.start();
    }
    
    @Override
    public void stop() throws Exception {
        this.connection.stop();
    }
    
    @Override
    public boolean isSlow() {
        return this.connection.isSlow();
    }
    
    @Override
    public boolean isBlocked() {
        return this.connection.isBlocked();
    }
    
    @Override
    public boolean isConnected() {
        return this.connection.isConnected();
    }
    
    @Override
    public boolean isActive() {
        return this.connection.isActive();
    }
    
    @Override
    public void resetStatistics() {
        this.connection.getStatistics().reset();
    }
    
    @Override
    public String getRemoteAddress() {
        return this.connection.getRemoteAddress();
    }
    
    public String getConnectionId() {
        return this.connection.getConnectionId();
    }
}
