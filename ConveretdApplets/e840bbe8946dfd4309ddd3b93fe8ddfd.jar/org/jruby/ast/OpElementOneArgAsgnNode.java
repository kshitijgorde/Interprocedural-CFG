// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class OpElementOneArgAsgnNode extends OpElementAsgnNode
{
    private Node arg1;
    
    public OpElementOneArgAsgnNode(final ISourcePosition position, final Node receiverNode, final String operatorName, final ArrayNode args, final Node valueNode) {
        super(position, receiverNode, operatorName, args, valueNode);
        assert args.size() == 1 : "args.size() is 1";
        this.arg1 = args.get(0);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.getReceiverNode().interpret(runtime, context, self, aBlock);
        final IRubyObject rArg1 = this.arg1.interpret(runtime, context, self, aBlock);
        final IRubyObject firstValue = this.callAdapter.call(context, self, this.elementAdapter.call(context, self, receiver, rArg1), this.getValueNode().interpret(runtime, context, self, aBlock));
        this.elementAsgnAdapter.call(context, self, receiver, rArg1, firstValue);
        return firstValue;
    }
}
