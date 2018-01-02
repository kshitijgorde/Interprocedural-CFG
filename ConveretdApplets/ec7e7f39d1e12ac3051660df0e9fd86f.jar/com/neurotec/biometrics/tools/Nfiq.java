// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import com.neurotec.jna.ptr.HNObject;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.images.NGrayscaleImage;

public class Nfiq
{
    static final NfiqLibrary LIBRARY;
    
    public static NfiqQuality compute(final NGrayscaleImage image) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        final IntByReference rValue = new IntByReference();
        NResult.check(Nfiq.LIBRARY.NfiqCompute(image.getHandle(), rValue));
        return NfiqQuality.get(rValue.getValue());
    }
    
    static {
        LIBRARY = (NfiqLibrary)Native.loadLibrary("NBiometricTools", NfiqLibrary.class, W32APIOptions.UNICODE_OPTIONS);
    }
    
    interface NfiqLibrary extends NLibrary
    {
        int NfiqCompute(final HNObject p0, final IntByReference p1);
    }
}
