// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

public class LockingException extends DbException
{
    private final Type type;
    static final long serialVersionUID = 3L;
    
    public LockingException(final Type type) {
        this.type = type;
    }
    
    public LockingException(final Type type, final String message) {
        super(message);
        this.type = type;
    }
    
    public LockingException(final Type type, final Exception e) {
        super(e);
        this.type = type;
    }
    
    public Type type() {
        return this.type;
    }
    
    public enum Type
    {
        Deadlock("Deadlock", 0), 
        Timeout("Timeout", 1);
        
        private Type(final String s, final int n) {
        }
    }
}
