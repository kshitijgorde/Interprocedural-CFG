// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.HeapInvocationBuffer;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;

final class DefaultMethodOneArg extends DefaultMethod
{
    private final ParameterMarshaller m1;
    
    public DefaultMethodOneArg(final RubyModule implementationClass, final Function function, final FunctionInvoker functionInvoker, final ParameterMarshaller[] marshallers) {
        super(implementationClass, function, functionInvoker, marshallers);
        this.m1 = marshallers[0];
    }
    
    public final IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1) {
        final HeapInvocationBuffer buffer = new HeapInvocationBuffer(this.function);
        if (this.needsInvocationSession) {
            final Invocation invocation = new Invocation(context, this.postInvokeCount, this.referenceCount);
            try {
                this.m1.marshal(invocation, buffer, arg1);
                return this.functionInvoker.invoke(context, this.function, buffer);
            }
            finally {
                invocation.finish();
            }
        }
        this.m1.marshal(context, buffer, arg1);
        return this.functionInvoker.invoke(context, this.function, buffer);
    }
}
