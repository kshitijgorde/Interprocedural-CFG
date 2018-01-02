// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.discovery.rendezvous;

import java.io.IOException;
import java.util.Map;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.util.IntrospectionSupport;
import org.apache.activemq.util.URISupport;
import org.apache.activemq.transport.discovery.DiscoveryAgent;
import java.net.URI;
import org.apache.activemq.transport.discovery.DiscoveryAgentFactory;

public class RendezvousDiscoveryAgentFactory extends DiscoveryAgentFactory
{
    @Override
    protected DiscoveryAgent doCreateDiscoveryAgent(final URI uri) throws IOException {
        try {
            final Map options = URISupport.parseParameters(uri);
            final RendezvousDiscoveryAgent rc = new RendezvousDiscoveryAgent();
            rc.setGroup(uri.getHost());
            IntrospectionSupport.setProperties(rc, options);
            return rc;
        }
        catch (Throwable e) {
            throw IOExceptionSupport.create("Could not create discovery agent: " + uri, e);
        }
    }
}
