// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NObject;
import java.nio.ByteBuffer;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import java.util.Iterator;
import com.neurotec.lang.NNativeModule;

public final class NImages
{
    static final String DLL_NAME = "NImages";
    static final NImagesLibrary LIBRARY;
    public static NNativeModule NATIVE_MODULE;
    
    public static String getOpenFileFilter() {
        final StringBuilder sb = new StringBuilder();
        for (final NImageFormat imageFormat : NImageFormat.formats) {
            if (imageFormat.isCanRead()) {
                if (sb.length() != 0) {
                    sb.append(';');
                }
                sb.append(imageFormat.getDefaultFileExtension());
            }
        }
        return sb.toString();
    }
    
    public static String getSaveFileFilter() {
        final StringBuilder sb = new StringBuilder();
        for (final NImageFormat imageFormat : NImageFormat.formats) {
            if (imageFormat.isCanWrite()) {
                if (sb.length() != 0) {
                    sb.append(';');
                }
                sb.append(imageFormat.getFileFilter());
            }
        }
        return sb.toString();
    }
    
    public static String getOpenFileFilterString(final boolean addAllImage, final boolean addAllFiles) {
        final StringBuilder sb = new StringBuilder();
        if (addAllImage) {
            sb.append("All Image Files " + getOpenFileFilter());
        }
        for (final NImageFormat imageFormat : NImageFormat.formats) {
            if (imageFormat.isCanRead()) {
                if (sb.length() != 0) {
                    sb.append('|');
                }
                sb.append(imageFormat.getName() + " Files " + imageFormat.getFileFilter());
            }
        }
        if (addAllFiles) {
            sb.append("|All Files (*.*)|*.*");
        }
        return sb.toString();
    }
    
    public static String getSaveFileFilterString() {
        final StringBuilder sb = new StringBuilder();
        for (final NImageFormat imageFormat : NImageFormat.formats) {
            if (imageFormat.isCanWrite()) {
                if (sb.length() != 0) {
                    sb.append('|');
                }
                sb.append(imageFormat.getName() + " Files " + imageFormat.getFileFilter());
            }
        }
        return sb.toString();
    }
    
    public static NImage getGrayscaleColorWrapper(final NImage image, final NRgb minColor, final NRgb maxColor) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImages.LIBRARY.NImagesGetGrayscaleColorWrapperEx(image.getHandle(), minColor, maxColor, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImage.fromHandle(hValue, image, null);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    static {
        LIBRARY = (NImagesLibrary)Native.loadLibrary("NImages", NImagesLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NImages.NATIVE_MODULE = NNativeModule.fromHandle(NImages.LIBRARY.NImagesModuleOf());
    }
    
    interface NImagesLibrary extends NLibrary
    {
        HNObject NImagesModuleOf();
        
        int NImagesGetGrayscaleColorWrapperEx(final HNObject p0, final NRgb p1, final NRgb p2, final HNObjectByReference p3);
    }
}
