// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.WString;
import com.sun.jna.ptr.ByteByReference;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;
import com.neurotec.lang.NObject;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.images.NImage;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public abstract class ANImageAsciiBinaryRecord extends ANAsciiBinaryRecord
{
    static final ANImageAsciiBinaryRecordLibrary LIBRARY;
    public static final int FIELD_HLL = 6;
    public static final int FIELD_VLL = 7;
    public static final int FIELD_SLC = 8;
    public static final int FIELD_HPS = 9;
    public static final int FIELD_VPS = 10;
    public static final int FIELD_CGA = 11;
    public static final int FIELD_BPX = 12;
    public static final int FIELD_CSP = 13;
    public static final int FIELD_SHPS = 16;
    public static final int FIELD_SVPS = 17;
    public static final int FIELD_COM = 20;
    public static final int FIELD_IQM = 24;
    public static final int FIELD_DMM = 30;
    public static final int MAX_LINE_LENGTH = 9999;
    public static final int MAX_PIXEL_SCALE = 9999;
    public static final int MIN_SCAN_PIXEL_SCALE_PPCM = 195;
    public static final int MIN_SCAN_PIXEL_SCALE_PPI = 495;
    public static final int MIN_LATENT_SCAN_PIXEL_SCALE_PPCM = 195;
    public static final int MIN_LATENT_SCAN_PIXEL_SCALE_PPI = 495;
    public static final int MIN_LATENT_SCAN_PIXEL_SCALE_V4_PPCM = 390;
    public static final int MIN_LATENT_SCAN_PIXEL_SCALE_V4_PPI = 990;
    public static final int MIN_PIXEL_SCALE_PPCM = 195;
    public static final int MIN_PIXEL_SCALE_PPI = 495;
    public static final int MIN_LATENT_PIXEL_SCALE_PPCM = 195;
    public static final int MIN_LATENT_PIXEL_SCALE_PPI = 495;
    public static final int MIN_LATENT_PIXEL_SCALE_V4_PPCM = 390;
    public static final int MIN_LATENT_PIXEL_SCALE_V4_PPI = 990;
    public static final int MIN_VENDOR_COMPRESSION_ALGORITHM_LENGTH = 3;
    public static final int MAX_VENDOR_COMPRESSION_ALGORITHM_LENGTH = 6;
    public static final int MAX_COMMENT_LENGTH = 127;
    public static final NNativeType NATIVE_TYPE;
    
    ANImageAsciiBinaryRecord(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
    }
    
    public final NImage toNImage() {
        return this.toNImage(0);
    }
    
    public final NImage toNImage(final int flags) {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordToNImage(this.getHandle(), flags, rhValue));
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
    
    public final short getHorzLineLength() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetHorzLineLength(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final short getVertLineLength() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetVertLineLength(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final BdifScaleUnits getScaleUnits() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetScaleUnits(this.getHandle(), rValue));
        return BdifScaleUnits.get(rValue.getValue());
    }
    
    public final short getHorzPixelScale() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetHorzPixelScale(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final short getVertPixelScale() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetVertPixelScale(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final ANImageCompressionAlgorithm getCompressionAlgorithm() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetCompressionAlgorithm(this.getHandle(), rValue));
        return ANImageCompressionAlgorithm.get(rValue.getValue());
    }
    
    public final String getVendorCompressionAlgorithm() {
        final int pLen = ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetVendorCompressionAlgorithmEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetVendorCompressionAlgorithmEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public final byte getBitsPerPixel() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetBitsPerPixel(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final ANImageColorSpace getColorSpace() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetColorSpace(this.getHandle(), rValue));
        return ANImageColorSpace.get(rValue.getValue());
    }
    
    public final int getScanHorzPixelScale() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetScanHorzPixelScale(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setScanHorzPixelScale(final int value) {
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordSetScanHorzPixelScale(this.getHandle(), value));
    }
    
    public final int getScanVertPixelScale() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetScanVertPixelScale(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setScanVertPixelScale(final int value) {
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordSetScanVertPixelScale(this.getHandle(), value));
    }
    
    public final String getComment() {
        final int pLen = ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetCommentEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetCommentEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public final void setComment(final String value) {
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordSetComment(this.getHandle(), new WString(value)));
    }
    
    public final ANDeviceMonitoringMode getDeviceMonitoringMode() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordGetDeviceMonitoringMode(this.getHandle(), rValue));
        return ANDeviceMonitoringMode.get(rValue.getValue());
    }
    
    public final void setDeviceMonitoringMode(final ANDeviceMonitoringMode value) {
        NResult.check(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordSetDeviceMonitoringMode(this.getHandle(), value.getValue()));
    }
    
    static {
        LIBRARY = (ANImageAsciiBinaryRecordLibrary)Native.loadLibrary("NBiometricStandards", ANImageAsciiBinaryRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANImageAsciiBinaryRecord.LIBRARY.ANImageAsciiBinaryRecordTypeOf());
    }
    
    interface ANImageAsciiBinaryRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANImageAsciiBinaryRecordTypeOf();
        
        int ANImageAsciiBinaryRecordToNImage(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int ANImageAsciiBinaryRecordGetHorzLineLength(final HNObject p0, final ShortByReference p1);
        
        int ANImageAsciiBinaryRecordGetVertLineLength(final HNObject p0, final ShortByReference p1);
        
        int ANImageAsciiBinaryRecordGetScaleUnits(final HNObject p0, final IntByReference p1);
        
        int ANImageAsciiBinaryRecordGetHorzPixelScale(final HNObject p0, final ShortByReference p1);
        
        int ANImageAsciiBinaryRecordGetVertPixelScale(final HNObject p0, final ShortByReference p1);
        
        int ANImageAsciiBinaryRecordGetCompressionAlgorithm(final HNObject p0, final IntByReference p1);
        
        int ANImageAsciiBinaryRecordGetVendorCompressionAlgorithmEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANImageAsciiBinaryRecordGetBitsPerPixel(final HNObject p0, final ByteByReference p1);
        
        int ANImageAsciiBinaryRecordGetColorSpace(final HNObject p0, final IntByReference p1);
        
        int ANImageAsciiBinaryRecordGetScanHorzPixelScale(final HNObject p0, final IntByReference p1);
        
        int ANImageAsciiBinaryRecordSetScanHorzPixelScale(final HNObject p0, final int p1);
        
        int ANImageAsciiBinaryRecordGetScanVertPixelScale(final HNObject p0, final IntByReference p1);
        
        int ANImageAsciiBinaryRecordSetScanVertPixelScale(final HNObject p0, final int p1);
        
        int ANImageAsciiBinaryRecordGetCommentEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANImageAsciiBinaryRecordSetComment(final HNObject p0, final WString p1);
        
        int ANImageAsciiBinaryRecordGetDeviceMonitoringMode(final HNObject p0, final IntByReference p1);
        
        int ANImageAsciiBinaryRecordSetDeviceMonitoringMode(final HNObject p0, final int p1);
    }
}
