// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.net.Socket;
import java.io.IOException;

public class SSHFtpListenChannel extends SSHListenChannel
{
    public SSHFtpListenChannel(final String localHost, final int localPort, final String remoteHost, final int remotePort, final SSHChannelController controller) throws IOException {
        super(localHost, localPort, remoteHost, remotePort, controller);
    }
    
    public SSHTunnel newTunnel(final Socket ioSocket, final int channelId, final int remoteChannelId, final SSHChannelController controller) throws IOException {
        return new SSHFtpTunnel(ioSocket, channelId, remoteChannelId, controller);
    }
}
