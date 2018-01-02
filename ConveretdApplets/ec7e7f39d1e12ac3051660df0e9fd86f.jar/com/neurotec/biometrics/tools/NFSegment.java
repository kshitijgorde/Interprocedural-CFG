// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.biometrics.NBiometricStatus;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.biometrics.NFPosition;
import com.neurotec.lang.NResult;
import com.neurotec.awt.NPointData;
import java.awt.Point;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public class NFSegment extends NObject
{
    static final NFSegmentLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NFSegment(final HNObject handle, final boolean claimHandle) {
        super(handle, NFSegment.NATIVE_TYPE, claimHandle);
    }
    
    public static NFSegment fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NFSegment fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NFSegment value = new NFSegment(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public final Point getTopLeft() {
        final NPointData rValue = new NPointData();
        NResult.check(NFSegment.LIBRARY.NFSegmentGetTopLeft(this.getHandle(), rValue));
        return new Point(rValue.x, rValue.y);
    }
    
    public final Point getTopRight() {
        final NPointData rValue = new NPointData();
        NResult.check(NFSegment.LIBRARY.NFSegmentGetTopRight(this.getHandle(), rValue));
        return new Point(rValue.x, rValue.y);
    }
    
    public final Point getBottomRight() {
        final NPointData rValue = new NPointData();
        NResult.check(NFSegment.LIBRARY.NFSegmentGetBottomRight(this.getHandle(), rValue));
        return new Point(rValue.x, rValue.y);
    }
    
    public final Point getBottomLeft() {
        final NPointData rValue = new NPointData();
        NResult.check(NFSegment.LIBRARY.NFSegmentGetBottomLeft(this.getHandle(), rValue));
        return new Point(rValue.x, rValue.y);
    }
    
    public final NFPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFSegment.LIBRARY.NFSegmentGetPosition(this.getHandle(), rValue));
        return NFPosition.get(rValue.getValue());
    }
    
    public NBiometricStatus getStatus() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFSegment.LIBRARY.NFSegmentGetStatus(this.getHandle(), rValue));
        return NBiometricStatus.get(rValue.getValue());
    }
    
    static {
        LIBRARY = (NFSegmentLibrary)Native.loadLibrary("NBiometrics", NFSegmentLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NFSegment.LIBRARY.NFSegmentTypeOf());
    }
    
    interface NFSegmentLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NFSegmentTypeOf();
        
        int NFSegmentGetTopLeft(final HNObject p0, final NPointData p1);
        
        int NFSegmentGetTopRight(final HNObject p0, final NPointData p1);
        
        int NFSegmentGetBottomRight(final HNObject p0, final NPointData p1);
        
        int NFSegmentGetBottomLeft(final HNObject p0, final NPointData p1);
        
        int NFSegmentGetPosition(final HNObject p0, final IntByReference p1);
        
        int NFSegmentGetStatus(final HNObject p0, final IntByReference p1);
    }
}
