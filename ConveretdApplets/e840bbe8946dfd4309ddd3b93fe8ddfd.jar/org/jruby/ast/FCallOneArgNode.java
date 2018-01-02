// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class FCallOneArgNode extends FCallNode
{
    private Node arg1;
    
    public FCallOneArgNode(final ISourcePosition position, final String name, final ArrayNode args) {
        super(position, name, args, null);
        assert args.size() == 1 : "args.size() is 1";
        this.arg1 = args.get(0);
    }
    
    public Node setIterNode(final Node iterNode) {
        return new FCallOneArgBlockNode(this.getPosition(), this.getName(), (ArrayNode)this.getArgsNode(), (IterNode)iterNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.callAdapter.call(context, self, self, this.arg1.interpret(runtime, context, self, aBlock));
    }
}
