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

public final class FirFingerView extends NObject
{
    static final FirFingerViewLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    FirFingerView(final HNObject handle) {
        super(handle, FirFingerView.NATIVE_TYPE, false);
    }
    
    public static int calculateSize(final BdifStandard standard, final int imageDataLength) {
        if (imageDataLength < 0) {
            throw new IllegalArgumentException("imageDataLength is less than zero");
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewCalculateSize(standard.getValue(), new NativeSize((long)imageDataLength), rValue));
        return rValue.getValue().intValue();
    }
    
    public NImage toNImage() {
        return this.toNImage(0);
    }
    
    public NImage toNImage(final int flags) {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewToNImage(this.getHandle(), flags, rhValue));
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
        NResult.check(FirFingerView.LIBRARY.FirFingerViewSetImageDataLength(this.getHandle(), new NativeSize((long)value)));
    }
    
    public ByteBuffer getImageData() {
        final PointerByReference rImageData = new PointerByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetImageDataPtr(this.getHandle(), rImageData));
        final NativeSizeByReference imageDataLength = new NativeSizeByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetImageDataLength(this.getHandle(), imageDataLength));
        return NCore.getByteBuffer(rImageData.getValue(), imageDataLength.getValue().longValue(), false);
    }
    
    public void setImageData(final ByteBuffer value) {
        if (value == null) {
            throw new NullPointerException("value");
        }
        NResult.check(FirFingerView.LIBRARY.FirFingerViewSetImageData(this.getHandle(), value.slice(), new NativeSize((long)value.remaining())));
    }
    
    public FirFinger getOwner() {
        return (FirFinger)super.getOwner();
    }
    
    public BdifFPPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetPosition(this.getHandle(), rValue));
        return BdifFPPosition.get(rValue.getValue());
    }
    
    public int getViewCount() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetViewCount(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getViewNumber() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetViewNumber(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public byte getImageQuality() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetImageQuality(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setImageQuality(final byte value) {
        NResult.check(FirFingerView.LIBRARY.FirFingerViewSetImageQuality(this.getHandle(), value));
    }
    
    public BdifFPImpressionType getImpressionType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetImpressionType(this.getHandle(), rValue));
        return BdifFPImpressionType.get(rValue.getValue());
    }
    
    public void setImpressionType(final BdifFPImpressionType value) {
        NResult.check(FirFingerView.LIBRARY.FirFingerViewSetImpressionType(this.getHandle(), value.getValue()));
    }
    
    public short getHorzLineLength() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetHorzLineLength(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getVertLineLength() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetVertLineLength(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getImageDataLength() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FirFingerView.LIBRARY.FirFingerViewGetImageDataLength(this.getHandle(), rValue));
        return rValue.getValue().intValue();
    }
    
    static {
        LIBRARY = (FirFingerViewLibrary)Native.loadLibrary("NBiometricStandards", FirFingerViewLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(FirFingerView.LIBRARY.FirFingerViewTypeOf());
    }
    
    interface FirFingerViewLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType FirFingerViewTypeOf();
        
        int FirFingerViewCalculateSize(final int p0, final NativeSize p1, final NativeSizeByReference p2);
        
        int FirFingerViewToNImage(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FirFingerViewGetPosition(final HNObject p0, final IntByReference p1);
        
        int FirFingerViewGetViewCount(final HNObject p0, final IntByReference p1);
        
        int FirFingerViewGetViewNumber(final HNObject p0, final IntByReference p1);
        
        int FirFingerViewGetImageQuality(final HNObject p0, final ByteByReference p1);
        
        int FirFingerViewSetImageQuality(final HNObject p0, final byte p1);
        
        int FirFingerViewGetImpressionType(final HNObject p0, final IntByReference p1);
        
        int FirFingerViewSetImpressionType(final HNObject p0, final int p1);
        
        int FirFingerViewGetHorzLineLength(final HNObject p0, final ShortByReference p1);
        
        int FirFingerViewGetVertLineLength(final HNObject p0, final ShortByReference p1);
        
        int FirFingerViewGetImageDataLength(final HNObject p0, final NativeSizeByReference p1);
        
        int FirFingerViewSetImageDataLength(final HNObject p0, final NativeSize p1);
        
        int FirFingerViewGetImageDataPtr(final HNObject p0, final PointerByReference p1);
        
        int FirFingerViewSetImageData(final HNObject p0, final ByteBuffer p1, final NativeSize p2);
    }
}
