// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public class IHeadInfo extends NImageInfo
{
    static final IHeadInfoLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    IHeadInfo(final HNObject handle) {
        super(handle, IHeadInfo.NATIVE_TYPE);
    }
    
    static {
        LIBRARY = (IHeadInfoLibrary)Native.loadLibrary("NImages", IHeadInfoLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(IHeadInfo.LIBRARY.IHeadInfoTypeOf());
    }
    
    interface IHeadInfoLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType IHeadInfoTypeOf();
    }
}
