// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.https;

import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import org.apache.activemq.transport.util.TextWireFormat;
import org.apache.activemq.transport.http.HttpTransport;

@Deprecated
public class HttpsTransport extends HttpTransport
{
    public HttpsTransport(final TextWireFormat wireFormat, final URI remoteUrl) throws MalformedURLException {
        super(wireFormat, remoteUrl);
    }
    
    @Override
    protected synchronized HttpURLConnection createSendConnection() throws IOException {
        final HttpsURLConnection conn = (HttpsURLConnection)this.getRemoteURL().openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        this.configureConnection(conn);
        conn.connect();
        return conn;
    }
    
    @Override
    protected synchronized HttpURLConnection createReceiveConnection() throws IOException {
        final HttpsURLConnection conn = (HttpsURLConnection)this.getRemoteURL().openConnection();
        conn.setDoOutput(false);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        this.configureConnection(conn);
        conn.connect();
        return conn;
    }
}
