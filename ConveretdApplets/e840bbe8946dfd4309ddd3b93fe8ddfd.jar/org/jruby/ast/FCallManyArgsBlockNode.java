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

public class FCallManyArgsBlockNode extends FCallNode
{
    public FCallManyArgsBlockNode(final ISourcePosition position, final String name, final Node args, final IterNode iter) {
        super(position, name, args, iter);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject[] args = ((ArrayNode)this.getArgsNode()).interpretPrimitive(runtime, context, self, aBlock);
        final Block block = RuntimeHelpers.getBlock(context, self, this.iterNode);
        return this.callAdapter.callIter(context, self, self, args, block);
    }
}
