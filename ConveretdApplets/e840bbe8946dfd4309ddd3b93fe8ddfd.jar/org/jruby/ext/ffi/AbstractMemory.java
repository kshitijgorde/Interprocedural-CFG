// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import java.nio.ByteOrder;
import org.jruby.util.ByteList;
import org.jruby.RubyFloat;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyArray;
import org.jruby.RubyNumeric;
import org.jruby.RubySymbol;
import org.jruby.RubyFixnum;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "FFI::AbtractMemoryAbstractMemory" }, parent = "Object")
public abstract class AbstractMemory extends RubyObject
{
    public static final String ABSTRACT_MEMORY_RUBY_CLASS = "AbstractMemory";
    protected long size;
    protected int typeSize;
    protected MemoryIO io;
    
    public static RubyClass createAbstractMemoryClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("AbstractMemory", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        result.defineAnnotatedMethods(AbstractMemory.class);
        result.defineAnnotatedConstants(AbstractMemory.class);
        return result;
    }
    
    protected static final int calculateTypeSize(final ThreadContext context, final IRubyObject sizeArg) {
        if (sizeArg instanceof RubyFixnum) {
            return (int)((RubyFixnum)sizeArg).getLongValue();
        }
        if (sizeArg instanceof RubySymbol) {
            return TypeSizeMapper.getTypeSize(context, sizeArg);
        }
        if (sizeArg instanceof RubyClass && Struct.isStruct(context.getRuntime(), (RubyClass)sizeArg)) {
            return Struct.getStructSize(context.getRuntime(), sizeArg);
        }
        if (sizeArg.respondsTo("size")) {
            return (int)RubyNumeric.num2long(sizeArg.callMethod(context, "size"));
        }
        throw context.getRuntime().newArgumentError("Invalid size argument");
    }
    
    protected static final RubyArray checkArray(final IRubyObject obj) {
        if (!(obj instanceof RubyArray)) {
            throw obj.getRuntime().newArgumentError("Array expected");
        }
        return (RubyArray)obj;
    }
    
    protected AbstractMemory(final Ruby runtime, final RubyClass klass, final MemoryIO io, final long size) {
        this(runtime, klass, io, size, 1);
    }
    
    protected AbstractMemory(final Ruby runtime, final RubyClass klass, final MemoryIO io, final long size, final int typeSize) {
        super(runtime, klass);
        this.io = io;
        this.size = size;
        this.typeSize = typeSize;
    }
    
    public final MemoryIO getMemoryIO() {
        return this.io;
    }
    
    protected final MemoryIO setMemoryIO(final MemoryIO io) {
        final MemoryIO old = this.io;
        this.io = io;
        return old;
    }
    
    protected final long getOffset(final IRubyObject offset) {
        return Util.longValue(offset);
    }
    
    public final long getSize() {
        return this.size;
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash(final ThreadContext context) {
        return context.getRuntime().newFixnum(this.hashCode());
    }
    
    @JRubyMethod(name = { "to_s" }, optional = 1)
    public IRubyObject to_s(final ThreadContext context, final IRubyObject[] args) {
        return RubyString.newString(context.getRuntime(), "AbstractMemory[size=" + this.size + "]");
    }
    
    @JRubyMethod(name = { "[]" })
    public final IRubyObject aref(final ThreadContext context, final IRubyObject indexArg) {
        final int index = RubyNumeric.num2int(indexArg);
        final int offset = index * this.typeSize;
        if (offset >= this.size) {
            throw context.getRuntime().newIndexError(String.format("Index %d out of range", index));
        }
        return this.slice(context.getRuntime(), offset);
    }
    
    public boolean equals(final Object obj) {
        if (!(obj instanceof AbstractMemory)) {
            return false;
        }
        final AbstractMemory other = (AbstractMemory)obj;
        return other.getMemoryIO().equals(this.getMemoryIO());
    }
    
    @JRubyMethod(name = { "==" }, required = 1)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject obj) {
        return context.getRuntime().newBoolean(this.equals(obj));
    }
    
    public final boolean eql(final IRubyObject other) {
        return this.equals(other);
    }
    
    public int hashCode() {
        return 67 * this.getMemoryIO().hashCode();
    }
    
    @JRubyMethod(name = { "clear" })
    public IRubyObject clear(final ThreadContext context) {
        this.getMemoryIO().setMemory(0L, this.size, (byte)0);
        return this;
    }
    
    @JRubyMethod(name = { "total", "size", "length" })
    public IRubyObject total(final ThreadContext context) {
        return RubyFixnum.newFixnum(context.getRuntime(), this.size);
    }
    
    @JRubyMethod(name = { "type_size" })
    public final IRubyObject type_size(final ThreadContext context) {
        return context.getRuntime().newFixnum(this.typeSize);
    }
    
    @JRubyMethod(name = { "write_char" }, required = 1)
    public IRubyObject write_char(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putByte(0L, Util.int8Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_int8", "put_char" }, required = 1)
    public IRubyObject put_int8(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putByte(0L, Util.int8Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_int8", "put_char" }, required = 2)
    public IRubyObject put_int8(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putByte(this.getOffset(offset), Util.int8Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_char" })
    public IRubyObject read_char(final ThreadContext context) {
        return Util.newSigned8(context.getRuntime(), this.getMemoryIO().getByte(0L));
    }
    
    @JRubyMethod(name = { "get_int8", "get_char" })
    public IRubyObject get_int8(final ThreadContext context) {
        return Util.newSigned8(context.getRuntime(), this.getMemoryIO().getByte(0L));
    }
    
    @JRubyMethod(name = { "get_int8", "get_char" }, required = 1)
    public IRubyObject get_int8(final ThreadContext context, final IRubyObject offset) {
        return Util.newSigned8(context.getRuntime(), this.getMemoryIO().getByte(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "write_uchar" }, required = 1)
    public IRubyObject write_uchar(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putByte(0L, (byte)Util.uint8Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_uint8", "put_uchar" }, required = 1)
    public IRubyObject put_uint8(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putByte(0L, (byte)Util.uint8Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_uint8", "put_uchar" }, required = 2)
    public IRubyObject put_uint8(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putByte(this.getOffset(offset), (byte)Util.uint8Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_uchar" })
    public IRubyObject read_uchar(final ThreadContext context) {
        return Util.newUnsigned8(context.getRuntime(), this.getMemoryIO().getByte(0L));
    }
    
    @JRubyMethod(name = { "get_uint8", "get_uchar" })
    public IRubyObject get_uint8(final ThreadContext context) {
        return Util.newUnsigned8(context.getRuntime(), this.getMemoryIO().getByte(0L));
    }
    
    @JRubyMethod(name = { "get_uint8", "get_uchar" }, required = 1)
    public IRubyObject get_uint8(final ThreadContext context, final IRubyObject offset) {
        return Util.newUnsigned8(context.getRuntime(), this.getMemoryIO().getByte(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "write_short" }, required = 1)
    public IRubyObject write_short(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putShort(0L, Util.int16Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_int16", "put_short" }, required = 1)
    public IRubyObject put_int16(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putShort(0L, Util.int16Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_int16", "put_short" }, required = 2)
    public IRubyObject put_int16(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putShort(this.getOffset(offset), Util.int16Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_short" })
    public IRubyObject read_short(final ThreadContext context) {
        return Util.newSigned16(context.getRuntime(), this.getMemoryIO().getShort(0L));
    }
    
    @JRubyMethod(name = { "get_int16", "get_short" })
    public IRubyObject get_int16(final ThreadContext context) {
        return Util.newSigned16(context.getRuntime(), this.getMemoryIO().getShort(0L));
    }
    
    @JRubyMethod(name = { "get_int16", "get_short" }, required = 1)
    public IRubyObject get_int16(final ThreadContext context, final IRubyObject offset) {
        return Util.newSigned16(context.getRuntime(), this.getMemoryIO().getShort(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "write_ushort" }, required = 1)
    public IRubyObject write_ushort(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putShort(0L, (short)Util.uint16Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_uint16", "put_ushort" }, required = 1)
    public IRubyObject put_uint16(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putShort(0L, (short)Util.uint16Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_uint16", "put_ushort" }, required = 2)
    public IRubyObject put_uint16(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putShort(this.getOffset(offset), (short)Util.uint16Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_ushort" })
    public IRubyObject read_ushort(final ThreadContext context) {
        return Util.newUnsigned16(context.getRuntime(), this.getMemoryIO().getShort(0L));
    }
    
    @JRubyMethod(name = { "get_uint16", "get_ushort" })
    public IRubyObject get_uint16(final ThreadContext context) {
        return Util.newUnsigned16(context.getRuntime(), this.getMemoryIO().getShort(0L));
    }
    
    @JRubyMethod(name = { "get_uint16", "get_ushort" }, required = 1)
    public IRubyObject get_uint16(final ThreadContext context, final IRubyObject offset) {
        return Util.newUnsigned16(context.getRuntime(), this.getMemoryIO().getShort(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "write_int" })
    public IRubyObject write_int(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putInt(0L, Util.int32Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_int32", "put_int" })
    public IRubyObject put_int32(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putInt(0L, Util.int32Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_int32", "put_int" }, required = 2)
    public IRubyObject put_int32(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putInt(this.getOffset(offset), Util.int32Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_int" })
    public IRubyObject read_int(final ThreadContext context) {
        return Util.newSigned32(context.getRuntime(), this.getMemoryIO().getInt(0L));
    }
    
    @JRubyMethod(name = { "get_int32", "get_int" })
    public IRubyObject get_int32(final ThreadContext context) {
        return Util.newSigned32(context.getRuntime(), this.getMemoryIO().getInt(0L));
    }
    
    @JRubyMethod(name = { "get_int32", "get_int" }, required = 1)
    public IRubyObject get_int32(final ThreadContext context, final IRubyObject offset) {
        return Util.newSigned32(context.getRuntime(), this.getMemoryIO().getInt(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "write_uint" }, required = 1)
    public IRubyObject write_uint(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putInt(0L, (int)Util.uint32Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_uint32", "put_uint" }, required = 1)
    public IRubyObject put_uint32(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putInt(0L, (int)Util.uint32Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_uint32", "put_uint" }, required = 2)
    public IRubyObject put_uint32(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putInt(this.getOffset(offset), (int)Util.uint32Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_uint" })
    public IRubyObject read_uint(final ThreadContext context) {
        return Util.newUnsigned32(context.getRuntime(), this.getMemoryIO().getInt(0L));
    }
    
    @JRubyMethod(name = { "get_uint32", "get_uint" })
    public IRubyObject get_uint32(final ThreadContext context) {
        return Util.newUnsigned32(context.getRuntime(), this.getMemoryIO().getInt(0L));
    }
    
    @JRubyMethod(name = { "get_uint32", "get_uint" }, required = 1)
    public IRubyObject get_uint32(final ThreadContext context, final IRubyObject offset) {
        return Util.newUnsigned32(context.getRuntime(), this.getMemoryIO().getInt(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "write_long_long" }, required = 1)
    public IRubyObject write_long_long(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putLong(0L, Util.int64Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_int64", "put_long_long" }, required = 1)
    public IRubyObject put_int64(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putLong(0L, Util.int64Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_int64", "put_long_long" }, required = 2)
    public IRubyObject put_int64(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putLong(this.getOffset(offset), Util.int64Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_long_long" })
    public IRubyObject read_long_long(final ThreadContext context) {
        return Util.newSigned64(context.getRuntime(), this.getMemoryIO().getLong(0L));
    }
    
    @JRubyMethod(name = { "get_int64", "get_long_long" })
    public IRubyObject get_int64(final ThreadContext context) {
        return Util.newSigned64(context.getRuntime(), this.getMemoryIO().getLong(0L));
    }
    
    @JRubyMethod(name = { "get_int64", "get_long_long" }, required = 1)
    public IRubyObject get_int64(final ThreadContext context, final IRubyObject offset) {
        return Util.newSigned64(context.getRuntime(), this.getMemoryIO().getLong(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "write_ulong_long" }, required = 1)
    public IRubyObject write_ulong_long(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putLong(0L, Util.uint64Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_uint64", "put_ulong_long" }, required = 1)
    public IRubyObject put_uint64(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putLong(0L, Util.uint64Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_uint64", "put_ulong_long" }, required = 2)
    public IRubyObject put_uint64(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putLong(this.getOffset(offset), Util.uint64Value(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_ulong_long" })
    public IRubyObject read_ulong_long(final ThreadContext context) {
        return Util.newUnsigned64(context.getRuntime(), this.getMemoryIO().getLong(0L));
    }
    
    @JRubyMethod(name = { "get_uint64", "get_ulong_long" })
    public IRubyObject get_uint64(final ThreadContext context) {
        return Util.newUnsigned64(context.getRuntime(), this.getMemoryIO().getLong(0L));
    }
    
    @JRubyMethod(name = { "get_uint64", "get_ulong_long" }, required = 1)
    public IRubyObject get_uint64(final ThreadContext context, final IRubyObject offset) {
        return Util.newUnsigned64(context.getRuntime(), this.getMemoryIO().getLong(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "write_long" }, required = 1)
    public IRubyObject write_long(final ThreadContext context, final IRubyObject value) {
        return this.put_long(context, value);
    }
    
    @JRubyMethod(name = { "put_long" }, required = 1)
    public IRubyObject put_long(final ThreadContext context, final IRubyObject value) {
        return (Platform.getPlatform().longSize() == 32) ? this.put_int32(context, value) : this.put_int64(context, value);
    }
    
    @JRubyMethod(name = { "put_long" }, required = 2)
    public IRubyObject put_long(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        return (Platform.getPlatform().longSize() == 32) ? this.put_int32(context, offset, value) : this.put_int64(context, offset, value);
    }
    
    @JRubyMethod(name = { "read_long" })
    public IRubyObject read_long(final ThreadContext context) {
        return this.get_long(context);
    }
    
    @JRubyMethod(name = { "get_long" })
    public IRubyObject get_long(final ThreadContext context) {
        return (Platform.getPlatform().longSize() == 32) ? this.get_int32(context) : this.get_int64(context);
    }
    
    @JRubyMethod(name = { "get_long" }, required = 1)
    public IRubyObject get_long(final ThreadContext context, final IRubyObject offset) {
        return (Platform.getPlatform().longSize() == 32) ? this.get_int32(context, offset) : this.get_int64(context, offset);
    }
    
    @JRubyMethod(name = { "put_ulong", "write_ulong" }, required = 1)
    public IRubyObject put_ulong(final ThreadContext context, final IRubyObject value) {
        return (Platform.getPlatform().longSize() == 32) ? this.put_uint32(context, value) : this.put_uint64(context, value);
    }
    
    @JRubyMethod(name = { "put_ulong" }, required = 2)
    public IRubyObject put_ulong(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        return (Platform.getPlatform().longSize() == 32) ? this.put_uint32(context, offset, value) : this.put_uint64(context, offset, value);
    }
    
    @JRubyMethod(name = { "read_ulong" })
    public IRubyObject read_ulong(final ThreadContext context) {
        return this.get_ulong(context);
    }
    
    @JRubyMethod(name = { "get_ulong", "read_ulong" })
    public IRubyObject get_ulong(final ThreadContext context) {
        return (Platform.getPlatform().longSize() == 32) ? this.get_uint32(context) : this.get_uint64(context);
    }
    
    @JRubyMethod(name = { "get_ulong" }, required = 1)
    public IRubyObject get_ulong(final ThreadContext context, final IRubyObject offset) {
        return (Platform.getPlatform().longSize() == 32) ? this.get_uint32(context, offset) : this.get_uint64(context, offset);
    }
    
    @JRubyMethod(name = { "write_float" }, required = 1)
    public IRubyObject write_float(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putFloat(0L, Util.floatValue(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_float32", "put_float" }, required = 1)
    public IRubyObject put_float32(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putFloat(0L, Util.floatValue(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_float32", "put_float" }, required = 2)
    public IRubyObject put_float32(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putFloat(this.getOffset(offset), Util.floatValue(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_float" })
    public IRubyObject read_float(final ThreadContext context) {
        return RubyFloat.newFloat(context.getRuntime(), this.getMemoryIO().getFloat(0L));
    }
    
    @JRubyMethod(name = { "get_float32", "get_float" })
    public IRubyObject get_float32(final ThreadContext context) {
        return RubyFloat.newFloat(context.getRuntime(), this.getMemoryIO().getFloat(0L));
    }
    
    @JRubyMethod(name = { "get_float32", "get_float" }, required = 1)
    public IRubyObject get_float32(final ThreadContext context, final IRubyObject offset) {
        return RubyFloat.newFloat(context.getRuntime(), this.getMemoryIO().getFloat(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "write_double" }, required = 1)
    public IRubyObject write_double(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putDouble(0L, Util.doubleValue(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_float64", "put_double" }, required = 1)
    public IRubyObject put_float64(final ThreadContext context, final IRubyObject value) {
        this.getMemoryIO().putDouble(0L, Util.doubleValue(value));
        return this;
    }
    
    @JRubyMethod(name = { "put_float64", "put_double" }, required = 2)
    public IRubyObject put_float64(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.getMemoryIO().putDouble(this.getOffset(offset), Util.doubleValue(value));
        return this;
    }
    
    @JRubyMethod(name = { "read_double" })
    public IRubyObject read_double(final ThreadContext context) {
        return RubyFloat.newFloat(context.getRuntime(), this.getMemoryIO().getDouble(0L));
    }
    
    @JRubyMethod(name = { "get_float64", "get_double" })
    public IRubyObject get_float64(final ThreadContext context) {
        return RubyFloat.newFloat(context.getRuntime(), this.getMemoryIO().getDouble(0L));
    }
    
    @JRubyMethod(name = { "get_float64", "get_double" }, required = 1)
    public IRubyObject get_float64(final ThreadContext context, final IRubyObject offset) {
        return RubyFloat.newFloat(context.getRuntime(), this.getMemoryIO().getDouble(this.getOffset(offset)));
    }
    
    @JRubyMethod(name = { "get_array_of_int8", "get_array_of_char" }, required = 2)
    public IRubyObject get_array_of_int8(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfSigned8(context.getRuntime(), this.io, this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_int8", "put_array_of_char" }, required = 2)
    public IRubyObject put_array_of_int8(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfSigned8(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_uint8", "get_array_of_uchar" }, required = 2)
    public IRubyObject get_array_of_uint8(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfUnsigned8(context.getRuntime(), this.io, this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_uint8", "put_array_of_uchar" }, required = 2)
    public IRubyObject put_array_of_uint8(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfUnsigned8(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_int16", "get_array_of_short" }, required = 2)
    public IRubyObject get_array_of_int16(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfSigned16(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_int16", "put_array_of_short" }, required = 2)
    public IRubyObject put_array_of_int16(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfSigned16(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_uint16", "get_array_of_ushort" }, required = 2)
    public IRubyObject get_array_of_uint16(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfUnsigned16(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_uint16", "put_array_of_ushort" }, required = 2)
    public IRubyObject put_array_of_uint16(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfUnsigned16(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_int32", "get_array_of_int" }, required = 2)
    public IRubyObject get_array_of_int32(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfSigned32(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_int32", "put_array_of_int" }, required = 2)
    public IRubyObject put_array_of_int32(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfSigned32(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_uint32", "get_array_of_uint" }, required = 2)
    public IRubyObject get_array_of_uint32(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfUnsigned32(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_uint32", "put_array_of_uint" }, required = 2)
    public IRubyObject put_array_of_uint32(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfUnsigned32(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_long" }, required = 2)
    public IRubyObject get_array_of_long(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return (Platform.getPlatform().longSize() == 32) ? this.get_array_of_int32(context, offset, length) : this.get_array_of_int64(context, offset, length);
    }
    
    @JRubyMethod(name = { "put_array_of_long" }, required = 2)
    public IRubyObject put_array_of_long(final ThreadContext context, final IRubyObject offset, final IRubyObject arr) {
        return (Platform.getPlatform().longSize() == 32) ? this.put_array_of_int32(context, offset, arr) : this.put_array_of_int64(context, offset, arr);
    }
    
    @JRubyMethod(name = { "get_array_of_ulong" }, required = 2)
    public IRubyObject get_array_of_ulong(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return (Platform.getPlatform().longSize() == 32) ? this.get_array_of_uint32(context, offset, length) : this.get_array_of_uint64(context, offset, length);
    }
    
    @JRubyMethod(name = { "put_array_of_ulong" }, required = 2)
    public IRubyObject put_array_of_ulong(final ThreadContext context, final IRubyObject offset, final IRubyObject arr) {
        return (Platform.getPlatform().longSize() == 32) ? this.put_array_of_uint32(context, offset, arr) : this.put_array_of_uint64(context, offset, arr);
    }
    
    @JRubyMethod(name = { "get_array_of_int64", "get_array_of_long_long" }, required = 2)
    public IRubyObject get_array_of_int64(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfSigned64(context.getRuntime(), this.io, this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_int64", "put_array_of_long_long" }, required = 2)
    public IRubyObject put_array_of_int64(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfSigned64(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_uint64", "get_array_of_ulong_long" }, required = 2)
    public IRubyObject get_array_of_uint64(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfUnsigned64(context.getRuntime(), this.io, this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_uint64", "put_array_of_ulong_long" }, required = 2)
    public IRubyObject put_array_of_uint64(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfUnsigned64(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_float32", "get_array_of_float" }, required = 2)
    public IRubyObject get_array_of_float(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfFloat32(context.getRuntime(), this.io, this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_float32", "put_array_of_float" }, required = 2)
    public IRubyObject put_array_of_float(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfFloat32(context.getRuntime(), this.io, this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_float64", "get_array_of_double" }, required = 2)
    public IRubyObject get_array_of_float64(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        return MemoryUtil.getArrayOfFloat64(context.getRuntime(), this.io, this.getOffset(offset), Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "put_array_of_float64", "put_array_of_double" }, required = 2)
    public IRubyObject put_array_of_float64(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        MemoryUtil.putArrayOfFloat64(context.getRuntime(), this.getMemoryIO(), this.getOffset(offset), checkArray(arrParam));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_int8", "read_array_of_char" }, required = 1)
    public IRubyObject read_array_of_int8(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfSigned8(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_int8", "write_array_of_char" }, required = 1)
    public IRubyObject write_array_of_int8(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfSigned8(context.getRuntime(), this.getMemoryIO(), 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_uint8", "read_array_of_uchar" }, required = 1)
    public IRubyObject read_array_of_uint8(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfUnsigned8(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_uint8", "write_array_of_uchar" }, required = 1)
    public IRubyObject write_array_of_uint8(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfUnsigned8(context.getRuntime(), this.getMemoryIO(), 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_int16", "read_array_of_short" }, required = 1)
    public IRubyObject read_array_of_int16(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfSigned16(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_int16", "write_array_of_short" }, required = 1)
    public IRubyObject write_array_of_int16(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfSigned16(context.getRuntime(), this.getMemoryIO(), 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_uint16", "read_array_of_ushort" }, required = 1)
    public IRubyObject read_array_of_uint16(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfUnsigned16(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_uint16", "write_array_of_ushort" }, required = 1)
    public IRubyObject write_array_of_uint16(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfUnsigned16(context.getRuntime(), this.getMemoryIO(), 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_int32", "read_array_of_int" }, required = 1)
    public IRubyObject read_array_of_int32(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfSigned32(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_int32", "write_array_of_int" }, required = 1)
    public IRubyObject write_array_of_int32(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfSigned32(context.getRuntime(), this.getMemoryIO(), 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_uint32", "read_array_of_uint" }, required = 1)
    public IRubyObject read_array_of_uint32(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfUnsigned32(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_uint32", "write_array_of_uint" }, required = 1)
    public IRubyObject write_array_of_uint32(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfUnsigned32(context.getRuntime(), this.getMemoryIO(), 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_int64", "read_array_of_long_long" }, required = 1)
    public IRubyObject read_array_of_int64(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfSigned64(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_int64", "write_array_of_long_long" }, required = 1)
    public IRubyObject write_array_of_int64(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfSigned64(context.getRuntime(), this.getMemoryIO(), 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_uint64", "read_array_of_ulong_long" }, required = 1)
    public IRubyObject read_array_of_uint64(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfUnsigned64(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_uint64", "write_array_of_ulong_long" }, required = 1)
    public IRubyObject write_array_of_uint64(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfUnsigned64(context.getRuntime(), this.getMemoryIO(), 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_long" }, required = 1)
    public IRubyObject read_array_of_long(final ThreadContext context, final IRubyObject length) {
        return (Platform.getPlatform().longSize() == 32) ? this.read_array_of_int32(context, length) : this.read_array_of_int64(context, length);
    }
    
    @JRubyMethod(name = { "write_array_of_long" }, required = 1)
    public IRubyObject write_array_of_long(final ThreadContext context, final IRubyObject ary) {
        return (Platform.getPlatform().longSize() == 32) ? this.write_array_of_int32(context, ary) : this.write_array_of_int64(context, ary);
    }
    
    @JRubyMethod(name = { "read_array_of_ulong" }, required = 1)
    public IRubyObject read_array_of_ulong(final ThreadContext context, final IRubyObject length) {
        return (Platform.getPlatform().longSize() == 32) ? this.read_array_of_uint32(context, length) : this.read_array_of_uint64(context, length);
    }
    
    @JRubyMethod(name = { "write_array_of_ulong" }, required = 1)
    public IRubyObject write_array_of_ulong(final ThreadContext context, final IRubyObject ary) {
        return (Platform.getPlatform().longSize() == 32) ? this.write_array_of_uint32(context, ary) : this.write_array_of_uint64(context, ary);
    }
    
    @JRubyMethod(name = { "read_array_of_float32", "read_array_of_float" }, required = 1)
    public IRubyObject read_array_of_float(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfFloat32(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_float32", "write_array_of_float" }, required = 1)
    public IRubyObject write_array_of_float(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfFloat32(context.getRuntime(), this.io, 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_float64", "read_array_of_double" }, required = 1)
    public IRubyObject read_array_of_float64(final ThreadContext context, final IRubyObject length) {
        return MemoryUtil.getArrayOfFloat64(context.getRuntime(), this.io, 0L, Util.int32Value(length));
    }
    
    @JRubyMethod(name = { "write_array_of_float64", "write_array_of_double" }, required = 1)
    public IRubyObject write_array_of_float64(final ThreadContext context, final IRubyObject ary) {
        MemoryUtil.putArrayOfFloat64(context.getRuntime(), this.getMemoryIO(), 0L, checkArray(ary));
        return this;
    }
    
    @JRubyMethod(name = { "read_string" })
    public IRubyObject read_string(final ThreadContext context) {
        return MemoryUtil.getTaintedString(context.getRuntime(), this.getMemoryIO(), 0L);
    }
    
    @JRubyMethod(name = { "read_string" })
    public IRubyObject read_string(final ThreadContext context, final IRubyObject rbLength) {
        return rbLength.isNil() ? MemoryUtil.getTaintedString(context.getRuntime(), this.getMemoryIO(), 0L) : MemoryUtil.getTaintedByteString(context.getRuntime(), this.getMemoryIO(), 0L, Util.int32Value(rbLength));
    }
    
    @JRubyMethod(name = { "get_string" })
    public IRubyObject get_string(final ThreadContext context) {
        return MemoryUtil.getTaintedString(context.getRuntime(), this.getMemoryIO(), 0L);
    }
    
    @JRubyMethod(name = { "get_string" })
    public IRubyObject get_string(final ThreadContext context, final IRubyObject offArg) {
        return MemoryUtil.getTaintedString(context.getRuntime(), this.getMemoryIO(), this.getOffset(offArg));
    }
    
    @JRubyMethod(name = { "get_string" })
    public IRubyObject get_string(final ThreadContext context, final IRubyObject offArg, final IRubyObject lenArg) {
        return MemoryUtil.getTaintedString(context.getRuntime(), this.getMemoryIO(), this.getOffset(offArg), Util.int32Value(lenArg));
    }
    
    @JRubyMethod(name = { "get_array_of_string" }, required = 1)
    public IRubyObject get_array_of_string(final ThreadContext context, final IRubyObject rbOffset) {
        final int POINTER_SIZE = Platform.getPlatform().addressSize() / 8;
        final Ruby runtime = context.getRuntime();
        final RubyArray arr = RubyArray.newArray(runtime);
        for (long off = this.getOffset(rbOffset); off <= this.size - POINTER_SIZE; off += POINTER_SIZE) {
            final MemoryIO mem = this.getMemoryIO().getMemoryIO(off);
            if (mem == null) {
                break;
            }
            if (mem.isNull()) {
                break;
            }
            arr.add(MemoryUtil.getTaintedString(runtime, mem, 0L));
        }
        return arr;
    }
    
    @JRubyMethod(name = { "get_array_of_string" }, required = 2)
    public IRubyObject get_array_of_string(final ThreadContext context, final IRubyObject rbOffset, final IRubyObject rbCount) {
        final int POINTER_SIZE = Platform.getPlatform().addressSize() / 8;
        final long off = this.getOffset(rbOffset);
        final int count = Util.int32Value(rbCount);
        final Ruby runtime = context.getRuntime();
        final RubyArray arr = RubyArray.newArray(runtime, count);
        for (int i = 0; i < count; ++i) {
            final MemoryIO mem = this.getMemoryIO().getMemoryIO(off + i * POINTER_SIZE);
            arr.add((mem != null && !mem.isNull()) ? MemoryUtil.getTaintedString(runtime, mem, 0L) : runtime.getNil());
        }
        return arr;
    }
    
    @JRubyMethod(name = { "read_array_of_string" })
    public IRubyObject read_array_of_string(final ThreadContext context) {
        return this.get_array_of_string(context, RubyFixnum.zero(context.getRuntime()));
    }
    
    @JRubyMethod(name = { "read_array_of_string" }, required = 1)
    public IRubyObject read_array_of_string(final ThreadContext context, final IRubyObject rbLength) {
        return this.get_array_of_string(context, RubyFixnum.zero(context.getRuntime()), rbLength);
    }
    
    @JRubyMethod(name = { "put_string" })
    public IRubyObject put_string(final ThreadContext context, final IRubyObject offArg, final IRubyObject strArg) {
        final long off = this.getOffset(offArg);
        final ByteList bl = strArg.convertToString().getByteList();
        this.getMemoryIO().putZeroTerminatedByteArray(off, bl.getUnsafeBytes(), bl.begin(), bl.length());
        return this;
    }
    
    @JRubyMethod(name = { "get_bytes" })
    public IRubyObject get_bytes(final ThreadContext context, final IRubyObject offArg, final IRubyObject lenArg) {
        return MemoryUtil.getTaintedByteString(context.getRuntime(), this.getMemoryIO(), this.getOffset(offArg), Util.int32Value(lenArg));
    }
    
    @JRubyMethod(name = { "put_bytes" }, required = 2, optional = 2)
    public IRubyObject put_bytes(final ThreadContext context, final IRubyObject[] args) {
        final long off = this.getOffset(args[0]);
        final ByteList bl = args[1].convertToString().getByteList();
        final int idx = (args.length > 2) ? Util.int32Value(args[2]) : 0;
        if (idx < 0 || idx > bl.length()) {
            throw context.getRuntime().newRangeError("Invalid string index");
        }
        final int len = (args.length > 3) ? Util.int32Value(args[3]) : (bl.length() - idx);
        if (len < 0 || len > bl.length() - idx) {
            throw context.getRuntime().newRangeError("Invalid length");
        }
        this.getMemoryIO().put(off, bl.getUnsafeBytes(), bl.begin() + idx, len);
        return this;
    }
    
    @JRubyMethod(name = { "read_pointer" })
    public IRubyObject read_pointer(final ThreadContext context) {
        return this.getPointer(context.getRuntime(), 0L);
    }
    
    @JRubyMethod(name = { "get_pointer" })
    public IRubyObject get_pointer(final ThreadContext context) {
        return this.getPointer(context.getRuntime(), 0L);
    }
    
    @JRubyMethod(name = { "get_pointer" }, required = 1)
    public IRubyObject get_pointer(final ThreadContext context, final IRubyObject offset) {
        return this.getPointer(context.getRuntime(), this.getOffset(offset));
    }
    
    private void putPointer(final ThreadContext context, final long offset, final IRubyObject value) {
        if (value instanceof Pointer) {
            this.putPointer(context, offset, (Pointer)value);
        }
        else if (value.isNil()) {
            this.getMemoryIO().putAddress(offset, 0L);
        }
        else {
            if (!value.respondsTo("to_ptr")) {
                throw context.getRuntime().newTypeError(value, context.getRuntime().fastGetModule("FFI").fastGetClass("Pointer"));
            }
            this.putPointer(context, offset, value.callMethod(context, "to_ptr"));
        }
    }
    
    private void putPointer(final ThreadContext context, final long offset, final Pointer value) {
        final MemoryIO ptr = value.getMemoryIO();
        if (ptr.isDirect()) {
            this.getMemoryIO().putMemoryIO(offset, ptr);
        }
        else {
            if (!ptr.isNull()) {
                throw context.getRuntime().newArgumentError("Cannot convert argument to pointer");
            }
            this.getMemoryIO().putAddress(offset, 0L);
        }
    }
    
    @JRubyMethod(name = { "write_pointer" })
    public IRubyObject write_pointer(final ThreadContext context, final IRubyObject value) {
        this.putPointer(context, 0L, value);
        return this;
    }
    
    @JRubyMethod(name = { "put_pointer" }, required = 1)
    public IRubyObject put_pointer(final ThreadContext context, final IRubyObject value) {
        this.putPointer(context, 0L, value);
        return this;
    }
    
    @JRubyMethod(name = { "put_pointer" }, required = 2)
    public IRubyObject put_pointer(final ThreadContext context, final IRubyObject offset, final IRubyObject value) {
        this.putPointer(context, this.getOffset(offset), value);
        return this;
    }
    
    @JRubyMethod(name = { "get_array_of_pointer" }, required = 2)
    public IRubyObject get_array_of_pointer(final ThreadContext context, final IRubyObject offset, final IRubyObject length) {
        final int POINTER_SIZE = Platform.getPlatform().addressSize / 8;
        final int count = Util.int32Value(length);
        final Ruby runtime = context.getRuntime();
        final RubyArray arr = RubyArray.newArray(runtime, count);
        final long off = this.getOffset(offset);
        for (int i = 0; i < count; ++i) {
            arr.add(this.getPointer(runtime, off + i * POINTER_SIZE));
        }
        return arr;
    }
    
    @JRubyMethod(name = { "put_array_of_pointer" }, required = 2)
    public IRubyObject put_array_of_pointer(final ThreadContext context, final IRubyObject offset, final IRubyObject arrParam) {
        final int POINTER_SIZE = Platform.getPlatform().addressSize / 8;
        final RubyArray arr = (RubyArray)arrParam;
        final int count = arr.getLength();
        final long off = this.getOffset(offset);
        for (int i = 0; i < count; ++i) {
            this.putPointer(context, off + i * POINTER_SIZE, arr.entry(i));
        }
        return this;
    }
    
    @JRubyMethod(name = { "read_array_of_pointer" }, required = 1)
    public IRubyObject read_array_of_pointer(final ThreadContext context, final IRubyObject length) {
        return this.get_array_of_pointer(context, RubyFixnum.zero(context.getRuntime()), length);
    }
    
    @JRubyMethod(name = { "write_array_of_pointer" }, required = 1)
    public IRubyObject write_array_of_pointer(final ThreadContext context, final IRubyObject arrParam) {
        return this.put_array_of_pointer(context, RubyFixnum.zero(context.getRuntime()), arrParam);
    }
    
    @JRubyMethod(name = { "put_callback" }, required = 3)
    public IRubyObject put_callback(final ThreadContext context, final IRubyObject offset, final IRubyObject proc, final IRubyObject cbInfo) {
        if (!(cbInfo instanceof CallbackInfo)) {
            throw context.getRuntime().newArgumentError("invalid CallbackInfo");
        }
        final Pointer ptr = Factory.getInstance().getCallbackManager().getCallback(context.getRuntime(), (CallbackInfo)cbInfo, proc);
        this.getMemoryIO().putMemoryIO(this.getOffset(offset), ptr.getMemoryIO());
        return this;
    }
    
    @JRubyMethod(name = { "+" }, required = 1)
    public IRubyObject op_plus(final ThreadContext context, final IRubyObject value) {
        return this.slice(context.getRuntime(), RubyNumeric.fix2long(value));
    }
    
    @JRubyMethod(name = { "order" }, required = 0)
    public final IRubyObject order(final ThreadContext context) {
        return context.getRuntime().newSymbol(this.getMemoryIO().order().equals(ByteOrder.LITTLE_ENDIAN) ? "little" : "big");
    }
    
    @JRubyMethod(name = { "order" }, required = 1)
    public final IRubyObject order(final ThreadContext context, final IRubyObject byte_order) {
        return this.order(context.getRuntime(), Util.parseByteOrder(context.getRuntime(), byte_order));
    }
    
    public abstract AbstractMemory order(final Ruby p0, final ByteOrder p1);
    
    protected abstract AbstractMemory slice(final Ruby p0, final long p1);
    
    protected abstract AbstractMemory slice(final Ruby p0, final long p1, final long p2);
    
    protected abstract Pointer getPointer(final Ruby p0, final long p1);
}
