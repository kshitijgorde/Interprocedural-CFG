// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.LongByReference;
import java.util.Date;
import com.sun.jna.WString;
import com.neurotec.lang.NCore;
import com.neurotec.lang.NResult;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public abstract class ANAsciiBinaryRecord extends ANRecord
{
    static final ANAsciiBinaryRecordLibrary LIBRARY;
    public static final int FIELD_SRC = 4;
    public static final int FIELD_DAT = 5;
    public static final int FIELD_UDF_FROM = 200;
    public static final int FIELD_UDF_TO = 998;
    public static final int MIN_SOURCE_AGENCY_LENGTH = 9;
    public static final int MAX_SOURCE_AGENCY_LENGTH = 20;
    public static final int MAX_SOURCE_AGENCY_LENGTH_V4 = 35;
    public static final byte QUALITY_METRIC_SCORE_NOT_AVAILABLE = -2;
    public static final byte QUALITY_METRIC_SCORE_FAILED = -1;
    public static final byte MAX_QUALITY_METRIC_SCORE = 100;
    public static final NNativeType NATIVE_TYPE;
    
    ANAsciiBinaryRecord(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
    }
    
    public final String getSourceAgency() {
        final int pLen = ANAsciiBinaryRecord.LIBRARY.ANAsciiBinaryRecordGetSourceAgencyEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANAsciiBinaryRecord.LIBRARY.ANAsciiBinaryRecordGetSourceAgencyEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public final void setSourceAgency(final String value) {
        NResult.check(ANAsciiBinaryRecord.LIBRARY.ANAsciiBinaryRecordSetSourceAgency(this.getHandle(), new WString(value)));
    }
    
    public final Date getDate() {
        final LongByReference rValue = new LongByReference();
        NResult.check(ANAsciiBinaryRecord.LIBRARY.ANAsciiBinaryRecordGetDate(this.getHandle(), rValue));
        return (rValue.getValue() == -1L) ? null : ((this.getRecordType() == ANRecordType.Type99) ? NCore.toUtcDate(rValue.getValue()) : NCore.toDate(rValue.getValue()));
    }
    
    public final void setDate(final Date value) {
        NResult.check(ANAsciiBinaryRecord.LIBRARY.ANAsciiBinaryRecordSetDate(this.getHandle(), (value == null) ? -1L : ((this.getRecordType() == ANRecordType.Type99) ? NCore.toRawUtcDate(value) : NCore.toRawDate(value))));
    }
    
    static {
        LIBRARY = (ANAsciiBinaryRecordLibrary)Native.loadLibrary("NBiometricStandards", ANAsciiBinaryRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANAsciiBinaryRecord.LIBRARY.ANAsciiBinaryRecordTypeOf());
    }
    
    interface ANAsciiBinaryRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANAsciiBinaryRecordTypeOf();
        
        int ANAsciiBinaryRecordGetSourceAgencyEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANAsciiBinaryRecordSetSourceAgency(final HNObject p0, final WString p1);
        
        int ANAsciiBinaryRecordGetDate(final HNObject p0, final LongByReference p1);
        
        int ANAsciiBinaryRecordSetDate(final HNObject p0, final long p1);
    }
}
