// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.backtrace;

import org.jruby.lexer.yacc.ISourcePosition;

public class BacktraceElement
{
    public String klass;
    public String method;
    public String filename;
    public int line;
    
    public BacktraceElement() {
    }
    
    public BacktraceElement(final String klass, final String method, final String filename, final int line) {
        this.method = method;
        this.filename = filename;
        this.line = line;
        this.klass = klass;
    }
    
    public String toString() {
        return this.klass + "#" + this.method + " at " + this.filename + ":" + this.line;
    }
    
    public BacktraceElement clone() {
        return new BacktraceElement(this.klass, this.method, this.filename, this.line);
    }
    
    public static void update(final BacktraceElement backtrace, final String klass, final String method, final ISourcePosition position) {
        backtrace.method = method;
        backtrace.filename = position.getFile();
        backtrace.line = position.getLine();
        backtrace.klass = klass;
    }
    
    public static void update(final BacktraceElement backtrace, final String klass, final String method, final String file, final int line) {
        backtrace.method = method;
        backtrace.filename = file;
        backtrace.line = line;
        backtrace.klass = klass;
    }
    
    public String getFilename() {
        return this.filename;
    }
    
    public void setFilename(final String filename) {
        this.filename = filename;
    }
    
    public String getKlass() {
        return this.klass;
    }
    
    public void setKlass(final String klass) {
        this.klass = klass;
    }
    
    public int getLine() {
        return this.line;
    }
    
    public void setLine(final int line) {
        this.line = line;
    }
    
    public String getMethod() {
        return this.method;
    }
    
    public void setMethod(final String method) {
        this.method = method;
    }
}
