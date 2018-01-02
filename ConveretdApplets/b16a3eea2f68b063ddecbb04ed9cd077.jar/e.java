import java.io.IOException;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class e implements Runnable
{
    public Properties a;
    private IOException b;
    private boolean c;
    private boolean d;
    private int e;
    private Thread f;
    
    public e(final Properties a) {
        this.a = null;
        this.a = a;
        this.b = null;
        this.d = false;
        this.c = false;
        this.e = 0;
    }
    
    public final boolean a(final int e) throws IOException {
        this.b = null;
        this.d = false;
        this.c = false;
        this.e = 0;
        this.b();
        (this.f = new Thread(this)).start();
        this.c(this.e = e);
        if (this.d) {
            if (this.b != null) {
                System.out.println(this.b.toString());
            }
            else {
                System.out.println(this.getClass().getName() + ": Connection timed out");
            }
            return false;
        }
        return this.c;
    }
    
    public void run() {
        IOException ex = null;
        boolean b;
        try {
            b = this.b(this.e);
        }
        catch (Exception ex2) {
            b = false;
            ex = new IOException(ex2.getMessage());
        }
        if (Thread.currentThread() == this.f) {
            this.a(b, ex);
        }
    }
    
    private synchronized void c(final int n) {
        if (this.c) {
            return;
        }
        try {
            this.wait(n);
            if (!this.c) {
                this.d = true;
                final Thread f = this.f;
                this.f = null;
                f.interrupt();
            }
        }
        catch (Exception ex) {}
    }
    
    private synchronized void a(final boolean c, final IOException b) {
        try {
            if (this.d && c) {
                this.c = false;
                this.b();
            }
            else {
                this.c = c;
            }
            this.b = b;
            this.notify();
        }
        catch (Exception ex) {}
    }
    
    public abstract boolean b(final int p0) throws IOException;
    
    public abstract int a(final byte[] p0, final int p1, final int p2) throws IOException;
    
    public final void a(final byte[] array) throws IOException {
        this.b(array, 0, array.length);
    }
    
    public abstract void b(final byte[] p0, final int p1, final int p2) throws IOException;
    
    public abstract void a() throws IOException;
    
    public abstract void b();
}
