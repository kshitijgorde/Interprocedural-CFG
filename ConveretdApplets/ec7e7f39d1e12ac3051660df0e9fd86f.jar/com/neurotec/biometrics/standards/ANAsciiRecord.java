// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public abstract class ANAsciiRecord extends ANRecord
{
    static final ANAsciiRecordLibrary LIBRARY;
    public static final int MAX_FIELD_NUMBER = 999999999;
    public static final NNativeType NATIVE_TYPE;
    
    ANAsciiRecord(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
    }
    
    static {
        LIBRARY = (ANAsciiRecordLibrary)Native.loadLibrary("NBiometricStandards", ANAsciiRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANAsciiRecord.LIBRARY.ANAsciiRecordTypeOf());
    }
    
    interface ANAsciiRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANAsciiRecordTypeOf();
    }
}
