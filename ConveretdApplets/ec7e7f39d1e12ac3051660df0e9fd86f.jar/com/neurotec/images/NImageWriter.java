// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.Pointer;
import com.neurotec.lang.NCore;
import com.neurotec.jna.NMemory;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.sun.jna.ptr.PointerByReference;
import java.nio.ByteBuffer;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NImageWriter extends NObject
{
    private NImageFormat format;
    static final NImageWriterLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NImageWriter(final HNObject handle) {
        super(handle, NImageWriter.NATIVE_TYPE, false);
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageWriter.LIBRARY.NImageWriterGetFormat(handle, rhValue));
        this.format = NImageFormat.fromHandle(rhValue.getValue());
    }
    
    public static NImageWriter fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NImageWriter fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NImageWriter reader = new NImageWriter(handle);
        if (ownsHandle) {
            reader.claimHandle();
        }
        return reader;
    }
    
    protected final void dispose(final boolean disposing) {
        this.format = null;
        super.dispose(disposing);
    }
    
    public void write(final NImage image, final NImageInfo info, final int flags) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        NResult.check(NImageWriter.LIBRARY.NImageWriterWrite(this.getHandle(), image.getHandle(), (info == null) ? HNObject.NULL : info.getHandle(), flags));
    }
    
    public ByteBuffer detachBuffer() {
        ByteBuffer buffer = null;
        final PointerByReference rBuffer = new PointerByReference();
        Pointer pBuffer = null;
        final NativeSizeByReference rBufferSize = new NativeSizeByReference();
        NResult.check(NImageWriter.LIBRARY.NImageWriterDetachBuffer(this.getHandle(), rBuffer, rBufferSize));
        try {
            final long size = rBufferSize.getValue().longValue();
            pBuffer = rBuffer.getValue();
            buffer = new NMemory(pBuffer, size, false).getByteBuffer(0L, size);
        }
        finally {
            if (buffer == null) {
                NCore.free(pBuffer);
            }
        }
        return buffer;
    }
    
    public NImageFormat getFormat() {
        this.check();
        return this.format;
    }
    
    static {
        LIBRARY = (NImageWriterLibrary)Native.loadLibrary("NImages", NImageWriterLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NImageWriter.LIBRARY.NImageWriterTypeOf());
    }
    
    interface NImageWriterLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NImageWriterTypeOf();
        
        int NImageWriterWrite(final HNObject p0, final HNObject p1, final HNObject p2, final int p3);
        
        int NImageWriterDetachBuffer(final HNObject p0, final PointerByReference p1, final NativeSizeByReference p2);
        
        int NImageWriterGetFormat(final HNObject p0, final HNObjectByReference p1);
    }
}
