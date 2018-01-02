// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.ast.types.IEqlNode;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.ThreadContext;
import org.jruby.lexer.yacc.ISourcePosition;

public class WhenOneArgNode extends WhenNode
{
    public WhenOneArgNode(final ISourcePosition position, final Node expressionNode, final Node bodyNode, final Node nextCase) {
        super(position, expressionNode, bodyNode, nextCase);
    }
    
    private IRubyObject whenNoTest(final ThreadContext context, final Ruby runtime, final IRubyObject self, final Block aBlock) {
        if (this.expressionNodes.interpret(runtime, context, self, aBlock).isTrue()) {
            return this.bodyNode.interpret(runtime, context, self, aBlock);
        }
        return null;
    }
    
    private IRubyObject whenSlowTest(final IRubyObject test, final ThreadContext context, final Ruby runtime, final IRubyObject self, final Block aBlock) {
        final IRubyObject expression = this.expressionNodes.interpret(runtime, context, self, aBlock);
        if (this.eqq.call(context, self, expression, test).isTrue()) {
            return this.bodyNode.interpret(runtime, context, self, aBlock);
        }
        return null;
    }
    
    public IRubyObject when(final IRubyObject test, final ThreadContext context, final Ruby runtime, final IRubyObject self, final Block aBlock) {
        if (test == null) {
            return this.whenNoTest(context, runtime, self, aBlock);
        }
        if (!(this.expressionNodes instanceof IEqlNode)) {
            return this.whenSlowTest(test, context, runtime, self, aBlock);
        }
        if (((IEqlNode)this.expressionNodes).eql(test, context, runtime, self, aBlock)) {
            return this.bodyNode.interpret(runtime, context, self, aBlock);
        }
        return null;
    }
}
