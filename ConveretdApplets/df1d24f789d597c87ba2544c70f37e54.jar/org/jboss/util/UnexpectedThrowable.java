// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public class UnexpectedThrowable extends NestedError
{
    public UnexpectedThrowable(final String msg) {
        super(msg);
    }
    
    public UnexpectedThrowable(final String msg, final Throwable nested) {
        super(msg, nested);
    }
    
    public UnexpectedThrowable(final Throwable nested) {
        super(nested);
    }
    
    public UnexpectedThrowable() {
    }
}
