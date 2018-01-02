// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util.zip;

import java.io.IOException;
import java.net.URLConnection;
import java.net.URL;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler
{
    public URLConnection openConnection(final URL url) throws IOException {
        return new ZipURLConnection(url);
    }
}
