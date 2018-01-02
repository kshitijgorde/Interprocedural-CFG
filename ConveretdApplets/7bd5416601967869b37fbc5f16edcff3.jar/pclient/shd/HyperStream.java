// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import com.pchat.sc.ByteChain;
import com.pchat.sc.ByteUtil;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

public class HyperStream implements SockStream, Runnable
{
    private Vector bufferList;
    private boolean userDisconnect;
    private boolean isSockConnected;
    private NetStreamListener netListener;
    private String chatCandy;
    private URL webURL;
    private boolean keepGoing;
    private Config paraConf;
    private Thread bgRunner;
    private long connectTime;
    private boolean isSecure;
    
    public HyperStream() {
        this.userDisconnect = false;
        this.isSockConnected = false;
        this.chatCandy = null;
        this.keepGoing = false;
        this.bgRunner = null;
        this.connectTime = 0L;
        this.isSecure = false;
        this.bufferList = new Vector();
        this.keepGoing = true;
    }
    
    public void setInit(final Config paraConf, final SessionEnclosure sessionEnclosure) {
        this.paraConf = paraConf;
    }
    
    public boolean isConnected() {
        return this.isSockConnected;
    }
    
    public boolean isTunnel() {
        return true;
    }
    
    public void setSecure() {
        this.isSecure = true;
    }
    
    public void connect(final String s, final int n) throws IOException {
        this.connectTime = System.currentTimeMillis();
        final String value = this.paraConf.get("Net.Tunnel", "/parachat/pchat/chat.jsp");
        String s2 = "http";
        if (this.isSecure) {
            s2 = "https";
        }
        this.webURL = new URL(s2, s, n, value);
        this.paraConf.printer().print("tun." + this.webURL);
        this.chatCandy = HyperUtil.initCandy(this.webURL);
        if (this.chatCandy == null) {
            this.isSockConnected = false;
        }
        else {
            this.isSockConnected = true;
        }
        if (this.isSockConnected) {
            (this.bgRunner = new Thread(this)).start();
            return;
        }
        throw new IOException("error H893. not connected to chat server");
    }
    
    public long getConnTime() {
        return this.connectTime;
    }
    
    public void send(final byte[] array) throws IOException {
        final byte[] duplicate = ByteUtil.duplicate(array);
        synchronized (this.bufferList) {
            this.bufferList.addElement(duplicate);
        }
    }
    
    public void setListener(final NetStreamListener netListener) {
        this.netListener = netListener;
    }
    
    public void disconnect() {
        this.userDisconnect = true;
        this.isSockConnected = false;
        this.keepGoing = false;
        ClientUtil.doze(1200);
    }
    
    public Object secSession() {
        return null;
    }
    
    public void run() {
        final int n = 25;
        final int n2 = 200;
        int n3 = n;
        while (this.keepGoing) {
            ClientUtil.doze(n2);
            final boolean b = --n3 <= 0;
            boolean loopbody;
            try {
                loopbody = this.loopbody(b);
            }
            catch (IOException ex) {
                System.out.println("Err33128.");
                ex.printStackTrace();
                break;
            }
            catch (Exception ex2) {
                System.out.println("Err3278.");
                ex2.printStackTrace();
                break;
            }
            if (n3 <= 0) {
                n3 = n;
            }
            if (loopbody) {
                n3 = 0;
            }
        }
        if (this.userDisconnect) {
            try {
                this.sendAll();
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
        this.isSockConnected = false;
        if (this.netListener != null && !this.userDisconnect) {
            this.netListener.notifyDisconnection(0);
        }
    }
    
    private void sendAll() throws IOException {
        for (byte[] array = this.retrieve(); array != null; array = this.retrieve()) {
            final byte[] post = HyperUtil.post(this.webURL, this.chatCandy, array);
            this.doIncoming(post, post.length);
        }
    }
    
    private boolean loopbody(final boolean b) throws IOException {
        final byte[] retrieve = this.retrieve();
        if (retrieve != null) {
            final byte[] post = HyperUtil.post(this.webURL, this.chatCandy, retrieve);
            this.doIncoming(post, post.length);
            return true;
        }
        if (b) {
            final byte[] download = HyperUtil.download(this.webURL, this.chatCandy);
            this.doIncoming(download, download.length);
        }
        return false;
    }
    
    private byte[] retrieve() {
        final byte[] dequeue = this.dequeue();
        if (dequeue == null) {
            return null;
        }
        final ByteChain byteChain = new ByteChain(dequeue, 640000);
        for (int i = 0; i < 10; ++i) {
            final byte[] dequeue2 = this.dequeue();
            if (dequeue2 == null) {
                break;
            }
            byteChain.add(dequeue2);
        }
        return byteChain.getBytes(0);
    }
    
    private byte[] dequeue() {
        byte[] array = null;
        synchronized (this.bufferList) {
            if (this.bufferList.size() > 0) {
                array = this.bufferList.firstElement();
                this.bufferList.removeElementAt(0);
            }
        }
        return array;
    }
    
    private void doIncoming(final byte[] array, final int n) {
        final byte[] duplicate = ByteUtil.duplicate(array, 0, n);
        if (this.netListener != null) {
            this.netListener.notifyIncoming(duplicate);
        }
    }
}
