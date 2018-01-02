// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType2Record extends ANAsciiRecord
{
    static final ANType2RecordLibrary LIBRARY;
    public static final int FIELD_UDF_FROM = 3;
    public static final int FIELD_UDF_TO = 999;
    public static final int FIELD_UDF_TO_V4 = 999999999;
    public static final NNativeType NATIVE_TYPE;
    
    ANType2Record(final HNObject handle) {
        super(handle, ANType2Record.NATIVE_TYPE);
    }
    
    static {
        LIBRARY = (ANType2RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType2RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType2Record.LIBRARY.ANType2RecordTypeOf());
    }
    
    interface ANType2RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType2RecordTypeOf();
    }
}
