// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.IASjTools;

public class IASJ2KException extends Exception
{
    public static final long serialVersionUID = 1L;
    private boolean fatal;
    
    public IASJ2KException() {
    }
    
    public IASJ2KException(final String s) {
        super(s);
        this.fatal = false;
    }
    
    public IASJ2KException(final String s, final Throwable t) {
        super(s, t);
        this.fatal = false;
    }
    
    public IASJ2KException(final String s, final boolean fatal, final Throwable t) {
        super(s, t);
        this.fatal = fatal;
    }
    
    public boolean isFatal() {
        return this.fatal;
    }
}
