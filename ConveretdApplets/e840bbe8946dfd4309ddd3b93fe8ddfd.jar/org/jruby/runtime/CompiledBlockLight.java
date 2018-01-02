// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.RubyModule;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.builtin.IRubyObject;

public class CompiledBlockLight extends CompiledBlock
{
    public static Block newCompiledClosureLight(final ThreadContext context, final IRubyObject self, final Arity arity, final StaticScope scope, final CompiledBlockCallback callback, final boolean hasMultipleArgsHead, final int argumentType) {
        final Binding binding = context.currentBinding(self, Visibility.PUBLIC);
        final BlockBody body = new CompiledBlockLight(arity, scope, callback, hasMultipleArgsHead, argumentType);
        return new Block(body, binding);
    }
    
    public static BlockBody newCompiledBlockLight(final Arity arity, final StaticScope scope, final CompiledBlockCallback callback, final boolean hasMultipleArgsHead, final int argumentType) {
        return new CompiledBlockLight(arity, scope, callback, hasMultipleArgsHead, argumentType);
    }
    
    protected CompiledBlockLight(final Arity arity, final StaticScope scope, final CompiledBlockCallback callback, final boolean hasMultipleArgsHead, final int argumentType) {
        super(arity, scope, callback, hasMultipleArgsHead, argumentType);
    }
    
    protected Frame pre(final ThreadContext context, final RubyModule klass, final Binding binding) {
        return context.preYieldLightBlock(binding, binding.getDummyScope(this.scope), klass);
    }
    
    protected final void post(final ThreadContext context, final Binding binding, final Visibility vis, final Frame lastFrame) {
        binding.getFrame().setVisibility(vis);
        context.postYieldLight(binding, lastFrame);
    }
}
