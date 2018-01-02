// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.http;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import com.stonewall.cornerstone.utility.SchemaServer;
import java.net.URLConnection;

public class HttpConnection extends URLConnection
{
    final SchemaServer server;
    
    public HttpConnection(final URL url, final SchemaServer server) {
        super(url);
        this.server = server;
    }
    
    @Override
    public void connect() throws IOException {
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        final InputStream result = this.server.processRequest(this.url);
        if (result == null) {
            throw new IOException(this.url + " - not resolved");
        }
        return result;
    }
}
