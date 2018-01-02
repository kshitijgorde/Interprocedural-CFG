// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;

public class CompiledSharedScopeBlock extends CompiledBlockLight
{
    public static Block newCompiledSharedScopeClosure(final ThreadContext context, final IRubyObject self, final Arity arity, final DynamicScope dynamicScope, final CompiledBlockCallback callback, final boolean hasMultipleArgsHead, final int argumentType) {
        final Binding binding = context.currentBinding(self, Visibility.PUBLIC, dynamicScope);
        final BlockBody body = new CompiledSharedScopeBlock(arity, dynamicScope, callback, hasMultipleArgsHead, argumentType);
        return new Block(body, binding);
    }
    
    private CompiledSharedScopeBlock(final Arity arity, final DynamicScope containingScope, final CompiledBlockCallback callback, final boolean hasMultipleArgsHead, final int argumentType) {
        super(arity, containingScope.getStaticScope(), callback, hasMultipleArgsHead, argumentType);
    }
    
    protected Frame pre(final ThreadContext context, final RubyModule klass, final Binding binding) {
        return context.preForBlock(binding, klass);
    }
    
    public Block cloneBlock(final Binding binding) {
        return new Block(this, binding);
    }
}
