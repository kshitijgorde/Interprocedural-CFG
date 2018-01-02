// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.domx;

public abstract class DOMException extends org.w3c.dom.DOMException
{
    public static final short INVALID_STATE_ERR = 11;
    public static final short UNSPECIFIED_EVENT_TYPE = 100;
    public static final short UNSUPPORTED_EVENT_TYPE = 101;
    
    public DOMException(final short n, final String s) {
        super(n, s);
    }
}
