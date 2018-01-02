// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

public class SimpleSourcePosition implements ISourcePosition
{
    final String filename;
    final int line;
    
    public SimpleSourcePosition(final String filename, final int line) {
        this.filename = filename;
        this.line = line;
    }
    
    public String getFile() {
        return this.filename;
    }
    
    public int getStartLine() {
        return this.line;
    }
    
    public int getLine() {
        return this.line;
    }
    
    public String toString() {
        return this.getFile() + ":" + (this.getStartLine() + 1);
    }
}
