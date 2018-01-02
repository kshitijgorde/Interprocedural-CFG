// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.lexer.yacc.ISourcePosition;

public class NilImplicitNode extends NilNode implements InvisibleNode
{
    public static final NilImplicitNode NIL;
    
    public NilImplicitNode() {
        super(ISourcePosition.INVALID_POSITION);
    }
    
    static {
        NIL = new NilImplicitNode();
    }
}
