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

abstract class FastLongMethod extends DynamicMethod
{
    protected static final Invoker invoker;
    protected final Arity arity;
    protected final Function function;
    protected final LongResultConverter resultConverter;
    
    public FastLongMethod(final RubyModule implementationClass, final Function function, final LongResultConverter resultConverter, final LongParameterConverter[] paramConverters) {
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
