// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.https;

import java.net.URI;
import org.apache.activemq.transport.util.TextWireFormat;
import org.apache.activemq.transport.http.HttpClientTransport;

public class HttpsClientTransport extends HttpClientTransport
{
    public HttpsClientTransport(final TextWireFormat wireFormat, final URI remoteUrl) {
        super(wireFormat, remoteUrl);
    }
}
