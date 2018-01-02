// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import com.kenai.jffi.Function;
import org.jruby.runtime.Arity;
import org.jruby.internal.runtime.methods.DynamicMethod;

abstract class JFFIDynamicMethod extends DynamicMethod
{
    protected final Arity arity;
    protected final Function function;
    protected final FunctionInvoker functionInvoker;
    
    public JFFIDynamicMethod(final RubyModule implementationClass, final Arity arity, final Function function, final FunctionInvoker functionInvoker) {
        super(implementationClass, Visibility.PUBLIC, CallConfiguration.FrameNoneScopeNone);
        this.arity = arity;
        this.function = function;
        this.functionInvoker = functionInvoker;
    }
    
    public final DynamicMethod dup() {
        return this;
    }
    
    public final Arity getArity() {
        return this.arity;
    }
    
    public final boolean isNative() {
        return true;
    }
}
