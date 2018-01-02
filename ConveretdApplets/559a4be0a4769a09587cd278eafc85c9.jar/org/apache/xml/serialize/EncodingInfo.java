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
    
    public EncodingInfo(final String mimeName, final String javaName, final int lastPrintable) {
        this.name = mimeName;
        this.javaName = ((javaName == null) ? mimeName : javaName);
        this.lastPrintable = lastPrintable;
    }
    
    public EncodingInfo(final String mimeName, final int lastPrintable) {
        this(mimeName, mimeName, lastPrintable);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Writer getWriter(final OutputStream output) throws UnsupportedEncodingException {
        if (this.javaName == null) {
            return new OutputStreamWriter(output);
        }
        return new OutputStreamWriter(output, this.javaName);
    }
    
    public boolean isPrintable(final int ch) {
        return ch <= this.lastPrintable;
    }
}
