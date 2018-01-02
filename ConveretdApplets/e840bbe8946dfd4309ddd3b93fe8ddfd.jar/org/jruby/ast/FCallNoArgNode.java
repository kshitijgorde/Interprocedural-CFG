// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class FCallNoArgNode extends FCallNode
{
    public FCallNoArgNode(final ISourcePosition position, final String name) {
        super(position, name, null, null);
    }
    
    public FCallNoArgNode(final ISourcePosition position, final Node args, final String name) {
        super(position, name, args, null);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.callAdapter.call(context, self, self);
    }
    
    public Node setIterNode(final Node iterNode) {
        return new FCallNoArgBlockNode(this.getPosition(), this.getName(), this.getArgsNode(), (IterNode)iterNode);
    }
}
