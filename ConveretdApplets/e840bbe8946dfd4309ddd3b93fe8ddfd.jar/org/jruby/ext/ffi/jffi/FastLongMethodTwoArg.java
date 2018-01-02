// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;

final class FastLongMethodTwoArg extends FastLongMethod
{
    private final LongParameterConverter c1;
    private final LongParameterConverter c2;
    
    public FastLongMethodTwoArg(final RubyModule implementationClass, final Function function, final LongResultConverter resultConverter, final LongParameterConverter[] parameterConverters) {
        super(implementationClass, function, resultConverter, parameterConverters);
        this.c1 = parameterConverters[0];
        this.c2 = parameterConverters[1];
    }
    
    private final IRubyObject invoke(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2) {
        final long retval = FastLongMethodTwoArg.invoker.invokeLLrL(this.function, this.c1.longValue(context, arg1), this.c2.longValue(context, arg2));
        return this.resultConverter.fromNative(context, retval);
    }
    
    public final IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        this.arity.checkArity(context.getRuntime(), args);
        return this.invoke(context, args[0], args[1]);
    }
    
    public final IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2) {
        return this.invoke(context, arg1, arg2);
    }
}
