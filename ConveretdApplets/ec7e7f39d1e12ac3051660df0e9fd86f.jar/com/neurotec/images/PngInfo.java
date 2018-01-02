// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class PngInfo extends NImageInfo
{
    static final PngInfoLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    PngInfo(final HNObject handle) {
        super(handle, PngInfo.NATIVE_TYPE);
    }
    
    public int getCompressionLevel() {
        final IntByReference rValue = new IntByReference();
        NResult.check(PngInfo.LIBRARY.PngInfoGetCompressionLevel(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setCompressionLevel(final int value) {
        NResult.check(PngInfo.LIBRARY.PngInfoSetCompressionLevel(this.getHandle(), value));
    }
    
    static {
        LIBRARY = (PngInfoLibrary)Native.loadLibrary("NImages", PngInfoLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(PngInfo.LIBRARY.PngInfoTypeOf());
    }
    
    interface PngInfoLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType PngInfoTypeOf();
        
        int PngInfoGetCompressionLevel(final HNObject p0, final IntByReference p1);
        
        int PngInfoSetCompressionLevel(final HNObject p0, final int p1);
    }
}
