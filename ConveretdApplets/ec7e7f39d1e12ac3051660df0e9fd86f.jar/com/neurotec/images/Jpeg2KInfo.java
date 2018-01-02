// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.FloatByReference;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class Jpeg2KInfo extends NImageInfo
{
    static final Jpeg2KInfoLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    Jpeg2KInfo(final HNObject handle) {
        super(handle, Jpeg2KInfo.NATIVE_TYPE);
    }
    
    public Jpeg2KProfile getProfile() {
        final IntByReference rValue = new IntByReference();
        NResult.check(Jpeg2KInfo.LIBRARY.Jpeg2KInfoGetProfile(this.getHandle(), rValue));
        return Jpeg2KProfile.get(rValue.getValue());
    }
    
    public void setProfile(final Jpeg2KProfile value) {
        NResult.check(Jpeg2KInfo.LIBRARY.Jpeg2KInfoSetProfile(this.getHandle(), value.getValue()));
    }
    
    public float isRatio() {
        final FloatByReference rValue = new FloatByReference();
        NResult.check(Jpeg2KInfo.LIBRARY.Jpeg2KInfoGetRatio(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setRatio(final float value) {
        NResult.check(Jpeg2KInfo.LIBRARY.Jpeg2KInfoSetRatio(this.getHandle(), value));
    }
    
    static {
        LIBRARY = (Jpeg2KInfoLibrary)Native.loadLibrary("NImages", Jpeg2KInfoLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(Jpeg2KInfo.LIBRARY.Jpeg2KInfoTypeOf());
    }
    
    interface Jpeg2KInfoLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType Jpeg2KInfoTypeOf();
        
        int Jpeg2KInfoGetProfile(final HNObject p0, final IntByReference p1);
        
        int Jpeg2KInfoSetProfile(final HNObject p0, final int p1);
        
        int Jpeg2KInfoGetRatio(final HNObject p0, final FloatByReference p1);
        
        int Jpeg2KInfoSetRatio(final HNObject p0, final float p1);
    }
}
