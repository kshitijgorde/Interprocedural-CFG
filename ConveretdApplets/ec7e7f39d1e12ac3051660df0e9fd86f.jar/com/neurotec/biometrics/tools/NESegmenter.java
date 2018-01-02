// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import com.sun.jna.Pointer;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.biometrics.NeeExtractionStatus;
import com.neurotec.images.NImage;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.biometrics.NeeImageKind;
import com.neurotec.images.NGrayscaleImage;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NESegmenter extends NObject
{
    static final NESegmenterLibrary LIBRARY;
    public static final int PARAMETER_HORIZONTAL_MARGIN = 10001;
    public static final int PARAMETER_VERTICAL_MARGIN = 10002;
    public static final int PARAMETER_UPPER_EYELID_MASK_VALUE = 10011;
    public static final int PARAMETER_SCLERA_MASK_VALUE = 10012;
    public static final int PARAMETER_LOWER_EYELID_MASK_VALUE = 10013;
    public static final int PARAMETER_QUALITY_THRESHOLD = 20001;
    public static final int PARAMETER_INTERLACE_THRESHOLD = 20017;
    public static final NNativeType NATIVE_TYPE;
    
    private NESegmenter(final HNObject handle, final boolean claimHandle) {
        super(handle, NESegmenter.NATIVE_TYPE, claimHandle);
    }
    
    public NESegmenter() {
        this(create(), true);
    }
    
    public static NESegmenter fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NESegmenter fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NESegmenter value = new NESegmenter(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private static HNObject create() {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NESegmenter.LIBRARY.NESegmenterCreate(rhValue));
        return rhValue.getValue();
    }
    
    public NESegmenterResult process(final NGrayscaleImage image, final NeeImageKind resultImageKind, final boolean returnAttributes) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        boolean ok = false;
        NESegmenterResult result = null;
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NESegmenterAttributes attributes = null;
        HNObject hAttributes = null;
        final HNObjectByReference rhAttributes = returnAttributes ? new HNObjectByReference() : null;
        final IntByReference rStatus = new IntByReference();
        final ByteByReference rQuality = new ByteByReference();
        NResult.check(NESegmenter.LIBRARY.NESegmenterProcess(this.getHandle(), image.getHandle(), resultImageKind.getValue(), rhAttributes, rQuality, rStatus, rhValue));
        try {
            if (returnAttributes) {
                hAttributes = rhAttributes.getValue();
                attributes = (HNObject.NULL.equals(hAttributes) ? null : NESegmenterAttributes.fromHandle(hAttributes));
            }
            hValue = rhValue.getValue();
            value = (HNObject.NULL.equals(hValue) ? null : NImage.fromHandle(hValue));
            result = new NESegmenterResult(value, attributes, rQuality.getValue() & 0xFF, NeeExtractionStatus.get(rStatus.getValue()));
            ok = true;
        }
        finally {
            if (!ok) {
                attributes.dispose();
                attributes = null;
                NObject.free(hAttributes);
                NObject.free(hValue);
            }
        }
        return result;
    }
    
    public double getHorizontalMargin() {
        return (double)this.getParameter(10001, Double.class);
    }
    
    public void setHorizontalMargin(final double value) {
        this.setParameter(10001, Double.class, value);
    }
    
    public double getVerticalMargin() {
        return (double)this.getParameter(10002, Double.class);
    }
    
    public void setVerticalMargin(final double value) {
        this.setParameter(10002, Double.class, value);
    }
    
    public int getUpperEyelidMaskValue() {
        return (byte)this.getParameter(10011, Byte.class) & 0xFF;
    }
    
    public void setUpperEyelidMaskValue(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(10011, Byte.class, (byte)value);
    }
    
    public int getScleraMaskValue() {
        return (byte)this.getParameter(10012, Byte.class) & 0xFF;
    }
    
    public void setScleraMaskValue(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(10012, Byte.class, (byte)value);
    }
    
    public int getLowerEyelidMaskValue() {
        return (byte)this.getParameter(10013, Byte.class) & 0xFF;
    }
    
    public void setLowerEyelidMaskValue(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(10013, Byte.class, (byte)value);
    }
    
    public int getQualityThreshold() {
        return (byte)this.getParameter(20001, Byte.class) & 0xFF;
    }
    
    public void setQualityThreshold(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(20001, Byte.class, (byte)value);
    }
    
    public int getInterlaceThreshold() {
        return (byte)this.getParameter(20017, Byte.class) & 0xFF;
    }
    
    public void setInterlaceThreshold(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(20017, Byte.class, (byte)value);
    }
    
    static {
        LIBRARY = (NESegmenterLibrary)Native.loadLibrary("NBiometrics", NESegmenterLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NESegmenter.LIBRARY.NESegmenterTypeOf());
    }
    
    public final class NESegmenterResult
    {
        private NImage image;
        private NESegmenterAttributes attributes;
        private int quality;
        private NeeExtractionStatus status;
        
        private NESegmenterResult(final NImage image, final NESegmenterAttributes attributes, final int quality, final NeeExtractionStatus status) {
            this.image = image;
            this.attributes = attributes;
            this.quality = quality;
            this.status = status;
        }
        
        public NImage getImage() {
            return this.image;
        }
        
        public NESegmenterAttributes getAttributes() {
            return this.attributes;
        }
        
        public int getQuality() {
            return this.quality;
        }
        
        public NeeExtractionStatus getStatus() {
            return this.status;
        }
    }
    
    interface NESegmenterLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NESegmenterTypeOf();
        
        int NESegmenterCreate(final HNObjectByReference p0);
        
        int NESegmenterProcess(final HNObject p0, final HNObject p1, final int p2, final Pointer p3, final ByteByReference p4, final IntByReference p5, final HNObjectByReference p6);
        
        int NESegmenterProcess(final HNObject p0, final HNObject p1, final int p2, final HNObjectByReference p3, final ByteByReference p4, final IntByReference p5, final HNObjectByReference p6);
    }
}
