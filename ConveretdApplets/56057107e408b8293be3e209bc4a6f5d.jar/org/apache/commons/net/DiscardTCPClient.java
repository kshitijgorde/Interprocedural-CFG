// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net;

import java.io.OutputStream;

public class DiscardTCPClient extends SocketClient
{
    public static final int DEFAULT_PORT = 9;
    
    public DiscardTCPClient() {
        this.setDefaultPort(9);
    }
    
    public OutputStream getOutputStream() {
        return this._output_;
    }
}
