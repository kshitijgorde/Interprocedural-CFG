// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.RubyModule;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.builtin.IRubyObject;

public class CompiledBlockLight19 extends CompiledBlock19
{
    public static Block newCompiledClosureLight(final ThreadContext context, final IRubyObject self, final Arity arity, final StaticScope scope, final CompiledBlockCallback19 callback, final boolean hasMultipleArgsHead, final int argumentType) {
        final Binding binding = context.currentBinding(self, Visibility.PUBLIC);
        final BlockBody body = new CompiledBlockLight19(arity, scope, callback, hasMultipleArgsHead, argumentType, CompiledBlockLight19.EMPTY_PARAMETER_LIST);
        return new Block(body, binding);
    }
    
    public static BlockBody newCompiledBlockLight(final Arity arity, final StaticScope scope, final CompiledBlockCallback19 callback, final boolean hasMultipleArgsHead, final int argumentType, final String[] parameterList) {
        return new CompiledBlockLight19(arity, scope, callback, hasMultipleArgsHead, argumentType, parameterList);
    }
    
    protected CompiledBlockLight19(final Arity arity, final StaticScope scope, final CompiledBlockCallback19 callback, final boolean hasMultipleArgsHead, final int argumentType, final String[] parameterList) {
        super(arity, scope, callback, hasMultipleArgsHead, argumentType, parameterList);
    }
    
    protected Frame pre(final ThreadContext context, final RubyModule klass, final Binding binding) {
        return context.preYieldLightBlock(binding, binding.getDummyScope(this.scope), klass);
    }
    
    protected final void post(final ThreadContext context, final Binding binding, final Visibility vis, final Frame lastFrame) {
        binding.getFrame().setVisibility(vis);
        context.postYieldLight(binding, lastFrame);
    }
}
