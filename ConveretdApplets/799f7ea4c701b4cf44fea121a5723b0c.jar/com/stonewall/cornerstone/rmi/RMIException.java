// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

public class RMIException extends Exception
{
    static final long serialVersionUID = 0L;
    
    public RMIException() {
    }
    
    public RMIException(final String msg) {
        super(msg);
    }
    
    public RMIException(final Exception e) {
        super(e);
    }
    
    public RMIException(final String s, final Exception e) {
        super(s, e);
    }
}
