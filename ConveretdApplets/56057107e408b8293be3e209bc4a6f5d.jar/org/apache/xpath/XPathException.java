// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import org.w3c.dom.Node;
import javax.xml.transform.TransformerException;

public class XPathException extends TransformerException
{
    Object m_styleNode;
    protected Exception m_exception;
    
    public XPathException(final String message) {
        super(message);
        this.m_styleNode = null;
    }
    
    public XPathException(final String message, final Exception e) {
        super(message);
        this.m_styleNode = null;
        this.m_exception = e;
    }
    
    public XPathException(final String message, final Object styleNode) {
        super(message);
        this.m_styleNode = null;
        this.m_styleNode = styleNode;
    }
    
    public XPathException(final String message, final Node styleNode, final Exception e) {
        super(message);
        this.m_styleNode = null;
        this.m_styleNode = styleNode;
        this.m_exception = e;
    }
    
    public Throwable getException() {
        return this.m_exception;
    }
    
    public String getMessage() {
        String lastMessage = super.getMessage();
        Throwable exception = this.m_exception;
        while (exception != null) {
            final String nextMessage = exception.getMessage();
            if (nextMessage != null) {
                lastMessage = nextMessage;
            }
            if (exception instanceof TransformerException) {
                final TransformerException se = (TransformerException)exception;
                final Throwable prev = exception;
                exception = se.getException();
                if (prev == exception) {
                    break;
                }
                continue;
            }
            else {
                exception = null;
            }
        }
        return (lastMessage != null) ? lastMessage : "";
    }
    
    public Object getStylesheetNode() {
        return this.m_styleNode;
    }
    
    public void printStackTrace(PrintStream s) {
        if (s == null) {
            s = System.err;
        }
        try {
            super.printStackTrace(s);
        }
        catch (Exception ex) {}
        Throwable exception = this.m_exception;
        for (int i = 0; i < 10 && exception != null; ++i) {
            s.println("---------");
            exception.printStackTrace(s);
            if (exception instanceof TransformerException) {
                final TransformerException se = (TransformerException)exception;
                final Throwable prev = exception;
                exception = se.getException();
                if (prev == exception) {
                    break;
                }
            }
            else {
                exception = null;
            }
        }
    }
    
    public void printStackTrace(PrintWriter s) {
        if (s == null) {
            s = new PrintWriter(System.err);
        }
        try {
            super.printStackTrace(s);
        }
        catch (Exception ex) {}
        Throwable exception = this.m_exception;
        for (int i = 0; i < 10 && exception != null; ++i) {
            s.println("---------");
            try {
                exception.printStackTrace(s);
            }
            catch (Exception ex2) {
                s.println("Could not print stack trace...");
            }
            if (exception instanceof TransformerException) {
                final TransformerException se = (TransformerException)exception;
                final Throwable prev = exception;
                exception = se.getException();
                if (prev == exception) {
                    exception = null;
                    break;
                }
            }
            else {
                exception = null;
            }
        }
    }
}
