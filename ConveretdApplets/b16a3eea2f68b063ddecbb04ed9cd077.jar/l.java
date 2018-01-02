import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.io.BufferedInputStream;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class l
{
    private URL b;
    private OutputStream c;
    private InputStream d;
    private URLConnection e;
    public boolean a;
    private boolean f;
    private boolean g;
    private int h;
    
    private l(final URL b, final boolean g) {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.h = 0;
        this.a = true;
        this.f = false;
        this.b = b;
        this.g = g;
        this.e = null;
    }
    
    public l(final URL url) {
        this(url, true);
    }
    
    public final InputStream a() throws IOException {
        String contentEncoding = "";
        try {
            if (this.e == null) {
                this.b();
            }
            if (this.c != null) {
                this.d();
            }
            contentEncoding = this.e.getContentEncoding();
        }
        catch (Exception ex) {
            System.out.println("error:" + ex);
            ex.printStackTrace(System.err);
        }
        this.d = this.e.getInputStream();
        if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
            this.d = new GZIPInputStream(new BufferedInputStream(this.d, 4096));
        }
        return this.d;
    }
    
    public final OutputStream b() throws IOException {
        this.c();
        (this.e = this.b.openConnection()).setDoInput(true);
        this.e.setDoOutput(this.g);
        this.e.setUseCaches(false);
        if (this.f) {
            this.e.setRequestProperty("Accept-Encoding", "gzip");
        }
        if (this.g) {
            if (this.a) {
                this.e.setRequestProperty("Connection", "keep-alive");
            }
            this.e.setRequestProperty("Content-Type", "application/octet-stream");
            if (this.h > 0) {
                this.e.setRequestProperty("Content-Length", Integer.toString(this.h));
                this.h = 0;
            }
            this.c = this.e.getOutputStream();
        }
        else {
            this.c = null;
        }
        return this.c;
    }
    
    public final void a(final int n) {
        this.h += n;
    }
    
    private void d() {
        try {
            this.c.flush();
            this.c.close();
            this.c = null;
        }
        catch (Exception ex) {}
    }
    
    public final void c() {
        if (this.c != null) {
            this.d();
        }
        if (this.d != null) {
            try {
                this.d.close();
                this.d = null;
            }
            catch (Exception ex) {}
        }
        this.e = null;
    }
}
