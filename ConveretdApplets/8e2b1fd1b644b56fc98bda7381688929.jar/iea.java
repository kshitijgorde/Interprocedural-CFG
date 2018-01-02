import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

public class iea extends Thread
{
    public static final int J = 30000;
    private boolean K;
    private URLConnection L;
    private IOException M;
    static Class Ooa;
    
    public static URLConnection _(final String s, final boolean useCaches, final boolean b) {
        final URLConnection openConnection = new URL(s).openConnection();
        openConnection.setUseCaches(useCaches);
        if (b) {
            openConnection.setRequestProperty("Accept-Encoding", "gzip");
        }
        a(openConnection);
        return openConnection;
    }
    
    public static URLConnection b(final String s, final int n) {
        final URLConnection openConnection = new URL(s).openConnection();
        try {
            final Class<?> forName = Class.forName("java.net.HttpURLConnection");
            if (forName.isInstance(openConnection)) {
                forName.getMethod("setRequestMethod", (iea.Ooa == null) ? (iea.Ooa = class$("java.lang.String")) : iea.Ooa).invoke(openConnection, "POST");
            }
        }
        catch (ClassNotFoundException ex) {}
        catch (SecurityException ex2) {}
        catch (NoSuchMethodException ex3) {}
        catch (IllegalArgumentException ex4) {}
        catch (IllegalAccessException ex5) {}
        catch (InvocationTargetException ex6) {}
        openConnection.setUseCaches(false);
        openConnection.setAllowUserInteraction(false);
        openConnection.setDoInput(true);
        if (!s.startsWith("file:")) {
            openConnection.setDoOutput(true);
        }
        openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        openConnection.setRequestProperty("Content-length", String.valueOf(n));
        openConnection.setRequestProperty("User-Agent", "FnCharts PRO");
        a(openConnection);
        return openConnection;
    }
    
    private static void a(final URLConnection urlConnection) {
        final iea iea = new iea(urlConnection);
        iea.start();
        try {
            iea.join(30000L);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            throw new IOException("FnChartsConnectionProvider: Interrupted timeoutConnector.join()");
        }
        synchronized (iea) {
            if (!iea.K) {
                if (iea.M != null) {
                    throw iea.M;
                }
                throw new IOException("FnChartsConnectionProvider: Connection timeout");
            }
        }
    }
    
    private iea(final URLConnection l) {
        this.K = false;
        this.M = null;
        this.L = l;
        this.setDaemon(true);
    }
    
    public void run() {
        try {
            this.L.connect();
            synchronized (this) {
                this.K = true;
            }
        }
        catch (IOException m) {
            synchronized (this) {
                this.M = m;
            }
        }
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
