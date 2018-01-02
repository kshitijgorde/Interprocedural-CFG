// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import com.kenai.jffi.Function;
import org.jruby.runtime.Arity;
import com.kenai.jffi.Invoker;
import org.jruby.internal.runtime.methods.DynamicMethod;

abstract class FastIntMethod extends DynamicMethod
{
    protected static final Invoker invoker;
    protected final Arity arity;
    protected final Function function;
    protected final IntResultConverter resultConverter;
    
    public FastIntMethod(final RubyModule implementationClass, final Function function, final IntResultConverter resultConverter, final IntParameterConverter[] paramConverters) {
        super(implementationClass, Visibility.PUBLIC, CallConfiguration.FrameNoneScopeNone);
        this.resultConverter = resultConverter;
        this.arity = Arity.fixed(paramConverters.length);
        this.function = function;
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
    
    static {
        invoker = Invoker.getInstance();
    }
}
