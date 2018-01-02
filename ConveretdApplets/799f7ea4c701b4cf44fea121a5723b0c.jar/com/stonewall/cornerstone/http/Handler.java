// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.http;

import java.io.IOException;
import java.net.URLConnection;
import java.net.URL;
import com.stonewall.cornerstone.utility.SchemaServer;

public class Handler extends sun.net.www.protocol.http.Handler
{
    final SchemaServer server;
    public static final String domain = "schema.stonewallnetworks.com";
    
    public Handler() {
        this.server = new SchemaServer();
    }
    
    @Override
    protected URLConnection openConnection(final URL url) throws IOException {
        return this.match(url) ? this.getConnection(url) : super.openConnection(url);
    }
    
    boolean match(final URL url) {
        return url.getHost().equals("schema.stonewallnetworks.com");
    }
    
    URLConnection getConnection(final URL url) {
        return new HttpConnection(url, this.server);
    }
}
