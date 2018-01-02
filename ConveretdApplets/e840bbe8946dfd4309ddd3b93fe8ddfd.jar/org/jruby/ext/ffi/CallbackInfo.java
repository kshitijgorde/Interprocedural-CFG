// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.anno.JRubyMethod;
import org.jruby.RubyHash;
import org.jruby.RubyArray;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.runtime.Arity;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "FFI::CallbackInfo" }, parent = "FFI::Type")
public class CallbackInfo extends Type implements NativeParam
{
    public static final String CLASS_NAME = "CallbackInfo";
    protected final Arity arity;
    protected final Type[] parameterTypes;
    protected final Type returnType;
    protected final boolean stdcall;
    private volatile Object providerCallbackInfo;
    
    public static RubyClass createCallbackInfoClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("CallbackInfo", module.fastGetClass("Type"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        result.defineAnnotatedMethods(CallbackInfo.class);
        result.defineAnnotatedConstants(CallbackInfo.class);
        module.fastGetClass("Type").fastSetConstant("Function", result);
        return result;
    }
    
    public CallbackInfo(final Ruby runtime, final RubyClass klazz, final Type returnType, final Type[] paramTypes, final boolean stdcall) {
        super(runtime, klazz, NativeType.POINTER);
        this.arity = Arity.fixed(paramTypes.length);
        this.parameterTypes = paramTypes;
        this.returnType = returnType;
        this.stdcall = stdcall;
    }
    
    @JRubyMethod(name = { "new" }, meta = true, required = 2, optional = 1)
    public static final IRubyObject newCallbackInfo(final ThreadContext context, final IRubyObject klass, final IRubyObject[] args) {
        final IRubyObject returnType = args[0];
        final IRubyObject paramTypes = args[1];
        if (!(returnType instanceof Type)) {
            throw context.getRuntime().newTypeError("wrong argument type " + returnType.getMetaClass().getName() + " (expected FFI::Type)");
        }
        if (!(paramTypes instanceof RubyArray)) {
            throw context.getRuntime().newTypeError("wrong argument type " + paramTypes.getMetaClass().getName() + " (expected Array)");
        }
        final Type[] nativeParamTypes = new Type[((RubyArray)paramTypes).size()];
        for (int i = 0; i < nativeParamTypes.length; ++i) {
            final IRubyObject obj = ((RubyArray)paramTypes).entry(i);
            if (!(obj instanceof Type)) {
                throw context.getRuntime().newTypeError("wrong argument type " + obj.getMetaClass().getName() + " (expected array of FFI::Type)");
            }
            nativeParamTypes[i] = (Type)obj;
        }
        boolean stdcall = false;
        if (args.length > 2) {
            if (!(args[2] instanceof RubyHash)) {
                throw context.getRuntime().newTypeError("wrong argument type " + args[3].getMetaClass().getName() + " (expected Hash)");
            }
            final RubyHash hash = (RubyHash)args[2];
            stdcall = "stdcall".equals(hash.get(context.getRuntime().newSymbol("convention")));
        }
        try {
            return new CallbackInfo(context.getRuntime(), (RubyClass)klass, (Type)returnType, nativeParamTypes, stdcall);
        }
        catch (UnsatisfiedLinkError ex) {
            return context.getRuntime().getNil();
        }
    }
    
    public final Arity getArity() {
        return this.arity;
    }
    
    public final Type getReturnType() {
        return this.returnType;
    }
    
    public final Type[] getParameterTypes() {
        return this.parameterTypes;
    }
    
    public final boolean isStdcall() {
        return this.stdcall;
    }
    
    public final Object getProviderCallbackInfo() {
        return this.providerCallbackInfo;
    }
    
    public final void setProviderCallbackInfo(final Object info) {
        this.providerCallbackInfo = info;
    }
    
    @JRubyMethod(name = { "to_s" })
    public final IRubyObject to_s(final ThreadContext context) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#<FFI::CallbackInfo [ ");
        for (int i = 0; i < this.parameterTypes.length; ++i) {
            sb.append(this.parameterTypes[i].toString().toLowerCase());
            if (i < this.parameterTypes.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(" ], " + this.returnType.toString().toLowerCase() + ">");
        return context.getRuntime().newString(sb.toString());
    }
    
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CallbackInfo[parameters=[");
        for (int i = 0; i < this.parameterTypes.length; ++i) {
            sb.append(this.parameterTypes[i].toString().toLowerCase());
            if (i < this.parameterTypes.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("] return=" + this.returnType.toString().toLowerCase() + "]");
        return sb.toString();
    }
    
    @JRubyMethod
    public final IRubyObject result_type(final ThreadContext context) {
        return this.returnType;
    }
    
    @JRubyMethod
    public final IRubyObject param_types(final ThreadContext context) {
        return RubyArray.newArray(context.getRuntime(), this.parameterTypes);
    }
}
