// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net;

import java.io.InputStream;

public final class EchoTCPClient extends DiscardTCPClient
{
    public static final int DEFAULT_PORT = 7;
    
    public EchoTCPClient() {
        this.setDefaultPort(7);
    }
    
    public InputStream getInputStream() {
        return this._input_;
    }
}
