// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.licensing;

import com.neurotec.jna.ptr.HNObject;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NNativeModule;

public final class NLicensing
{
    public static final String DLL_NAME = "NLicensing";
    static NLicensingLibrary LIBRARY;
    public static NNativeModule NATIVE_MODULE;
    
    static {
        NLicensing.LIBRARY = null;
        try {
            NLicensing.LIBRARY = (NLicensingLibrary)Native.loadLibrary("NLicensing", NLicensingLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        }
        catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
        NLicensing.NATIVE_MODULE = NNativeModule.fromHandle(NLicensing.LIBRARY.NLicensingModuleOf());
    }
    
    public interface NLicensingLibrary extends NLibrary
    {
        HNObject NLicensingModuleOf();
    }
}
