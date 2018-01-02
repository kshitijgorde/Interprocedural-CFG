// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.Pointer;
import com.neurotec.lang.NCore;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.images.NGrayscaleImage;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NLExtractor extends NObject
{
    static final NLExtractorLibrary LIBRARY;
    public static final int PARAMETER_MIN_IOD = 10101;
    public static final int PARAMETER_MAX_IOD = 10102;
    public static final int PARAMETER_FACE_CONFIDENCE_THRESHOLD = 10103;
    public static final int PARAMETER_FAVOR_LARGEST_FACE = 10104;
    public static final int PARAMETER_MAX_ROLL_ANGLE_DEVIATION = 10105;
    public static final int PARAMETER_FACE_QUALITY_THRESHOLD = 10350;
    public static final int PARAMETER_TEMPLATE_SIZE = 10360;
    public static final int PARAMETER_USE_LIVENESS_CHECK = 10402;
    public static final int PARAMETER_LIVENESS_THRESHOLD = 10403;
    public static final int PARAMETER_MAX_RECORDS_PER_TEMPLATE = 10408;
    public static final NNativeType NATIVE_TYPE;
    
    private NLExtractor(final HNObject handle, final boolean claimHandle) {
        super(handle, NLExtractor.NATIVE_TYPE, claimHandle);
    }
    
    public NLExtractor() {
        this(create(), true);
    }
    
    public static NLExtractor fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NLExtractor fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NLExtractor value = new NLExtractor(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public static NLTemplate compress(final NLTemplate template, final NleTemplateSize compressedTemplateSize) {
        if (template == null) {
            throw new NullPointerException("template");
        }
        NLTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NLExtractor.LIBRARY.NleCompressEx(template.getHandle(), compressedTemplateSize.getValue(), rhValue));
        try {
            hValue = rhValue.getValue();
            value = NLTemplate.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    private static HNObject create() {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NLExtractor.LIBRARY.NleCreate(rhValue));
        return rhValue.getValue();
    }
    
    public NleFace[] detectFaces(final NGrayscaleImage image) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        final IntByReference rFaceCount = new IntByReference();
        final PointerByReference rpValue = new PointerByReference();
        NResult.check(NLExtractor.LIBRARY.NleDetectFaces(this.getHandle(), image.getHandle(), rFaceCount, rpValue));
        final Pointer pValue = rpValue.getValue();
        final int count = rFaceCount.getValue();
        if (count == 0) {
            return null;
        }
        final NleFace[] values = new NleFace[count];
        Pointer p = pValue;
        for (int i = 0; i < count; ++i) {
            final NleFace value = new NleFace();
            final NleFace.NleFaceData vd = value.getData();
            final int size = vd.size();
            NCore.copy(vd.getPointer(), p, size);
            vd.read();
            p = p.share(size);
            values[i] = value;
        }
        NCore.free(pValue);
        return values;
    }
    
    public NleFace detectFace(final NGrayscaleImage image) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        final BooleanByReference rDetected = new BooleanByReference();
        final NleFace value = new NleFace();
        NResult.check(NLExtractor.LIBRARY.NleDetectFace(this.getHandle(), image.getHandle(), rDetected, value.getData()));
        if (!rDetected.getValue()) {
            return null;
        }
        return value;
    }
    
    public NleDetectionDetails detectFacialFeatures(final NGrayscaleImage image, final NleFace face) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        final NleDetectionDetails details = new NleDetectionDetails();
        NResult.check(NLExtractor.LIBRARY.NleDetectFacialFeatures(this.getHandle(), image.getHandle(), face.getData(), details.getData()));
        return details;
    }
    
    public NLExtractorResult extract(final NGrayscaleImage image) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        NLExtractorResult result = null;
        NLTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NleDetectionDetails detectionDetails = new NleDetectionDetails();
        final IntByReference rStatus = new IntByReference();
        NResult.check(NLExtractor.LIBRARY.NleExtract(this.getHandle(), image.getHandle(), detectionDetails.getData(), rStatus, rhValue));
        try {
            hValue = rhValue.getValue();
            value = (HNObject.NULL.equals(hValue) ? null : NLTemplate.fromHandle(hValue));
            result = new NLExtractorResult(value, detectionDetails, NleExtractionStatus.get(rStatus.getValue()), 0);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return result;
    }
    
    public void extractStart(final int durationInFrames) {
        NResult.check(NLExtractor.LIBRARY.NleExtractStart(this.getHandle(), durationInFrames));
    }
    
    public NLExtractorResult extractNext(final NGrayscaleImage image) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        NLExtractorResult result = null;
        NLTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NleDetectionDetails detectionDetails = new NleDetectionDetails();
        final IntByReference rStatus = new IntByReference();
        final IntByReference rBaseFrameIndex = new IntByReference();
        NResult.check(NLExtractor.LIBRARY.NleExtractNextEx(this.getHandle(), image.getHandle(), detectionDetails.getData(), rBaseFrameIndex, rhValue, rStatus));
        try {
            hValue = rhValue.getValue();
            value = (HNObject.NULL.equals(hValue) ? null : NLTemplate.fromHandle(hValue));
            result = new NLExtractorResult(value, detectionDetails, NleExtractionStatus.get(rStatus.getValue()), rBaseFrameIndex.getValue());
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return result;
    }
    
    public NLExtractorResult extractUsingDetails(final NGrayscaleImage image, final NleDetectionDetails detectionDetails) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        NLExtractorResult result = null;
        NLTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final IntByReference rStatus = new IntByReference();
        NResult.check(NLExtractor.LIBRARY.NleExtractUsingDetails(this.getHandle(), image.getHandle(), detectionDetails.getData(), rStatus, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NLTemplate.fromHandle(hValue);
            result = new NLExtractorResult(value, detectionDetails, NleExtractionStatus.get(rStatus.getValue()), 0);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return result;
    }
    
    public NLExtractorResult generalize(NLTemplate[] templates) {
        NLExtractorResult result = null;
        if (templates == null) {
            templates = new NLTemplate[0];
        }
        final int templateCount = templates.length;
        final HNObject[] hValues = new HNObject[templateCount];
        for (int i = 0; i != templateCount; ++i) {
            if (templates[i] == null) {
                throw new NullPointerException("One of templates elements is null");
            }
            hValues[i] = templates[i].getHandle();
        }
        NLTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final IntByReference rStatus = new IntByReference();
        final IntByReference rBaseFrameIndex = new IntByReference();
        NResult.check(NLExtractor.LIBRARY.NleGeneralizeEx(this.getHandle(), templateCount, hValues, rStatus, rBaseFrameIndex, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NLTemplate.fromHandle(hValue);
            result = new NLExtractorResult(value, (NleDetectionDetails)null, NleExtractionStatus.get(rStatus.getValue()), rBaseFrameIndex.getValue());
        }
        finally {
            NObject.free(hValue);
        }
        return result;
    }
    
    public int getMinIod() {
        return (int)this.getParameter(10101, Integer.class);
    }
    
    public void setMinIod(final int value) {
        this.setParameter(10101, Integer.class, value);
    }
    
    public int getMaxIod() {
        return (int)this.getParameter(10102, Integer.class);
    }
    
    public void setMaxIod(final int value) {
        this.setParameter(10102, Integer.class, value);
    }
    
    public double getFaceConfidenceThreshold() {
        return (double)this.getParameter(10103, Double.class);
    }
    
    public void setFaceConfidenceThreshold(final double value) {
        this.setParameter(10103, Double.class, value);
    }
    
    public boolean isFavorLargestFace() {
        return (boolean)this.getParameter(10104, Boolean.class);
    }
    
    public void setFavorLargestFace(final boolean value) {
        this.setParameter(10104, Boolean.class, value);
    }
    
    public short getMaxRollAngleDeviation() {
        return (short)this.getParameter(10105, Short.class);
    }
    
    public void setMaxRollAngleDeviation(final short value) {
        this.setParameter(10105, Short.class, value);
    }
    
    public int getFaceQualityThreshold() {
        return (byte)this.getParameter(10350, Byte.class) & 0xFF;
    }
    
    public void setFaceQualityThreshold(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(10350, Byte.class, (byte)value);
    }
    
    public NleTemplateSize getTemplateSize() {
        return NleTemplateSize.get((int)this.getParameter(10360, Integer.class));
    }
    
    public void setTemplateSize(final NleTemplateSize value) {
        this.setParameter(10360, Integer.class, value.getValue());
    }
    
    public double getLivenessThreshold() {
        return (double)this.getParameter(10403, Double.class);
    }
    
    public void setLivenessThreshold(final double value) {
        this.setParameter(10403, Double.class, value);
    }
    
    public boolean isUseLivenessCheck() {
        return (boolean)this.getParameter(10402, Boolean.class);
    }
    
    public void setUseLivenessCheck(final boolean value) {
        this.setParameter(10402, Boolean.class, value);
    }
    
    public int getMaxRecordsPerTemplate() {
        return (int)this.getParameter(10408, Integer.class);
    }
    
    public void setMaxRecordsPerTemplate(final int value) {
        this.setParameter(10408, Integer.class, value);
    }
    
    static {
        LIBRARY = (NLExtractorLibrary)Native.loadLibrary("NBiometrics", NLExtractorLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NLExtractor.LIBRARY.NLExtractorTypeOf());
    }
    
    public final class NLExtractorResult
    {
        private NLTemplate template;
        private NleDetectionDetails detectionDetails;
        private NleExtractionStatus status;
        private int baseFrameIndex;
        
        private NLExtractorResult(final NLTemplate value, final NleDetectionDetails detectionDetails, final NleExtractionStatus status, final int baseFrameIndex) {
            this.template = value;
            this.detectionDetails = detectionDetails;
            this.status = status;
            this.baseFrameIndex = baseFrameIndex;
        }
        
        public NLTemplate getTemplate() {
            return this.template;
        }
        
        public NleDetectionDetails getDetectionDetails() {
            return this.detectionDetails;
        }
        
        public NleExtractionStatus getStatus() {
            return this.status;
        }
        
        public int getBaseFrameIndex() {
            return this.baseFrameIndex;
        }
    }
    
    interface NLExtractorLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NLExtractorTypeOf();
        
        int NleCompressEx(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NleCreate(final HNObjectByReference p0);
        
        int NleDetectFaces(final HNObject p0, final HNObject p1, final IntByReference p2, final PointerByReference p3);
        
        int NleDetectFace(final HNObject p0, final HNObject p1, final BooleanByReference p2, final NleFace.NleFaceData p3);
        
        int NleDetectFacialFeatures(final HNObject p0, final HNObject p1, final NleFace.NleFaceData p2, final NleDetectionDetails.NleDetectionDetailsData p3);
        
        int NleExtract(final HNObject p0, final HNObject p1, final NleDetectionDetails.NleDetectionDetailsData p2, final IntByReference p3, final HNObjectByReference p4);
        
        int NleExtractStart(final HNObject p0, final int p1);
        
        int NleExtractNextEx(final HNObject p0, final HNObject p1, final NleDetectionDetails.NleDetectionDetailsData p2, final IntByReference p3, final HNObjectByReference p4, final IntByReference p5);
        
        int NleExtractUsingDetails(final HNObject p0, final HNObject p1, final NleDetectionDetails.NleDetectionDetailsData p2, final IntByReference p3, final HNObjectByReference p4);
        
        int NleGeneralizeEx(final HNObject p0, final int p1, final HNObject[] p2, final IntByReference p3, final IntByReference p4, final HNObjectByReference p5);
    }
}
