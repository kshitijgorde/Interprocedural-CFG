// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.FileNotFoundException;
import com.hw.client.util.a;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.URL;

public final class cr implements cZ, Runnable
{
    private URL a;
    private String b;
    private aD c;
    private Thread d;
    private boolean e;
    private DataInputStream f;
    private int g;
    private boolean h;
    
    public cr(final URL a, final String b, final aD c) {
        this.h = false;
        this.a = a;
        this.b = b;
        this.c = c;
        this.g = -1;
        this.e = true;
        (this.d = new Thread(this, "OBJECT_GETTER")).start();
    }
    
    public final void a() {
        this.e = false;
        this.c = null;
        ca.a((InputStream)this.f);
        ca.a((URLConnection)null);
    }
    
    public final void run() {
        int n = 0;
        try {
            while (this.e && !this.h) {
                try {
                    final URLConnection a = ca.a(new URL(this.a, this.a.getFile() + "?sid=" + this.b + "&start=" + this.g), false);
                    int responseCode = 200;
                    if (a instanceof HttpURLConnection) {
                        responseCode = ((HttpURLConnection)a).getResponseCode();
                    }
                    if (responseCode == 404) {
                        n = 50;
                        throw new IOException("status code=" + responseCode + ", session does not exist...");
                    }
                    if (responseCode != 200) {
                        throw new IOException("status code=" + responseCode);
                    }
                    this.f = new DataInputStream(a.getInputStream());
                    if (this.g == -1) {
                        this.b = this.f.readUTF();
                        this.c.a(this.b);
                        ++this.g;
                        com.hw.client.util.a.c("HttpOG: Got session info sid=" + this.b);
                    }
                    else {
                        int n2 = 0;
                        Object a2;
                        while (this.e && (a2 = this.c.b().a(this.f)) != null) {
                            if (!this.e) {
                                return;
                            }
                            this.c.a(a2);
                            ++this.g;
                            ++n2;
                        }
                        this.f.close();
                        if (n2 == 0 && this.e) {
                            throw new IOException("No event at all in response");
                        }
                        com.hw.client.util.a.a("HttpOG: Got " + n2 + " events, total=" + this.g);
                    }
                    n = 0;
                }
                catch (IOException ex) {
                    if (!this.e) {
                        return;
                    }
                    ++n;
                    com.hw.client.util.a.d("HttpObjectGetter.run: nberr=" + n + ", exc=" + ex);
                    if (n > 10 || ex instanceof FileNotFoundException) {
                        com.hw.client.util.a.e("HttpObjectGetter.run ends after 10 errors or file not found, ioe=" + ex);
                        this.c.d();
                        return;
                    }
                    if (!this.e) {
                        continue;
                    }
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex3) {
                        return;
                    }
                }
            }
            if (this.h) {
                com.hw.client.util.a.d("HttpObjectGetter.run: Switching get channel");
                this.c.b = new dB(this.a, this.b, this.c, this.g);
            }
        }
        catch (RuntimeException ex2) {
            com.hw.client.util.a.b("HttpObjectGetter.run: RuntimeException", ex2);
        }
    }
    
    public final void b() {
        this.h = true;
    }
}
