// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

public class AuthenticationException extends Exception
{
    static final long serialVersionUID = 0L;
    
    public AuthenticationException(final Reason reason) {
        super(reason.name());
    }
    
    public AuthenticationException(final String reason) {
        this(Reason.valueOf(reason));
    }
    
    public Reason getReason() {
        return Reason.valueOf(this.getMessage());
    }
    
    public enum Reason
    {
        invalidUserid("invalidUserid", 0), 
        invalidPassword("invalidPassword", 1);
        
        private Reason(final String s, final int n) {
        }
    }
}
