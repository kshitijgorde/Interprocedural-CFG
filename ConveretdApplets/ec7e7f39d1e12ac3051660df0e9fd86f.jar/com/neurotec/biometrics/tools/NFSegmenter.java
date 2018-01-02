// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.images.NImage;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.sun.jna.Pointer;
import com.neurotec.lang.NCore;
import com.neurotec.lang.NObject;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.neurotec.biometrics.NFPosition;
import com.neurotec.images.NGrayscaleImage;

public class NFSegmenter
{
    static final NFSegmenterLibrary LIBRARY;
    
    public static NFSegment[] locate(final NGrayscaleImage image, final NFPosition position) {
        return locate(image, position, null);
    }
    
    public static NFSegment[] locate(final NGrayscaleImage image, final NFPosition position, final NFPosition[] missingFingers) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        boolean ok = false;
        NFSegment[] segments = null;
        Pointer[] pHSegments = null;
        int segmentCount = 0;
        final PointerByReference rArHSegments = new PointerByReference();
        Pointer pArHSegments = null;
        final IntByReference rSegmentCount = new IntByReference();
        final int[] missingFingersValues = (int[])((missingFingers == null) ? null : new int[missingFingers.length]);
        if (missingFingersValues != null) {
            for (int i = 0; i < missingFingersValues.length; ++i) {
                missingFingersValues[i] = missingFingers[i].getValue();
            }
        }
        NResult.check(NFSegmenter.LIBRARY.NFSegmenterLocateEx(image.getHandle(), position.getValue(), missingFingersValues, (missingFingersValues != null) ? missingFingersValues.length : 0, rArHSegments, rSegmentCount));
        try {
            segmentCount = rSegmentCount.getValue();
            if (segmentCount == 0) {
                return null;
            }
            pArHSegments = rArHSegments.getValue();
            pHSegments = pArHSegments.getPointerArray(0L, segmentCount);
            segments = new NFSegment[segmentCount];
            for (int i = 0; i < segmentCount; ++i) {
                final HNObject object = new HNObject(pHSegments[i]);
                segments[i] = NFSegment.fromHandle(object);
            }
            ok = true;
        }
        finally {
            if (!ok) {
                for (int j = 0; j < segmentCount; ++j) {
                    if (segments != null && segments[j] != null) {
                        segments[j].dispose();
                    }
                    else if (pHSegments != null) {
                        NObject.free(new HNObject(pHSegments[j]));
                    }
                }
            }
            NCore.free(pArHSegments);
        }
        return segments;
    }
    
    public static NGrayscaleImage cutSingle(final NGrayscaleImage image, final NFSegment segment) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        if (segment == null) {
            throw new NullPointerException("segment");
        }
        NGrayscaleImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NFSegmenter.LIBRARY.NFSegmenterCutSingle(image.getHandle(), segment.getHandle(), rhValue));
        try {
            hValue = rhValue.getValue();
            value = (NGrayscaleImage)NImage.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NGrayscaleImage[] cutMultiple(final NGrayscaleImage image, final NFSegment[] segments) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        if (segments == null) {
            throw new NullPointerException("segments");
        }
        boolean ok = false;
        final PointerByReference rArHImages = new PointerByReference();
        Pointer pArHImages = null;
        NGrayscaleImage[] images = null;
        Pointer[] pHImages = null;
        final HNObject[] segmentArray = new HNObject[segments.length];
        for (int i = 0; i < segmentArray.length; ++i) {
            segmentArray[i] = segments[i].getHandle();
        }
        NResult.check(NFSegmenter.LIBRARY.NFSegmenterCutMultiple(image.getHandle(), segmentArray, segments.length, rArHImages));
        try {
            pArHImages = rArHImages.getValue();
            pHImages = pArHImages.getPointerArray(0L, segments.length);
            images = new NGrayscaleImage[segments.length];
            for (int i = 0; i < images.length; ++i) {
                images[i] = (NGrayscaleImage)NImage.fromHandle(new HNObject(pHImages[i]), true);
            }
            ok = true;
        }
        finally {
            if (!ok) {
                for (int j = 0; j < images.length; ++j) {
                    if (images != null && images[j] != null) {
                        images[j].dispose();
                    }
                    else if (pHImages != null) {
                        NObject.free(new HNObject(pHImages[j]));
                    }
                }
            }
            NCore.free(pArHImages);
        }
        return images;
    }
    
    static {
        LIBRARY = (NFSegmenterLibrary)Native.loadLibrary("NBiometrics", NFSegmenterLibrary.class, W32APIOptions.UNICODE_OPTIONS);
    }
    
    interface NFSegmenterLibrary extends NLibrary
    {
        int NFSegmenterLocate(final HNObject p0, final int p1, final HNObjectByReference[] p2, final IntByReference p3);
        
        int NFSegmenterLocateEx(final HNObject p0, final int p1, final int[] p2, final int p3, final PointerByReference p4, final IntByReference p5);
        
        int NFSegmenterCutSingle(final HNObject p0, final HNObject p1, final HNObjectByReference p2);
        
        int NFSegmenterCutMultiple(final HNObject p0, final HNObject[] p1, final int p2, final PointerByReference p3);
    }
}
