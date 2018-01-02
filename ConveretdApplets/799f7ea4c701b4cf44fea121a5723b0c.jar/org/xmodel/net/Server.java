// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net;

import java.io.IOException;
import org.xmodel.net.stream.TcpServer;

public class Server extends Protocol
{
    public static int defaultPort;
    private TcpServer O;
    
    static {
        Server.defaultPort = 27613;
    }
    
    public Server(final String s, final int n, final int n2, final boolean enableDebugging) throws IOException {
        super(n2);
        this.setEnableDebugging(enableDebugging);
        this.O = new TcpServer(s, n, this);
    }
    
    public void start(final boolean b) {
        this.O.start(b);
    }
    
    public void stop() {
        this.O.stop();
    }
}
