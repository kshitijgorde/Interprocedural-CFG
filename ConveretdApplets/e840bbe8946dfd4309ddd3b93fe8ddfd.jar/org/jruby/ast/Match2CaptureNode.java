// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class Match2CaptureNode extends Match2Node
{
    private int[] scopeOffsets;
    
    public Match2CaptureNode(final ISourcePosition position, final Node receiverNode, final Node valueNode, final int[] scopeOffsets) {
        super(position, receiverNode, valueNode);
        this.scopeOffsets = scopeOffsets;
    }
    
    public int[] getScopeOffsets() {
        return this.scopeOffsets;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject result = super.interpret(runtime, context, self, aBlock);
        final DynamicScope scope = context.getCurrentScope();
        RuntimeHelpers.updateScopeWithCaptures(context, scope, this.scopeOffsets, result);
        return result;
    }
}
