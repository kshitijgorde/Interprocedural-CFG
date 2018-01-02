// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.net;

import java.util.Date;
import java.net.ProtocolException;
import java.net.HttpURLConnection;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URL;

public class ServerConnection
{
    protected URL servletURL;
    protected URLConnection connection;
    private static final String[] validProtocols;
    protected OutputStream outputToServlet;
    protected InputStream inputFromServlet;
    protected static final String CONTENT_TYPE_HEADER = "Content-Type";
    protected String contentType;
    public static final String OCTET_STREAM_CONTENT_TYPE = "application/octet-stream";
    public static final String FORM_URLENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String JAVA_SERIALIZED_OBJECT_CONTENT_TYPE = "application/x-java-serialized-object";
    public static final String TEXT_PLAIN_CONTENT_TYPE = "text/plain";
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    protected boolean addFollowTx;
    protected static final String HEAD_HTTP_METHOD = "HEAD";
    
    public ServerConnection(final String s, final String s2) throws ServerCommunicationException {
        this(stringToURL(s), s2);
    }
    
    public ServerConnection(final String s) throws ServerCommunicationException {
        this(s, "application/octet-stream");
    }
    
    public ServerConnection(final URL servletURL, final String contentType) throws ServerCommunicationException {
        this.setServletURL(servletURL);
        this.setContentType(contentType);
        this.addFollowTx = FollowTxHeader.isAddFollowTx();
    }
    
    public ServerConnection(final URL url) throws ServerCommunicationException {
        this(url, "application/octet-stream");
    }
    
    public URLConnection getOrCreateConnection() throws IOException {
        if (this.getConnection() == null) {
            this.createConnection();
        }
        return this.getConnection();
    }
    
    public URLConnection getConnection() {
        return this.connection;
    }
    
    protected static URL stringToURL(final String s) throws ServerCommunicationException {
        try {
            final URL url = new URL(s);
            if (!isUrlProtocolValid(url)) {
                throw new ServerCommunicationException("URL " + s + " uses an unsupported protocol");
            }
            return url;
        }
        catch (MalformedURLException ex) {
            throw new ServerCommunicationException(ex.getMessage());
        }
    }
    
    protected static boolean isUrlProtocolValid(final URL url) {
        final String protocol = url.getProtocol();
        for (int i = 0; i < ServerConnection.validProtocols.length; ++i) {
            if (ServerConnection.validProtocols[i].equalsIgnoreCase(protocol)) {
                return true;
            }
        }
        return false;
    }
    
    protected void setServletURL(final URL servletURL) throws ServerCommunicationException {
        if (servletURL == null) {
            throw new ServerCommunicationException("servletURL can't be null");
        }
        this.servletURL = servletURL;
    }
    
    protected URLConnection createConnection() throws IOException {
        this.connection = this.servletURL.openConnection();
        this.setGenericConnectionProperties();
        this.setHeaders();
        return this.connection;
    }
    
    protected void setGenericConnectionProperties() throws IOException {
        final URLConnection orCreateConnection = this.getOrCreateConnection();
        orCreateConnection.setDoInput(true);
        orCreateConnection.setDoOutput(true);
        orCreateConnection.setUseCaches(false);
        orCreateConnection.setDefaultUseCaches(false);
    }
    
    protected void setHeaders() throws IOException {
        final URLConnection orCreateConnection = this.getOrCreateConnection();
        orCreateConnection.setRequestProperty("Content-Type", this.contentType);
        if (this.addFollowTx) {
            orCreateConnection.setRequestProperty("FollowTX", new FollowTxHeader().toString());
        }
    }
    
    public synchronized InputStream getInputStream() throws IOException {
        final URLConnection orCreateConnection = this.getOrCreateConnection();
        if (this.inputFromServlet == null) {
            this.inputFromServlet = new BufferedInputStream(orCreateConnection.getInputStream());
        }
        return this.inputFromServlet;
    }
    
    public synchronized OutputStream getOutputStream() throws IOException {
        final URLConnection orCreateConnection = this.getOrCreateConnection();
        if (this.outputToServlet == null) {
            this.outputToServlet = new BufferedOutputStream(orCreateConnection.getOutputStream());
        }
        return this.outputToServlet;
    }
    
    public synchronized void cleanUp() {
        if (this.outputToServlet != null) {
            try {
                this.outputToServlet.flush();
            }
            catch (IOException ex) {}
            try {
                this.outputToServlet.close();
            }
            catch (IOException ex2) {}
            finally {
                this.outputToServlet = null;
            }
        }
        if (this.inputFromServlet != null) {
            try {
                this.inputFromServlet.close();
            }
            catch (IOException ex3) {}
            finally {
                this.inputFromServlet = null;
            }
        }
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
    
    public boolean isAddFollowTx() {
        return this.addFollowTx;
    }
    
    public void setAddFollowTx(final boolean addFollowTx) {
        this.addFollowTx = addFollowTx;
    }
    
    protected synchronized String getHeader(final String s) throws IOException {
        URLConnection urlConnection = this.getConnection();
        if (urlConnection == null) {
            urlConnection = this.createConnection();
            this.useHeadHttpMethodIfAvailable(urlConnection);
        }
        return urlConnection.getHeaderField(s);
    }
    
    protected void useHeadHttpMethodIfAvailable(final URLConnection urlConnection) throws ProtocolException {
        if (urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection)urlConnection).setRequestMethod("HEAD");
        }
    }
    
    public String getResponseHeader(final String s) {
        try {
            return this.getHeader(s);
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public int getIntResponseHeader(final String s, final int n) {
        try {
            return Integer.parseInt(this.getHeader(s));
        }
        catch (IOException ex) {
            return n;
        }
    }
    
    public Date getDateResponseHeader(final String s, final Date date) {
        URLConnection urlConnection = this.getConnection();
        if (urlConnection == null) {
            try {
                urlConnection = this.createConnection();
                this.useHeadHttpMethodIfAvailable(urlConnection);
            }
            catch (IOException ex) {
                return date;
            }
        }
        return new Date(urlConnection.getHeaderFieldDate(s, date.getTime()));
    }
    
    public Date getLastModified() {
        return this.getDateResponseHeader("Last-Modified", new Date());
    }
    
    static {
        validProtocols = new String[] { "http", "https" };
    }
}
