// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType7Record extends ANBinaryRecord
{
    static final ANType7RecordLibrary LIBRARY;
    public static final int FIELD_UDF = 999;
    public static final NNativeType NATIVE_TYPE;
    
    ANType7Record(final HNObject handle) {
        super(handle, ANType7Record.NATIVE_TYPE);
    }
    
    static {
        LIBRARY = (ANType7RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType7RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType7Record.LIBRARY.ANType7RecordTypeOf());
    }
    
    interface ANType7RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType7RecordTypeOf();
    }
}
