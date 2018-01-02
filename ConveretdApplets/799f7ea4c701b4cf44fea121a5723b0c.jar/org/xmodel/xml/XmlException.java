// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xml;

public class XmlException extends Exception
{
    public XmlException(final String s) {
        super(s);
    }
    
    public XmlException(final String s, final Throwable t) {
        super(s);
        this.initCause(t);
    }
}
