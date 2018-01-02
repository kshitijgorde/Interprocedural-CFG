// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import java.io.PrintWriter;
import java.io.PrintStream;

abstract class NeurotecExceptionBase extends RuntimeException implements NThrowable
{
    private int code;
    private String callStack;
    
    protected NeurotecExceptionBase(final int code, final String message, final String callStack, final Throwable cause) {
        super((message == null) ? NError.getDefaultMessage(code) : message, cause);
        this.code = code;
        this.callStack = callStack;
    }
    
    public final int getCode() {
        return this.code;
    }
    
    public final String getNativeStackTrace() {
        return (this.callStack != null) ? this.callStack : "";
    }
    
    public final void printStackTrace(final PrintStream printstream) {
        printstream.print(this.getNativeStackTrace());
        super.printStackTrace(printstream);
    }
    
    public final void printStackTrace(final PrintWriter printstream) {
        printstream.print(this.callStack);
        super.printStackTrace(printstream);
    }
}
