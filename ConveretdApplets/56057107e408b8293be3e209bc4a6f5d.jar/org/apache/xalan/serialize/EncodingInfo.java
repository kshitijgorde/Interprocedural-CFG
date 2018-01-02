// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

public class EncodingInfo
{
    final String name;
    final String javaName;
    final int lastPrintable;
    
    public EncodingInfo(final String name, final String javaName, final int lastPrintable) {
        this.name = name;
        this.javaName = javaName;
        this.lastPrintable = lastPrintable;
    }
}
