// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker;

import java.net.URI;

public interface BrokerFactoryHandler
{
    BrokerService createBroker(final URI p0) throws Exception;
}
