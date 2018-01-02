// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

import org.jruby.runtime.PositionAware;

public interface ISourcePosition extends PositionAware
{
    public static final ISourcePosition INVALID_POSITION = new ISourcePosition() {
        public String getFile() {
            return "dummy";
        }
        
        public int getStartLine() {
            return -1;
        }
        
        public int getLine() {
            return -1;
        }
    };
    
    String getFile();
    
    int getStartLine();
}
