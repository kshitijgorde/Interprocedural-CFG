// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.struct;

import java.nio.charset.Charset;
import com.kenai.jaffl.util.EnumMapper;
import com.kenai.jaffl.Address;
import com.kenai.jaffl.ParameterFlags;
import com.kenai.jaffl.Type;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.MemoryIO;

public abstract class Struct
{
    final Info __info;
    
    protected Struct() {
        this.__info = new Info();
    }
    
    Struct(final boolean isUnion) {
        this.__info = new Info();
        this.__info.resetIndex = isUnion;
    }
    
    public void useMemory(final MemoryIO address) {
        this.__info.useMemory(address);
    }
    
    public void useMemory(final com.kenai.jaffl.Pointer address) {
        this.__info.useMemory(MemoryIO.wrap(address));
    }
    
    public java.lang.String toString() {
        final StringBuilder sb = new StringBuilder();
        final Field[] fields = this.getClass().getDeclaredFields();
        sb.append(this.getClass().getSimpleName() + " { \n");
        final java.lang.String fieldPrefix = "    ";
        for (final Field field : fields) {
            try {
                sb.append("    ");
                sb.append(field.getName()).append(" = ");
                sb.append(field.get(this).toString());
                sb.append("\n");
            }
            catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
    
    protected final void arrayBegin() {
        this.__info.resetIndex = false;
    }
    
    protected final void arrayEnd() {
        this.__info.resetIndex = this.__info.isUnion;
    }
    
    protected <T extends Member> T[] array(final T[] array) {
        this.arrayBegin();
        try {
            final Class<?> arrayClass = array.getClass().getComponentType();
            final Constructor<?> ctor = arrayClass.getDeclaredConstructor(arrayClass.getEnclosingClass());
            final Object[] parameters = { this };
            for (int i = 0; i < array.length; ++i) {
                array[i] = (T)ctor.newInstance(parameters);
            }
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Signed8[] array(final Signed8[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Signed8();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Unsigned8[] array(final Unsigned8[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Unsigned8();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Signed16[] array(final Signed16[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Signed16();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Unsigned16[] array(final Unsigned16[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Unsigned16();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Signed32[] array(final Signed32[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Signed32();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Unsigned32[] array(final Unsigned32[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Unsigned32();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Signed64[] array(final Signed64[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Signed64();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Unsigned64[] array(final Unsigned64[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Unsigned64();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final SignedLong[] array(final SignedLong[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new SignedLong();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final UnsignedLong[] array(final UnsignedLong[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new UnsignedLong();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Float[] array(final Float[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Float();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Double[] array(final Double[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Double();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Address[] array(final Address[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Address();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final Pointer[] array(final Pointer[] array) {
        this.arrayBegin();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Pointer();
        }
        this.arrayEnd();
        return array;
    }
    
    protected final <T extends Struct> T inner(final Struct struct) {
        final int salign = struct.__info.getMinimumAlignment();
        final int off = salign + (this.__info.size - 1 & ~(salign - 1));
        struct.__info.enclosing = this;
        struct.__info.offset = off;
        this.__info.size = off + struct.__info.size;
        return (T)struct;
    }
    
    protected static final class Constants
    {
        static final int LONG_SIZE;
        static final int ADDRESS_SIZE;
        static final long LONG_MASK;
        static final int LONG_ALIGN;
        static final int INT64_ALIGN;
        static final int DOUBLE_ALIGN;
        static final int FLOAT_ALIGN;
        
        static {
            LONG_SIZE = Type.SLONG.size() * 8;
            ADDRESS_SIZE = Type.ADDRESS.size() * 8;
            LONG_MASK = ((Constants.LONG_SIZE == 32) ? 2147483647L : Long.MAX_VALUE);
            LONG_ALIGN = Type.SLONG.alignment() * 8;
            INT64_ALIGN = Type.SLONGLONG.alignment() * 8;
            DOUBLE_ALIGN = Type.DOUBLE.alignment() * 8;
            FLOAT_ALIGN = Type.FLOAT.alignment() * 8;
        }
    }
    
    static final class Info
    {
        Struct enclosing;
        int offset;
        MemoryIO io;
        int size;
        int minAlign;
        boolean isUnion;
        boolean resetIndex;
        
        Info() {
            this.enclosing = null;
            this.offset = 0;
            this.size = 0;
            this.minAlign = 1;
            this.isUnion = false;
            this.resetIndex = false;
        }
        
        public final MemoryIO getMemoryIO(final int flags) {
            return (this.enclosing != null) ? this.enclosing.__info.getMemoryIO(flags) : ((this.io != null) ? this.io : (this.io = this.allocateMemory(flags)));
        }
        
        public final MemoryIO getMemoryIO() {
            return this.getMemoryIO(16);
        }
        
        final boolean isDirect() {
            return (this.enclosing != null && this.enclosing.__info.isDirect()) || (this.io != null && this.io.isDirect());
        }
        
        final int size() {
            return this.size;
        }
        
        final int getMinimumAlignment() {
            return this.minAlign;
        }
        
        private final MemoryIO allocateMemory(final int flags) {
            if (ParameterFlags.isDirect(flags)) {
                return MemoryIO.allocateDirect(this.size(), true);
            }
            return MemoryIO.allocate(this.size());
        }
        
        public final void useMemory(final MemoryIO io) {
            this.io = io;
        }
        
        protected final int addField(final int sizeBits, final int alignBits, final Offset offset) {
            this.size = Math.max(this.size, offset.intValue() + (sizeBits >> 3));
            this.minAlign = Math.max(this.minAlign, alignBits >> 3);
            return offset.intValue();
        }
        
        protected final int addField(final int sizeBits, final int alignBits) {
            final int mask = (alignBits >> 3) - 1;
            int off = this.resetIndex ? 0 : this.size;
            if ((off & mask) != 0x0) {
                off = (off & ~mask) + (alignBits >> 3);
            }
            this.size = Math.max(this.size, off + (sizeBits >> 3));
            this.minAlign = Math.max(this.minAlign, alignBits >> 3);
            return off;
        }
    }
    
    public static final class Offset extends Number
    {
        private final int offset;
        
        public Offset(final int offset) {
            this.offset = offset;
        }
        
        public int intValue() {
            return this.offset;
        }
        
        public long longValue() {
            return this.offset;
        }
        
        public float floatValue() {
            return this.offset;
        }
        
        public double doubleValue() {
            return this.offset;
        }
    }
    
    public abstract class AbstractMember implements Member
    {
        private final int offset;
        
        protected AbstractMember(final Struct struct, final int size) {
            this(struct, size, size);
        }
        
        protected AbstractMember(final int size, final int align, final Offset offset) {
            this.offset = Struct.this.__info.addField(size, align, offset);
        }
        
        protected AbstractMember(final int size, final int align) {
            this.offset = Struct.this.__info.addField(size, align);
        }
        
        protected AbstractMember(final Type type) {
            this.offset = Struct.this.__info.addField(type.size() * 8, type.alignment() * 8);
        }
        
        protected AbstractMember(final Type type, final Offset offset) {
            this.offset = Struct.this.__info.addField(type.size() * 8, type.alignment() * 8, offset);
        }
        
        public final MemoryIO getMemoryIO() {
            return Struct.this.__info.getMemoryIO();
        }
        
        public final Struct struct() {
            return Struct.this;
        }
        
        public final long offset() {
            return this.offset + Struct.this.__info.offset;
        }
    }
    
    public abstract class AbstractBoolean extends AbstractMember
    {
        public AbstractBoolean(final Type type) {
            super(type);
        }
        
        protected AbstractBoolean(final Type type, final Offset offset) {
            super(type, offset);
        }
        
        public abstract boolean get();
        
        public abstract void set(final boolean p0);
        
        public java.lang.String toString() {
            return java.lang.Boolean.toString(this.get());
        }
    }
    
    public final class Boolean extends AbstractBoolean
    {
        public Boolean() {
            super(Type.SCHAR);
        }
        
        public final boolean get() {
            return (this.getMemoryIO().getByte(this.offset()) & 0x1) != 0x0;
        }
        
        public final void set(final boolean value) {
            this.getMemoryIO().putByte(this.offset(), (byte)(value ? 1 : 0));
        }
    }
    
    public final class WBOOL extends AbstractBoolean
    {
        public WBOOL() {
            super(Type.SINT);
        }
        
        public final boolean get() {
            return (this.getMemoryIO().getInt(this.offset()) & 0x1) != 0x0;
        }
        
        public final void set(final boolean value) {
            this.getMemoryIO().putInt(this.offset(), value ? 1 : 0);
        }
    }
    
    protected abstract class NumberField extends Number implements Member
    {
        private final int offset;
        
        protected NumberField(final Struct struct, final int size) {
            this(struct, size, size);
        }
        
        protected NumberField(final Struct struct, final int size, final Offset offset) {
            this(struct, size, size, offset);
        }
        
        protected NumberField(final int size, final int align, final Offset offset) {
            this.offset = Struct.this.__info.addField(size, align, offset);
        }
        
        protected NumberField(final int size, final int align) {
            this.offset = Struct.this.__info.addField(size, align);
        }
        
        public final MemoryIO getMemoryIO() {
            return Struct.this.__info.getMemoryIO();
        }
        
        public final Struct struct() {
            return Struct.this;
        }
        
        public final long offset() {
            return this.offset + Struct.this.__info.offset;
        }
        
        public abstract void set(final Number p0);
        
        public double doubleValue() {
            return this.longValue();
        }
        
        public float floatValue() {
            return this.intValue();
        }
        
        public long longValue() {
            return this.intValue();
        }
        
        public java.lang.String toString() {
            return Integer.toString(this.intValue(), 10);
        }
    }
    
    public class Signed8 extends NumberField
    {
        public Signed8() {
            super(8);
        }
        
        public Signed8(final Offset offset) {
            super(8, offset);
        }
        
        public final byte get() {
            return this.getMemoryIO().getByte(this.offset());
        }
        
        public final void set(final byte value) {
            this.getMemoryIO().putByte(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putByte(this.offset(), value.byteValue());
        }
        
        public final byte byteValue() {
            return this.get();
        }
        
        public final short shortValue() {
            return this.get();
        }
        
        public final int intValue() {
            return this.get();
        }
    }
    
    public class Unsigned8 extends NumberField
    {
        public Unsigned8() {
            super(8);
        }
        
        public Unsigned8(final Offset offset) {
            super(8, offset);
        }
        
        public final short get() {
            final short value = this.getMemoryIO().getByte(this.offset());
            return (value < 0) ? ((short)((value & 0x7F) + 128)) : value;
        }
        
        public final void set(final short value) {
            this.getMemoryIO().putByte(this.offset(), (byte)value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putByte(this.offset(), value.byteValue());
        }
        
        public final short shortValue() {
            return this.get();
        }
        
        public final int intValue() {
            return this.get();
        }
    }
    
    public class Signed16 extends NumberField
    {
        public Signed16() {
            super(16);
        }
        
        public Signed16(final Offset offset) {
            super(16, offset);
        }
        
        public final short get() {
            return this.getMemoryIO().getShort(this.offset());
        }
        
        public final void set(final short value) {
            this.getMemoryIO().putShort(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putShort(this.offset(), value.shortValue());
        }
        
        public final short shortValue() {
            return this.get();
        }
        
        public final int intValue() {
            return this.get();
        }
    }
    
    public class Unsigned16 extends NumberField
    {
        public Unsigned16() {
            super(16);
        }
        
        public Unsigned16(final Offset offset) {
            super(16, offset);
        }
        
        public final int get() {
            final int value = this.getMemoryIO().getShort(this.offset());
            return (value < 0) ? ((value & 0x7FFF) + 32768) : value;
        }
        
        public final void set(final int value) {
            this.getMemoryIO().putShort(this.offset(), (short)value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putShort(this.offset(), value.shortValue());
        }
        
        public final int intValue() {
            return this.get();
        }
    }
    
    public class Signed32 extends NumberField
    {
        public Signed32() {
            super(32);
        }
        
        public Signed32(final Offset offset) {
            super(32, offset);
        }
        
        public final int get() {
            return this.getMemoryIO().getInt(this.offset());
        }
        
        public final void set(final int value) {
            this.getMemoryIO().putInt(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putInt(this.offset(), value.intValue());
        }
        
        public final int intValue() {
            return this.get();
        }
    }
    
    public class Unsigned32 extends NumberField
    {
        public Unsigned32() {
            super(32);
        }
        
        public Unsigned32(final Offset offset) {
            super(32, offset);
        }
        
        public final long get() {
            final long value = this.getMemoryIO().getInt(this.offset());
            return (value < 0L) ? ((value & 0x7FFFFFFFL) + 2147483648L) : value;
        }
        
        public final void set(final long value) {
            this.getMemoryIO().putInt(this.offset(), (int)value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putInt(this.offset(), value.intValue());
        }
        
        public final int intValue() {
            return (int)this.get();
        }
        
        public final long longValue() {
            return this.get();
        }
    }
    
    public class Signed64 extends NumberField
    {
        public Signed64() {
            super(64, Constants.INT64_ALIGN);
        }
        
        public Signed64(final Offset offset) {
            super(64, Constants.INT64_ALIGN, offset);
        }
        
        public final long get() {
            return this.getMemoryIO().getLong(this.offset());
        }
        
        public final void set(final long value) {
            this.getMemoryIO().putLong(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putLong(this.offset(), value.longValue());
        }
        
        public final int intValue() {
            return (int)this.get();
        }
        
        public final long longValue() {
            return this.get();
        }
        
        public final java.lang.String toString() {
            return Long.toString(this.get());
        }
    }
    
    public class Unsigned64 extends NumberField
    {
        public Unsigned64() {
            super(64, Constants.INT64_ALIGN);
        }
        
        public Unsigned64(final Offset offset) {
            super(64, Constants.INT64_ALIGN, offset);
        }
        
        public final long get() {
            return this.getMemoryIO().getLong(this.offset());
        }
        
        public final void set(final long value) {
            this.getMemoryIO().putLong(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putLong(this.offset(), value.longValue());
        }
        
        public final int intValue() {
            return (int)this.get();
        }
        
        public final long longValue() {
            return this.get();
        }
        
        public final java.lang.String toString() {
            return Long.toString(this.get());
        }
    }
    
    public class SignedLong extends NumberField
    {
        public SignedLong() {
            super(Constants.LONG_SIZE, Constants.LONG_ALIGN);
        }
        
        public SignedLong(final Offset offset) {
            super(Constants.LONG_SIZE, Constants.LONG_ALIGN, offset);
        }
        
        public final long get() {
            return this.getMemoryIO().getNativeLong(this.offset());
        }
        
        public final void set(final long value) {
            this.getMemoryIO().putNativeLong(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putNativeLong(this.offset(), value.longValue());
        }
        
        public final int intValue() {
            return (int)this.get();
        }
        
        public final long longValue() {
            return this.get();
        }
        
        public final java.lang.String toString() {
            return Long.toString(this.get());
        }
    }
    
    public class UnsignedLong extends NumberField
    {
        public UnsignedLong() {
            super(Constants.LONG_SIZE, Constants.LONG_ALIGN);
        }
        
        public UnsignedLong(final Offset offset) {
            super(Constants.LONG_SIZE, Constants.LONG_ALIGN, offset);
        }
        
        public final long get() {
            final long value = this.getMemoryIO().getNativeLong(this.offset());
            return (value < 0L) ? ((value & Constants.LONG_MASK) + Constants.LONG_MASK + 1L) : value;
        }
        
        public final void set(final long value) {
            this.getMemoryIO().putNativeLong(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putNativeLong(this.offset(), value.longValue());
        }
        
        public final int intValue() {
            return (int)this.get();
        }
        
        public final long longValue() {
            return this.get();
        }
        
        public final java.lang.String toString() {
            return Long.toString(this.get());
        }
    }
    
    public class Float extends NumberField
    {
        public Float() {
            super(32, Constants.FLOAT_ALIGN);
        }
        
        public Float(final Offset offset) {
            super(32, Constants.FLOAT_ALIGN, offset);
        }
        
        public final float get() {
            return this.getMemoryIO().getFloat(this.offset());
        }
        
        public final void set(final float value) {
            this.getMemoryIO().putFloat(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putFloat(this.offset(), value.floatValue());
        }
        
        public final int intValue() {
            return (int)this.get();
        }
        
        public final double doubleValue() {
            return this.get();
        }
        
        public final float floatValue() {
            return this.get();
        }
        
        public final long longValue() {
            return (long)this.get();
        }
        
        public final java.lang.String toString() {
            return java.lang.String.valueOf(this.get());
        }
    }
    
    public final class Double extends NumberField
    {
        public Double() {
            super(64, Constants.DOUBLE_ALIGN);
        }
        
        public Double(final Offset offset) {
            super(64, Constants.DOUBLE_ALIGN, offset);
        }
        
        public final double get() {
            return this.getMemoryIO().getDouble(this.offset());
        }
        
        public final void set(final double value) {
            this.getMemoryIO().putDouble(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putDouble(this.offset(), value.doubleValue());
        }
        
        public final int intValue() {
            return (int)this.get();
        }
        
        public final long longValue() {
            return (long)this.get();
        }
        
        public final float floatValue() {
            return (float)this.get();
        }
        
        public final double doubleValue() {
            return this.get();
        }
        
        public final java.lang.String toString() {
            return java.lang.String.valueOf(this.get());
        }
    }
    
    public class Address extends NumberField
    {
        public Address() {
            super(Constants.ADDRESS_SIZE);
        }
        
        public Address(final Offset offset) {
            super(Constants.ADDRESS_SIZE, offset);
        }
        
        public final com.kenai.jaffl.Address get() {
            final long value = this.getMemoryIO().getAddress(this.offset());
            return (value != 0L) ? new com.kenai.jaffl.Address(value) : null;
        }
        
        public final void set(final com.kenai.jaffl.Address value) {
            this.getMemoryIO().putAddress(this.offset(), (value != null) ? value.nativeAddress() : 0L);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putAddress(this.offset(), value.longValue());
        }
        
        public final int intValue() {
            return this.get().intValue();
        }
        
        public final long longValue() {
            return this.get().longValue();
        }
        
        public final java.lang.String toString() {
            return this.get().toString();
        }
    }
    
    public class Pointer extends NumberField
    {
        public Pointer() {
            super(Constants.ADDRESS_SIZE);
        }
        
        public Pointer(final Offset offset) {
            super(Constants.ADDRESS_SIZE, offset);
        }
        
        public final com.kenai.jaffl.Pointer get() {
            return this.getMemoryIO().getPointer(this.offset());
        }
        
        public final int size() {
            return com.kenai.jaffl.Address.SIZE;
        }
        
        public final void set(final com.kenai.jaffl.Pointer value) {
            this.getMemoryIO().putPointer(this.offset(), value);
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putAddress(this.offset(), value.longValue());
        }
        
        public final int intValue() {
            return (int)this.getMemoryIO().getAddress(this.offset());
        }
        
        public final long longValue() {
            return this.getMemoryIO().getAddress(this.offset());
        }
        
        public final java.lang.String toString() {
            return this.get().toString();
        }
    }
    
    protected abstract class EnumField<E> extends NumberField
    {
        protected final Class<E> enumClass;
        
        public EnumField(final Struct struct, final int size, final Class<E> enumClass) {
            this(struct, size, size, enumClass);
        }
        
        public EnumField(final Struct struct, final int size, final Offset offset, final Class<E> enumClass) {
            this(struct, size, size, offset, enumClass);
        }
        
        public EnumField(final int size, final int align, final Class<E> enumClass) {
            super(size, align);
            this.enumClass = enumClass;
        }
        
        public EnumField(final int size, final int align, final Offset offset, final Class<E> enumClass) {
            super(size, align, offset);
            this.enumClass = enumClass;
        }
        
        public abstract E get();
        
        public final java.lang.String toString() {
            return this.get().toString();
        }
    }
    
    public class Enum8<E extends java.lang.Enum<E>> extends EnumField<E>
    {
        public Enum8(final Class<E> enumClass) {
            super(8, enumClass);
        }
        
        public final E get() {
            return EnumMapper.getInstance().valueOf(this.intValue(), this.enumClass);
        }
        
        public final void set(final E value) {
            this.getMemoryIO().putByte(this.offset(), (byte)EnumMapper.getInstance().intValue(value));
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putByte(this.offset(), value.byteValue());
        }
        
        public final int intValue() {
            return this.getMemoryIO().getByte(this.offset());
        }
    }
    
    public class Enum16<E extends java.lang.Enum<E>> extends EnumField<E>
    {
        public Enum16(final Class<E> enumClass) {
            super(16, enumClass);
        }
        
        public final E get() {
            return EnumMapper.getInstance().valueOf(this.intValue(), this.enumClass);
        }
        
        public final void set(final E value) {
            this.getMemoryIO().putShort(this.offset(), (short)EnumMapper.getInstance().intValue(value));
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putShort(this.offset(), value.shortValue());
        }
        
        public final int intValue() {
            return this.getMemoryIO().getShort(this.offset());
        }
    }
    
    public class Enum32<E extends java.lang.Enum<E>> extends EnumField<E>
    {
        public Enum32(final Class<E> enumClass) {
            super(32, enumClass);
        }
        
        public final E get() {
            return EnumMapper.getInstance().valueOf(this.intValue(), this.enumClass);
        }
        
        public final void set(final E value) {
            this.getMemoryIO().putInt(this.offset(), EnumMapper.getInstance().intValue(value));
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putInt(this.offset(), value.intValue());
        }
        
        public final int intValue() {
            return this.getMemoryIO().getInt(this.offset());
        }
    }
    
    public class Enum64<E extends java.lang.Enum<E>> extends EnumField<E>
    {
        public Enum64(final Class<E> enumClass) {
            super(64, Constants.INT64_ALIGN, enumClass);
        }
        
        public final E get() {
            return EnumMapper.getInstance().valueOf(this.intValue(), this.enumClass);
        }
        
        public final void set(final E value) {
            this.getMemoryIO().putLong(this.offset(), EnumMapper.getInstance().intValue(value));
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putLong(this.offset(), value.longValue());
        }
        
        public final int intValue() {
            return (int)this.longValue();
        }
        
        public final long longValue() {
            return this.getMemoryIO().getLong(this.offset());
        }
    }
    
    public class EnumLong<E extends java.lang.Enum<E>> extends EnumField<E>
    {
        public EnumLong(final Class<E> enumClass) {
            super(Constants.LONG_SIZE, Constants.LONG_ALIGN, enumClass);
        }
        
        public final E get() {
            return EnumMapper.getInstance().valueOf(this.intValue(), this.enumClass);
        }
        
        public final void set(final E value) {
            this.getMemoryIO().putNativeLong(this.offset(), EnumMapper.getInstance().intValue(value));
        }
        
        public void set(final Number value) {
            this.getMemoryIO().putNativeLong(this.offset(), value.longValue());
        }
        
        public final int intValue() {
            return (int)this.longValue();
        }
        
        public final long longValue() {
            return this.getMemoryIO().getNativeLong(this.offset());
        }
    }
    
    public class Enum<T extends java.lang.Enum<T>> extends Enum32<T>
    {
        public Enum(final Class<T> enumClass) {
            super(enumClass);
        }
    }
    
    public abstract class String extends AbstractMember
    {
        private final Charset charset;
        private final int length;
        
        protected String(final int size, final int align, final int length, final Charset cs) {
            super(size, align);
            this.length = length;
            this.charset = cs;
        }
        
        protected String(final int size, final int align, final Offset offset, final int length, final Charset cs) {
            super(size, align, offset);
            this.length = length;
            this.charset = cs;
        }
        
        public final int length() {
            return this.length;
        }
        
        protected abstract MemoryIO getStringMemory();
        
        public final java.lang.String get() {
            return this.getStringMemory().getString(0L, this.length, this.charset);
        }
        
        public final void set(final java.lang.String value) {
            this.getStringMemory().putString(0L, value, this.length, this.charset);
        }
        
        public final java.lang.String toString() {
            return this.get();
        }
    }
    
    public class UTFString extends String
    {
        public UTFString(final int length, final Charset cs) {
            super(length * 8, 8, length, cs);
        }
        
        protected MemoryIO getStringMemory() {
            return this.getMemoryIO().slice(this.offset(), this.length());
        }
    }
    
    public class UTF8String extends UTFString
    {
        public UTF8String(final int size) {
            super(size, Charset.forName("UTF-8"));
        }
    }
    
    public class AsciiString extends UTFString
    {
        public AsciiString(final int size) {
            super(size, Charset.forName("ASCII"));
        }
    }
    
    public class UTFStringRef extends String
    {
        public UTFStringRef(final int length, final Charset cs) {
            super(Constants.ADDRESS_SIZE, Constants.ADDRESS_SIZE, length, cs);
        }
        
        public UTFStringRef(final Struct struct, final Charset cs) {
            this(struct, Integer.MAX_VALUE, cs);
        }
        
        protected MemoryIO getStringMemory() {
            return this.getMemoryIO().getMemoryIO(this.offset(), this.length());
        }
    }
    
    public class UTF8StringRef extends UTFStringRef
    {
        public UTF8StringRef(final int size) {
            super(size, Charset.forName("UTF-8"));
        }
        
        public UTF8StringRef() {
            super(Integer.MAX_VALUE, Charset.forName("UTF-8"));
        }
    }
    
    public class AsciiStringRef extends UTFStringRef
    {
        public AsciiStringRef(final int size) {
            super(size, Charset.forName("ASCII"));
        }
        
        public AsciiStringRef() {
            super(Integer.MAX_VALUE, Charset.forName("ASCII"));
        }
    }
    
    public final class Padding extends AbstractMember
    {
        public Padding(final Type type, final int length) {
            super(type.size() * 8 * length, type.alignment() * 8);
        }
    }
    
    protected interface Member
    {
        Struct struct();
        
        MemoryIO getMemoryIO();
        
        long offset();
    }
}
