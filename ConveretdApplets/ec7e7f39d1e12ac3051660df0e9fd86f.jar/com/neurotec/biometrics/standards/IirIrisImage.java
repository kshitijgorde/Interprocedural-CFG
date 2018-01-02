// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NCore;
import com.sun.jna.ptr.PointerByReference;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.images.NImage;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class IirIrisImage extends NObject
{
    static final IirIrisImageLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    IirIrisImage(final HNObject handle) {
        super(handle, IirIrisImage.NATIVE_TYPE, false);
    }
    
    public static int calculateSize(final BdifStandard standard, final int imageDataLength) {
        if (imageDataLength < 0) {
            throw new IllegalArgumentException("imageDataLength is less than zero");
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageCalculateSize(standard.getValue(), new NativeSize((long)imageDataLength), rValue));
        return rValue.getValue().intValue();
    }
    
    public NImage toNImage() {
        return this.toNImage(0);
    }
    
    public NImage toNImage(final int flags) {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageToNImage(this.getHandle(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImage.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public void setImageDataLength(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value is less than zero");
        }
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageSetImageDataLength(this.getHandle(), new NativeSize((long)value)));
    }
    
    public ByteBuffer getImageData() {
        final PointerByReference rImageData = new PointerByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageGetImageDataPtr(this.getHandle(), rImageData));
        final NativeSizeByReference imageDataLength = new NativeSizeByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageGetImageDataLength(this.getHandle(), imageDataLength));
        return NCore.getByteBuffer(rImageData.getValue(), imageDataLength.getValue().longValue(), false);
    }
    
    public void setImageData(final ByteBuffer value) {
        if (value == null) {
            throw new NullPointerException("value");
        }
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageSetImageData(this.getHandle(), value.slice(), new NativeSize((long)value.remaining())));
    }
    
    public IirIris getOwner() {
        return (IirIris)super.getOwner();
    }
    
    public BdifFPPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageGetPosition(this.getHandle(), rValue));
        return BdifFPPosition.get(rValue.getValue());
    }
    
    public int getImageCount() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageGetImageCount(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getImageNumber() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageGetImageNumber(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public byte getQuality() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageGetQuality(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setQuality(final byte value) {
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageSetQuality(this.getHandle(), value));
    }
    
    public double getRotationAngle() {
        final short value = this.getRotationAngleRaw();
        return (value == 32767) ? Double.NaN : (value * 3.141592653589793 / 32768.0);
    }
    
    public void setRotationAngle(double value) {
        if (Double.isNaN(value)) {
            this.setRotationAngleRaw((short)32767);
        }
        else {
            value -= Math.floor(value / 6.283185307179586) * 6.283185307179586;
            value = Math.round(value * 32768.0 / 3.141592653589793);
            this.setRotationAngleRaw((short)value);
        }
    }
    
    public short getRotationAngleRaw() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageGetRotationAngle(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setRotationAngleRaw(final short value) {
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageSetRotationAngle(this.getHandle(), value));
    }
    
    public double getRotationAngleUncertainty() {
        final short value = this.getRotationAngleUncertaintyRaw();
        return (value == 32767) ? Double.NaN : (value * 3.141592653589793 / 65536.0);
    }
    
    public void setRotationAngleUncertainty(final double value) {
        this.setRotationAngleUncertaintyRaw((short)((value < 0.0 || value >= 3.141592653589793 || Double.isNaN(value)) ? 32767 : ((short)Math.round(value * 65536.0 / 3.141592653589793))));
    }
    
    public short getRotationAngleUncertaintyRaw() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageGetRotationAngleUncertainty(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setRotationAngleUncertaintyRaw(final short value) {
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageSetRotationAngleUncertainty(this.getHandle(), value));
    }
    
    public int getImageDataLength() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(IirIrisImage.LIBRARY.IirIrisImageGetImageDataLength(this.getHandle(), rValue));
        return rValue.getValue().intValue();
    }
    
    static {
        LIBRARY = (IirIrisImageLibrary)Native.loadLibrary("NBiometricStandards", IirIrisImageLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(IirIrisImage.LIBRARY.IirIrisImageTypeOf());
    }
    
    interface IirIrisImageLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType IirIrisImageTypeOf();
        
        int IirIrisImageCalculateSize(final int p0, final NativeSize p1, final NativeSizeByReference p2);
        
        int IirIrisImageToNImage(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int IirIrisImageGetPosition(final HNObject p0, final IntByReference p1);
        
        int IirIrisImageGetImageCount(final HNObject p0, final IntByReference p1);
        
        int IirIrisImageGetImageNumber(final HNObject p0, final IntByReference p1);
        
        int IirIrisImageGetQuality(final HNObject p0, final ByteByReference p1);
        
        int IirIrisImageSetQuality(final HNObject p0, final byte p1);
        
        int IirIrisImageGetRotationAngle(final HNObject p0, final ShortByReference p1);
        
        int IirIrisImageSetRotationAngle(final HNObject p0, final short p1);
        
        int IirIrisImageGetRotationAngleUncertainty(final HNObject p0, final ShortByReference p1);
        
        int IirIrisImageSetRotationAngleUncertainty(final HNObject p0, final short p1);
        
        int IirIrisImageGetImageDataLength(final HNObject p0, final NativeSizeByReference p1);
        
        int IirIrisImageSetImageDataLength(final HNObject p0, final NativeSize p1);
        
        int IirIrisImageGetImageDataPtr(final HNObject p0, final PointerByReference p1);
        
        int IirIrisImageSetImageData(final HNObject p0, final ByteBuffer p1, final NativeSize p2);
    }
}
