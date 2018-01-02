// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.ext.ffi.Struct;
import org.jruby.RubyNumeric;
import org.jruby.RubyBoolean;
import com.kenai.jffi.MemoryIO;
import org.jruby.ext.ffi.Util;
import org.jruby.runtime.ThreadContext;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.ext.ffi.Pointer;
import org.jruby.RubyHash;
import org.jruby.ext.ffi.NativeType;
import org.jruby.ext.ffi.NativeParam;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;
import org.jruby.ext.ffi.MappedType;
import org.jruby.ext.ffi.Platform;
import org.jruby.ext.ffi.Type;

public class FastIntMethodFactory extends MethodFactory
{
    public static final FastIntMethodFactory getFactory() {
        return SingletonHolder.INSTANCE;
    }
    
    final boolean isFastIntMethod(final Type returnType, final Type[] parameterTypes) {
        for (int i = 0; i < parameterTypes.length; ++i) {
            if (!this.isFastIntParam(parameterTypes[i])) {
                return false;
            }
        }
        if (parameterTypes.length <= 3 && this.isFastIntResult(returnType)) {
            final Platform.CPU_TYPE cpu = Platform.getPlatform().getCPU();
            final Platform.CPU_TYPE cpu2 = Platform.CPU;
            if (cpu != Platform.CPU_TYPE.I386) {
                final Platform.CPU_TYPE cpu3 = Platform.getPlatform().getCPU();
                final Platform.CPU_TYPE cpu4 = Platform.CPU;
                if (cpu3 != Platform.CPU_TYPE.X86_64) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    final boolean isFastIntResult(final Type type) {
        if (type instanceof Type.Builtin) {
            switch (type.getNativeType()) {
                case VOID:
                case CHAR:
                case UCHAR:
                case SHORT:
                case USHORT:
                case INT:
                case UINT:
                case BOOL: {
                    return true;
                }
                case POINTER:
                case STRING: {
                    return Platform.getPlatform().addressSize() == 32;
                }
                case LONG:
                case ULONG: {
                    return Platform.getPlatform().longSize() == 32;
                }
            }
        }
        else if (type instanceof MappedType) {
            return this.isFastIntResult(((MappedType)type).getRealType());
        }
        return false;
    }
    
    final boolean isFastIntParam(final Type paramType) {
        if (paramType instanceof Type.Builtin) {
            switch (paramType.getNativeType()) {
                case CHAR:
                case UCHAR:
                case SHORT:
                case USHORT:
                case INT:
                case UINT:
                case BOOL: {
                    return true;
                }
                case LONG:
                case ULONG: {
                    return Platform.getPlatform().longSize() == 32;
                }
            }
        }
        else if (paramType instanceof MappedType) {
            final MappedType mt = (MappedType)paramType;
            return this.isFastIntParam(mt.getRealType()) && !mt.isReferenceRequired() && !mt.isPostInvokeRequired();
        }
        return false;
    }
    
    DynamicMethod createMethod(final RubyModule module, final Function function, final Type returnType, final Type[] parameterTypes, final IRubyObject enums) {
        final IntParameterConverter[] parameterConverters = new IntParameterConverter[parameterTypes.length];
        final IntResultConverter resultConverter = this.getIntResultConverter(returnType);
        for (int i = 0; i < parameterConverters.length; ++i) {
            parameterConverters[i] = this.getIntParameterConverter(parameterTypes[i], enums);
        }
        switch (parameterTypes.length) {
            case 0: {
                return new FastIntMethodZeroArg(module, function, resultConverter, parameterConverters);
            }
            case 1: {
                return new FastIntMethodOneArg(module, function, resultConverter, parameterConverters);
            }
            case 2: {
                return new FastIntMethodTwoArg(module, function, resultConverter, parameterConverters);
            }
            case 3: {
                return new FastIntMethodThreeArg(module, function, resultConverter, parameterConverters);
            }
            default: {
                throw module.getRuntime().newRuntimeError("Arity " + parameterTypes.length + " not implemented");
            }
        }
    }
    
    final IntParameterConverter getIntParameterConverter(final Type type, final IRubyObject enums) {
        if (type instanceof Type.Builtin) {
            return this.getIntParameterConverter(type.getNativeType(), enums);
        }
        if (type instanceof MappedType) {
            final MappedType ctype = (MappedType)type;
            return new MappedParameterConverter(this.getIntParameterConverter(ctype.getRealType(), enums), ctype);
        }
        return null;
    }
    
    final IntParameterConverter getIntParameterConverter(final NativeParam type, final IRubyObject enums) {
        switch ((NativeType)type) {
            case BOOL: {
                return BooleanParameterConverter.INSTANCE;
            }
            case CHAR: {
                return Signed8ParameterConverter.INSTANCE;
            }
            case UCHAR: {
                return Unsigned8ParameterConverter.INSTANCE;
            }
            case SHORT: {
                return Signed16ParameterConverter.INSTANCE;
            }
            case USHORT: {
                return Unsigned16ParameterConverter.INSTANCE;
            }
            case INT: {
                return (enums instanceof RubyHash) ? new IntOrEnumParameterConverter((RubyHash)enums) : Signed32ParameterConverter.INSTANCE;
            }
            case UINT: {
                return Unsigned32ParameterConverter.INSTANCE;
            }
            case FLOAT: {
                return Float32ParameterConverter.INSTANCE;
            }
            case LONG: {
                if (Platform.getPlatform().longSize() == 32) {
                    return Signed32ParameterConverter.INSTANCE;
                }
                throw new IllegalArgumentException("Long is too big for int parameter");
            }
            case ULONG: {
                if (Platform.getPlatform().longSize() == 32) {
                    return Unsigned32ParameterConverter.INSTANCE;
                }
                throw new IllegalArgumentException("Long is too big for int parameter");
            }
            case POINTER:
            case BUFFER_IN:
            case BUFFER_OUT:
            case BUFFER_INOUT: {
                if (Platform.getPlatform().addressSize() == 32) {
                    return PointerParameterConverter.INSTANCE;
                }
                throw new IllegalArgumentException("Pointer is too big for int parameter");
            }
            default: {
                throw new IllegalArgumentException("Unknown type " + type);
            }
        }
    }
    
    final IntResultConverter getIntResultConverter(final Type type) {
        if (type instanceof Type.Builtin) {
            return this.getIntResultConverter(type.getNativeType());
        }
        if (type instanceof MappedType) {
            final MappedType ctype = (MappedType)type;
            return new MappedResultConverter(this.getIntResultConverter(ctype.getRealType()), ctype);
        }
        return null;
    }
    
    final IntResultConverter getIntResultConverter(final NativeType type) {
        switch (type) {
            case VOID: {
                return VoidResultConverter.INSTANCE;
            }
            case BOOL: {
                return BooleanResultConverter.INSTANCE;
            }
            case CHAR: {
                return Signed8ResultConverter.INSTANCE;
            }
            case UCHAR: {
                return Unsigned8ResultConverter.INSTANCE;
            }
            case SHORT: {
                return Signed16ResultConverter.INSTANCE;
            }
            case USHORT: {
                return Unsigned16ResultConverter.INSTANCE;
            }
            case INT: {
                return Signed32ResultConverter.INSTANCE;
            }
            case UINT: {
                return Unsigned32ResultConverter.INSTANCE;
            }
            case FLOAT: {
                return Float32ResultConverter.INSTANCE;
            }
            case LONG: {
                if (Platform.getPlatform().longSize() == 32) {
                    return Signed32ResultConverter.INSTANCE;
                }
                throw new IllegalArgumentException(":long is too big for int result");
            }
            case ULONG: {
                if (Platform.getPlatform().longSize() == 32) {
                    return Unsigned32ResultConverter.INSTANCE;
                }
                throw new IllegalArgumentException(":ulong is too big for int result");
            }
            case POINTER: {
                if (Platform.getPlatform().addressSize() == 32) {
                    return PointerResultConverter.INSTANCE;
                }
                throw new IllegalArgumentException(":pointer is too big for int result");
            }
            case STRING: {
                if (Platform.getPlatform().addressSize() == 32) {
                    return StringResultConverter.INSTANCE;
                }
                throw new IllegalArgumentException(":string is too big for int result");
            }
            default: {
                throw new IllegalArgumentException("Unknown type " + type);
            }
        }
    }
    
    private static final int getAddress(final Pointer ptr) {
        return (int)((DirectMemoryIO)ptr.getMemoryIO()).getAddress();
    }
    
    private static final class SingletonHolder
    {
        private static final FastIntMethodFactory INSTANCE;
        
        static {
            INSTANCE = new FastIntMethodFactory(null);
        }
    }
    
    static final class VoidResultConverter implements IntResultConverter
    {
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return context.getRuntime().getNil();
        }
        
        static {
            INSTANCE = new VoidResultConverter();
        }
    }
    
    static final class BooleanResultConverter implements IntResultConverter
    {
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return context.getRuntime().newBoolean((value & 0xFF) != 0x0);
        }
        
        static {
            INSTANCE = new BooleanResultConverter();
        }
    }
    
    static final class Signed8ResultConverter implements IntResultConverter
    {
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return Util.newSigned8(context.getRuntime(), (byte)value);
        }
        
        static {
            INSTANCE = new Signed8ResultConverter();
        }
    }
    
    static final class Unsigned8ResultConverter implements IntResultConverter
    {
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return Util.newUnsigned8(context.getRuntime(), (byte)value);
        }
        
        static {
            INSTANCE = new Unsigned8ResultConverter();
        }
    }
    
    static final class Signed16ResultConverter implements IntResultConverter
    {
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return Util.newSigned16(context.getRuntime(), (short)value);
        }
        
        static {
            INSTANCE = new Signed16ResultConverter();
        }
    }
    
    static final class Unsigned16ResultConverter implements IntResultConverter
    {
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return Util.newUnsigned16(context.getRuntime(), (short)value);
        }
        
        static {
            INSTANCE = new Unsigned16ResultConverter();
        }
    }
    
    static final class Signed32ResultConverter implements IntResultConverter
    {
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return Util.newSigned32(context.getRuntime(), value);
        }
        
        static {
            INSTANCE = new Signed32ResultConverter();
        }
    }
    
    static final class Unsigned32ResultConverter implements IntResultConverter
    {
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return Util.newUnsigned32(context.getRuntime(), value);
        }
        
        static {
            INSTANCE = new Unsigned32ResultConverter();
        }
    }
    
    static final class Float32ResultConverter implements IntResultConverter
    {
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return context.getRuntime().newFloat(Float.intBitsToFloat(value));
        }
        
        static {
            INSTANCE = new Float32ResultConverter();
        }
    }
    
    static final class PointerResultConverter implements IntResultConverter
    {
        static final long ADDRESS_MASK;
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            final long address = value & PointerResultConverter.ADDRESS_MASK;
            return new Pointer(context.getRuntime(), NativeMemoryIO.wrap(context.getRuntime(), address));
        }
        
        static {
            ADDRESS_MASK = ((Platform.getPlatform().addressSize() == 32) ? 4294967295L : -1L);
            INSTANCE = new PointerResultConverter();
        }
    }
    
    static final class MappedResultConverter implements IntResultConverter
    {
        private final IntResultConverter nativeConverter;
        private final MappedType mappedType;
        
        public MappedResultConverter(final IntResultConverter nativeConverter, final MappedType mappedType) {
            this.nativeConverter = nativeConverter;
            this.mappedType = mappedType;
        }
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            return this.mappedType.fromNative(context, this.nativeConverter.fromNative(context, value));
        }
    }
    
    static final class StringResultConverter implements IntResultConverter
    {
        private static final MemoryIO IO;
        public static final IntResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final int value) {
            final long address = value & PointerResultConverter.ADDRESS_MASK;
            return FFIUtil.getString(context.getRuntime(), address);
        }
        
        static {
            IO = MemoryIO.getInstance();
            INSTANCE = new StringResultConverter();
        }
    }
    
    abstract static class BaseParameterConverter implements IntParameterConverter
    {
        static final MemoryIO IO;
        
        public boolean isConvertible(final ThreadContext context, final IRubyObject value) {
            return true;
        }
        
        static {
            IO = MemoryIO.getInstance();
        }
    }
    
    static final class BooleanParameterConverter extends BaseParameterConverter
    {
        public static final IntParameterConverter INSTANCE;
        
        public final int intValue(final ThreadContext context, final IRubyObject obj) {
            if (!(obj instanceof RubyBoolean)) {
                throw context.getRuntime().newTypeError("wrong argument type.  Expected true or false");
            }
            return obj.isTrue() ? 1 : 0;
        }
        
        static {
            INSTANCE = new BooleanParameterConverter();
        }
    }
    
    static final class Signed8ParameterConverter extends BaseParameterConverter
    {
        public static final IntParameterConverter INSTANCE;
        
        public final int intValue(final ThreadContext context, final IRubyObject obj) {
            return Util.int8Value(obj);
        }
        
        static {
            INSTANCE = new Signed8ParameterConverter();
        }
    }
    
    static final class Unsigned8ParameterConverter extends BaseParameterConverter
    {
        public static final IntParameterConverter INSTANCE;
        
        public final int intValue(final ThreadContext context, final IRubyObject obj) {
            return Util.uint8Value(obj);
        }
        
        static {
            INSTANCE = new Unsigned8ParameterConverter();
        }
    }
    
    static final class Signed16ParameterConverter extends BaseParameterConverter
    {
        public static final IntParameterConverter INSTANCE;
        
        public final int intValue(final ThreadContext context, final IRubyObject obj) {
            return Util.int16Value(obj);
        }
        
        static {
            INSTANCE = new Signed16ParameterConverter();
        }
    }
    
    static final class Unsigned16ParameterConverter extends BaseParameterConverter
    {
        public static final IntParameterConverter INSTANCE;
        
        public final int intValue(final ThreadContext context, final IRubyObject obj) {
            return Util.uint16Value(obj);
        }
        
        static {
            INSTANCE = new Unsigned16ParameterConverter();
        }
    }
    
    static final class Signed32ParameterConverter extends BaseParameterConverter
    {
        public static final IntParameterConverter INSTANCE;
        
        public final int intValue(final ThreadContext context, final IRubyObject obj) {
            return Util.int32Value(obj);
        }
        
        static {
            INSTANCE = new Signed32ParameterConverter();
        }
    }
    
    static final class Unsigned32ParameterConverter extends BaseParameterConverter
    {
        public static final IntParameterConverter INSTANCE;
        
        public final int intValue(final ThreadContext context, final IRubyObject obj) {
            return (int)Util.uint32Value(obj);
        }
        
        static {
            INSTANCE = new Unsigned32ParameterConverter();
        }
    }
    
    static final class Float32ParameterConverter extends BaseParameterConverter
    {
        public static final IntParameterConverter INSTANCE;
        
        public final int intValue(final ThreadContext context, final IRubyObject obj) {
            return Float.floatToRawIntBits((float)RubyNumeric.num2dbl(obj));
        }
        
        static {
            INSTANCE = new Float32ParameterConverter();
        }
    }
    
    static final class PointerParameterConverter extends BaseParameterConverter
    {
        public static final IntParameterConverter INSTANCE;
        
        public final int intValue(final ThreadContext context, final IRubyObject parameter) {
            if (parameter instanceof Pointer) {
                return getAddress((Pointer)parameter);
            }
            if (parameter instanceof Struct) {
                final IRubyObject memory = ((Struct)parameter).getMemory();
                if (memory instanceof Pointer) {
                    return getAddress((Pointer)memory);
                }
                if (memory == null || memory.isNil()) {
                    return 0;
                }
            }
            else if (parameter.isNil()) {
                return 0;
            }
            throw context.getRuntime().newArgumentError("Cannot convert pointer to 32bit integer");
        }
        
        public boolean isConvertible(final ThreadContext context, final IRubyObject parameter) {
            return parameter instanceof Pointer || (parameter instanceof Struct && ((Struct)parameter).getMemory() instanceof Pointer) || parameter.isNil();
        }
        
        static {
            INSTANCE = new PointerParameterConverter();
        }
    }
    
    static final class IntOrEnumParameterConverter extends BaseParameterConverter
    {
        private final RubyHash enums;
        
        public IntOrEnumParameterConverter(final RubyHash enums) {
            this.enums = enums;
        }
        
        public final int intValue(final ThreadContext context, final IRubyObject parameter) {
            return Util.intValue(parameter, this.enums);
        }
    }
    
    static final class MappedParameterConverter extends BaseParameterConverter
    {
        private final IntParameterConverter nativeConverter;
        private final MappedType mappedType;
        
        public MappedParameterConverter(final IntParameterConverter nativeConverter, final MappedType mappedType) {
            this.nativeConverter = nativeConverter;
            this.mappedType = mappedType;
        }
        
        public final int intValue(final ThreadContext context, final IRubyObject obj) {
            return this.nativeConverter.intValue(context, this.mappedType.toNative(context, obj));
        }
        
        public boolean isConvertible(final ThreadContext context, final IRubyObject value) {
            return this.nativeConverter.isConvertible(context, this.mappedType.toNative(context, value));
        }
    }
}
