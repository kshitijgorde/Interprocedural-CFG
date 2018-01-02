// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public class TiffInfo extends NImageInfo
{
    static final TiffInfoLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    TiffInfo(final HNObject handle) {
        super(handle, TiffInfo.NATIVE_TYPE);
    }
    
    static {
        LIBRARY = (TiffInfoLibrary)Native.loadLibrary("NImages", TiffInfoLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(TiffInfo.LIBRARY.TiffInfoTypeOf());
    }
    
    interface TiffInfoLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType TiffInfoTypeOf();
    }
}
