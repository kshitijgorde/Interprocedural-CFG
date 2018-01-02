// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.RubyNumeric;
import org.jruby.RubyBoolean;
import com.kenai.jffi.MemoryIO;
import org.jruby.ext.ffi.Pointer;
import org.jruby.ext.ffi.Util;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyHash;
import org.jruby.ext.ffi.NativeType;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.jffi.Function;
import org.jruby.RubyModule;
import org.jruby.ext.ffi.MappedType;
import org.jruby.ext.ffi.Platform;
import org.jruby.ext.ffi.Type;

public class FastLongMethodFactory
{
    public static final FastLongMethodFactory getFactory() {
        return SingletonHolder.INSTANCE;
    }
    
    final boolean isFastLongMethod(final Type returnType, final Type[] parameterTypes) {
        for (int i = 0; i < parameterTypes.length; ++i) {
            if (!this.isFastLongParam(parameterTypes[i])) {
                return false;
            }
        }
        if (parameterTypes.length <= 3 && this.isFastLongResult(returnType)) {
            final Platform.CPU_TYPE cpu = Platform.getPlatform().getCPU();
            final Platform.CPU_TYPE cpu2 = Platform.CPU;
            if (cpu == Platform.CPU_TYPE.X86_64) {
                return true;
            }
        }
        return false;
    }
    
    final boolean isFastLongResult(final Type type) {
        if (type instanceof Type.Builtin) {
            switch (type.getNativeType()) {
                case VOID:
                case BOOL:
                case CHAR:
                case UCHAR:
                case SHORT:
                case USHORT:
                case INT:
                case UINT:
                case LONG_LONG:
                case ULONG_LONG:
                case POINTER:
                case STRING:
                case LONG:
                case ULONG: {
                    return true;
                }
            }
        }
        else if (type instanceof MappedType) {
            final MappedType mt = (MappedType)type;
            return this.isFastLongResult(mt.getRealType()) && !mt.isReferenceRequired() && !mt.isPostInvokeRequired();
        }
        return false;
    }
    
    final boolean isFastLongParam(final Type paramType) {
        if (paramType instanceof Type.Builtin) {
            switch (paramType.getNativeType()) {
                case BOOL:
                case CHAR:
                case UCHAR:
                case SHORT:
                case USHORT:
                case INT:
                case UINT:
                case LONG_LONG:
                case ULONG_LONG:
                case LONG:
                case ULONG: {
                    return true;
                }
            }
        }
        else if (paramType instanceof MappedType) {
            return this.isFastLongParam(((MappedType)paramType).getRealType());
        }
        return false;
    }
    
    DynamicMethod createMethod(final RubyModule module, final Function function, final Type returnType, final Type[] parameterTypes, final IRubyObject enums) {
        final LongParameterConverter[] parameterConverters = new LongParameterConverter[parameterTypes.length];
        final LongResultConverter resultConverter = this.getLongResultConverter(returnType);
        for (int i = 0; i < parameterConverters.length; ++i) {
            parameterConverters[i] = this.getLongParameterConverter(parameterTypes[i], enums);
        }
        switch (parameterTypes.length) {
            case 0: {
                return new FastLongMethodZeroArg(module, function, resultConverter, parameterConverters);
            }
            case 1: {
                return new FastLongMethodOneArg(module, function, resultConverter, parameterConverters);
            }
            case 2: {
                return new FastLongMethodTwoArg(module, function, resultConverter, parameterConverters);
            }
            case 3: {
                return new FastLongMethodThreeArg(module, function, resultConverter, parameterConverters);
            }
            default: {
                throw module.getRuntime().newRuntimeError("Arity " + parameterTypes.length + " not implemented");
            }
        }
    }
    
    final LongParameterConverter getLongParameterConverter(final Type type, final IRubyObject enums) {
        if (type instanceof Type.Builtin) {
            return this.getLongParameterConverter(type.getNativeType(), enums);
        }
        if (type instanceof MappedType) {
            final MappedType mtype = (MappedType)type;
            return new MappedParameterConverter(this.getLongParameterConverter(mtype.getRealType(), enums), mtype);
        }
        throw new IllegalArgumentException("Unknown type " + type);
    }
    
    final LongParameterConverter getLongParameterConverter(final NativeType type, final IRubyObject enums) {
        switch (type) {
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
            case LONG_LONG: {
                return Signed64ParameterConverter.INSTANCE;
            }
            case ULONG_LONG: {
                return Unsigned64ParameterConverter.INSTANCE;
            }
            case FLOAT: {
                return Float32ParameterConverter.INSTANCE;
            }
            case DOUBLE: {
                return Float64ParameterConverter.INSTANCE;
            }
            case LONG: {
                if (Platform.getPlatform().longSize() == 32) {
                    return Signed32ParameterConverter.INSTANCE;
                }
                return Signed64ParameterConverter.INSTANCE;
            }
            case ULONG: {
                if (Platform.getPlatform().longSize() == 32) {
                    return Unsigned32ParameterConverter.INSTANCE;
                }
                return Unsigned64ParameterConverter.INSTANCE;
            }
            default: {
                throw new IllegalArgumentException("Unknown type " + type);
            }
        }
    }
    
    final LongResultConverter getLongResultConverter(final Type type) {
        if (type instanceof Type.Builtin) {
            return this.getLongResultConverter(type.getNativeType());
        }
        if (type instanceof MappedType) {
            final MappedType mtype = (MappedType)type;
            return new MappedResultConverter(this.getLongResultConverter(mtype.getRealType()), mtype);
        }
        throw new IllegalArgumentException("unsupported return type " + type);
    }
    
    final LongResultConverter getLongResultConverter(final NativeType type) {
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
            case LONG_LONG: {
                return Signed64ResultConverter.INSTANCE;
            }
            case ULONG_LONG: {
                return Unsigned64ResultConverter.INSTANCE;
            }
            case FLOAT: {
                return Float32ResultConverter.INSTANCE;
            }
            case DOUBLE: {
                return Float64ResultConverter.INSTANCE;
            }
            case LONG: {
                return (Platform.getPlatform().longSize() == 32) ? Signed32ResultConverter.INSTANCE : Signed64ResultConverter.INSTANCE;
            }
            case ULONG: {
                return (Platform.getPlatform().longSize() == 32) ? Unsigned32ResultConverter.INSTANCE : Unsigned64ResultConverter.INSTANCE;
            }
            case POINTER: {
                return PointerResultConverter.INSTANCE;
            }
            case STRING: {
                return StringResultConverter.INSTANCE;
            }
            default: {
                throw new IllegalArgumentException("Unknown type " + type);
            }
        }
    }
    
    private static final class SingletonHolder
    {
        private static final FastLongMethodFactory INSTANCE;
        
        static {
            INSTANCE = new FastLongMethodFactory(null);
        }
    }
    
    static final class VoidResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return context.getRuntime().getNil();
        }
        
        static {
            INSTANCE = new VoidResultConverter();
        }
    }
    
    static final class BooleanResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return context.getRuntime().newBoolean(value != 0L);
        }
        
        static {
            INSTANCE = new Signed8ResultConverter();
        }
    }
    
    static final class Signed8ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return Util.newSigned8(context.getRuntime(), (byte)value);
        }
        
        static {
            INSTANCE = new Signed8ResultConverter();
        }
    }
    
    static final class Unsigned8ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return Util.newUnsigned8(context.getRuntime(), (byte)value);
        }
        
        static {
            INSTANCE = new Unsigned8ResultConverter();
        }
    }
    
    static final class Signed16ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return Util.newSigned16(context.getRuntime(), (short)value);
        }
        
        static {
            INSTANCE = new Signed16ResultConverter();
        }
    }
    
    static final class Unsigned16ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return Util.newUnsigned16(context.getRuntime(), (short)value);
        }
        
        static {
            INSTANCE = new Unsigned16ResultConverter();
        }
    }
    
    static final class Signed32ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return Util.newSigned32(context.getRuntime(), (int)value);
        }
        
        static {
            INSTANCE = new Signed32ResultConverter();
        }
    }
    
    static final class Unsigned32ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return Util.newUnsigned32(context.getRuntime(), (int)value);
        }
        
        static {
            INSTANCE = new Unsigned32ResultConverter();
        }
    }
    
    static final class Signed64ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return Util.newSigned64(context.getRuntime(), value);
        }
        
        static {
            INSTANCE = new Signed64ResultConverter();
        }
    }
    
    static final class Unsigned64ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return Util.newUnsigned64(context.getRuntime(), value);
        }
        
        static {
            INSTANCE = new Unsigned64ResultConverter();
        }
    }
    
    static final class Float32ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return context.getRuntime().newFloat(Float.intBitsToFloat((int)value));
        }
        
        static {
            INSTANCE = new Float32ResultConverter();
        }
    }
    
    static final class Float64ResultConverter implements LongResultConverter
    {
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return context.getRuntime().newFloat(Double.longBitsToDouble(value));
        }
        
        static {
            INSTANCE = new Float64ResultConverter();
        }
    }
    
    static final class PointerResultConverter implements LongResultConverter
    {
        static final long ADDRESS_MASK;
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            final long address = value & PointerResultConverter.ADDRESS_MASK;
            return new Pointer(context.getRuntime(), NativeMemoryIO.wrap(context.getRuntime(), address));
        }
        
        static {
            ADDRESS_MASK = ((Platform.getPlatform().addressSize() == 32) ? 4294967295L : -1L);
            INSTANCE = new PointerResultConverter();
        }
    }
    
    static final class MappedResultConverter implements LongResultConverter
    {
        private final LongResultConverter longConverter;
        private final MappedType mappedType;
        
        public MappedResultConverter(final LongResultConverter longConverter, final MappedType mappedType) {
            this.longConverter = longConverter;
            this.mappedType = mappedType;
        }
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            return this.mappedType.fromNative(context, this.longConverter.fromNative(context, value));
        }
    }
    
    static final class StringResultConverter implements LongResultConverter
    {
        private static final MemoryIO IO;
        public static final LongResultConverter INSTANCE;
        
        public final IRubyObject fromNative(final ThreadContext context, final long value) {
            final long address = value & PointerResultConverter.ADDRESS_MASK;
            return FFIUtil.getString(context.getRuntime(), address);
        }
        
        static {
            IO = MemoryIO.getInstance();
            INSTANCE = new StringResultConverter();
        }
    }
    
    abstract static class BaseParameterConverter implements LongParameterConverter
    {
        static final MemoryIO IO;
        
        static {
            IO = MemoryIO.getInstance();
        }
    }
    
    static final class BooleanParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
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
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Util.int8Value(obj);
        }
        
        static {
            INSTANCE = new Signed8ParameterConverter();
        }
    }
    
    static final class Unsigned8ParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Util.uint8Value(obj);
        }
        
        static {
            INSTANCE = new Unsigned8ParameterConverter();
        }
    }
    
    static final class Signed16ParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Util.int16Value(obj);
        }
        
        static {
            INSTANCE = new Signed16ParameterConverter();
        }
    }
    
    static final class Unsigned16ParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Util.uint16Value(obj);
        }
        
        static {
            INSTANCE = new Unsigned16ParameterConverter();
        }
    }
    
    static final class Signed32ParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Util.int32Value(obj);
        }
        
        static {
            INSTANCE = new Signed32ParameterConverter();
        }
    }
    
    static final class Unsigned32ParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Util.uint32Value(obj);
        }
        
        static {
            INSTANCE = new Unsigned32ParameterConverter();
        }
    }
    
    static final class Signed64ParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Util.int64Value(obj);
        }
        
        static {
            INSTANCE = new Signed64ParameterConverter();
        }
    }
    
    static final class Unsigned64ParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Util.uint64Value(obj);
        }
        
        static {
            INSTANCE = new Unsigned64ParameterConverter();
        }
    }
    
    static final class Float32ParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Float.floatToRawIntBits((float)RubyNumeric.num2dbl(obj)) & 0xFFFFFFFFL;
        }
        
        static {
            INSTANCE = new Float32ParameterConverter();
        }
    }
    
    static final class Float64ParameterConverter extends BaseParameterConverter
    {
        public static final LongParameterConverter INSTANCE;
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return Double.doubleToRawLongBits(RubyNumeric.num2dbl(obj));
        }
        
        static {
            INSTANCE = new Float64ParameterConverter();
        }
    }
    
    static final class IntOrEnumParameterConverter extends BaseParameterConverter
    {
        private final RubyHash enums;
        
        public IntOrEnumParameterConverter(final RubyHash enums) {
            this.enums = enums;
        }
        
        public final long longValue(final ThreadContext context, final IRubyObject parameter) {
            return Util.intValue(parameter, this.enums);
        }
    }
    
    static final class MappedParameterConverter extends BaseParameterConverter
    {
        private final LongParameterConverter longConverter;
        private final MappedType mappedType;
        
        public MappedParameterConverter(final LongParameterConverter longConverter, final MappedType mappedType) {
            this.longConverter = longConverter;
            this.mappedType = mappedType;
        }
        
        public final long longValue(final ThreadContext context, final IRubyObject obj) {
            return this.longConverter.longValue(context, this.mappedType.toNative(context, obj));
        }
    }
}
