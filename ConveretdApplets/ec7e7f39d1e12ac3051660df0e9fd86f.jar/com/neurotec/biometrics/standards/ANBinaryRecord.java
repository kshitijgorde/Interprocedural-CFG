// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public abstract class ANBinaryRecord extends ANRecord
{
    static final ANBinaryRecordLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    ANBinaryRecord(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
    }
    
    static {
        LIBRARY = (ANBinaryRecordLibrary)Native.loadLibrary("NBiometricStandards", ANBinaryRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANBinaryRecord.LIBRARY.ANBinaryRecordTypeOf());
    }
    
    interface ANBinaryRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANBinaryRecordTypeOf();
    }
}
