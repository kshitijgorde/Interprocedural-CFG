// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;

public class SSHFtpTunnel extends SSHTunnel
{
    public static final String TUNNEL_NAME = "#FTP";
    public static final int MAX_REMOTE_LISTEN = 10;
    boolean havePORT;
    boolean waitingPASVResponse;
    String localAddrPASVStr;
    InetAddress localAddr;
    byte[][] newPortMsg;
    static int timeWaitKludgeToggler;
    static Object timeWaitKludgeCrit;
    
    public SSHFtpTunnel(final Socket ioSocket, final int channelId, final int remoteChannelId, final SSHChannelController controller) throws IOException {
        super(ioSocket, channelId, remoteChannelId, controller);
        this.havePORT = controller.sshAsClient().havePORTFtp;
        if (this.havePORT) {
            final int firstPort = controller.sshAsClient().firstFTPPort;
            final byte[] serverAddr = controller.sshAsClient().getServerRealAddr().getAddress();
            final int a1 = serverAddr[0] & 0xFF;
            final int a2 = serverAddr[1] & 0xFF;
            final int a3 = serverAddr[2] & 0xFF;
            final int a4 = serverAddr[3] & 0xFF;
            this.newPortMsg = new byte[10][1];
            for (int i = 0; i < 10; ++i) {
                final int p1 = firstPort + i >>> 8 & 0xFF;
                final int p2 = firstPort + i & 0xFF;
                final String msg = "PORT " + a1 + "," + a2 + "," + a3 + "," + a4 + "," + p1 + "," + p2 + "\n";
                this.newPortMsg[i] = msg.getBytes();
            }
        }
        this.localAddr = controller.sshAsClient().getLocalAddr();
        byte[] localAddrArr = controller.sshAsClient().getLocalAddr().getAddress();
        if (localAddrArr[0] == 0) {
            localAddrArr = InetAddress.getLocalHost().getAddress();
        }
        final int a1 = localAddrArr[0] & 0xFF;
        final int a2 = localAddrArr[1] & 0xFF;
        final int a3 = localAddrArr[2] & 0xFF;
        final int a4 = localAddrArr[3] & 0xFF;
        this.localAddrPASVStr = a1 + "," + a2 + "," + a3 + "," + a4;
    }
    
    boolean parseHostAndPort(final String msg, final int[] d) {
        boolean ok = true;
        int cl = 0;
        int cn = 0;
        try {
            for (int i = 0; i < 6; ++i) {
                if (i == 5) {
                    cn = msg.indexOf(41, cl);
                    if (cn == -1) {
                        cn = msg.indexOf(13, cl);
                    }
                    else if (cn == -1) {
                        cn = msg.indexOf(10, cl);
                    }
                }
                else {
                    cn = msg.indexOf(44, cl);
                }
                final String num = msg.substring(cl, cn);
                cl = cn + 1;
                d[i] = Integer.parseInt(num);
            }
        }
        catch (Exception e) {
            ok = false;
        }
        return ok;
    }
    
    public void receive(final SSHPdu pdu) {
        String msg = new String(pdu.rawData(), pdu.rawOffset(), pdu.rawSize());
        if (msg.startsWith("PASV") || msg.startsWith("pasv")) {
            this.waitingPASVResponse = true;
        }
        else if (msg.startsWith("PORT ") || msg.startsWith("port ")) {
            if (!this.havePORT) {
                this.controller.alert("Ftp-client is using PORT commands, either \nenable 'passive mode' in the ftp-client or \nenable 'ftp PORT' in 'SSH Settings' and reconnect.");
            }
            else {
                msg = msg.substring(5);
                final int[] d = new int[6];
                if (this.parseHostAndPort(msg, d)) {
                    final byte[] dst = pdu.rawData();
                    final byte[] newmsg;
                    final String mapName;
                    synchronized (SSHFtpTunnel.timeWaitKludgeCrit) {
                        newmsg = this.newPortMsg[SSHFtpTunnel.timeWaitKludgeToggler];
                        mapName = "#FTP" + SSHFtpTunnel.timeWaitKludgeToggler;
                        SSHFtpTunnel.timeWaitKludgeToggler = (SSHFtpTunnel.timeWaitKludgeToggler + 1) % 10;
                    }
                    final int len = newmsg.length;
                    int off = pdu.rawOffset() - 4;
                    dst[off++] = (byte)(len >>> 24 & 0xFF);
                    dst[off++] = (byte)(len >>> 16 & 0xFF);
                    dst[off++] = (byte)(len >>> 8 & 0xFF);
                    dst[off++] = (byte)(len & 0xFF);
                    System.arraycopy(newmsg, 0, dst, off, len);
                    pdu.rawAdjustSize(off + len);
                    final String toHost = d[0] + "." + d[1] + "." + d[2] + "." + d[3];
                    final int toPort = d[4] << 8 | d[5];
                    this.controller.addHostMapTemporary(mapName, toHost, toPort);
                }
                else {
                    this.controller.alert("Bug in SSHFtpTunnel (PORT), please report: " + msg);
                }
            }
        }
        super.receive(pdu);
    }
    
    public void transmit(final SSHPdu pdu) {
        SSHListenChannel listenChan = null;
        String msg = new String(pdu.rawData(), pdu.rawOffset(), pdu.rawSize());
        if (this.waitingPASVResponse && msg.startsWith("227 ")) {
            this.waitingPASVResponse = false;
            msg = msg.substring(27);
            final int[] d = new int[6];
            if (this.parseHostAndPort(msg, d)) {
                final String toHost = d[0] + "." + d[1] + "." + d[2] + "." + d[3];
                final int toPort = d[4] << 8 | d[5];
                try {
                    listenChan = this.controller.newListenChannel(this.localAddr.getHostAddress(), 0, toHost, toPort, "general");
                }
                catch (IOException e) {
                    this.controller.alert("Error in FtpTunnel: " + e.toString());
                }
                listenChan.setTemporaryListener(true);
                final int localPort = listenChan.getListenPort();
                final int p1 = localPort >>> 8 & 0xFF;
                final int p2 = localPort & 0xFF;
                msg = "227 Entering Passive Mode (" + this.localAddrPASVStr + "," + p1 + "," + p2 + ")\n";
                final byte[] newmsg = msg.getBytes();
                pdu.rawSetData(newmsg);
            }
            else {
                this.controller.alert("Bug in SSHFtpTunnel (PASV), please report: " + msg);
            }
        }
        super.transmit(pdu);
    }
    
    public String getDescription() {
        if (this.ioSocket != null) {
            return this.ioSocket.getInetAddress().getHostAddress() + ":" + this.ioSocket.getPort() + " <-ftp-> " + this.getLocalHost() + ":" + this.ioSocket.getLocalPort() + " <-ssh-> " + this.remoteDesc;
        }
        return "< N/A >";
    }
    
    static {
        SSHFtpTunnel.timeWaitKludgeCrit = new Object();
    }
}
