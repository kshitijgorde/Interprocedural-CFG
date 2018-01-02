// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.images.NGrayscaleImage;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NEExtractor extends NObject
{
    static final NEExtractorLibrary LIBRARY;
    public static final int PARAMETER_DEINTERLACE = 10001;
    public static final int PARAMETER_INNER_BOUNDARY_FROM = 10002;
    public static final int PARAMETER_INNER_BOUNDARY_TO = 10003;
    public static final int PARAMETER_OUTER_BOUNDARY_FROM = 10004;
    public static final int PARAMETER_OUTER_BOUNDARY_TO = 10005;
    public static final NNativeType NATIVE_TYPE;
    
    private NEExtractor(final HNObject handle, final boolean claimHandle) {
        super(handle, NEExtractor.NATIVE_TYPE, claimHandle);
    }
    
    public NEExtractor() {
        this(create(), true);
    }
    
    public static NEExtractor fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NEExtractor fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NEExtractor value = new NEExtractor(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private static HNObject create() {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NEExtractor.LIBRARY.NeeCreate(rhValue));
        return rhValue.getValue();
    }
    
    public NEExtractorResult extract(final NGrayscaleImage image, final NEPosition position, final boolean isSegmentationDetailsAvailable) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        NEExtractorResult result = null;
        NERecord value = null;
        HNObject hValue = null;
        NeeSegmentationDetails details = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final IntByReference rStatus = new IntByReference();
        if (isSegmentationDetailsAvailable) {
            details = new NeeSegmentationDetails();
        }
        NResult.check(NEExtractor.LIBRARY.NeeExtract(this.getHandle(), image.getHandle(), position.getValue(), (details == null) ? null : details.getData(), rStatus, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NERecord.fromHandle(hValue);
            result = new NEExtractorResult(value, details, NeeExtractionStatus.get(rStatus.getValue()));
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return result;
    }
    
    public boolean isDeinterlace() {
        return (boolean)this.getParameter(10001, Boolean.class);
    }
    
    public void setDeinterlace(final boolean value) {
        this.setParameter(10001, Boolean.class, value);
    }
    
    public int getInnerBoundaryFrom() {
        return (int)this.getParameter(10002, Integer.class);
    }
    
    public void setInnerBoundaryFrom(final int value) {
        this.setParameter(10002, Integer.class, value);
    }
    
    public int getInnerBoundaryTo() {
        return (int)this.getParameter(10003, Integer.class);
    }
    
    public void setInnerBoundaryTo(final int value) {
        this.setParameter(10003, Integer.class, value);
    }
    
    public int getOuterBoundaryFrom() {
        return (int)this.getParameter(10004, Integer.class);
    }
    
    public void setOuterBoundaryFrom(final int value) {
        this.setParameter(10004, Integer.class, value);
    }
    
    public int getOuterBoundaryTo() {
        return (int)this.getParameter(10005, Integer.class);
    }
    
    public void setOuterBoundaryTo(final int value) {
        this.setParameter(10005, Integer.class, value);
    }
    
    static {
        LIBRARY = (NEExtractorLibrary)Native.loadLibrary("NBiometrics", NEExtractorLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NEExtractor.LIBRARY.NEExtractorTypeOf());
    }
    
    public final class NEExtractorResult
    {
        private NERecord record;
        private NeeSegmentationDetails segmentationDetails;
        private NeeExtractionStatus status;
        
        private NEExtractorResult(final NERecord record, final NeeSegmentationDetails segmentationDetails, final NeeExtractionStatus status) {
            this.record = record;
            this.segmentationDetails = segmentationDetails;
            this.status = status;
        }
        
        public NERecord getRecord() {
            return this.record;
        }
        
        public NeeSegmentationDetails getSegmentationDetails() {
            return this.segmentationDetails;
        }
        
        public NeeExtractionStatus getStatus() {
            return this.status;
        }
    }
    
    interface NEExtractorLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NEExtractorTypeOf();
        
        int NeeCreate(final HNObjectByReference p0);
        
        int NeeExtract(final HNObject p0, final HNObject p1, final int p2, final NeeSegmentationDetails.NeeSegmentationDetailsData p3, final IntByReference p4, final HNObjectByReference p5);
    }
}
