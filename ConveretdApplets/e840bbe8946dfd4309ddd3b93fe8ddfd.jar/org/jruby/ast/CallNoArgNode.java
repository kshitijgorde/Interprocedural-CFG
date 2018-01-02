// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.exceptions.JumpException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.util.ByteList;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public final class CallNoArgNode extends CallNode
{
    public CallNoArgNode(final ISourcePosition position, final Node receiverNode, final String name) {
        super(position, receiverNode, name, null, null);
    }
    
    public CallNoArgNode(final ISourcePosition position, final Node receiverNode, final Node args, final String name) {
        super(position, receiverNode, name, args, null);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.callAdapter.call(context, self, this.getReceiverNode().interpret(runtime, context, self, aBlock));
    }
    
    public Node setIterNode(final Node iterNode) {
        return new CallNoArgBlockNode(this.getPosition(), this.getReceiverNode(), this.getName(), this.getArgsNode(), (IterNode)iterNode);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final ByteList definition = null;
        if (this.getReceiverNode().definition(runtime, context, self, aBlock) != null) {
            try {
                final IRubyObject receiver = this.getReceiverNode().interpret(runtime, context, self, aBlock);
                return RuntimeHelpers.getDefinedCall(context, self, receiver, this.getName());
            }
            catch (JumpException ex) {}
        }
        return definition;
    }
}
