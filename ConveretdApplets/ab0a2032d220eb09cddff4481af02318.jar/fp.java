import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

public class fp extends Thread
{
    public static int GGb;
    private boolean HGb;
    private URLConnection IGb;
    private IOException JGb;
    
    public static URLConnection a(final String s, final boolean useCaches, final boolean b) {
        final URLConnection openConnection = new URL(s).openConnection();
        openConnection.setUseCaches(useCaches);
        if (b) {
            openConnection.setRequestProperty("Accept-Encoding", "gzip");
        }
        b(openConnection);
        return openConnection;
    }
    
    public static URLConnection a(final String s, final int n) {
        final URLConnection openConnection = new URL(s).openConnection();
        openConnection.setUseCaches(false);
        openConnection.setAllowUserInteraction(false);
        openConnection.setDoInput(true);
        if (!s.startsWith("file:")) {
            openConnection.setDoOutput(true);
        }
        openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        openConnection.setRequestProperty("Content-length", String.valueOf(n));
        openConnection.setRequestProperty("User-Agent", "FnCharts PRO");
        b(openConnection);
        return openConnection;
    }
    
    private static void b(final URLConnection urlConnection) {
        final fp fp = new fp(urlConnection);
        fp.start();
        try {
            fp.join(fp.GGb);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            throw new IOException("FnChartsConnectionProvider: Interrupted timeoutConnector.join()");
        }
        synchronized (fp) {
            if (!fp.HGb) {
                if (fp.JGb != null) {
                    throw fp.JGb;
                }
                throw new IOException("FnChartsConnectionProvider: Connection timeout");
            }
        }
    }
    
    private fp(final URLConnection iGb) {
        this.HGb = false;
        this.JGb = null;
        this.IGb = iGb;
        this.setDaemon(true);
    }
    
    public void run() {
        try {
            this.IGb.connect();
            synchronized (this) {
                this.HGb = true;
            }
        }
        catch (IOException jGb) {
            synchronized (this) {
                this.JGb = jGb;
            }
        }
    }
    
    static {
        fp.GGb = 30000;
    }
}
