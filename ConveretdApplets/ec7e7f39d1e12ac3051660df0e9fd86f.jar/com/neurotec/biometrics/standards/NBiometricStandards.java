// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.ptr.HNObject;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NNativeModule;

public final class NBiometricStandards
{
    static final String DLL_NAME = "NBiometricStandards";
    static final NBiometricStandardsLibrary LIBRARY;
    public static NNativeModule NATIVE_MODULE;
    
    static {
        LIBRARY = (NBiometricStandardsLibrary)Native.loadLibrary("NBiometricStandards", NBiometricStandardsLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NBiometricStandards.NATIVE_MODULE = NNativeModule.fromHandle(NBiometricStandards.LIBRARY.NBiometricStandardsModuleOf());
    }
    
    interface NBiometricStandardsLibrary extends NLibrary
    {
        HNObject NBiometricStandardsModuleOf();
    }
}
