// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.SwingUtilities;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.hw.client.util.a;
import java.net.URLConnection;
import java.util.Observer;
import java.util.Observable;
import java.net.URL;
import java.io.InputStream;

public final class t extends InputStream implements Runnable
{
    public static final Integer a;
    private static Integer d;
    private static Integer e;
    private static Integer f;
    private static Integer g;
    private static Integer h;
    private byte[] i;
    private URL j;
    private InputStream k;
    private int l;
    private int m;
    private int n;
    private Thread o;
    public Observable b;
    public Observer c;
    private boolean p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private boolean u;
    private URLConnection v;
    
    public t(final URL j, final boolean b, final Observer c, final int n, final int n2) {
        this.m = 0;
        this.n = 0;
        this.c = c;
        this.j = j;
        this.s = 0;
        this.t = 0;
        this.u = false;
        this.b = new Observable();
        this.l = -1;
        this.p = false;
        this.q = false;
        this.r = false;
    }
    
    public final synchronized void a() {
        if (!this.r) {
            com.hw.client.util.a.c("UrlIS: Loading " + this.j);
            this.r = true;
            (this.o = new Thread(this)).start();
        }
    }
    
    public final int available() {
        this.a();
        return this.m - this.n;
    }
    
    public final void run() {
        int n = 0;
        int n2 = 0;
        while (n == 0 && n2 < 3) {
            ++n2;
            try {
                this.v = ca.a(this.j, this.u);
                this.k = this.v.getInputStream();
                final int contentLength = this.v.getContentLength();
                com.hw.client.util.a.c("UrlIS.runLoader:connection.getContentLength()=" + contentLength);
                n = 1;
                if (this.p) {
                    if (contentLength != this.l) {
                        com.hw.client.util.a.e("UrlIS: Not same length twice: l=" + contentLength + " & len=" + contentLength + ", useCaches=" + this.u);
                        n = 0;
                        n2 = 10;
                    }
                }
                else {
                    synchronized (this) {
                        this.l = contentLength;
                        if (this.l >= 0) {
                            this.i = new byte[this.l];
                        }
                        this.p = true;
                        this.notifyAll();
                    }
                    this.a(VT_6_1_0_11.t.a);
                    Thread.yield();
                }
            }
            catch (FileNotFoundException ex5) {
                com.hw.client.util.a.d("UrlIS: init failed, try=" + n2 + ", not found, url=" + this.j);
                n2 = 10;
            }
            catch (IOException ex) {
                com.hw.client.util.a.e("UrlIS: init failed, try=" + n2 + ", exc=" + ex + ", url=" + this.j);
            }
            catch (Exception ex2) {
                com.hw.client.util.a.b("UrlIS: init failed, try=" + n2 + ", url=" + this.j, ex2);
            }
            if (n != 0) {
                n = 0;
                try {
                    if (this.l >= 0) {
                        int m = 0;
                        int read;
                        while (m < this.l && (read = this.k.read(this.i, m, Math.min(this.l - m, 1024))) != -1) {
                            synchronized (this) {
                                if ((m += read) > this.m) {
                                    this.m = m;
                                    this.notifyAll();
                                }
                            }
                            this.a(VT_6_1_0_11.t.d);
                            Thread.yield();
                        }
                        if (this.m != this.l) {
                            throw new IOException("Unexpected end of stream, nbread=" + m + ", expected" + this.l);
                        }
                        n = 1;
                    }
                    else {
                        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        final byte[] array = new byte[10240];
                        int read2;
                        while ((read2 = this.k.read(array, 0, array.length)) != -1) {
                            byteArrayOutputStream.write(array, 0, read2);
                        }
                        this.i = byteArrayOutputStream.toByteArray();
                        this.l = this.i.length;
                        n = 1;
                    }
                }
                catch (IOException ex3) {
                    com.hw.client.util.a.e("UrlIS: init failed, try=" + n2 + ", exc=" + ex3 + ", url=" + this.j);
                }
                catch (Exception ex4) {
                    com.hw.client.util.a.b("UrlIS: load failed, try=" + n2 + ", url=" + this.j, ex4);
                }
            }
            if (n == 0) {
                this.k = ca.a(this.k);
            }
        }
        synchronized (this) {
            if (n == 0) {
                this.q = true;
                this.a(VT_6_1_0_11.t.e);
                com.hw.client.util.a.b("urlis:updateObserver(FAILED)");
                this.close();
            }
            else {
                com.hw.client.util.a.c("UrlIS: Loaded " + this.j);
                this.a(VT_6_1_0_11.t.h);
                this.q = true;
                this.k = ca.a(this.k);
                this.o = ca.a(this.o);
                this.notifyAll();
            }
        }
    }
    
    public final synchronized int read(final byte[] array, final int n, int i) {
        if (i < 0) {
            throw new IOException("Can not read " + i + " bytes!!!");
        }
        if (!this.r) {
            this.a();
        }
        boolean b = false;
        while ((!this.p || this.m < this.t + this.s) && !this.q) {
            b = true;
            this.a(VT_6_1_0_11.t.f);
            try {
                this.wait(2000L);
            }
            catch (Exception ex) {}
        }
        if (b) {
            this.a(VT_6_1_0_11.t.g);
        }
        if (i != 0) {
            while (i > this.m - this.n) {
                if (this.m == this.l) {
                    if ((i = this.m - this.n) > 0) {
                        System.arraycopy(this.i, this.n, array, n, i);
                        this.n += i;
                        return i;
                    }
                    return -1;
                }
                else {
                    if (this.n < this.m) {
                        i = this.m - this.n;
                        System.arraycopy(this.i, this.n, array, n, i);
                        this.n += i;
                        return i;
                    }
                    try {
                        this.wait(200L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
            System.arraycopy(this.i, this.n, array, n, i);
            this.n += i;
            return i;
        }
        if (this.n == this.l) {
            return -1;
        }
        return 0;
    }
    
    public final synchronized int read() {
        final byte[] array = { 0 };
        if (this.read(array, 0, 1) == -1) {
            return -1;
        }
        return array[0];
    }
    
    public final synchronized void reset() {
        this.a();
        this.n = 0;
        this.notifyAll();
    }
    
    public final synchronized long skip(long min) {
        this.a();
        final long n = this.n;
        min = Math.min(min, this.m - this.n);
        this.n += (int)min;
        this.notifyAll();
        return this.n - n;
    }
    
    public final boolean markSupported() {
        return true;
    }
    
    public final synchronized void mark(final int n) {
        this.a();
        this.notifyAll();
    }
    
    public final synchronized void close() {
        if (Thread.currentThread() != this.o) {
            this.o = ca.a(this.o);
        }
        this.k = ca.a(this.k);
        this.i = null;
        final boolean n = false;
        this.m = (n ? 1 : 0);
        this.l = (n ? 1 : 0);
        this.n = (n ? 1 : 0);
        this.q = true;
        this.notifyAll();
        this.o = ca.a(this.o);
    }
    
    private void a(final Object o) {
        if (this.c != null) {
            SwingUtilities.invokeLater(new cw(this, o));
        }
    }
    
    public final URLConnection b() {
        return this.v;
    }
    
    public final String toString() {
        return "UrlInputStream:url=" + this.j;
    }
    
    static {
        a = new Integer(1);
        t.d = new Integer(2);
        t.e = new Integer(3);
        t.f = new Integer(4);
        t.g = new Integer(5);
        t.h = new Integer(6);
    }
}
