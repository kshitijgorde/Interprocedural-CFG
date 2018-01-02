// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import java.io.OutputStream;
import java.util.Hashtable;

public class SSHConnectChannel extends SSHTxChannel
{
    SSHChannelController controller;
    Hashtable hostMap;
    
    public SSHConnectChannel(final SSHChannelController controller) {
        super(null, -2);
        this.controller = controller;
        this.hostMap = new Hashtable();
    }
    
    public synchronized void addHostMapPermanent(final String fromHost, final String toHost, final int toPort) {
        final Vector hostPortPair = new Vector();
        hostPortPair.addElement(toHost);
        hostPortPair.addElement(new Integer(toPort));
        hostPortPair.addElement(new Boolean(true));
        this.hostMap.put(fromHost, hostPortPair);
    }
    
    public synchronized void addHostMapTemporary(final String fromHost, final String toHost, final int toPort) {
        final Vector hostPortPair = new Vector();
        hostPortPair.addElement(toHost);
        hostPortPair.addElement(new Integer(toPort));
        hostPortPair.addElement(new Boolean(false));
        this.hostMap.put(fromHost, hostPortPair);
    }
    
    public synchronized void delHostMap(final String fromHost) {
        this.hostMap.remove(fromHost);
    }
    
    public synchronized Vector getHostMap(final String fromHost) {
        final Vector hostPortPair = this.hostMap.get(fromHost);
        if (hostPortPair != null && !hostPortPair.elementAt(2)) {
            this.delHostMap(fromHost);
        }
        return hostPortPair;
    }
    
    int displayNumber(final String display) {
        final int hostEnd;
        if (display == null || display.equals("") || (hostEnd = display.indexOf(58)) == -1) {
            return 0;
        }
        int dispEnd;
        if ((dispEnd = display.indexOf(46, hostEnd)) == -1) {
            dispEnd = display.length();
        }
        try {
            return Integer.parseInt(display.substring(hostEnd + 1, dispEnd));
        }
        catch (Exception e) {
            final int displayNum = 0;
            return displayNum;
        }
    }
    
    String displayHost(final String display) {
        final int hostEnd;
        if (display == null || display.equals("") || display.charAt(0) == ':' || display.indexOf("unix:") == 0 || (hostEnd = display.indexOf(58)) == -1) {
            return "localhost";
        }
        return display.substring(0, hostEnd);
    }
    
    public void serviceLoop() throws Exception {
        while (true) {
            final SSHPduInputStream inPdu = (SSHPduInputStream)this.queue.getFirst();
            final int remoteChannel = inPdu.readInt();
            String host;
            int port;
            if (inPdu.type == 27) {
                if (!this.controller.sshAsClient().user.wantX11Forward()) {
                    break;
                }
                final String display = this.controller.sshAsClient().user.getDisplay();
                host = this.displayHost(display);
                port = 6000 + this.displayNumber(display);
            }
            else {
                host = inPdu.readString();
                port = inPdu.readInt();
            }
            String origin;
            if (this.controller.haveHostInFwdOpen()) {
                origin = inPdu.readString();
            }
            else {
                origin = "unknown (origin-option not used)";
            }
            final Vector hostPortPair = this.getHostMap(host);
            if (hostPortPair != null) {
                host = hostPortPair.elementAt(0);
                port = hostPortPair.elementAt(1);
            }
            try {
                final Socket fwdSocket = new Socket(host, port);
                final int newChan = this.controller.newChannelId();
                final SSHTunnel tunnel = new SSHTunnel(fwdSocket, newChan, remoteChannel, this.controller);
                this.controller.addTunnel(tunnel);
                tunnel.setRemoteDesc(origin);
                final SSHPduOutputStream respPdu = new SSHPduOutputStream(21, this.controller.sndCipher);
                respPdu.writeInt(remoteChannel);
                respPdu.writeInt(newChan);
                SSH.log("Port open (" + origin + ") : " + host + ": " + port + " (#" + remoteChannel + ")" + " new: " + newChan);
                this.controller.transmit(respPdu);
                tunnel.start();
            }
            catch (IOException e) {
                final SSHPduOutputStream respPdu = new SSHPduOutputStream(22, this.controller.sndCipher);
                respPdu.writeInt(remoteChannel);
                this.controller.alert("Failed port open (" + origin + ") : " + host + ": " + port + " (#" + remoteChannel + ")");
                this.controller.transmit(respPdu);
            }
        }
        this.controller.alert("Something is fishy with the server, unsolicited X11 forward!");
        throw new Exception("Something is fishy with the server, unsolicited X11 forward!");
    }
}
