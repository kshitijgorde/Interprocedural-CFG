// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.payload;

import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import com.postx.util.FileMap;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AppletTOCEntry extends TOCEntry
{
    public static final String Ident = "$Id: AppletTOCEntry.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private URL url;
    private InputStream stream;
    
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.stream;
        this.stream = null;
        if (inputStream == null) {
            inputStream = this.url.openStream();
        }
        return inputStream;
    }
    
    public String toString() {
        return "<url:" + this.url + ", loc:[0, " + this.getLength() + "]>";
    }
    
    public AppletTOCEntry(final FileMap fileMap, final String s, final String s2) throws IOException {
        final String string = s + s2;
        final URL resource = this.getClass().getResource(string);
        this.url = resource;
        if (resource == null) {
            throw new FileNotFoundException(string + " not found in applet");
        }
        this.stream = this.url.openStream();
        this.setLength(this.stream.available());
        fileMap.put(s2, 2, this);
    }
    
    public OutputStream getOutputStream() throws IOException {
        throw new IllegalStateException("can't write to a applet entry");
    }
    
    public Reader getReader() throws IOException, UnsupportedEncodingException {
        throw new IllegalStateException("can't use a Reader to read a applet entry");
    }
}
