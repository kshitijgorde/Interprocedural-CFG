import java.io.IOException;
import java.net.URLConnection;
import java.net.URL;
import I.I;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class C implements Runnable
{
    private String abs;
    private Thread append;
    private InputStream close;
    private long currentTimeMillis;
    private long equalsIgnoreCase;
    private volatile boolean exp;
    private long finalize;
    
    C(final String s) {
        this.abs = I.I(793);
        final long n = 3600L;
        this.equalsIgnoreCase = n;
        this.currentTimeMillis = n;
        try {
            final URL url = new URL(s + ((-1 != s.indexOf(I.I(805))) ? I.I(807) : I.I(805)) + System.currentTimeMillis());
            if (url.getProtocol().equalsIgnoreCase(I.I(788))) {
                final long n2 = 536870912L;
                this.equalsIgnoreCase = n2;
                this.currentTimeMillis = n2;
                return;
            }
            final URLConnection openConnection = url.openConnection();
            openConnection.setRequestProperty(I.I(809), I.I(820));
            openConnection.setUseCaches(false);
            this.close = openConnection.getInputStream();
        }
        catch (Exception ex) {
            return;
        }
        (this.append = new Thread(this, this.abs)).start();
        this.exp = true;
    }
    
    final boolean I() {
        return this.exp;
    }
    
    final long Z() {
        return this.currentTimeMillis;
    }
    
    public final void run() {
        int n = 0;
        final byte[] array = new byte[2048];
        double n2 = 3600.0;
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            do {
                final int read = this.close.read(array);
                if (-1 == read) {
                    break;
                }
                final long currentTimeMillis2 = System.currentTimeMillis();
                n += read;
                if (currentTimeMillis2 <= currentTimeMillis) {
                    continue;
                }
                this.finalize = currentTimeMillis2 - currentTimeMillis;
                final double n3 = n2 + (1000.0 * n / this.finalize - n2 / Math.exp(2.0 / n2));
                final double n4 = n3 - n2;
                n2 = n3;
                this.equalsIgnoreCase = (long)n3;
                if (0.0 > n4 && 0.005 > Math.abs(n4) / n3) {
                    break;
                }
            } while (this.finalize < 3000L);
            this.currentTimeMillis = this.equalsIgnoreCase * 19L / 20L;
            this.close.close();
        }
        catch (IOException ex) {}
        synchronized (this) {
            this.exp = false;
            this.notifyAll();
            this.append = null;
        }
    }
    
    protected final void finalize() {
        try {
            synchronized (this) {
                if (null != this.append && this.append.isAlive()) {
                    try {
                        this.append.join();
                    }
                    catch (InterruptedException ex) {}
                }
                this.append = null;
            }
        }
        finally {
            super.finalize();
        }
    }
}
