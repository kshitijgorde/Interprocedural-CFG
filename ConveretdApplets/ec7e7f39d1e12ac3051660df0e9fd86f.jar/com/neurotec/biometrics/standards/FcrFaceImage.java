// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.ShortByReference;
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

public final class FcrFaceImage extends NObject
{
    private FeaturePointCollection featurePoints;
    static final FcrFaceImageLibrary LIBRARY;
    public static final int MAX_FEATURE_POINT_COUNT = 32767;
    public static final int FLAG_SKIP_FEATURE_POINTS = 65536;
    public static final NNativeType NATIVE_TYPE;
    
    FcrFaceImage(final HNObject handle) {
        super(handle, FcrFaceImage.NATIVE_TYPE, false);
        this.featurePoints = new FeaturePointCollection(this);
    }
    
    private static double poseAngleToDouble(final byte value) {
        return (value == 0 || value > 181) ? Double.NaN : (((value <= 91) ? (value - 1) : (181 - value)) * 3.141592653589793 / 90.0);
    }
    
    private static byte poseAngleFromDouble(double value) {
        if (Double.isNaN(value)) {
            return 0;
        }
        value -= Math.floor(value / 6.283185307179586) * 6.283185307179586;
        value = Math.round(value * 90.0 / 3.141592653589793);
        return (byte)((int)value % 180 + 1);
    }
    
    private static double poseAngleUncertaintyToDouble(final byte value) {
        return (value == 0 || value > 181) ? Double.NaN : ((value - 1) * 3.141592653589793 / 180.0);
    }
    
    private static byte poseAngleUncertaintyFromDouble(final double value) {
        return (byte)((value < 0.0 || value > 3.141592653589793 || Double.isNaN(value)) ? 0 : ((int)Math.round(value * 180.0 / 3.141592653589793) + 1));
    }
    
    public static int calculateSize(final BdifStandard standard, final int featurePointCount, final int imageDataLength) {
        if (imageDataLength < 0) {
            throw new IllegalArgumentException("imageDataLength is less than zero");
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageCalculateSize(standard.getValue(), featurePointCount, new NativeSize((long)imageDataLength), rValue));
        return rValue.getValue().intValue();
    }
    
    public NImage toNImage() {
        return this.toNImage(0);
    }
    
    public NImage toNImage(final int flags) {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageToNImage(this.getHandle(), flags, rhValue));
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
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetImageDataLength(this.getHandle(), new NativeSize((long)value)));
    }
    
    public ByteBuffer getImageData() {
        final PointerByReference rImageData = new PointerByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetImageDataPtr(this.getHandle(), rImageData));
        final NativeSizeByReference imageDataLength = new NativeSizeByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetImageDataLength(this.getHandle(), imageDataLength));
        return NCore.getByteBuffer(rImageData.getValue(), imageDataLength.getValue().longValue(), false);
    }
    
    public void setImageData(final ByteBuffer value) {
        if (value == null) {
            throw new NullPointerException("value");
        }
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetImageData(this.getHandle(), value.slice(), new NativeSize((long)value.remaining())));
    }
    
    public void setExpression(final BdifFaceExpression value, final short vendorValue) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetExpression(this.getHandle(), value.getValue(), vendorValue));
    }
    
    public void setPoseAngle(final double yaw, final double pitch, final double roll) {
        this.setPoseAngleRaw(poseAngleFromDouble(yaw), poseAngleFromDouble(pitch), poseAngleFromDouble(roll));
    }
    
    public void setPoseAngleRaw(final byte yaw, final byte pitch, final byte roll) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetPoseAngle(this.getHandle(), yaw, pitch, roll));
    }
    
    public void setPoseAngleUncertainty(final double yaw, final double pitch, final double roll) {
        this.setPoseAngleUncertaintyRaw(poseAngleUncertaintyFromDouble(yaw), poseAngleUncertaintyFromDouble(pitch), poseAngleUncertaintyFromDouble(roll));
    }
    
    public void setPoseAngleUncertaintyRaw(final byte yaw, final byte pitch, final byte roll) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetPoseAngleUncertainty(this.getHandle(), yaw, pitch, roll));
    }
    
    public void setSourceType(final BdifImageSourceType value, final byte vendorValue) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetSourceType(this.getHandle(), value.getValue(), vendorValue));
    }
    
    public FCRecord getOwner() {
        return (FCRecord)super.getOwner();
    }
    
    public BdifGender getGender() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetGender(this.getHandle(), rValue));
        return BdifGender.get(rValue.getValue());
    }
    
    public void setGender(final BdifGender value) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetGender(this.getHandle(), value.getValue()));
    }
    
    public BdifEyeColor getEyeColor() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetEyeColor(this.getHandle(), rValue));
        return BdifEyeColor.get(rValue.getValue());
    }
    
    public void setEyeColor(final BdifEyeColor value) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetEyeColor(this.getHandle(), value.getValue()));
    }
    
    public BdifFaceProperties getProperties() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetProperties(this.getHandle(), rValue));
        return BdifFaceProperties.get(rValue.getValue());
    }
    
    public void setProperties(final BdifFaceProperties value) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetProperties(this.getHandle(), value.getValue()));
    }
    
    public BdifFaceExpression getExpression() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetExpression(this.getHandle(), rValue));
        return BdifFaceExpression.get(rValue.getValue());
    }
    
    public void setExpression(final BdifFaceExpression value) {
        this.setExpression(value, (short)0);
    }
    
    public short getVendorExpression() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetVendorExpression(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public double getPoseAngleYaw() {
        return poseAngleToDouble(this.getPoseAngleYawRaw());
    }
    
    public void setPoseAngleYaw(final double value) {
        this.setPoseAngleYawRaw(poseAngleFromDouble(value));
    }
    
    public byte getPoseAngleYawRaw() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetPoseAngleYaw(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setPoseAngleYawRaw(final byte value) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetPoseAngleYaw(this.getHandle(), value));
    }
    
    public double getPoseAnglePitch() {
        return poseAngleToDouble(this.getPoseAnglePitchRaw());
    }
    
    public void setPoseAnglePitch(final double value) {
        this.setPoseAnglePitchRaw(poseAngleFromDouble(value));
    }
    
    public byte getPoseAnglePitchRaw() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetPoseAnglePitch(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setPoseAnglePitchRaw(final byte value) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetPoseAnglePitch(this.getHandle(), value));
    }
    
    public double getPoseAngleRoll() {
        return poseAngleToDouble(this.getPoseAngleRollRaw());
    }
    
    public void setPoseAngleRoll(final double value) {
        this.setPoseAngleRollRaw(poseAngleFromDouble(value));
    }
    
    public byte getPoseAngleRollRaw() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetPoseAngleRoll(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setPoseAngleRollRaw(final byte value) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetPoseAngleRoll(this.getHandle(), value));
    }
    
    public double poseAngleUncertaintyYaw() {
        return poseAngleUncertaintyToDouble(this.poseAngleUncertaintyYawRaw());
    }
    
    public byte poseAngleUncertaintyYawRaw() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetPoseAngleUncertaintyYaw(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public double getPoseAngleUncertaintyPitch() {
        return poseAngleUncertaintyToDouble(this.getPoseAngleUncertaintyPitchRaw());
    }
    
    public byte getPoseAngleUncertaintyPitchRaw() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetPoseAngleUncertaintyPitch(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public double getPoseAngleUncertaintyRoll() {
        return poseAngleUncertaintyToDouble(this.getPoseAngleUncertaintyRollRaw());
    }
    
    public byte getPoseAngleUncertaintyRollRaw() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetPoseAngleUncertaintyRoll(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public FeaturePointCollection getFeaturePoints() {
        this.check();
        return this.featurePoints;
    }
    
    public FcrFaceImageType getFaceImageType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetFaceImageType(this.getHandle(), rValue));
        return FcrFaceImageType.get(rValue.getValue());
    }
    
    public FcrImageDataType getImageDataType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetImageDataType(this.getHandle(), rValue));
        return FcrImageDataType.get(rValue.getValue());
    }
    
    public short getWidth() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetWidth(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getHeight() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetHeight(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public FcrImageColorSpace getImageColorSpace() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetImageDataType(this.getHandle(), rValue));
        return FcrImageColorSpace.get(rValue.getValue());
    }
    
    public byte getVendorImageColorSpace() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetVendorImageColorSpace(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public BdifImageSourceType getSourceType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetSourceType(this.getHandle(), rValue));
        return BdifImageSourceType.get(rValue.getValue());
    }
    
    public void setSourceType(final BdifImageSourceType value) {
        this.setSourceType(value, (byte)0);
    }
    
    public byte getVendorSourceType() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetVendorSourceType(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getDeviceType() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetDeviceType(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setDeviceType(final short value) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetDeviceType(this.getHandle(), value));
    }
    
    public short getQuality() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetQuality(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setQuality(final short value) {
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetQuality(this.getHandle(), value));
    }
    
    public int getImageDataLength() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetImageDataLength(this.getHandle(), rValue));
        return rValue.getValue().intValue();
    }
    
    protected void dispose(final boolean disposing) {
        this.featurePoints = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (FcrFaceImageLibrary)Native.loadLibrary("NBiometricStandards", FcrFaceImageLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(FcrFaceImage.LIBRARY.FcrFaceImageTypeOf());
    }
    
    public final class FeaturePointCollection extends NSimpleCollection<BdifFaceFeaturePoint>
    {
        FeaturePointCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        protected void addNative(final BdifFaceFeaturePoint value) {
            NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageAddFeaturePoint(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final BdifFaceFeaturePoint value) {
            throw new UnsupportedOperationException();
        }
        
        protected BdifFaceFeaturePoint[] getAllNative() {
            final int size = this.sizeNative();
            final BdifFaceFeaturePoint.BdifFaceFeaturePointData[] structures = (BdifFaceFeaturePoint.BdifFaceFeaturePointData[])new BdifFaceFeaturePoint.BdifFaceFeaturePointData().toArray(size);
            final BdifFaceFeaturePoint[] values = new BdifFaceFeaturePoint[size];
            FcrFaceImage.LIBRARY.FcrFaceImageGetFeaturePointsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new BdifFaceFeaturePoint(structures[i]);
            }
            return values;
        }
        
        protected BdifFaceFeaturePoint getNative(final int index) {
            final BdifFaceFeaturePoint value = new BdifFaceFeaturePoint();
            NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetFeaturePoint(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final BdifFaceFeaturePoint value) {
            NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageInsertFeaturePoint(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final BdifFaceFeaturePoint value) {
            NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetFeaturePoint(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageClearFeaturePoints(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetFeaturePointCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageGetFeaturePointCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageRemoveFeaturePoint(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final BdifFaceFeaturePoint value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(FcrFaceImage.LIBRARY.FcrFaceImageSetFeaturePointCapacity(this.getOwner().getHandle(), value));
        }
    }
    
    interface FcrFaceImageLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType FcrFaceImageTypeOf();
        
        int FcrFaceImageCalculateSize(final int p0, final int p1, final NativeSize p2, final NativeSizeByReference p3);
        
        int FcrFaceImageGetFeaturePointCount(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageGetFeaturePoint(final HNObject p0, final int p1, final BdifFaceFeaturePoint.BdifFaceFeaturePointData p2);
        
        int FcrFaceImageSetFeaturePoint(final HNObject p0, final int p1, final BdifFaceFeaturePoint.BdifFaceFeaturePointData p2);
        
        int FcrFaceImageGetFeaturePointsEx(final HNObject p0, final BdifFaceFeaturePoint.BdifFaceFeaturePointData[] p1, final int p2);
        
        int FcrFaceImageGetFeaturePointCapacity(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageSetFeaturePointCapacity(final HNObject p0, final int p1);
        
        int FcrFaceImageAddFeaturePoint(final HNObject p0, final BdifFaceFeaturePoint.BdifFaceFeaturePointData p1);
        
        int FcrFaceImageInsertFeaturePoint(final HNObject p0, final int p1, final BdifFaceFeaturePoint.BdifFaceFeaturePointData p2);
        
        int FcrFaceImageRemoveFeaturePoint(final HNObject p0, final int p1);
        
        int FcrFaceImageClearFeaturePoints(final HNObject p0);
        
        int FcrFaceImageToNImage(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FcrFaceImageGetGender(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageSetGender(final HNObject p0, final int p1);
        
        int FcrFaceImageGetEyeColor(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageSetEyeColor(final HNObject p0, final int p1);
        
        int FcrFaceImageGetHairColor(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageSetHairColor(final HNObject p0, final int p1);
        
        int FcrFaceImageGetProperties(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageSetProperties(final HNObject p0, final int p1);
        
        int FcrFaceImageGetExpression(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageSetExpression(final HNObject p0, final int p1, final short p2);
        
        int FcrFaceImageGetVendorExpression(final HNObject p0, final ShortByReference p1);
        
        int FcrFaceImageGetPoseAngleYaw(final HNObject p0, final ByteByReference p1);
        
        int FcrFaceImageSetPoseAngleYaw(final HNObject p0, final byte p1);
        
        int FcrFaceImageGetPoseAnglePitch(final HNObject p0, final ByteByReference p1);
        
        int FcrFaceImageSetPoseAnglePitch(final HNObject p0, final byte p1);
        
        int FcrFaceImageGetPoseAngleRoll(final HNObject p0, final ByteByReference p1);
        
        int FcrFaceImageSetPoseAngleRoll(final HNObject p0, final byte p1);
        
        int FcrFaceImageGetPoseAngleUncertaintyYaw(final HNObject p0, final ByteByReference p1);
        
        int FcrFaceImageGetPoseAngleUncertaintyPitch(final HNObject p0, final ByteByReference p1);
        
        int FcrFaceImageGetPoseAngleUncertaintyRoll(final HNObject p0, final ByteByReference p1);
        
        int FcrFaceImageSetPoseAngle(final HNObject p0, final byte p1, final byte p2, final byte p3);
        
        int FcrFaceImageSetPoseAngleUncertainty(final HNObject p0, final byte p1, final byte p2, final byte p3);
        
        int FcrFaceImageGetFaceImageType(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageGetImageDataType(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageGetWidth(final HNObject p0, final ShortByReference p1);
        
        int FcrFaceImageGetHeight(final HNObject p0, final ShortByReference p1);
        
        int FcrFaceImageGetImageColorSpace(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageGetVendorImageColorSpace(final HNObject p0, final ByteByReference p1);
        
        int FcrFaceImageGetSourceType(final HNObject p0, final IntByReference p1);
        
        int FcrFaceImageGetVendorSourceType(final HNObject p0, final ByteByReference p1);
        
        int FcrFaceImageSetSourceType(final HNObject p0, final int p1, final byte p2);
        
        int FcrFaceImageGetDeviceType(final HNObject p0, final ShortByReference p1);
        
        int FcrFaceImageSetDeviceType(final HNObject p0, final short p1);
        
        int FcrFaceImageGetQuality(final HNObject p0, final ShortByReference p1);
        
        int FcrFaceImageSetQuality(final HNObject p0, final short p1);
        
        int FcrFaceImageGetImageDataLength(final HNObject p0, final NativeSizeByReference p1);
        
        int FcrFaceImageSetImageDataLength(final HNObject p0, final NativeSize p1);
        
        int FcrFaceImageGetImageDataPtr(final HNObject p0, final PointerByReference p1);
        
        int FcrFaceImageSetImageData(final HNObject p0, final ByteBuffer p1, final NativeSize p2);
    }
}
