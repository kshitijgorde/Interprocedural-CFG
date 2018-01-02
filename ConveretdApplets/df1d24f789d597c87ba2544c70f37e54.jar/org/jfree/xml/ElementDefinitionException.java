// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml;

import java.io.PrintWriter;
import java.io.PrintStream;

public class ElementDefinitionException extends ParseException
{
    private Exception parent;
    
    public ElementDefinitionException(final String s) {
        super(s);
    }
    
    public ElementDefinitionException(final Exception ex) {
        this(ex, ex.getMessage());
    }
    
    public ElementDefinitionException(final Exception parent, final String s) {
        this(s);
        this.parent = parent;
    }
    
    public Exception getParentException() {
        return this.parent;
    }
    
    public void printStackTrace(final PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.parent != null) {
            printStream.print("ParentException:");
            this.parent.printStackTrace(printStream);
        }
        else {
            printStream.println("ParentException: <null>");
        }
    }
    
    public void printStackTrace(final PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.parent != null) {
            printWriter.print("ParentException:");
            this.parent.printStackTrace(printWriter);
        }
        else {
            printWriter.println("ParentException: <null>");
        }
    }
}
