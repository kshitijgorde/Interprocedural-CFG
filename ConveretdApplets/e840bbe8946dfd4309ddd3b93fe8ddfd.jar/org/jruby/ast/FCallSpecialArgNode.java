// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyArray;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class FCallSpecialArgNode extends FCallNode
{
    public FCallSpecialArgNode(final ISourcePosition position, final String name, final Node args) {
        super(position, name, args, null);
    }
    
    public Node setIterNode(final Node iterNode) {
        return new FCallSpecialArgBlockNode(this.getPosition(), this.getName(), this.getArgsNode(), (IterNode)iterNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject arg = this.getArgsNode().interpret(runtime, context, self, aBlock);
        if (!(arg instanceof RubyArray)) {
            return this.callAdapter.call(context, self, self, arg);
        }
        final RubyArray nodes = (RubyArray)arg;
        switch (nodes.size()) {
            case 0: {
                return this.callAdapter.call(context, self, self);
            }
            case 1: {
                return this.callAdapter.call(context, self, self, nodes.eltInternal(0));
            }
            case 2: {
                return this.callAdapter.call(context, self, self, nodes.eltInternal(0), nodes.eltInternal(1));
            }
            case 3: {
                return this.callAdapter.call(context, self, self, nodes.eltInternal(0), nodes.eltInternal(1), nodes.eltInternal(2));
            }
            default: {
                return this.callAdapter.call(context, self, self, nodes.toJavaArrayMaybeUnsafe());
            }
        }
    }
}
