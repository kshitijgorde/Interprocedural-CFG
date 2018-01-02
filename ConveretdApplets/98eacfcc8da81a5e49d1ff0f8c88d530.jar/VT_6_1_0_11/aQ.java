// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.InputStream;
import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.hw.client.util.a;
import java.net.URL;

public final class aQ implements o, Runnable
{
    private URL a;
    private H b;
    private int c;
    private String d;
    private Thread e;
    private int f;
    private aD g;
    
    public aQ(final URL a, H b, final aD g, final String d, final int c) {
        this.a = a;
        this.b = b;
        this.g = g;
        this.d = d;
        this.c = c;
        (b = new H()).b();
        (this.e = new Thread(this, "OBJECT_POSTER")).start();
    }
    
    public final void a(final String d) {
        this.d = d;
    }
    
    public final synchronized void a() {
        this.b.d();
    }
    
    public final void run() {
        this.f = 0;
        aj aj = null;
        try {
            Vector a;
            while ((a = this.b.a()) != null) {
                if (aj == null) {
                    (aj = new aj(this.a.getHost(), this.a.getPort())).a(10000);
                }
                byte[] a2;
                try {
                    a2 = this.g.b().a(a);
                }
                catch (IOException ex) {
                    com.hw.client.util.a.c("HttpOP1.run: SHOULD NEVER HAPPEN", ex);
                    try {
                        aj.a();
                        return;
                    }
                    catch (Exception ex2) {
                        com.hw.client.util.a.a("HttpOP1: exc while stopping httpConnection", ex2);
                        return;
                    }
                }
                try {
                    final aj aj2 = aj;
                    final byte[] array = a2;
                    final aj aj3 = aj2;
                    final long currentTimeMillis = System.currentTimeMillis();
                    final String string = this.a.getFile() + "?sid=" + this.d + "&start=" + this.c;
                    InputStream c;
                    try {
                        final cM a3;
                        if ((a3 = aj3.a(string, array)).a() == 404) {
                            this.f = 50;
                            throw new IOException("status code=" + a3.a() + ", session does not exist...");
                        }
                        if (a3.a() != 200) {
                            throw new IOException("status code=" + a3.a());
                        }
                        c = a3.c();
                    }
                    catch (bw bw) {
                        throw new IOException(bw.toString());
                    }
                    if (c.read() != 65) {
                        try {
                            c.close();
                        }
                        catch (IOException ex7) {}
                        throw new IOException("Invalid response");
                    }
                    final long currentTimeMillis2 = System.currentTimeMillis();
                    com.hw.client.util.a.c("HttpOP1: Bytes posted:" + array.length + ", time=" + (currentTimeMillis2 - currentTimeMillis) + " kbps=" + (array.length << 3) * 1000 / (currentTimeMillis2 - currentTimeMillis + 1L));
                    this.b.b();
                    this.c += a.size();
                    this.f = 0;
                }
                catch (IOException ex4) {
                    ++this.f;
                    try {
                        aj.a();
                    }
                    catch (Exception ex3) {
                        com.hw.client.util.a.a("HttpOP1: exc while stopping httpConnection", ex3);
                    }
                    aj = null;
                    com.hw.client.util.a.d("HttpOP1.run: nberr=" + this.f + ", exc=" + ex4);
                    if (this.f > 10 || ex4 instanceof FileNotFoundException) {
                        com.hw.client.util.a.e("HttpOP1.run ends after 10 errors or file not found, ioe=" + ex4);
                        this.g.d();
                        return;
                    }
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex8) {
                        return;
                    }
                    this.b.c();
                }
            }
        }
        catch (InterruptedException ex9) {
            com.hw.client.util.a.d("HttpOP1.run ends because InterruptedException");
            if (aj != null) {
                try {
                    aj.a();
                }
                catch (Exception ex5) {
                    com.hw.client.util.a.a("HttpOP1: exc while stopping httpConnection", ex5);
                }
            }
        }
        finally {
            if (aj != null) {
                try {
                    aj.a();
                }
                catch (Exception ex6) {
                    com.hw.client.util.a.a("HttpOP1: exc while stopping httpConnection", ex6);
                }
            }
        }
    }
    
    public final void b() {
    }
}
