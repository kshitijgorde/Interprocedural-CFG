// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.Function;
import com.kenai.jaffl.util.EnumMapper;
import com.kenai.jaffl.ParameterFlags;
import com.kenai.jffi.ArrayFlags;
import java.nio.ByteOrder;
import com.kenai.jaffl.byref.ByReference;
import com.kenai.jaffl.provider.InvocationSession;
import com.kenai.jaffl.provider.DelegatingMemoryIO;
import com.kenai.jaffl.struct.StructUtil;
import com.kenai.jaffl.struct.Struct;
import com.kenai.jaffl.provider.StringIO;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.LongBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import com.kenai.jaffl.Address;
import com.kenai.jaffl.provider.AbstractArrayMemoryIO;
import com.kenai.jaffl.Pointer;
import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.MemoryIO;

public final class AsmRuntime
{
    public static final MemoryIO IO;
    
    public static final void marshal(final InvocationBuffer buffer, final byte[] array, final int flags) {
        if (array == null) {
            buffer.putAddress(0L);
        }
        else {
            buffer.putArray(array, 0, array.length, flags);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final short[] array, final int flags) {
        if (array == null) {
            buffer.putAddress(0L);
        }
        else {
            buffer.putArray(array, 0, array.length, flags);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final int[] array, final int flags) {
        if (array == null) {
            buffer.putAddress(0L);
        }
        else {
            buffer.putArray(array, 0, array.length, flags);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final long[] array, final int flags) {
        if (array == null) {
            buffer.putAddress(0L);
        }
        else {
            buffer.putArray(array, 0, array.length, flags);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final float[] array, final int flags) {
        if (array == null) {
            buffer.putAddress(0L);
        }
        else {
            buffer.putArray(array, 0, array.length, flags);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final double[] array, final int flags) {
        if (array == null) {
            buffer.putAddress(0L);
        }
        else {
            buffer.putArray(array, 0, array.length, flags);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final Pointer ptr, final int nativeArrayFlags) {
        if (ptr == null) {
            buffer.putAddress(0L);
        }
        else if (ptr.isDirect()) {
            buffer.putAddress(ptr.address());
        }
        else {
            if (!(ptr instanceof AbstractArrayMemoryIO)) {
                throw new IllegalArgumentException("unsupported argument type" + ptr.getClass());
            }
            final AbstractArrayMemoryIO aio = (AbstractArrayMemoryIO)ptr;
            buffer.putArray(aio.array(), aio.offset(), aio.length(), nativeArrayFlags);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final Address ptr) {
        if (ptr == null) {
            buffer.putAddress(0L);
        }
        else {
            buffer.putAddress(ptr.nativeAddress());
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final ByteBuffer buf, final int flags) {
        if (buf == null) {
            buffer.putAddress(0L);
        }
        else if (buf.hasArray()) {
            buffer.putArray(buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), flags);
        }
        else {
            buffer.putDirectBuffer(buf, buf.position(), buf.remaining());
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final ShortBuffer buf, final int flags) {
        if (buf == null) {
            buffer.putAddress(0L);
        }
        else if (buf.hasArray()) {
            buffer.putArray(buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), flags);
        }
        else {
            buffer.putDirectBuffer(buf, buf.position() << 1, buf.remaining() << 1);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final IntBuffer buf, final int flags) {
        if (buf == null) {
            buffer.putAddress(0L);
        }
        else if (buf.hasArray()) {
            buffer.putArray(buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), flags);
        }
        else {
            buffer.putDirectBuffer(buf, buf.position() << 2, buf.remaining() << 2);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final LongBuffer buf, final int flags) {
        if (buf == null) {
            buffer.putAddress(0L);
        }
        else if (buf.hasArray()) {
            buffer.putArray(buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), flags);
        }
        else {
            buffer.putDirectBuffer(buf, buf.position() << 3, buf.remaining() << 3);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final FloatBuffer buf, final int flags) {
        if (buf == null) {
            buffer.putAddress(0L);
        }
        else if (buf.hasArray()) {
            buffer.putArray(buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), flags);
        }
        else {
            buffer.putDirectBuffer(buf, buf.position() << 2, buf.remaining() << 2);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final DoubleBuffer buf, final int flags) {
        if (buf == null) {
            buffer.putAddress(0L);
        }
        else if (buf.hasArray()) {
            buffer.putArray(buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), flags);
        }
        else {
            buffer.putDirectBuffer(buf, buf.position() << 3, buf.remaining() << 3);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final CharSequence cs) {
        if (cs == null) {
            buffer.putAddress(0L);
        }
        else {
            final ByteBuffer buf = StringIO.getStringIO().toNative(cs, cs.length(), true);
            buffer.putArray(buf.array(), buf.arrayOffset(), buf.remaining(), 5);
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final Struct parameter, final int parameterFlags, final int nativeArrayFlags) {
        if (parameter == null) {
            buffer.putAddress(0L);
        }
        else {
            final com.kenai.jaffl.MemoryIO io = StructUtil.getMemoryIO(parameter, parameterFlags);
            if (io instanceof AbstractArrayMemoryIO) {
                final AbstractArrayMemoryIO aio = (AbstractArrayMemoryIO)io;
                buffer.putArray(aio.array(), aio.offset(), aio.length(), nativeArrayFlags);
            }
            else if (io.isDirect()) {
                buffer.putAddress(io.address());
            }
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final Struct[] parameter, final int parameterFlags, final int nativeArrayFlags) {
        if (parameter == null) {
            buffer.putAddress(0L);
        }
        else {
            com.kenai.jaffl.MemoryIO io = StructUtil.getMemoryIO(parameter[0], parameterFlags);
            if (!(io instanceof DelegatingMemoryIO)) {
                throw new RuntimeException("Struct array must be backed by contiguous array");
            }
            io = ((DelegatingMemoryIO)io).getDelegatedMemoryIO();
            if (io instanceof AbstractArrayMemoryIO) {
                final AbstractArrayMemoryIO aio = (AbstractArrayMemoryIO)io;
                buffer.putArray(aio.array(), aio.offset(), aio.length(), nativeArrayFlags);
            }
            else if (io.isDirect()) {
                buffer.putAddress(io.address());
            }
        }
    }
    
    public static final void marshal(final InvocationSession session, final InvocationBuffer buffer, final ByReference parameter, final int flags) {
        if (parameter == null) {
            buffer.putAddress(0L);
        }
        else {
            final ByteBuffer buf = ByteBuffer.allocate(parameter.nativeSize()).order(ByteOrder.nativeOrder());
            buf.clear();
            if (ArrayFlags.isIn(flags)) {
                parameter.marshal(buf);
            }
            buffer.putArray(buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), flags);
            if (ArrayFlags.isOut(flags)) {
                session.addPostInvoke(new InvocationSession.PostInvoke() {
                    public void postInvoke() {
                        parameter.unmarshal(buf);
                    }
                });
            }
        }
    }
    
    public static final void marshal(final InvocationSession session, final InvocationBuffer buffer, final StringBuilder parameter, final int inout, final int nflags) {
        if (parameter == null) {
            buffer.putAddress(0L);
        }
        else {
            final StringIO io = StringIO.getStringIO();
            final ByteBuffer buf = io.toNative(parameter, parameter.capacity(), ParameterFlags.isIn(inout));
            buffer.putArray(buf.array(), buf.arrayOffset(), buf.remaining(), nflags);
            if (ParameterFlags.isOut(inout)) {
                session.addPostInvoke(new InvocationSession.PostInvoke() {
                    public void postInvoke() {
                        parameter.delete(0, parameter.length()).append(io.fromNative(buf, parameter.capacity()));
                    }
                });
            }
        }
    }
    
    public static final void marshal(final InvocationSession session, final InvocationBuffer buffer, final StringBuffer parameter, final int inout, final int nflags) {
        if (parameter == null) {
            buffer.putAddress(0L);
        }
        else {
            final StringIO io = StringIO.getStringIO();
            final ByteBuffer buf = io.toNative(parameter, parameter.capacity(), ParameterFlags.isIn(inout));
            buffer.putArray(buf.array(), buf.arrayOffset(), buf.remaining(), nflags);
            if (ParameterFlags.isOut(inout)) {
                session.addPostInvoke(new InvocationSession.PostInvoke() {
                    public void postInvoke() {
                        parameter.delete(0, parameter.length()).append(io.fromNative(buf, parameter.capacity()));
                    }
                });
            }
        }
    }
    
    public static final void marshal(final InvocationSession session, final InvocationBuffer buffer, final CharSequence[] strings, final int inout, final int nativeArrayFlags) {
        if (strings == null) {
            buffer.putAddress(0L);
        }
        else {
            final AllocatedDirectMemoryIO[] pointers = new AllocatedDirectMemoryIO[strings.length];
            final StringIO io = StringIO.getStringIO();
            if (ParameterFlags.isIn(inout)) {
                for (int i = 0; i < strings.length; ++i) {
                    if (strings[i] != null) {
                        final ByteBuffer buf = io.toNative(strings[i], strings[i].length(), ParameterFlags.isIn(inout));
                        final AllocatedDirectMemoryIO ptr = new AllocatedDirectMemoryIO(buf.remaining(), false);
                        ptr.put(0L, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining());
                        pointers[i] = ptr;
                    }
                    else {
                        pointers[i] = null;
                    }
                }
            }
            final Pointer[] tmp = new Pointer[pointers.length];
            System.arraycopy(pointers, 0, tmp, 0, tmp.length);
            marshal(session, buffer, tmp, inout, nativeArrayFlags);
            session.addPostInvoke(new InvocationSession.PostInvoke() {
                public void postInvoke() {
                    if (ParameterFlags.isOut(inout)) {
                        for (int i = 0; i < pointers.length; ++i) {
                            if (tmp[i] != null) {
                                strings[i] = tmp[i].getString(0L);
                            }
                        }
                    }
                }
            });
        }
    }
    
    public static final void marshal(final InvocationBuffer buffer, final Enum parameter) {
        buffer.putInt(EnumMapper.getInstance().intValue(parameter));
    }
    
    public static final void marshal(final InvocationSession session, final InvocationBuffer buffer, final Pointer[] pointers, final int inout, final int nativeArrayFlags) {
        if (pointers == null) {
            buffer.putAddress(0L);
        }
        else if (Pointer.SIZE == 32) {
            final int[] raw = new int[pointers.length + 1];
            for (int i = 0; i < pointers.length; ++i) {
                if (pointers[i] != null && !pointers[i].isDirect()) {
                    throw new IllegalArgumentException("invalid pointer in array at index " + i);
                }
                raw[i] = ((pointers[i] != null) ? ((int)pointers[i].address()) : 0);
            }
            buffer.putArray(raw, 0, raw.length, nativeArrayFlags);
            if (ParameterFlags.isOut(inout)) {
                session.addPostInvoke(new InvocationSession.PostInvoke() {
                    public void postInvoke() {
                        for (int i = 0; i < pointers.length; ++i) {
                            pointers[i] = MemoryUtil.newPointer(raw[i]);
                        }
                    }
                });
            }
        }
        else {
            final long[] raw2 = new long[pointers.length + 1];
            for (int i = 0; i < pointers.length; ++i) {
                if (pointers[i] != null && !pointers[i].isDirect()) {
                    throw new IllegalArgumentException("invalid pointer in array at index " + i);
                }
                raw2[i] = ((pointers[i] != null) ? pointers[i].address() : 0L);
            }
            buffer.putArray(raw2, 0, raw2.length, nativeArrayFlags);
            if (ParameterFlags.isOut(inout)) {
                session.addPostInvoke(new InvocationSession.PostInvoke() {
                    public void postInvoke() {
                        for (int i = 0; i < pointers.length; ++i) {
                            pointers[i] = MemoryUtil.newPointer(raw2[i]);
                        }
                    }
                });
            }
        }
    }
    
    public static final UnsatisfiedLinkError newUnsatisifiedLinkError(final String msg) {
        return new UnsatisfiedLinkError(msg);
    }
    
    public static final HeapInvocationBuffer newHeapInvocationBuffer(final Function function) {
        return new HeapInvocationBuffer(function);
    }
    
    public static final String returnString(final long ptr) {
        if (ptr == 0L) {
            return null;
        }
        final ByteBuffer buf = ByteBuffer.wrap(AsmRuntime.IO.getZeroTerminatedByteArray(ptr));
        return StringIO.getStringIO().fromNative(buf).toString();
    }
    
    public static final Pointer pointerValue(final long ptr) {
        return (ptr != 0L) ? new DirectMemoryIO(ptr) : null;
    }
    
    public static final Pointer pointerValue(final int ptr) {
        return (ptr != 0) ? new DirectMemoryIO(ptr & 0xFFFFFFFFL) : null;
    }
    
    public static final com.kenai.jaffl.MemoryIO newMemoryIO(final long ptr) {
        return (ptr == 0L) ? null : new DirectMemoryIO(ptr);
    }
    
    public static final void useMemory(final long ptr, final Struct s) {
        s.useMemory(new DirectMemoryIO(ptr));
    }
    
    public static final boolean isDirect(final Pointer ptr) {
        return ptr == null || ptr.isDirect();
    }
    
    public static final int intValue(final Pointer ptr) {
        return (ptr != null) ? ((int)ptr.address()) : 0;
    }
    
    public static final long longValue(final Pointer ptr) {
        return (ptr != null) ? ptr.address() : 0L;
    }
    
    public static final boolean isDirect(final Struct s) {
        return s == null || StructUtil.isDirect(s);
    }
    
    public static final boolean isDirect(final Struct s, final int flags) {
        return s == null || StructUtil.getMemoryIO(s, flags).isDirect();
    }
    
    public static final int intValue(final Struct s) {
        return (s != null) ? ((int)StructUtil.getMemoryIO(s).address()) : 0;
    }
    
    public static final long longValue(final Struct s) {
        return (s != null) ? StructUtil.getMemoryIO(s).address() : 0L;
    }
    
    static {
        IO = MemoryIO.getInstance();
    }
}
