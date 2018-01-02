// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

public class DuplicateKeyException extends DbException
{
    static final long serialVersionUID = 2L;
    
    public DuplicateKeyException() {
    }
    
    public DuplicateKeyException(final String message) {
        super(message);
    }
    
    public DuplicateKeyException(final Exception e) {
        super(e);
    }
}
