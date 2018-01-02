// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class SSHListenChannel extends SSHChannel
{
    static boolean allowRemoteConnect;
    static final int LISTEN_QUEUE_SIZE = 16;
    SSHChannelController controller;
    ServerSocket listenSocket;
    String remoteHost;
    int remotePort;
    InetAddress localHost1;
    InetAddress localHost2;
    boolean temporaryListener;
    
    public SSHListenChannel(final String localHost, final int localPort, final String remoteHost, final int remotePort, final SSHChannelController controller) throws IOException {
        super(-3);
        this.controller = controller;
        try {
            this.listenSocket = new ServerSocket(localPort, 16, InetAddress.getByName(localHost));
        }
        catch (IOException e) {
            throw new IOException("Error setting up local forward on port " + localPort + ", " + e.getMessage());
        }
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.localHost1 = InetAddress.getLocalHost();
        this.localHost2 = InetAddress.getByName("127.0.0.1");
    }
    
    public int getListenPort() {
        return this.listenSocket.getLocalPort();
    }
    
    public String getListenHost() {
        return this.listenSocket.getInetAddress().getHostAddress();
    }
    
    public static synchronized void setAllowRemoteConnect(final boolean val) {
        SSHListenChannel.allowRemoteConnect = val;
    }
    
    static synchronized boolean getAllowRemoteConnect() {
        return SSHListenChannel.allowRemoteConnect;
    }
    
    public SSHTunnel newTunnel(final Socket ioSocket, final int channelId, final int remoteChannelId, final SSHChannelController controller) throws IOException {
        return new SSHTunnel(ioSocket, channelId, remoteChannelId, controller);
    }
    
    public void setTemporaryListener(final boolean val) {
        this.temporaryListener = val;
    }
    
    public void serviceLoop() throws IOException {
        SSH.log("Starting listen-chan: " + this.listenSocket.getLocalPort());
        try {
            while (true) {
                final Socket fwdSocket = this.listenSocket.accept();
                if (!getAllowRemoteConnect() && !fwdSocket.getInetAddress().equals(this.localHost1) && !fwdSocket.getInetAddress().equals(this.localHost2)) {
                    this.controller.alert("Remote connect to local tunnel rejected: " + fwdSocket.getInetAddress());
                    fwdSocket.close();
                }
                else {
                    final SSHPduOutputStream respPdu = new SSHPduOutputStream(29, this.controller.sndCipher);
                    final int newChan = this.controller.newChannelId();
                    final SSHTunnel tunnel = this.newTunnel(fwdSocket, newChan, -4, this.controller);
                    this.controller.addTunnel(tunnel);
                    tunnel.setRemoteDesc(this.remoteHost + ":" + this.remotePort);
                    respPdu.writeInt(newChan);
                    respPdu.writeString(this.remoteHost);
                    respPdu.writeInt(this.remotePort);
                    SSH.log("got connect for: " + this.remoteHost + " : " + this.remotePort + ", " + newChan);
                    respPdu.writeString(fwdSocket.getInetAddress().getHostAddress());
                    this.controller.transmit(respPdu);
                    if (this.temporaryListener) {
                        break;
                    }
                    continue;
                }
            }
        }
        finally {
            this.listenSocket.close();
        }
    }
    
    public void forceClose() {
        while (true) {
            if (this.isAlive()) {
                try {
                    this.listenSocket.close();
                }
                catch (IOException ex) {}
                return;
            }
            continue;
        }
    }
    
    static {
        SSHListenChannel.allowRemoteConnect = false;
    }
}
