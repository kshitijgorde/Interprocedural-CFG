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

final class FastIntPointerMethodThreeArg extends FastIntMethod
{
    private final IntParameterConverter c1;
    private final IntParameterConverter c2;
    private final IntParameterConverter c3;
    private final ParameterMarshaller m1;
    private final ParameterMarshaller m2;
    private final ParameterMarshaller m3;
    
    public FastIntPointerMethodThreeArg(final RubyModule implementationClass, final Function function, final IntResultConverter intResultConverter, final IntParameterConverter[] intParameterConverters, final ParameterMarshaller[] marshallers) {
        super(implementationClass, function, intResultConverter, intParameterConverters);
        this.c1 = intParameterConverters[0];
        this.c2 = intParameterConverters[1];
        this.c3 = intParameterConverters[2];
        this.m1 = marshallers[0];
        this.m2 = marshallers[1];
        this.m3 = marshallers[2];
    }
    
    private final IRubyObject invoke(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        if (this.c1.isConvertible(context, arg1) && this.c2.isConvertible(context, arg2) && this.c3.isConvertible(context, arg3)) {
            final int retval = FastIntPointerMethodThreeArg.invoker.invokeIIIrI(this.function, this.c1.intValue(context, arg1), this.c2.intValue(context, arg2), this.c3.intValue(context, arg3));
            return this.resultConverter.fromNative(context, retval);
        }
        final HeapInvocationBuffer buffer = new HeapInvocationBuffer(this.function);
        this.m1.marshal(context, buffer, arg1);
        this.m2.marshal(context, buffer, arg2);
        this.m3.marshal(context, buffer, arg3);
        return this.resultConverter.fromNative(context, FastIntPointerMethodThreeArg.invoker.invokeInt(this.function, buffer));
    }
    
    public final IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        this.arity.checkArity(context.getRuntime(), args);
        return this.invoke(context, args[0], args[1], args[2]);
    }
    
    public final IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        return this.invoke(context, arg1, arg2, arg3);
    }
}
