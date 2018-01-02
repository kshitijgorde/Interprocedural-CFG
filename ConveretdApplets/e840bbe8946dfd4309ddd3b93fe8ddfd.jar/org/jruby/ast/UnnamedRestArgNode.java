// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.lexer.yacc.ISourcePosition;

public class UnnamedRestArgNode extends RestArgNode
{
    public UnnamedRestArgNode(final ISourcePosition position, final String name, final int index) {
        super(position, name, index);
    }
    
    public boolean isStar() {
        return this.getName() != null;
    }
}
