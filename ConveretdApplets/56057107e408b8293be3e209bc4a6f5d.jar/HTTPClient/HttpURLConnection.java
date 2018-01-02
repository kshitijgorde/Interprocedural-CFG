// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.URLConnection;
import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.io.IOException;
import java.net.URL;
import java.io.OutputStream;

public class HttpURLConnection extends java.net.HttpURLConnection implements GlobalConstants
{
    private static CIHashtable connections;
    private HTTPConnection con;
    private String resource;
    private String method;
    private boolean method_set;
    private static NVPair[] default_headers;
    private NVPair[] headers;
    private HTTPResponse resp;
    private boolean do_redir;
    private static Class redir_mod;
    private OutputStream output_stream;
    private static boolean in_hotjava;
    private static String non_proxy_hosts;
    private static String proxy_host;
    private static int proxy_port;
    private String[] hdr_keys;
    private String[] hdr_values;
    
    public HttpURLConnection(final URL url) throws ProtocolNotSuppException, IOException {
        super(url);
        try {
            final String property = System.getProperty("http.nonProxyHosts", "");
            if (!property.equalsIgnoreCase(HttpURLConnection.non_proxy_hosts)) {
                HttpURLConnection.connections.clear();
                HttpURLConnection.non_proxy_hosts = property;
                final String[] splitProperty = Util.splitProperty(property);
                for (int i = 0; i < splitProperty.length; ++i) {
                    HTTPConnection.dontProxyFor(splitProperty[i]);
                }
            }
        }
        catch (ParseException ex) {
            throw new IOException(ex.toString());
        }
        catch (SecurityException ex2) {}
        try {
            final String property2 = System.getProperty("http.proxyHost", "");
            final int intValue = Integer.getInteger("http.proxyPort", -1);
            if (!property2.equalsIgnoreCase(HttpURLConnection.proxy_host) || intValue != HttpURLConnection.proxy_port) {
                HttpURLConnection.connections.clear();
                HTTPConnection.setProxyServer(HttpURLConnection.proxy_host = property2, HttpURLConnection.proxy_port = intValue);
            }
        }
        catch (SecurityException ex3) {}
        this.con = this.getConnection(url);
        this.method = "GET";
        this.method_set = false;
        this.resource = url.getFile();
        this.headers = HttpURLConnection.default_headers;
        this.do_redir = java.net.HttpURLConnection.getFollowRedirects();
        this.output_stream = null;
    }
    
    private HTTPConnection getConnection(final URL url) throws ProtocolNotSuppException {
        final String string = url.getProtocol() + ":" + url.getHost() + ":" + ((url.getPort() != -1) ? url.getPort() : URI.defaultPort(url.getProtocol()));
        final HTTPConnection httpConnection = (HTTPConnection)HttpURLConnection.connections.get(string);
        if (httpConnection != null) {
            return httpConnection;
        }
        final HTTPConnection httpConnection2 = new HTTPConnection(url);
        HttpURLConnection.connections.put(string, httpConnection2);
        return httpConnection2;
    }
    
    public void setRequestMethod(final String s) throws ProtocolException {
        if (super.connected) {
            throw new ProtocolException("Already connected!");
        }
        if (GlobalConstants.DebugURLC) {
            Util.logLine("URLC:  (" + super.url + ") Setting request method: " + s);
        }
        this.method = s.trim().toUpperCase();
        this.method_set = true;
    }
    
    public String getRequestMethod() {
        return this.method;
    }
    
    public int getResponseCode() throws IOException {
        if (!super.connected) {
            this.connect();
        }
        try {
            if (HttpURLConnection.in_hotjava && this.resp.getStatusCode() >= 300) {
                try {
                    this.resp.getData();
                }
                catch (InterruptedIOException ex2) {
                    this.disconnect();
                }
            }
            return this.resp.getStatusCode();
        }
        catch (ModuleException ex) {
            throw new IOException(ex.toString());
        }
    }
    
    public String getResponseMessage() throws IOException {
        if (!super.connected) {
            this.connect();
        }
        try {
            return this.resp.getReasonLine();
        }
        catch (ModuleException ex) {
            throw new IOException(ex.toString());
        }
    }
    
    public String getHeaderField(final String s) {
        try {
            if (!super.connected) {
                this.connect();
            }
            return this.resp.getHeader(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public int getHeaderFieldInt(final String s, final int n) {
        try {
            if (!super.connected) {
                this.connect();
            }
            return this.resp.getHeaderAsInt(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public long getHeaderFieldDate(final String s, final long n) {
        try {
            if (!super.connected) {
                this.connect();
            }
            return this.resp.getHeaderAsDate(s).getTime();
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public String getHeaderFieldKey(final int n) {
        if (this.hdr_keys == null) {
            this.fill_hdr_arrays();
        }
        if (n >= 0 && n < this.hdr_keys.length) {
            return this.hdr_keys[n];
        }
        return null;
    }
    
    public String getHeaderField(final int n) {
        if (this.hdr_values == null) {
            this.fill_hdr_arrays();
        }
        if (n >= 0 && n < this.hdr_values.length) {
            return this.hdr_values[n];
        }
        return null;
    }
    
    private void fill_hdr_arrays() {
        try {
            if (!super.connected) {
                this.connect();
            }
            int n = 1;
            final Enumeration listHeaders = this.resp.listHeaders();
            while (listHeaders.hasMoreElements()) {
                ++n;
                listHeaders.nextElement();
            }
            this.hdr_keys = new String[n];
            this.hdr_values = new String[n];
            final Enumeration listHeaders2 = this.resp.listHeaders();
            for (int i = 1; i < n; ++i) {
                this.hdr_keys[i] = listHeaders2.nextElement();
                this.hdr_values[i] = this.resp.getHeader(this.hdr_keys[i]);
            }
            this.hdr_values[0] = this.resp.getVersion() + " " + this.resp.getStatusCode() + " " + this.resp.getReasonLine();
        }
        catch (Exception ex) {
            final String[] array = new String[0];
            this.hdr_values = array;
            this.hdr_keys = array;
        }
    }
    
    public InputStream getInputStream() throws IOException {
        if (!super.doInput) {
            throw new ProtocolException("Input not enabled! (use setDoInput(true))");
        }
        if (!super.connected) {
            this.connect();
        }
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(this.resp.getInputStream());
        }
        catch (ModuleException ex) {
            throw new IOException(ex.toString());
        }
        return bufferedInputStream;
    }
    
    public InputStream getErrorStream() {
        try {
            if (!super.doInput || !super.connected || this.resp.getStatusCode() < 300 || this.resp.getHeaderAsInt("Content-length") <= 0) {
                return null;
            }
            return this.resp.getInputStream();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public synchronized OutputStream getOutputStream() throws IOException {
        if (super.connected) {
            throw new ProtocolException("Already connected!");
        }
        if (!super.doOutput) {
            throw new ProtocolException("Output not enabled! (use setDoOutput(true))");
        }
        if (!this.method_set) {
            this.method = "POST";
        }
        else if (this.method.equals("HEAD") || this.method.equals("GET") || this.method.equals("TRACE")) {
            throw new ProtocolException("Method " + this.method + " does not support output!");
        }
        if (this.getRequestProperty("Content-type") == null) {
            this.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        }
        if (this.output_stream == null) {
            if (GlobalConstants.DebugURLC) {
                Util.logLine("URLC:  (" + super.url + ") creating output stream");
            }
            if (this.getRequestProperty("Content-type").equals("application/x-www-form-urlencoded")) {
                this.output_stream = new ByteArrayOutputStream(300);
            }
            else {
                this.output_stream = new HttpOutputStream();
                this.connect();
            }
        }
        return this.output_stream;
    }
    
    public URL getURL() {
        if (super.connected) {
            try {
                if (this.resp.getEffectiveURL() != this.resp.getOriginalURL()) {
                    return this.resp.getEffectiveURL();
                }
            }
            catch (Exception ex) {
                return null;
            }
        }
        return super.url;
    }
    
    public void setIfModifiedSince(final long ifModifiedSince) {
        super.setIfModifiedSince(ifModifiedSince);
        this.setRequestProperty("If-Modified-Since", Util.httpDate(new Date(ifModifiedSince)));
    }
    
    public void setRequestProperty(final String s, final String s2) {
        if (GlobalConstants.DebugURLC) {
            Util.logLine("URLC:  (" + super.url + ") Setting request property: " + s + " : " + s2);
        }
        int n;
        for (n = 0; n < this.headers.length && !this.headers[n].getName().equalsIgnoreCase(s); ++n) {}
        if (n == this.headers.length) {
            this.headers = Util.resizeArray(this.headers, n + 1);
        }
        this.headers[n] = new NVPair(s, s2);
    }
    
    public String getRequestProperty(final String s) {
        for (int i = 0; i < this.headers.length; ++i) {
            if (this.headers[i].getName().equalsIgnoreCase(s)) {
                return this.headers[i].getValue();
            }
        }
        return null;
    }
    
    public static void setDefaultRequestProperty(final String s, final String s2) {
        if (GlobalConstants.DebugURLC) {
            Util.logLine("URLC:  Setting default request property: " + s + " : " + s2);
        }
        int n;
        for (n = 0; n < HttpURLConnection.default_headers.length && !HttpURLConnection.default_headers[n].getName().equalsIgnoreCase(s); ++n) {}
        if (n == HttpURLConnection.default_headers.length) {
            HttpURLConnection.default_headers = Util.resizeArray(HttpURLConnection.default_headers, n + 1);
        }
        HttpURLConnection.default_headers[n] = new NVPair(s, s2);
    }
    
    public static String getDefaultRequestProperty(final String s) {
        for (int i = 0; i < HttpURLConnection.default_headers.length; ++i) {
            if (HttpURLConnection.default_headers[i].getName().equalsIgnoreCase(s)) {
                return HttpURLConnection.default_headers[i].getValue();
            }
        }
        return null;
    }
    
    public void setInstanceFollowRedirects(final boolean do_redir) {
        if (super.connected) {
            throw new IllegalStateException("Already connected!");
        }
        this.do_redir = do_redir;
    }
    
    public boolean getInstanceFollowRedirects() {
        return this.do_redir;
    }
    
    public synchronized void connect() throws IOException {
        if (super.connected) {
            return;
        }
        if (GlobalConstants.DebugURLC) {
            Util.logLine("URLC:  (" + super.url + ") Connecting ...");
        }
        synchronized (this.con) {
            this.con.setAllowUserInteraction(super.allowUserInteraction);
            if (this.do_redir) {
                this.con.addModule(HttpURLConnection.redir_mod, 2);
            }
            else {
                this.con.removeModule(HttpURLConnection.redir_mod);
            }
            try {
                if (this.output_stream instanceof ByteArrayOutputStream) {
                    this.resp = this.con.ExtensionMethod(this.method, this.resource, ((ByteArrayOutputStream)this.output_stream).toByteArray(), this.headers);
                }
                else {
                    this.resp = this.con.ExtensionMethod(this.method, this.resource, (HttpOutputStream)this.output_stream, this.headers);
                }
            }
            catch (ModuleException ex) {
                throw new IOException(ex.toString());
            }
        }
        super.connected = true;
    }
    
    public void disconnect() {
        if (GlobalConstants.DebugURLC) {
            Util.logLine("URLC:  (" + super.url + ") Disconnecting ...");
        }
        this.con.stop();
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
    }
    
    public boolean usingProxy() {
        return this.con.getProxyHost() != null;
    }
    
    public String toString() {
        return this.getClass().getName() + "[" + super.url + "]";
    }
    
    static {
        HttpURLConnection.connections = new CIHashtable();
        HttpURLConnection.default_headers = new NVPair[0];
        HttpURLConnection.in_hotjava = false;
        try {
            final String property = System.getProperty("browser");
            if (property != null && property.equals("HotJava")) {
                HttpURLConnection.in_hotjava = true;
            }
        }
        catch (SecurityException ex2) {}
        try {
            if (Boolean.getBoolean("HTTPClient.HttpURLConnection.AllowUI")) {
                URLConnection.setDefaultAllowUserInteraction(true);
            }
        }
        catch (SecurityException ex3) {}
        try {
            HttpURLConnection.redir_mod = Class.forName("HTTPClient.RedirectionModule");
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
        try {
            final String property2 = System.getProperty("http.agent");
            if (property2 != null) {
                setDefaultRequestProperty("User-Agent", property2);
            }
        }
        catch (SecurityException ex4) {}
        HttpURLConnection.non_proxy_hosts = "";
        HttpURLConnection.proxy_host = "";
        HttpURLConnection.proxy_port = -1;
    }
}
