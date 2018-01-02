// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import java.nio.ByteOrder;
import org.jruby.RubyModule;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.ByteList;
import java.nio.ByteBuffer;
import org.jruby.javasupport.JavaObject;
import org.jruby.Ruby;
import org.jruby.RubySymbol;
import org.jruby.RubyInteger;
import org.jruby.RubyHash;
import org.jruby.RubyString;
import org.jruby.RubyNumeric;
import org.jruby.RubyBignum;
import org.jruby.runtime.builtin.IRubyObject;
import java.math.BigInteger;

public final class Util
{
    private static final BigInteger UINT64_BASE;
    
    public static final byte int8Value(final IRubyObject parameter) {
        return (byte)longValue(parameter);
    }
    
    public static final short uint8Value(final IRubyObject parameter) {
        return (short)longValue(parameter);
    }
    
    public static final short int16Value(final IRubyObject parameter) {
        return (short)longValue(parameter);
    }
    
    public static final int uint16Value(final IRubyObject parameter) {
        return (int)longValue(parameter);
    }
    
    public static final int int32Value(final IRubyObject parameter) {
        return (int)longValue(parameter);
    }
    
    public static final long uint32Value(final IRubyObject parameter) {
        return longValue(parameter);
    }
    
    public static final long int64Value(final IRubyObject parameter) {
        return longValue(parameter);
    }
    
    public static final long uint64Value(final IRubyObject parameter) {
        final long value = (parameter instanceof RubyBignum) ? ((RubyBignum)parameter).getValue().longValue() : longValue(parameter);
        return value;
    }
    
    public static final float floatValue(final IRubyObject parameter) {
        return (float)RubyNumeric.num2dbl(parameter);
    }
    
    public static final double doubleValue(final IRubyObject parameter) {
        return RubyNumeric.num2dbl(parameter);
    }
    
    public static final long longValue(final IRubyObject parameter) {
        if (parameter instanceof RubyNumeric) {
            return ((RubyNumeric)parameter).getLongValue();
        }
        if (parameter.isNil()) {
            return 0L;
        }
        if (parameter instanceof RubyString) {
            return longValue((RubyString)parameter);
        }
        throw parameter.getRuntime().newRangeError("Value " + parameter + " is not an integer");
    }
    
    private static final long longValue(final RubyString parameter) {
        final CharSequence cs = parameter.asJavaString();
        if (cs.length() == 1) {
            return cs.charAt(0);
        }
        throw parameter.getRuntime().newRangeError("Value " + parameter + " is not an integer");
    }
    
    public static int intValue(final IRubyObject obj, final RubyHash enums) {
        if (obj instanceof RubyInteger) {
            return (int)((RubyInteger)obj).getLongValue();
        }
        if (!(obj instanceof RubySymbol)) {
            return (int)longValue(obj);
        }
        final IRubyObject value = enums.fastARef(obj);
        if (value.isNil()) {
            throw obj.getRuntime().newArgumentError("invalid enum value, " + obj.inspect());
        }
        return (int)longValue(value);
    }
    
    public static final IRubyObject newSigned8(final Ruby runtime, final byte value) {
        return runtime.newFixnum(value);
    }
    
    public static final IRubyObject newUnsigned8(final Ruby runtime, final byte value) {
        return runtime.newFixnum((value < 0) ? ((value & 0x7FL) + 128L) : value);
    }
    
    public static final IRubyObject newSigned16(final Ruby runtime, final short value) {
        return runtime.newFixnum(value);
    }
    
    public static final IRubyObject newUnsigned16(final Ruby runtime, final short value) {
        return runtime.newFixnum((value < 0) ? ((value & 0x7FFFL) + 32768L) : value);
    }
    
    public static final IRubyObject newSigned32(final Ruby runtime, final int value) {
        return runtime.newFixnum(value);
    }
    
    public static final IRubyObject newUnsigned32(final Ruby runtime, final int value) {
        return runtime.newFixnum((value < 0) ? ((value & 0x7FFFFFFFL) + 2147483648L) : value);
    }
    
    public static final IRubyObject newSigned64(final Ruby runtime, final long value) {
        return runtime.newFixnum(value);
    }
    
    public static final IRubyObject newUnsigned64(final Ruby runtime, final long value) {
        return (value < 0L) ? RubyBignum.newBignum(runtime, BigInteger.valueOf(value & Long.MAX_VALUE).add(Util.UINT64_BASE)) : runtime.newFixnum(value);
    }
    
    @Deprecated
    public static final <T> T convertParameter(final IRubyObject parameter, final Class<T> paramClass) {
        return paramClass.cast((parameter instanceof JavaObject) ? ((JavaObject)parameter).getValue() : parameter.toJava(paramClass));
    }
    
    public static final ByteBuffer slice(final ByteBuffer buf, final int offset) {
        final ByteBuffer tmp = buf.duplicate();
        tmp.position(offset);
        return tmp.slice();
    }
    
    public static final void checkStringSafety(final Ruby runtime, final IRubyObject value) {
        final RubyString s = value.asString();
        if (runtime.getSafeLevel() > 0 && s.isTaint()) {
            throw runtime.newSecurityError("Unsafe string parameter");
        }
        final ByteList bl = s.getByteList();
        final byte[] array = bl.getUnsafeBytes();
        for (int end = bl.length(), i = bl.begin(); i < end; ++i) {
            if (array[i] == 0) {
                throw runtime.newSecurityError("string contains null byte");
            }
        }
    }
    
    public static final void checkBounds(final Ruby runtime, final long size, final long off, final long len) {
        if ((off | len | off + len | size - (off + len)) < 0L) {
            throw runtime.newIndexError("Memory access offset=" + off + " size=" + len + " is out of bounds");
        }
    }
    
    public static final Type findType(final ThreadContext context, final IRubyObject name) {
        if (name instanceof Type) {
            return (Type)name;
        }
        final RubyModule ffi = context.getRuntime().fastGetModule("FFI");
        final IRubyObject typeDefs = ffi.fastFetchConstant("TypeDefs");
        final IRubyObject type = ((RubyHash)typeDefs).fastARef(name);
        return (Type)((type instanceof Type) ? type : ((Type)ffi.callMethod(context, "find_type", name)));
    }
    
    public static ByteOrder parseByteOrder(final Ruby runtime, final IRubyObject byte_order) {
        if (!(byte_order instanceof RubySymbol) && !(byte_order instanceof RubyString)) {
            throw runtime.newTypeError(byte_order, runtime.getSymbol());
        }
        final String orderName = byte_order.asJavaString();
        if ("network".equals(orderName) || "big".equals(orderName)) {
            return ByteOrder.BIG_ENDIAN;
        }
        if ("little".equals(orderName)) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        return ByteOrder.nativeOrder();
    }
    
    static {
        UINT64_BASE = BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE);
    }
}
