// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.RubyString;
import org.jruby.RubyFloat;
import org.jruby.RubyArray;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;

public final class MemoryUtil
{
    public static final IRubyObject getArrayOfSigned8(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final byte[] array = new byte[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(Util.newSigned8(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfSigned8(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final byte[] array = new byte[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Util.int8Value(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final IRubyObject getArrayOfUnsigned8(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final byte[] array = new byte[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(Util.newUnsigned8(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfUnsigned8(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final byte[] array = new byte[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte)Util.uint8Value(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final IRubyObject getArrayOfSigned16(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final short[] array = new short[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(Util.newSigned16(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfSigned16(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final short[] array = new short[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Util.int16Value(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final IRubyObject getArrayOfUnsigned16(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final short[] array = new short[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(Util.newUnsigned16(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfUnsigned16(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final short[] array = new short[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (short)Util.uint16Value(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final IRubyObject getArrayOfSigned32(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final int[] array = new int[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(Util.newSigned32(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfSigned32(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final int[] array = new int[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Util.int32Value(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final IRubyObject getArrayOfUnsigned32(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final int[] array = new int[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(Util.newUnsigned32(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfUnsigned32(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final int[] array = new int[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (int)Util.uint32Value(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final IRubyObject getArrayOfSigned64(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final long[] array = new long[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(Util.newSigned64(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfSigned64(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final long[] array = new long[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Util.int64Value(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final IRubyObject getArrayOfUnsigned64(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final long[] array = new long[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(Util.newUnsigned64(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfUnsigned64(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final long[] array = new long[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Util.uint64Value(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final IRubyObject getArrayOfFloat32(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final float[] array = new float[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(RubyFloat.newFloat(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfFloat32(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final float[] array = new float[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Util.floatValue(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final IRubyObject getArrayOfFloat64(final Ruby runtime, final MemoryIO io, final long offset, final int count) {
        final double[] array = new double[count];
        io.get(offset, array, 0, array.length);
        final RubyArray arr = RubyArray.newArray(runtime, array.length);
        for (int i = 0; i < array.length; ++i) {
            arr.add(RubyFloat.newFloat(runtime, array[i]));
        }
        return arr;
    }
    
    public static final void putArrayOfFloat64(final Ruby runtime, final MemoryIO io, final long offset, final RubyArray ary) {
        final double[] array = new double[ary.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Util.doubleValue(ary.entry(i));
        }
        io.put(offset, array, 0, array.length);
    }
    
    public static final RubyString newTaintedString(final Ruby runtime, final byte[] bytes) {
        final RubyString s = RubyString.newStringNoCopy(runtime, bytes);
        s.setTaint(true);
        return s;
    }
    
    public static final RubyString getTaintedByteString(final Ruby runtime, final MemoryIO io, final long offset, final int length) {
        final byte[] bytes = new byte[length];
        io.get(offset, bytes, 0, bytes.length);
        return newTaintedString(runtime, bytes);
    }
    
    public static final IRubyObject getTaintedString(final Ruby runtime, final MemoryIO io, final long offset) {
        return newTaintedString(runtime, io.getZeroTerminatedByteArray(offset));
    }
    
    public static final IRubyObject getTaintedString(final Ruby runtime, final MemoryIO io, final long offset, final int length) {
        return newTaintedString(runtime, io.getZeroTerminatedByteArray(offset, length));
    }
}
