// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.io.IOException;
import java.net.URLConnection;
import java.net.URL;
import java.net.URLStreamHandler;

class l extends URLStreamHandler
{
    protected final URLConnection openConnection(final URL url) throws IOException {
        return new p(url);
    }
}
