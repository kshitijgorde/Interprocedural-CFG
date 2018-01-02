// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.http;

import java.net.URISyntaxException;
import java.net.URI;
import java.io.IOException;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.net.URL;

public final class Get extends Http
{
    public String send(final URL url, final Charset encoding) {
        try {
            this.responseCode = -1;
            this.responseMessage = "no connect";
            this.url = url;
            final HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
            urlc.setAllowUserInteraction(false);
            urlc.setDoInput(true);
            urlc.setDoOutput(false);
            urlc.setUseCaches(false);
            urlc.setRequestMethod("GET");
            this.setStandardProperties(urlc);
            return this.processResponse(encoding, urlc);
        }
        catch (ClassCastException e) {
            return null;
        }
        catch (IOException e2) {
            return null;
        }
    }
    
    public String send(final String host, final int port, final String action, final Charset encoding) {
        try {
            this.responseCode = -1;
            this.responseMessage = "no connect";
            URL url = new URI("http", null, host, port, action, null, null).toURL();
            url = new URL(url.toString() + this.getEncodedParms(encoding));
            return this.send(url, encoding);
        }
        catch (URISyntaxException e) {
            return null;
        }
        catch (IOException e2) {
            return null;
        }
    }
}
