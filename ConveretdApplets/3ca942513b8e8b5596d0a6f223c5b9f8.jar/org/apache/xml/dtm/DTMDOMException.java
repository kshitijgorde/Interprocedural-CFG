// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm;

import org.w3c.dom.DOMException;

public class DTMDOMException extends DOMException
{
    static final long serialVersionUID = 1895654266613192414L;
    
    public DTMDOMException(final short code, final String message) {
        super(code, message);
    }
    
    public DTMDOMException(final short code) {
        super(code, "");
    }
}
