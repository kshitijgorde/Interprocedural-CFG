// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.ext.ffi.MemoryIO;
import org.jruby.ext.ffi.FreedMemoryIO;
import org.jruby.ext.ffi.AllocatedDirectMemoryIO;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyHash;
import org.jruby.RubyProc;
import org.jruby.ext.ffi.Pointer;
import org.jruby.RubyArray;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import com.kenai.jffi.CallingConvention;
import org.jruby.ext.ffi.Type;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;
import org.jruby.ext.ffi.AbstractInvoker;

@JRubyClass(name = { "FFI::Function" }, parent = "FFI::Pointer")
public final class Function extends AbstractInvoker
{
    private final com.kenai.jffi.Function function;
    private final NativeFunctionInfo functionInfo;
    private final IRubyObject enums;
    private volatile boolean autorelease;
    
    public static RubyClass createFunctionClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("Function", module.fastGetClass("Pointer"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        result.defineAnnotatedMethods(AbstractInvoker.class);
        result.defineAnnotatedMethods(Function.class);
        result.defineAnnotatedConstants(Function.class);
        return result;
    }
    
    Function(final Ruby runtime, final RubyClass klass, final DirectMemoryIO address, final Type returnType, final Type[] parameterTypes, final CallingConvention convention, final IRubyObject enums) {
        super(runtime, klass, parameterTypes.length, address);
        this.autorelease = true;
        this.functionInfo = new NativeFunctionInfo(runtime, returnType, parameterTypes, convention);
        this.function = new com.kenai.jffi.Function(address.getAddress(), this.functionInfo.jffiReturnType, this.functionInfo.jffiParameterTypes, this.functionInfo.convention);
        this.enums = enums;
        this.getSingletonClass().addMethod("call", this.createDynamicMethod(this.getSingletonClass()));
    }
    
    Function(final Ruby runtime, final RubyClass klass, final DirectMemoryIO address, final NativeFunctionInfo functionInfo, final IRubyObject enums) {
        super(runtime, klass, functionInfo.parameterTypes.length, address);
        this.autorelease = true;
        this.functionInfo = functionInfo;
        this.function = new com.kenai.jffi.Function(address.getAddress(), functionInfo.jffiReturnType, functionInfo.jffiParameterTypes, functionInfo.convention);
        this.enums = enums;
        this.getSingletonClass().addMethod("call", this.createDynamicMethod(this.getSingletonClass()));
    }
    
    @JRubyMethod(name = { "new" }, meta = true, required = 2, optional = 2)
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        DirectMemoryIO fptr = null;
        RubyHash options = null;
        Object proc = null;
        int optionsIndex = 2;
        final Type returnType = FFIUtil.resolveType(context, args[0]);
        if (!(args[1] instanceof RubyArray)) {
            throw context.getRuntime().newTypeError("Invalid parameter array " + args[1].getMetaClass().getName() + " (expected Array)");
        }
        final RubyArray paramTypes = (RubyArray)args[1];
        final Type[] parameterTypes = new Type[paramTypes.size()];
        for (int i = 0; i < parameterTypes.length; ++i) {
            parameterTypes[i] = FFIUtil.resolveType(context, paramTypes.entry(i));
        }
        if (args.length > 2 && args[2] instanceof Pointer) {
            fptr = new CodeMemoryIO(context.getRuntime(), (Pointer)args[2]);
            optionsIndex = 3;
        }
        else if (args.length > 2 && (args[2] instanceof RubyProc || args[2].respondsTo("call"))) {
            proc = args[2];
            optionsIndex = 3;
        }
        else {
            if (!block.isGiven()) {
                throw context.getRuntime().newTypeError("Invalid function address " + args[0].getMetaClass().getName() + " (expected FFI::Pointer)");
            }
            proc = block;
            optionsIndex = 2;
        }
        String convention = "default";
        IRubyObject enums = null;
        if (args.length > optionsIndex && args[optionsIndex] instanceof RubyHash) {
            options = (RubyHash)args[optionsIndex];
            final IRubyObject rbConvention = options.fastARef(context.getRuntime().newSymbol("convention"));
            if (rbConvention != null && !rbConvention.isNil()) {
                convention = rbConvention.asJavaString();
            }
            enums = options.fastARef(context.getRuntime().newSymbol("enums"));
            if (enums != null && !enums.isNil() && !(enums instanceof RubyHash)) {
                throw context.getRuntime().newTypeError("wrong type for options[:enum] " + enums.getMetaClass().getName() + " (expected Hash)");
            }
        }
        final CallingConvention callConvention = "stdcall".equals(convention) ? CallingConvention.STDCALL : CallingConvention.DEFAULT;
        if (fptr == null && proc != null) {
            fptr = CallbackManager.getInstance().newClosure(context.getRuntime(), returnType, parameterTypes, proc, callConvention);
        }
        return new Function(context.getRuntime(), (RubyClass)recv, fptr, returnType, parameterTypes, callConvention, enums);
    }
    
    @JRubyMethod(name = { "free" })
    public final IRubyObject free(final ThreadContext context) {
        if (this.getMemoryIO() instanceof AllocatedDirectMemoryIO) {
            ((AllocatedDirectMemoryIO)this.getMemoryIO()).free();
            this.setMemoryIO(new FreedMemoryIO(context.getRuntime()));
            return context.getRuntime().getNil();
        }
        throw context.getRuntime().newRuntimeError("cannot free non-allocated function");
    }
    
    @JRubyMethod(name = { "autorelease=" }, required = 1)
    public final IRubyObject autorelease(final ThreadContext context, final IRubyObject release) {
        if (this.autorelease != release.isTrue() && this.getMemoryIO() instanceof AllocatedDirectMemoryIO) {
            ((AllocatedDirectMemoryIO)this.getMemoryIO()).setAutoRelease(this.autorelease = release.isTrue());
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "autorelease?", "autorelease" })
    public final IRubyObject autorelease_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.autorelease);
    }
    
    public DynamicMethod createDynamicMethod(final RubyModule module) {
        return MethodFactory.createDynamicMethod(this.getRuntime(), module, this.function, this.functionInfo.returnType, this.functionInfo.parameterTypes, this.functionInfo.convention, this.enums);
    }
}
