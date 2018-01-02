// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.Socket;

public class SSHTunnel implements SSHChannelListener
{
    int channelId;
    int remoteChannelId;
    boolean sentInputEOF;
    boolean sentOutputClosed;
    boolean receivedInputEOF;
    boolean receivedOutputClosed;
    protected SSHChannelController controller;
    protected Socket ioSocket;
    protected SSHTxChannel txChan;
    protected SSHRxChannel rxChan;
    protected SSHPduQueue txQueue;
    protected String remoteDesc;
    
    public SSHTunnel(final Socket ioSocket, final int channelId, final int remoteChannelId, final SSHChannelController controller) throws IOException {
        this.ioSocket = ioSocket;
        this.channelId = channelId;
        this.remoteChannelId = remoteChannelId;
        this.controller = controller;
        this.sentInputEOF = false;
        this.sentOutputClosed = false;
        this.receivedInputEOF = false;
        this.receivedOutputClosed = false;
        if (ioSocket != null) {
            try {
                this.rxChan = new SSHRxChannel(new BufferedInputStream(ioSocket.getInputStream(), 8192), channelId);
                this.txChan = new SSHTxChannel(new BufferedOutputStream(ioSocket.getOutputStream()), channelId);
            }
            catch (Exception e) {
                throw new IOException("Could not create tunnel: " + e.toString());
            }
            this.txQueue = this.txChan.getQueue();
            this.rxChan.setSSHPduFactory(new SSHPduOutputStream(23, controller.sndCipher));
            this.txChan.setSSHChannelListener(this);
            this.rxChan.setSSHChannelListener(this);
        }
    }
    
    public int getLocalPort() {
        if (this.ioSocket != null) {
            return this.ioSocket.getLocalPort();
        }
        return 0;
    }
    
    public String getLocalHost() {
        if (this.ioSocket != null) {
            return this.ioSocket.getLocalAddress().getHostAddress();
        }
        return "N/A";
    }
    
    public boolean isOpen() {
        return this.remoteChannelId != -4;
    }
    
    public boolean setRemoteChannelId(final int remoteChannelId) {
        if (this.isOpen()) {
            return false;
        }
        this.remoteChannelId = remoteChannelId;
        return true;
    }
    
    public void start() {
        this.txChan.start();
        this.rxChan.start();
    }
    
    public void openFailure() {
        if (this.ioSocket != null) {
            try {
                this.ioSocket.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public SSHPdu prepare(final SSHPdu pdu) throws IOException {
        ((SSHPduOutputStream)pdu).writeInt(this.remoteChannelId);
        return pdu;
    }
    
    public void receive(final SSHPdu pdu) {
        this.controller.transmit(pdu);
    }
    
    public void transmit(final SSHPdu pdu) {
        this.txQueue.putLast(pdu);
    }
    
    public void close(final SSHChannel chan) {
        if (chan == null || chan instanceof SSHTxChannel) {
            this.sendOutputClosed();
            try {
                this.ioSocket.close();
            }
            catch (IOException e) {
                this.controller.alert("Error closing socket for: " + this.channelId + " : " + e.toString());
            }
        }
        else {
            this.sendInputEOF();
        }
        this.checkTermination();
    }
    
    public synchronized void terminateNow() {
        this.close(null);
    }
    
    public synchronized void checkTermination() {
        if (this.sentInputEOF && this.sentOutputClosed && this.receivedInputEOF && this.receivedOutputClosed) {
            this.controller.delTunnel(this.channelId);
            if (this.txChan != null && this.txChan.isAlive()) {
                this.txChan = null;
            }
            if (this.rxChan != null && this.rxChan.isAlive()) {
                this.rxChan = null;
            }
        }
    }
    
    public void sendOutputClosed() {
        if (this.sentOutputClosed) {
            return;
        }
        try {
            final SSHPduOutputStream pdu = new SSHPduOutputStream(25, this.controller.sndCipher);
            pdu.writeInt(this.remoteChannelId);
            this.controller.transmit(pdu);
            this.sentOutputClosed = true;
        }
        catch (Exception e) {
            this.controller.alert("Error sending output-closed: " + e.toString());
        }
    }
    
    public void sendInputEOF() {
        if (this.sentInputEOF) {
            return;
        }
        try {
            final SSHPduOutputStream pdu = new SSHPduOutputStream(24, this.controller.sndCipher);
            pdu.writeInt(this.remoteChannelId);
            this.controller.transmit(pdu);
            this.sentInputEOF = true;
        }
        catch (Exception e) {
            this.controller.alert("Error sending input-EOF: " + e.toString());
        }
    }
    
    public void receiveOutputClosed() {
        if (this.rxChan != null) {
            this.receivedOutputClosed = true;
        }
        this.sendInputEOF();
        this.checkTermination();
    }
    
    public void receiveInputEOF() {
        if (this.txChan != null) {
            this.txChan.setClosePending();
        }
        this.receivedInputEOF = true;
        this.checkTermination();
    }
    
    public void setRemoteDesc(final String desc) {
        this.remoteDesc = desc;
    }
    
    public String getDescription() {
        if (this.ioSocket != null) {
            return this.ioSocket.getInetAddress().getHostAddress() + ":" + this.ioSocket.getPort() + " <--> " + this.getLocalHost() + ":" + this.ioSocket.getLocalPort() + " <-ssh-> " + this.remoteDesc;
        }
        return "< N/A >";
    }
}
