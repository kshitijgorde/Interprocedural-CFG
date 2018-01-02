// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.hw.client.util.a;
import java.net.URL;

public final class D implements o, Runnable
{
    private URL a;
    private H b;
    private int c;
    private String d;
    private Thread e;
    private int f;
    private aD g;
    private boolean h;
    
    public D(final URL a, H b, final aD g) {
        this.h = false;
        this.a = a;
        this.b = b;
        this.g = g;
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
        try {
            this.c = 0;
            Vector a;
            while (!this.h && (a = this.b.a()) != null) {
                byte[] a2;
                try {
                    a2 = this.g.b().a(a);
                }
                catch (IOException ex) {
                    com.hw.client.util.a.b("HttpOP.run: SHOULD NEVER HAPPEN - FATAL", ex);
                    return;
                }
                try {
                    final byte[] array = a2;
                    final long currentTimeMillis = System.currentTimeMillis();
                    final byte[] a3;
                    if ((a3 = ca.a(new URL(this.a + "?sid=" + this.d + "&start=" + this.c), array, 0, array.length)).length != 1 || a3[0] != 65) {
                        throw new IOException("Invalid response");
                    }
                    final long currentTimeMillis2 = System.currentTimeMillis();
                    com.hw.client.util.a.c("HttpOP: Bytes posted:" + array.length + ", time=" + (currentTimeMillis2 - currentTimeMillis) + " kbps=" + (array.length << 3) * 1000 / (currentTimeMillis2 - currentTimeMillis + 1L));
                    this.b.b();
                    this.c += a.size();
                    this.f = 0;
                }
                catch (IOException ex2) {
                    ++this.f;
                    com.hw.client.util.a.d("HttpOP.run: nberr=" + this.f + ", exc=" + ex2);
                    if (this.f > 10 || ex2 instanceof FileNotFoundException) {
                        com.hw.client.util.a.e("HttpOP.run ends after 10 errors or file not found ioe=" + ex2);
                        this.g.d();
                        return;
                    }
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex3) {
                        return;
                    }
                    this.b.c();
                }
            }
            if (this.h) {
                com.hw.client.util.a.d("HttpOP.run: Switching post channel");
                this.g.a = new aQ(this.a, this.b, this.g, this.d, this.c);
            }
        }
        catch (InterruptedException ex4) {
            com.hw.client.util.a.d("HttpOP.run ends because InterruptedException");
        }
    }
    
    public final void b() {
        this.h = true;
    }
}
