// 
// Decompiled by Procyon v0.5.30
// 

package net.Slingshot.ClientAPI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.InputStream;
import java.net.URL;

class HttpGetConnection
{
    private URL url;
    private InputStream input;
    private URLConnection Conn;
    private URL servUrl;
    private long connSignature;
    private long connId;
    private long IncCnt;
    private String servLoc;
    private URL get;
    
    HttpGetConnection(final URL url) {
        this.setURL(url);
    }
    
    public void clearServerUrl() {
        this.servUrl = null;
        this.servLoc = null;
        this.connSignature = 0L;
        this.connId = 0L;
    }
    
    public void setSignatureConnId(final long connSignature, final long connId) {
        this.connSignature = connSignature;
        this.connId = connId;
    }
    
    public URL getServerURL() {
        if (this.servLoc != null) {
            ++this.IncCnt;
            try {
                this.servUrl = new URL(String.valueOf(this.servLoc) + "/CSK" + this.IncCnt + "/SlingShot2000/" + this.connSignature + "/" + this.connId);
            }
            catch (MalformedURLException ex) {}
        }
        return this.servUrl;
    }
    
    public void setURL(final URL url) {
        int port = 80;
        if (url.getPort() != -1) {
            port = url.getPort();
        }
        try {
            this.url = new URL(url.getProtocol(), url.getHost(), port, ("/CSK" + System.currentTimeMillis() + "/SlingShot2000/index.html").toString());
        }
        catch (MalformedURLException ex) {}
    }
    
    public void close() throws IOException {
        if (this.input != null) {
            this.input.close();
        }
        this.input = null;
        this.Conn = null;
    }
    
    public void shutdown() throws IOException {
        this.close();
        this.url = null;
        this.servUrl = null;
        this.get = null;
    }
    
    public InputStream getInputStream() throws IOException, FileNotFoundException {
        if (this.Conn == null) {
            if (this.url == null) {
                throw new IOException("URL not set");
            }
            if (this.servLoc != null) {
                ++this.IncCnt;
                try {
                    this.servUrl = new URL(String.valueOf(this.servLoc) + "/CSK" + this.IncCnt + "/SlingShot2000/" + this.connSignature + "/" + this.connId);
                }
                catch (MalformedURLException ex3) {}
            }
            if (this.servUrl != null) {
                this.get = this.servUrl;
            }
            else {
                this.get = this.url;
            }
            try {
                this.Conn = this.get.openConnection();
            }
            catch (IOException ex) {
                throw new IOException("GET connection Failed to host(" + this.get + ") " + ex);
            }
            this.Conn.setAllowUserInteraction(true);
            this.Conn.setUseCaches(false);
            this.Conn.setRequestProperty("Accept", "text/html");
            this.Conn.setDoInput(true);
        }
        if (this.input == null) {
            try {
                this.input = this.Conn.getInputStream();
            }
            catch (IOException ex2) {
                final URLConnection conn = this.Conn;
                this.close();
                throw new IOException("GET connection Failed to URL(" + conn + ") " + ex2);
            }
        }
        if (this.Conn != null) {
            int port = 80;
            if (this.Conn.getURL().getPort() != -1) {
                port = this.Conn.getURL().getPort();
            }
            this.servLoc = String.valueOf(this.url.getProtocol()) + "://" + this.Conn.getURL().getHost() + ":" + port;
        }
        return this.input;
    }
}
