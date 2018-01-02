// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.Ruby;
import com.kenai.jffi.CallingConvention;
import org.jruby.ext.ffi.Type;

class NativeFunctionInfo
{
    final Type returnType;
    final Type[] parameterTypes;
    final com.kenai.jffi.Type jffiReturnType;
    final com.kenai.jffi.Type[] jffiParameterTypes;
    final CallingConvention convention;
    
    public NativeFunctionInfo(final Ruby runtime, final Type returnType, final Type[] parameterTypes, final CallingConvention convention) {
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.jffiReturnType = FFIUtil.getFFIType(returnType);
        if (this.jffiReturnType == null) {
            throw runtime.newTypeError("invalid FFI return type: " + returnType);
        }
        this.jffiParameterTypes = new com.kenai.jffi.Type[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; ++i) {
            this.jffiParameterTypes[i] = FFIUtil.getFFIType(parameterTypes[i]);
            if (this.jffiParameterTypes[i] == null) {
                throw runtime.newTypeError("invalid FFI parameter type: " + parameterTypes[i]);
            }
        }
        this.convention = convention;
    }
}
