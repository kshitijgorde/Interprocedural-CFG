// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ByteByReference;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType5Record extends ANFImageBinaryRecord
{
    static final ANType5RecordLibrary LIBRARY;
    public static final int FIELD_BCA = 8;
    public static final NNativeType NATIVE_TYPE;
    
    ANType5Record(final HNObject handle) {
        super(handle, ANType5Record.NATIVE_TYPE);
    }
    
    public ANImageCompressionAlgorithm getUserDefinedQualityScore() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType5Record.LIBRARY.ANType5RecordGetCompressionAlgorithm(this.getHandle(), rValue));
        return ANImageCompressionAlgorithm.get(rValue.getValue());
    }
    
    public byte getVendorCompressionAlgorithm() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(ANType5Record.LIBRARY.ANType5RecordGetVendorCompressionAlgorithm(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (ANType5RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType5RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType5Record.LIBRARY.ANType5RecordTypeOf());
    }
    
    interface ANType5RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType5RecordTypeOf();
        
        int ANType5RecordGetCompressionAlgorithm(final HNObject p0, final IntByReference p1);
        
        int ANType5RecordGetVendorCompressionAlgorithm(final HNObject p0, final ByteByReference p1);
    }
}
