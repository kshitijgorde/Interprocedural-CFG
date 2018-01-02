// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt;

import org.eclipse.swt.internal.Library;

public class SWTException extends RuntimeException
{
    public int code;
    public Throwable throwable;
    static final long serialVersionUID = 3257282552304842547L;
    
    public SWTException() {
        this(1);
    }
    
    public SWTException(final String s) {
        this(1, s);
    }
    
    public SWTException(final int n) {
        this(n, SWT.findErrorText(n));
    }
    
    public SWTException(final int code, final String s) {
        super(s);
        this.code = code;
    }
    
    public Throwable getCause() {
        return this.throwable;
    }
    
    public String getMessage() {
        if (this.throwable == null) {
            return super.getMessage();
        }
        return String.valueOf(super.getMessage()) + " (" + this.throwable.toString() + ")";
    }
    
    public void printStackTrace() {
        super.printStackTrace();
        if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 4, 0) && this.throwable != null) {
            System.err.println("*** Stack trace of contained exception ***");
            this.throwable.printStackTrace();
        }
    }
}
