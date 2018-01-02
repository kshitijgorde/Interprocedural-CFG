// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.broker.ft.MasterConnector;

public class FTConnectorView implements FTConnectorViewMBean
{
    private final MasterConnector connector;
    
    public FTConnectorView(final MasterConnector connector) {
        this.connector = connector;
    }
    
    @Override
    public void start() throws Exception {
        this.connector.start();
    }
    
    @Override
    public void stop() throws Exception {
        this.connector.stop();
    }
}
