// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.biometrics.NBiometricType;
import java.util.EnumSet;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public class NBiometricDevice extends NDevice
{
    static final NBiometricDeviceLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NBiometricDevice(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
    }
    
    NBiometricDevice(final HNObject handle) {
        this(handle, NBiometricDevice.NATIVE_TYPE);
    }
    
    public final void cancel() {
        NResult.check(NBiometricDevice.LIBRARY.NBiometricDeviceCancel(this.getHandle()));
    }
    
    public final EnumSet<NBiometricType> getBiometricType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NBiometricDevice.LIBRARY.NBiometricDeviceGetBiometricType(this.getHandle(), rValue));
        return NBiometricType.getSet(rValue.getValue());
    }
    
    public final short getVendorId() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NBiometricDevice.LIBRARY.NBiometricDeviceGetVendorId(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final short getProductId() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NBiometricDevice.LIBRARY.NBiometricDeviceGetProductId(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final boolean isSpoofDetectionSupported() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NBiometricDevice.LIBRARY.NBiometricDeviceIsSpoofDetectionSupported(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final boolean isSpoofDetection() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NBiometricDevice.LIBRARY.NBiometricDeviceGetSpoofDetection(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setSpoofDetection(final boolean value) {
        NResult.check(NBiometricDevice.LIBRARY.NBiometricDeviceSetSpoofDetection(this.getHandle(), value));
    }
    
    static {
        LIBRARY = (NBiometricDeviceLibrary)Native.loadLibrary("NDevices", NBiometricDeviceLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NBiometricDevice.LIBRARY.NBiometricDeviceTypeOf());
    }
    
    interface NBiometricDeviceLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NBiometricDeviceTypeOf();
        
        int NBiometricDeviceCancel(final HNObject p0);
        
        int NBiometricDeviceGetBiometricType(final HNObject p0, final IntByReference p1);
        
        int NBiometricDeviceGetVendorId(final HNObject p0, final ShortByReference p1);
        
        int NBiometricDeviceGetProductId(final HNObject p0, final ShortByReference p1);
        
        int NBiometricDeviceIsSpoofDetectionSupported(final HNObject p0, final BooleanByReference p1);
        
        int NBiometricDeviceGetSpoofDetection(final HNObject p0, final BooleanByReference p1);
        
        int NBiometricDeviceSetSpoofDetection(final HNObject p0, final boolean p1);
    }
}
