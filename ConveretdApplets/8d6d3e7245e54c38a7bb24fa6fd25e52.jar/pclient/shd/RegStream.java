// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.net.UnknownHostException;
import com.pchat.sc.ByteUtil;
import java.util.Date;
import java.io.OutputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

public class RegStream implements SockStream, Runnable
{
    private Socket clientSock;
    private DataInputStream inStream;
    private DataOutputStream outStream;
    protected boolean userDisconnect;
    private boolean isSockConnected;
    private NetStreamListener netListener;
    private RegOut outThread;
    public SessionEnclosure chatModel;
    private long connectTime;
    
    public RegStream() {
        this.clientSock = null;
        this.userDisconnect = false;
        this.isSockConnected = false;
        this.chatModel = null;
        this.connectTime = 0L;
    }
    
    public void setInit(final Config config, final SessionEnclosure chatModel) {
        this.chatModel = chatModel;
    }
    
    public boolean isConnected() {
        return this.isSockConnected;
    }
    
    public boolean isTunnel() {
        return false;
    }
    
    public void connect(final String s, final int n) throws IOException {
        this.connectTime = System.currentTimeMillis();
        try {
            this.connectSocket(s, n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("Err CT8793. Connection failed.");
        }
        this.isSockConnected = true;
        new Thread(this).start();
        (this.outThread = new RegOut(this, this.outStream)).start();
    }
    
    public long getConnTime() {
        return this.connectTime;
    }
    
    public void send(final byte[] array) throws IOException {
        if (this.outThread != null) {
            this.outThread.send(array);
        }
    }
    
    public void setListener(final NetStreamListener netListener) {
        this.netListener = netListener;
    }
    
    public void disconnect() {
        this.userDisconnect = true;
        this.isSockConnected = false;
        if (this.outThread != null) {
            this.outThread.disconnect();
        }
        this.closeSock();
    }
    
    public Object secSession() {
        return null;
    }
    
    protected void closeSock() {
        if (this.clientSock == null) {
            return;
        }
        if (this.inStream != null) {
            try {
                this.inStream.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (this.outStream != null) {
            try {
                this.outStream.close();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        try {
            this.clientSock.close();
        }
        catch (Exception ex3) {
            System.out.println("error closing conn");
            ex3.printStackTrace();
        }
        this.clientSock = null;
    }
    
    public void run() {
        final byte[] array = new byte[256];
        try {
            while (this.isConnected()) {
                final int read = this.inStream.read(array);
                if (read < 0) {
                    System.out.println("Stream ended.");
                    break;
                }
                if (read == 0) {
                    System.out.println("IO error #82975.");
                    ClientUtil.doze(150);
                }
                else {
                    if (!this.isConnected()) {
                        continue;
                    }
                    try {
                        this.doIncoming(array, read);
                    }
                    catch (Exception ex) {
                        System.out.println("Err53923");
                        ex.printStackTrace();
                    }
                }
            }
        }
        catch (Exception ex2) {
            if (!this.userDisconnect) {
                System.out.println("Err83472");
                ex2.printStackTrace();
            }
        }
        System.out.println(new Date() + " in-stream end. " + this.getConnTime());
        this.isSockConnected = false;
        this.outThread.stopIt();
        this.errorNotify();
    }
    
    private void doIncoming(final byte[] array, final int n) {
        final byte[] duplicate = ByteUtil.duplicate(array, 0, n);
        if (this.netListener != null) {
            this.netListener.notifyIncoming(duplicate);
        }
    }
    
    protected void errorNotify() {
        if (this.userDisconnect) {
            return;
        }
        if (this.netListener != null) {
            this.netListener.notifyDisconnection(0);
        }
    }
    
    private void connectSocket(final String s, final int n) throws IOException, UnknownHostException {
        System.out.println(new Date() + " connecting to " + n + " " + s);
        this.clientSock = new Socket(s, n);
        this.inStream = new DataInputStream(this.clientSock.getInputStream());
        this.outStream = new DataOutputStream(this.clientSock.getOutputStream());
        try {
            System.out.println("local=" + this.clientSock.getLocalAddress().getHostAddress());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
