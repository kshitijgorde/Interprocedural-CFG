// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.stomp;

import java.security.cert.X509Certificate;
import java.io.IOException;
import org.apache.activemq.command.Command;

public interface StompTransport
{
    void sendToActiveMQ(final Command p0);
    
    void sendToStomp(final StompFrame p0) throws IOException;
    
    X509Certificate[] getPeerCertificates();
    
    void onException(final IOException p0);
}
