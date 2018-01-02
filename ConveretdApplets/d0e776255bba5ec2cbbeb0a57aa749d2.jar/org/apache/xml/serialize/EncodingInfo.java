// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import java.io.UnsupportedEncodingException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.OutputStream;

public class EncodingInfo
{
    String name;
    String javaName;
    int lastPrintable;
    
    public EncodingInfo(final String name, final String s, final int lastPrintable) {
        this.name = name;
        this.javaName = ((s == null) ? name : s);
        this.lastPrintable = lastPrintable;
    }
    
    public EncodingInfo(final String s, final int n) {
        this(s, s, n);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Writer getWriter(final OutputStream outputStream) throws UnsupportedEncodingException {
        if (this.javaName == null) {
            return new OutputStreamWriter(outputStream);
        }
        return new OutputStreamWriter(outputStream, this.javaName);
    }
    
    public boolean isPrintable(final int n) {
        return n <= this.lastPrintable;
    }
}
