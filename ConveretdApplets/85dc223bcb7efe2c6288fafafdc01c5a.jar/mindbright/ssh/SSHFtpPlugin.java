// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.util.Random;

public class SSHFtpPlugin extends SSHProtocolPlugin
{
    public void initiate(final SSHClient client) {
        client.delRemotePortForward("ftp");
        if (client.havePORTFtp) {
            final Random rnd = new Random();
            int rndval;
            while ((rndval = (rnd.nextInt() & 0xFFF0)) < 8192) {}
            client.firstFTPPort = rndval;
            for (int i = 0; i < 10; ++i) {
                client.addRemotePortForward(client.firstFTPPort + i, "#FTP" + i, client.firstFTPPort + i, "ftp");
            }
        }
    }
    
    public SSHListenChannel localListener(final String localHost, final int localPort, final String remoteHost, final int remotePort, final SSHChannelController controller) throws IOException {
        return new SSHFtpListenChannel(localHost, localPort, remoteHost, remotePort, controller);
    }
}
