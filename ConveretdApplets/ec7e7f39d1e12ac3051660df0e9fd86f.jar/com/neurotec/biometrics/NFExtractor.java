// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.images.NGrayscaleImage;
import com.neurotec.jna.ptr.BooleanByReference;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NFExtractor extends NObject
{
    static final NFExtractorLibrary LIBRARY;
    public static final int PARAMETER_TEMPLATE_SIZE = 100;
    public static final int PARAMETER_MIN_MINUTIA_COUNT = 119;
    public static final int PARAMETER_EXTRACTED_RIDGE_COUNTS = 120;
    public static final int PARAMETER_RETURNED_IMAGE = 190;
    public static final int PARAMETER_USE_QUALITY = 900;
    public static final int PARAMETER_QUALITY_THRESHOLD = 910;
    public static final int PARAMETER_GENERALIZATION_THRESHOLD = 300;
    public static final int PARAMETER_GENERALIZATION_MAXIMAL_ROTATION = 301;
    public static final int PARAMETER_GENERALIZATION_MIN_MINUTIA_COUNT = 319;
    public static final int PARAMETER_MODE = 1000;
    public static final int MODE_GENERAL = 0;
    public static final int MODE_DIGITAL_PERSONA_U_ARE_U = 100;
    public static final int MODE_BIOMETRIKA_FX2000 = 200;
    public static final int MODE_BIOMETRIKA_FX3000 = 201;
    public static final int MODE_KEYTRONIC_SECURE_DESKTOP = 300;
    public static final int MODE_IDENTIX_TOUCH_VIEW = 400;
    public static final int MODE_IDENTIX_DFR2090 = 401;
    public static final int MODE_PRECISE_BIOMETRICS_100CS = 500;
    public static final int MODE_UPEK_TOUCH_CHIP = 600;
    public static final int MODE_IDENTICATOR_TECHNOLOGY_DF90 = 700;
    public static final int MODE_AUTHENTEC_AFS2 = 800;
    public static final int MODE_AUTHENTEC_AES4000 = 810;
    public static final int MODE_AUTHENTEC_AES2501B = 811;
    public static final int MODE_ATMEL_FINGER_CHIP = 900;
    public static final int MODE_BMF_BLP100 = 1000;
    public static final int MODE_SECUGEN_HAMSTER = 1100;
    public static final int MODE_ETHENTICA = 1200;
    public static final int MODE_CROSS_MATCH_VERIFIER_300 = 1300;
    public static final int MODE_NITGEN_FINGKEY_HAMSTER = 1400;
    public static final int MODE_TESTECH_BIO_I = 1500;
    public static final int MODE_DIGENT_IZZIX = 1600;
    public static final int MODE_STARTEK_FM200 = 1700;
    public static final int MODE_FUJITSU_MBF200 = 1800;
    public static final int MODE_FUTRONIC_FS80 = 1900;
    public static final int MODE_LIGHT_TUNING_LTT_C500 = 2000;
    public static final int MODE_TACOMA_CMOS = 2100;
    public static final NNativeType NATIVE_TYPE;
    
    private NFExtractor(final HNObject handle, final boolean claimHandle) {
        super(handle, NFExtractor.NATIVE_TYPE, claimHandle);
    }
    
    public NFExtractor() {
        this(false);
    }
    
    public NFExtractor(final boolean isPalm) {
        this(create(isPalm), true);
    }
    
    public static NFExtractor fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NFExtractor fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NFExtractor value = new NFExtractor(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public static NFRecord makeLightTemplate(final NFRecord record, final boolean makeSmallTemplate, final boolean preserveRidgeCounts) {
        if (record == null) {
            throw new NullPointerException("record");
        }
        NFRecord value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NFExtractor.LIBRARY.NfeMakeLightTemplate(record.getHandle(), makeSmallTemplate, preserveRidgeCounts, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NFRecord.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NFRecord makeSmallTemplate(final NFRecord record) {
        if (record == null) {
            throw new NullPointerException("record");
        }
        NFRecord value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NFExtractor.LIBRARY.NfeMakeSmallTemplate(record.getHandle(), rhValue));
        try {
            hValue = rhValue.getValue();
            value = NFRecord.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static boolean isTemplateSmall(final NFRecord record) {
        if (record == null) {
            throw new NullPointerException("record");
        }
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NFExtractor.LIBRARY.NfeIsTemplateSmall(record.getHandle(), rValue));
        return rValue.getValue();
    }
    
    private static HNObject create(final boolean isPalm) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NFExtractor.LIBRARY.NfeCreateEx(isPalm, rhValue));
        return rhValue.getValue();
    }
    
    public NFExtractorResult extract(final NGrayscaleImage image, final NFPosition position, final NFImpressionType impressionType) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        NFExtractorResult result = null;
        NFRecord value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final IntByReference rStatus = new IntByReference();
        NResult.check(NFExtractor.LIBRARY.NfeExtract(this.getHandle(), image.getHandle(), position.getValue(), impressionType.getValue(), rStatus, rhValue));
        try {
            hValue = rhValue.getValue();
            value = (HNObject.NULL.equals(hValue) ? null : NFRecord.fromHandle(hValue));
            result = new NFExtractorResult(value, NfeExtractionStatus.get(rStatus.getValue()), 0);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return result;
    }
    
    public void updateTemplate(final NGrayscaleImage image, final NFRecord record) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        if (record == null) {
            throw new NullPointerException("record");
        }
        NResult.check(NFExtractor.LIBRARY.NfeUpdateTemplate(this.getHandle(), image.getHandle(), record.getHandle()));
    }
    
    public NFExtractorResult generalize(NFRecord[] records) {
        if (records == null) {
            records = new NFRecord[0];
        }
        final int recordCount = records.length;
        NFRecord value = null;
        NFExtractorResult result = null;
        HNObject hValue = null;
        final HNObject[] hRecords = new HNObject[recordCount];
        for (int i = 0; i != recordCount; ++i) {
            if (records[i] == null) {
                throw new IllegalArgumentException("One of records elements is null");
            }
            hRecords[i] = records[i].getHandle();
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        final IntByReference rStatus = new IntByReference();
        final IntByReference rbaseTemplateIndex = new IntByReference();
        NResult.check(NFExtractor.LIBRARY.NfeGeneralizeEx(this.getHandle(), recordCount, hRecords, rStatus, rbaseTemplateIndex, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NFRecord.fromHandle(hValue);
            result = new NFExtractorResult(value, NfeExtractionStatus.get(rStatus.getValue()), rbaseTemplateIndex.getValue());
        }
        finally {
            NObject.free(hValue);
        }
        return result;
    }
    
    public boolean isPalm() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NFExtractor.LIBRARY.NfeIsPalm(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public NfeTemplateSize getTemplateSize() {
        return NfeTemplateSize.get((int)this.getParameter(100, Integer.class));
    }
    
    public void setTemplateSize(final NfeTemplateSize value) {
        this.setParameter(100, Integer.class, value.getValue());
    }
    
    public int getMinMinutiaCount() {
        return (int)this.getParameter(119, Integer.class);
    }
    
    public void setMinMinutiaCount(final int value) {
        this.setParameter(119, Integer.class, value);
    }
    
    public NFRidgeCountsType getExtractedRidgeCounts() {
        return NFRidgeCountsType.get((int)this.getParameter(120, Integer.class));
    }
    
    public void setExtractedRidgeCounts(final NFRidgeCountsType value) {
        this.setParameter(120, Integer.class, value.getValue());
    }
    
    public NfeReturnedImage getReturnedImage() {
        return NfeReturnedImage.get((int)this.getParameter(190, Integer.class));
    }
    
    public void setReturnedImage(final NfeReturnedImage value) {
        this.setParameter(190, Integer.class, value.getValue());
    }
    
    public int getMode() {
        return (int)this.getParameter(1000, Integer.class);
    }
    
    public void setMode(final int value) {
        this.setParameter(1000, Integer.class, value);
    }
    
    public boolean isUseQuality() {
        return (boolean)this.getParameter(900, Boolean.class);
    }
    
    public void setUseQuality(final boolean value) {
        this.setParameter(900, Boolean.class, value);
    }
    
    public int getQualityThreshold() {
        return (byte)this.getParameter(910, Byte.class) & 0xFF;
    }
    
    public void setQualityThreshold(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(910, Byte.class, (byte)value);
    }
    
    public int getGeneralizationThreshold() {
        return (int)this.getParameter(300, Integer.class);
    }
    
    public void setGeneralizationThreshold(final int value) {
        this.setParameter(300, Integer.class, value);
    }
    
    public int getGeneralizationMaximalRotation() {
        return (byte)this.getParameter(301, Byte.class) & 0xFF;
    }
    
    public void setGeneralizationMaximalRotation(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(301, Byte.class, (byte)value);
    }
    
    public int getGeneralizationMinMinutiaCount() {
        return (int)this.getParameter(319, Integer.class);
    }
    
    public void setGeneralizationMinMinutiaCount(final int value) {
        this.setParameter(319, Integer.class, value);
    }
    
    static {
        LIBRARY = (NFExtractorLibrary)Native.loadLibrary("NBiometrics", NFExtractorLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NFExtractor.LIBRARY.NFExtractorTypeOf());
    }
    
    public final class NFExtractorResult
    {
        private NFRecord record;
        private NfeExtractionStatus status;
        private int index;
        
        private NFExtractorResult(final NFRecord record, final NfeExtractionStatus status, final int index) {
            this.record = record;
            this.status = status;
            this.index = index;
        }
        
        public NFRecord getRecord() {
            return this.record;
        }
        
        public NfeExtractionStatus getStatus() {
            return this.status;
        }
        
        public int getIndex() {
            return this.index;
        }
    }
    
    interface NFExtractorLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NFExtractorTypeOf();
        
        int NfeMakeLightTemplate(final HNObject p0, final boolean p1, final boolean p2, final HNObjectByReference p3);
        
        int NfeMakeSmallTemplate(final HNObject p0, final HNObjectByReference p1);
        
        int NfeIsTemplateSmall(final HNObject p0, final BooleanByReference p1);
        
        int NfeCreateEx(final boolean p0, final HNObjectByReference p1);
        
        int NfeExtract(final HNObject p0, final HNObject p1, final int p2, final int p3, final IntByReference p4, final HNObjectByReference p5);
        
        int NfeUpdateTemplate(final HNObject p0, final HNObject p1, final HNObject p2);
        
        int NfeGeneralizeEx(final HNObject p0, final int p1, final HNObject[] p2, final IntByReference p3, final IntByReference p4, final HNObjectByReference p5);
        
        int NfeIsPalm(final HNObject p0, final BooleanByReference p1);
    }
}
