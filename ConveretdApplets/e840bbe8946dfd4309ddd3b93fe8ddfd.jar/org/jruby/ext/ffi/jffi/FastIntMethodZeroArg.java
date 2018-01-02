// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;

final class FastIntMethodZeroArg extends FastIntMethod
{
    public FastIntMethodZeroArg(final RubyModule implementationClass, final Function function, final IntResultConverter resultConverter, final IntParameterConverter[] parameterConverters) {
        super(implementationClass, function, resultConverter, parameterConverters);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        this.arity.checkArity(context.getRuntime(), args);
        return this.resultConverter.fromNative(context, FastIntMethodZeroArg.invoker.invokeVrI(this.function));
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name) {
        return this.resultConverter.fromNative(context, FastIntMethodZeroArg.invoker.invokeVrI(this.function));
    }
}
