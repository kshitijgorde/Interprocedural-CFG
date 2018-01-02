// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net;

import java.io.IOException;
import org.xmodel.net.stream.TcpClient;

public class Client extends Protocol
{
    private static TcpClient P;
    private String S;
    private int Q;
    private ILink R;
    
    public Client(final String s, final int q, final int n, final boolean b) throws IOException {
        super(n);
        if (Client.P == null) {
            (Client.P = new TcpClient()).start(b);
        }
        this.S = s;
        this.Q = q;
    }
    
    public String getHost() {
        return this.S;
    }
    
    public int getPort() {
        return this.Q;
    }
    
    public Session connect(final int n) throws IOException {
        if (this.R == null || !this.R.isOpen()) {
            this.R = Client.P.connect(this.S, this.Q, n, this);
        }
        return (this.R != null) ? this.openSession(this.R) : null;
    }
    
    public void disconnect() throws IOException {
        if (this.isConnected()) {
            this.R.close();
        }
    }
    
    public boolean isConnected() {
        return this.R != null && this.R.isOpen();
    }
}
