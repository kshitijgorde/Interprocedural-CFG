// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.http;

import java.util.Locale;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.IllegalCharsetNameException;
import com.mindprod.common11.ST;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

abstract class Http
{
    static final boolean DEBUGGING = false;
    static final int DEFAULT_LENGTH = 32768;
    static final int DEFAULT_RESPONSE_CODE = -1;
    private static final String ACCEPT_CHARSET = "iso-8859-1,utf-8,utf-16;q=0.7,*;q=0.3";
    private static final String ACCEPT_ENCODING = "gzip,x-gzip,identity";
    private static final String ACCEPT_PROPERTY = "application/octet-stream,application/x-java-jnlp-file,application/x-java-serialized-object,application/xhtml+xml,application/xml,application/zip,image/gif,image/jpeg,image/png,text/css,text/html,text/plain,text/x-java-source,text/xml;q=0.9,*/*;q=0.8";
    static final String DEFAULT_RESPONSE_MESSAGE = "no connect";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1998-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-05-19";
    public static final String VERSION_STRING = "2.7";
    private static final String[] responseCodeLookup;
    public static final Charset UTF8Charset;
    private String[] parms;
    private String[] requestProperties;
    private String referer;
    String responseMessage;
    private String userAgent;
    URL url;
    private boolean followRedirects;
    private int connectTimeout;
    int readTimeout;
    int responseCode;
    
    public String getRawResponseMessage() {
        return this.responseMessage;
    }
    
    public String getReferer() {
        return this.referer;
    }
    
    public void setReferer(final String referer) {
        this.referer = referer;
    }
    
    public int getResponseCode() {
        return this.responseCode;
    }
    
    public String getResponseMessage() {
        return responseCodeToResponseMessage(this.responseCode, this.responseMessage);
    }
    
    public URL getURL() {
        return this.url;
    }
    
    public void setConnectTimeout(final int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
    
    public void setInstanceFollowRedirects(final boolean followRedirects) {
        this.followRedirects = followRedirects;
    }
    
    public void setParms(final String... parms) {
        assert (parms.length & 0x1) == 0x0 : "must have an even number of parms, keyword=value";
        this.parms = parms;
    }
    
    public void setReadTimeout(final int readTimeout) {
        this.readTimeout = readTimeout;
    }
    
    public void setRequestProperties(final String... requestProperties) {
        if ((requestProperties.length & 0x1) != 0x0) {
            throw new IllegalArgumentException("setRequestProperties needs an even number of parameters: key,value");
        }
        this.requestProperties = requestProperties;
    }
    
    public void setUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }
    
    protected static void dumpHeaders(final String title, final HttpURLConnection urlc) {
        System.out.println(title);
        final Map<String, List<String>> pairs = urlc.getHeaderFields();
        for (final Map.Entry<String, List<String>> entry : pairs.entrySet()) {
            final String key = entry.getKey();
            final List<String> values = entry.getValue();
            System.out.print(key + ":");
            for (final String value : values) {
                System.out.print(" [" + value + "]");
            }
            System.out.println();
        }
    }
    
    private static String encodeParms(final Charset encoding, final String... parms) throws UnsupportedEncodingException {
        if (parms == null || parms.length == 0) {
            return "";
        }
        assert (parms.length & 0x1) == 0x0 : "must have an even number of parms, keyword=value";
        int estLength = 10;
        for (final String p : parms) {
            estLength += p.length() + 1;
        }
        final StringBuilder sb = new StringBuilder(estLength);
        for (int i = 0; i < parms.length - 1; i += 2) {
            sb.append((i == 0) ? "?" : "&");
            sb.append(URLEncoder.encode(parms[i], encoding.name()));
            sb.append('=');
            sb.append(URLEncoder.encode(parms[i + 1], encoding.name()));
        }
        return sb.toString();
    }
    
    static Charset guessCharSet(final String contentType, final Charset defaultEncoding) {
        if (contentType == null) {
            return defaultEncoding;
        }
        final int place = contentType.lastIndexOf("charset=");
        if (place >= 0) {
            String charset = null;
            try {
                charset = contentType.substring(place + "charset=".length()).trim().toUpperCase();
                charset = ST.trimLeading(ST.trimTrailing(charset, '\"'), '\"');
                return Charset.forName(charset);
            }
            catch (IllegalCharsetNameException e) {
                System.err.println("Warning: unrecognised charset " + charset + " using " + defaultEncoding + " instead.");
                return defaultEncoding;
            }
        }
        return defaultEncoding;
    }
    
    private static void means(final int responseCode, final String meaning) {
        Http.responseCodeLookup[responseCode - 200] = meaning;
    }
    
    private static String responseCodeToResponseMessage(final int responseCode, final String rawResponseMessage) {
        if (200 <= responseCode && responseCode <= 505) {
            final String responseMessage = Http.responseCodeLookup[responseCode - 200];
            if (responseMessage != null) {
                return responseMessage;
            }
        }
        if (responseCode == -1) {
            return "no connect";
        }
        return rawResponseMessage;
    }
    
    Http() {
        this.requestProperties = new String[0];
        this.referer = null;
        this.userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0) Gecko/20100101 Firefox/4.0";
        this.followRedirects = true;
        this.connectTimeout = 50000;
        this.readTimeout = 40000;
    }
    
    String getEncodedParms(final Charset encoding) throws UnsupportedEncodingException {
        return encodeParms(encoding, this.parms);
    }
    
    String processResponse(final Charset defaultCharSet, final HttpURLConnection urlc) throws IOException {
        urlc.connect();
        this.responseCode = urlc.getResponseCode();
        this.responseMessage = urlc.getResponseMessage();
        int estimatedLength = urlc.getContentLength();
        if (estimatedLength < 0) {
            estimatedLength = 32768;
        }
        final InputStream is = urlc.getInputStream();
        final String contentType = urlc.getContentType();
        final Charset charSet = guessCharSet(contentType, defaultCharSet);
        final boolean gzipped = "gzip".equals(urlc.getContentEncoding()) || "x-gzip".equals(urlc.getContentEncoding());
        final String result = Read.readStringBlocking(is, estimatedLength, this.readTimeout, gzipped, charSet);
        is.close();
        urlc.disconnect();
        return result;
    }
    
    protected void setStandardProperties(final URLConnection urlc) {
        urlc.setConnectTimeout(this.connectTimeout);
        urlc.setReadTimeout(this.readTimeout);
        if (this.userAgent != null) {
            urlc.setRequestProperty("User-Agent", this.userAgent);
        }
        if (urlc instanceof HttpURLConnection) {
            ((HttpURLConnection)urlc).setInstanceFollowRedirects(this.followRedirects);
        }
        if (this.referer != null) {
            urlc.setRequestProperty("Referer", this.referer);
        }
        for (int i = 0; i < this.requestProperties.length; i += 2) {
            urlc.setRequestProperty(this.requestProperties[i], this.requestProperties[i + 1]);
        }
        urlc.setRequestProperty("Accept", "application/octet-stream,application/x-java-jnlp-file,application/x-java-serialized-object,application/xhtml+xml,application/xml,application/zip,image/gif,image/jpeg,image/png,text/css,text/html,text/plain,text/x-java-source,text/xml;q=0.9,*/*;q=0.8");
        urlc.setRequestProperty("Accept-Charset", "iso-8859-1,utf-8,utf-16;q=0.7,*;q=0.3");
        urlc.setRequestProperty("Accept-Encoding", "gzip,x-gzip,identity");
        final Locale locale = Locale.getDefault();
        urlc.setRequestProperty("Accept-Language", locale.toString() + "," + locale.getLanguage() + ";q=0.9");
    }
    
    static {
        UTF8Charset = Charset.forName("UTF-8");
        responseCodeLookup = new String[306];
        means(200, "ok");
        means(201, "created");
        means(202, "accepted");
        means(203, "non-authoritative information");
        means(204, "no content");
        means(205, "reset content");
        means(206, "partial content");
        means(300, "multiple choices");
        means(301, "object permanently moved");
        means(302, "object temporarily moved");
        means(303, "access method changed");
        means(304, "not modified since last access");
        means(305, "use proxy");
        means(400, "bad request");
        means(401, "unauthorized. must logon first.");
        means(402, "payment required");
        means(403, "forbidden");
        means(404, "page not found");
        means(405, "method not allowed");
        means(406, "not acceptable");
        means(407, "proxy authentication required");
        means(408, "request time-out");
        means(409, "conflict");
        means(410, "gone");
        means(411, "length required");
        means(412, "precondition failed");
        means(413, "request entity too large");
        means(414, "request-uri too large");
        means(415, "unsupported media type");
        means(500, "server error");
        means(500, "internal server error");
        means(501, "not implemented");
        means(502, "bad gateway");
        means(503, "service unavailable");
        means(504, "gateway timeout");
        means(505, "http version not supported");
    }
}
