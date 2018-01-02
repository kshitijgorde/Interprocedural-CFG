// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.DoubleByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public class NtfiAttributes extends NObject
{
    static final NtfiAttributesLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NtfiAttributes(final HNObject handle, final boolean claimHandle) {
        super(handle, NtfiAttributes.NATIVE_TYPE, claimHandle);
    }
    
    public static NtfiAttributes fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NtfiAttributes fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NtfiAttributes value = new NtfiAttributes(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public final double getSharpness() {
        final DoubleByReference rValue = new DoubleByReference();
        NResult.check(NtfiAttributes.LIBRARY.NtfiAttributesGetSharpness(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final double getBackgroundUniformity() {
        final DoubleByReference rValue = new DoubleByReference();
        NResult.check(NtfiAttributes.LIBRARY.NtfiAttributesGetBackgroundUniformity(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final double getGrayscaleDensity() {
        final DoubleByReference rValue = new DoubleByReference();
        NResult.check(NtfiAttributes.LIBRARY.NtfiAttributesGetGrayscaleDensity(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NtfiAttributesLibrary)Native.loadLibrary("NBiometrics", NtfiAttributesLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NtfiAttributes.LIBRARY.NtfiAttributesTypeOf());
    }
    
    interface NtfiAttributesLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NtfiAttributesTypeOf();
        
        int NtfiAttributesGetSharpness(final HNObject p0, final DoubleByReference p1);
        
        int NtfiAttributesGetBackgroundUniformity(final HNObject p0, final DoubleByReference p1);
        
        int NtfiAttributesGetGrayscaleDensity(final HNObject p0, final DoubleByReference p1);
    }
}
