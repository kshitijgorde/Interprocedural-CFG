// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public final class CallManyArgsNode extends CallNode
{
    public CallManyArgsNode(final ISourcePosition position, final Node receiverNode, final String name, final Node args) {
        super(position, receiverNode, name, args, null);
    }
    
    public Node setIterNode(final Node iterNode) {
        return new CallManyArgsBlockNode(this.getPosition(), this.getReceiverNode(), this.getName(), this.getArgsNode(), (IterNode)iterNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.getReceiverNode().interpret(runtime, context, self, aBlock);
        final IRubyObject[] args = ((ArrayNode)this.getArgsNode()).interpretPrimitive(runtime, context, self, aBlock);
        return this.callAdapter.call(context, self, receiver, args);
    }
}
