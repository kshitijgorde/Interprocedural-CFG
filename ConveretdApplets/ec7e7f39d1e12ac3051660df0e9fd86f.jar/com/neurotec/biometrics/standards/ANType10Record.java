// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NCollection;
import com.neurotec.biometrics.standards.util.ANRecordArrayCollection;
import com.neurotec.lang.NObject;
import com.neurotec.biometrics.standards.util.ANRecordSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.awt.NDimensionData;
import java.awt.Dimension;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.BooleanByReference;
import java.util.Date;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NResult;
import com.sun.jna.WString;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType10Record extends ANImageAsciiBinaryRecord
{
    static final ANType10RecordLibrary LIBRARY;
    public static final int FIELD_IMT = 3;
    public static final int FIELD_PHD = 5;
    public static final int FIELD_CSP = 12;
    public static final int FIELD_SAP = 13;
    public static final int FIELD_POS = 20;
    public static final int FIELD_POA = 21;
    public static final int FIELD_PXS = 22;
    public static final int FIELD_PAS = 23;
    public static final int FIELD_SQS = 24;
    public static final int FIELD_SPA = 25;
    public static final int FIELD_SXS = 26;
    public static final int FIELD_SEC = 27;
    public static final int FIELD_SHC = 28;
    public static final int FIELD_FFP = 29;
    public static final int FIELD_SMT = 40;
    public static final int FIELD_SMS = 41;
    public static final int FIELD_SMD = 42;
    public static final int FIELD_COL = 43;
    public static final int SAP_UNKNOWN = 0;
    public static final int SAP_SURVEILLANCE_FACIAL_IMAGE = 1;
    public static final int SAP_DRIVERS_LICENSE_IMAGE = 10;
    public static final int SAP_ANSI_FULL_FRONTAL_FACIAL_IMAGE = 11;
    public static final int SAP_ANSI_TOKEN_FACIAL_IMAGE = 12;
    public static final int SAP_ISO_FULL_FRONTAL_FACIAL_IMAGE = 13;
    public static final int SAP_ISO_TOKEN_FACIAL_IMAGE = 14;
    public static final int SAP_PIV_FACIAL_IMAGE = 15;
    public static final int SAP_LEGACY_MUGSHOT = 20;
    public static final int SAP_BPA_LEVEL_30 = 30;
    public static final int SAP_BPA_LEVEL_40 = 40;
    public static final int SAP_BPA_LEVEL_50 = 50;
    public static final int SAP_BPA_LEVEL_51 = 51;
    public static final int MAX_PHOTO_DESCRIPTION_COUNT = 9;
    public static final int MAX_QUALITY_METRIC_COUNT = 9;
    public static final int MAX_SUBJECT_FACIAL_DESCRIPTION_COUNT = 50;
    public static final int MAX_FACIAL_FEATURE_POINT_COUNT = 88;
    public static final int MAX_NCIC_DESIGNATION_CODE_COUNT = 3;
    public static final int MAX_SMT_COUNT = 9;
    public static final int MAX_PHYSICAL_PHOTO_CHARACTERISTIC_LENGTH = 11;
    public static final int MAX_OTHER_PHOTO_CHARACTERISTIC_LENGTH = 14;
    public static final int MIN_SUBJECT_FACIAL_CHARACTERISTIC_LENGTH = 5;
    public static final int MAX_SUBJECT_FACIAL_CHARACTERISTIC_LENGTH = 20;
    public static final int MAX_VENDOR_PHOTO_ACQUISITION_SOURCE_LENGTH = 7;
    public static final int MIN_NCIC_DESIGNATION_CODE_LENGTH = 3;
    public static final int MAX_NCIC_DESIGNATION_CODE_LENGTH = 10;
    public static final int MAX_SMT_SIZE = 99;
    private PhysicalPhotoCharacteristicCollection physicalPhotoCharacteristics;
    private OtherPhotoCharacteristicCollection otherPhotoCharacteristics;
    private SubjectQualityScoreCollection subjectQualityScores;
    private SubjectFacialCharacteristicCollection subjectFacialCharacteristics;
    private FacialFeaturePointCollection facialFeaturePoints;
    private NcicDesignationCodeCollection ncicDesignationCodes;
    private SmtCollection smts;
    private SmtColorsCollection smtsColors;
    public static final NNativeType NATIVE_TYPE;
    
    ANType10Record(final HNObject handle) {
        super(handle, ANType10Record.NATIVE_TYPE);
        this.physicalPhotoCharacteristics = new PhysicalPhotoCharacteristicCollection(this);
        this.otherPhotoCharacteristics = new OtherPhotoCharacteristicCollection(this);
        this.subjectQualityScores = new SubjectQualityScoreCollection(this);
        this.subjectFacialCharacteristics = new SubjectFacialCharacteristicCollection(this);
        this.facialFeaturePoints = new FacialFeaturePointCollection(this);
        this.ncicDesignationCodes = new NcicDesignationCodeCollection(this);
        this.smts = new SmtCollection(this);
        this.smtsColors = new SmtColorsCollection(this, this.smts);
    }
    
    public void setPhotoAcquisitionSource(final BdifImageSourceType value, final String vendorValue) {
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetPhotoAcquisitionSource(this.getHandle(), value.getValue(), new WString(vendorValue)));
        this.refresh();
    }
    
    public void setSubjectPoseAngles(final int yaw, final int pitch, final int roll, final int yawUncertainty, final int pitchUncertainty, final int rollUncertainty) {
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSubjectPoseAngles(this.getHandle(), yaw, pitch, roll, yawUncertainty, pitchUncertainty, rollUncertainty));
        this.refresh();
    }
    
    public void setSubjectHairColor(final BdifHairColor value, final BdifHairColor baldValue) {
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSubjectHairColor(this.getHandle(), value.getValue(), baldValue.getValue()));
        this.refresh();
    }
    
    public ANImageType getImageType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetImageType(this.getHandle(), rValue));
        return ANImageType.get(rValue.getValue());
    }
    
    public Date getPhotoDate() {
        return this.getDate();
    }
    
    public void setPhotoDate(final Date value) {
        this.setDate(value);
    }
    
    public int getSubjectAcquisitionProfile() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectAcquisitionProfile(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setSubjectAcquisitionProfile(final int value) {
        if (value < 0 || value > 32767) {
            throw new IllegalArgumentException("value");
        }
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSubjectAcquisitionProfile(this.getHandle(), (short)value));
    }
    
    public ANSubjectPose getSubjectPose() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectPose(this.getHandle(), rValue));
        return ANSubjectPose.get(rValue.getValue());
    }
    
    public void setSubjectPose(final ANSubjectPose value) {
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSubjectPose(this.getHandle(), value.getValue()));
    }
    
    public Integer getPoseOffsetAngle() {
        final IntByReference rValue = new IntByReference();
        final BooleanByReference rHasValue = new BooleanByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetPoseOffsetAngle(this.getHandle(), rValue, rHasValue));
        return rHasValue.getValue() ? rValue.getValue() : null;
    }
    
    public void setPoseOffsetAngle(final Integer value) {
        final IntByReference rValue = new IntByReference(value);
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetPoseOffsetAngle(this.getHandle(), (value == null) ? null : rValue));
        this.refresh();
    }
    
    public BdifFaceProperties getPhotoAttributes() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetPhotoAttributes(this.getHandle(), rValue));
        return BdifFaceProperties.get(rValue.getValue());
    }
    
    public void setPhotoAttributes(final BdifFaceProperties value) {
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetPhotoAttributes(this.getHandle(), value.getValue()));
    }
    
    public BdifImageSourceType getPhotoAcquisitionSource() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetPhotoAcquisitionSource(this.getHandle(), rValue));
        return BdifImageSourceType.get(rValue.getValue());
    }
    
    public String getVendorPhotoAcquisitionSource() {
        final int pLen = ANType10Record.LIBRARY.ANType10RecordGetVendorPhotoAcquisitionSourceEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType10Record.LIBRARY.ANType10RecordGetVendorPhotoAcquisitionSourceEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public int getSubjectPoseAnglesYaw() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectPoseAnglesYaw(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getSubjectPoseAnglesPitch() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectPoseAnglesPitch(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getSubjectPoseAnglesRoll() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectPoseAnglesRoll(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getSubjectPoseAnglesYawUncertainty() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectPoseAnglesYawUncertainty(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getSubjectPoseAnglesPitchUncertainty() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectPoseAnglesPitchUncertainty(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getSubjectPoseAnglesRollUncertainty() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectPoseAnglesRollUncertainty(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public BdifFaceExpression getSubjectFacialExpression() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectFacialExpression(this.getHandle(), rValue));
        return BdifFaceExpression.get(rValue.getValue());
    }
    
    public void setSubjectFacialExpression(final BdifFaceExpression value) {
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSubjectFacialExpression(this.getHandle(), value.getValue()));
    }
    
    public BdifFaceProperties getSubjectFacialAttributes() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectFacialAttributes(this.getHandle(), rValue));
        return BdifFaceProperties.get(rValue.getValue());
    }
    
    public void setSubjectFacialAttributes(final BdifFaceProperties value) {
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSubjectFacialAttributes(this.getHandle(), value.getValue()));
    }
    
    public BdifEyeColor getSubjectEyeColor() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectEyeColor(this.getHandle(), rValue));
        return BdifEyeColor.get(rValue.getValue());
    }
    
    public void setSubjectEyeColor(final BdifEyeColor value) {
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSubjectEyeColor(this.getHandle(), value.getValue()));
    }
    
    public BdifHairColor getSubjectHairColor() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectHairColor(this.getHandle(), rValue));
        return BdifHairColor.get(rValue.getValue());
    }
    
    public BdifHairColor getBaldSubjectHairColor() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetBaldSubjectHairColor(this.getHandle(), rValue));
        return BdifHairColor.get(rValue.getValue());
    }
    
    public Dimension getSmtSize() {
        final NDimensionData rValue = new NDimensionData();
        final BooleanByReference rHasValue = new BooleanByReference();
        NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSmtSize(this.getHandle(), rValue, rHasValue));
        return rHasValue.getValue() ? new Dimension(rValue.width, rValue.height) : null;
    }
    
    public void setSmtSize(final Dimension value) {
        NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSmtSize(this.getHandle(), (value == null) ? null : new NDimensionData(value.width, value.width)));
        this.refresh();
    }
    
    public PhysicalPhotoCharacteristicCollection getPhysicalPhotoCharacteristics() {
        this.check();
        return this.physicalPhotoCharacteristics;
    }
    
    public OtherPhotoCharacteristicCollection getOtherPhotoCharacteristics() {
        this.check();
        return this.otherPhotoCharacteristics;
    }
    
    public SubjectQualityScoreCollection getSubjectQualityScores() {
        this.check();
        return this.subjectQualityScores;
    }
    
    public SubjectFacialCharacteristicCollection getSubjectFacialCharacteristics() {
        this.check();
        return this.subjectFacialCharacteristics;
    }
    
    public FacialFeaturePointCollection getFacialFeaturePoints() {
        this.check();
        return this.facialFeaturePoints;
    }
    
    public NcicDesignationCodeCollection getNcicDesignationCodes() {
        this.check();
        return this.ncicDesignationCodes;
    }
    
    public SmtCollection getSmts() {
        this.check();
        return this.smts;
    }
    
    public SmtColorsCollection getSmtsColors() {
        this.check();
        return this.smtsColors;
    }
    
    static {
        LIBRARY = (ANType10RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType10RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType10Record.LIBRARY.ANType10RecordTypeOf());
    }
    
    public final class PhysicalPhotoCharacteristicCollection extends ANRecordSimpleCollection<String>
    {
        protected PhysicalPhotoCharacteristicCollection(final ANType10Record owner) {
            super(owner, 22, false, false, false);
        }
        
        protected void addNative(final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordAddPhysicalPhotoCharacteristic(this.getOwner().getHandle(), new WString(value)));
        }
        
        protected int addWithIndexNative(final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected String[] getAllNative() {
            throw new UnsupportedOperationException();
        }
        
        protected String getNative(final int index) {
            final int pLen = ANType10Record.LIBRARY.ANType10RecordGetPhysicalPhotoCharacteristicEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANType10Record.LIBRARY.ANType10RecordGetPhysicalPhotoCharacteristicEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        protected void addNative(final int index, final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordInsertPhysicalPhotoCharacteristic(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        protected void setNative(final int index, final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordSetPhysicalPhotoCharacteristic(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        protected void clearNative() {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordClearPhysicalPhotoCharacteristics(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetPhysicalPhotoCharacteristicCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordRemovePhysicalPhotoCharacteristic(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class OtherPhotoCharacteristicCollection extends ANRecordSimpleCollection<String>
    {
        protected OtherPhotoCharacteristicCollection(final ANType10Record owner) {
            super(owner, 22, false, false, false);
        }
        
        protected void addNative(final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordAddOtherPhotoCharacteristic(this.getOwner().getHandle(), new WString(value)));
        }
        
        protected int addWithIndexNative(final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected String[] getAllNative() {
            throw new UnsupportedOperationException();
        }
        
        protected String getNative(final int index) {
            final int pLen = ANType10Record.LIBRARY.ANType10RecordGetOtherPhotoCharacteristicEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANType10Record.LIBRARY.ANType10RecordGetOtherPhotoCharacteristicEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        protected void addNative(final int index, final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordInsertOtherPhotoCharacteristic(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        protected void setNative(final int index, final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordSetOtherPhotoCharacteristic(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        protected void clearNative() {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordClearOtherPhotoCharacteristics(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetOtherPhotoCharacteristicCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordRemoveOtherPhotoCharacteristic(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class SubjectQualityScoreCollection extends ANRecordSimpleCollection<ANQualityMetric>
    {
        protected SubjectQualityScoreCollection(final ANType10Record owner) {
            super(owner, 24, false, false, false);
        }
        
        protected void addNative(final ANQualityMetric value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordAddSubjectQualityScore(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final ANQualityMetric value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANQualityMetric[] getAllNative() {
            final int size = this.sizeNative();
            final ANQualityMetric.ANQualityMetricData[] structures = (ANQualityMetric.ANQualityMetricData[])new ANQualityMetric.ANQualityMetricData().toArray(size);
            final ANQualityMetric[] values = new ANQualityMetric[size];
            ANType10Record.LIBRARY.ANType10RecordGetSubjectQualityScoresEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANQualityMetric(structures[i]);
            }
            return values;
        }
        
        protected ANQualityMetric getNative(final int index) {
            final ANQualityMetric value = new ANQualityMetric();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectQualityScore(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final ANQualityMetric value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordInsertSubjectQualityScore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final ANQualityMetric value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSubjectQualityScore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordClearSubjectQualityScores(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectQualityScoreCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordRemoveSubjectQualityScore(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANQualityMetric value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class SubjectFacialCharacteristicCollection extends ANRecordSimpleCollection<String>
    {
        protected SubjectFacialCharacteristicCollection(final ANType10Record owner) {
            super(owner, 22, false, false, false);
        }
        
        protected void addNative(final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordAddSubjectFacialCharacteristic(this.getOwner().getHandle(), new WString(value)));
        }
        
        protected int addWithIndexNative(final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected String[] getAllNative() {
            throw new UnsupportedOperationException();
        }
        
        protected String getNative(final int index) {
            final int pLen = ANType10Record.LIBRARY.ANType10RecordGetSubjectFacialCharacteristicEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANType10Record.LIBRARY.ANType10RecordGetSubjectFacialCharacteristicEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        protected void addNative(final int index, final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordInsertSubjectFacialCharacteristic(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        protected void setNative(final int index, final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordSetSubjectFacialCharacteristic(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        protected void clearNative() {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordClearSubjectFacialCharacteristics(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSubjectFacialCharacteristicCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordRemoveSubjectFacialCharacteristic(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class FacialFeaturePointCollection extends ANRecordSimpleCollection<BdifFaceFeaturePoint>
    {
        protected FacialFeaturePointCollection(final ANType10Record owner) {
            super(owner, 29, false, false, false);
        }
        
        protected void addNative(final BdifFaceFeaturePoint value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordAddFacialFeaturePoint(this.getOwner().getHandle(), value.getData()));
        }
        
        protected void addNative(final int index, final BdifFaceFeaturePoint value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordInsertFacialFeaturePoint(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected int addWithIndexNative(final BdifFaceFeaturePoint value) {
            throw new UnsupportedOperationException();
        }
        
        protected BdifFaceFeaturePoint[] getAllNative() {
            final int size = this.sizeNative();
            final BdifFaceFeaturePoint.BdifFaceFeaturePointData[] structures = (BdifFaceFeaturePoint.BdifFaceFeaturePointData[])new BdifFaceFeaturePoint.BdifFaceFeaturePointData().toArray(size);
            final BdifFaceFeaturePoint[] values = new BdifFaceFeaturePoint[size];
            ANType10Record.LIBRARY.ANType10RecordGetFacialFeaturePointsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new BdifFaceFeaturePoint(structures[i]);
            }
            return values;
        }
        
        protected BdifFaceFeaturePoint getNative(final int index) {
            final BdifFaceFeaturePoint value = new BdifFaceFeaturePoint();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetFacialFeaturePoint(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void setNative(final int index, final BdifFaceFeaturePoint value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordSetFacialFeaturePoint(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordClearFacialFeaturePoints(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetFacialFeaturePointCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordRemoveFacialFeaturePoint(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final BdifFaceFeaturePoint value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class NcicDesignationCodeCollection extends ANRecordSimpleCollection<String>
    {
        protected NcicDesignationCodeCollection(final ANType10Record owner) {
            super(owner, 22, false, false, false);
        }
        
        protected void addNative(final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordAddNcicDesignationCode(this.getOwner().getHandle(), new WString(value)));
        }
        
        protected int addWithIndexNative(final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected String[] getAllNative() {
            throw new UnsupportedOperationException();
        }
        
        protected String getNative(final int index) {
            final int pLen = ANType10Record.LIBRARY.ANType10RecordGetNcicDesignationCodeEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANType10Record.LIBRARY.ANType10RecordGetNcicDesignationCodeEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        protected void addNative(final int index, final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordInsertNcicDesignationCode(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        protected void setNative(final int index, final String value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordSetNcicDesignationCode(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        protected void clearNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetNcicDesignationCodeCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordRemoveNcicDesignationCode(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class SmtCollection extends ANRecordSimpleCollection<ANSmt>
    {
        protected SmtCollection(final ANType10Record owner) {
            super(owner, 42, false, false, false);
        }
        
        protected void addNative(final ANSmt value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordAddSmt(this.getOwner().getHandle(), value.getSource().getValue(), value.getTattooClass().getValue(), value.getTattooSubclass().getValue(), new WString(value.getDescription())));
        }
        
        protected int addWithIndexNative(final ANSmt value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANSmt[] getAllNative() {
            throw new UnsupportedOperationException();
        }
        
        protected ANSmt getNative(final int index) {
            final IntByReference rSource = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSmtSource(this.getOwner().getHandle(), index, rSource));
            final ANSmtSource source = ANSmtSource.get(rSource.getValue());
            final IntByReference rTattooClass = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSmtTattooClass(this.getOwner().getHandle(), index, rTattooClass));
            final ANTattooClass tattooClass = ANTattooClass.get(rTattooClass.getValue());
            final IntByReference rTattooSubclass = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSmtTattooSubclass(this.getOwner().getHandle(), index, rTattooSubclass));
            final ANTattooSubclass tattooSubclass = ANTattooSubclass.get(rTattooSubclass.getValue());
            String descrption = null;
            final int pLen = ANType10Record.LIBRARY.ANType10RecordGetSmtDescriptionEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANType10Record.LIBRARY.ANType10RecordGetSmtDescriptionEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                descrption = pValue.getString(0L, true);
                return new ANSmt(source, tattooClass, tattooSubclass, descrption);
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        protected void addNative(final int index, final ANSmt value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordInsertSmt(this.getOwner().getHandle(), index, value.getSource().getValue(), value.getTattooClass().getValue(), value.getTattooSubclass().getValue(), new WString(value.getDescription())));
        }
        
        protected void setNative(final int index, final ANSmt value) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordInsertSmt(this.getOwner().getHandle(), index, value.getSource().getValue(), value.getTattooClass().getValue(), value.getTattooSubclass().getValue(), new WString(value.getDescription())));
        }
        
        protected void clearNative() {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordClearSmts(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSmtCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType10Record.LIBRARY.ANType10RecordRemoveSmt(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANSmt value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
        
        public boolean add(final ANSmtSource source, final ANTattooClass tattooClass, final ANTattooSubclass tattooSubclass, final String description) {
            return this.add(new ANSmt(source, tattooClass, tattooSubclass, description));
        }
        
        public void add(final int index, final ANSmtSource source, final ANTattooClass tattooClass, final ANTattooSubclass tattooSubclass, final String description) {
            this.add(index, new ANSmt(source, tattooClass, tattooSubclass, description));
        }
        
        public ANSmtSource getSource(final int index) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSmtSource(this.getOwner().getHandle(), index, rValue));
            return ANSmtSource.get(rValue.getValue());
        }
        
        public ANTattooClass getTattooClass(final int index) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSmtTattooClass(this.getOwner().getHandle(), index, rValue));
            return ANTattooClass.get(rValue.getValue());
        }
        
        public ANTattooSubclass getTattooSubclass(final int index) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType10Record.LIBRARY.ANType10RecordGetSmtTattooSubclass(this.getOwner().getHandle(), index, rValue));
            return ANTattooSubclass.get(rValue.getValue());
        }
        
        public String getDescription(final int index) {
            final int pLen = ANType10Record.LIBRARY.ANType10RecordGetSmtDescriptionEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANType10Record.LIBRARY.ANType10RecordGetSmtDescriptionEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        public void set(final int index, final ANSmtSource source, final ANTattooClass tattooClass, final ANTattooSubclass tattooSubclass, final String description) {
            this.set(index, new ANSmt(source, tattooClass, tattooSubclass, description));
        }
    }
    
    public final class SmtColorsCollection extends ANRecordArrayCollection<ANColor, ANSmt>
    {
        protected SmtColorsCollection(final ANType10Record owner, final SmtCollection smtCollection) {
            super(owner, smtCollection, false, true, false, false);
        }
        
        protected void addNative(final int baseIndex, final ANColor value) {
            ANType10Record.LIBRARY.ANType10RecordAddSmtColor(this.getOwner().getHandle(), baseIndex, value.getValue());
        }
        
        protected void addNative(final int baseIndex, final int index, final ANColor value) {
            ANType10Record.LIBRARY.ANType10RecordInsertSmtColor(this.getOwner().getHandle(), baseIndex, index, value.getValue());
        }
        
        protected int addWithIndexNative(final int baseIndex, final ANColor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void clearNative(final int baseIndex) {
            ANType10Record.LIBRARY.ANType10RecordClearSmtColors(this.getOwner().getHandle(), baseIndex);
        }
        
        protected ANColor[] getAllNative(final int baseIndex) {
            final int size = this.sizeNative(baseIndex);
            final ANColor[] values = new ANColor[size];
            final int[] data = new int[size];
            ANType10Record.LIBRARY.ANType10RecordGetSmtColorsEx(this.getOwner().getHandle(), baseIndex, data, size);
            for (int i = 0; i < data.length; ++i) {
                values[i] = ANColor.get(data[i]);
            }
            return values;
        }
        
        protected ANColor getNative(final int baseIndex, final int index) {
            final IntByReference rValue = new IntByReference();
            ANType10Record.LIBRARY.ANType10RecordGetSmtColor(this.getOwner().getHandle(), baseIndex, index, rValue);
            return ANColor.get(rValue.getValue());
        }
        
        protected void removeNative(final int baseIndex, final int index) {
            ANType10Record.LIBRARY.ANType10RecordRemoveSmtColor(this.getOwner().getHandle(), baseIndex, index);
        }
        
        protected int removeNative(final int baseIndex, final ANColor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int baseIndex, final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setNative(final int baseIndex, final int index, final ANColor value) {
            ANType10Record.LIBRARY.ANType10RecordSetSmtColor(this.getOwner().getHandle(), baseIndex, index, value.getValue());
        }
        
        protected int sizeNative(final int baseIndex) {
            final IntByReference rValue = new IntByReference();
            ANType10Record.LIBRARY.ANType10RecordGetSmtColorCount(this.getOwner().getHandle(), baseIndex, rValue);
            return rValue.getValue();
        }
    }
    
    interface ANType10RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType10RecordTypeOf();
        
        int ANType10RecordGetPhysicalPhotoCharacteristicCount(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetPhysicalPhotoCharacteristicEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANType10RecordSetPhysicalPhotoCharacteristic(final HNObject p0, final int p1, final WString p2);
        
        int ANType10RecordAddPhysicalPhotoCharacteristic(final HNObject p0, final WString p1);
        
        int ANType10RecordInsertPhysicalPhotoCharacteristic(final HNObject p0, final int p1, final WString p2);
        
        int ANType10RecordRemovePhysicalPhotoCharacteristic(final HNObject p0, final int p1);
        
        int ANType10RecordClearPhysicalPhotoCharacteristics(final HNObject p0);
        
        int ANType10RecordGetOtherPhotoCharacteristicCount(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetOtherPhotoCharacteristicEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANType10RecordSetOtherPhotoCharacteristic(final HNObject p0, final int p1, final WString p2);
        
        int ANType10RecordAddOtherPhotoCharacteristic(final HNObject p0, final WString p1);
        
        int ANType10RecordInsertOtherPhotoCharacteristic(final HNObject p0, final int p1, final WString p2);
        
        int ANType10RecordRemoveOtherPhotoCharacteristic(final HNObject p0, final int p1);
        
        int ANType10RecordClearOtherPhotoCharacteristics(final HNObject p0);
        
        int ANType10RecordGetSubjectQualityScoreCount(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSubjectQualityScore(final HNObject p0, final int p1, final ANQualityMetric.ANQualityMetricData p2);
        
        int ANType10RecordGetSubjectQualityScoresEx(final HNObject p0, final ANQualityMetric.ANQualityMetricData[] p1, final int p2);
        
        int ANType10RecordSetSubjectQualityScore(final HNObject p0, final int p1, final ANQualityMetric.ANQualityMetricData p2);
        
        int ANType10RecordAddSubjectQualityScore(final HNObject p0, final ANQualityMetric.ANQualityMetricData p1);
        
        int ANType10RecordInsertSubjectQualityScore(final HNObject p0, final int p1, final ANQualityMetric.ANQualityMetricData p2);
        
        int ANType10RecordRemoveSubjectQualityScore(final HNObject p0, final int p1);
        
        int ANType10RecordClearSubjectQualityScores(final HNObject p0);
        
        int ANType10RecordGetSubjectFacialCharacteristicCount(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSubjectFacialCharacteristicEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANType10RecordSetSubjectFacialCharacteristic(final HNObject p0, final int p1, final WString p2);
        
        int ANType10RecordAddSubjectFacialCharacteristic(final HNObject p0, final WString p1);
        
        int ANType10RecordInsertSubjectFacialCharacteristic(final HNObject p0, final int p1, final WString p2);
        
        int ANType10RecordRemoveSubjectFacialCharacteristic(final HNObject p0, final int p1);
        
        int ANType10RecordClearSubjectFacialCharacteristics(final HNObject p0);
        
        int ANType10RecordGetFacialFeaturePointCount(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetFacialFeaturePoint(final HNObject p0, final int p1, final BdifFaceFeaturePoint.BdifFaceFeaturePointData p2);
        
        int ANType10RecordGetFacialFeaturePointsEx(final HNObject p0, final BdifFaceFeaturePoint.BdifFaceFeaturePointData[] p1, final int p2);
        
        int ANType10RecordSetFacialFeaturePoint(final HNObject p0, final int p1, final BdifFaceFeaturePoint.BdifFaceFeaturePointData p2);
        
        int ANType10RecordAddFacialFeaturePoint(final HNObject p0, final BdifFaceFeaturePoint.BdifFaceFeaturePointData p1);
        
        int ANType10RecordInsertFacialFeaturePoint(final HNObject p0, final int p1, final BdifFaceFeaturePoint.BdifFaceFeaturePointData p2);
        
        int ANType10RecordRemoveFacialFeaturePoint(final HNObject p0, final int p1);
        
        int ANType10RecordClearFacialFeaturePoints(final HNObject p0);
        
        int ANType10RecordGetNcicDesignationCodeCount(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetNcicDesignationCodeEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANType10RecordSetNcicDesignationCode(final HNObject p0, final int p1, final WString p2);
        
        int ANType10RecordAddNcicDesignationCode(final HNObject p0, final WString p1);
        
        int ANType10RecordInsertNcicDesignationCode(final HNObject p0, final int p1, final WString p2);
        
        int ANType10RecordRemoveNcicDesignationCode(final HNObject p0, final int p1);
        
        int ANType10RecordGetSmtCount(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSmtSource(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANType10RecordGetSmtTattooClass(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANType10RecordGetSmtTattooSubclass(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANType10RecordGetSmtDescriptionEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANType10RecordSetSmt(final HNObject p0, final int p1, final int p2, final int p3, final int p4, final WString p5);
        
        int ANType10RecordAddSmt(final HNObject p0, final int p1, final int p2, final int p3, final WString p4);
        
        int ANType10RecordInsertSmt(final HNObject p0, final int p1, final int p2, final int p3, final int p4, final WString p5);
        
        int ANType10RecordRemoveSmt(final HNObject p0, final int p1);
        
        int ANType10RecordClearSmts(final HNObject p0);
        
        int ANType10RecordGetSmtColorCount(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANType10RecordGetSmtColor(final HNObject p0, final int p1, final int p2, final IntByReference p3);
        
        int ANType10RecordGetSmtColorsEx(final HNObject p0, final int p1, final int[] p2, final int p3);
        
        int ANType10RecordSetSmtColor(final HNObject p0, final int p1, final int p2, final int p3);
        
        int ANType10RecordAddSmtColor(final HNObject p0, final int p1, final int p2);
        
        int ANType10RecordInsertSmtColor(final HNObject p0, final int p1, final int p2, final int p3);
        
        int ANType10RecordRemoveSmtColor(final HNObject p0, final int p1, final int p2);
        
        int ANType10RecordClearSmtColors(final HNObject p0, final int p1);
        
        int ANType10RecordGetImageType(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSubjectAcquisitionProfile(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordSetSubjectAcquisitionProfile(final HNObject p0, final short p1);
        
        int ANType10RecordGetSubjectPose(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordSetSubjectPose(final HNObject p0, final int p1);
        
        int ANType10RecordGetPoseOffsetAngle(final HNObject p0, final IntByReference p1, final BooleanByReference p2);
        
        int ANType10RecordSetPoseOffsetAngle(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetPhotoAttributes(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordSetPhotoAttributes(final HNObject p0, final int p1);
        
        int ANType10RecordGetPhotoAcquisitionSource(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordSetPhotoAcquisitionSource(final HNObject p0, final int p1, final WString p2);
        
        int ANType10RecordGetVendorPhotoAcquisitionSourceEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType10RecordGetSubjectPoseAnglesYaw(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSubjectPoseAnglesPitch(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSubjectPoseAnglesRoll(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSubjectPoseAnglesYawUncertainty(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSubjectPoseAnglesPitchUncertainty(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSubjectPoseAnglesRollUncertainty(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSubjectPoseAngles(final HNObject p0, final IntByReference p1, final IntByReference p2, final IntByReference p3, final IntByReference p4, final IntByReference p5, final IntByReference p6);
        
        int ANType10RecordSetSubjectPoseAngles(final HNObject p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
        
        int ANType10RecordGetSubjectFacialExpression(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordSetSubjectFacialExpression(final HNObject p0, final int p1);
        
        int ANType10RecordGetSubjectFacialAttributes(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordSetSubjectFacialAttributes(final HNObject p0, final int p1);
        
        int ANType10RecordGetSubjectEyeColor(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordSetSubjectEyeColor(final HNObject p0, final int p1);
        
        int ANType10RecordGetSubjectHairColor(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordSetSubjectHairColor(final HNObject p0, final int p1, final int p2);
        
        int ANType10RecordGetBaldSubjectHairColor(final HNObject p0, final IntByReference p1);
        
        int ANType10RecordGetSmtSize(final HNObject p0, final NDimensionData p1, final BooleanByReference p2);
        
        int ANType10RecordSetSmtSize(final HNObject p0, final NDimensionData p1);
    }
}
