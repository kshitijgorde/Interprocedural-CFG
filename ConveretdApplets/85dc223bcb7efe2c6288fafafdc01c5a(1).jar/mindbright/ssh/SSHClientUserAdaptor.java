// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.net.Socket;

public class SSHClientUserAdaptor implements SSHClientUser
{
    protected String sshHost;
    protected int sshPort;
    
    public SSHClientUserAdaptor(final String server, final int port) {
        this.sshHost = server;
        this.sshPort = port;
    }
    
    public SSHClientUserAdaptor(final String server) {
        this(server, 22);
    }
    
    public String getSrvHost() {
        return this.sshHost;
    }
    
    public int getSrvPort() {
        return this.sshPort;
    }
    
    public Socket getProxyConnection() throws IOException {
        return null;
    }
    
    public String getDisplay() {
        return "";
    }
    
    public int getMaxPacketSz() {
        return 0;
    }
    
    public int getAliveInterval() {
        return 0;
    }
    
    public boolean wantX11Forward() {
        return false;
    }
    
    public boolean wantPrivileged() {
        return false;
    }
    
    public boolean wantPTY() {
        return false;
    }
    
    public SSHInteractor getInteractor() {
        return null;
    }
}
