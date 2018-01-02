// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;

final class DefaultErrorHandler implements ErrorHandler
{
    private static DefaultErrorHandler ERROR_HANDLER_INSTANCE;
    
    public static DefaultErrorHandler getInstance() {
        return DefaultErrorHandler.ERROR_HANDLER_INSTANCE;
    }
    
    public void warning(final SAXParseException ex) throws SAXException {
    }
    
    public void error(final SAXParseException ex) throws SAXException {
        throw ex;
    }
    
    public void fatalError(final SAXParseException ex) throws SAXException {
        throw ex;
    }
    
    static {
        DefaultErrorHandler.ERROR_HANDLER_INSTANCE = new DefaultErrorHandler();
    }
}
