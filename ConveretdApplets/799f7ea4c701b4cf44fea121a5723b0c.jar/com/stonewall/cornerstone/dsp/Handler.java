// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp;

import java.io.IOException;
import com.stonewall.cornerstone.dsp.loader.DspURLConnection;
import java.net.URLConnection;
import java.net.URL;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler
{
    @Override
    protected URLConnection openConnection(final URL url) throws IOException {
        return new DspURLConnection(url);
    }
}
