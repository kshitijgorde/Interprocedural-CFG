// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.io.FilterInputStream;

public class SocketInputStream extends FilterInputStream
{
    private Socket __socket;
    
    public SocketInputStream(final Socket socket, final InputStream stream) {
        super(stream);
        this.__socket = socket;
    }
    
    public void close() throws IOException {
        super.close();
        this.__socket.close();
    }
}
