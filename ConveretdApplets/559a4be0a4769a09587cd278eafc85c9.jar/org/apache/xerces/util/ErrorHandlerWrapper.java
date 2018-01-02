// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.apache.xerces.xni.parser.XMLParseException;
import org.xml.sax.ErrorHandler;
import org.apache.xerces.xni.parser.XMLErrorHandler;

public class ErrorHandlerWrapper implements XMLErrorHandler
{
    protected ErrorHandler fErrorHandler;
    
    public ErrorHandlerWrapper() {
    }
    
    public ErrorHandlerWrapper(final ErrorHandler errorHandler) {
        this.setErrorHandler(errorHandler);
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.fErrorHandler = errorHandler;
    }
    
    public ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }
    
    public void warning(final String domain, final String key, final XMLParseException exception) throws XNIException {
        final SAXParseException saxException = createSAXParseException(exception);
        try {
            this.fErrorHandler.warning(saxException);
        }
        catch (SAXParseException e) {
            throw createXMLParseException(e);
        }
        catch (SAXException e2) {
            throw createXNIException(e2);
        }
    }
    
    public void error(final String domain, final String key, final XMLParseException exception) throws XNIException {
        final SAXParseException saxException = createSAXParseException(exception);
        try {
            this.fErrorHandler.error(saxException);
        }
        catch (SAXParseException e) {
            throw createXMLParseException(e);
        }
        catch (SAXException e2) {
            throw createXNIException(e2);
        }
    }
    
    public void fatalError(final String domain, final String key, final XMLParseException exception) throws XNIException {
        final SAXParseException saxException = createSAXParseException(exception);
        try {
            this.fErrorHandler.fatalError(saxException);
        }
        catch (SAXParseException e) {
            throw createXMLParseException(e);
        }
        catch (SAXException e2) {
            throw createXNIException(e2);
        }
    }
    
    protected static SAXParseException createSAXParseException(final XMLParseException exception) {
        return new SAXParseException(exception.getMessage(), exception.getPublicId(), exception.getExpandedSystemId(), exception.getLineNumber(), exception.getColumnNumber(), exception.getException());
    }
    
    protected static XMLParseException createXMLParseException(final SAXParseException exception) {
        final String fPublicId = exception.getPublicId();
        final String fExpandedSystemId = exception.getSystemId();
        final int fLineNumber = exception.getLineNumber();
        final int fColumnNumber = exception.getColumnNumber();
        final XMLLocator location = new XMLLocator() {
            public String getPublicId() {
                return fPublicId;
            }
            
            public String getExpandedSystemId() {
                return fExpandedSystemId;
            }
            
            public String getBaseSystemId() {
                return null;
            }
            
            public String getLiteralSystemId() {
                return null;
            }
            
            public int getColumnNumber() {
                return fColumnNumber;
            }
            
            public int getLineNumber() {
                return fLineNumber;
            }
        };
        return new XMLParseException(location, exception.getMessage(), exception.getException());
    }
    
    protected static XNIException createXNIException(final SAXException exception) {
        return new XNIException(exception.getMessage(), exception.getException());
    }
}
