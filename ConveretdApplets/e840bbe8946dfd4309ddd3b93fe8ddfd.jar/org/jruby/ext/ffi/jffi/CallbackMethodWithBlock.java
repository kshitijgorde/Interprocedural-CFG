// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.HeapInvocationBuffer;
import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import com.kenai.jffi.Function;
import org.jruby.internal.runtime.methods.DynamicMethod;

final class CallbackMethodWithBlock extends DynamicMethod
{
    private final ParameterMarshaller[] marshallers;
    private final Function function;
    private final FunctionInvoker functionInvoker;
    private final int cbindex;
    
    public CallbackMethodWithBlock(final RubyModule implementationClass, final Function function, final FunctionInvoker functionInvoker, final ParameterMarshaller[] marshallers, final int cbindex) {
        super(implementationClass, Visibility.PUBLIC, CallConfiguration.FrameFullScopeFull);
        this.function = function;
        this.functionInvoker = functionInvoker;
        this.marshallers = marshallers;
        this.cbindex = cbindex;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        final boolean blockGiven = block.isGiven();
        Arity.checkArgumentCount(context.getRuntime(), args, this.marshallers.length - (blockGiven ? 1 : 0), this.marshallers.length);
        final Invocation invocation = new Invocation(context, 0, 0);
        try {
            final HeapInvocationBuffer buffer = new HeapInvocationBuffer(this.function);
            if (!blockGiven) {
                for (int i = 0; i < args.length; ++i) {
                    this.marshallers[i].marshal(invocation, buffer, args[i]);
                }
            }
            else {
                for (int i = 0; i < this.cbindex; ++i) {
                    this.marshallers[i].marshal(invocation, buffer, args[i]);
                }
                ((CallbackMarshaller)this.marshallers[this.cbindex]).marshal(invocation, buffer, block);
                for (int i = this.cbindex + 1; i < this.marshallers.length; ++i) {
                    this.marshallers[i].marshal(invocation, buffer, args[i - 1]);
                }
            }
            return this.functionInvoker.invoke(context, this.function, buffer);
        }
        finally {
            invocation.finish();
        }
    }
    
    public DynamicMethod dup() {
        return this;
    }
    
    public Arity getArity() {
        return Arity.fixed(this.marshallers.length);
    }
    
    public boolean isNative() {
        return true;
    }
}
