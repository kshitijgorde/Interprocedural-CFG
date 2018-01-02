// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.apache.xml.res.XMLMessages;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.xml.transform.ErrorListener;
import org.xml.sax.ErrorHandler;

public class DefaultErrorHandler implements ErrorHandler, ErrorListener
{
    PrintWriter m_pw;
    
    public DefaultErrorHandler(final PrintWriter pw) {
        this.m_pw = pw;
    }
    
    public DefaultErrorHandler(final PrintStream pw) {
        this.m_pw = new PrintWriter(pw, true);
    }
    
    public DefaultErrorHandler() {
        this.m_pw = new PrintWriter(System.err, true);
    }
    
    public void warning(final SAXParseException exception) throws SAXException {
        printLocation(this.m_pw, exception);
        this.m_pw.println("Parser warning: " + exception.getMessage());
    }
    
    public void error(final SAXParseException exception) throws SAXException {
        throw exception;
    }
    
    public void fatalError(final SAXParseException exception) throws SAXException {
        throw exception;
    }
    
    public void warning(final TransformerException exception) throws TransformerException {
        printLocation(this.m_pw, exception);
        this.m_pw.println(exception.getMessage());
    }
    
    public void error(final TransformerException exception) throws TransformerException {
        throw exception;
    }
    
    public void fatalError(final TransformerException exception) throws TransformerException {
        throw exception;
    }
    
    public static void ensureLocationSet(final TransformerException exception) {
        SourceLocator locator = null;
        Throwable cause = exception;
        do {
            if (cause instanceof SAXParseException) {
                locator = new SAXSourceLocator((SAXParseException)cause);
            }
            else if (cause instanceof TransformerException) {
                final SourceLocator causeLocator = ((TransformerException)cause).getLocator();
                if (null != causeLocator) {
                    locator = causeLocator;
                }
            }
            if (cause instanceof TransformerException) {
                cause = ((TransformerException)cause).getCause();
            }
            else if (cause instanceof SAXException) {
                cause = ((SAXException)cause).getException();
            }
            else {
                cause = null;
            }
        } while (null != cause);
        exception.setLocator(locator);
    }
    
    public static void printLocation(final PrintStream pw, final TransformerException exception) {
        printLocation(new PrintWriter(pw), exception);
    }
    
    public static void printLocation(final PrintStream pw, final SAXParseException exception) {
        printLocation(new PrintWriter(pw), exception);
    }
    
    public static void printLocation(final PrintWriter pw, final Throwable exception) {
        SourceLocator locator = null;
        Throwable cause = exception;
        do {
            if (cause instanceof SAXParseException) {
                locator = new SAXSourceLocator((SAXParseException)cause);
            }
            else if (cause instanceof TransformerException) {
                final SourceLocator causeLocator = ((TransformerException)cause).getLocator();
                if (null != causeLocator) {
                    locator = causeLocator;
                }
            }
            if (cause instanceof TransformerException) {
                cause = ((TransformerException)cause).getCause();
            }
            else if (cause instanceof WrappedRuntimeException) {
                cause = ((WrappedRuntimeException)cause).getException();
            }
            else if (cause instanceof SAXException) {
                cause = ((SAXException)cause).getException();
            }
            else {
                cause = null;
            }
        } while (null != cause);
        if (null != locator) {
            final String id = (null != locator.getPublicId()) ? locator.getPublicId() : ((null != locator.getSystemId()) ? locator.getSystemId() : XMLMessages.createXMLMessage("ER_SYSTEMID_UNKNOWN", null));
            pw.print(id + "; " + XMLMessages.createXMLMessage("line", null) + locator.getLineNumber() + "; " + XMLMessages.createXMLMessage("column", null) + locator.getColumnNumber() + "; ");
        }
        else {
            pw.print("(" + XMLMessages.createXMLMessage("ER_LOCATION_UNKNOWN", null) + ")");
        }
    }
}
