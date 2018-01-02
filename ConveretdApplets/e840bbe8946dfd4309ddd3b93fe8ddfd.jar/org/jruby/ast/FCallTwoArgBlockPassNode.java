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

public class FCallTwoArgBlockPassNode extends FCallNode
{
    private Node arg1;
    private Node arg2;
    
    public FCallTwoArgBlockPassNode(final ISourcePosition position, final String name, final ArrayNode args, final BlockPassNode iter) {
        super(position, name, args, iter);
        assert args.size() == 2 : "args.size() is 2";
        this.arg1 = args.get(0);
        this.arg2 = args.get(1);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.callAdapter.call(context, self, self, this.arg1.interpret(runtime, context, self, aBlock), this.arg2.interpret(runtime, context, self, aBlock), RuntimeHelpers.getBlock(runtime, context, self, this.iterNode, aBlock));
    }
}
