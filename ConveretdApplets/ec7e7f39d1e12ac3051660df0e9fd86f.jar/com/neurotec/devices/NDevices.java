// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import com.neurotec.jna.ptr.HNObject;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NNativeModule;

public final class NDevices
{
    static final String DLL_NAME = "NDevices";
    static final NDevicesLibrary LIBRARY;
    public static NNativeModule NATIVE_MODULE;
    
    static {
        LIBRARY = (NDevicesLibrary)Native.loadLibrary("NDevices", NDevicesLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NDevices.NATIVE_MODULE = NNativeModule.fromHandle(NDevices.LIBRARY.NDevicesModuleOf());
    }
    
    interface NDevicesLibrary extends NLibrary
    {
        HNObject NDevicesModuleOf();
    }
}
