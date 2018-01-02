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

public class NEAttributes extends NBiometricAttributes
{
    static final NEAttributesLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NEAttributes(final HNObject handle, final boolean claimHandle) {
        super(handle, NEAttributes.NATIVE_TYPE, claimHandle);
    }
    
    public static NEAttributes fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NEAttributes fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NEAttributes value = new NEAttributes(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public final NEPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NEAttributes.LIBRARY.NEAttributesGetPosition(this.getHandle(), rValue));
        return NEPosition.get(rValue.getValue());
    }
    
    static {
        LIBRARY = (NEAttributesLibrary)Native.loadLibrary("NBiometrics", NEAttributesLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NEAttributes.LIBRARY.NEAttributesTypeOf());
    }
    
    interface NEAttributesLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NEAttributesTypeOf();
        
        int NEAttributesGetPosition(final HNObject p0, final IntByReference p1);
    }
}
