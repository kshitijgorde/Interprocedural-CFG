// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.io;

import java.io.PrintWriter;
import java.io.PrintStream;
import com.neurotec.lang.NError;
import com.neurotec.lang.NThrowable;
import java.io.FileNotFoundException;

final class NFileNotFoundException extends FileNotFoundException implements NThrowable
{
    private int code;
    private String message;
    private String callStack;
    
    NFileNotFoundException(final int code, final String message, final String param, final String callStack, final Throwable cause) {
        super(NError.getMessage(message, (code == -21 || code == -22) ? "FileName: " : "Path: ", param));
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
