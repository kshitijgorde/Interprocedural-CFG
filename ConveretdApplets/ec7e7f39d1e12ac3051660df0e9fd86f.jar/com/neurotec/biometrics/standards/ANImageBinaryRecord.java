// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.BooleanByReference;
import com.neurotec.lang.NObject;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.images.NImage;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public abstract class ANImageBinaryRecord extends ANBinaryRecord
{
    static final ANImageBinaryRecordLibrary LIBRARY;
    public static final int AN_IMAGE_BINARY_RECORD_FIELD_ISR = 5;
    public static final int AN_IMAGE_BINARY_RECORD_FIELD_HLL = 6;
    public static final int AN_IMAGE_BINARY_RECORD_FIELD_VLL = 7;
    public static final NNativeType NATIVE_TYPE;
    
    ANImageBinaryRecord(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
    }
    
    public final NImage toNImage() {
        return this.toNImage(0);
    }
    
    public final NImage toNImage(final int flags) {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANImageBinaryRecord.LIBRARY.ANImageBinaryRecordToNImage(this.getHandle(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImage.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public final boolean isImageScanResolution() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANImageBinaryRecord.LIBRARY.ANImageBinaryRecordGetImageScanResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final int getImageScanResolutionValue() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANImageBinaryRecord.LIBRARY.ANImageBinaryRecordGetImageScanResolutionValue(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final int getImageResolution() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANImageBinaryRecord.LIBRARY.ANImageBinaryRecordGetImageResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final short getHorzLineLength() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANImageBinaryRecord.LIBRARY.ANImageBinaryRecordGetHorzLineLength(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final short getVertLineLength() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANImageBinaryRecord.LIBRARY.ANImageBinaryRecordGetVertLineLength(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (ANImageBinaryRecordLibrary)Native.loadLibrary("NBiometricStandards", ANImageBinaryRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANImageBinaryRecord.LIBRARY.ANImageBinaryRecordTypeOf());
    }
    
    interface ANImageBinaryRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANImageBinaryRecordTypeOf();
        
        int ANImageBinaryRecordToNImage(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int ANImageBinaryRecordGetImageScanResolution(final HNObject p0, final BooleanByReference p1);
        
        int ANImageBinaryRecordGetImageScanResolutionValue(final HNObject p0, final IntByReference p1);
        
        int ANImageBinaryRecordGetImageResolution(final HNObject p0, final IntByReference p1);
        
        int ANImageBinaryRecordGetHorzLineLength(final HNObject p0, final ShortByReference p1);
        
        int ANImageBinaryRecordGetVertLineLength(final HNObject p0, final ShortByReference p1);
    }
}
