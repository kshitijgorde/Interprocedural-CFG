// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.exceptions.JumpException;
import org.jruby.RubyLocalJumpError;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public final class CallNoArgBlockNode extends CallNode
{
    public CallNoArgBlockNode(final ISourcePosition position, final Node receiverNode, final String name, final IterNode iter) {
        super(position, receiverNode, name, null, iter);
    }
    
    public CallNoArgBlockNode(final ISourcePosition position, final Node receiverNode, final String name, final Node args, final IterNode iter) {
        super(position, receiverNode, name, args, iter);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.getReceiverNode().interpret(runtime, context, self, aBlock);
        final Block block = RuntimeHelpers.getBlock(context, self, this.iterNode);
        try {
            return this.callAdapter.callIter(context, self, receiver, block);
        }
        catch (JumpException.RetryJump rj) {
            throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.RETRY, self, "retry is not supported outside rescue");
        }
        finally {
            block.escape();
        }
    }
}
