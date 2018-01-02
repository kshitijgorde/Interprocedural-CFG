// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.io.IOException;
import java.io.FileInputStream;
import java.net.URL;
import java.io.InputStream;
import java.net.URLConnection;

class p extends URLConnection
{
    boolean connected;
    InputStream a;
    
    protected p(final URL url) {
        super(url);
        this.connected = false;
    }
    
    public void connect() throws IOException {
        if (!this.connected) {
            String s = this.url.getFile();
            if (s.charAt(1) == '|') {
                final StringBuffer sb = new StringBuffer(s);
                sb.setCharAt(1, ':');
                s = sb.toString();
            }
            else if (s.charAt(2) == '|') {
                final StringBuffer sb2 = new StringBuffer(s);
                sb2.setCharAt(2, ':');
                s = sb2.toString();
            }
            this.a = new FileInputStream(s);
        }
    }
    
    public InputStream getInputStream() throws IOException {
        if (!this.connected) {
            this.connect();
        }
        if (this.a == null) {
            throw new IOException();
        }
        return this.a;
    }
}
