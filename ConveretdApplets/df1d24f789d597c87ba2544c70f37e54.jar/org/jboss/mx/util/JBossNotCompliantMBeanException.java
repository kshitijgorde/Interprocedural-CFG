// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.io.PrintWriter;
import java.io.PrintStream;
import javax.management.NotCompliantMBeanException;

public class JBossNotCompliantMBeanException extends NotCompliantMBeanException
{
    private static final long serialVersionUID = 7441880585611238427L;
    private final Throwable t;
    
    public JBossNotCompliantMBeanException() {
        this.t = null;
    }
    
    public JBossNotCompliantMBeanException(final String message) {
        super(message);
        this.t = null;
    }
    
    public JBossNotCompliantMBeanException(final Throwable t) {
        this.t = t;
    }
    
    public JBossNotCompliantMBeanException(final String message, final Throwable t) {
        super(message);
        this.t = t;
    }
    
    public Throwable getNested() {
        return this.t;
    }
    
    public Throwable getCause() {
        return this.t;
    }
    
    public String getMessage() {
        return super.getMessage();
    }
    
    public void printStackTrace(final PrintStream stream) {
        super.printStackTrace(stream);
    }
    
    public void printStackTrace(final PrintWriter writer) {
        super.printStackTrace(writer);
    }
    
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }
}
