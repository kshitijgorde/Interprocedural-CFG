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

public class FCallSpecialArgBlockNode extends FCallNode
{
    public FCallSpecialArgBlockNode(final ISourcePosition position, final String name, final Node args, final IterNode iter) {
        super(position, name, args, iter);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject arg = this.getArgsNode().interpret(runtime, context, self, aBlock);
        final Block block = RuntimeHelpers.getBlock(context, self, this.iterNode);
        if (!(arg instanceof RubyArray)) {
            return this.callAdapter.callIter(context, self, self, arg, block);
        }
        final RubyArray nodes = (RubyArray)arg;
        switch (nodes.size()) {
            case 0: {
                return this.callAdapter.callIter(context, self, self, block);
            }
            case 1: {
                return this.callAdapter.callIter(context, self, self, nodes.eltInternal(0), block);
            }
            case 2: {
                return this.callAdapter.callIter(context, self, self, nodes.eltInternal(0), nodes.eltInternal(1), block);
            }
            case 3: {
                return this.callAdapter.callIter(context, self, self, nodes.eltInternal(0), nodes.eltInternal(1), nodes.eltInternal(2), block);
            }
            default: {
                return this.callAdapter.callIter(context, self, self, nodes.toJavaArrayMaybeUnsafe(), block);
            }
        }
    }
}
