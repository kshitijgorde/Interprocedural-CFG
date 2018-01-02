// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import org.w3c.dom.Node;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;

public class XPathException extends TransformerException
{
    static final long serialVersionUID = 4263549717619045963L;
    Object m_styleNode;
    protected Exception m_exception;
    static /* synthetic */ Class class$java$lang$Throwable;
    
    public Object getStylesheetNode() {
        return this.m_styleNode;
    }
    
    public void setStylesheetNode(final Object styleNode) {
        this.m_styleNode = styleNode;
    }
    
    public XPathException(final String message, final ExpressionNode ex) {
        super(message);
        this.m_styleNode = null;
        this.setLocator(ex);
        this.setStylesheetNode(this.getStylesheetNode(ex));
    }
    
    public XPathException(final String message) {
        super(message);
        this.m_styleNode = null;
    }
    
    public Node getStylesheetNode(final ExpressionNode ex) {
        final ExpressionNode owner = this.getExpressionOwner(ex);
        if (null != owner && owner instanceof Node) {
            return (Node)owner;
        }
        return null;
    }
    
    protected ExpressionNode getExpressionOwner(final ExpressionNode ex) {
        ExpressionNode parent;
        for (parent = ex.exprGetParent(); null != parent && parent instanceof Expression; parent = parent.exprGetParent()) {}
        return parent;
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
    
    public XPathException(final String message, final Exception e) {
        super(message);
        this.m_styleNode = null;
        this.m_exception = e;
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
        for (int i = 0; i < 10 && null != exception; ++i) {
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
    
    public String getMessage() {
        String lastMessage = super.getMessage();
        Throwable exception = this.m_exception;
        while (null != exception) {
            final String nextMessage = exception.getMessage();
            if (null != nextMessage) {
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
        return (null != lastMessage) ? lastMessage : "";
    }
    
    public void printStackTrace(PrintWriter s) {
        if (s == null) {
            s = new PrintWriter(System.err);
        }
        try {
            super.printStackTrace(s);
        }
        catch (Exception ex) {}
        boolean isJdk14OrHigher = false;
        try {
            ((XPathException.class$java$lang$Throwable == null) ? (XPathException.class$java$lang$Throwable = class$("java.lang.Throwable")) : XPathException.class$java$lang$Throwable).getMethod("getCause", (Class[])null);
            isJdk14OrHigher = true;
        }
        catch (NoSuchMethodException ex2) {}
        if (!isJdk14OrHigher) {
            Throwable exception = this.m_exception;
            for (int i = 0; i < 10 && null != exception; ++i) {
                s.println("---------");
                try {
                    exception.printStackTrace(s);
                }
                catch (Exception e) {
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
    
    public Throwable getException() {
        return this.m_exception;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
