// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public class NFingerScanner extends NFScanner
{
    static final NFingerDeviceLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NFingerScanner(final HNObject handle) {
        super(handle, NFingerScanner.NATIVE_TYPE);
    }
    
    static {
        LIBRARY = (NFingerDeviceLibrary)Native.loadLibrary("NDevices", NFingerDeviceLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NFingerScanner.LIBRARY.NFingerScannerTypeOf());
    }
    
    interface NFingerDeviceLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NFingerScannerTypeOf();
    }
}
