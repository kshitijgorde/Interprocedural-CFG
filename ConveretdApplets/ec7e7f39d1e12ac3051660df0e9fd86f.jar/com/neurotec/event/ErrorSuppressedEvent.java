// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.event;

import java.util.EventObject;

public class ErrorSuppressedEvent extends EventObject
{
    private Throwable cause;
    
    public ErrorSuppressedEvent(final Object source, final Throwable cause) {
        super(source);
        this.cause = cause;
    }
    
    public Throwable getCause() {
        return this.cause;
    }
}
