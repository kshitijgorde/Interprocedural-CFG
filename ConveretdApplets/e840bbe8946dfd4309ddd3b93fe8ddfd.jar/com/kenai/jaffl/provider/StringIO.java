// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import java.nio.charset.CharacterCodingException;
import com.kenai.jaffl.util.BufferUtil;
import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.CharsetDecoder;
import java.lang.ref.SoftReference;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.Charset;

public final class StringIO
{
    private final Charset charset;
    
    public static StringIO getStringIO() {
        return StaticDataHolder.INSTANCE;
    }
    
    public static StringIO getStringIO(final Charset charset) {
        return new StringIO(charset);
    }
    
    private StringIO(final Charset charset) {
        this.charset = charset;
    }
    
    private static CharsetEncoder getEncoder(final Charset charset) {
        final SoftReference<CharsetEncoder> ref = StaticDataHolder.ENCODER.get();
        final CharsetEncoder encoder;
        if (ref != null && (encoder = ref.get()) != null && encoder.charset() == charset) {
            return encoder;
        }
        return initEncoder(charset);
    }
    
    private static CharsetDecoder getDecoder(final Charset charset) {
        final SoftReference<CharsetDecoder> ref = StaticDataHolder.DECODER.get();
        final CharsetDecoder decoder;
        if (ref != null && (decoder = ref.get()) != null && decoder.charset() == charset) {
            return decoder;
        }
        return initDecoder(charset);
    }
    
    private static CharsetEncoder initEncoder(final Charset charset) {
        final CharsetEncoder encoder = charset.newEncoder();
        encoder.onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        StaticDataHolder.ENCODER.set(new SoftReference<CharsetEncoder>(encoder));
        return encoder;
    }
    
    private static CharsetDecoder initDecoder(final Charset charset) {
        final CharsetDecoder decoder = charset.newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        StaticDataHolder.DECODER.set(new SoftReference<CharsetDecoder>(decoder));
        return decoder;
    }
    
    public final ByteBuffer toNative(final CharSequence value, final int minSize, final boolean copyIn) {
        return toNative(getEncoder(this.charset), value, minSize, copyIn);
    }
    
    public final ByteBuffer toNative(final CharSequence value, final ByteBuffer buf) {
        return toNative(getEncoder(this.charset), value, buf);
    }
    
    private static ByteBuffer toNative(final CharsetEncoder encoder, final CharSequence value, final int minSize, final boolean copyIn) {
        final int len = (int)((Math.max(minSize, value.length()) + 1.0f) * encoder.maxBytesPerChar());
        final ByteBuffer buf = ByteBuffer.allocate(len);
        if (copyIn) {
            toNative(encoder, value, buf);
        }
        return buf;
    }
    
    private static ByteBuffer toNative(final CharsetEncoder encoder, final CharSequence value, final ByteBuffer buf) {
        buf.mark();
        try {
            encoder.reset();
            encoder.encode(CharBuffer.wrap(value), buf, true);
            encoder.flush(buf);
            nulTerminate(encoder, buf);
        }
        finally {
            buf.reset();
        }
        return buf;
    }
    
    public final CharSequence fromNative(final ByteBuffer buf, final int maxSize) {
        int end = BufferUtil.positionOf(buf, (byte)0);
        if (end < 0 || end > maxSize) {
            end = maxSize;
        }
        final int limit = buf.limit();
        buf.limit(end);
        try {
            return getDecoder(this.charset).reset().decode(buf);
        }
        catch (CharacterCodingException ex) {
            throw new Error("Illegal character data in native string", ex);
        }
        finally {
            buf.limit(limit);
        }
    }
    
    public final CharSequence fromNative(final ByteBuffer buf) {
        try {
            return getDecoder(this.charset).reset().decode(buf);
        }
        catch (CharacterCodingException ex) {
            throw new Error("Illegal character data in native string", ex);
        }
    }
    
    public final void nulTerminate(final ByteBuffer buf) {
        nulTerminate(getEncoder(this.charset), buf);
    }
    
    private static void nulTerminate(final CharsetEncoder encoder, final ByteBuffer buf) {
        int nulSize;
        for (nulSize = Math.round(encoder.maxBytesPerChar()); nulSize >= 4; nulSize -= 4) {
            buf.putInt(0);
        }
        if (nulSize >= 2) {
            buf.putShort((short)0);
            nulSize -= 2;
        }
        if (nulSize >= 1) {
            buf.put((byte)0);
        }
    }
    
    private static final class StaticDataHolder
    {
        static final StringIO INSTANCE;
        static final ThreadLocal<SoftReference<CharsetEncoder>> ENCODER;
        static final ThreadLocal<SoftReference<CharsetDecoder>> DECODER;
        
        static {
            INSTANCE = new StringIO(Charset.defaultCharset(), null);
            ENCODER = new ThreadLocal<SoftReference<CharsetEncoder>>();
            DECODER = new ThreadLocal<SoftReference<CharsetDecoder>>();
        }
    }
}
