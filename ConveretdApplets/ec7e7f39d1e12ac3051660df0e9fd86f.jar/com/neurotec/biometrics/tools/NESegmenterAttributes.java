// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.ByteByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public class NESegmenterAttributes extends NObject
{
    static final NFSegmentLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NESegmenterAttributes(final HNObject handle, final boolean claimHandle) {
        super(handle, NESegmenterAttributes.NATIVE_TYPE, claimHandle);
    }
    
    public static NESegmenterAttributes fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NESegmenterAttributes fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NESegmenterAttributes value = new NESegmenterAttributes(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public final byte getGrayLevelSpread() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetGrayLevelSpread(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getIrisSize() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetIrisSize(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getPupilIrisRatio() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetPupilIrisRatio(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getUsableIrisArea() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetUsableIrisArea(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getIrisScleraContrast() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetIrisScleraContrast(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getIrisPupilContrast() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetIrisPupilContrast(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getIrisPupilBoundaryShape() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetIrisPupilBoundaryShape(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getIrisScleraBoundaryShape() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetIrisScleraBoundaryShape(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getMargin() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetMargin(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getSharpness() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetSharpness(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getSignalToNoiseRatio() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetSignalToNoiseRatio(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final byte getInterlace() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NESegmenterAttributes.LIBRARY.NESegmenterAttributesGetInterlace(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NFSegmentLibrary)Native.loadLibrary("NBiometrics", NFSegmentLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NESegmenterAttributes.LIBRARY.NESegmenterAttributesTypeOf());
    }
    
    interface NFSegmentLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NESegmenterAttributesTypeOf();
        
        int NESegmenterAttributesGetGrayLevelSpread(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetIrisSize(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetPupilIrisRatio(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetUsableIrisArea(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetIrisScleraContrast(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetIrisPupilContrast(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetIrisScleraBoundaryShape(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetIrisPupilBoundaryShape(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetMargin(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetSharpness(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetSignalToNoiseRatio(final HNObject p0, final ByteByReference p1);
        
        int NESegmenterAttributesGetInterlace(final HNObject p0, final ByteByReference p1);
    }
}
