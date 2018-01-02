// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.net.Socket;
import java.io.IOException;

public interface SSHClientUser
{
    String getSrvHost() throws IOException;
    
    int getSrvPort();
    
    Socket getProxyConnection() throws IOException;
    
    String getDisplay();
    
    int getMaxPacketSz();
    
    int getAliveInterval();
    
    boolean wantX11Forward();
    
    boolean wantPrivileged();
    
    boolean wantPTY();
    
    SSHInteractor getInteractor();
}
