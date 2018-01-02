import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.SocketPermission;
import java.net.URL;
import java.net.InetSocketAddress;

// 
// Decompiled by Procyon v0.5.30
// 

public class qent
{
    InetSocketAddress sa;
    IAXFrame ifr;
    boolean acked;
    long txtime;
    byte seqno;
    URL hostURL;
    SocketPermission sp;
    
    public qent(final String h, final int p, final IAXFrame iframe) {
        try {
            this.sa = new InetSocketAddress(InetAddress.getByName(h), p);
        }
        catch (UnknownHostException uhe) {
            uhe.printStackTrace();
            System.err.println(uhe);
        }
        this.ifr = iframe;
        this.acked = false;
        this.txtime = 0L;
        if (this.ifr.full) {
            this.seqno = ((IAXFullFrame)iframe).oseqno;
        }
    }
    
    public qent(final InetAddress h, final int p, final IAXFrame iframe) {
        this.sa = new InetSocketAddress(h, p);
        this.ifr = iframe;
        this.acked = false;
        this.txtime = 0L;
        if (this.ifr.full) {
            this.seqno = ((IAXFullFrame)iframe).oseqno;
        }
    }
    
    public qent(final IAXCall ic, final IAXFrame iframe) {
        try {
            this.sa = new InetSocketAddress(InetAddress.getByName(ic.peerAddress), 4569);
        }
        catch (UnknownHostException uhe) {
            System.err.println(uhe);
        }
        this.ifr = iframe;
        this.acked = false;
        this.txtime = 0L;
        if (this.ifr.full) {
            this.seqno = ((IAXFullFrame)iframe).oseqno;
        }
    }
    
    public void print() {
        this.ifr.print(0);
    }
}
