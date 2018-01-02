import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.awt.Component;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public final class i
{
    public d a;
    public long b;
    
    public i(final d a) {
        this.b = 30000L;
        this.a = a;
    }
    
    public final void a(final Properties properties) {
        final String property;
        if ((property = properties.getProperty("TimeOut")) != null) {
            try {
                final Double d = this.a.d().d(property);
                if (d >= 0.0) {
                    this.b = Math.round(1000.0 * d);
                }
                else {
                    this.a.c().a((Component)this.a.s, 4, new Object[] { property, "TimeOut", "negative" });
                }
            }
            catch (ParseException ex) {
                this.a.c().a((Component)this.a.s, 4, new Object[] { property, "TimeOut", ex.getMessage() });
            }
        }
    }
    
    public final InputStream a(final URLConnection urlConnection) throws Throwable {
        final Thread thread = new Thread(this.a, urlConnection) {
            public URLConnection a;
            public InputStream b;
            public Throwable c;
            public boolean d = false;
            public Object e = new Object();
            
            {
                i.a(i.this, d);
                i.this.a(i.a(i.this).k());
                this.a = a;
            }
            
            public final void run() {
                try {
                    this.b = this.a.getInputStream();
                }
                catch (Throwable c) {
                    this.b = null;
                    this.c = c;
                    synchronized (this.e) {
                        if (this.d && this.b != null) {
                            try {
                                this.b.close();
                            }
                            catch (IOException ex) {}
                            this.b = null;
                        }
                    }
                }
                finally {
                    synchronized (this.e) {
                        if (this.d && this.b != null) {
                            try {
                                this.b.close();
                            }
                            catch (IOException ex2) {}
                            this.b = null;
                        }
                    }
                }
            }
            
            public final Object a() {
                final InputStream b;
                synchronized (this.e) {
                    b = this.b;
                    final Throwable c = this.c;
                    this.d = (b == null);
                }
                final Throwable c;
                return (b != null) ? b : c;
            }
        };
        try {
            thread.start();
            final long b = this.b;
            if (b > 0L) {
                thread.join(b);
            }
            else {
                thread.join();
            }
        }
        catch (InterruptedException ex) {}
        final Object a = thread.a();
        if (a instanceof InputStream) {
            return (InputStream)a;
        }
        if (a instanceof Throwable) {
            throw (Throwable)a;
        }
        throw new IOException("Timed out");
    }
    
    public final void a() {
    }
    
    public static /* synthetic */ d a(final i i, final d a) {
        return i.a = a;
    }
    
    public static /* synthetic */ d a(final i i) {
        return i.a;
    }
}
