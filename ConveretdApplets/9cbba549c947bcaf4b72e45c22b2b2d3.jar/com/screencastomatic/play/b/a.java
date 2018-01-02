// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.b;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import com.screencastomatic.play.q;

class a extends Thread
{
    private g b;
    private boolean c;
    final /* synthetic */ b a;
    
    public a(final b a, final g b) {
        this.a = a;
        this.b = b;
        while (this.b.a() && !this.b.d) {
            this.b = a.f.a.a(this.b.a + 1);
        }
        if (this.b.d) {
            q.a("Looks like we've got the last buffer so not downloading.");
            return;
        }
        q.a("Starting download at buffer: " + this.b.a + " num starts: " + a.d++);
        this.c = true;
        this.start();
    }
    
    public boolean a(final g g) {
        if (this.b == g) {
            q.a("Download working on current buffer so not stopping. (" + g.a + " len " + g.b + ")");
            return this.isAlive();
        }
        if (!g.a()) {
            q.a("Download not working on current buffer and we need some in buffer. (" + g.a + " len " + g.b + ")");
            return false;
        }
        final g c = this.a.f.a.c(g.a);
        if (g.a + this.a.c() > c.a && this.b.a > c.a) {
            q.a("Download worked ahead but we need to go back (" + g.a + " + " + this.a.c() + " > " + c.a + " current:" + this.b.a + ")");
            return false;
        }
        if (!this.isAlive()) {
            q.a("Download not alive so saying not to continue.");
            return false;
        }
        return true;
    }
    
    public void a() {
        this.c = false;
        com.screencastomatic.play.stream.q.a(this, "Download thread - " + this.b.a);
    }
    
    public void run() {
        int n = 0;
        try {
            final int n2 = this.b.a * 204800 + this.b.b;
            int n3 = -1;
            final g b = this.a.f.a.b(this.b.a);
            if (b != null) {
                n3 = b.a * 204800;
            }
            q.a("Starting download range: " + n2 + " - " + ((n3 != -1) ? n3 : ""));
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.a.c).openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Range", "bytes=" + n2 + "-" + ((n3 != -1) ? n3 : ""));
            httpURLConnection.setDoInput(true);
            final int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200 && responseCode != 206) {
                this.a.e = new RuntimeException("Response code invalid so marking as bad url: " + responseCode);
                throw new RuntimeException("Response code invalid so marking as bad url: " + responseCode);
            }
            final InputStream inputStream = httpURLConnection.getInputStream();
            int read = 0;
            try {
                while (this.c && (read = inputStream.read(this.b.c, this.b.b, Math.min(4000, 204800 - this.b.b))) != -1) {
                    final g b2 = this.b;
                    b2.b += read;
                    n += read;
                    if (this.b.b == 204800) {
                        if (this.b.a > this.a.f.c.a().a + this.a.c()) {
                            q.a("Stopping download since way a head: (" + this.a.f.c.a().a + " + " + this.a.c() + " < " + this.b.a + ")");
                            break;
                        }
                        q.a("Download moving to next buffer: " + this.b.a);
                        this.b = this.a.f.a.a(this.b.a + 1);
                    }
                }
            }
            finally {
                q.a("Closing download input stream.");
                inputStream.close();
            }
            if (read == -1 && n3 == -1) {
                this.b.d = true;
            }
        }
        catch (Throwable t) {
            q.a(t);
            this.a.e = t;
        }
        q.a("Download thread going away. " + n);
    }
}
