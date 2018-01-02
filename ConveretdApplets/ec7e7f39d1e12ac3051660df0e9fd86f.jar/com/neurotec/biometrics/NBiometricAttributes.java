// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ByteByReference;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import java.util.EnumSet;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public abstract class NBiometricAttributes extends NObject
{
    static final NBiometricAttributesLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NBiometricAttributes(final HNObject handle, final NNativeType requiredType, final boolean claimHandle) {
        super(handle, requiredType, claimHandle);
    }
    
    public final EnumSet<NBiometricType> getBiometricType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NBiometricAttributes.LIBRARY.NBiometricAttributesGetBiometricType(this.getHandle(), rValue));
        return NBiometricType.getSet(rValue.getValue());
    }
    
    public final NBiometricStatus getStatus() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NBiometricAttributes.LIBRARY.NBiometricAttributesGetStatus(this.getHandle(), rValue));
        return NBiometricStatus.get(rValue.getValue());
    }
    
    public final void setStatus(final NBiometricStatus value) {
        NResult.check(NBiometricAttributes.LIBRARY.NBiometricAttributesSetStatus(this.getHandle(), value.getValue()));
    }
    
    public final byte getQuality() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NBiometricAttributes.LIBRARY.NBiometricAttributesGetQuality(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setQuality(final byte value) {
        NResult.check(NBiometricAttributes.LIBRARY.NBiometricAttributesSetQuality(this.getHandle(), value));
    }
    
    static {
        LIBRARY = (NBiometricAttributesLibrary)Native.loadLibrary("NBiometrics", NBiometricAttributesLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NBiometricAttributes.LIBRARY.NBiometricAttributesTypeOf());
    }
    
    interface NBiometricAttributesLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NBiometricAttributesTypeOf();
        
        int NBiometricAttributesGetBiometricType(final HNObject p0, final IntByReference p1);
        
        int NBiometricAttributesGetStatus(final HNObject p0, final IntByReference p1);
        
        int NBiometricAttributesSetStatus(final HNObject p0, final int p1);
        
        int NBiometricAttributesGetQuality(final HNObject p0, final ByteByReference p1);
        
        int NBiometricAttributesSetQuality(final HNObject p0, final byte p1);
    }
}
