// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.util.Date;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType15Record extends ANFPImageAsciiBinaryRecord
{
    static final ANType15RecordLibrary LIBRARY;
    public static final int FIELD_PCD = 5;
    public static final int FIELD_PLP = 13;
    public static final int FIELD_PQM = 24;
    public static final int MAX_QUALITY_METRIC_COUNT = 4;
    public static final NNativeType NATIVE_TYPE;
    
    ANType15Record(final HNObject handle) {
        super(handle, ANType15Record.NATIVE_TYPE);
    }
    
    public Date getPalmprintCaptureDate() {
        return this.getDate();
    }
    
    public void setPalmprintCaptureDate(final Date value) {
        this.setDate(value);
    }
    
    static {
        LIBRARY = (ANType15RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType15RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType15Record.LIBRARY.ANType15RecordTypeOf());
    }
    
    interface ANType15RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType15RecordTypeOf();
    }
}
