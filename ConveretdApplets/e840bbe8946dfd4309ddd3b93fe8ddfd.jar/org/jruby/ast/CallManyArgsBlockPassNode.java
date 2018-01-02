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

public final class CallManyArgsBlockPassNode extends CallNode
{
    public CallManyArgsBlockPassNode(final ISourcePosition position, final Node receiverNode, final String name, final Node args, final BlockPassNode iter) {
        super(position, receiverNode, name, args, iter);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.getReceiverNode().interpret(runtime, context, self, aBlock);
        final IRubyObject[] args = ((ArrayNode)this.getArgsNode()).interpretPrimitive(runtime, context, self, aBlock);
        final Block block = RuntimeHelpers.getBlock(runtime, context, self, this.iterNode, aBlock);
        return this.callAdapter.call(context, self, receiver, args, block);
    }
}
