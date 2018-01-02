// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.HeapInvocationBuffer;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Arity;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;

class DefaultMethod extends JFFIDynamicMethod
{
    private final ParameterMarshaller[] marshallers;
    protected final boolean needsInvocationSession;
    protected final int postInvokeCount;
    protected final int referenceCount;
    
    public DefaultMethod(final RubyModule implementationClass, final Function function, final FunctionInvoker functionInvoker, final ParameterMarshaller[] marshallers) {
        super(implementationClass, Arity.fixed(marshallers.length), function, functionInvoker);
        this.marshallers = marshallers;
        int piCount = 0;
        int refCount = 0;
        for (final ParameterMarshaller m : marshallers) {
            if (m.requiresPostInvoke()) {
                ++piCount;
            }
            if (m.requiresReference()) {
                ++refCount;
            }
        }
        this.postInvokeCount = piCount;
        this.referenceCount = refCount;
        this.needsInvocationSession = (piCount > 0 || refCount > 0);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        this.arity.checkArity(context.getRuntime(), args);
        final HeapInvocationBuffer buffer = new HeapInvocationBuffer(this.function);
        if (this.needsInvocationSession) {
            final Invocation invocation = new Invocation(context, this.postInvokeCount, this.referenceCount);
            try {
                for (int i = 0; i < args.length; ++i) {
                    this.marshallers[i].marshal(invocation, buffer, args[i]);
                }
                return this.functionInvoker.invoke(context, this.function, buffer);
            }
            finally {
                invocation.finish();
            }
        }
        for (int j = 0; j < args.length; ++j) {
            this.marshallers[j].marshal(context, buffer, args[j]);
        }
        return this.functionInvoker.invoke(context, this.function, buffer);
    }
}
