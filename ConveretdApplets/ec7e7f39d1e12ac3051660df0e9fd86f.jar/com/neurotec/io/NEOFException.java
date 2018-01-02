// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.io;

import java.io.PrintWriter;
import java.io.PrintStream;
import com.neurotec.lang.NThrowable;
import java.io.EOFException;

final class NEOFException extends EOFException implements NThrowable
{
    private int code;
    private String message;
    private String callStack;
    
    NEOFException(final int code, final String message, final String callStack, final Throwable cause) {
        super(message);
        this.initCause(cause);
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
        s.println(this.getNativeStackTrace());
        super.printStackTrace(s);
    }
}
