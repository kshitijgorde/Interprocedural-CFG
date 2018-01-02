// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Arity;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;
import com.kenai.jffi.HeapInvocationBuffer;

class DefaultMethodZeroArg extends JFFIDynamicMethod
{
    private final HeapInvocationBuffer dummyBuffer;
    
    public DefaultMethodZeroArg(final RubyModule implementationClass, final Function function, final FunctionInvoker functionInvoker) {
        super(implementationClass, Arity.NO_ARGUMENTS, function, functionInvoker);
        this.dummyBuffer = new HeapInvocationBuffer(function);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        this.arity.checkArity(context.getRuntime(), args);
        return this.functionInvoker.invoke(context, this.function, this.dummyBuffer);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name) {
        return this.functionInvoker.invoke(context, this.function, this.dummyBuffer);
    }
}
