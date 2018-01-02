// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol.file;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URL;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler
{
    public URLConnection openConnection(final URL url) throws IOException {
        return new FileURLConnection(url);
    }
    
    protected void parseURL(final URL url, final String s, final int i, final int j) {
        super.parseURL(url, s.replace(File.separatorChar, '/'), i, j);
    }
}
