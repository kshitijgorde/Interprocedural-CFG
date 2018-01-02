// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

public class DbException extends Exception
{
    private int errorCode;
    static final long serialVersionUID = 1L;
    
    public DbException() {
        this.errorCode = 0;
    }
    
    public DbException(final String message) {
        super(message);
        this.errorCode = 0;
    }
    
    public DbException(final Exception e) {
        super(e);
        this.errorCode = 0;
    }
    
    public DbException(final Exception e, final int err) {
        super(e);
        this.errorCode = 0;
        this.errorCode = err;
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
}
