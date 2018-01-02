import java.net.UnknownHostException;
import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;

// 
// Decompiled by Procyon v0.5.30
// 

public final class n extends Thread
{
    public InetAddress a;
    public int b;
    public IOException c;
    public Socket d;
    public boolean e;
    
    public n(final String s, final int n) throws UnknownHostException {
        this(InetAddress.getByName(s), n);
    }
    
    private n(final InetAddress a, final int b) {
        this.e = false;
        this.d = null;
        this.c = null;
        this.a = a;
        this.b = b;
        this.start();
    }
    
    public final Socket a(final int n) throws IOException {
        this.b(n);
        if (this.e) {
            if (this.c == null) {
                this.c = new IOException("Connection timed out");
            }
            this.c.fillInStackTrace();
            throw this.c;
        }
        return this.d;
    }
    
    public final void run() {
        Socket socket;
        try {
            socket = new Socket(this.a, this.b);
        }
        catch (IOException ex) {
            this.a(null, ex);
            return;
        }
        catch (Exception ex2) {
            this.a(null, new IOException(ex2.getMessage()));
            return;
        }
        this.a(socket, null);
    }
    
    private synchronized void b(final int n) {
        if (this.d != null) {
            return;
        }
        try {
            this.wait(n);
            if (this.d == null) {
                this.e = true;
            }
        }
        catch (Exception ex) {}
    }
    
    private synchronized void a(Socket d, final IOException c) {
        try {
            if (this.e && d != null) {
                d.close();
                d = null;
            }
            this.d = d;
            this.c = c;
            this.notify();
        }
        catch (Exception ex) {}
    }
}
