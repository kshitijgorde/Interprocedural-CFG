// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;

public final class c
{
    private URL a;
    
    public c(final String s) throws MalformedURLException {
        this.a = new URL(s);
    }
    
    private DataInputStream if(final String s) throws IOException {
        final URL url = new URL(String.valueOf(String.valueOf(this.a)) + s);
        try {
            return new DataInputStream(new BufferedInputStream(url.openStream(), 4096));
        }
        catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    public URL a() {
        return this.a;
    }
    
    public DataInputStream a(final String s) throws IOException {
        try {
            return this.if(s);
        }
        catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }
}
