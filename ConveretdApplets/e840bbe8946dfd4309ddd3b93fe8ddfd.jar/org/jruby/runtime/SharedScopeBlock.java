// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.IterNode;

public class SharedScopeBlock extends InterpretedBlock
{
    protected SharedScopeBlock(final IterNode iterNode) {
        super(iterNode, BlockBody.asArgumentType(BlockBody.getArgumentTypeWackyHack(iterNode)));
    }
    
    public static Block newInterpretedSharedScopeClosure(final ThreadContext context, final IterNode iterNode, final DynamicScope dynamicScope, final IRubyObject self) {
        final Binding binding = context.currentBinding(self, dynamicScope);
        final InterpretedBlock body = new SharedScopeBlock(iterNode);
        return new Block(body, binding);
    }
    
    protected Frame pre(final ThreadContext context, final RubyModule klass, final Binding binding) {
        return context.preForBlock(binding, klass);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final IRubyObject replacementSelf, final Binding binding, final Block.Type type) {
        return this.yield(context, context.getRuntime().newArrayNoCopy(args), null, null, true, binding, type);
    }
    
    public Block cloneBlock(final Binding binding) {
        return new Block(this, binding);
    }
}
