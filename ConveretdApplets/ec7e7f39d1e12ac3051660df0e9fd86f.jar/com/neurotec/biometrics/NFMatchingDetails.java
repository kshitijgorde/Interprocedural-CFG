// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NResult;
import com.neurotec.lang.NIndexPair;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class NFMatchingDetails extends NXMatchingDetails
{
    static final NFMatchingDetailsLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NFMatchingDetails(final HNObject handle) {
        super(handle, NFMatchingDetails.NATIVE_TYPE, false);
    }
    
    static NFMatchingDetails fromHandle(final HNObject handle) {
        return new NFMatchingDetails(handle);
    }
    
    public NIndexPair[] getMatedMinutiae() {
        final int size = NResult.check(NFMatchingDetails.LIBRARY.NFMatchingDetailsGetMatedMinutiae(this.getHandle(), null, 0));
        final NIndexPair.NIndexPairData[] structures = (NIndexPair.NIndexPairData[])new NIndexPair.NIndexPairData().toArray(size);
        final NIndexPair[] values = new NIndexPair[size];
        NResult.check(NFMatchingDetails.LIBRARY.NFMatchingDetailsGetMatedMinutiae(this.getHandle(), structures, size));
        for (int i = 0; i < values.length; ++i) {
            values[i] = new NIndexPair(structures[i]);
        }
        return values;
    }
    
    public int getCenterX() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFMatchingDetails.LIBRARY.NFMatchingDetailsGetCenterX(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getCenterY() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFMatchingDetails.LIBRARY.NFMatchingDetailsGetCenterY(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getRotation() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NFMatchingDetails.LIBRARY.NFMatchingDetailsGetRotation(this.getHandle(), rValue));
        return rValue.getValue() & 0xFF;
    }
    
    public int getTranslationX() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFMatchingDetails.LIBRARY.NFMatchingDetailsGetTranslationX(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getTranslationY() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFMatchingDetails.LIBRARY.NFMatchingDetailsGetTranslationY(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NFMatchingDetailsLibrary)Native.loadLibrary("NBiometrics", NFMatchingDetailsLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NFMatchingDetails.LIBRARY.NFMatchingDetailsTypeOf());
    }
    
    interface NFMatchingDetailsLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NFMatchingDetailsTypeOf();
        
        int NFMatchingDetailsGetCenterX(final HNObject p0, final IntByReference p1);
        
        int NFMatchingDetailsGetCenterY(final HNObject p0, final IntByReference p1);
        
        int NFMatchingDetailsGetRotation(final HNObject p0, final ByteByReference p1);
        
        int NFMatchingDetailsGetTranslationX(final HNObject p0, final IntByReference p1);
        
        int NFMatchingDetailsGetTranslationY(final HNObject p0, final IntByReference p1);
        
        int NFMatchingDetailsGetMatedMinutiae(final HNObject p0, final NIndexPair.NIndexPairData[] p1, final int p2);
    }
}
