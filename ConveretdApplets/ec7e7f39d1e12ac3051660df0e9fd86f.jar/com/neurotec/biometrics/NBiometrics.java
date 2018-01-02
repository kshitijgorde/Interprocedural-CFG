// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.ptr.HNObject;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NNativeModule;

public class NBiometrics
{
    public static final String DLL_NAME = "NBiometrics";
    static final NBiometricsLibrary LIBRARY;
    public static NNativeModule NATIVE_MODULE;
    
    static {
        try {
            LIBRARY = (NBiometricsLibrary)Native.loadLibrary("NBiometrics", NBiometricsLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        }
        catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
        NBiometrics.NATIVE_MODULE = NNativeModule.fromHandle(NBiometrics.LIBRARY.NBiometricsModuleOf());
    }
    
    interface NBiometricsLibrary extends NLibrary
    {
        HNObject NBiometricsModuleOf();
    }
}
