// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.Block;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import java.nio.ByteOrder;

abstract class MemoryOp
{
    public static final MemoryOp BOOL;
    public static final MemoryOp INT8;
    public static final MemoryOp UINT8;
    public static final MemoryOp INT16;
    public static final MemoryOp UINT16;
    public static final MemoryOp INT32;
    public static final MemoryOp UINT32;
    public static final MemoryOp INT64;
    public static final MemoryOp UINT64;
    public static final MemoryOp FLOAT32;
    public static final MemoryOp FLOAT64;
    public static final MemoryOp INT16SWAP;
    public static final MemoryOp UINT16SWAP;
    public static final MemoryOp INT32SWAP;
    public static final MemoryOp UINT32SWAP;
    public static final MemoryOp INT64SWAP;
    public static final MemoryOp UINT64SWAP;
    
    public static MemoryOp getMemoryOp(final NativeType type) {
        return getMemoryOp(type, ByteOrder.nativeOrder());
    }
    
    public static MemoryOp getMemoryOp(final NativeType type, final ByteOrder order) {
        switch (type) {
            case BOOL: {
                return MemoryOp.BOOL;
            }
            case CHAR: {
                return MemoryOp.INT8;
            }
            case UCHAR: {
                return MemoryOp.UINT8;
            }
            case SHORT: {
                return order.equals(ByteOrder.nativeOrder()) ? MemoryOp.INT16 : MemoryOp.INT16SWAP;
            }
            case USHORT: {
                return order.equals(ByteOrder.nativeOrder()) ? MemoryOp.UINT16 : MemoryOp.UINT16SWAP;
            }
            case INT: {
                return order.equals(ByteOrder.nativeOrder()) ? MemoryOp.INT32 : MemoryOp.INT32SWAP;
            }
            case UINT: {
                return order.equals(ByteOrder.nativeOrder()) ? MemoryOp.UINT32 : MemoryOp.UINT32SWAP;
            }
            case LONG_LONG: {
                return order.equals(ByteOrder.nativeOrder()) ? MemoryOp.INT64 : MemoryOp.INT64SWAP;
            }
            case ULONG_LONG: {
                return order.equals(ByteOrder.nativeOrder()) ? MemoryOp.UINT64 : MemoryOp.UINT64SWAP;
            }
            case FLOAT: {
                return MemoryOp.FLOAT32;
            }
            case DOUBLE: {
                return MemoryOp.FLOAT64;
            }
            case LONG: {
                return (Platform.getPlatform().longSize() == 32) ? getMemoryOp(NativeType.INT, order) : getMemoryOp(NativeType.LONG_LONG, order);
            }
            case ULONG: {
                return (Platform.getPlatform().longSize() == 32) ? getMemoryOp(NativeType.UINT, order) : getMemoryOp(NativeType.ULONG_LONG, order);
            }
            default: {
                return null;
            }
        }
    }
    
    public static MemoryOp getMemoryOp(final Type type) {
        return getMemoryOp(type, ByteOrder.nativeOrder());
    }
    
    public static MemoryOp getMemoryOp(final Type type, final ByteOrder order) {
        if (type instanceof Type.Builtin) {
            return getMemoryOp(type.getNativeType(), order);
        }
        if (type instanceof StructByValue) {
            final StructByValue sbv = (StructByValue)type;
            return new StructOp(sbv.getStructClass());
        }
        if (type instanceof MappedType) {
            return new Mapped(getMemoryOp(((MappedType)type).getRealType(), order), (MappedType)type);
        }
        return null;
    }
    
    abstract IRubyObject get(final ThreadContext p0, final MemoryIO p1, final long p2);
    
    abstract void put(final ThreadContext p0, final MemoryIO p1, final long p2, final IRubyObject p3);
    
    IRubyObject get(final ThreadContext context, final AbstractMemory ptr, final long offset) {
        return this.get(context, ptr.getMemoryIO(), offset);
    }
    
    void put(final ThreadContext context, final AbstractMemory ptr, final long offset, final IRubyObject value) {
        this.put(context, ptr.getMemoryIO(), offset, value);
    }
    
    static {
        BOOL = new BooleanOp();
        INT8 = new Signed8();
        UINT8 = new Unsigned8();
        INT16 = new Signed16();
        UINT16 = new Unsigned16();
        INT32 = new Signed32();
        UINT32 = new Unsigned32();
        INT64 = new Signed64();
        UINT64 = new Unsigned64();
        FLOAT32 = new Float32();
        FLOAT64 = new Float64();
        INT16SWAP = new Signed16Swapped();
        UINT16SWAP = new Unsigned16Swapped();
        INT32SWAP = new Signed32Swapped();
        UINT32SWAP = new Unsigned32Swapped();
        INT64SWAP = new Signed64Swapped();
        UINT64SWAP = new Unsigned64Swapped();
    }
    
    abstract static class PrimitiveOp extends MemoryOp
    {
        abstract IRubyObject get(final Ruby p0, final MemoryIO p1, final long p2);
        
        abstract void put(final Ruby p0, final MemoryIO p1, final long p2, final IRubyObject p3);
        
        IRubyObject get(final ThreadContext context, final MemoryIO io, final long offset) {
            return this.get(context.getRuntime(), io, offset);
        }
        
        void put(final ThreadContext context, final MemoryIO io, final long offset, final IRubyObject value) {
            this.put(context.getRuntime(), io, offset, value);
        }
    }
    
    static final class BooleanOp extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putByte(offset, (byte)(value.isTrue() ? 1 : 0));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return runtime.newBoolean(io.getByte(offset) != 0);
        }
    }
    
    static final class Signed8 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putByte(offset, Util.int8Value(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newSigned8(runtime, io.getByte(offset));
        }
    }
    
    static final class Unsigned8 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putByte(offset, (byte)Util.uint8Value(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newUnsigned8(runtime, io.getByte(offset));
        }
    }
    
    static final class Signed16 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putShort(offset, Util.int16Value(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newSigned16(runtime, io.getShort(offset));
        }
    }
    
    static final class Signed16Swapped extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putShort(offset, Short.reverseBytes(Util.int16Value(value)));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newSigned16(runtime, Short.reverseBytes(io.getShort(offset)));
        }
    }
    
    static final class Unsigned16 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putShort(offset, (short)Util.uint16Value(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newUnsigned16(runtime, io.getShort(offset));
        }
    }
    
    static final class Unsigned16Swapped extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putShort(offset, Short.reverseBytes((short)Util.uint16Value(value)));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newUnsigned16(runtime, Short.reverseBytes(io.getShort(offset)));
        }
    }
    
    static final class Signed32 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putInt(offset, Util.int32Value(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newSigned32(runtime, io.getInt(offset));
        }
    }
    
    static final class Signed32Swapped extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putInt(offset, Integer.reverseBytes(Util.int32Value(value)));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newSigned32(runtime, Integer.reverseBytes(io.getInt(offset)));
        }
    }
    
    static final class Unsigned32 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putInt(offset, (int)Util.uint32Value(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newUnsigned32(runtime, io.getInt(offset));
        }
    }
    
    static final class Unsigned32Swapped extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putInt(offset, Integer.reverseBytes((int)Util.uint32Value(value)));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newUnsigned32(runtime, Integer.reverseBytes(io.getInt(offset)));
        }
    }
    
    static final class Signed64 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putLong(offset, Util.int64Value(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newSigned64(runtime, io.getLong(offset));
        }
    }
    
    static final class Signed64Swapped extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putLong(offset, Long.reverseBytes(Util.int64Value(value)));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newSigned64(runtime, Long.reverseBytes(io.getLong(offset)));
        }
    }
    
    static final class Unsigned64 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putLong(offset, Util.uint64Value(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newUnsigned64(runtime, io.getLong(offset));
        }
    }
    
    static final class Unsigned64Swapped extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putLong(offset, Long.reverseBytes(Util.uint64Value(value)));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return Util.newUnsigned64(runtime, Long.reverseBytes(io.getLong(offset)));
        }
    }
    
    static final class Float32 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putFloat(offset, Util.floatValue(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return runtime.newFloat(io.getFloat(offset));
        }
    }
    
    static final class Float64 extends PrimitiveOp
    {
        public final void put(final Ruby runtime, final MemoryIO io, final long offset, final IRubyObject value) {
            io.putDouble(offset, Util.doubleValue(value));
        }
        
        public final IRubyObject get(final Ruby runtime, final MemoryIO io, final long offset) {
            return runtime.newFloat(io.getDouble(offset));
        }
    }
    
    static final class StructOp extends MemoryOp
    {
        private final RubyClass structClass;
        
        public StructOp(final RubyClass structClass) {
            this.structClass = structClass;
        }
        
        IRubyObject get(final ThreadContext context, final MemoryIO io, final long offset) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        void put(final ThreadContext context, final MemoryIO io, final long offset, final IRubyObject value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        IRubyObject get(final ThreadContext context, final AbstractMemory ptr, final long offset) {
            return this.structClass.newInstance(context, new IRubyObject[] { ptr.slice(context.getRuntime(), offset) }, Block.NULL_BLOCK);
        }
        
        void put(final ThreadContext context, final AbstractMemory ptr, final long offset, final IRubyObject value) {
            if (!(value instanceof Struct)) {
                throw context.getRuntime().newTypeError("expected a struct");
            }
            final Struct s = (Struct)value;
            final byte[] tmp = new byte[Struct.getStructSize(context.getRuntime(), s)];
            s.getMemoryIO().get(0L, tmp, 0, tmp.length);
            ptr.getMemoryIO().put(offset, tmp, 0, tmp.length);
        }
    }
    
    static final class Mapped extends MemoryOp
    {
        private final MemoryOp nativeOp;
        private final MappedType mappedType;
        
        public Mapped(final MemoryOp nativeOp, final MappedType mappedType) {
            this.nativeOp = nativeOp;
            this.mappedType = mappedType;
        }
        
        IRubyObject get(final ThreadContext context, final AbstractMemory ptr, final long offset) {
            return this.mappedType.fromNative(context, this.nativeOp.get(context, ptr, offset));
        }
        
        void put(final ThreadContext context, final AbstractMemory ptr, final long offset, final IRubyObject value) {
            this.nativeOp.put(context, ptr, offset, this.mappedType.toNative(context, value));
        }
        
        IRubyObject get(final ThreadContext context, final MemoryIO io, final long offset) {
            return this.mappedType.fromNative(context, this.nativeOp.get(context, io, offset));
        }
        
        void put(final ThreadContext context, final MemoryIO io, final long offset, final IRubyObject value) {
            this.nativeOp.put(context, io, offset, this.mappedType.toNative(context, value));
        }
    }
}
