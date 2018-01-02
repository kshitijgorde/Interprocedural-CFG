// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.BooleanByReference;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class JpegInfo extends NImageInfo
{
    static final JpegInfoLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    JpegInfo(final HNObject handle) {
        super(handle, JpegInfo.NATIVE_TYPE);
    }
    
    public int getQuality() {
        final IntByReference rValue = new IntByReference();
        NResult.check(JpegInfo.LIBRARY.JpegInfoGetQuality(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setQuality(final int value) {
        NResult.check(JpegInfo.LIBRARY.JpegInfoSetQuality(this.getHandle(), value));
    }
    
    public boolean isLossless() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(JpegInfo.LIBRARY.JpegInfoIsLossless(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setLossless(final boolean value) {
        NResult.check(JpegInfo.LIBRARY.JpegInfoSetLossless(this.getHandle(), value));
    }
    
    static {
        LIBRARY = (JpegInfoLibrary)Native.loadLibrary("NImages", JpegInfoLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(JpegInfo.LIBRARY.JpegInfoTypeOf());
    }
    
    interface JpegInfoLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType JpegInfoTypeOf();
        
        int JpegInfoGetQuality(final HNObject p0, final IntByReference p1);
        
        int JpegInfoSetQuality(final HNObject p0, final int p1);
        
        int JpegInfoIsLossless(final HNObject p0, final BooleanByReference p1);
        
        int JpegInfoSetLossless(final HNObject p0, final boolean p1);
    }
}
