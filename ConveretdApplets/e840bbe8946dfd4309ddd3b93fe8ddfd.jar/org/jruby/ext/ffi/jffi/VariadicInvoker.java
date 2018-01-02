// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.Function;
import org.jruby.ext.ffi.NativeType;
import org.jruby.RubyArray;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import com.kenai.jffi.Type;
import org.jruby.ext.ffi.Pointer;
import com.kenai.jffi.CallingConvention;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "FFI::VariadicInvoker" }, parent = "Object")
public class VariadicInvoker extends RubyObject
{
    private final CallingConvention convention;
    private final Pointer address;
    private final FunctionInvoker functionInvoker;
    private final Type returnType;
    
    public static RubyClass createVariadicInvokerClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("VariadicInvoker", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        result.defineAnnotatedMethods(VariadicInvoker.class);
        result.defineAnnotatedConstants(VariadicInvoker.class);
        return result;
    }
    
    private VariadicInvoker(final Ruby runtime, final IRubyObject klazz, final Pointer address, final FunctionInvoker functionInvoker, final Type returnType, final CallingConvention convention) {
        super(runtime, (RubyClass)klazz);
        this.address = address;
        this.functionInvoker = functionInvoker;
        this.returnType = returnType;
        this.convention = convention;
    }
    
    public final Arity getArity() {
        return Arity.OPTIONAL;
    }
    
    @JRubyMethod(name = { "__new" }, meta = true, required = 3, optional = 1)
    public static VariadicInvoker newInvoker(final ThreadContext context, final IRubyObject klass, final IRubyObject[] args) {
        if (!(args[0] instanceof Pointer)) {
            throw context.getRuntime().newTypeError(args[0], context.getRuntime().fastGetModule("FFI").fastGetClass("Pointer"));
        }
        final Pointer address = (Pointer)args[0];
        if (!(args[1] instanceof org.jruby.ext.ffi.Type)) {
            throw context.getRuntime().newTypeError("invalid return type");
        }
        final org.jruby.ext.ffi.Type returnType = (org.jruby.ext.ffi.Type)args[1];
        final CallingConvention conv = "stdcall".equals(args[2].toString()) ? CallingConvention.STDCALL : CallingConvention.DEFAULT;
        final FunctionInvoker functionInvoker = DefaultMethodFactory.getFunctionInvoker(returnType);
        return new VariadicInvoker(context.getRuntime(), klass, address, functionInvoker, FFIUtil.getFFIType(returnType), conv);
    }
    
    @JRubyMethod(name = { "invoke" })
    public IRubyObject invoke(final ThreadContext context, final IRubyObject typesArg, final IRubyObject paramsArg) {
        final IRubyObject[] types = ((RubyArray)typesArg).toJavaArrayMaybeUnsafe();
        final IRubyObject[] params = ((RubyArray)paramsArg).toJavaArrayMaybeUnsafe();
        final Type[] ffiParamTypes = new Type[types.length];
        final ParameterMarshaller[] marshallers = new ParameterMarshaller[types.length];
        for (int i = 0; i < types.length; ++i) {
            final org.jruby.ext.ffi.Type type = (org.jruby.ext.ffi.Type)types[i];
            switch (VariadicInvoker$1.$SwitchMap$org$jruby$ext$ffi$NativeType[NativeType.valueOf(type).ordinal()]) {
                case 0:
                case 1:
                case 2: {
                    ffiParamTypes[i] = Type.SINT32;
                    marshallers[i] = DefaultMethodFactory.getMarshaller(NativeType.INT);
                    break;
                }
                case 3:
                case 4:
                case 5: {
                    ffiParamTypes[i] = Type.UINT32;
                    marshallers[i] = DefaultMethodFactory.getMarshaller(NativeType.UINT);
                    break;
                }
                case 6:
                case 7: {
                    ffiParamTypes[i] = Type.DOUBLE;
                    marshallers[i] = DefaultMethodFactory.getMarshaller(NativeType.DOUBLE);
                    break;
                }
                default: {
                    ffiParamTypes[i] = FFIUtil.getFFIType(type);
                    marshallers[i] = DefaultMethodFactory.getMarshaller((org.jruby.ext.ffi.Type)types[i], CallingConvention.DEFAULT, null);
                    break;
                }
            }
        }
        final Invocation invocation = new Invocation(context);
        final Function function = new Function(this.address.getAddress(), this.returnType, ffiParamTypes, this.convention);
        try {
            final HeapInvocationBuffer args = new HeapInvocationBuffer(function);
            for (int j = 0; j < marshallers.length; ++j) {
                marshallers[j].marshal(invocation, args, params[j]);
            }
            return this.functionInvoker.invoke(context, function, args);
        }
        finally {
            invocation.finish();
            function.dispose();
        }
    }
}
