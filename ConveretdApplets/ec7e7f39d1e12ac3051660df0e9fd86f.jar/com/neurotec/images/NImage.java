// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import java.awt.image.WritableRaster;
import java.awt.image.SampleModel;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.nio.IntBuffer;
import java.util.Hashtable;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.DataBufferByte;
import java.awt.image.ComponentColorModel;
import java.awt.color.ColorSpace;
import java.awt.Image;
import java.util.EnumSet;
import com.sun.jna.Pointer;
import com.neurotec.lang.NCore;
import com.sun.jna.ptr.PointerByReference;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.sun.jna.WString;
import java.io.IOException;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import java.nio.ByteBuffer;
import com.neurotec.lang.NObject;

public class NImage extends NObject implements Cloneable
{
    private NImage wrappedImage;
    private ByteBuffer wrappedPixels;
    public static final int FLAG_DST_SWAP_CHANNELS = 256;
    public static final int FLAG_DST_ALPHA_CHANNEL_FIRST = 512;
    public static final int FLAG_DST_BOTTOM_TO_TOP = 1024;
    public static final int FLAG_SRC_SWAP_CHANNELS = 4096;
    public static final int FLAG_SRC_ALPHA_CHANNEL_FIRST = 8192;
    public static final int FLAG_SRC_BOTTOM_TO_TOP = 16384;
    static final NImageLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    protected NImage(final HNObject handle, final NNativeType requiredType, final boolean claimHandle) {
        super(handle, requiredType, claimHandle);
    }
    
    protected NImage(final HNObject handle, final NNativeType requiredType, final boolean claimHandle, final NImage wrappedImage, final ByteBuffer wrappedPixels) {
        super(handle, requiredType, claimHandle);
        this.wrappedImage = wrappedImage;
        this.wrappedPixels = wrappedPixels;
    }
    
    static NImage fromHandle(final HNObject handle, final NImage wrappedImage, final ByteBuffer wrappedPixels) {
        return fromHandle(handle, true, wrappedImage, wrappedPixels);
    }
    
    static NImage fromHandle(final HNObject handle, final boolean ownsHandle, final NImage wrappedImage, final ByteBuffer wrappedPixels) {
        NImage value;
        if (NMonochromeImage.NATIVE_TYPE.isInstanceOfType(handle)) {
            value = new NMonochromeImage(handle, false, wrappedImage, wrappedPixels);
        }
        else if (NGrayscaleImage.NATIVE_TYPE.isInstanceOfType(handle)) {
            value = new NGrayscaleImage(handle, false, wrappedImage, wrappedPixels);
        }
        else if (NRgbImage.NATIVE_TYPE.isInstanceOfType(handle)) {
            value = new NRgbImage(handle, false, wrappedImage, wrappedPixels);
        }
        else {
            value = new NImage(handle, NImage.NATIVE_TYPE, false, wrappedImage, wrappedPixels);
        }
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public static NImage fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NImage fromHandle(final HNObject handle, final boolean ownsHandle) {
        return fromHandle(handle, ownsHandle, null, null);
    }
    
    public static void copy(final NImage srcImage, final int left, final int top, final int width, final int height, final NImage dstImage, final int dstLeft, final int dstTop) {
        if (srcImage == null) {
            throw new IllegalArgumentException("srcImage");
        }
        if (dstImage == null) {
            throw new IllegalArgumentException("dstImage");
        }
        NResult.check(NImage.LIBRARY.NImageCopy(srcImage.getHandle(), left, top, width, height, dstImage.getHandle(), dstLeft, dstTop));
    }
    
    public static NImage getWrapper(final NPixelFormat pixelFormat, final int width, final int height, final int stride, final ByteBuffer pixels) {
        return getWrapper(pixelFormat, width, height, (long)stride, pixels, 0);
    }
    
    public static NImage getWrapper(final NPixelFormat pixelFormat, final int width, final int height, final int stride, final ByteBuffer pixels, final int flags) {
        return getWrapper(pixelFormat, width, height, (long)stride, pixels, flags);
    }
    
    public static NImage getWrapper(final NPixelFormat pixelFormat, final int width, final int height, final long stride, final ByteBuffer pixels) {
        return getWrapper(pixelFormat, width, height, stride, pixels, 0);
    }
    
    public static NImage getWrapper(final NPixelFormat pixelFormat, final int width, final int height, final long stride, final ByteBuffer pixels, final int flags) {
        if (pixels == null) {
            throw new NullPointerException("pixels");
        }
        if (height * stride != pixels.remaining()) {
            throw new IllegalArgumentException("pixels.remaining do not match height and stride");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageCreateWrapperEx(pixelFormat.getValue(), width, height, new NativeSize(stride), pixels.slice(), false, flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(hValue, null, pixels);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImage getWrapper(final NPixelFormat pixelFormat, final int srcWidth, final int srcHeight, final int stride, final ByteBuffer pixels, final int left, final int top, final int width, final int height) {
        return getWrapper(pixelFormat, srcWidth, srcHeight, (long)stride, pixels, left, top, width, height, 0);
    }
    
    public static NImage getWrapper(final NPixelFormat pixelFormat, final int srcWidth, final int srcHeight, final int stride, final ByteBuffer pixels, final int left, final int top, final int width, final int height, final int flags) {
        return getWrapper(pixelFormat, srcWidth, srcHeight, (long)stride, pixels, left, top, width, height, flags);
    }
    
    public static NImage getWrapper(final NPixelFormat pixelFormat, final int srcWidth, final int srcHeight, final long stride, final ByteBuffer pixels, final int left, final int top, final int width, final int height) {
        return getWrapper(pixelFormat, srcWidth, srcHeight, stride, pixels, left, top, width, height, 0);
    }
    
    public static NImage getWrapper(final NPixelFormat pixelFormat, final int srcWidth, final int srcHeight, final long stride, final ByteBuffer pixels, final int left, final int top, final int width, final int height, final int flags) {
        if (pixels == null) {
            throw new NullPointerException("pixels");
        }
        if (height * stride != pixels.remaining()) {
            throw new IllegalArgumentException("pixels.remaining do not match height and stride");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageCreateWrapperForPartEx(pixelFormat.getValue(), srcWidth, srcHeight, new NativeSize(stride), pixels.slice(), left, top, width, height, flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(hValue, null, pixels);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImage getWrapper(final NImage srcImage, final int left, final int top, final int width, final int height) {
        return getWrapper(srcImage, left, top, width, height, 0);
    }
    
    public static NImage getWrapper(final NImage srcImage, final int left, final int top, final int width, final int height, final int flags) {
        if (srcImage == null) {
            throw new NullPointerException("srcImage");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageCreateWrapperForImagePartEx2(srcImage.getHandle(), left, top, width, height, flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(hValue, srcImage, null);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImage create(final NPixelFormat pixelFormat, final int width, final int height, final int stride) {
        return create(pixelFormat, width, height, (long)stride, 0);
    }
    
    public static NImage create(final NPixelFormat pixelFormat, final int width, final int height, final int stride, final int flags) {
        return create(pixelFormat, width, height, (long)stride, flags);
    }
    
    public static NImage create(final NPixelFormat pixelFormat, final int width, final int height, final long stride) {
        return create(pixelFormat, width, height, stride, 0);
    }
    
    public static NImage create(final NPixelFormat pixelFormat, final int width, final int height, final long stride, final int flags) {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageCreateEx(pixelFormat.getValue(), width, height, new NativeSize(stride), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImage fromData(final NPixelFormat pixelFormat, final int width, final int height, final int stride, final int srcStride, final ByteBuffer srcPixels) {
        return fromData(pixelFormat, width, height, stride, (long)srcStride, srcPixels, 0);
    }
    
    public static NImage fromData(final NPixelFormat pixelFormat, final int width, final int height, final int stride, final int srcStride, final ByteBuffer srcPixels, final int flags) {
        return fromData(pixelFormat, width, height, stride, (long)srcStride, srcPixels, flags);
    }
    
    public static NImage fromData(final NPixelFormat pixelFormat, final int width, final int height, final long stride, final long srcStride, final ByteBuffer srcPixels) {
        return fromData(pixelFormat, width, height, stride, srcStride, srcPixels, 0);
    }
    
    public static NImage fromData(final NPixelFormat pixelFormat, final int width, final int height, final long stride, final long srcStride, final ByteBuffer srcPixels, final int flags) {
        if (srcPixels == null) {
            throw new NullPointerException("srcPixels");
        }
        if (height * stride != srcPixels.remaining()) {
            throw new IllegalArgumentException("srcPixels.remaining do not match height and stride");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageCreateFromDataEx(pixelFormat.getValue(), width, height, new NativeSize(stride), new NativeSize(srcStride), srcPixels.slice(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImage fromData(final NPixelFormat pixelFormat, final int stride, final int srcWidth, final int srcHeight, final int srcStride, final ByteBuffer srcPixels, final int left, final int top, final int width, final int height) {
        return fromData(pixelFormat, stride, srcWidth, srcHeight, (long)srcStride, srcPixels.slice(), left, top, width, height, 0);
    }
    
    public static NImage fromData(final NPixelFormat pixelFormat, final int stride, final int srcWidth, final int srcHeight, final int srcStride, final ByteBuffer srcPixels, final int left, final int top, final int width, final int height, final int flags) {
        return fromData(pixelFormat, stride, srcWidth, srcHeight, (long)srcStride, srcPixels.slice(), left, top, width, height, flags);
    }
    
    public static NImage fromData(final NPixelFormat pixelFormat, final long stride, final int srcWidth, final int srcHeight, final long srcStride, final ByteBuffer srcPixels, final int left, final int top, final int width, final int height) {
        return fromData(pixelFormat, stride, srcWidth, srcHeight, srcStride, srcPixels.slice(), left, top, width, height, 0);
    }
    
    public static NImage fromData(final NPixelFormat pixelFormat, final long stride, final int srcWidth, final int srcHeight, final long srcStride, final ByteBuffer srcPixels, final int left, final int top, final int width, final int height, final int flags) {
        if (srcPixels == null) {
            throw new NullPointerException("srcPixels");
        }
        if (height * stride != srcPixels.remaining()) {
            throw new IllegalArgumentException("srcPixels.remaining do not match height and stride");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageCreateFromDataPartEx(pixelFormat.getValue(), new NativeSize(stride), srcWidth, srcHeight, new NativeSize(srcStride), srcPixels.slice(), left, top, width, height, flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImage fromImage(final NPixelFormat pixelFormat, final int stride, final NImage srcImage) {
        return fromImage(pixelFormat, (long)stride, srcImage, 0);
    }
    
    public static NImage fromImage(final NPixelFormat pixelFormat, final int stride, final NImage srcImage, final int flags) {
        return fromImage(pixelFormat, (long)stride, srcImage, flags);
    }
    
    public static NImage fromImage(final NPixelFormat pixelFormat, final long stride, final NImage srcImage) {
        return fromImage(pixelFormat, stride, srcImage, 0);
    }
    
    public static NImage fromImage(final NPixelFormat pixelFormat, final long stride, final NImage srcImage, final int flags) {
        if (stride < 0L) {
            throw new IllegalArgumentException("stride is less than zero");
        }
        if (srcImage == null) {
            throw new NullPointerException("srcImage");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageCreateFromImageEx2(pixelFormat.getValue(), new NativeSize(stride), srcImage.getHandle(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImage fromImage(final NPixelFormat pixelFormat, final int stride, final NImage srcImage, final int left, final int top, final int width, final int height) {
        return fromImage(pixelFormat, (long)stride, srcImage, left, top, width, height, 0);
    }
    
    public static NImage fromImage(final NPixelFormat pixelFormat, final int stride, final NImage srcImage, final int left, final int top, final int width, final int height, final int flags) {
        return fromImage(pixelFormat, (long)stride, srcImage, left, top, width, height, flags);
    }
    
    public static NImage fromImage(final NPixelFormat pixelFormat, final long stride, final NImage srcImage, final int left, final int top, final int width, final int height) {
        return fromImage(pixelFormat, stride, srcImage, left, top, width, height, 0);
    }
    
    public static NImage fromImage(final NPixelFormat pixelFormat, final long stride, final NImage srcImage, final int left, final int top, final int width, final int height, final int flags) {
        if (srcImage == null) {
            throw new NullPointerException("srcImage");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageCreateFromImagePartEx2(pixelFormat.getValue(), new NativeSize(stride), srcImage.getHandle(), left, top, width, height, flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImage fromFile(final String fileName) throws IOException {
        return fromFile(fileName, null, 0);
    }
    
    public static NImage fromFile(final String fileName, final NImageFormat imageFormat) throws IOException {
        return fromFile(fileName, imageFormat, 0);
    }
    
    public static NImage fromFile(final String fileName, final NImageFormat imageFormat, final int flags) throws IOException {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.checkAll(NResult.checkIO(NResult.checkUnchecked(NImage.LIBRARY.NImageCreateFromFileEx(new WString(fileName), (imageFormat == null) ? null : imageFormat.getHandle(), flags, null, rhValue))));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(rhValue.getValue());
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImageWithInfo fromFile(final String fileName, final boolean returnImageInfo) throws IOException {
        return fromFile(fileName, null, 0, returnImageInfo);
    }
    
    public static NImageWithInfo fromFile(final String fileName, final NImageFormat imageFormat, final boolean returnImageInfo) throws IOException {
        return fromFile(fileName, imageFormat, 0, returnImageInfo);
    }
    
    public static NImageWithInfo fromFile(final String fileName, final NImageFormat imageFormat, final int flags, final boolean returnImageInfo) throws IOException {
        NImageWithInfo result = null;
        boolean ok = false;
        NImage image = null;
        HNObject hImage = null;
        final HNObjectByReference rhImage = new HNObjectByReference();
        NImageInfo info = null;
        HNObject hInfo = null;
        HNObjectByReference rhInfo = null;
        if (returnImageInfo) {
            rhInfo = new HNObjectByReference();
        }
        NResult.checkAll(NResult.checkIO(NResult.checkUnchecked(NImage.LIBRARY.NImageCreateFromFileEx(new WString(fileName), (imageFormat == null) ? HNObject.NULL : imageFormat.getHandle(), flags, returnImageInfo ? rhInfo : null, rhImage))));
        try {
            hImage = rhImage.getValue();
            image = (HNObject.NULL.equals(hImage) ? null : fromHandle(hImage));
            if (returnImageInfo) {
                hInfo = rhInfo.getValue();
                info = (HNObject.NULL.equals(hInfo) ? null : NImageInfo.fromHandle(hInfo));
            }
            result = new NImageWithInfo(image, info);
            ok = true;
        }
        finally {
            if (!ok) {
                if (image == null) {
                    NObject.free(hImage);
                }
                else {
                    image.dispose();
                }
                if (info == null) {
                    NObject.free(hInfo);
                }
                else {
                    info.dispose();
                }
            }
        }
        return result;
    }
    
    public static NImage fromMemory(final ByteBuffer buffer) {
        return fromMemory(buffer, null, 0);
    }
    
    public static NImage fromMemory(final ByteBuffer buffer, final NImageFormat imageFormat) {
        return fromMemory(buffer, imageFormat, 0);
    }
    
    public static NImage fromMemory(final ByteBuffer buffer, final NImageFormat imageFormat, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        try {
            NResult.check(NImage.LIBRARY.NImageCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), (imageFormat == null) ? null : imageFormat.getHandle(), flags, rSize, null, rhValue));
            hValue = rhValue.getValue();
            value = fromHandle(rhValue.getValue());
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
            buffer.position(buffer.position() + rSize.getValue().intValue());
        }
        return value;
    }
    
    public static NImageWithInfo fromMemory(final ByteBuffer buffer, final boolean returnImageInfo) throws IOException {
        return fromMemory(buffer, null, 0, returnImageInfo);
    }
    
    public static NImageWithInfo fromMemory(final ByteBuffer buffer, final NImageFormat imageFormat, final boolean returnImageInfo) throws IOException {
        return fromMemory(buffer, imageFormat, 0, returnImageInfo);
    }
    
    public static NImageWithInfo fromMemory(final ByteBuffer buffer, final NImageFormat imageFormat, final int flags, final boolean returnImageInfo) throws IOException {
        NImageWithInfo result = null;
        boolean ok = false;
        NImage image = null;
        HNObject hImage = null;
        final HNObjectByReference rhImage = new HNObjectByReference();
        NImageInfo info = null;
        HNObject hInfo = null;
        HNObjectByReference rhInfo = null;
        final NativeSizeByReference rSize = new NativeSizeByReference();
        if (returnImageInfo) {
            rhInfo = new HNObjectByReference();
        }
        try {
            NResult.check(NImage.LIBRARY.NImageCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), (imageFormat == null) ? HNObject.NULL : imageFormat.getHandle(), flags, rSize, returnImageInfo ? rhInfo : null, rhImage));
            hImage = rhImage.getValue();
            image = (HNObject.NULL.equals(hImage) ? null : fromHandle(hImage));
            if (returnImageInfo) {
                hInfo = rhInfo.getValue();
                info = (HNObject.NULL.equals(hInfo) ? null : NImageInfo.fromHandle(hInfo));
            }
            result = new NImageWithInfo(image, info);
            ok = true;
        }
        finally {
            if (!ok) {
                if (image == null) {
                    NObject.free(hImage);
                }
                else {
                    image.dispose();
                }
                if (info == null) {
                    NObject.free(hInfo);
                }
                else {
                    info.dispose();
                }
            }
            buffer.position(buffer.position() + rSize.getValue().intValue());
        }
        return result;
    }
    
    protected final void dispose(final boolean disposing) {
        this.wrappedImage = null;
        if (this.wrappedPixels != null) {
            this.wrappedPixels = null;
        }
        super.dispose(disposing);
    }
    
    public final void copyTo(final NPixelFormat dstPixelFormat, final int dstWidth, final int dstHeight, final int dstStride, final ByteBuffer dstPixels, final int dstLeft, final int dstTop) {
        this.copyTo(dstPixelFormat, dstWidth, dstHeight, (long)dstStride, dstPixels, dstLeft, dstTop, 0);
    }
    
    public final void copyTo(final NPixelFormat dstPixelFormat, final int dstWidth, final int dstHeight, final int dstStride, final ByteBuffer dstPixels, final int dstLeft, final int dstTop, final int flags) {
        this.copyTo(dstPixelFormat, dstWidth, dstHeight, (long)dstStride, dstPixels, dstLeft, dstTop, flags);
    }
    
    public final void copyTo(final NPixelFormat dstPixelFormat, final int dstWidth, final int dstHeight, final long dstStride, final ByteBuffer dstPixels, final int dstLeft, final int dstTop) {
        this.copyTo(dstPixelFormat, dstWidth, dstHeight, dstStride, dstPixels, dstLeft, dstTop, 0);
    }
    
    public final void copyTo(final NPixelFormat dstPixelFormat, final int dstWidth, final int dstHeight, final long dstStride, final ByteBuffer dstPixels, final int dstLeft, final int dstTop, final int flags) {
        if (dstPixels == null) {
            throw new NullPointerException("dstPixels");
        }
        if (dstHeight * dstStride != dstPixels.remaining()) {
            throw new IllegalArgumentException("dstPixels.remaining do not match height and stride");
        }
        NResult.check(NImage.LIBRARY.NImageCopyToData(this.getHandle(), dstPixelFormat.getValue(), dstWidth, dstHeight, new NativeSize(dstStride), dstPixels.slice(), new NativeSize((long)dstPixels.remaining()), dstLeft, dstTop, flags));
    }
    
    public final void copyTo(final NImage dstImage, final int dstLeft, final int dstTop) {
        if (dstImage == null) {
            throw new NullPointerException("dstImage");
        }
        NResult.check(NImage.LIBRARY.NImageCopyTo(this.getHandle(), dstImage.getHandle(), dstLeft, dstTop));
    }
    
    public final void save(final String fileName) throws IOException {
        this.save(fileName, null, null, 0);
    }
    
    public final void save(final String fileName, final NImageFormat imageFormat) throws IOException {
        this.save(fileName, imageFormat, null, 0);
    }
    
    public final void save(final String fileName, final NImageFormat imageFormat, final NImageInfo info, final int flags) throws IOException {
        NResult.checkAll(NResult.checkIO(NResult.checkUnchecked(NImage.LIBRARY.NImageSaveToFileEx(this.getHandle(), new WString(fileName), (imageFormat == null) ? null : imageFormat.getHandle(), (info == null) ? null : info.getHandle(), flags))));
    }
    
    public ByteBuffer save(final NImageFormat imageFormat) {
        return this.save(imageFormat, null, 0);
    }
    
    public ByteBuffer save(final NImageInfo info) {
        return this.save(null, info, 0);
    }
    
    public ByteBuffer save(final NImageFormat imageFormat, final NImageInfo info, final int flags) {
        if (imageFormat == null && info == null) {
            throw new NullPointerException("imageFormat");
        }
        final PointerByReference rValue = new PointerByReference();
        ByteBuffer value = null;
        Pointer pBuffer = null;
        final NativeSizeByReference rBufferLength = new NativeSizeByReference();
        NResult.check(NImage.LIBRARY.NImageSaveToMemory(this.getHandle(), (imageFormat == null) ? HNObject.NULL : imageFormat.getHandle(), (info == null) ? HNObject.NULL : info.getHandle(), flags, rValue, rBufferLength));
        try {
            pBuffer = rValue.getValue();
            value = NCore.getByteBuffer(pBuffer, rBufferLength.getValue().longValue());
        }
        finally {
            if (value == null) {
                NCore.free(pBuffer);
            }
        }
        return value;
    }
    
    public final void flipHorizontally() {
        NResult.check(NImage.LIBRARY.NImageFlipHorizontally(this.getHandle()));
    }
    
    public final void flipVertically() {
        NResult.check(NImage.LIBRARY.NImageFlipVertically(this.getHandle()));
    }
    
    public final void flipDiagonally() {
        NResult.check(NImage.LIBRARY.NImageFlipDiagonally(this.getHandle()));
    }
    
    public final NImage rotateFlip(final NImageRotateType rotateType, final EnumSet<NImageFlipType> flipType) {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageRotateFlip(this.getHandle(), rotateType.getValue() | NImageFlipType.getValue(flipType), rhValue));
        try {
            hValue = rhValue.getValue();
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public final NImage crop(final int left, final int top, final int width, final int height) {
        if (left < 0) {
            throw new IllegalArgumentException("left is less than zero");
        }
        if (top < 0) {
            throw new IllegalArgumentException("top is less than zero");
        }
        if (width < 0) {
            throw new IllegalArgumentException("width is less than zero");
        }
        if (height < 0) {
            throw new IllegalArgumentException("height is less than zero");
        }
        NImage value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageCrop(this.getHandle(), left, top, width, height, rhValue));
        final HNObject hValue = rhValue.getValue();
        try {
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public final NMonochromeImage toMonochrome() {
        NMonochromeImage value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageToMonochrome(this.getHandle(), rhValue));
        final HNObject hValue = rhValue.getValue();
        try {
            value = (NMonochromeImage)fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public final NGrayscaleImage toGrayscale() {
        NGrayscaleImage value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageToGrayscale(this.getHandle(), rhValue));
        final HNObject hValue = rhValue.getValue();
        try {
            value = (NGrayscaleImage)fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public final NRgbImage toRgb() {
        NRgbImage value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImage.LIBRARY.NImageToRgb(this.getHandle(), rhValue));
        final HNObject hValue = rhValue.getValue();
        try {
            value = (NRgbImage)fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public final Image toImage() {
        if (this instanceof NGrayscaleImage) {
            final int[] nBits = { 8 };
            final byte[] data = new byte[this.getWidth() * this.getHeight()];
            NResult.check(NImage.LIBRARY.NImageCopyToData(this.getHandle(), NPixelFormat.GRAYSCALE.getValue(), this.getWidth(), this.getHeight(), new NativeSize((long)this.getWidth()), ByteBuffer.wrap(data), new NativeSize((long)(this.getWidth() * this.getHeight())), 0, 0, 0));
            final ColorSpace cs = ColorSpace.getInstance(1003);
            final ColorModel colorModel = new ComponentColorModel(cs, nBits, false, true, 1, 0);
            final SampleModel sampleModel = colorModel.createCompatibleSampleModel(this.getWidth(), this.getHeight());
            final WritableRaster raster = Raster.createWritableRaster(sampleModel, new DataBufferByte(data, data.length), null);
            return new BufferedImage(colorModel, raster, false, null);
        }
        final int[] data2 = new int[this.getWidth() * this.getHeight()];
        NResult.check(NImage.LIBRARY.NImageCopyToData(this.getHandle(), NPixelFormat.RGB_A.getValue(), this.getWidth(), this.getHeight(), new NativeSize((long)(4 * this.getWidth())), IntBuffer.wrap(data2), new NativeSize((long)(4 * this.getWidth() * this.getHeight())), 0, 0, 256));
        final ColorModel colorModel2 = new DirectColorModel(32, 16711680, 65280, 255, -16777216);
        final SampleModel sampleModel2 = colorModel2.createCompatibleSampleModel(this.getWidth(), this.getHeight());
        final WritableRaster raster2 = Raster.createWritableRaster(sampleModel2, new DataBufferInt(data2, data2.length), null);
        return new BufferedImage(colorModel2, raster2, false, null);
    }
    
    public final NPixelFormat getPixelFormat() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NImage.LIBRARY.NImageGetPixelFormat(this.getHandle(), rValue));
        return new NPixelFormat(rValue.getValue());
    }
    
    public final int getWidth() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NImage.LIBRARY.NImageGetWidth(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final int getHeight() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NImage.LIBRARY.NImageGetHeight(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final int getStride() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NImage.LIBRARY.NImageGetStride(this.getHandle(), rValue));
        return rValue.getValue().intValue();
    }
    
    public final long getLongStride() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NImage.LIBRARY.NImageGetStride(this.getHandle(), rValue));
        return rValue.getValue().longValue();
    }
    
    public final int getSize() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NImage.LIBRARY.NImageGetSize(this.getHandle(), rValue));
        return rValue.getValue().intValue();
    }
    
    public final long getLongSize() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NImage.LIBRARY.NImageGetSize(this.getHandle(), rValue));
        return rValue.getValue().longValue();
    }
    
    public final float getHorzResolution() {
        final FloatByReference rValue = new FloatByReference();
        NResult.check(NImage.LIBRARY.NImageGetHorzResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setHorzResolution(final float value) {
        NResult.check(NImage.LIBRARY.NImageSetHorzResolution(this.getHandle(), value));
    }
    
    public final float getVertResolution() {
        final FloatByReference rValue = new FloatByReference();
        NResult.check(NImage.LIBRARY.NImageGetVertResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setVertResolution(final float value) {
        NResult.check(NImage.LIBRARY.NImageSetVertResolution(this.getHandle(), value));
    }
    
    public final boolean isResolutionIsAspectRatio() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NImage.LIBRARY.NImageGetResolutionIsAspectRatio(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setResolutionIsAspectRatio(final boolean value) {
        NResult.check(NImage.LIBRARY.NImageSetResolutionIsAspectRatio(this.getHandle(), value));
    }
    
    public final ByteBuffer getPixels() {
        final PointerByReference rPixels = new PointerByReference();
        NResult.check(NImage.LIBRARY.NImageGetPixels(this.getHandle(), rPixels));
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NImage.LIBRARY.NImageGetSize(this.getHandle(), rSize));
        return NCore.getByteBuffer(rPixels.getValue(), rSize.getValue().longValue(), false);
    }
    
    public final Object clone() throws CloneNotSupportedException {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(NImage.LIBRARY.NImageClone(this.getHandle(), rhValue));
            hValue = rhValue.getValue();
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    static {
        LIBRARY = (NImageLibrary)Native.loadLibrary("NImages", NImageLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NImage.LIBRARY.NImageTypeOf());
    }
    
    interface NImageLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NImageTypeOf();
        
        int NImageCopy(final HNObject p0, final int p1, final int p2, final int p3, final int p4, final HNObject p5, final int p6, final int p7);
        
        int NImageCreateWrapperEx(final int p0, final int p1, final int p2, final NativeSize p3, final Buffer p4, final boolean p5, final int p6, final HNObjectByReference p7);
        
        int NImageCreateWrapperForPartEx(final int p0, final int p1, final int p2, final NativeSize p3, final Buffer p4, final int p5, final int p6, final int p7, final int p8, final int p9, final HNObjectByReference p10);
        
        int NImageCreateWrapperForImagePartEx2(final HNObject p0, final int p1, final int p2, final int p3, final int p4, final int p5, final HNObjectByReference p6);
        
        int NImageCreateEx(final int p0, final int p1, final int p2, final NativeSize p3, final int p4, final HNObjectByReference p5);
        
        int NImageCreateFromDataEx(final int p0, final int p1, final int p2, final NativeSize p3, final NativeSize p4, final Buffer p5, final int p6, final HNObjectByReference p7);
        
        int NImageCreateFromDataPartEx(final int p0, final NativeSize p1, final int p2, final int p3, final NativeSize p4, final Buffer p5, final int p6, final int p7, final int p8, final int p9, final int p10, final HNObjectByReference p11);
        
        int NImageCreateFromImageEx2(final int p0, final NativeSize p1, final HNObject p2, final int p3, final HNObjectByReference p4);
        
        int NImageCreateFromImagePartEx2(final int p0, final NativeSize p1, final HNObject p2, final int p3, final int p4, final int p5, final int p6, final int p7, final HNObjectByReference p8);
        
        int NImageCreateFromFileEx(final WString p0, final HNObject p1, final int p2, final HNObjectByReference p3, final HNObjectByReference p4);
        
        int NImageCreateFromMemory(final Buffer p0, final NativeSize p1, final HNObject p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5, final HNObjectByReference p6);
        
        int NImageClone(final HNObject p0, final HNObjectByReference p1);
        
        int NImageCopyToData(final HNObject p0, final int p1, final int p2, final int p3, final NativeSize p4, final Buffer p5, final NativeSize p6, final int p7, final int p8, final int p9);
        
        int NImageCopyTo(final HNObject p0, final HNObject p1, final int p2, final int p3);
        
        int NImageSaveToFileEx(final HNObject p0, final WString p1, final HNObject p2, final HNObject p3, final int p4);
        
        int NImageSaveToMemory(final HNObject p0, final HNObject p1, final HNObject p2, final int p3, final PointerByReference p4, final NativeSizeByReference p5);
        
        int NImageFlipHorizontally(final HNObject p0);
        
        int NImageFlipVertically(final HNObject p0);
        
        int NImageFlipDiagonally(final HNObject p0);
        
        int NImageRotateFlip(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NImageCrop(final HNObject p0, final int p1, final int p2, final int p3, final int p4, final HNObjectByReference p5);
        
        int NImageToMonochrome(final HNObject p0, final HNObjectByReference p1);
        
        int NImageToGrayscale(final HNObject p0, final HNObjectByReference p1);
        
        int NImageToRgb(final HNObject p0, final HNObjectByReference p1);
        
        int NImageGetPixelFormat(final HNObject p0, final IntByReference p1);
        
        int NImageGetWidth(final HNObject p0, final IntByReference p1);
        
        int NImageGetHeight(final HNObject p0, final IntByReference p1);
        
        int NImageGetStride(final HNObject p0, final NativeSizeByReference p1);
        
        int NImageGetSize(final HNObject p0, final NativeSizeByReference p1);
        
        int NImageGetHorzResolution(final HNObject p0, final FloatByReference p1);
        
        int NImageSetHorzResolution(final HNObject p0, final float p1);
        
        int NImageGetVertResolution(final HNObject p0, final FloatByReference p1);
        
        int NImageSetVertResolution(final HNObject p0, final float p1);
        
        int NImageGetResolutionIsAspectRatio(final HNObject p0, final BooleanByReference p1);
        
        int NImageSetResolutionIsAspectRatio(final HNObject p0, final boolean p1);
        
        int NImageGetPixels(final HNObject p0, final PointerByReference p1);
    }
}
