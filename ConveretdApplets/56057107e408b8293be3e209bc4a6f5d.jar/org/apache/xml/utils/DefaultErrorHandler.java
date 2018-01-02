// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import javax.xml.transform.SourceLocator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.ErrorListener;
import org.xml.sax.ErrorHandler;

public class DefaultErrorHandler implements ErrorHandler, ErrorListener
{
    public void error(final TransformerException exception) throws TransformerException {
        this.printLocation(exception);
        throw exception;
    }
    
    public void error(final SAXParseException exception) throws SAXException {
        this.printLocation(exception);
        throw exception;
    }
    
    public void fatalError(final TransformerException exception) throws TransformerException {
        this.printLocation(exception);
        throw exception;
    }
    
    public void fatalError(final SAXParseException exception) throws SAXException {
        this.printLocation(exception);
        throw exception;
    }
    
    private void printLocation(final TransformerException exception) {
        final SourceLocator locator = exception.getLocator();
        if (locator != null) {
            final String id = (locator.getPublicId() != locator.getPublicId()) ? locator.getPublicId() : ((locator.getSystemId() != null) ? locator.getSystemId() : "SystemId Unknown");
            System.out.print(String.valueOf(id) + "; Line " + locator.getLineNumber() + "; Column " + locator.getColumnNumber() + "; ");
        }
    }
    
    private void printLocation(final SAXParseException exception) {
        final String id = (exception.getSystemId() != null) ? exception.getSystemId() : "SystemId Unknown";
        System.out.print(String.valueOf(id) + "; Line " + exception.getLineNumber() + "; Column " + exception.getColumnNumber() + "; ");
    }
    
    public void warning(final TransformerException exception) throws TransformerException {
        this.printLocation(exception);
        System.out.println(exception.getMessage());
    }
    
    public void warning(final SAXParseException exception) throws SAXException {
        this.printLocation(exception);
        System.out.println("Parser warning: " + exception.getMessage());
    }
}
