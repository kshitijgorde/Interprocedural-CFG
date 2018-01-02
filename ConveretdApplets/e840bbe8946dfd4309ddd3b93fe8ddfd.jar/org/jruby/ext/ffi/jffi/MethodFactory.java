// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.jffi.CallingConvention;
import org.jruby.ext.ffi.Type;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;
import org.jruby.Ruby;

public abstract class MethodFactory
{
    public static final DynamicMethod createDynamicMethod(final Ruby runtime, final RubyModule module, final Function function, final Type returnType, final Type[] parameterTypes, final CallingConvention convention, final IRubyObject enums) {
        DynamicMethod dm;
        if (convention == CallingConvention.DEFAULT && FastIntMethodFactory.getFactory().isFastIntMethod(returnType, parameterTypes)) {
            dm = FastIntMethodFactory.getFactory().createMethod(module, function, returnType, parameterTypes, enums);
        }
        else if (convention == CallingConvention.DEFAULT && FastLongMethodFactory.getFactory().isFastLongMethod(returnType, parameterTypes)) {
            dm = FastLongMethodFactory.getFactory().createMethod(module, function, returnType, parameterTypes, enums);
        }
        else {
            dm = DefaultMethodFactory.getFactory().createMethod(module, function, returnType, parameterTypes, convention, enums);
        }
        return dm;
    }
}
