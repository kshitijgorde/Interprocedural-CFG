// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public class NPalmScanner extends NFScanner
{
    static final NPalmDeviceLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NPalmScanner(final HNObject handle) {
        super(handle, NPalmScanner.NATIVE_TYPE);
    }
    
    static {
        LIBRARY = (NPalmDeviceLibrary)Native.loadLibrary("NDevices", NPalmDeviceLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NPalmScanner.LIBRARY.NPalmScannerTypeOf());
    }
    
    interface NPalmDeviceLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NPalmScannerTypeOf();
    }
}
