// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.runtime.MethodIndex;
import org.jruby.lexer.yacc.ISourcePosition;

public final class CallOneArgFixnumNode extends CallNode
{
    private long arg1;
    
    public CallOneArgFixnumNode(final ISourcePosition position, final Node receiverNode, final String name, final ArrayNode args) {
        super(position, receiverNode, name, args, null);
        assert args.size() == 1 : "args.size() is 1";
        this.arg1 = ((FixnumNode)args.get(0)).getValue();
        this.callAdapter = MethodIndex.getCallSite(name);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.callAdapter.call(context, self, this.getReceiverNode().interpret(runtime, context, self, aBlock), this.arg1);
    }
    
    public Node setIterNode(final Node iterNode) {
        return new CallOneArgBlockNode(this.getPosition(), this.getReceiverNode(), this.getName(), (ArrayNode)this.getArgsNode(), (IterNode)iterNode);
    }
}
