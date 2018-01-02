// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public final class CallSpecialArgBlockPassNode extends CallNode
{
    public CallSpecialArgBlockPassNode(final ISourcePosition position, final Node receiverNode, final String name, final Node args, final BlockPassNode iter) {
        super(position, receiverNode, name, args, iter);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.getReceiverNode().interpret(runtime, context, self, aBlock);
        final IRubyObject arg = this.getArgsNode().interpret(runtime, context, self, aBlock);
        final Block block = RuntimeHelpers.getBlock(runtime, context, self, this.iterNode, aBlock);
        if (!(arg instanceof RubyArray)) {
            return this.callAdapter.call(context, self, receiver, arg, block);
        }
        final RubyArray nodes = (RubyArray)arg;
        switch (nodes.size()) {
            case 0: {
                return this.callAdapter.call(context, self, receiver, block);
            }
            case 1: {
                return this.callAdapter.call(context, self, receiver, nodes.eltInternal(0), block);
            }
            case 2: {
                return this.callAdapter.call(context, self, receiver, nodes.eltInternal(0), nodes.eltInternal(1), block);
            }
            case 3: {
                return this.callAdapter.call(context, self, receiver, nodes.eltInternal(0), nodes.eltInternal(1), nodes.eltInternal(2), block);
            }
            default: {
                return this.callAdapter.call(context, self, receiver, nodes.toJavaArrayMaybeUnsafe(), block);
            }
        }
    }
}
