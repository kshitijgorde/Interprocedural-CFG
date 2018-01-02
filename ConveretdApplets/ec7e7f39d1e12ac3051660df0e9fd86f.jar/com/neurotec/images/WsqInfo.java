// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.FloatByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class WsqInfo extends NImageInfo
{
    static final WsqInfoLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    WsqInfo(final HNObject handle) {
        super(handle, WsqInfo.NATIVE_TYPE);
    }
    
    public float getBitRate() {
        final FloatByReference rValue = new FloatByReference();
        NResult.check(WsqInfo.LIBRARY.WsqInfoGetBitRate(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setBitRate(final float value) {
        NResult.check(WsqInfo.LIBRARY.WsqInfoSetBitRate(this.getHandle(), value));
    }
    
    static {
        LIBRARY = (WsqInfoLibrary)Native.loadLibrary("NImages", WsqInfoLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(WsqInfo.LIBRARY.WsqInfoTypeOf());
    }
    
    interface WsqInfoLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType WsqInfoTypeOf();
        
        int WsqInfoGetBitRate(final HNObject p0, final FloatByReference p1);
        
        int WsqInfoSetBitRate(final HNObject p0, final float p1);
    }
}
