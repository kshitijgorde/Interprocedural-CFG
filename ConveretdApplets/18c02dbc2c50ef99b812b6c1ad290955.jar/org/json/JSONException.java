// 
// Decompiled by Procyon v0.5.30
// 

package org.json;

public class JSONException extends Exception
{
    private Throwable cause;
    
    public JSONException(final String s) {
        super(s);
    }
    
    public JSONException(final Throwable cause) {
        super(cause.getMessage());
        this.cause = cause;
    }
    
    public Throwable getCause() {
        return this.cause;
    }
}
