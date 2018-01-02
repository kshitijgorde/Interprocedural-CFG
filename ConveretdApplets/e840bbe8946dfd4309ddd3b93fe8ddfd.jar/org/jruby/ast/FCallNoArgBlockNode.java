// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class FCallNoArgBlockNode extends FCallNode
{
    public FCallNoArgBlockNode(final ISourcePosition position, final String name, final IterNode iter) {
        super(position, name, null, iter);
    }
    
    public FCallNoArgBlockNode(final ISourcePosition position, final String name, final Node args, final IterNode iter) {
        super(position, name, args, iter);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.callAdapter.callIter(context, self, self, RuntimeHelpers.getBlock(context, self, this.iterNode));
    }
}
