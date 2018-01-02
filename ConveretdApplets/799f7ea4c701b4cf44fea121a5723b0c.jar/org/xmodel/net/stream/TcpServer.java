// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.stream;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import org.xmodel.net.ILink;
import java.nio.channels.ServerSocketChannel;
import org.xmodel.log.Log;

public final class TcpServer extends TcpBase
{
    private static final Log I;
    private ServerSocketChannel H;
    
    static {
        I = Log.getLog("org.xmodel.net.stream");
    }
    
    public TcpServer(final String s, final int n, final ILink.IListener listener) throws IOException {
        super(listener);
        (this.H = ServerSocketChannel.open()).configureBlocking(false);
        this.H.socket().bind(new InetSocketAddress(s, n));
        this.H.register(this.selector, 16);
    }
    
    @Override
    public void stop() {
        super.stop();
        try {
            if (this.H != null) {
                this.H.close();
            }
        }
        catch (IOException ex) {
            TcpServer.I.exception(ex);
        }
        this.H = null;
    }
}
