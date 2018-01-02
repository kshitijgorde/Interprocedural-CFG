// 
// Decompiled by Procyon v0.5.30
// 

package com.skype;

public class SkypeException extends Exception
{
    private static final long serialVersionUID = -4277557764382543108L;
    
    SkypeException() {
    }
    
    SkypeException(final String message) {
        super(message);
    }
    
    SkypeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
