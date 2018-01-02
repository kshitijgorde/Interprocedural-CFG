// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.PrintWriter;
import java.io.PrintStream;

public class NestedRuntimeException extends RuntimeException implements NestedThrowable
{
    protected final Throwable nested;
    
    public NestedRuntimeException(final String msg) {
        super(msg);
        this.nested = null;
    }
    
    public NestedRuntimeException(final String msg, final Throwable nested) {
        super(msg);
        Util.checkNested(this, this.nested = nested);
    }
    
    public NestedRuntimeException(final Throwable nested) {
        this(nested.getMessage(), nested);
    }
    
    public NestedRuntimeException() {
        this.nested = null;
    }
    
    public Throwable getNested() {
        return this.nested;
    }
    
    public Throwable getCause() {
        return this.nested;
    }
    
    public String getMessage() {
        return Util.getMessage(super.getMessage(), this.nested);
    }
    
    public void printStackTrace(final PrintStream stream) {
        if (this.nested == null || NestedThrowable.PARENT_TRACE_ENABLED) {
            super.printStackTrace(stream);
        }
        Util.print(this.nested, stream);
    }
    
    public void printStackTrace(final PrintWriter writer) {
        if (this.nested == null || NestedThrowable.PARENT_TRACE_ENABLED) {
            super.printStackTrace(writer);
        }
        Util.print(this.nested, writer);
    }
    
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }
}