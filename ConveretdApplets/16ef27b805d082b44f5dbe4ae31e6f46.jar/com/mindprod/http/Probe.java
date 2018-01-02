// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.http;

import java.net.URISyntaxException;
import java.net.URI;
import java.io.InputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;

public final class Probe extends Http
{
    public int send(final URL url) {
        try {
            this.responseCode = -1;
            this.responseMessage = "no connect";
            this.url = url;
            HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
            urlc.setAllowUserInteraction(false);
            urlc.setDoInput(true);
            urlc.setDoOutput(false);
            urlc.setUseCaches(false);
            urlc.setRequestMethod("HEAD");
            this.setStandardProperties(urlc);
            urlc.connect();
            this.responseCode = urlc.getResponseCode();
            this.responseMessage = urlc.getResponseMessage();
            urlc.disconnect();
            if (this.responseCode == 200) {
                return this.responseCode;
            }
            urlc = (HttpURLConnection)url.openConnection();
            urlc.setAllowUserInteraction(false);
            urlc.setDoInput(true);
            urlc.setDoOutput(false);
            urlc.setUseCaches(false);
            urlc.setRequestMethod("GET");
            this.setStandardProperties(urlc);
            urlc.connect();
            this.responseCode = urlc.getResponseCode();
            this.responseMessage = urlc.getResponseMessage();
            final InputStream is = urlc.getInputStream();
            is.close();
            urlc.disconnect();
            return this.responseCode;
        }
        catch (ClassCastException e) {
            return this.responseCode;
        }
        catch (IOException e2) {
            return this.responseCode;
        }
    }
    
    public int send(final String host, final int port, final String action) {
        try {
            this.responseCode = -1;
            this.responseMessage = "no connect";
            URL url = new URI("http", null, host, port, action, null, null).toURL();
            url = new URL(url.toString() + this.getEncodedParms(Probe.UTF8Charset));
            return this.send(url);
        }
        catch (URISyntaxException e) {
            return this.responseCode;
        }
        catch (IOException e2) {
            return this.responseCode;
        }
    }
}
