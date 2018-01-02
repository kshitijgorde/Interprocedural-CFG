// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.ext.ffi.NativeType;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jruby.ext.ffi.AllocatedDirectMemoryIO;
import org.jruby.ext.ffi.InvalidMemoryIO;
import java.lang.ref.WeakReference;
import org.jruby.runtime.ThreadContext;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.anno.JRubyClass;
import org.jruby.ext.ffi.AbstractInvoker;
import com.kenai.jffi.CallContext;
import org.jruby.ext.ffi.Platform;
import org.jruby.ext.ffi.NullMemoryIO;
import org.jruby.ext.ffi.MemoryIO;
import org.jruby.ext.ffi.MappedType;
import org.jruby.ext.ffi.ArrayMemoryIO;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.ext.ffi.Struct;
import org.jruby.ext.ffi.StructByValue;
import org.jruby.ext.ffi.Factory;
import org.jruby.RubyProc;
import org.jruby.ext.ffi.Util;
import org.jruby.RubyNumeric;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.Type;
import com.kenai.jffi.CallingConvention;
import com.kenai.jffi.Closure;
import com.kenai.jffi.ClosureManager;
import org.jruby.runtime.Block;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.Map;
import org.jruby.RubyObject;
import org.jruby.ext.ffi.Pointer;
import org.jruby.ext.ffi.CallbackInfo;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;

public class CallbackManager extends org.jruby.ext.ffi.CallbackManager
{
    private static final int LONG_SIZE;
    private static final String CALLBACK_ID = "ffi_callback";
    
    public static final CallbackManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public static RubyClass createCallbackClass(final Ruby runtime, final RubyModule module) {
        final RubyClass cbClass = module.defineClassUnder("Callback", module.fastGetClass("Pointer"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        cbClass.defineAnnotatedMethods(Callback.class);
        cbClass.defineAnnotatedConstants(Callback.class);
        return cbClass;
    }
    
    public final Pointer getCallback(final Ruby runtime, final CallbackInfo cbInfo, final Object proc) {
        return (proc instanceof RubyObject) ? this.getCallback(runtime, cbInfo, (RubyObject)proc) : this.newCallback(runtime, cbInfo, proc);
    }
    
    public final Pointer getCallback(final Ruby runtime, final CallbackInfo cbInfo, final RubyObject proc) {
        if (proc instanceof Function) {
            return (Function)proc;
        }
        synchronized (proc) {
            final Object existing = proc.fastGetInternalVariable("ffi_callback");
            if (existing instanceof Callback && ((Callback)existing).cbInfo == cbInfo) {
                return (Callback)existing;
            }
            if (existing instanceof Map) {
                final Map m = (Map)existing;
                final Callback cb = m.get(proc);
                if (cb != null) {
                    return cb;
                }
            }
            final Callback cb2 = this.newCallback(runtime, cbInfo, proc);
            if (existing == null) {
                proc.fastSetInternalVariable("ffi_callback", cb2);
            }
            else {
                final Map<CallbackInfo, Callback> i = (Map<CallbackInfo, Callback>)((existing instanceof Map) ? ((Map)existing) : Collections.synchronizedMap(new WeakHashMap<CallbackInfo, Callback>()));
                i.put(cbInfo, cb2);
                i.put(((Callback)existing).cbInfo, (Callback)existing);
                proc.fastSetInternalVariable("ffi_callback", i);
            }
            return cb2;
        }
    }
    
    final Callback getCallback(final Ruby runtime, final CallbackInfo cbInfo, final Block proc) {
        return this.newCallback(runtime, cbInfo, proc);
    }
    
    private final Callback newCallback(final Ruby runtime, final CallbackInfo cbInfo, final Object proc) {
        final ClosureInfo info = this.getClosureInfo(runtime, cbInfo);
        final WeakRefCallbackProxy cbProxy = new WeakRefCallbackProxy(runtime, info, proc);
        final Closure.Handle handle = ClosureManager.getInstance().newClosure(cbProxy, info.callContext);
        return new Callback(runtime, handle, cbInfo, info);
    }
    
    private final ClosureInfo getClosureInfo(final Ruby runtime, final CallbackInfo cbInfo) {
        Object info = cbInfo.getProviderCallbackInfo();
        if (info != null && info instanceof ClosureInfo) {
            return (ClosureInfo)info;
        }
        cbInfo.setProviderCallbackInfo(info = this.newClosureInfo(runtime, cbInfo));
        return (ClosureInfo)info;
    }
    
    private final ClosureInfo newClosureInfo(final Ruby runtime, final CallbackInfo cbInfo) {
        return new ClosureInfo(runtime, cbInfo.getReturnType(), cbInfo.getParameterTypes(), cbInfo.isStdcall() ? CallingConvention.STDCALL : CallingConvention.DEFAULT);
    }
    
    final CallbackMemoryIO newClosure(final Ruby runtime, final Type returnType, final Type[] parameterTypes, final Object proc, final CallingConvention convention) {
        final ClosureInfo info = new ClosureInfo(runtime, returnType, parameterTypes, convention);
        final CallbackProxy cbProxy = new CallbackProxy(runtime, info, proc);
        final Closure.Handle handle = ClosureManager.getInstance().newClosure(cbProxy, info.callContext);
        return new CallbackMemoryIO(runtime, handle);
    }
    
    private static final long longValue(final IRubyObject value) {
        if (value instanceof RubyNumeric) {
            return ((RubyNumeric)value).getLongValue();
        }
        if (value.isNil()) {
            return 0L;
        }
        return 0L;
    }
    
    private static final long addressValue(final IRubyObject value) {
        if (value instanceof RubyNumeric) {
            return ((RubyNumeric)value).getLongValue();
        }
        if (value instanceof Pointer) {
            return ((Pointer)value).getAddress();
        }
        if (value.isNil()) {
            return 0L;
        }
        return 0L;
    }
    
    private static final void setReturnValue(final Ruby runtime, final Type type, final Closure.Buffer buffer, final IRubyObject value) {
        if (type instanceof Type.Builtin) {
            switch (type.getNativeType()) {
                case CHAR: {
                    buffer.setByteReturn((byte)longValue(value));
                    break;
                }
                case UCHAR: {
                    buffer.setByteReturn((byte)longValue(value));
                    break;
                }
                case SHORT: {
                    buffer.setShortReturn((short)longValue(value));
                    break;
                }
                case USHORT: {
                    buffer.setShortReturn((short)longValue(value));
                    break;
                }
                case INT: {
                    buffer.setIntReturn((int)longValue(value));
                    break;
                }
                case UINT: {
                    buffer.setIntReturn((int)longValue(value));
                    break;
                }
                case LONG_LONG: {
                    buffer.setLongReturn(Util.int64Value(value));
                    break;
                }
                case ULONG_LONG: {
                    buffer.setLongReturn(Util.uint64Value(value));
                    break;
                }
                case LONG: {
                    if (CallbackManager.LONG_SIZE == 32) {
                        buffer.setIntReturn((int)longValue(value));
                        break;
                    }
                    buffer.setLongReturn(Util.int64Value(value));
                    break;
                }
                case ULONG: {
                    if (CallbackManager.LONG_SIZE == 32) {
                        buffer.setIntReturn((int)longValue(value));
                        break;
                    }
                    buffer.setLongReturn(Util.uint64Value(value));
                    break;
                }
                case FLOAT: {
                    buffer.setFloatReturn((float)RubyNumeric.num2dbl(value));
                    break;
                }
                case DOUBLE: {
                    buffer.setDoubleReturn(RubyNumeric.num2dbl(value));
                    break;
                }
                case POINTER: {
                    buffer.setAddressReturn(addressValue(value));
                    break;
                }
                case BOOL: {
                    buffer.setIntReturn(value.isTrue() ? 1 : 0);
                    break;
                }
            }
        }
        else if (type instanceof CallbackInfo) {
            if (!(value instanceof RubyProc) && !value.respondsTo("call")) {
                buffer.setAddressReturn(0L);
                throw runtime.newTypeError("invalid callback return value, expected Proc or callable object");
            }
            final Pointer cb = Factory.getInstance().getCallbackManager().getCallback(runtime, (CallbackInfo)type, value);
            buffer.setAddressReturn(addressValue(cb));
        }
        else if (type instanceof StructByValue) {
            if (value instanceof Struct) {
                final Struct s = (Struct)value;
                final MemoryIO memory = s.getMemory().getMemoryIO();
                if (memory instanceof DirectMemoryIO) {
                    final long address = ((DirectMemoryIO)memory).getAddress();
                    if (address != 0L) {
                        buffer.setStructReturn(address);
                    }
                    else {
                        buffer.setStructReturn(new byte[type.getNativeSize()], 0);
                    }
                }
                else {
                    if (!(memory instanceof ArrayMemoryIO)) {
                        throw runtime.newRuntimeError("struct return value has illegal backing memory");
                    }
                    final ArrayMemoryIO arrayMemory = (ArrayMemoryIO)memory;
                    if (arrayMemory.arrayLength() < type.getNativeSize()) {
                        throw runtime.newRuntimeError("size of struct returned from callback too small");
                    }
                    buffer.setStructReturn(arrayMemory.array(), arrayMemory.arrayOffset());
                }
            }
            else {
                if (!value.isNil()) {
                    throw runtime.newTypeError(value, runtime.fastGetModule("FFI").fastGetClass("Struct"));
                }
                buffer.setStructReturn(new byte[type.getNativeSize()], 0);
            }
        }
        else {
            if (!(type instanceof MappedType)) {
                buffer.setLongReturn(0L);
                throw runtime.newRuntimeError("unsupported return type from struct: " + type);
            }
            final MappedType mappedType = (MappedType)type;
            setReturnValue(runtime, mappedType.getRealType(), buffer, mappedType.toNative(runtime.getCurrentContext(), value));
        }
    }
    
    private static final IRubyObject fromNative(final Ruby runtime, final Type type, final Closure.Buffer buffer, final int index) {
        if (type instanceof Type.Builtin) {
            switch (type.getNativeType()) {
                case VOID: {
                    return runtime.getNil();
                }
                case CHAR: {
                    return Util.newSigned8(runtime, buffer.getByte(index));
                }
                case UCHAR: {
                    return Util.newUnsigned8(runtime, buffer.getByte(index));
                }
                case SHORT: {
                    return Util.newSigned16(runtime, buffer.getShort(index));
                }
                case USHORT: {
                    return Util.newUnsigned16(runtime, buffer.getShort(index));
                }
                case INT: {
                    return Util.newSigned32(runtime, buffer.getInt(index));
                }
                case UINT: {
                    return Util.newUnsigned32(runtime, buffer.getInt(index));
                }
                case LONG_LONG: {
                    return Util.newSigned64(runtime, buffer.getLong(index));
                }
                case ULONG_LONG: {
                    return Util.newUnsigned64(runtime, buffer.getLong(index));
                }
                case LONG: {
                    return (CallbackManager.LONG_SIZE == 32) ? Util.newSigned32(runtime, buffer.getInt(index)) : Util.newSigned64(runtime, buffer.getLong(index));
                }
                case ULONG: {
                    return (CallbackManager.LONG_SIZE == 32) ? Util.newUnsigned32(runtime, buffer.getInt(index)) : Util.newUnsigned64(runtime, buffer.getLong(index));
                }
                case FLOAT: {
                    return runtime.newFloat(buffer.getFloat(index));
                }
                case DOUBLE: {
                    return runtime.newFloat(buffer.getDouble(index));
                }
                case POINTER: {
                    return new Pointer(runtime, NativeMemoryIO.wrap(runtime, buffer.getAddress(index)));
                }
                case STRING: {
                    return getStringParameter(runtime, buffer, index);
                }
                case BOOL: {
                    return runtime.newBoolean(buffer.getByte(index) != 0);
                }
                default: {
                    throw runtime.newTypeError("invalid callback parameter type " + type);
                }
            }
        }
        else {
            if (type instanceof CallbackInfo) {
                final CallbackInfo cbInfo = (CallbackInfo)type;
                final long address = buffer.getAddress(index);
                return (address != 0L) ? new Function(runtime, cbInfo.getMetaClass(), new CodeMemoryIO(runtime, address), cbInfo.getReturnType(), cbInfo.getParameterTypes(), cbInfo.isStdcall() ? CallingConvention.STDCALL : CallingConvention.DEFAULT, runtime.getNil()) : runtime.getNil();
            }
            if (type instanceof StructByValue) {
                final StructByValue sbv = (StructByValue)type;
                final long address = buffer.getStruct(index);
                final DirectMemoryIO memory = (DirectMemoryIO)((address != 0L) ? new BoundedNativeMemoryIO(runtime, address, type.getNativeSize()) : new NullMemoryIO(runtime));
                return sbv.getStructClass().newInstance(runtime.getCurrentContext(), new IRubyObject[] { new Pointer(runtime, memory) }, Block.NULL_BLOCK);
            }
            if (type instanceof MappedType) {
                final MappedType mappedType = (MappedType)type;
                return mappedType.fromNative(runtime.getCurrentContext(), fromNative(runtime, mappedType.getRealType(), buffer, index));
            }
            throw runtime.newTypeError("unsupported callback parameter type: " + type);
        }
    }
    
    private static final IRubyObject getStringParameter(final Ruby runtime, final Closure.Buffer buffer, final int index) {
        return FFIUtil.getString(runtime, buffer.getAddress(index));
    }
    
    private static final boolean isReturnTypeValid(final Type type) {
        if (type instanceof Type.Builtin) {
            switch (type.getNativeType()) {
                case VOID:
                case CHAR:
                case UCHAR:
                case SHORT:
                case USHORT:
                case INT:
                case UINT:
                case LONG_LONG:
                case ULONG_LONG:
                case LONG:
                case ULONG:
                case FLOAT:
                case DOUBLE:
                case POINTER:
                case BOOL: {
                    return true;
                }
            }
        }
        else {
            if (type instanceof CallbackInfo) {
                return true;
            }
            if (type instanceof StructByValue) {
                return true;
            }
        }
        return false;
    }
    
    private static final boolean isParameterTypeValid(final Type type) {
        if (type instanceof Type.Builtin) {
            switch (type.getNativeType()) {
                case CHAR:
                case UCHAR:
                case SHORT:
                case USHORT:
                case INT:
                case UINT:
                case LONG_LONG:
                case ULONG_LONG:
                case LONG:
                case ULONG:
                case FLOAT:
                case DOUBLE:
                case POINTER:
                case BOOL:
                case STRING: {
                    return true;
                }
            }
        }
        else {
            if (type instanceof CallbackInfo) {
                return true;
            }
            if (type instanceof StructByValue) {
                return true;
            }
            if (type instanceof MappedType) {
                return isParameterTypeValid(((MappedType)type).getRealType());
            }
        }
        return false;
    }
    
    static {
        LONG_SIZE = Platform.getPlatform().longSize();
    }
    
    private static final class SingletonHolder
    {
        static final CallbackManager INSTANCE;
        
        static {
            INSTANCE = new CallbackManager();
        }
    }
    
    private static class ClosureInfo
    {
        final CallingConvention convention;
        final Type returnType;
        final Type[] parameterTypes;
        final com.kenai.jffi.Type ffiReturnType;
        final com.kenai.jffi.Type[] ffiParameterTypes;
        final CallContext callContext;
        
        public ClosureInfo(final Ruby runtime, final Type returnType, final Type[] paramTypes, final CallingConvention convention) {
            this.convention = convention;
            this.ffiParameterTypes = new com.kenai.jffi.Type[paramTypes.length];
            for (int i = 0; i < paramTypes.length; ++i) {
                if (!isParameterTypeValid(paramTypes[i]) || (this.ffiParameterTypes[i] = FFIUtil.getFFIType(paramTypes[i])) == null) {
                    throw runtime.newTypeError("invalid callback parameter type: " + paramTypes[i]);
                }
            }
            this.ffiReturnType = FFIUtil.getFFIType(returnType);
            if (!isReturnTypeValid(returnType) || this.ffiReturnType == null) {
                runtime.newTypeError("invalid callback return type: " + returnType);
            }
            this.callContext = new CallContext(this.ffiReturnType, this.ffiParameterTypes, convention);
            this.returnType = returnType;
            this.parameterTypes = paramTypes.clone();
        }
    }
    
    @JRubyClass(name = { "FFI::Callback" }, parent = "FFI::Pointer")
    static class Callback extends AbstractInvoker
    {
        private final CallbackInfo cbInfo;
        private final ClosureInfo closureInfo;
        
        Callback(final Ruby runtime, final Closure.Handle handle, final CallbackInfo cbInfo, final ClosureInfo closureInfo) {
            super(runtime, runtime.fastGetModule("FFI").fastGetClass("Callback"), cbInfo.getParameterTypes().length, new CallbackMemoryIO(runtime, handle));
            this.cbInfo = cbInfo;
            this.closureInfo = closureInfo;
        }
        
        void dispose() {
            final MemoryIO mem = this.getMemoryIO();
            if (mem instanceof CallbackMemoryIO) {
                ((CallbackMemoryIO)mem).free();
            }
        }
        
        public DynamicMethod createDynamicMethod(final RubyModule module) {
            final com.kenai.jffi.Function function = new com.kenai.jffi.Function(((DirectMemoryIO)this.getMemoryIO()).getAddress(), this.closureInfo.ffiReturnType, this.closureInfo.ffiParameterTypes);
            return MethodFactory.createDynamicMethod(this.getRuntime(), module, function, this.closureInfo.returnType, this.closureInfo.parameterTypes, this.closureInfo.convention, this.getRuntime().getNil());
        }
    }
    
    private abstract static class AbstractCallbackProxy implements Closure
    {
        protected final Ruby runtime;
        protected final ClosureInfo closureInfo;
        
        AbstractCallbackProxy(final Ruby runtime, final ClosureInfo closureInfo) {
            this.runtime = runtime;
            this.closureInfo = closureInfo;
        }
        
        protected final void invoke(final Buffer buffer, final Object recv) {
            final ThreadContext context = this.runtime.getCurrentContext();
            final IRubyObject[] params = new IRubyObject[this.closureInfo.parameterTypes.length];
            for (int i = 0; i < params.length; ++i) {
                params[i] = fromNative(this.runtime, this.closureInfo.parameterTypes[i], buffer, i);
            }
            IRubyObject retVal;
            if (recv instanceof RubyProc) {
                retVal = ((RubyProc)recv).call(context, params);
            }
            else if (recv instanceof Block) {
                retVal = ((Block)recv).call(context, params);
            }
            else {
                retVal = ((IRubyObject)recv).callMethod(context, "call", params);
            }
            setReturnValue(this.runtime, this.closureInfo.returnType, buffer, retVal);
        }
    }
    
    private static final class WeakRefCallbackProxy extends AbstractCallbackProxy implements Closure
    {
        private final WeakReference<Object> proc;
        
        WeakRefCallbackProxy(final Ruby runtime, final ClosureInfo closureInfo, final Object proc) {
            super(runtime, closureInfo);
            this.proc = new WeakReference<Object>(proc);
        }
        
        public void invoke(final Buffer buffer) {
            final Object recv = this.proc.get();
            if (recv == null) {
                buffer.setIntReturn(0);
                return;
            }
            this.invoke(buffer, recv);
        }
    }
    
    private static final class CallbackProxy extends AbstractCallbackProxy implements Closure
    {
        private final Object proc;
        
        CallbackProxy(final Ruby runtime, final ClosureInfo closureInfo, final Object proc) {
            super(runtime, closureInfo);
            this.proc = proc;
        }
        
        public void invoke(final Buffer buffer) {
            this.invoke(buffer, this.proc);
        }
    }
    
    static final class CallbackMemoryIO extends InvalidMemoryIO implements AllocatedDirectMemoryIO
    {
        private final Closure.Handle handle;
        private final AtomicBoolean released;
        
        public CallbackMemoryIO(final Ruby runtime, final Closure.Handle handle) {
            super(runtime);
            this.released = new AtomicBoolean(false);
            this.handle = handle;
        }
        
        public final long getAddress() {
            return this.handle.getAddress();
        }
        
        public final boolean isNull() {
            return false;
        }
        
        public final boolean isDirect() {
            return true;
        }
        
        public void free() {
            if (this.released.getAndSet(true)) {
                throw this.runtime.newRuntimeError("callback already freed");
            }
            this.handle.dispose();
        }
        
        public void setAutoRelease(final boolean autorelease) {
            this.handle.setAutoRelease(autorelease);
        }
    }
}
