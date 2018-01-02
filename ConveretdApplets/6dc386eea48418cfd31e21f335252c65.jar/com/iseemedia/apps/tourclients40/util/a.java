// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.util;

import java.net.URLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;

public final class a
{
    private URL a;
    
    public a(final String s) throws MalformedURLException {
        this.a = new URL(s);
    }
    
    public final DataInputStream a(final String s) throws IOException {
        try {
            return this.b(s);
        }
        catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    public final URL a() {
        return this.a;
    }
    
    private DataInputStream b(final String s) throws IOException {
        final StringBuffer sb;
        (sb = new StringBuffer()).append(this.a());
        if (!sb.toString().endsWith("/") && !s.startsWith("/")) {
            sb.append("/?");
        }
        sb.append(s);
        final URL url = new URL(sb.toString());
        try {
            final URLConnection openConnection;
            if ((openConnection = url.openConnection()) == null) {
                return null;
            }
            openConnection.setUseCaches(false);
            final InputStream inputStream;
            if ((inputStream = openConnection.getInputStream()) != null) {
                return new DataInputStream(new BufferedInputStream(inputStream, 4096));
            }
            return null;
        }
        catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
        catch (Exception ex2) {
            return null;
        }
    }
}
