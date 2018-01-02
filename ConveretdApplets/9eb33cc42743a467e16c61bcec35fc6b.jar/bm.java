import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

// 
// Decompiled by Procyon v0.5.30
// 

public class bm extends b8
{
    public static boolean kl;
    public b7 fq;
    public ServerSocket kk;
    public String fg;
    public int ff;
    public InetAddress kj;
    public InetAddress ki;
    public boolean kh;
    
    public bm(final String s, final int n, final String fg, final int ff, final b7 fq) throws IOException {
        super(-3);
        this.fq = fq;
        try {
            this.kk = new ServerSocket(n, 16, InetAddress.getByName(s));
        }
        catch (IOException ex) {
            throw new IOException("Error setting up local forward on port " + n + ", " + ex.getMessage());
        }
        this.fg = fg;
        this.ff = ff;
        this.kj = InetAddress.getLocalHost();
        this.ki = InetAddress.getByName("127.0.0.1");
    }
    
    public final int i7() {
        return this.kk.getLocalPort();
    }
    
    public final String i6() {
        return this.kk.getInetAddress().getHostAddress();
    }
    
    public y i5(final Socket socket, final int n, final int n2, final b7 b7) throws IOException {
        return new y(socket, n, n2, b7);
    }
    
    public final void i4(final boolean kh) {
        this.kh = kh;
    }
    
    public final void d_() throws IOException {
        ca.me("Starting listen-chan: " + this.kk.getLocalPort());
        try {
            while (true) {
                final Socket accept = this.kk.accept();
                if (!bm.kl && !accept.getInetAddress().equals(this.kj) && !accept.getInetAddress().equals(this.ki)) {
                    this.fq.h9("Remote connect to local tunnel rejected: " + accept.getInetAddress());
                    accept.close();
                }
                else {
                    final av av = new av(29, this.fq.f7);
                    final int l2 = this.fq.l2();
                    final y i5 = this.i5(accept, l2, -4, this.fq);
                    this.fq.lz(i5);
                    i5.d6(String.valueOf(this.fg) + ":" + this.ff);
                    av.writeInt(l2);
                    av.jt(this.fg);
                    av.writeInt(this.ff);
                    ca.me("got connect for: " + this.fg + " : " + this.ff + ", " + l2);
                    av.jt(accept.getInetAddress().getHostAddress());
                    this.fq.ee(av);
                    if (this.kh) {
                        break;
                    }
                    continue;
                }
            }
        }
        finally {
            this.kk.close();
        }
    }
    
    public final void fh() {
        if (this.isAlive()) {
            this.stop();
        }
        try {
            this.kk.close();
        }
        catch (IOException ex) {}
    }
}
