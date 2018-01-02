// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class NLMatchingDetails extends NXMatchingDetails
{
    static final NLMatchingDetailsLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NLMatchingDetails(final HNObject handle) {
        super(handle, NLMatchingDetails.NATIVE_TYPE, false);
    }
    
    static NLMatchingDetails fromHandle(final HNObject handle) {
        return new NLMatchingDetails(handle);
    }
    
    static {
        LIBRARY = (NLMatchingDetailsLibrary)Native.loadLibrary("NBiometrics", NLMatchingDetailsLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NLMatchingDetails.LIBRARY.NLMatchingDetailsTypeOf());
    }
    
    interface NLMatchingDetailsLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NLMatchingDetailsTypeOf();
    }
}
