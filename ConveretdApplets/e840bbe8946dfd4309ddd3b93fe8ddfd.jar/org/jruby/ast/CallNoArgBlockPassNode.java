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

public final class CallNoArgBlockPassNode extends CallNode
{
    public CallNoArgBlockPassNode(final ISourcePosition position, final Node receiverNode, final String name, final Node args, final BlockPassNode iter) {
        super(position, receiverNode, name, args, iter);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.callAdapter.call(context, self, this.getReceiverNode().interpret(runtime, context, self, aBlock), RuntimeHelpers.getBlock(runtime, context, self, this.iterNode, aBlock));
    }
}
