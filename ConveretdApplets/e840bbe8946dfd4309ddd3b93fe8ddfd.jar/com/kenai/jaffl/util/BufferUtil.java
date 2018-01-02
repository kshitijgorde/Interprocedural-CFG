// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.util;

import com.kenai.jaffl.Platform;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.CharBuffer;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;

public final class BufferUtil
{
    public static final void putString(final ByteBuffer buf, final Charset charset, final String value) {
        putCharSequence(buf, charset, value);
    }
    
    public static final String getString(final ByteBuffer buf, final Charset charset) {
        return getCharSequence(buf, charset).toString();
    }
    
    public static final void putCharSequence(final ByteBuffer buf, final Charset charset, final CharSequence value) {
        putCharSequence(buf, charset.newEncoder(), value);
    }
    
    public static final void putCharSequence(final ByteBuffer buf, final CharsetEncoder encoder, final CharSequence value) {
        encoder.reset().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).encode(CharBuffer.wrap(value), buf, true);
        encoder.flush(buf);
        final int nulSize = Math.round(encoder.maxBytesPerChar());
        if (nulSize == 4) {
            buf.putInt(0);
        }
        else if (nulSize == 2) {
            buf.putShort((short)0);
        }
        else if (nulSize == 1) {
            buf.put((byte)0);
        }
    }
    
    public static final CharSequence getCharSequence(final ByteBuffer buf, final Charset charset) {
        return getCharSequence(buf, charset.newDecoder());
    }
    
    public static final CharSequence getCharSequence(final ByteBuffer buf, final CharsetDecoder decoder) {
        final ByteBuffer buffer = buf.slice();
        int end = indexOf(buffer, (byte)0);
        if (end < 0) {
            end = buffer.limit();
        }
        buffer.position(0).limit(end);
        try {
            return decoder.reset().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).decode(buffer);
        }
        catch (CharacterCodingException ex) {
            throw new Error("Illegal character data in native string", ex);
        }
    }
    
    public static final int positionOf(final ByteBuffer buf, final byte value) {
        if (buf.hasArray()) {
            final byte[] array = buf.array();
            final int offset = buf.arrayOffset();
            for (int limit = buf.limit(), pos = buf.position(); pos < limit; ++pos) {
                if (array[offset + pos] == value) {
                    return pos;
                }
            }
        }
        else {
            for (int limit2 = buf.limit(), pos2 = buf.position(); pos2 < limit2; ++pos2) {
                if (buf.get(pos2) == value) {
                    return pos2;
                }
            }
        }
        return -1;
    }
    
    public static final int indexOf(final ByteBuffer buf, final byte value) {
        if (buf.hasArray()) {
            final byte[] array = buf.array();
            final int begin = buf.arrayOffset() + buf.position();
            for (int end = begin + buf.limit(), offset = 0; offset < end && offset > -1; ++offset) {
                if (array[begin + offset] == value) {
                    return offset;
                }
            }
        }
        else {
            final int begin2 = buf.position();
            for (int offset2 = 0; offset2 < buf.limit(); ++offset2) {
                if (buf.get(begin2 + offset2) == value) {
                    return offset2;
                }
            }
        }
        return -1;
    }
    
    public static final long getAddress(final ByteBuffer buf, final int position) {
        return AddressIO.INSTANCE.getAddress(buf, position);
    }
    
    public static final void putAddress(final ByteBuffer buf, final int position, final long address) {
        AddressIO.INSTANCE.putAddress(buf, position, address);
    }
    
    public static ByteBuffer slice(final ByteBuffer buffer, final int position) {
        final ByteBuffer tmp = buffer.duplicate();
        tmp.position(position);
        return tmp.slice();
    }
    
    public static ByteBuffer slice(final ByteBuffer buffer, final int position, final int size) {
        final ByteBuffer tmp = buffer.duplicate();
        tmp.position(position).limit(position + size);
        return tmp.slice();
    }
    
    private interface AddressIO
    {
        public static final AddressIO INSTANCE = (Platform.getPlatform().addressSize() == 32) ? AddressIO32.IMPL : AddressIO64.IMPL;
        
        long getAddress(final ByteBuffer p0, final int p1);
        
        void putAddress(final ByteBuffer p0, final int p1, final long p2);
        
        public static class AddressIO32 implements AddressIO
        {
            public static final AddressIO IMPL;
            
            public long getAddress(final ByteBuffer io, final int offset) {
                return io.getInt(offset);
            }
            
            public void putAddress(final ByteBuffer io, final int offset, final long address) {
                io.putInt(offset, (int)address);
            }
            
            static {
                IMPL = new AddressIO32();
            }
        }
        
        public static class AddressIO64 implements AddressIO
        {
            public static final AddressIO IMPL;
            
            public long getAddress(final ByteBuffer io, final int offset) {
                return io.getLong(offset);
            }
            
            public void putAddress(final ByteBuffer io, final int offset, final long address) {
                io.putLong(offset, address);
            }
            
            static {
                IMPL = new AddressIO64();
            }
        }
    }
}
