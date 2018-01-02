// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol.file;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.security.Permission;
import java.io.FilePermission;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URL;
import java.io.File;
import java.net.URLConnection;

public class FileURLConnection extends URLConnection
{
    static boolean decodeFilePaths;
    protected File file;
    
    public FileURLConnection(final URL url) throws MalformedURLException, IOException {
        super(url);
        String path = url.getPath();
        if (FileURLConnection.decodeFilePaths) {
            path = URLDecoder.decode(path, "UTF-8");
        }
        this.file = new File(path.replace('/', File.separatorChar).replace('|', ':'));
        this.doOutput = false;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void connect() throws IOException {
        if (this.connected) {
            return;
        }
        if (!this.file.exists()) {
            throw new FileNotFoundException(this.file.getPath());
        }
        this.connected = true;
    }
    
    public InputStream getInputStream() throws IOException {
        if (!this.connected) {
            this.connect();
        }
        return new FileInputStream(this.file);
    }
    
    public OutputStream getOutputStream() throws IOException {
        if (!this.connected) {
            this.connect();
        }
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            final FilePermission p = new FilePermission(this.file.getPath(), "write");
            sm.checkPermission(p);
        }
        return new FileOutputStream(this.file);
    }
    
    public String getHeaderField(final String name) {
        String headerField = null;
        if (name.equalsIgnoreCase("last-modified")) {
            headerField = String.valueOf(this.getLastModified());
        }
        else if (name.equalsIgnoreCase("content-length")) {
            headerField = String.valueOf(this.file.length());
        }
        else if (name.equalsIgnoreCase("content-type")) {
            headerField = URLConnection.getFileNameMap().getContentTypeFor(this.file.getName());
            if (headerField == null) {
                try {
                    final InputStream is = this.getInputStream();
                    final BufferedInputStream bis = new BufferedInputStream(is);
                    headerField = URLConnection.guessContentTypeFromStream(bis);
                    bis.close();
                }
                catch (IOException e) {}
            }
        }
        else if (name.equalsIgnoreCase("date")) {
            headerField = String.valueOf(this.file.lastModified());
        }
        else {
            headerField = super.getHeaderField(name);
        }
        return headerField;
    }
    
    public Permission getPermission() throws IOException {
        return new FilePermission(this.file.getPath(), "read");
    }
    
    public long getLastModified() {
        return this.file.lastModified();
    }
    
    static {
        FileURLConnection.decodeFilePaths = true;
        final String flag = System.getProperty("org.jboss.net.protocol.file.decodeFilePaths");
        if (flag != null) {
            FileURLConnection.decodeFilePaths = Boolean.valueOf(flag);
        }
    }
}
