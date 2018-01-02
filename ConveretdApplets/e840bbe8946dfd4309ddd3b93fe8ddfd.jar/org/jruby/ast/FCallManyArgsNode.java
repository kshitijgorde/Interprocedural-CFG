// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class FCallManyArgsNode extends FCallNode implements INameNode, IArgumentNode, BlockAcceptingNode
{
    public FCallManyArgsNode(final ISourcePosition position, final String name, final Node argsNode) {
        super(position, name, argsNode, null);
    }
    
    public Node setIterNode(final Node iterNode) {
        return new FCallManyArgsBlockNode(this.getPosition(), this.getName(), this.getArgsNode(), (IterNode)iterNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject[] args = ((ArrayNode)this.getArgsNode()).interpretPrimitive(runtime, context, self, aBlock);
        return this.callAdapter.call(context, self, self, args);
    }
}
