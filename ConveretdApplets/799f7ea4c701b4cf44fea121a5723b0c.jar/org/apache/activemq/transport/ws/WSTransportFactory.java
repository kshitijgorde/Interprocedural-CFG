// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.ws;

import java.io.IOException;
import org.apache.activemq.transport.TransportServer;
import java.net.URI;
import org.apache.activemq.transport.TransportFactory;

public class WSTransportFactory extends TransportFactory
{
    @Override
    public TransportServer doBind(final URI location) throws IOException {
        return new WSTransportServer(location);
    }
}
