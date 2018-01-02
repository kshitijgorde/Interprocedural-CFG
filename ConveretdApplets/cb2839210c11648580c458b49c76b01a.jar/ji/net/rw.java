// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import ji.io.a5;
import java.io.InputStream;
import ji.util.d;
import ji.io.h;
import ji.document.ad;
import java.io.IOException;
import java.net.URL;
import ji.io.ac;
import java.net.URLConnection;

public class rw extends URLConnection
{
    private ac a;
    private Object b;
    
    public rw(final URL url, final Object b) throws IOException {
        super(url);
        this.b = b;
        this.connect();
    }
    
    public void connect() throws IOException {
        String s = this.getClass().getName();
        if (this.b instanceof ad) {
            s = ((ad)this.b).getId();
        }
        h.f(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.getClass().getName()))).append(" to ").append(super.url))));
        final String file = super.url.getFile();
        try {
            if (ac.d(file, s)) {
                (this.a = new ac(file, false, true, 0, true, this.b, s)).a(true);
            }
        }
        catch (Exception ex) {
            d.a(ex);
            throw new IOException(ex.getMessage());
        }
        finally {
            this.b = null;
        }
        if (this.a == null) {
            throw new IllegalStateException(String.valueOf(String.valueOf(file)).concat(" not found"));
        }
    }
    
    public InputStream getInputStream() throws IOException {
        return new a5(this.a, this);
    }
}
