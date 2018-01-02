// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import java.io.PrintWriter;
import java.io.PrintStream;

final class NRuntimeException extends RuntimeException implements NThrowable
{
    private int code;
    private String message;
    private String callStack;
    
    NRuntimeException(final int code, final String message, final String callStack, final Throwable cause) {
        super(message, cause);
        this.callStack = callStack;
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getNativeStackTrace() {
        return (this.callStack != null) ? this.callStack : "";
    }
    
    public void printStackTrace(final PrintStream printstream) {
        printstream.print(this.getNativeStackTrace());
        super.printStackTrace(printstream);
    }
    
    public void printStackTrace(final PrintWriter s) {
        s.print(this.getNativeStackTrace());
        super.printStackTrace(s);
    }
}
