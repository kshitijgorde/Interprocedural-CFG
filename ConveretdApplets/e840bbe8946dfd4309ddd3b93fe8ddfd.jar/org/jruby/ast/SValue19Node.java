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

public class SValue19Node extends SValueNode
{
    public SValue19Node(final ISourcePosition position, final Node node) {
        super(position, node);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return RuntimeHelpers.aValueSplat19(this.node.interpret(runtime, context, self, aBlock));
    }
}
