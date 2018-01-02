// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public class BmpInfo extends NImageInfo
{
    static final BmpInfoLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    BmpInfo(final HNObject handle) {
        super(handle, BmpInfo.NATIVE_TYPE);
    }
    
    static {
        LIBRARY = (BmpInfoLibrary)Native.loadLibrary("NImages", BmpInfoLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(BmpInfo.LIBRARY.BmpInfoTypeOf());
    }
    
    interface BmpInfoLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType BmpInfoTypeOf();
    }
}
