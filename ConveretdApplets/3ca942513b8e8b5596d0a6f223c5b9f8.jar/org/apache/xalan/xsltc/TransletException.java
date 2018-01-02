// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc;

import org.xml.sax.SAXException;

public final class TransletException extends SAXException
{
    static final long serialVersionUID = -878916829521217293L;
    
    public TransletException() {
        super("Translet error");
    }
    
    public TransletException(final Exception e) {
        super(e.toString());
    }
    
    public TransletException(final String message) {
        super(message);
    }
}
