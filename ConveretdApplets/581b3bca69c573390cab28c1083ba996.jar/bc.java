import java.awt.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class bc extends Thread
{
    static final int a = -1;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    static final int e = 4;
    protected IpixViewer f;
    protected InputStream g;
    protected o h;
    protected URL i;
    boolean j;
    protected boolean k;
    protected boolean l;
    protected Object m;
    
    bc(final URL i) {
        this.j = (System.getProperty("os.name").startsWith("Win") && System.getProperty("java.vendor").startsWith("Netscape") && System.getProperty("java.version").compareTo("1.1") >= 0);
        this.k = false;
        this.l = false;
        this.m = new Object();
        this.i = i;
    }
    
    void a(final InputStream g) {
        this.g = g;
    }
    
    void a(final IpixViewer f) {
        this.f = f;
    }
    
    void a(final o h) {
        this.h = h;
        this.start();
    }
    
    protected void a() throws IOException, RuntimeException, InterruptedException, Exception {
        this.h.a("DefViewpoint", new float[] { 0.0f, 0.0f, 0.0f, 0.1f });
        this.a(this.h, 0);
    }
    
    public void run() {
        try {
            if (this.k) {
                throw new Exception("exit");
            }
            this.a();
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            if (t.getMessage() == "exit") {
                this.f.a(4, 0.0f);
                return;
            }
            t.printStackTrace();
            this.f.a(-1, 0.0f);
        }
        finally {
            this.f = null;
            synchronized (this.m) {
                this.l = true;
                this.k = false;
            }
        }
    }
    
    protected byte[] a(final byte[] array, final int n) {
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    protected byte[] a(final DataInputStream dataInputStream) throws IOException {
        byte[] a = new byte[6];
        dataInputStream.readFully(a, 0, 6);
        int n2;
        for (int n = 2; a[n + 1] != -64; n += 2 + n2) {
            if (a[n + 1] == -38) {
                return a;
            }
            n2 = (a[n + 2] << 8 & 0xFF00) + (a[n + 3] & 0xFF);
            a = this.a(a, n + 4 + (n2 + 2));
            dataInputStream.readFully(a, n + 4, n2 + 2);
        }
        return a;
    }
    
    protected int b(final byte[] array, final int n) throws RuntimeException {
        for (int i = array.length - 2; i > n; --i) {
            if (array[i] == -1) {
                if (array[i + 1] == -39) {
                    return i;
                }
                if (array[i + 1] == -38) {
                    return i;
                }
            }
        }
        throw new RuntimeException();
    }
    
    protected void a(final o o, final int n) throws IOException, InterruptedException, Exception {
        final byte[] a = this.a(new DataInputStream(this.g));
        int length = a.length;
        final byte[] a2 = this.a(a, n);
        while (!this.k) {
            Thread.yield();
            final int min = Math.min(Math.max(1024, this.g.available()), a2.length - length);
            if (min == 0) {
                return;
            }
            int i;
            int n2;
            for (i = 0; i < min; i += n2) {
                if (this.j) {
                    n2 = this.g.read(a2, length + i, a2.length - length - i);
                }
                else {
                    n2 = this.g.read(a2, length + i, min - i);
                }
                if (n2 == -1) {
                    throw new IOException();
                }
            }
            length += i;
            Thread.yield();
            try {
                final int b = this.b(a2, length - i);
                if (a2[b + 1] != -38) {
                    this.a(o, new be(this.i).a(a2, 0, b + 1));
                    this.f.a(2, 1.0f);
                    return;
                }
                a2[b + 1] = -39;
                this.a(o, new be(this.i).a(a2, 0, b + 1));
                this.f.a(1, 1.0f);
                a2[b + 1] = -38;
            }
            catch (RuntimeException ex) {}
        }
        throw new Exception("exit");
    }
    
    void a(final int n) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.m) {
            if (this.l) {
                return;
            }
            this.k = true;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        while (this.k && System.currentTimeMillis() - currentTimeMillis < n) {
            Thread.sleep(10L);
        }
    }
    
    protected abstract void a(final o p0, final Image p1) throws InterruptedException;
}
