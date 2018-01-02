// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import java.nio.ByteBuffer;
import com.neurotec.lang.NObject;

public final class NImageReader extends NObject
{
    private NImageFormat format;
    private ByteBuffer buffer;
    private int position;
    static final NImageReaderLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NImageReader(final HNObject handle, final ByteBuffer buffer) {
        super(handle, NImageReader.NATIVE_TYPE, false);
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageReader.LIBRARY.NImageReaderGetFormat(handle, rhValue));
        this.format = NImageFormat.fromHandle(rhValue.getValue());
        this.buffer = buffer;
        this.position = buffer.position();
    }
    
    static NImageReader fromHandle(final HNObject handle, final ByteBuffer buffer) {
        return fromHandle(handle, true, buffer);
    }
    
    private static NImageReader fromHandle(final HNObject handle, final boolean ownsHandle, final ByteBuffer buffer) {
        final NImageReader reader = new NImageReader(handle, buffer);
        if (ownsHandle) {
            reader.claimHandle();
        }
        return reader;
    }
    
    public static NImageReader fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NImageReader fromHandle(final HNObject handle, final boolean ownsHandle) {
        return fromHandle(handle, ownsHandle, null);
    }
    
    protected final void dispose(final boolean disposing) {
        this.buffer = null;
        this.format = null;
        super.dispose(disposing);
    }
    
    public NImage read() {
        return this.read(0);
    }
    
    public NImage read(final int flags) {
        NImage image = null;
        HNObject hImage = null;
        final HNObjectByReference rhImage = new HNObjectByReference();
        NResult.check(NImageReader.LIBRARY.NImageReaderRead(this.getHandle(), flags, null, rhImage));
        try {
            hImage = rhImage.getValue();
            image = (HNObject.NULL.equals(hImage) ? null : NImage.fromHandle(hImage));
        }
        finally {
            if (image == null) {
                NObject.free(hImage);
            }
            this.shiftBuffer();
        }
        return image;
    }
    
    public NImageWithInfo read(final boolean returnImageInfo) {
        return this.read(0, returnImageInfo);
    }
    
    public NImageWithInfo read(final int flags, final boolean returnImageInfo) {
        NImageWithInfo result = null;
        boolean ok = false;
        NImage image = null;
        HNObject hImage = null;
        final HNObjectByReference rhImage = new HNObjectByReference();
        NImageInfo info = null;
        HNObject hInfo = null;
        HNObjectByReference rhInfo = null;
        if (returnImageInfo) {
            rhInfo = new HNObjectByReference();
        }
        NResult.check(NImageReader.LIBRARY.NImageReaderRead(this.getHandle(), flags, returnImageInfo ? rhInfo : null, rhImage));
        try {
            hImage = rhImage.getValue();
            image = (HNObject.NULL.equals(hImage) ? null : NImage.fromHandle(hImage));
            if (returnImageInfo) {
                hInfo = rhInfo.getValue();
                info = (HNObject.NULL.equals(hInfo) ? null : NImageInfo.fromHandle(hInfo));
            }
            result = new NImageWithInfo(image, info);
            ok = true;
        }
        finally {
            if (!ok) {
                if (image == null) {
                    NObject.free(hImage);
                }
                else {
                    image.dispose();
                }
                if (info == null) {
                    NObject.free(hInfo);
                }
                else {
                    info.dispose();
                }
            }
            this.shiftBuffer();
        }
        return result;
    }
    
    public NImageInfo readInfo() {
        return this.readInfo(0);
    }
    
    public NImageInfo readInfo(final int flags) {
        NImageInfo info = null;
        HNObject hInfo = null;
        final HNObjectByReference rhInfo = new HNObjectByReference();
        NResult.check(NImageReader.LIBRARY.NImageReaderReadInfo(this.getHandle(), flags, rhInfo));
        try {
            hInfo = rhInfo.getValue();
            info = (HNObject.NULL.equals(hInfo) ? null : NImageInfo.fromHandle(hInfo));
        }
        finally {
            if (info == null) {
                NObject.free(hInfo);
            }
            this.shiftBuffer();
        }
        return info;
    }
    
    public NImageFormat getFormat() {
        this.check();
        return this.format;
    }
    
    private void shiftBuffer() {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NImageReader.LIBRARY.NImageReaderGetReadSize(this.getHandle(), rSize));
        this.buffer.position(this.position + rSize.getValue().intValue());
    }
    
    static {
        LIBRARY = (NImageReaderLibrary)Native.loadLibrary("NImages", NImageReaderLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NImageReader.LIBRARY.NImageReaderTypeOf());
    }
    
    interface NImageReaderLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NImageReaderTypeOf();
        
        int NImageReaderGetFormat(final HNObject p0, final HNObjectByReference p1);
        
        int NImageReaderRead(final HNObject p0, final int p1, final HNObjectByReference p2, final HNObjectByReference p3);
        
        int NImageReaderReadInfo(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NImageReaderGetReadSize(final HNObject p0, final NativeSizeByReference p1);
    }
}
