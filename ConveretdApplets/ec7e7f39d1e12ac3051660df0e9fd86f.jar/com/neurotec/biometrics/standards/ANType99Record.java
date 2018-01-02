// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.ShortByReference;
import java.util.Date;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NVersion;
import com.neurotec.lang.NNativeType;

public final class ANType99Record extends ANAsciiBinaryRecord
{
    static final ANType99RecordLibrary LIBRARY;
    public static final int FIELD_BCD = 5;
    public static final int FIELD_HDV = 100;
    public static final int FIELD_BTY = 101;
    public static final int FIELD_BDQ = 102;
    public static final int FIELD_BFO = 103;
    public static final int FIELD_BFT = 104;
    public static final int FIELD_BDB = 999;
    public static final NNativeType NATIVE_TYPE;
    public static final NVersion HEADER_VERSION_1_0;
    public static final NVersion HEADER_VERSION_1_1;
    
    ANType99Record(final HNObject handle) {
        super(handle, ANType99Record.NATIVE_TYPE);
    }
    
    public Date getBiometricCreationDate() {
        return this.getDate();
    }
    
    public void setBiometricCreationDate(final Date value) {
        this.setDate(value);
    }
    
    public NVersion getHeaderVersion() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANType99Record.LIBRARY.ANType99RecordGetHeaderVersion(this.getHandle(), rValue));
        return new NVersion(rValue.getValue());
    }
    
    public ANBiometricType getBiometricType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType99Record.LIBRARY.ANType99RecordGetBiometricType(this.getHandle(), rValue));
        return ANBiometricType.get(rValue.getValue());
    }
    
    public ANQualityMetric getBiometricDataQuality() {
        final BooleanByReference rValue = new BooleanByReference();
        final ANQualityMetric value = new ANQualityMetric();
        NResult.check(ANType99Record.LIBRARY.ANType99RecordGetBiometricDataQuality(this.getHandle(), value.getData(), rValue));
        return rValue.getValue() ? value : null;
    }
    
    public void setBiometricDataQuality(final ANQualityMetric value) {
        if (value == null || value.getData() == null) {
            NResult.check(ANType99Record.LIBRARY.ANType99RecordSetBiometricDataQuality(this.getHandle(), null));
        }
        else {
            NResult.check(ANType99Record.LIBRARY.ANType99RecordSetBiometricDataQuality(this.getHandle(), value.getData()));
        }
        this.refresh();
    }
    
    public short getBdbFormatOwner() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANType99Record.LIBRARY.ANType99RecordGetBdbFormatOwner(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getBdbFormatType() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANType99Record.LIBRARY.ANType99RecordGetBdbFormatType(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (ANType99RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType99RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType99Record.LIBRARY.ANType99RecordTypeOf());
        HEADER_VERSION_1_0 = new NVersion((short)256);
        HEADER_VERSION_1_1 = new NVersion((short)257);
    }
    
    interface ANType99RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType99RecordTypeOf();
        
        int ANType99RecordGetHeaderVersion(final HNObject p0, final ShortByReference p1);
        
        int ANType99RecordGetBiometricType(final HNObject p0, final IntByReference p1);
        
        int ANType99RecordGetBiometricDataQuality(final HNObject p0, final ANQualityMetric.ANQualityMetricData p1, final BooleanByReference p2);
        
        int ANType99RecordSetBiometricDataQuality(final HNObject p0, final ANQualityMetric.ANQualityMetricData p1);
        
        int ANType99RecordGetBdbFormatOwner(final HNObject p0, final ShortByReference p1);
        
        int ANType99RecordGetBdbFormatType(final HNObject p0, final ShortByReference p1);
    }
}
