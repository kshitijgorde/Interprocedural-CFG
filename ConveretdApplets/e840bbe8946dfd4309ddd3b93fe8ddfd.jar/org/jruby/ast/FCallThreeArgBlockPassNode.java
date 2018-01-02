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

public class FCallThreeArgBlockPassNode extends FCallNode
{
    private Node arg1;
    private Node arg2;
    private Node arg3;
    
    public FCallThreeArgBlockPassNode(final ISourcePosition position, final String name, final ArrayNode args, final BlockPassNode iter) {
        super(position, name, args, iter);
        assert args.size() == 3 : "args.size() is 3";
        this.arg1 = args.get(0);
        this.arg2 = args.get(1);
        this.arg3 = args.get(2);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.callAdapter.call(context, self, self, this.arg1.interpret(runtime, context, self, aBlock), this.arg2.interpret(runtime, context, self, aBlock), this.arg3.interpret(runtime, context, self, aBlock), RuntimeHelpers.getBlock(runtime, context, self, this.iterNode, aBlock));
    }
}
