// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;

public class J2KViewerXmlException extends RuntimeException implements ErrorHandler
{
    public J2KViewerXmlException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public J2KViewerXmlException() {
    }
    
    public void warning(final SAXParseException e) throws SAXException {
        throw new SAXException("Unable to parse XML", e);
    }
    
    public void error(final SAXParseException e) throws SAXException {
        throw new SAXException("Unable to parse XML", e);
    }
    
    public void fatalError(final SAXParseException e) throws SAXException {
        throw new SAXException("Unable to parse XML", e);
    }
}
