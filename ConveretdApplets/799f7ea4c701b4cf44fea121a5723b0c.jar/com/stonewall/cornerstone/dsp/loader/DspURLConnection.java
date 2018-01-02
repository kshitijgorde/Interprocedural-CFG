// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.loader;

import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DspURLConnection extends URLConnection
{
    public DspURLConnection(final URL url) {
        super(url);
    }
    
    @Override
    public void connect() throws IOException {
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        final String file = this.getURL().getFile().substring(1);
        final StringTokenizer st = new StringTokenizer(file, "/");
        final String software = st.nextToken();
        final String hardware = st.nextToken();
        final String id = st.nextToken();
        try {
            return Loader.getResourceAsStream(software, hardware, id);
        }
        catch (LoaderException e) {
            throw new IOException(e.getMessage());
        }
    }
}
