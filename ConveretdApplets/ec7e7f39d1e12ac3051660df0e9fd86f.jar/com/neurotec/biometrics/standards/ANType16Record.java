// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.BooleanByReference;
import java.util.Date;
import com.sun.jna.WString;
import com.neurotec.lang.NCore;
import com.neurotec.lang.NResult;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType16Record extends ANImageAsciiBinaryRecord
{
    static final ANType16RecordLibrary LIBRARY;
    public static final int FIELD_UDI = 3;
    public static final int FIELD_UTD = 5;
    public static final int FIELD_UQS = 24;
    public static final int MAX_USER_DEFINED_IMAGE_LENGTH = 35;
    public static final NNativeType NATIVE_TYPE;
    
    ANType16Record(final HNObject handle) {
        super(handle, ANType16Record.NATIVE_TYPE);
    }
    
    public String getUserDefinedImage() {
        final int pLen = ANType16Record.LIBRARY.ANType16RecordGetUserDefinedImageEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType16Record.LIBRARY.ANType16RecordGetUserDefinedImageEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setUserDefinedImage(final String value) {
        NResult.check(ANType16Record.LIBRARY.ANType16RecordSetUserDefinedImage(this.getHandle(), new WString(value)));
    }
    
    public Date getUserDefinedTestingDate() {
        return this.getDate();
    }
    
    public void setUserDefinedTestingDate(final Date value) {
        this.setDate(value);
    }
    
    public ANQualityMetric getUserDefinedQualityScore() {
        final BooleanByReference rValue = new BooleanByReference();
        final ANQualityMetric value = new ANQualityMetric();
        NResult.check(ANType16Record.LIBRARY.ANType16RecordGetUserDefinedQualityScore(this.getHandle(), value.getData(), rValue));
        return rValue.getValue() ? value : null;
    }
    
    public void setUserDefinedQualityScore(final ANQualityMetric value) {
        if (value == null || value.getData() == null) {
            NResult.check(ANType16Record.LIBRARY.ANType16RecordSetUserDefinedQualityScore(this.getHandle(), null));
        }
        else {
            NResult.check(ANType16Record.LIBRARY.ANType16RecordSetUserDefinedQualityScore(this.getHandle(), value.getData()));
        }
        this.refresh();
    }
    
    static {
        LIBRARY = (ANType16RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType16RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType16Record.LIBRARY.ANType16RecordTypeOf());
    }
    
    interface ANType16RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType16RecordTypeOf();
        
        int ANType16RecordGetUserDefinedImageEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType16RecordSetUserDefinedImage(final HNObject p0, final WString p1);
        
        int ANType16RecordGetUserDefinedQualityScore(final HNObject p0, final ANQualityMetric.ANQualityMetricData p1, final BooleanByReference p2);
        
        int ANType16RecordSetUserDefinedQualityScore(final HNObject p0, final ANQualityMetric.ANQualityMetricData p1);
    }
}
