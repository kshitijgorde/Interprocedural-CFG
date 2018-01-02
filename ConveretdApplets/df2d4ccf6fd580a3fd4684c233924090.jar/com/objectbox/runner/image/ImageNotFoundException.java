// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.image;

public class ImageNotFoundException extends RuntimeException
{
    private Exception originalException;
    
    public ImageNotFoundException(final Exception originalException, final String s) {
        super(s);
        this.originalException = null;
        if (originalException != null) {
            this.originalException = originalException;
        }
    }
    
    public ImageNotFoundException(final String s) {
        this(null, s);
    }
    
    public Exception getOriginalException() {
        return this.originalException;
    }
}
