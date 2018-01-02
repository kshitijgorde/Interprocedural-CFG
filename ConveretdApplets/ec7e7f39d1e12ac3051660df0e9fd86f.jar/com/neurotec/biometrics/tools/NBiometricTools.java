// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import com.neurotec.jna.ptr.HNObject;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NNativeModule;

public class NBiometricTools
{
    static final String DLL_NAME = "NBiometricTools";
    static final NBiometricToolsLibrary LIBRARY;
    public static NNativeModule NATIVE_MODULE;
    
    static {
        LIBRARY = (NBiometricToolsLibrary)Native.loadLibrary("NBiometricTools", NBiometricToolsLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NBiometricTools.NATIVE_MODULE = NNativeModule.fromHandle(NBiometricTools.LIBRARY.NBiometricToolsModuleOf());
    }
    
    interface NBiometricToolsLibrary extends NLibrary
    {
        HNObject NBiometricToolsModuleOf();
    }
}
