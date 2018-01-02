// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyHash;
import org.jruby.RubyArray;
import org.jruby.ext.ffi.Pointer;
import org.jruby.runtime.ThreadContext;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.jffi.CallingConvention;
import org.jruby.ext.ffi.Type;
import com.kenai.jffi.Function;
import org.jruby.ext.ffi.AbstractInvoker;

public class JFFIInvoker extends AbstractInvoker
{
    private final Function function;
    private final Type returnType;
    private final Type[] parameterTypes;
    private final CallingConvention convention;
    private final IRubyObject enums;
    
    public static RubyClass createInvokerClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("Invoker", module.fastGetClass("AbstractInvoker"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        result.defineAnnotatedMethods(AbstractInvoker.class);
        result.defineAnnotatedMethods(JFFIInvoker.class);
        result.defineAnnotatedConstants(JFFIInvoker.class);
        return result;
    }
    
    JFFIInvoker(final Ruby runtime, final long address, final Type returnType, final Type[] parameterTypes, final CallingConvention convention) {
        this(runtime, runtime.fastGetModule("FFI").fastGetClass("Invoker"), new CodeMemoryIO(runtime, address), returnType, parameterTypes, convention, null);
    }
    
    JFFIInvoker(final Ruby runtime, final RubyClass klass, final DirectMemoryIO fptr, final Type returnType, final Type[] parameterTypes, final CallingConvention convention, final IRubyObject enums) {
        super(runtime, klass, parameterTypes.length, fptr);
        final com.kenai.jffi.Type jffiReturnType = FFIUtil.getFFIType(returnType);
        if (jffiReturnType == null) {
            throw runtime.newArgumentError("Invalid return type " + returnType);
        }
        final com.kenai.jffi.Type[] jffiParamTypes = new com.kenai.jffi.Type[parameterTypes.length];
        for (int i = 0; i < jffiParamTypes.length; ++i) {
            if ((jffiParamTypes[i] = FFIUtil.getFFIType(parameterTypes[i])) == null) {
                throw runtime.newArgumentError("Invalid parameter type " + parameterTypes[i]);
            }
        }
        this.function = new Function(fptr.getAddress(), jffiReturnType, jffiParamTypes);
        this.parameterTypes = parameterTypes.clone();
        this.returnType = returnType;
        this.convention = convention;
        this.enums = enums;
        this.getSingletonClass().addMethod("call", this.createDynamicMethod(this.getSingletonClass()));
    }
    
    @JRubyMethod(name = { "new" }, meta = true, required = 4)
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        if (!(args[0] instanceof Pointer)) {
            throw context.getRuntime().newTypeError("Invalid function address " + args[0].getMetaClass().getName() + " (expected FFI::Pointer)");
        }
        if (!(args[1] instanceof RubyArray)) {
            throw context.getRuntime().newTypeError("Invalid parameter array " + args[1].getMetaClass().getName() + " (expected Array)");
        }
        if (!(args[2] instanceof Type)) {
            throw context.getRuntime().newTypeError("Invalid return type " + args[2]);
        }
        final Pointer ptr = (Pointer)args[0];
        final RubyArray paramTypes = (RubyArray)args[1];
        final Type returnType = (Type)args[2];
        String convention = "default";
        IRubyObject enums = null;
        if (args[3] instanceof RubyHash) {
            final RubyHash options = (RubyHash)args[3];
            convention = options.fastARef(context.getRuntime().newSymbol("convention")).asJavaString();
            enums = options.fastARef(context.getRuntime().newSymbol("enums"));
            if (enums != null && !enums.isNil() && !(enums instanceof RubyHash)) {
                throw context.getRuntime().newTypeError("wrong type for options[:enum] " + enums.getMetaClass().getName() + " (expected Hash)");
            }
        }
        else {
            convention = args[3].asJavaString();
        }
        final Type[] parameterTypes = new Type[paramTypes.size()];
        for (int i = 0; i < parameterTypes.length; ++i) {
            final IRubyObject type = paramTypes.entry(i);
            if (!(type instanceof Type)) {
                throw context.getRuntime().newArgumentError("Invalid parameter type");
            }
            parameterTypes[i] = (Type)paramTypes.entry(i);
        }
        final DirectMemoryIO fptr = (DirectMemoryIO)ptr.getMemoryIO();
        return new JFFIInvoker(context.getRuntime(), (RubyClass)recv, fptr, returnType, parameterTypes, "stdcall".equals(convention) ? CallingConvention.STDCALL : CallingConvention.DEFAULT, enums);
    }
    
    public DynamicMethod createDynamicMethod(final RubyModule module) {
        return MethodFactory.createDynamicMethod(this.getRuntime(), module, this.function, this.returnType, this.parameterTypes, this.convention, this.enums);
    }
}
