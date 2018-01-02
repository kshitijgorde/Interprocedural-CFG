// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class YieldOneNode extends YieldNode
{
    private final Node argument1;
    
    public YieldOneNode(final ISourcePosition position, final ArrayNode args) {
        super(position, args, true);
        this.argument1 = args.get(0);
    }
    
    public YieldOneNode(final ISourcePosition position, final FixnumNode arg) {
        super(position, arg, true);
        this.argument1 = arg;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return context.getCurrentFrame().getBlock().yieldSpecific(context, this.argument1.interpret(runtime, context, self, aBlock));
    }
}
