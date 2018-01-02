// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml;

import java.io.PrintWriter;
import java.io.PrintStream;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class ParseException extends SAXException
{
    private int line;
    private int column;
    
    public ParseException(final String message) {
        super(message);
        this.fillLocation(null);
    }
    
    public ParseException(final Exception e) {
        super(e);
        this.fillLocation(null);
    }
    
    public ParseException(final String message, final Exception e) {
        super(message, e);
        this.fillLocation(null);
    }
    
    public ParseException(final String message, final Locator locator) {
        super(message);
        this.fillLocation(locator);
    }
    
    public ParseException(final Exception e, final Locator locator) {
        super(e);
        this.fillLocation(locator);
    }
    
    public ParseException(final String message, final Exception e, final Locator locator) {
        super(message, e);
        this.fillLocation(locator);
    }
    
    public String getMessage() {
        final StringBuffer sb = new StringBuffer(String.valueOf(super.getMessage()));
        sb.append(" [Location: Line=");
        sb.append(this.line);
        sb.append(" Column=");
        sb.append(this.column);
        sb.append("] ");
        return sb.toString();
    }
    
    protected void fillLocation(final Locator locator) {
        if (locator == null) {
            this.line = -1;
            this.column = -1;
        }
        else {
            this.line = locator.getLineNumber();
            this.column = locator.getColumnNumber();
        }
    }
    
    public int getLine() {
        return this.line;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public void printStackTrace(final PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.getException() != null) {
            printStream.println("ParentException: ");
            this.getException().printStackTrace(printStream);
        }
    }
    
    public String toString() {
        return this.getClass().getName() + ": " + this.getMessage();
    }
    
    public void printStackTrace(final PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.getException() != null) {
            printWriter.println("ParentException: ");
            this.getException().printStackTrace(printWriter);
        }
    }
}
