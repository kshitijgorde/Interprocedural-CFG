// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;

final class FastLongMethodZeroArg extends FastLongMethod
{
    public FastLongMethodZeroArg(final RubyModule implementationClass, final Function function, final LongResultConverter resultConverter, final LongParameterConverter[] parameterConverters) {
        super(implementationClass, function, resultConverter, parameterConverters);
    }
    
    public final IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        this.arity.checkArity(context.getRuntime(), args);
        return this.resultConverter.fromNative(context, FastLongMethodZeroArg.invoker.invokeVrL(this.function));
    }
    
    public final IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name) {
        return this.resultConverter.fromNative(context, FastLongMethodZeroArg.invoker.invokeVrL(this.function));
    }
}
