// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import mindbright.security.Cipher;
import java.util.Vector;

public final class SSHChannelController extends SSH implements SSHChannelListener
{
    protected SSHTxChannel txChan;
    protected SSHRxChannel rxChan;
    protected SSHConnectChannel cnChan;
    protected SSHPduQueue txQueue;
    protected SSHPduQueue cnQueue;
    protected int totalTunnels;
    protected int nextEmptyChan;
    protected Object[] tunnels;
    protected Vector listenChannels;
    protected SSH sshHook;
    protected SSHConsole console;
    protected Cipher sndCipher;
    protected Cipher rcvCipher;
    
    public SSHChannelController(final SSH sshHook, final InputStream in, final OutputStream out, final Cipher sndCipher, final Cipher rcvCipher, final SSHConsole console, final boolean haveCnxWatch) {
        this.sndCipher = sndCipher;
        this.rcvCipher = rcvCipher;
        this.sshHook = sshHook;
        this.console = console;
        this.tunnels = new Object[16];
        this.nextEmptyChan = 0;
        this.totalTunnels = 0;
        this.listenChannels = new Vector();
        this.txChan = new SSHTxChannel(out, -1);
        (this.rxChan = new SSHRxChannel(in, -1)).setSSHChannelListener(this);
        this.txChan.setSSHChannelListener(this);
        this.rxChan.setSSHPduFactory(new SSHPduInputStream(-1, rcvCipher));
        this.txQueue = this.txChan.getQueue();
        if (haveCnxWatch) {
            (this.cnChan = new SSHConnectChannel(this)).setSSHChannelListener(this);
            this.cnQueue = this.cnChan.getQueue();
        }
        else {
            this.cnQueue = new SSHPduQueue();
        }
    }
    
    public void start() {
        this.txChan.start();
        this.rxChan.start();
        if (this.cnChan != null) {
            this.cnChan.start();
        }
    }
    
    public void waitForExit() throws InterruptedException {
        this.waitForExit(0L);
    }
    
    public void waitForExit(final long msWait) throws InterruptedException {
        if (this.rxChan != null) {
            this.rxChan.join(msWait);
        }
        Thread.sleep(100L);
        this.killAll();
    }
    
    public void killAll() {
        this.killAllTunnels();
        this.killListenChannels();
        if (this.rxChan != null && this.rxChan.isAlive() && this.txChan != null && this.txChan.isAlive() && this.cnChan != null && this.cnChan.isAlive()) {
            this.rxChan = null;
        }
        this.txChan = null;
        this.cnChan = null;
        System.runFinalization();
    }
    
    public synchronized int newChannelId() {
        final int newChan = this.nextEmptyChan;
        if (this.nextEmptyChan < this.tunnels.length) {
            int i;
            for (i = this.nextEmptyChan + 1; i < this.tunnels.length && this.tunnels[i] != null; ++i) {}
            this.nextEmptyChan = i;
        }
        else {
            final Object[] tmp = new Object[this.tunnels.length + 16];
            System.arraycopy(this.tunnels, 0, tmp, 0, this.tunnels.length);
            this.tunnels = tmp;
            ++this.nextEmptyChan;
        }
        return newChan;
    }
    
    public synchronized String[] listTunnels() {
        int cnt = 0;
        final String[] list1 = new String[this.tunnels.length];
        for (int i = 0; i < this.tunnels.length; ++i) {
            if (this.tunnels[i] != null) {
                list1[cnt++] = ((SSHTunnel)this.tunnels[i]).getDescription();
            }
        }
        final String[] list2 = new String[cnt];
        System.arraycopy(list1, 0, list2, 0, cnt);
        return list2;
    }
    
    public synchronized void closeTunnelFromList(int listIdx) {
        int i;
        for (i = 0; i < this.tunnels.length; ++i) {
            if (this.tunnels[i] != null) {
                if (--listIdx < 0) {
                    break;
                }
            }
        }
        if (i < this.tunnels.length) {
            ((SSHTunnel)this.tunnels[i]).terminateNow();
        }
    }
    
    public synchronized void killAllTunnels() {
        for (int i = 0; i < this.tunnels.length; ++i) {
            if (this.tunnels[i] != null) {
                ((SSHTunnel)this.tunnels[i]).openFailure();
                this.tunnels[i] = null;
            }
        }
        this.tunnels = new Object[16];
    }
    
    public synchronized void addTunnel(final SSHTunnel tunnel) throws IOException {
        ++this.totalTunnels;
        this.tunnels[tunnel.channelId] = tunnel;
    }
    
    public synchronized SSHTunnel delTunnel(final int channelId) {
        final SSHTunnel tunnelToDelete = (SSHTunnel)this.tunnels[channelId];
        this.tunnels[channelId] = null;
        this.nextEmptyChan = ((channelId < this.nextEmptyChan) ? channelId : this.nextEmptyChan);
        --this.totalTunnels;
        return tunnelToDelete;
    }
    
    public boolean haveHostInFwdOpen() {
        return this.sshHook.isProtocolFlagSet(2);
    }
    
    public SSHListenChannel newListenChannel(final String localHost, final int localPort, final String remoteHost, final int remotePort, final String plugin) throws IOException {
        SSHListenChannel newListenChan = null;
        newListenChan = SSHProtocolPlugin.getPlugin(plugin).localListener(localHost, localPort, remoteHost, remotePort, this);
        newListenChan.setSSHChannelListener(this);
        newListenChan.start();
        synchronized (this.listenChannels) {
            this.listenChannels.addElement(newListenChan);
        }
        return newListenChan;
    }
    
    public void killListenChannel(final String localHost, final int listenPort) {
        synchronized (this.listenChannels) {
            for (int i = 0; i < this.listenChannels.size(); ++i) {
                final SSHListenChannel listenChan = this.listenChannels.elementAt(i);
                if (listenChan.getListenPort() == listenPort && listenChan.getListenHost().equals(localHost)) {
                    this.listenChannels.removeElementAt(i);
                    listenChan.forceClose();
                    break;
                }
            }
        }
    }
    
    public void killListenChannels() {
        synchronized (this.listenChannels) {
            while (this.listenChannels.size() > 0) {
                final SSHListenChannel listenChan = this.listenChannels.elementAt(0);
                listenChan.forceClose();
                this.listenChannels.removeElementAt(0);
            }
        }
    }
    
    public SSHPdu prepare(final SSHPdu pdu) {
        return pdu;
    }
    
    public void transmit(final SSHPdu pdu) {
        this.txQueue.putLast(pdu);
    }
    
    public void receive(final SSHPdu pdu) {
        final SSHPduInputStream inPdu = (SSHPduInputStream)pdu;
        try {
            switch (inPdu.type) {
                case 17: {
                    if (this.console != null) {
                        this.console.stdoutWriteString(inPdu.readStringAsBytes());
                        break;
                    }
                    break;
                }
                case 18: {
                    if (this.console != null) {
                        this.console.stderrWriteString(inPdu.readStringAsBytes());
                        break;
                    }
                    break;
                }
                case 20: {
                    final SSHPduOutputStream exitPdu = new SSHPduOutputStream(33, this.sndCipher);
                    final int status = inPdu.readInt();
                    if (this.console != null) {
                        if (status != 0) {
                            this.console.serverDisconnect(this.sshAsClient().getServerAddr().getHostName() + " disconnected: " + status);
                        }
                        else {
                            this.console.serverDisconnect("Connection to " + this.sshAsClient().getServerAddr().getHostName() + " closed.");
                        }
                    }
                    this.transmit(exitPdu);
                    this.sshAsClient().disconnect(true);
                    break;
                }
                case 27:
                case 29: {
                    this.cnQueue.putLast(inPdu);
                    break;
                }
                case 23: {
                    final int channelNum = inPdu.readInt();
                    final SSHTunnel tunnel = (SSHTunnel)this.tunnels[channelNum];
                    if (tunnel != null) {
                        tunnel.transmit(pdu);
                        break;
                    }
                    throw new Exception("Data on nonexistent channel: " + channelNum);
                }
                case 21: {
                    final int channelNum = inPdu.readInt();
                    final SSHTunnel tunnel = (SSHTunnel)this.tunnels[channelNum];
                    if (tunnel == null) {
                        throw new Exception("Open confirm on nonexistent: " + channelNum);
                    }
                    if (!tunnel.setRemoteChannelId(inPdu.readInt())) {
                        throw new Exception("Open confirmation on allready opened channel!");
                    }
                    tunnel.start();
                    break;
                }
                case 22: {
                    final int channelNum = inPdu.readInt();
                    final SSHTunnel failTunnel;
                    if ((failTunnel = this.delTunnel(channelNum)) != null) {
                        this.alert("Channel open failure on " + failTunnel.remoteDesc);
                        failTunnel.openFailure();
                        break;
                    }
                    throw new Exception("Open failure on nonexistent channel: " + channelNum);
                }
                case 24: {
                    final int channelNum = inPdu.readInt();
                    final SSHTunnel tunnel = (SSHTunnel)this.tunnels[channelNum];
                    if (tunnel != null) {
                        tunnel.receiveInputEOF();
                        break;
                    }
                    throw new Exception("Input eof on nonexistent channel: " + channelNum);
                }
                case 25: {
                    final int channelNum = inPdu.readInt();
                    final SSHTunnel tunnel;
                    if (channelNum < this.tunnels.length && (tunnel = (SSHTunnel)this.tunnels[channelNum]) != null) {
                        tunnel.receiveOutputClosed();
                        break;
                    }
                    throw new Exception("Output closed on nonexistent channel: " + channelNum);
                }
                case 1: {
                    this.disconnect("Peer disconnected: " + inPdu.readString());
                    break;
                }
                case 11: {
                    break;
                }
                case 16: {
                    break;
                }
                case 19: {
                    System.out.println("!!! EOF received...");
                    break;
                }
                case 33: {
                    break;
                }
                default: {
                    throw new Exception("Unknown packet type (" + inPdu.type + "), disconnecting...");
                }
            }
        }
        catch (Exception e) {
            final StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            System.out.println("\nplease send a mail to mats@mindbright.se with:");
            System.out.println("(I found a bug in MindTerm!), error: " + e.getMessage());
            System.out.println(sw.toString());
            this.sendDisconnect("please send a mail to mats@mindbright.se with:\n\r(I found a bug in MindTerm!), error: " + e.getMessage() + "\n\r" + kludgeLF2CRLFMap(sw.toString()));
        }
    }
    
    static String kludgeLF2CRLFMap(final String orig) {
        int o = 0;
        String result = "";
        int n;
        while ((n = orig.indexOf(10, o)) != -1) {
            result = result + orig.substring(o, n) + "\n\r";
            o = n + 1;
        }
        result += orig.substring(o);
        return result;
    }
    
    public void close(final SSHChannel chan) {
        if (chan instanceof SSHConnectChannel) {
            SSH.logExtra("Controller connect-channel closed");
        }
        else if (chan instanceof SSHTxChannel) {
            SSH.logExtra("Controller TX-channel closed");
        }
        else if (chan instanceof SSHRxChannel) {
            SSH.logExtra("Controller RX-channel closed");
        }
        else if (chan instanceof SSHListenChannel) {
            SSH.logExtra("Listen channel for port " + ((SSHListenChannel)chan).getListenPort() + " closed");
        }
        else {
            this.alert("Bug in SSHChannelController.close 'chan' is: " + chan);
        }
    }
    
    public void disconnect(final String reason) {
        if (this.sshHook.isAnSSHClient) {
            this.sshAsClient().disconnect(false);
        }
        if (this.txChan != null) {
            this.txChan.setClosePending();
        }
        if (this.console != null) {
            this.console.serverDisconnect("\r\nDisconnecting, " + reason);
        }
        else {
            SSH.log("\r\nDisconnecting, " + reason);
        }
        if (!this.sshHook.isAnSSHClient && this.rxChan != null) {
            this.rxChan.forceClose();
        }
    }
    
    public void sendDisconnect(final String reason) {
        try {
            final SSHPduOutputStream pdu = new SSHPduOutputStream(1, this.sndCipher);
            pdu.writeString(reason);
            if (this.txQueue != null) {
                this.txQueue.putFirst(pdu);
            }
            Thread.sleep(300L);
            this.disconnect(reason);
        }
        catch (Exception e) {
            this.alert("Error in sendDisconnect: " + e.toString());
        }
    }
    
    public void alert(final String msg) {
        if (this.sshHook.isAnSSHClient) {
            final SSHInteractor interactor = this.sshAsClient().user.getInteractor();
            if (interactor != null) {
                interactor.alert(msg);
            }
        }
        else {
            SSH.log(msg);
        }
    }
    
    protected SSHClient sshAsClient() {
        return (SSHClient)this.sshHook;
    }
    
    public SSHPduQueue getCnQueue() {
        return this.cnQueue;
    }
    
    public void addHostMapTemporary(final String fromHost, final String toHost, final int toPort) {
        this.cnChan.addHostMapTemporary(fromHost, toHost, toPort);
    }
    
    public void addHostMapPermanent(final String fromHost, final String toHost, final int toPort) {
        this.cnChan.addHostMapPermanent(fromHost, toHost, toPort);
    }
    
    public void delHostMap(final String fromHost) {
        this.cnChan.delHostMap(fromHost);
    }
    
    public Vector getHostMap(final String fromHost) {
        return this.cnChan.getHostMap(fromHost);
    }
}
