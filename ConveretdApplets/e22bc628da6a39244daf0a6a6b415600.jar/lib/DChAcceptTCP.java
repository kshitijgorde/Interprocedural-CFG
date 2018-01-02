// 
// Decompiled by Procyon v0.5.30
// 

package lib;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public class DChAcceptTCP
{
    ServerSocket ssocket;
    
    public DChAcceptTCP(final int n) throws IOException {
        this.ssocket = new ServerSocket(n, 4);
    }
    
    public Socket accept() throws IOException {
        return this.ssocket.accept();
    }
    
    public void close() throws IOException {
        this.ssocket.close();
    }
}
