import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

public class fj extends Thread
{
    public static final int a = 30000;
    private boolean b;
    private URLConnection c;
    private IOException d;
    static Class e;
    
    public static URLConnection a(final String s, final boolean useCaches, final boolean b) {
        final URLConnection openConnection = new URL(s).openConnection();
        openConnection.setUseCaches(useCaches);
        openConnection.setRequestProperty("User-Agent", "FnCharts PRO");
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
                forName.getMethod("setRequestMethod", (fj.e == null) ? (fj.e = class$("java.lang.String")) : fj.e).invoke(openConnection, "POST");
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
        openConnection.setRequestProperty("Content-Length", String.valueOf(n));
        openConnection.setRequestProperty("User-Agent", "FnCharts PRO");
        a(openConnection);
        return openConnection;
    }
    
    private static void a(final URLConnection urlConnection) {
        final fj fj = new fj(urlConnection);
        fj.start();
        try {
            fj.join(30000L);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            throw new IOException("FnChartsConnectionProvider: Interrupted timeoutConnector.join()");
        }
        synchronized (fj) {
            if (!fj.b) {
                if (fj.d != null) {
                    throw fj.d;
                }
                throw new IOException("FnChartsConnectionProvider: Connection timeout");
            }
        }
    }
    
    private fj(final URLConnection c) {
        this.b = false;
        this.d = null;
        this.c = c;
        this.setDaemon(true);
    }
    
    public void run() {
        try {
            this.c.connect();
            synchronized (this) {
                this.b = true;
            }
        }
        catch (IOException d) {
            synchronized (this) {
                this.d = d;
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
