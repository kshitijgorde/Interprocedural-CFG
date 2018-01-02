// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xpath;

public class XPathException extends Exception
{
    private String fKey;
    
    public XPathException() {
        this.fKey = "c-general-xpath";
    }
    
    public XPathException(final String key) {
        this.fKey = key;
    }
    
    public String getKey() {
        return this.fKey;
    }
}
