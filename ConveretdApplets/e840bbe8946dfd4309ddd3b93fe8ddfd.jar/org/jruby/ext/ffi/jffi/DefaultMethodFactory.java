// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.ext.ffi.MemoryIO;
import org.jruby.ext.ffi.AbstractMemory;
import org.jruby.ext.ffi.StructLayout;
import org.jruby.util.ByteList;
import org.jruby.RubyString;
import org.jruby.ext.ffi.Struct;
import org.jruby.ext.ffi.ArrayMemoryIO;
import org.jruby.RubyNumeric;
import org.jruby.RubyBoolean;
import com.kenai.jffi.InvocationBuffer;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.runtime.Block;
import org.jruby.ext.ffi.Buffer;
import org.jruby.ext.ffi.Pointer;
import org.jruby.ext.ffi.Util;
import com.kenai.jffi.HeapInvocationBuffer;
import org.jruby.runtime.ThreadContext;
import com.kenai.jffi.Invoker;
import org.jruby.RubyHash;
import org.jruby.ext.ffi.NativeType;
import org.jruby.ext.ffi.StructByValue;
import org.jruby.ext.ffi.CallbackInfo;
import org.jruby.ext.ffi.Platform;
import org.jruby.ext.ffi.MappedType;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.jffi.CallingConvention;
import org.jruby.ext.ffi.Type;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;

public final class DefaultMethodFactory
{
    public static final DefaultMethodFactory getFactory() {
        return SingletonHolder.INSTANCE;
    }
    
    DynamicMethod createMethod(final RubyModule module, final Function function, final Type returnType, final Type[] parameterTypes, final CallingConvention convention, final IRubyObject enums) {
        final FunctionInvoker functionInvoker = getFunctionInvoker(returnType);
        final ParameterMarshaller[] marshallers = new ParameterMarshaller[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; ++i) {
            marshallers[i] = getMarshaller(parameterTypes[i], convention, enums);
            if (marshallers[i] == null) {
                throw module.getRuntime().newTypeError("Could not create marshaller for " + parameterTypes[i]);
            }
        }
        if (marshallers.length > 0) {
            int cbcount = 0;
            int cbindex = -1;
            for (int j = 0; j < marshallers.length; ++j) {
                if (marshallers[j] instanceof CallbackMarshaller) {
                    ++cbcount;
                    cbindex = j;
                }
            }
            if (cbcount == 1) {
                return new CallbackMethodWithBlock(module, function, functionInvoker, marshallers, cbindex);
            }
        }
        final FastIntMethodFactory fastIntFactory = FastIntMethodFactory.getFactory();
        boolean canBeFastInt = parameterTypes.length <= 3 && fastIntFactory.isFastIntResult(returnType) && convention == CallingConvention.DEFAULT;
        for (int j = 0; canBeFastInt && j < parameterTypes.length; ++j) {
            final Type t = (parameterTypes[j] instanceof MappedType) ? ((MappedType)parameterTypes[j]).getRealType() : parameterTypes[j];
            if (!(t instanceof Type.Builtin) || marshallers[j].requiresPostInvoke()) {
                canBeFastInt = false;
            }
            else {
                switch (t.getNativeType()) {
                    case POINTER:
                    case BUFFER_IN:
                    case BUFFER_OUT:
                    case BUFFER_INOUT: {
                        canBeFastInt = (Platform.getPlatform().addressSize() == 32);
                        break;
                    }
                    default: {
                        canBeFastInt = fastIntFactory.isFastIntParam(parameterTypes[j]);
                        break;
                    }
                }
            }
        }
        if (!canBeFastInt) {
            switch (parameterTypes.length) {
                case 0: {
                    return new DefaultMethodZeroArg(module, function, functionInvoker);
                }
                case 1: {
                    return new DefaultMethodOneArg(module, function, functionInvoker, marshallers);
                }
                case 2: {
                    return new DefaultMethodTwoArg(module, function, functionInvoker, marshallers);
                }
                case 3: {
                    return new DefaultMethodThreeArg(module, function, functionInvoker, marshallers);
                }
                default: {
                    return new DefaultMethod(module, function, functionInvoker, marshallers);
                }
            }
        }
        else {
            final IntResultConverter resultConverter = fastIntFactory.getIntResultConverter(returnType);
            final IntParameterConverter[] intParameterConverters = new IntParameterConverter[parameterTypes.length];
            for (int k = 0; k < parameterTypes.length; ++k) {
                intParameterConverters[k] = fastIntFactory.getIntParameterConverter(parameterTypes[k], enums);
            }
            switch (parameterTypes.length) {
                case 0: {
                    return new FastIntMethodZeroArg(module, function, resultConverter, intParameterConverters);
                }
                case 1: {
                    return new FastIntPointerMethodOneArg(module, function, resultConverter, intParameterConverters, marshallers);
                }
                case 2: {
                    return new FastIntPointerMethodTwoArg(module, function, resultConverter, intParameterConverters, marshallers);
                }
                case 3: {
                    return new FastIntPointerMethodThreeArg(module, function, resultConverter, intParameterConverters, marshallers);
                }
                default: {
                    throw new IllegalArgumentException("Parameter types not supported");
                }
            }
        }
    }
    
    static FunctionInvoker getFunctionInvoker(final Type returnType) {
        if (returnType instanceof Type.Builtin) {
            return getFunctionInvoker(returnType.getNativeType());
        }
        if (returnType instanceof CallbackInfo) {
            return new CallbackInvoker((CallbackInfo)returnType);
        }
        if (returnType instanceof StructByValue) {
            return new StructByValueInvoker((StructByValue)returnType);
        }
        if (returnType instanceof MappedType) {
            final MappedType ctype = (MappedType)returnType;
            return new MappedTypeInvoker(getFunctionInvoker(ctype.getRealType()), ctype);
        }
        throw returnType.getRuntime().newArgumentError("Cannot get FunctionInvoker for " + returnType);
    }
    
    static FunctionInvoker getFunctionInvoker(final NativeType returnType) {
        switch (returnType) {
            case VOID: {
                return VoidInvoker.INSTANCE;
            }
            case BOOL: {
                return BooleanInvoker.INSTANCE;
            }
            case POINTER: {
                return PointerInvoker.INSTANCE;
            }
            case CHAR: {
                return Signed8Invoker.INSTANCE;
            }
            case SHORT: {
                return Signed16Invoker.INSTANCE;
            }
            case INT: {
                return Signed32Invoker.INSTANCE;
            }
            case UCHAR: {
                return Unsigned8Invoker.INSTANCE;
            }
            case USHORT: {
                return Unsigned16Invoker.INSTANCE;
            }
            case UINT: {
                return Unsigned32Invoker.INSTANCE;
            }
            case LONG_LONG: {
                return Signed64Invoker.INSTANCE;
            }
            case ULONG_LONG: {
                return Unsigned64Invoker.INSTANCE;
            }
            case LONG: {
                return (Platform.getPlatform().longSize() == 32) ? Signed32Invoker.INSTANCE : Signed64Invoker.INSTANCE;
            }
            case ULONG: {
                return (Platform.getPlatform().longSize() == 32) ? Unsigned32Invoker.INSTANCE : Unsigned64Invoker.INSTANCE;
            }
            case FLOAT: {
                return Float32Invoker.INSTANCE;
            }
            case DOUBLE: {
                return Float64Invoker.INSTANCE;
            }
            case STRING: {
                return StringInvoker.INSTANCE;
            }
            default: {
                throw new IllegalArgumentException("Invalid return type: " + returnType);
            }
        }
    }
    
    static final ParameterMarshaller getMarshaller(final Type type, final CallingConvention convention, final IRubyObject enums) {
        if (type instanceof Type.Builtin) {
            return (enums != null && !enums.isNil()) ? getEnumMarshaller(type, enums) : getMarshaller(type.getNativeType());
        }
        if (type instanceof CallbackInfo) {
            return new CallbackMarshaller((CallbackInfo)type, convention);
        }
        if (type instanceof StructByValue) {
            return new StructByValueMarshaller((StructByValue)type);
        }
        if (type instanceof MappedType) {
            final MappedType ctype = (MappedType)type;
            return new MappedTypeMarshaller(getMarshaller(ctype.getRealType(), convention, enums), ctype);
        }
        return null;
    }
    
    static final ParameterMarshaller getEnumMarshaller(final Type type, final IRubyObject enums) {
        switch (type.getNativeType()) {
            case CHAR:
            case SHORT:
            case INT:
            case UCHAR:
            case USHORT:
            case UINT: {
                if (!(enums instanceof RubyHash)) {
                    throw type.getRuntime().newArgumentError("wrong argument type " + enums.getMetaClass().getName() + " (expected Hash)");
                }
                return new IntOrEnumMarshaller((RubyHash)enums);
            }
            default: {
                return getMarshaller(type.getNativeType());
            }
        }
    }
    
    static final ParameterMarshaller getMarshaller(final NativeType type) {
        switch (type) {
            case BOOL: {
                return BooleanMarshaller.INSTANCE;
            }
            case CHAR: {
                return Signed8Marshaller.INSTANCE;
            }
            case UCHAR: {
                return Unsigned8Marshaller.INSTANCE;
            }
            case SHORT: {
                return Signed16Marshaller.INSTANCE;
            }
            case USHORT: {
                return Unsigned16Marshaller.INSTANCE;
            }
            case INT: {
                return Signed32Marshaller.INSTANCE;
            }
            case UINT: {
                return Unsigned32Marshaller.INSTANCE;
            }
            case LONG_LONG: {
                return Signed64Marshaller.INSTANCE;
            }
            case ULONG_LONG: {
                return Unsigned64Marshaller.INSTANCE;
            }
            case LONG: {
                return (Platform.getPlatform().longSize() == 32) ? Signed32Marshaller.INSTANCE : Signed64Marshaller.INSTANCE;
            }
            case ULONG: {
                return (Platform.getPlatform().longSize() == 32) ? Signed32Marshaller.INSTANCE : Unsigned64Marshaller.INSTANCE;
            }
            case FLOAT: {
                return Float32Marshaller.INSTANCE;
            }
            case DOUBLE: {
                return Float64Marshaller.INSTANCE;
            }
            case STRING: {
                return StringMarshaller.INSTANCE;
            }
            case POINTER: {
                return BufferMarshaller.INOUT;
            }
            case BUFFER_IN: {
                return BufferMarshaller.IN;
            }
            case BUFFER_OUT: {
                return BufferMarshaller.OUT;
            }
            case BUFFER_INOUT: {
                return BufferMarshaller.INOUT;
            }
            default: {
                throw new IllegalArgumentException("Invalid parameter type: " + type);
            }
        }
    }
    
    private static final class SingletonHolder
    {
        private static final DefaultMethodFactory INSTANCE;
        
        static {
            INSTANCE = new DefaultMethodFactory(null);
        }
    }
    
    private abstract static class BaseInvoker implements FunctionInvoker
    {
        static final Invoker invoker;
        
        static {
            invoker = Invoker.getInstance();
        }
    }
    
    private static final class VoidInvoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            VoidInvoker.invoker.invokeInt(function, args);
            return context.getRuntime().getNil();
        }
        
        static {
            INSTANCE = new VoidInvoker();
        }
    }
    
    private static final class BooleanInvoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return context.getRuntime().newBoolean((BooleanInvoker.invoker.invokeInt(function, args) & 0xFF) != 0x0);
        }
        
        static {
            INSTANCE = new BooleanInvoker();
        }
    }
    
    private static final class Signed8Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return Util.newSigned8(context.getRuntime(), (byte)Signed8Invoker.invoker.invokeInt(function, args));
        }
        
        static {
            INSTANCE = new Signed8Invoker();
        }
    }
    
    private static final class Unsigned8Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return Util.newUnsigned8(context.getRuntime(), (byte)Unsigned8Invoker.invoker.invokeInt(function, args));
        }
        
        static {
            INSTANCE = new Unsigned8Invoker();
        }
    }
    
    private static final class Signed16Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return Util.newSigned16(context.getRuntime(), (short)Signed16Invoker.invoker.invokeInt(function, args));
        }
        
        static {
            INSTANCE = new Signed16Invoker();
        }
    }
    
    private static final class Unsigned16Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return Util.newUnsigned16(context.getRuntime(), (short)Unsigned16Invoker.invoker.invokeInt(function, args));
        }
        
        static {
            INSTANCE = new Unsigned16Invoker();
        }
    }
    
    private static final class Signed32Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return Util.newSigned32(context.getRuntime(), Signed32Invoker.invoker.invokeInt(function, args));
        }
        
        static {
            INSTANCE = new Signed32Invoker();
        }
    }
    
    private static final class Unsigned32Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return Util.newUnsigned32(context.getRuntime(), Unsigned32Invoker.invoker.invokeInt(function, args));
        }
        
        static {
            INSTANCE = new Unsigned32Invoker();
        }
    }
    
    private static final class Signed64Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return Util.newSigned64(context.getRuntime(), Signed64Invoker.invoker.invokeLong(function, args));
        }
        
        static {
            INSTANCE = new Signed64Invoker();
        }
    }
    
    private static final class Unsigned64Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return Util.newUnsigned64(context.getRuntime(), Unsigned64Invoker.invoker.invokeLong(function, args));
        }
        
        static {
            INSTANCE = new Unsigned64Invoker();
        }
    }
    
    private static final class Float32Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return context.getRuntime().newFloat(Float32Invoker.invoker.invokeFloat(function, args));
        }
        
        static {
            INSTANCE = new Float32Invoker();
        }
    }
    
    private static final class Float64Invoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return context.getRuntime().newFloat(Float64Invoker.invoker.invokeDouble(function, args));
        }
        
        static {
            INSTANCE = new Float64Invoker();
        }
    }
    
    private static final class PointerInvoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            final long address = PointerInvoker.invoker.invokeAddress(function, args);
            return new Pointer(context.getRuntime(), NativeMemoryIO.wrap(context.getRuntime(), address));
        }
        
        static {
            INSTANCE = new PointerInvoker();
        }
    }
    
    private static final class StringInvoker extends BaseInvoker
    {
        public static final FunctionInvoker INSTANCE;
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return FFIUtil.getString(context.getRuntime(), StringInvoker.invoker.invokeAddress(function, args));
        }
        
        static {
            INSTANCE = new StringInvoker();
        }
    }
    
    private static final class StructByValueInvoker extends BaseInvoker
    {
        private final StructByValue info;
        
        public StructByValueInvoker(final StructByValue info) {
            this.info = info;
        }
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            final Buffer buf = new Buffer(context.getRuntime(), StructByValueInvoker.invoker.invokeStruct(function, args), 0, this.info.getStructLayout().getSize());
            return this.info.getStructClass().newInstance(context, new IRubyObject[] { buf }, Block.NULL_BLOCK);
        }
    }
    
    private static final class MappedTypeInvoker extends BaseInvoker
    {
        private final FunctionInvoker nativeInvoker;
        private final MappedType mappedType;
        
        public MappedTypeInvoker(final FunctionInvoker nativeInvoker, final MappedType converter) {
            this.nativeInvoker = nativeInvoker;
            this.mappedType = converter;
        }
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            return this.mappedType.fromNative(context, this.nativeInvoker.invoke(context, function, args));
        }
    }
    
    private static final class CallbackInvoker extends BaseInvoker
    {
        NativeFunctionInfo functionInfo;
        
        public CallbackInvoker(final CallbackInfo cbInfo) {
            this.functionInfo = new NativeFunctionInfo(cbInfo.getRuntime(), cbInfo.getReturnType(), cbInfo.getParameterTypes(), cbInfo.isStdcall() ? CallingConvention.STDCALL : CallingConvention.DEFAULT);
        }
        
        public final IRubyObject invoke(final ThreadContext context, final Function function, final HeapInvocationBuffer args) {
            final long address = CallbackInvoker.invoker.invokeAddress(function, args);
            if (address == 0L) {
                return context.getRuntime().getNil();
            }
            return new org.jruby.ext.ffi.jffi.Function(context.getRuntime(), context.getRuntime().fastGetModule("FFI").fastGetClass("Function"), new CodeMemoryIO(context.getRuntime(), address), this.functionInfo, null);
        }
    }
    
    abstract static class BaseMarshaller implements ParameterMarshaller
    {
        public boolean requiresPostInvoke() {
            return false;
        }
        
        public boolean requiresReference() {
            return false;
        }
    }
    
    static final class IntOrEnumMarshaller extends BaseMarshaller
    {
        private final RubyHash enums;
        
        public IntOrEnumMarshaller(final RubyHash enums) {
            this.enums = enums;
        }
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putInt(Util.intValue(parameter, this.enums));
        }
        
        public void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            this.marshal(invocation.getThreadContext(), buffer, parameter);
        }
    }
    
    static final class BooleanMarshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putByte(parameter.isTrue() ? 1 : 0);
        }
        
        public void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            if (!(parameter instanceof RubyBoolean)) {
                throw invocation.getThreadContext().getRuntime().newTypeError("wrong argument type.  Expected true or false");
            }
            buffer.putByte(parameter.isTrue() ? 1 : 0);
        }
        
        static {
            INSTANCE = new BooleanMarshaller();
        }
    }
    
    static final class Signed8Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putByte(Util.int8Value(parameter));
        }
        
        public void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putByte(Util.int8Value(parameter));
        }
        
        static {
            INSTANCE = new Signed8Marshaller();
        }
    }
    
    static final class Unsigned8Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putByte(Util.uint8Value(parameter));
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putByte(Util.uint8Value(parameter));
        }
        
        static {
            INSTANCE = new Unsigned8Marshaller();
        }
    }
    
    static final class Signed16Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putShort(Util.int16Value(parameter));
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putShort(Util.int16Value(parameter));
        }
        
        static {
            INSTANCE = new Signed16Marshaller();
        }
    }
    
    static final class Unsigned16Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putShort(Util.uint16Value(parameter));
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putShort(Util.uint16Value(parameter));
        }
        
        static {
            INSTANCE = new Unsigned16Marshaller();
        }
    }
    
    static final class Signed32Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putInt(Util.int32Value(parameter));
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putInt(Util.int32Value(parameter));
        }
        
        static {
            INSTANCE = new Signed32Marshaller();
        }
    }
    
    static final class Unsigned32Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putInt((int)Util.uint32Value(parameter));
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putInt((int)Util.uint32Value(parameter));
        }
        
        static {
            INSTANCE = new Unsigned32Marshaller();
        }
    }
    
    static final class Signed64Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putLong(Util.int64Value(parameter));
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putLong(Util.int64Value(parameter));
        }
        
        static {
            INSTANCE = new Signed64Marshaller();
        }
    }
    
    static final class Unsigned64Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putLong(Util.uint64Value(parameter));
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putLong(Util.uint64Value(parameter));
        }
        
        static {
            INSTANCE = new Unsigned64Marshaller();
        }
    }
    
    static final class Float32Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putFloat((float)RubyNumeric.num2dbl(parameter));
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putFloat((float)RubyNumeric.num2dbl(parameter));
        }
        
        static {
            INSTANCE = new Float32Marshaller();
        }
    }
    
    static final class Float64Marshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putDouble(RubyNumeric.num2dbl(parameter));
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            buffer.putDouble(RubyNumeric.num2dbl(parameter));
        }
        
        static {
            INSTANCE = new Float64Marshaller();
        }
    }
    
    static final class BufferMarshaller extends BaseMarshaller
    {
        static final ParameterMarshaller IN;
        static final ParameterMarshaller OUT;
        static final ParameterMarshaller INOUT;
        private final int flags;
        
        public BufferMarshaller(final int flags) {
            this.flags = flags;
        }
        
        private static final int bufferFlags(final Buffer buffer) {
            final int f = buffer.getInOutFlags();
            return (((f & 0x1) != 0x0) ? 1 : 0) | (((f & 0x2) != 0x0) ? 2 : 0);
        }
        
        public boolean requiresPostInvoke() {
            return false;
        }
        
        private static final void addBufferParameter(final InvocationBuffer buffer, final IRubyObject parameter, final int flags) {
            final ArrayMemoryIO memory = (ArrayMemoryIO)((Buffer)parameter).getMemoryIO();
            buffer.putArray(memory.array(), memory.arrayOffset(), memory.arrayLength(), flags & bufferFlags((Buffer)parameter));
        }
        
        private static final long getAddress(final Pointer ptr) {
            return ((DirectMemoryIO)ptr.getMemoryIO()).getAddress();
        }
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, IRubyObject parameter) {
            if (parameter instanceof Buffer) {
                addBufferParameter(buffer, parameter, this.flags);
            }
            else if (parameter instanceof Pointer) {
                buffer.putAddress(getAddress((Pointer)parameter));
            }
            else if (parameter instanceof Struct) {
                final IRubyObject memory = ((Struct)parameter).getMemory();
                if (memory instanceof Buffer) {
                    addBufferParameter(buffer, memory, this.flags);
                }
                else if (memory instanceof Pointer) {
                    buffer.putAddress(getAddress((Pointer)memory));
                }
                else {
                    if (memory != null && !memory.isNil()) {
                        throw context.getRuntime().newArgumentError("Invalid Struct memory");
                    }
                    buffer.putAddress(0L);
                }
            }
            else if (parameter.isNil()) {
                buffer.putAddress(0L);
            }
            else if (parameter instanceof RubyString) {
                final ByteList bl = ((RubyString)parameter).getByteList();
                buffer.putArray(bl.getUnsafeBytes(), bl.begin(), bl.length(), this.flags | 0x4);
            }
            else {
                if (!parameter.respondsTo("to_ptr")) {
                    throw context.getRuntime().newArgumentError("Invalid buffer/pointer parameter");
                }
                final int MAXRECURSE = 4;
                for (int depth = 0; depth < 4; ++depth) {
                    final IRubyObject ptr = parameter.callMethod(context, "to_ptr");
                    if (ptr instanceof Pointer) {
                        buffer.putAddress(getAddress((Pointer)ptr));
                        break;
                    }
                    if (ptr instanceof Buffer) {
                        addBufferParameter(buffer, ptr, this.flags);
                        break;
                    }
                    if (ptr.isNil()) {
                        buffer.putAddress(0L);
                        break;
                    }
                    if (depth >= 4 || !ptr.respondsTo("to_ptr")) {
                        throw context.getRuntime().newArgumentError("to_ptr returned an invalid pointer");
                    }
                    parameter = ptr;
                }
            }
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            this.marshal(invocation.getThreadContext(), buffer, parameter);
        }
        
        static {
            IN = new BufferMarshaller(1);
            OUT = new BufferMarshaller(2);
            INOUT = new BufferMarshaller(3);
        }
    }
    
    static final class StringMarshaller extends BaseMarshaller
    {
        public static final ParameterMarshaller INSTANCE;
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            if (parameter instanceof RubyString) {
                Util.checkStringSafety(context.getRuntime(), parameter);
                final ByteList bl = ((RubyString)parameter).getByteList();
                buffer.putArray(bl.getUnsafeBytes(), bl.begin(), bl.length(), 5);
            }
            else {
                if (!parameter.isNil()) {
                    throw context.getRuntime().newArgumentError("Invalid string parameter");
                }
                buffer.putAddress(0L);
            }
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            this.marshal(invocation.getThreadContext(), buffer, parameter);
        }
        
        static {
            INSTANCE = new StringMarshaller();
        }
    }
    
    static final class StructByValueMarshaller extends BaseMarshaller
    {
        private final StructLayout layout;
        
        public StructByValueMarshaller(final StructByValue sbv) {
            this.layout = sbv.getStructLayout();
        }
        
        public final void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            if (!(parameter instanceof Struct)) {
                throw context.getRuntime().newTypeError("wrong argument type " + parameter.getMetaClass().getName() + " (expected instance of FFI::Struct)");
            }
            final AbstractMemory memory = ((Struct)parameter).getMemory();
            if (memory.getSize() < this.layout.getSize()) {
                throw context.getRuntime().newArgumentError("struct memory too small for parameter");
            }
            final MemoryIO io = memory.getMemoryIO();
            if (io instanceof DirectMemoryIO) {
                if (io.isNull()) {
                    throw context.getRuntime().newRuntimeError("Cannot use a NULL pointer as a struct by value argument");
                }
                buffer.putStruct(((DirectMemoryIO)io).getAddress());
            }
            else {
                if (!(io instanceof ArrayMemoryIO)) {
                    throw context.getRuntime().newRuntimeError("invalid struct memory");
                }
                final ArrayMemoryIO aio = (ArrayMemoryIO)io;
                buffer.putStruct(aio.array(), aio.arrayOffset());
            }
        }
        
        public final void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            this.marshal(invocation.getThreadContext(), buffer, parameter);
        }
    }
    
    static final class MappedTypeMarshaller implements ParameterMarshaller
    {
        private final ParameterMarshaller nativeMarshaller;
        private final MappedType mappedType;
        
        public MappedTypeMarshaller(final ParameterMarshaller nativeMarshaller, final MappedType mappedType) {
            this.nativeMarshaller = nativeMarshaller;
            this.mappedType = mappedType;
        }
        
        public void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject parameter) {
            final ThreadContext context = invocation.getThreadContext();
            final IRubyObject nativeValue = this.mappedType.toNative(context, parameter);
            if (this.mappedType.isReferenceRequired()) {
                invocation.addReference(nativeValue);
            }
            this.nativeMarshaller.marshal(context, buffer, nativeValue);
        }
        
        public void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject parameter) {
            this.nativeMarshaller.marshal(context, buffer, this.mappedType.toNative(context, parameter));
        }
        
        public boolean requiresPostInvoke() {
            return this.mappedType.isReferenceRequired();
        }
        
        public boolean requiresReference() {
            return this.mappedType.isReferenceRequired();
        }
    }
}
