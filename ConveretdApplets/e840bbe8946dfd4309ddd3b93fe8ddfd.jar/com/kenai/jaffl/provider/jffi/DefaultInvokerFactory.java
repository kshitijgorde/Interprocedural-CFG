// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.mapper.ToNativeContext;
import com.kenai.jaffl.provider.StringIO;
import com.kenai.jaffl.MemoryIO;
import com.kenai.jaffl.util.EnumMapper;
import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jaffl.provider.InvocationSession;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.LongBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.ByteBuffer;
import com.kenai.jaffl.ParameterFlags;
import java.lang.annotation.Annotation;
import com.kenai.jaffl.byref.ByReference;
import java.nio.Buffer;
import com.kenai.jaffl.mapper.ToNativeConverter;
import com.kenai.jaffl.struct.Struct;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.Platform;
import com.kenai.jaffl.NativeLong;
import com.kenai.jaffl.mapper.FromNativeConverter;
import com.kenai.jaffl.mapper.FromNativeContext;
import com.kenai.jaffl.mapper.MethodResultContext;
import com.kenai.jffi.Function;
import com.kenai.jffi.Type;
import com.kenai.jffi.CallingConvention;
import com.kenai.jaffl.annotations.StdCall;
import com.kenai.jaffl.mapper.TypeMapper;
import com.kenai.jaffl.mapper.FunctionMapper;
import com.kenai.jaffl.provider.Invoker;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;
import com.kenai.jaffl.provider.Library;
import java.lang.reflect.Method;

final class DefaultInvokerFactory implements InvokerFactory
{
    public static final InvokerFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public final boolean isMethodSupported(final Method method) {
        return true;
    }
    
    public final Invoker createInvoker(final Method method, final Library library, final Map<LibraryOption, ?> options) {
        final FunctionMapper functionMapper = (FunctionMapper)(options.containsKey(LibraryOption.FunctionMapper) ? options.get(LibraryOption.FunctionMapper) : IdentityFunctionMapper.getInstance());
        final long address = ((com.kenai.jaffl.provider.jffi.Library)library).findSymbolAddress(functionMapper.mapFunctionName(method.getName(), null));
        final TypeMapper typeMapper = (TypeMapper)(options.containsKey(LibraryOption.TypeMapper) ? options.get(LibraryOption.TypeMapper) : NullTypeMapper.INSTANCE);
        final CallingConvention convention = (method.getAnnotation(StdCall.class) != null) ? CallingConvention.STDCALL : InvokerUtil.getCallingConvention(options);
        final Marshaller[] marshallers = new Marshaller[method.getParameterTypes().length];
        final Type[] paramTypes = new Type[marshallers.length];
        for (int i = 0; i < marshallers.length; ++i) {
            marshallers[i] = getMarshaller(method, i, typeMapper);
            paramTypes[i] = getNativeParameterType(method, i, typeMapper);
        }
        Class returnType = method.getReturnType();
        final FromNativeConverter resultConverter = typeMapper.getFromNativeConverter(returnType);
        if (resultConverter != null) {
            returnType = resultConverter.nativeType();
        }
        final Function function = new Function(address, getNativeReturnType(returnType), paramTypes, convention, InvokerUtil.requiresErrno(method));
        FunctionInvoker invoker = getFunctionInvoker(returnType);
        if (resultConverter != null) {
            final MethodResultContext context = new MethodResultContext(method);
            invoker = new ConvertingInvoker(resultConverter, context, invoker);
        }
        return isSessionRequired(marshallers) ? new SessionInvoker(function, invoker, marshallers) : new DefaultInvoker(function, invoker, marshallers);
    }
    
    private static final boolean isSessionRequired(final Marshaller[] marshallers) {
        for (final Marshaller m : marshallers) {
            if (m.isSessionRequired()) {
                return true;
            }
        }
        return false;
    }
    
    private static final FunctionInvoker getFunctionInvoker(final Class returnType) {
        if (Void.class.isAssignableFrom(returnType) || Void.TYPE == returnType) {
            return VoidInvoker.INSTANCE;
        }
        if (Boolean.class.isAssignableFrom(returnType) || Boolean.TYPE == returnType) {
            return BooleanInvoker.INSTANCE;
        }
        if (Enum.class.isAssignableFrom(returnType)) {
            return new EnumInvoker(returnType);
        }
        if (Byte.class.isAssignableFrom(returnType) || Byte.TYPE == returnType) {
            return Int8Invoker.INSTANCE;
        }
        if (Short.class.isAssignableFrom(returnType) || Short.TYPE == returnType) {
            return Int16Invoker.INSTANCE;
        }
        if (Integer.class.isAssignableFrom(returnType) || Integer.TYPE == returnType) {
            return Int32Invoker.INSTANCE;
        }
        if (Long.class.isAssignableFrom(returnType) || Long.TYPE == returnType) {
            return Int64Invoker.INSTANCE;
        }
        if (NativeLong.class.isAssignableFrom(returnType)) {
            return (Platform.getPlatform().longSize() == 32) ? NativeLong32Invoker.INSTANCE : NativeLong64Invoker.INSTANCE;
        }
        if (Float.class.isAssignableFrom(returnType) || Float.TYPE == returnType) {
            return Float32Invoker.INSTANCE;
        }
        if (Double.class.isAssignableFrom(returnType) || Double.TYPE == returnType) {
            return Float64Invoker.INSTANCE;
        }
        if (Pointer.class.isAssignableFrom(returnType)) {
            return PointerInvoker.INSTANCE;
        }
        if (Struct.class.isAssignableFrom(returnType)) {
            return new StructInvoker(returnType);
        }
        if (String.class.isAssignableFrom(returnType)) {
            return StringInvoker.INSTANCE;
        }
        throw new IllegalArgumentException("Unknown return type: " + returnType);
    }
    
    private static final Type getNativeReturnType(final Class type) {
        if (Void.class.isAssignableFrom(type) || Void.TYPE == type) {
            return Type.VOID;
        }
        if (Boolean.class.isAssignableFrom(type) || Boolean.TYPE == type) {
            return Type.SINT32;
        }
        if (Byte.class.isAssignableFrom(type) || Byte.TYPE == type) {
            return Type.SINT8;
        }
        if (Short.class.isAssignableFrom(type) || Short.TYPE == type) {
            return Type.SINT16;
        }
        if (Integer.class.isAssignableFrom(type) || Integer.TYPE == type) {
            return Type.SINT32;
        }
        if (Long.class.isAssignableFrom(type) || Long.TYPE == type) {
            return Type.SINT64;
        }
        if (NativeLong.class.isAssignableFrom(type)) {
            return (Platform.getPlatform().longSize() == 32) ? Type.SINT32 : Type.SINT64;
        }
        if (Float.class.isAssignableFrom(type) || Float.TYPE == type) {
            return Type.FLOAT;
        }
        if (Double.class.isAssignableFrom(type) || Double.TYPE == type) {
            return Type.DOUBLE;
        }
        if (Enum.class.isAssignableFrom(type)) {
            return Type.SINT32;
        }
        if (Pointer.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (Struct.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (String.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        throw new IllegalArgumentException("Unsupported return type: " + type);
    }
    
    private static final Type getNativeParameterType(final Method method, final int paramIndex, final TypeMapper mapper) {
        final Class type = method.getParameterTypes()[paramIndex];
        final ToNativeConverter converter = mapper.getToNativeConverter(type);
        return getNativeParameterType((converter != null) ? converter.nativeType() : type);
    }
    
    private static final Type getNativeParameterType(final Class type) {
        if (Byte.class.isAssignableFrom(type) || Byte.TYPE == type) {
            return Type.SINT8;
        }
        if (Short.class.isAssignableFrom(type) || Short.TYPE == type) {
            return Type.SINT16;
        }
        if (Integer.class.isAssignableFrom(type) || Integer.TYPE == type) {
            return Type.SINT32;
        }
        if (Long.class.isAssignableFrom(type) || Long.TYPE == type) {
            return Type.SINT64;
        }
        if (NativeLong.class.isAssignableFrom(type)) {
            return (Platform.getPlatform().longSize() == 32) ? Type.SINT32 : Type.SINT64;
        }
        if (Float.class.isAssignableFrom(type) || Float.TYPE == type) {
            return Type.FLOAT;
        }
        if (Double.class.isAssignableFrom(type) || Double.TYPE == type) {
            return Type.DOUBLE;
        }
        if (Boolean.class.isAssignableFrom(type) || Boolean.TYPE == type) {
            return Type.SINT32;
        }
        if (Enum.class.isAssignableFrom(type)) {
            return Type.SINT32;
        }
        if (Pointer.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (Struct.class.isAssignableFrom(type) || (type.isArray() && Struct.class.isAssignableFrom(type.getComponentType()))) {
            return Type.POINTER;
        }
        if (Buffer.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (CharSequence.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (ByReference.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (type.isArray()) {
            return Type.POINTER;
        }
        throw new IllegalArgumentException("Unsupported parameter type: " + type);
    }
    
    static final int getParameterFlags(final Method method, final int paramIndex) {
        return getParameterFlags(method.getParameterAnnotations()[paramIndex]);
    }
    
    static final int getParameterFlags(final Annotation[] annotations) {
        return ParameterFlags.parse(annotations);
    }
    
    static final int getNativeArrayFlags(final int flags) {
        int nflags = 0;
        nflags |= (ParameterFlags.isIn(flags) ? 1 : 0);
        nflags |= (ParameterFlags.isOut(flags) ? 2 : 0);
        nflags |= (ParameterFlags.isNulTerminate(flags) ? 4 : 0);
        return nflags;
    }
    
    static final int getNativeArrayFlags(final Annotation[] annotations) {
        return getNativeArrayFlags(getParameterFlags(annotations));
    }
    
    static final Marshaller getMarshaller(final Method method, final int paramIndex, final TypeMapper mapper) {
        final Class type = method.getParameterTypes()[paramIndex];
        final ToNativeConverter converter = (mapper != null) ? mapper.getToNativeConverter(type) : null;
        if (converter != null) {
            return new ToNativeConverterMarshaller(converter, getMarshaller(converter.nativeType(), method.getParameterAnnotations()[paramIndex]));
        }
        return getMarshaller(method, paramIndex);
    }
    
    static final Marshaller getMarshaller(final Method method, final int paramIndex) {
        return getMarshaller(method.getParameterTypes()[paramIndex], method.getParameterAnnotations()[paramIndex]);
    }
    
    static final Marshaller getMarshaller(final Class type, final Annotation[] annotations) {
        if (Byte.class.isAssignableFrom(type) || Byte.TYPE == type) {
            return Int8Marshaller.INSTANCE;
        }
        if (Short.class.isAssignableFrom(type) || Short.TYPE == type) {
            return Int16Marshaller.INSTANCE;
        }
        if (Integer.class.isAssignableFrom(type) || Integer.TYPE == type) {
            return Int32Marshaller.INSTANCE;
        }
        if (Long.class.isAssignableFrom(type) || Long.TYPE == type) {
            return Int64Marshaller.INSTANCE;
        }
        if (NativeLong.class.isAssignableFrom(type)) {
            return (Platform.getPlatform().longSize() == 32) ? Int32Marshaller.INSTANCE : Int64Marshaller.INSTANCE;
        }
        if (Float.class.isAssignableFrom(type) || Float.TYPE == type) {
            return Float32Marshaller.INSTANCE;
        }
        if (Double.class.isAssignableFrom(type) || Double.TYPE == type) {
            return Float64Marshaller.INSTANCE;
        }
        if (Boolean.class.isAssignableFrom(type) || Boolean.TYPE == type) {
            return BooleanMarshaller.INSTANCE;
        }
        if (Enum.class.isAssignableFrom(type)) {
            return EnumMarshaller.INSTANCE;
        }
        if (Pointer.class.isAssignableFrom(type)) {
            return PointerMarshaller.INSTANCE;
        }
        if (StringBuffer.class.isAssignableFrom(type)) {
            return new StringBufferMarshaller(getParameterFlags(annotations));
        }
        if (StringBuilder.class.isAssignableFrom(type)) {
            return new StringBuilderMarshaller(getParameterFlags(annotations));
        }
        if (CharSequence.class.isAssignableFrom(type)) {
            return CharSequenceMarshaller.INSTANCE;
        }
        if (type.isArray() && CharSequence.class.isAssignableFrom(type.getComponentType())) {
            return new StringArrayMarshaller(getParameterFlags(annotations));
        }
        if (ByReference.class.isAssignableFrom(type)) {
            return new ByReferenceMarshaller(getParameterFlags(annotations));
        }
        if (Struct.class.isAssignableFrom(type)) {
            return new StructMarshaller(getParameterFlags(annotations));
        }
        if (ByteBuffer.class.isAssignableFrom(type)) {
            return new ByteBufferMarshaller(getParameterFlags(annotations));
        }
        if (ShortBuffer.class.isAssignableFrom(type)) {
            return new ShortBufferMarshaller(getParameterFlags(annotations));
        }
        if (IntBuffer.class.isAssignableFrom(type)) {
            return new IntBufferMarshaller(getParameterFlags(annotations));
        }
        if (LongBuffer.class.isAssignableFrom(type)) {
            return new LongBufferMarshaller(getParameterFlags(annotations));
        }
        if (FloatBuffer.class.isAssignableFrom(type)) {
            return new FloatBufferMarshaller(getParameterFlags(annotations));
        }
        if (DoubleBuffer.class.isAssignableFrom(type)) {
            return new DoubleBufferMarshaller(getParameterFlags(annotations));
        }
        if (type.isArray() && type.getComponentType() == Byte.TYPE) {
            return new ByteArrayMarshaller(getParameterFlags(annotations));
        }
        if (type.isArray() && type.getComponentType() == Short.TYPE) {
            return new ShortArrayMarshaller(getParameterFlags(annotations));
        }
        if (type.isArray() && type.getComponentType() == Integer.TYPE) {
            return new IntArrayMarshaller(getParameterFlags(annotations));
        }
        if (type.isArray() && type.getComponentType() == Long.TYPE) {
            return new LongArrayMarshaller(getParameterFlags(annotations));
        }
        if (type.isArray() && type.getComponentType() == Float.TYPE) {
            return new FloatArrayMarshaller(getParameterFlags(annotations));
        }
        if (type.isArray() && type.getComponentType() == Double.TYPE) {
            return new DoubleArrayMarshaller(getParameterFlags(annotations));
        }
        if (type.isArray() && Struct.class.isAssignableFrom(type.getComponentType())) {
            return new StructArrayMarshaller(getParameterFlags(annotations));
        }
        if (type.isArray() && Pointer.class.isAssignableFrom(type.getComponentType())) {
            return new PointerArrayMarshaller(getParameterFlags(annotations));
        }
        throw new IllegalArgumentException("Unsupported parameter type: " + type);
    }
    
    private static final class SingletonHolder
    {
        static InvokerFactory INSTANCE;
        
        static {
            SingletonHolder.INSTANCE = new DefaultInvokerFactory();
        }
    }
    
    static final class SessionInvoker implements Invoker
    {
        static final com.kenai.jffi.Invoker invoker;
        final Function function;
        final FunctionInvoker functionInvoker;
        final Marshaller[] marshallers;
        
        SessionInvoker(final Function function, final FunctionInvoker invoker, final Marshaller[] marshallers) {
            this.function = function;
            this.functionInvoker = invoker;
            this.marshallers = marshallers;
        }
        
        final HeapInvocationBuffer marshal(final InvocationSession session, final Object[] parameters) {
            final HeapInvocationBuffer buffer = new HeapInvocationBuffer(this.function);
            for (int i = 0; i < parameters.length; ++i) {
                this.marshallers[i].marshal(session, buffer, parameters[i]);
            }
            return buffer;
        }
        
        public final Object invoke(final Object[] parameters) {
            final InvocationSession session = new InvocationSession();
            final Object retVal = this.functionInvoker.invoke(this.function, this.marshal(session, parameters));
            session.finish();
            return retVal;
        }
        
        static {
            invoker = com.kenai.jffi.Invoker.getInstance();
        }
    }
    
    static final class DefaultInvoker implements Invoker
    {
        final Function function;
        final FunctionInvoker functionInvoker;
        final Marshaller[] marshallers;
        
        DefaultInvoker(final Function function, final FunctionInvoker invoker, final Marshaller[] marshallers) {
            this.function = function;
            this.functionInvoker = invoker;
            this.marshallers = marshallers;
        }
        
        final HeapInvocationBuffer marshal(final Object[] parameters) {
            final HeapInvocationBuffer buffer = new HeapInvocationBuffer(this.function);
            for (int i = 0; i < parameters.length; ++i) {
                this.marshallers[i].marshal(buffer, parameters[i]);
            }
            return buffer;
        }
        
        public final Object invoke(final Object[] parameters) {
            return this.functionInvoker.invoke(this.function, this.marshal(parameters));
        }
    }
    
    abstract static class BaseMarshaller implements Marshaller
    {
        public boolean isSessionRequired() {
            return false;
        }
        
        public void marshal(final InvocationSession session, final InvocationBuffer buffer, final Object parameter) {
            this.marshal(buffer, parameter);
        }
    }
    
    abstract static class BaseInvoker implements FunctionInvoker
    {
        static final com.kenai.jffi.Invoker invoker;
        
        static {
            invoker = com.kenai.jffi.Invoker.getInstance();
        }
    }
    
    static final class ConvertingInvoker extends BaseInvoker
    {
        private final FromNativeConverter converter;
        private final FromNativeContext context;
        private final FunctionInvoker nativeInvoker;
        
        public ConvertingInvoker(final FromNativeConverter converter, final FromNativeContext context, final FunctionInvoker nativeInvoker) {
            this.converter = converter;
            this.context = context;
            this.nativeInvoker = nativeInvoker;
        }
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return this.converter.fromNative(this.nativeInvoker.invoke(function, buffer), this.context);
        }
    }
    
    static final class VoidInvoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            VoidInvoker.invoker.invokeInt(function, buffer);
            return null;
        }
        
        static {
            INSTANCE = new VoidInvoker();
        }
    }
    
    static final class BooleanInvoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return BooleanInvoker.invoker.invokeInt(function, buffer) != 0;
        }
        
        static {
            INSTANCE = new BooleanInvoker();
        }
    }
    
    static final class EnumInvoker extends BaseInvoker
    {
        private final Class enumClass;
        
        private EnumInvoker(final Class enumClass) {
            this.enumClass = enumClass;
        }
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return EnumMapper.getInstance().valueOf(EnumInvoker.invoker.invokeInt(function, buffer), (Class<Object>)this.enumClass);
        }
    }
    
    static final class Int8Invoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return (byte)Int8Invoker.invoker.invokeInt(function, buffer);
        }
        
        static {
            INSTANCE = new Int8Invoker();
        }
    }
    
    static final class Int16Invoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return (short)Int16Invoker.invoker.invokeInt(function, buffer);
        }
        
        static {
            INSTANCE = new Int16Invoker();
        }
    }
    
    static final class Int32Invoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return Int32Invoker.invoker.invokeInt(function, buffer);
        }
        
        static {
            INSTANCE = new Int32Invoker();
        }
    }
    
    static final class Int64Invoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return Int64Invoker.invoker.invokeLong(function, buffer);
        }
        
        static {
            INSTANCE = new Int64Invoker();
        }
    }
    
    static final class NativeLong32Invoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return NativeLong.valueOf(NativeLong32Invoker.invoker.invokeInt(function, buffer));
        }
        
        static {
            INSTANCE = new NativeLong32Invoker();
        }
    }
    
    static final class NativeLong64Invoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return NativeLong.valueOf(NativeLong64Invoker.invoker.invokeLong(function, buffer));
        }
        
        static {
            INSTANCE = new NativeLong64Invoker();
        }
    }
    
    static final class Float32Invoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return Float32Invoker.invoker.invokeFloat(function, buffer);
        }
        
        static {
            INSTANCE = new Float32Invoker();
        }
    }
    
    static final class Float64Invoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return Float64Invoker.invoker.invokeDouble(function, buffer);
        }
        
        static {
            INSTANCE = new Float64Invoker();
        }
    }
    
    static final class PointerInvoker extends BaseInvoker
    {
        static final FunctionInvoker INSTANCE;
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            return MemoryUtil.newPointer(PointerInvoker.invoker.invokeAddress(function, buffer));
        }
        
        static {
            INSTANCE = new PointerInvoker();
        }
    }
    
    static final class StructInvoker extends BaseInvoker
    {
        private final Class structClass;
        
        public StructInvoker(final Class structClass) {
            this.structClass = structClass;
        }
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            final long ptr = StructInvoker.invoker.invokeAddress(function, buffer);
            if (ptr == 0L) {
                return null;
            }
            try {
                final Struct s = this.structClass.newInstance();
                s.useMemory(new DirectMemoryIO(ptr));
                return s;
            }
            catch (Throwable t) {
                throw new RuntimeException(t);
            }
        }
    }
    
    static final class StringInvoker extends BaseInvoker
    {
        com.kenai.jffi.MemoryIO IO;
        static final FunctionInvoker INSTANCE;
        
        StringInvoker() {
            this.IO = com.kenai.jffi.MemoryIO.getInstance();
        }
        
        public final Object invoke(final Function function, final HeapInvocationBuffer buffer) {
            final long ptr = StringInvoker.invoker.invokeAddress(function, buffer);
            if (ptr == 0L) {
                return null;
            }
            final ByteBuffer buf = ByteBuffer.wrap(this.IO.getZeroTerminatedByteArray(ptr));
            return StringIO.getStringIO().fromNative(buf).toString();
        }
        
        static {
            INSTANCE = new StringInvoker();
        }
    }
    
    static final class BooleanMarshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            buffer.putInt(((boolean)parameter) ? 1 : 0);
        }
        
        static {
            INSTANCE = new BooleanMarshaller();
        }
    }
    
    static final class EnumMarshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            buffer.putInt(EnumMapper.getInstance().intValue((Enum)parameter));
        }
        
        static {
            INSTANCE = new EnumMarshaller();
        }
    }
    
    static final class Int8Marshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            buffer.putByte(((Number)parameter).intValue());
        }
        
        static {
            INSTANCE = new Int8Marshaller();
        }
    }
    
    static final class Int16Marshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            buffer.putShort(((Number)parameter).intValue());
        }
        
        static {
            INSTANCE = new Int16Marshaller();
        }
    }
    
    static final class Int32Marshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            buffer.putInt(((Number)parameter).intValue());
        }
        
        static {
            INSTANCE = new Int32Marshaller();
        }
    }
    
    static final class Int64Marshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            buffer.putLong(((Number)parameter).longValue());
        }
        
        static {
            INSTANCE = new Int64Marshaller();
        }
    }
    
    static final class Float32Marshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            buffer.putFloat(((Number)parameter).floatValue());
        }
        
        static {
            INSTANCE = new Float32Marshaller();
        }
    }
    
    static final class Float64Marshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            buffer.putDouble(((Number)parameter).doubleValue());
        }
        
        static {
            INSTANCE = new Float64Marshaller();
        }
    }
    
    static final class PointerMarshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            buffer.putAddress(((Pointer)parameter).address());
        }
        
        static {
            INSTANCE = new PointerMarshaller();
        }
    }
    
    static final class CharSequenceMarshaller extends BaseMarshaller
    {
        static final Marshaller INSTANCE;
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, (CharSequence)parameter);
        }
        
        static {
            INSTANCE = new CharSequenceMarshaller();
        }
    }
    
    abstract static class SessionRequiredMarshaller extends BaseMarshaller
    {
        public final boolean isSessionRequired() {
            return true;
        }
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            throw new UnsupportedOperationException("Cannot marshal this type without session");
        }
    }
    
    static final class StringBuilderMarshaller extends SessionRequiredMarshaller
    {
        private final int nflags;
        private final int inout;
        
        public StringBuilderMarshaller(final int inout) {
            this.inout = inout;
            this.nflags = DefaultInvokerFactory.getNativeArrayFlags(inout | (ParameterFlags.isIn(inout) ? 8 : 0));
        }
        
        public void marshal(final InvocationSession session, final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(session, buffer, (StringBuilder)parameter, this.inout, this.nflags);
        }
    }
    
    static final class StringArrayMarshaller extends SessionRequiredMarshaller
    {
        private final int nflags;
        private final int inout;
        
        public StringArrayMarshaller(final int inout) {
            this.inout = inout;
            this.nflags = DefaultInvokerFactory.getNativeArrayFlags(inout | (ParameterFlags.isIn(inout) ? 8 : 0));
        }
        
        public void marshal(final InvocationSession session, final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(session, buffer, (CharSequence[])parameter, this.inout, this.nflags);
        }
    }
    
    static final class StringBufferMarshaller extends SessionRequiredMarshaller
    {
        private final int nflags;
        private final int inout;
        
        public StringBufferMarshaller(final int inout) {
            this.inout = inout;
            this.nflags = DefaultInvokerFactory.getNativeArrayFlags(inout);
        }
        
        public void marshal(final InvocationSession session, final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(session, buffer, (StringBuffer)parameter, this.inout, this.nflags);
        }
    }
    
    static final class ByteArrayMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public ByteArrayMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags | (ParameterFlags.isIn(flags) ? 8 : 0));
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, byte[].class.cast(parameter), this.flags);
        }
    }
    
    static final class ShortArrayMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public ShortArrayMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, short[].class.cast(parameter), this.flags);
        }
    }
    
    static final class IntArrayMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public IntArrayMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, int[].class.cast(parameter), this.flags);
        }
    }
    
    static final class LongArrayMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public LongArrayMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, long[].class.cast(parameter), this.flags);
        }
    }
    
    static final class FloatArrayMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public FloatArrayMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, float[].class.cast(parameter), this.flags);
        }
    }
    
    static final class DoubleArrayMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public DoubleArrayMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, double[].class.cast(parameter), this.flags);
        }
    }
    
    static final class PointerArrayMarshaller extends SessionRequiredMarshaller
    {
        private final int nflags;
        private final int inout;
        
        public PointerArrayMarshaller(final int inout) {
            this.inout = inout;
            this.nflags = DefaultInvokerFactory.getNativeArrayFlags(inout);
        }
        
        public void marshal(final InvocationSession session, final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(session, buffer, (Pointer[])parameter, this.inout, this.nflags);
        }
    }
    
    static final class ByteBufferMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public ByteBufferMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags | (ParameterFlags.isIn(flags) ? 8 : 0));
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, (ByteBuffer)parameter, this.flags);
        }
    }
    
    static final class ShortBufferMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public ShortBufferMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, (ShortBuffer)parameter, this.flags);
        }
    }
    
    static final class IntBufferMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public IntBufferMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, (IntBuffer)parameter, this.flags);
        }
    }
    
    static final class LongBufferMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public LongBufferMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, (LongBuffer)parameter, this.flags);
        }
    }
    
    static final class FloatBufferMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public FloatBufferMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, (FloatBuffer)parameter, this.flags);
        }
    }
    
    static final class DoubleBufferMarshaller extends BaseMarshaller
    {
        private final int flags;
        
        public DoubleBufferMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, (DoubleBuffer)parameter, this.flags);
        }
    }
    
    static final class ByReferenceMarshaller extends SessionRequiredMarshaller
    {
        private final int flags;
        
        public ByReferenceMarshaller(final int flags) {
            this.flags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationSession session, final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(session, buffer, (ByReference)parameter, this.flags);
        }
    }
    
    static final class StructMarshaller extends BaseMarshaller
    {
        private final int nflags;
        private final int flags;
        
        public StructMarshaller(final int flags) {
            this.flags = flags;
            this.nflags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, (Struct)parameter, this.flags, this.nflags);
        }
    }
    
    static final class StructArrayMarshaller extends BaseMarshaller
    {
        private final int nflags;
        private final int flags;
        
        public StructArrayMarshaller(final int flags) {
            this.flags = flags;
            this.nflags = DefaultInvokerFactory.getNativeArrayFlags(flags);
        }
        
        public final void marshal(final InvocationBuffer buffer, final Object parameter) {
            AsmRuntime.marshal(buffer, Struct[].class.cast(parameter), this.flags, this.nflags);
        }
    }
    
    static final class ToNativeConverterMarshaller extends BaseMarshaller
    {
        private final ToNativeConverter converter;
        private final ToNativeContext context;
        private final Marshaller marshaller;
        
        public ToNativeConverterMarshaller(final ToNativeConverter converter, final Marshaller marshaller) {
            this.context = null;
            this.converter = converter;
            this.marshaller = marshaller;
        }
        
        public void marshal(final InvocationBuffer buffer, final Object parameter) {
            this.marshaller.marshal(buffer, this.converter.toNative(parameter, this.context));
        }
        
        public boolean isSessionRequired() {
            return this.marshaller.isSessionRequired();
        }
        
        public void marshal(final InvocationSession session, final InvocationBuffer buffer, final Object parameter) {
            this.marshaller.marshal(session, buffer, this.converter.toNative(parameter, this.context));
        }
    }
    
    interface Marshaller
    {
        boolean isSessionRequired();
        
        void marshal(final InvocationSession p0, final InvocationBuffer p1, final Object p2);
        
        void marshal(final InvocationBuffer p0, final Object p1);
    }
    
    interface FunctionInvoker
    {
        Object invoke(final Function p0, final HeapInvocationBuffer p1);
    }
}
