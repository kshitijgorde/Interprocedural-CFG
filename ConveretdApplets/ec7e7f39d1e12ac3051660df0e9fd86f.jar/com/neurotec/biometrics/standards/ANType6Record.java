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

public final class ANType6Record extends ANFImageBinaryRecord
{
    static final ANType6RecordLibrary LIBRARY;
    public static final int FIELD_BCA = 8;
    public static final NNativeType NATIVE_TYPE;
    
    ANType6Record(final HNObject handle) {
        super(handle, ANType6Record.NATIVE_TYPE);
    }
    
    public ANImageCompressionAlgorithm getUserDefinedQualityScore() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType6Record.LIBRARY.ANType6RecordGetCompressionAlgorithm(this.getHandle(), rValue));
        return ANImageCompressionAlgorithm.get(rValue.getValue());
    }
    
    public byte getVendorCompressionAlgorithm() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(ANType6Record.LIBRARY.ANType6RecordGetVendorCompressionAlgorithm(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (ANType6RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType6RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType6Record.LIBRARY.ANType6RecordTypeOf());
    }
    
    interface ANType6RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType6RecordTypeOf();
        
        int ANType6RecordGetCompressionAlgorithm(final HNObject p0, final IntByReference p1);
        
        int ANType6RecordGetVendorCompressionAlgorithm(final HNObject p0, final ByteByReference p1);
    }
}
