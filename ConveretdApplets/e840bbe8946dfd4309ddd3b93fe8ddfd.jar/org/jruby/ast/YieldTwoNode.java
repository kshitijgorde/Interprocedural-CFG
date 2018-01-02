// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class YieldTwoNode extends YieldNode
{
    private final Node argument1;
    private final Node argument2;
    
    public YieldTwoNode(final ISourcePosition position, final ArrayNode args) {
        super(position, args, true);
        this.argument1 = args.get(0);
        this.argument2 = args.get(1);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return context.getCurrentFrame().getBlock().yieldSpecific(context, this.argument1.interpret(runtime, context, self, aBlock), this.argument2.interpret(runtime, context, self, aBlock));
    }
}
