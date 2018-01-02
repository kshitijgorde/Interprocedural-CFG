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

public final class ANType3Record extends ANFImageBinaryRecord
{
    static final ANType3RecordLibrary LIBRARY;
    public static final int FIELD_GCA = 8;
    public static final NNativeType NATIVE_TYPE;
    
    ANType3Record(final HNObject handle) {
        super(handle, ANType3Record.NATIVE_TYPE);
    }
    
    public ANImageCompressionAlgorithm getUserDefinedQualityScore() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType3Record.LIBRARY.ANType3RecordGetCompressionAlgorithm(this.getHandle(), rValue));
        return ANImageCompressionAlgorithm.get(rValue.getValue());
    }
    
    public byte getVendorCompressionAlgorithm() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(ANType3Record.LIBRARY.ANType3RecordGetVendorCompressionAlgorithm(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (ANType3RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType3RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType3Record.LIBRARY.ANType3RecordTypeOf());
    }
    
    interface ANType3RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType3RecordTypeOf();
        
        int ANType3RecordGetCompressionAlgorithm(final HNObject p0, final IntByReference p1);
        
        int ANType3RecordGetVendorCompressionAlgorithm(final HNObject p0, final ByteByReference p1);
    }
}
