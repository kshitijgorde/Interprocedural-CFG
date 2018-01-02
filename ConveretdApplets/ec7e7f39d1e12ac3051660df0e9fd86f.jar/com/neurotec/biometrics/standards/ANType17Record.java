// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.util.UUIDData;
import java.util.UUID;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.BooleanByReference;
import java.util.Date;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NResult;
import com.sun.jna.WString;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType17Record extends ANImageAsciiBinaryRecord
{
    static final ANType17RecordLibrary LIBRARY;
    public static final int FIELD_FID = 3;
    public static final int FIELD_ICD = 5;
    public static final int FIELD_RAE = 14;
    public static final int FIELD_RAU = 15;
    public static final int FIELD_IPC = 16;
    public static final int FIELD_DUI = 17;
    public static final int FIELD_GUI = 18;
    public static final int FIELD_MMS = 19;
    public static final int FIELD_ECL = 20;
    public static final int FIELD_COM = 21;
    public static final int FIELD_SHPS = 22;
    public static final int FIELD_SVPS = 23;
    public static final int FIELD_IQS = 24;
    public static final int FIELD_ALS = 25;
    public static final int FIELD_IRD = 26;
    public static final int MAX_IRIS_DIAMETER = 9999;
    public static final int MAX_MAKE_LENGTH = 50;
    public static final int MAX_MODEL_LENGTH = 50;
    public static final int MAX_SERIAL_NUMBER_LENGTH = 50;
    public static final NNativeType NATIVE_TYPE;
    
    ANType17Record(final HNObject handle) {
        super(handle, ANType17Record.NATIVE_TYPE);
    }
    
    public void setMakeModelSerialNumber(final String make, final String model, final String serialNumber) {
        NResult.check(ANType17Record.LIBRARY.ANType17RecordSetMakeModelSerialNumber(this.getHandle(), new WString(make), new WString(model), new WString(serialNumber)));
        this.refresh();
    }
    
    public BdifEyePosition getFeatureIdentifier() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType17Record.LIBRARY.ANType17RecordGetFeatureIdentifier(this.getHandle(), rValue));
        return BdifEyePosition.get(rValue.getValue());
    }
    
    public void setFeatureIdentifier(final BdifEyePosition value) {
        NResult.check(ANType17Record.LIBRARY.ANType17RecordSetFeatureIdentifier(this.getHandle(), value.getValue()));
    }
    
    public Date getIrisCaptureDate() {
        return this.getDate();
    }
    
    public void setIrisCaptureDate(final Date value) {
        this.setDate(value);
    }
    
    public int getRotationAngle() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType17Record.LIBRARY.ANType17RecordGetRotationAngle(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setRotationAngle(final int value) {
        NResult.check(ANType17Record.LIBRARY.ANType17RecordSetRotationAngle(this.getHandle(), value));
    }
    
    public int getRotationAngleUncertainty() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType17Record.LIBRARY.ANType17RecordGetRotationAngleUncertainty(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setRotationAngleUncertainty(final int value) {
        NResult.check(ANType17Record.LIBRARY.ANType17RecordSetRotationAngleUncertainty(this.getHandle(), value));
    }
    
    public ANIrisImageProperties getImageProperties() {
        final BooleanByReference rValue = new BooleanByReference();
        final ANIrisImageProperties value = new ANIrisImageProperties();
        NResult.check(ANType17Record.LIBRARY.ANType17RecordGetImageProperties(this.getHandle(), value.getData(), rValue));
        return rValue.getValue() ? value : null;
    }
    
    public void setImageProperties(final ANIrisImageProperties value) {
        if (value == null || value.getData() == null) {
            NResult.check(ANType17Record.LIBRARY.ANType17RecordSetImageProperties(this.getHandle(), null));
        }
        else {
            NResult.check(ANType17Record.LIBRARY.ANType17RecordSetImageProperties(this.getHandle(), value.getData()));
        }
        this.refresh();
    }
    
    public String getDeviceUniqueIdentifier() {
        final int pLen = ANType17Record.LIBRARY.ANType17RecordGetDeviceUniqueIdentifierEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType17Record.LIBRARY.ANType17RecordGetDeviceUniqueIdentifierEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setDeviceUniqueIdentifier(final String value) {
        NResult.check(ANType17Record.LIBRARY.ANType17RecordSetDeviceUniqueIdentifier(this.getHandle(), new WString(value)));
    }
    
    public UUID getUuid() {
        final UUIDData rValue = new UUIDData();
        final BooleanByReference rHasValue = new BooleanByReference();
        NResult.check(ANType17Record.LIBRARY.ANType17RecordGetGuid(this.getHandle(), rValue, rHasValue));
        return rHasValue.getValue() ? new UUID(rValue.leastSigBits, rValue.mostSigBits) : null;
    }
    
    public void setUuid(final UUID value) {
        if (value != null) {
            final UUIDData rValue = new UUIDData(value.getLeastSignificantBits(), value.getMostSignificantBits());
            NResult.check(ANType17Record.LIBRARY.ANType17RecordSetGuid(this.getHandle(), rValue));
        }
        else {
            NResult.check(ANType17Record.LIBRARY.ANType17RecordSetGuid(this.getHandle(), null));
        }
    }
    
    public String getMake() {
        final int pLen = ANType17Record.LIBRARY.ANType17RecordGetMakeEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType17Record.LIBRARY.ANType17RecordGetMakeEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getModel() {
        final int pLen = ANType17Record.LIBRARY.ANType17RecordGetModelEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType17Record.LIBRARY.ANType17RecordGetModelEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getSerialNumber() {
        final int pLen = ANType17Record.LIBRARY.ANType17RecordGetSerialNumberEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType17Record.LIBRARY.ANType17RecordGetSerialNumberEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public BdifEyeColor getEyeColor() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType17Record.LIBRARY.ANType17RecordGetEyeColor(this.getHandle(), rValue));
        return BdifEyeColor.get(rValue.getValue());
    }
    
    public void setEyeColor(final BdifEyeColor value) {
        NResult.check(ANType17Record.LIBRARY.ANType17RecordSetEyeColor(this.getHandle(), value.getValue()));
    }
    
    public ANQualityMetric getImageQualityScore() {
        final BooleanByReference rValue = new BooleanByReference();
        final ANQualityMetric value = new ANQualityMetric();
        NResult.check(ANType17Record.LIBRARY.ANType17RecordGetImageQualityScore(this.getHandle(), value.getData(), rValue));
        return rValue.getValue() ? value : null;
    }
    
    public void setImageQualityScore(final ANQualityMetric value) {
        if (value == null || value.getData() == null) {
            NResult.check(ANType17Record.LIBRARY.ANType17RecordSetImageQualityScore(this.getHandle(), null));
        }
        else {
            NResult.check(ANType17Record.LIBRARY.ANType17RecordSetImageQualityScore(this.getHandle(), value.getData()));
        }
        this.refresh();
    }
    
    public ANIrisAcquisitionLightingSpectrum getAcquisitionLightingSpectrum() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType17Record.LIBRARY.ANType17RecordGetAcquisitionLightingSpectrum(this.getHandle(), rValue));
        return ANIrisAcquisitionLightingSpectrum.get(rValue.getValue());
    }
    
    public void setAcquisitionLightingSpectrum(final ANIrisAcquisitionLightingSpectrum value) {
        NResult.check(ANType17Record.LIBRARY.ANType17RecordSetAcquisitionLightingSpectrum(this.getHandle(), value.getValue()));
    }
    
    public int getIrisDiameter() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType17Record.LIBRARY.ANType17RecordGetIrisDiameter(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setIrisDiameter(final int value) {
        NResult.check(ANType17Record.LIBRARY.ANType17RecordSetIrisDiameter(this.getHandle(), value));
    }
    
    static {
        LIBRARY = (ANType17RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType17RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType17Record.LIBRARY.ANType17RecordTypeOf());
    }
    
    interface ANType17RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType17RecordTypeOf();
        
        int ANType17RecordGetFeatureIdentifier(final HNObject p0, final IntByReference p1);
        
        int ANType17RecordSetFeatureIdentifier(final HNObject p0, final int p1);
        
        int ANType17RecordGetRotationAngle(final HNObject p0, final IntByReference p1);
        
        int ANType17RecordSetRotationAngle(final HNObject p0, final int p1);
        
        int ANType17RecordGetRotationAngleUncertainty(final HNObject p0, final IntByReference p1);
        
        int ANType17RecordSetRotationAngleUncertainty(final HNObject p0, final int p1);
        
        int ANType17RecordGetImageProperties(final HNObject p0, final ANIrisImageProperties.ANIrisImagePropertiesData p1, final BooleanByReference p2);
        
        int ANType17RecordSetImageProperties(final HNObject p0, final ANIrisImageProperties.ANIrisImagePropertiesData p1);
        
        int ANType17RecordGetDeviceUniqueIdentifierEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType17RecordSetDeviceUniqueIdentifier(final HNObject p0, final WString p1);
        
        int ANType17RecordGetGuid(final HNObject p0, final UUIDData p1, final BooleanByReference p2);
        
        int ANType17RecordSetGuid(final HNObject p0, final UUIDData p1);
        
        int ANType17RecordGetMakeEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType17RecordGetModelEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType17RecordGetSerialNumberEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType17RecordSetMakeModelSerialNumber(final HNObject p0, final WString p1, final WString p2, final WString p3);
        
        int ANType17RecordGetEyeColor(final HNObject p0, final IntByReference p1);
        
        int ANType17RecordSetEyeColor(final HNObject p0, final int p1);
        
        int ANType17RecordGetImageQualityScore(final HNObject p0, final ANQualityMetric.ANQualityMetricData p1, final BooleanByReference p2);
        
        int ANType17RecordSetImageQualityScore(final HNObject p0, final ANQualityMetric.ANQualityMetricData p1);
        
        int ANType17RecordGetAcquisitionLightingSpectrum(final HNObject p0, final IntByReference p1);
        
        int ANType17RecordSetAcquisitionLightingSpectrum(final HNObject p0, final int p1);
        
        int ANType17RecordGetIrisDiameter(final HNObject p0, final IntByReference p1);
        
        int ANType17RecordSetIrisDiameter(final HNObject p0, final int p1);
    }
}
