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

public class NFAttributes extends NBiometricAttributes
{
    static final NFAttributesLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NFAttributes(final HNObject handle, final boolean claimHandle) {
        super(handle, NFAttributes.NATIVE_TYPE, claimHandle);
    }
    
    public static NFAttributes fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NFAttributes fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NFAttributes value = new NFAttributes(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public final NFImpressionType getImpressionType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFAttributes.LIBRARY.NFAttributesGetImpressionType(this.getHandle(), rValue));
        return NFImpressionType.get(rValue.getValue());
    }
    
    public final NFPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFAttributes.LIBRARY.NFAttributesGetPosition(this.getHandle(), rValue));
        return NFPosition.get(rValue.getValue());
    }
    
    static {
        LIBRARY = (NFAttributesLibrary)Native.loadLibrary("NBiometrics", NFAttributesLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NFAttributes.LIBRARY.NFAttributesTypeOf());
    }
    
    interface NFAttributesLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NFAttributesTypeOf();
        
        int NFAttributesGetImpressionType(final HNObject p0, final IntByReference p1);
        
        int NFAttributesGetPosition(final HNObject p0, final IntByReference p1);
    }
}
