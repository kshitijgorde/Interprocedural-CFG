// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.DoubleByReference;
import com.neurotec.awt.NPointData;
import java.awt.Point;
import com.neurotec.images.NImage;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class Ntfi extends NObject
{
    static final NtfiLibrary LIBRARY;
    public static final int PARAMETER_TOKEN_FACE_IMAGE_WIDTH = 10001;
    public static final int PARAMETER_USE_LIGHTNESS_NORMALIZATION = 10002;
    public static final int PARAMETER_QUALITY_THRESHOLD = 20001;
    public static final int PARAMETER_SHARPNESS_THRESHOLD = 20002;
    public static final int PARAMETER_BACKGROUND_UNIFORMITY_THRESHOLD = 20003;
    public static final int PARAMETER_GRAYSCALE_DENSITY_THRESHOLD = 20004;
    public static final NNativeType NATIVE_TYPE;
    
    private Ntfi(final HNObject handle, final boolean claimHandle) {
        super(handle, Ntfi.NATIVE_TYPE, claimHandle);
    }
    
    public Ntfi() {
        this(create(), true);
    }
    
    public static Ntfi fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static Ntfi fromHandle(final HNObject handle, final boolean ownsHandle) {
        final Ntfi value = new Ntfi(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private static HNObject create() {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(Ntfi.LIBRARY.NtfiCreate(rhValue));
        return rhValue.getValue();
    }
    
    public NImage createTokenFaceImage(final NImage image, final Point leftEye, final Point rightEye) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(Ntfi.LIBRARY.NtfiCreateTokenFaceImage(this.getHandle(), image.getHandle(), new NPointData(leftEye.x, leftEye.y), new NPointData(rightEye.x, rightEye.y), rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImage.fromHandle(hValue);
        }
        finally {
            if (image == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public NtfiResult testTokenFaceImage(final NImage tokenFaceImage) {
        if (tokenFaceImage == null) {
            throw new NullPointerException("tokenFaceImage");
        }
        NtfiResult result = null;
        NtfiAttributes attributes = null;
        HNObject hAttributes = null;
        final HNObjectByReference rhAttributes = new HNObjectByReference();
        final DoubleByReference rScore = new DoubleByReference();
        NResult.check(Ntfi.LIBRARY.NtfiTestTokenFaceImage(this.getHandle(), tokenFaceImage.getHandle(), rhAttributes, rScore));
        try {
            hAttributes = rhAttributes.getValue();
            attributes = NtfiAttributes.fromHandle(hAttributes);
            result = new NtfiResult(attributes, rScore.getValue());
        }
        finally {
            if (attributes == null) {
                NObject.free(hAttributes);
            }
        }
        return result;
    }
    
    public int getTokenFaceImageWidth() {
        return (int)this.getParameter(10001, Integer.class);
    }
    
    public void setTokenFaceImageWidth(final int value) {
        this.setParameter(10001, Integer.class, value);
    }
    
    public boolean isUseLightnessNormalization() {
        return (boolean)this.getParameter(10002, Boolean.class);
    }
    
    public void setUseLightnessNormalization(final boolean value) {
        this.setParameter(10002, Boolean.class, value);
    }
    
    public double getQualityThreshold() {
        return (double)this.getParameter(20001, Double.class);
    }
    
    public void setQualityThreshold(final double value) {
        this.setParameter(20001, Double.class, value);
    }
    
    public double getSharpnessThreshold() {
        return (double)this.getParameter(20002, Double.class);
    }
    
    public void setSharpnessThreshold(final double value) {
        this.setParameter(20002, Double.class, value);
    }
    
    public double getBackgroundUniformityThreshold() {
        return (double)this.getParameter(20003, Double.class);
    }
    
    public void setBackgroundUniformityThreshold(final double value) {
        this.setParameter(20003, Double.class, value);
    }
    
    public double getGrayscaleDensityThreshold() {
        return (double)this.getParameter(20004, Double.class);
    }
    
    public void setGrayscaleDensityThreshold(final double value) {
        this.setParameter(20004, Double.class, value);
    }
    
    static {
        LIBRARY = (NtfiLibrary)Native.loadLibrary("NBiometrics", NtfiLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(Ntfi.LIBRARY.NtfiTypeOf());
    }
    
    public final class NtfiResult
    {
        private NtfiAttributes attributes;
        private double score;
        
        private NtfiResult(final NtfiAttributes attributes, final double score) {
            this.attributes = attributes;
            this.score = score;
        }
        
        public NtfiAttributes getAttributes() {
            return this.attributes;
        }
        
        public double getScore() {
            return this.score;
        }
    }
    
    interface NtfiLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NtfiTypeOf();
        
        int NtfiCreate(final HNObjectByReference p0);
        
        int NtfiCreateTokenFaceImage(final HNObject p0, final HNObject p1, final NPointData p2, final NPointData p3, final HNObjectByReference p4);
        
        int NtfiTestTokenFaceImage(final HNObject p0, final HNObject p1, final HNObjectByReference p2, final DoubleByReference p3);
    }
}
