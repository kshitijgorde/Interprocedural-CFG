// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.FileNotFoundException;
import com.hw.client.util.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.URL;

public final class dB implements cZ, Runnable
{
    private URL a;
    private String b;
    private aD c;
    private Thread d;
    private boolean e;
    private DataInputStream f;
    private int g;
    
    public dB(final URL a, final String b, final aD c, final int g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.g = g;
        this.e = true;
        (this.d = new Thread(this, "OBJECT_GETTER")).start();
    }
    
    public final void a() {
        this.c = null;
        this.e = false;
        ca.a((InputStream)this.f);
    }
    
    public final void run() {
        int n = 0;
        this.f = null;
        aj aj = null;
        try {
            while (this.e) {
                if (aj == null) {
                    (aj = new aj(this.a.getHost(), this.a.getPort())).a(10000);
                }
                try {
                    final String string = this.a.getFile() + "?sid=" + this.b + "&start=" + this.g;
                    try {
                        final cM a;
                        if ((a = aj.a(string)).a() == 404) {
                            n = 50;
                            throw new IOException("status code=" + a.a() + ", session does not exist...");
                        }
                        if (a.a() != 200) {
                            throw new IOException("status code=" + a.a());
                        }
                        this.f = new DataInputStream(a.c());
                    }
                    catch (bw bw) {
                        throw new IOException(bw.toString());
                    }
                    if (this.g == -1) {
                        this.b = this.f.readUTF();
                        this.c.a(this.b);
                        ++this.g;
                        com.hw.client.util.a.c("HttpOG1: Got session info sid=" + this.b);
                    }
                    else {
                        int n2 = 0;
                        Object a2;
                        while (this.e && (a2 = this.c.b().a(this.f)) != null) {
                            if (!this.e) {
                                if (aj != null) {
                                    try {
                                        aj.a();
                                    }
                                    catch (Exception ex) {
                                        com.hw.client.util.a.a("HttpOG1: exc while stopping httpConnection", ex);
                                    }
                                }
                                return;
                            }
                            this.c.a(a2);
                            ++this.g;
                            ++n2;
                        }
                        if (n2 == 0 && this.e) {
                            throw new IOException("No event at all in response");
                        }
                        com.hw.client.util.a.a("HttpOG1: Got " + n2 + " events, total=" + this.g);
                    }
                    n = 0;
                }
                catch (IOException ex4) {
                    if (!this.e) {
                        if (aj != null) {
                            try {
                                aj.a();
                            }
                            catch (Exception ex2) {
                                com.hw.client.util.a.a("HttpOG1: exc while stopping httpConnection", ex2);
                            }
                        }
                        return;
                    }
                    ++n;
                    try {
                        aj.a();
                    }
                    catch (Exception ex3) {
                        com.hw.client.util.a.a("HttpOG1: exc while stopping httpConnection", ex3);
                    }
                    aj = null;
                    com.hw.client.util.a.d("HttpObjectGetter1.run: nberr=" + n + ", exc=" + ex4);
                    if (n > 10 || ex4 instanceof FileNotFoundException) {
                        com.hw.client.util.a.e("HttpObjectGetter1.run: ends after 10 errors or file not found, ioe=" + ex4);
                        this.c.d();
                        return;
                    }
                    if (!this.e) {
                        continue;
                    }
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex6) {
                        return;
                    }
                }
            }
        }
        finally {
            if (aj != null) {
                try {
                    aj.a();
                }
                catch (Exception ex5) {
                    com.hw.client.util.a.a("HttpOG1: exc while stopping httpConnection", ex5);
                }
            }
        }
    }
    
    public final void b() {
    }
}
