// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URISyntaxException;
import java.net.URI;
import java.io.OutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.net.URL;

public final class Post extends Http
{
    private String[] postParms;
    private String body;
    private String contentType;
    
    public Post() {
        this.body = "";
        this.contentType = "application/x-www-form-urlencoded";
    }
    
    public String send(final URL url, final Charset encoding) {
        try {
            this.responseCode = -1;
            this.responseMessage = "no connect";
            this.url = url;
            final HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
            urlc.setAllowUserInteraction(false);
            urlc.setDoInput(true);
            urlc.setDoOutput(true);
            urlc.setUseCaches(false);
            urlc.setRequestMethod("POST");
            this.setStandardProperties(urlc);
            final byte[] encodedPostParms = this.getEncodedPostParms(encoding).getBytes("UTF-8");
            final byte[] encodedBody = this.body.getBytes(encoding);
            urlc.setRequestProperty("Content-Length", Integer.toString(encodedPostParms.length + encodedBody.length));
            urlc.setRequestProperty("Content-Type", this.contentType);
            urlc.connect();
            final OutputStream os = urlc.getOutputStream();
            os.write(encodedPostParms);
            os.write(encodedBody);
            os.close();
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
            final String encodedParms = this.getEncodedParms(encoding);
            if (encodedParms.length() > 0) {
                url = new URL(url.toString() + this.getEncodedParms(encoding));
            }
            return this.send(url, encoding);
        }
        catch (URISyntaxException e) {
            return null;
        }
        catch (IOException e2) {
            return null;
        }
    }
    
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
    
    public void setPostBody(String body) {
        if (body == null) {
            body = "";
        }
        this.body = body;
    }
    
    public void setPostParms(String... postParms) {
        if (postParms == null) {
            postParms = new String[0];
        }
        assert (postParms.length & 0x1) == 0x0 : "must have an even number of post parms, keyword=value";
        this.postParms = postParms;
    }
    
    String getEncodedPostParms(final Charset encoding) throws UnsupportedEncodingException {
        if (this.postParms == null || this.postParms.length == 0) {
            return "";
        }
        int estLength = 10;
        for (final String p : this.postParms) {
            estLength += p.length() + 1;
        }
        final StringBuilder sb = new StringBuilder(estLength);
        for (int i = 0; i < this.postParms.length - 1; i += 2) {
            if (i != 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(this.postParms[i], encoding.name()));
            sb.append('=');
            sb.append(URLEncoder.encode(this.postParms[i + 1], encoding.name()));
        }
        return sb.toString();
    }
}
