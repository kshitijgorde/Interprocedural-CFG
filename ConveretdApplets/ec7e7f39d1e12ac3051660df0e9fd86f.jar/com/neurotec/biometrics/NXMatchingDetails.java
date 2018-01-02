// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public abstract class NXMatchingDetails extends NObject
{
    static final NXMatchingDetailsLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NXMatchingDetails(final HNObject handle, final NNativeType requiredType, final boolean claimHandle) {
        super(handle, NXMatchingDetails.NATIVE_TYPE, claimHandle);
    }
    
    public int getMatchedIndex() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NXMatchingDetails.LIBRARY.NXMatchingDetailsGetMatchedIndex(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NXMatchingDetailsLibrary)Native.loadLibrary("NBiometrics", NXMatchingDetailsLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NXMatchingDetails.LIBRARY.NXMatchingDetailsTypeOf());
    }
    
    interface NXMatchingDetailsLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NXMatchingDetailsTypeOf();
        
        int NXMatchingDetailsGetMatchedIndex(final HNObject p0, final IntByReference p1);
    }
}
