// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import java.util.EnumSet;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public abstract class NMatchingDetailsBase extends NObject
{
    static final NMatchingDetailsBaseLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NMatchingDetailsBase(final HNObject handle, final NNativeType requiredType, final boolean claimHandle) {
        super(handle, NMatchingDetailsBase.NATIVE_TYPE, claimHandle);
    }
    
    public EnumSet<NBiometricType> getBiometricType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NMatchingDetailsBase.LIBRARY.NMatchingDetailsBaseGetBiometricType(this.getHandle(), rValue));
        return NBiometricType.getSet(rValue.getValue());
    }
    
    public int getScore() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NMatchingDetailsBase.LIBRARY.NMatchingDetailsBaseGetScore(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NMatchingDetailsBaseLibrary)Native.loadLibrary("NBiometrics", NMatchingDetailsBaseLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NMatchingDetailsBase.LIBRARY.NMatchingDetailsBaseTypeOf());
    }
    
    interface NMatchingDetailsBaseLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NMatchingDetailsBaseTypeOf();
        
        int NMatchingDetailsBaseGetBiometricType(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsBaseGetScore(final HNObject p0, final IntByReference p1);
    }
}
