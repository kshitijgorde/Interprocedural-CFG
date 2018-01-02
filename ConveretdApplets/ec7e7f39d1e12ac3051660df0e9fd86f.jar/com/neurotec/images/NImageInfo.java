// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public class NImageInfo extends NObject
{
    private NImageFormat format;
    static final NImageInfoLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NImageInfo(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType, false);
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageInfo.LIBRARY.NImageInfoGetFormat(handle, rhValue));
        this.format = NImageFormat.fromHandle(rhValue.getValue());
    }
    
    public static NImageInfo fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NImageInfo fromHandle(final HNObject handle, final boolean ownsHandle) {
        NImageInfo info = null;
        if (TiffInfo.NATIVE_TYPE.isInstanceOfType(handle)) {
            info = new TiffInfo(handle);
        }
        else if (JpegInfo.NATIVE_TYPE.isInstanceOfType(handle)) {
            info = new JpegInfo(handle);
        }
        else if (PngInfo.NATIVE_TYPE.isInstanceOfType(handle)) {
            info = new PngInfo(handle);
        }
        else if (WsqInfo.NATIVE_TYPE.isInstanceOfType(handle)) {
            info = new WsqInfo(handle);
        }
        else if (Jpeg2KInfo.NATIVE_TYPE.isInstanceOfType(handle)) {
            info = new Jpeg2KInfo(handle);
        }
        else if (BmpInfo.NATIVE_TYPE.isInstanceOfType(handle)) {
            info = new BmpInfo(handle);
        }
        else {
            if (!IHeadInfo.NATIVE_TYPE.isInstanceOfType(handle)) {
                throw new IllegalArgumentException("The NImageInfo handle native type is unknown");
            }
            info = new IHeadInfo(handle);
        }
        if (ownsHandle) {
            info.claimHandle();
        }
        return info;
    }
    
    protected void dispose(final boolean disposing) {
        this.format = null;
        super.dispose(disposing);
    }
    
    public NImageFormat getFormat() {
        this.check();
        return this.format;
    }
    
    static {
        LIBRARY = (NImageInfoLibrary)Native.loadLibrary("NImages", NImageInfoLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NImageInfo.LIBRARY.NImageInfoTypeOf());
    }
    
    interface NImageInfoLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NImageInfoTypeOf();
        
        int NImageInfoGetFormat(final HNObject p0, final HNObjectByReference p1);
    }
}
