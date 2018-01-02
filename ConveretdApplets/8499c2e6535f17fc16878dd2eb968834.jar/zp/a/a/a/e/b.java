// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.e;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;

final class b
{
    private URL if;
    private String a;
    
    public b(final String s) throws MalformedURLException {
        this.if = new URL(s);
    }
    
    public b(final String s, final String a) throws MalformedURLException {
        this.a = a;
        this.if = new URL(s + a);
    }
    
    public DataInputStream a(final String s) throws IOException {
        try {
            return this.if(s);
        }
        catch (IOException ex) {
            throw ex;
        }
    }
    
    public URL a() {
        return this.if;
    }
    
    private DataInputStream if(final String s) throws IOException {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.a());
        if (!sb.toString().endsWith("/") && !s.startsWith("/")) {
            sb.append("/?");
        }
        sb.append(s);
        final URL url = new URL(sb.toString());
        try {
            return new DataInputStream(new BufferedInputStream(url.openStream(), 4096));
        }
        catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }
}
