// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class NRgbImage extends NImage
{
    static final NRgbImageLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    protected NRgbImage(final HNObject handle, final boolean claimHandle, final NImage wrappedImage, final ByteBuffer wrappedPixels) {
        super(handle, NRgbImage.NATIVE_TYPE, claimHandle, wrappedImage, wrappedPixels);
    }
    
    public NRgb get(final int x, final int y) {
        final NRgb value = new NRgb();
        NResult.check(NRgbImage.LIBRARY.NRgbImageGetPixel(this.getHandle(), x, y, value.getData()));
        return value;
    }
    
    public void setRgbImage(final int x, final int y, final NRgb value) {
        NResult.check(NRgbImage.LIBRARY.NRgbImageSetPixel(this.getHandle(), x, y, value.getData()));
    }
    
    static {
        LIBRARY = (NRgbImageLibrary)Native.loadLibrary("NImages", NRgbImageLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NRgbImage.LIBRARY.NRgbImageTypeOf());
    }
    
    interface NRgbImageLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NRgbImageTypeOf();
        
        int NRgbImageGetPixel(final HNObject p0, final int p1, final int p2, final NRgb.NRgbData p3);
        
        int NRgbImageSetPixel(final HNObject p0, final int p1, final int p2, final NRgb.NRgbData p3);
    }
}
