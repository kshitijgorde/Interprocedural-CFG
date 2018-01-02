// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.anno.JRubyMethod;
import com.kenai.jffi.LastError;
import org.jruby.runtime.ThreadContext;
import org.jruby.ext.ffi.AbstractInvoker;
import com.kenai.jffi.Type;
import org.jruby.ext.ffi.NativeType;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.jffi.CallingConvention;
import org.jruby.ext.ffi.CallbackInfo;
import org.jruby.ext.ffi.Pointer;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.ext.ffi.AllocatedDirectMemoryIO;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import com.kenai.jffi.Platform;

public class Factory extends org.jruby.ext.ffi.Factory
{
    public Factory() {
        if (!Platform.getPlatform().isSupported()) {
            throw new UnsatisfiedLinkError("JFFI backend not available");
        }
    }
    
    public void init(final Ruby runtime, final RubyModule ffi) {
        super.init(runtime, ffi);
        synchronized (ffi) {
            if (ffi.fastGetClass("DynamicLibrary") == null) {
                DynamicLibrary.createDynamicLibraryClass(runtime, ffi);
            }
            if (ffi.fastGetClass("Invoker") == null) {
                JFFIInvoker.createInvokerClass(runtime, ffi);
            }
            if (ffi.fastGetClass("VariadicInvoker") == null) {
                VariadicInvoker.createVariadicInvokerClass(runtime, ffi);
            }
            if (ffi.fastGetClass("Callback") == null) {
                CallbackManager.createCallbackClass(runtime, ffi);
            }
            if (ffi.fastGetClass("Function") == null) {
                Function.createFunctionClass(runtime, ffi);
            }
            if (ffi.fastGetClass("LastError") == null) {
                ffi.defineModuleUnder("LastError").defineAnnotatedMethods(LastError.class);
            }
        }
    }
    
    public AllocatedDirectMemoryIO allocateDirectMemory(final Ruby runtime, final int size, final boolean clear) {
        return AllocatedNativeMemoryIO.allocate(runtime, size, clear);
    }
    
    public AllocatedDirectMemoryIO allocateDirectMemory(final Ruby runtime, final int size, final int align, final boolean clear) {
        return AllocatedNativeMemoryIO.allocateAligned(runtime, size, align, clear);
    }
    
    public DirectMemoryIO wrapDirectMemory(final Ruby runtime, final long address) {
        return NativeMemoryIO.wrap(runtime, address);
    }
    
    public Function newFunction(final Ruby runtime, final Pointer address, final CallbackInfo cbInfo) {
        final CodeMemoryIO mem = new CodeMemoryIO(runtime, address);
        final RubyClass klass = runtime.fastGetModule("FFI").fastGetClass("Function");
        return new Function(runtime, klass, mem, cbInfo.getReturnType(), cbInfo.getParameterTypes(), cbInfo.isStdcall() ? CallingConvention.STDCALL : CallingConvention.DEFAULT, null);
    }
    
    public CallbackManager getCallbackManager() {
        return CallbackManager.getInstance();
    }
    
    private static final Type getType(final NativeType type) {
        final Type jffiType = FFIUtil.getFFIType(type);
        if (jffiType == null) {
            throw new UnsupportedOperationException("Cannot determine native type for " + type);
        }
        return jffiType;
    }
    
    public int sizeOf(final NativeType type) {
        return getType(type).size();
    }
    
    public int alignmentOf(final NativeType type) {
        return getType(type).alignment();
    }
    
    public static final class LastError
    {
        @JRubyMethod(name = { "error" }, module = true)
        public static final IRubyObject error(final ThreadContext context, final IRubyObject recv) {
            return context.getRuntime().newFixnum(com.kenai.jffi.LastError.getInstance().get());
        }
        
        @JRubyMethod(name = { "error=" }, module = true)
        public static final IRubyObject error_set(final ThreadContext context, final IRubyObject recv, final IRubyObject value) {
            com.kenai.jffi.LastError.getInstance().set((int)value.convertToInteger().getLongValue());
            return value;
        }
    }
}
