// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net;

import java.io.InputStream;
import java.io.IOException;

public final class WhoisClient extends FingerClient
{
    public static final String DEFAULT_HOST = "whois.internic.net";
    public static final int DEFAULT_PORT = 43;
    
    public WhoisClient() {
        this.setDefaultPort(43);
    }
    
    public String query(final String handle) throws IOException {
        return this.query(false, handle);
    }
    
    public InputStream getInputStream(final String handle) throws IOException {
        return this.getInputStream(false, handle);
    }
}
