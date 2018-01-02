// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.BooleanByReference;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class NMonochromeImage extends NImage
{
    static final NMonochromeImageLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    protected NMonochromeImage(final HNObject handle, final boolean claimHandle, final NImage wrappedImage, final ByteBuffer wrappedPixels) {
        super(handle, NMonochromeImage.NATIVE_TYPE, claimHandle, wrappedImage, wrappedPixels);
    }
    
    public boolean get(final int x, final int y) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NMonochromeImage.LIBRARY.NMonochromeImageGetPixel(this.getHandle(), x, y, rValue));
        return rValue.getValue();
    }
    
    public void setMonochromeImage(final int x, final int y, final boolean value) {
        NResult.check(NMonochromeImage.LIBRARY.NMonochromeImageSetPixel(this.getHandle(), x, y, value));
    }
    
    static {
        LIBRARY = (NMonochromeImageLibrary)Native.loadLibrary("NImages", NMonochromeImageLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NMonochromeImage.LIBRARY.NMonochromeImageTypeOf());
    }
    
    interface NMonochromeImageLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NMonochromeImageTypeOf();
        
        int NMonochromeImageGetPixel(final HNObject p0, final int p1, final int p2, final BooleanByReference p3);
        
        int NMonochromeImageSetPixel(final HNObject p0, final int p1, final int p2, final boolean p3);
    }
}
