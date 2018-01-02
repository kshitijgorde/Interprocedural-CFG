// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class NGrayscaleImage extends NImage
{
    static final NGrayscaleImageLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    protected NGrayscaleImage(final HNObject handle, final boolean claimHandle, final NImage wrappedImage, final ByteBuffer wrappedPixels) {
        super(handle, NGrayscaleImage.NATIVE_TYPE, claimHandle, wrappedImage, wrappedPixels);
    }
    
    public int get(final int x, final int y) {
        final ByteBuffer buf = ByteBuffer.allocate(2);
        NResult.check(NGrayscaleImage.LIBRARY.NGrayscaleImageGetPixel(this.getHandle(), x, y, buf));
        return buf.get() & 0xFF;
    }
    
    public void set(final int x, final int y, final byte value) {
        NResult.check(NGrayscaleImage.LIBRARY.NGrayscaleImageSetPixel(this.getHandle(), x, y, value));
    }
    
    static {
        LIBRARY = (NGrayscaleImageLibrary)Native.loadLibrary("NImages", NGrayscaleImageLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NGrayscaleImage.LIBRARY.NGrayscaleImageTypeOf());
    }
    
    interface NGrayscaleImageLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NGrayscaleImageTypeOf();
        
        int NGrayscaleImageGetPixel(final HNObject p0, final int p1, final int p2, final Buffer p3);
        
        int NGrayscaleImageSetPixel(final HNObject p0, final int p1, final int p2, final byte p3);
    }
}
