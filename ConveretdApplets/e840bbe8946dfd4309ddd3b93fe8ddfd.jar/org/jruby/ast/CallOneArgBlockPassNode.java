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

public final class CallOneArgBlockPassNode extends CallNode
{
    private Node arg1;
    
    public CallOneArgBlockPassNode(final ISourcePosition position, final Node receiverNode, final String name, final ArrayNode args, final BlockPassNode iter) {
        super(position, receiverNode, name, args, iter);
        assert args.size() == 1 : "args.size() is 1";
        this.arg1 = args.get(0);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.getReceiverNode().interpret(runtime, context, self, aBlock);
        return this.callAdapter.call(context, self, receiver, this.arg1.interpret(runtime, context, self, aBlock), RuntimeHelpers.getBlock(runtime, context, self, this.iterNode, aBlock));
    }
}
