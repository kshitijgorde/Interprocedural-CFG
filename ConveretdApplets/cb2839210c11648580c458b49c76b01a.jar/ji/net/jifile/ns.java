// 
// Decompiled by Procyon v0.5.30
// 

package ji.net.jifile;

import java.io.IOException;
import ji.net.rw;
import ji.io.h;
import ji.document.ad;
import ji.util.d;
import java.net.URLConnection;
import java.net.URL;
import java.net.URLStreamHandler;

public class ns extends URLStreamHandler
{
    private Object a;
    
    public ns(final Object a) {
        this.a = a;
    }
    
    protected URLConnection openConnection(final URL url) throws IOException {
        if (d.cy()) {
            String s;
            if (this.a instanceof ad) {
                s = ((ad)this.a).getId();
            }
            else {
                s = this.getClass().getName();
            }
            h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.getClass().getName()))).append("openConnection() to ").append(url))));
        }
        return new rw(url, this.a);
    }
    
    public void finalize() throws Throwable {
        super.finalize();
        this.a = null;
    }
}
