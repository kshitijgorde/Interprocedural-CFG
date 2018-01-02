// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class NEMatchingDetails extends NXMatchingDetails
{
    static final NEMatchingDetailsLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NEMatchingDetails(final HNObject handle) {
        super(handle, NEMatchingDetails.NATIVE_TYPE, false);
    }
    
    static NEMatchingDetails fromHandle(final HNObject handle) {
        return new NEMatchingDetails(handle);
    }
    
    static {
        LIBRARY = (NEMatchingDetailsLibrary)Native.loadLibrary("NBiometrics", NEMatchingDetailsLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NEMatchingDetails.LIBRARY.NEMatchingDetailsTypeOf());
    }
    
    interface NEMatchingDetailsLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NEMatchingDetailsTypeOf();
    }
}
