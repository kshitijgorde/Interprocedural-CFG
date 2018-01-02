// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.Block;
import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.HeapInvocationBuffer;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;

final class FastIntPointerMethodOneArg extends FastIntMethod
{
    private final IntParameterConverter c1;
    private final ParameterMarshaller m1;
    
    public FastIntPointerMethodOneArg(final RubyModule implementationClass, final Function function, final IntResultConverter intResultConverter, final IntParameterConverter[] intParameterConverters, final ParameterMarshaller[] marshallers) {
        super(implementationClass, function, intResultConverter, intParameterConverters);
        this.c1 = intParameterConverters[0];
        this.m1 = marshallers[0];
    }
    
    private final IRubyObject invoke(final ThreadContext context, final IRubyObject arg1) {
        if (this.c1.isConvertible(context, arg1)) {
            final int retval = FastIntPointerMethodOneArg.invoker.invokeIrI(this.function, this.c1.intValue(context, arg1));
            return this.resultConverter.fromNative(context, retval);
        }
        final HeapInvocationBuffer buffer = new HeapInvocationBuffer(this.function);
        this.m1.marshal(context, buffer, arg1);
        return this.resultConverter.fromNative(context, FastIntPointerMethodOneArg.invoker.invokeInt(this.function, buffer));
    }
    
    public final IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        this.arity.checkArity(context.getRuntime(), args);
        return this.invoke(context, args[0]);
    }
    
    public final IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1) {
        return this.invoke(context, arg1);
    }
}