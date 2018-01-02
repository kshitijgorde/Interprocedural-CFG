// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.util.NObjectStaticReadOnlyCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.BooleanByReference;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import java.nio.ByteBuffer;
import java.io.IOException;
import com.neurotec.lang.NResult;
import com.sun.jna.WString;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NObject;

public final class NImageFormat extends NObject
{
    static final NImageFormatLibrary LIBRARY;
    public static NImageFormat Bmp;
    public static NImageFormat Tiff;
    public static NImageFormat Jpeg;
    public static NImageFormat Png;
    public static NImageFormat Wsq;
    public static NImageFormat IHead;
    public static NImageFormat Jpeg2K;
    public static ImageFormatCollection formats;
    private static HNObject hBmp;
    private static HNObject hTiff;
    private static HNObject hJpeg;
    private static HNObject hPng;
    private static HNObject hWsq;
    private static HNObject hIHead;
    private static HNObject hJpeg2K;
    
    public static NImageFormat fromHandle(final HNObject handle) {
        if (handle == null || handle.getPointer() == null) {
            throw new NullPointerException("handle");
        }
        if (handle.equals(NImageFormat.hTiff)) {
            return NImageFormat.Tiff;
        }
        if (handle.equals(NImageFormat.hJpeg)) {
            return NImageFormat.Jpeg;
        }
        if (handle.equals(NImageFormat.hPng)) {
            return NImageFormat.Png;
        }
        if (handle.equals(NImageFormat.hWsq)) {
            return NImageFormat.Wsq;
        }
        if (handle.equals(NImageFormat.hJpeg2K)) {
            return NImageFormat.Jpeg2K;
        }
        if (handle.equals(NImageFormat.hBmp)) {
            return NImageFormat.Bmp;
        }
        if (handle.equals(NImageFormat.hIHead)) {
            return NImageFormat.IHead;
        }
        throw new IllegalArgumentException("Unknown NImageFormat handle");
    }
    
    public static NImageFormat select(final String fileName, final FileAccess fileAccess) throws IOException {
        if (fileName == null) {
            throw new NullPointerException("fileName");
        }
        final HNObjectByReference rhImageFormat = new HNObjectByReference();
        NResult.checkAll(NResult.checkIO(NResult.checkUnchecked(NImageFormat.LIBRARY.NImageFormatSelect(new WString(fileName), fileAccess.getValue(), rhImageFormat))));
        final HNObject hImageFormat = rhImageFormat.getValue();
        return (hImageFormat == null || hImageFormat.getPointer() == null) ? null : fromHandle(hImageFormat);
    }
    
    public static NImageReader selectReader(final String fileName) {
        return selectReader(fileName, 0);
    }
    
    public static NImageReader selectReader(final String fileName, final int flags) {
        NImageReader value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatSelectReaderFromFile(new WString(fileName), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = (HNObject.NULL.equals(hValue) ? null : NImageReader.fromHandle(hValue, null));
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public static NImageReader selectReader(final ByteBuffer buffer) {
        return selectReader(buffer, 0);
    }
    
    public static NImageReader selectReader(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NImageReader value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatSelectReaderFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = (HNObject.NULL.equals(hValue) ? null : NImageReader.fromHandle(hValue, buffer));
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    private NImageFormat(final HNObject handle) {
        super(handle, NImageFormat.NATIVE_TYPE, false);
    }
    
    public NImageReader openReader(final String fileName) {
        return this.openReader(fileName, 0);
    }
    
    public NImageReader openReader(final String fileName, final int flags) {
        NImageReader value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatOpenReaderFromFile(this.getHandle(), new WString(fileName), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImageReader.fromHandle(hValue, null);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public NImageReader openReader(final ByteBuffer buffer) {
        return this.openReader(buffer, 0);
    }
    
    public NImageReader openReader(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NImageReader value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatOpenReaderFromMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImageReader.fromHandle(hValue, buffer);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public NImageInfo createInfo(final NImage image) {
        return this.createInfo(image, 0);
    }
    
    public NImageInfo createInfo(final NImage image, final int flags) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        NImageInfo value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatCreateInfo(this.getHandle(), image.getHandle(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImageInfo.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public NImageWriter openWriter(final String fileName) {
        return this.openWriter(fileName, 0);
    }
    
    public NImageWriter openWriter(final String fileName, final int flags) {
        NImageWriter value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatOpenWriterToFile(this.getHandle(), new WString(fileName), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImageWriter.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public NImageWriter openWriter() {
        return this.openWriter(0);
    }
    
    public NImageWriter openWriter(final int flags) {
        NImageWriter value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatOpenWriterToMemory(this.getHandle(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImageWriter.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public String getName() {
        final int vLen = NImageFormat.LIBRARY.NImageFormatGetNameEx(this.getHandle(), null, 0);
        if (NResult.isFailed(vLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(vLen);
        try {
            if (NResult.isFailed(NImageFormat.LIBRARY.NImageFormatGetNameEx(this.getHandle(), pValue, vLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getDefaultFileExtension() {
        final int vLen = NImageFormat.LIBRARY.NImageFormatGetDefaultFileExtensionEx(this.getHandle(), null, 0);
        if (NResult.isFailed(vLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(vLen);
        try {
            if (NResult.isFailed(NImageFormat.LIBRARY.NImageFormatGetDefaultFileExtensionEx(this.getHandle(), pValue, vLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getFileFilter() {
        final int vLen = NImageFormat.LIBRARY.NImageFormatGetFileFilterEx(this.getHandle(), null, 0);
        if (NResult.isFailed(vLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(vLen);
        try {
            if (NResult.isFailed(NImageFormat.LIBRARY.NImageFormatGetFileFilterEx(this.getHandle(), pValue, vLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public boolean isCanRead() {
        final BooleanByReference rValue = new BooleanByReference();
        NImageFormat.LIBRARY.NImageFormatCanRead(this.getHandle(), rValue);
        return rValue.getValue();
    }
    
    public boolean isCanWrite() {
        final BooleanByReference rValue = new BooleanByReference();
        NImageFormat.LIBRARY.NImageFormatCanWrite(this.getHandle(), rValue);
        return rValue.getValue();
    }
    
    public boolean isCanWriteMultiple() {
        final BooleanByReference rValue = new BooleanByReference();
        NImageFormat.LIBRARY.NImageFormatCanWriteMultiple(this.getHandle(), rValue);
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NImageFormatLibrary)Native.loadLibrary("NImages", NImageFormatLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        final HNObjectByReference rhTiff = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatGetTiff(rhTiff));
        NImageFormat.hTiff = rhTiff.getValue();
        NImageFormat.Tiff = (HNObject.NULL.equals(NImageFormat.hTiff) ? null : new NImageFormat(NImageFormat.hTiff));
        final HNObjectByReference rhJpeg = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatGetJpeg(rhJpeg));
        NImageFormat.hJpeg = rhJpeg.getValue();
        NImageFormat.Jpeg = (HNObject.NULL.equals(NImageFormat.hJpeg) ? null : new NImageFormat(NImageFormat.hJpeg));
        final HNObjectByReference rhPng = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatGetPng(rhPng));
        NImageFormat.hPng = rhPng.getValue();
        NImageFormat.Png = (HNObject.NULL.equals(NImageFormat.hPng) ? null : new NImageFormat(NImageFormat.hPng));
        final HNObjectByReference rhWsg = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatGetWsq(rhWsg));
        NImageFormat.hWsq = rhWsg.getValue();
        NImageFormat.Wsq = (HNObject.NULL.equals(NImageFormat.hWsq) ? null : new NImageFormat(NImageFormat.hWsq));
        final HNObjectByReference rhJpeg2K = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatGetJpeg2K(rhJpeg2K));
        NImageFormat.hJpeg2K = rhJpeg2K.getValue();
        NImageFormat.Jpeg2K = (HNObject.NULL.equals(NImageFormat.hJpeg2K) ? null : new NImageFormat(NImageFormat.hJpeg2K));
        final HNObjectByReference rhBmp = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatGetBmp(rhBmp));
        NImageFormat.hBmp = rhBmp.getValue();
        NImageFormat.Bmp = (HNObject.NULL.equals(NImageFormat.hBmp) ? null : new NImageFormat(NImageFormat.hBmp));
        final HNObjectByReference rhIHead = new HNObjectByReference();
        NResult.check(NImageFormat.LIBRARY.NImageFormatGetIHead(rhIHead));
        NImageFormat.hIHead = rhIHead.getValue();
        NImageFormat.IHead = (HNObject.NULL.equals(NImageFormat.hIHead) ? null : new NImageFormat(NImageFormat.hIHead));
        NImageFormat.formats = new ImageFormatCollection();
    }
    
    public enum FileAccess
    {
        READ(1), 
        WRITE(2), 
        READWRITE(3);
        
        private int value;
        
        private FileAccess(final int value) {
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
    }
    
    public static final class ImageFormatCollection extends NObjectStaticReadOnlyCollection<NImageFormat>
    {
        protected NImageFormat fromHandleNative(final HNObject handle) {
            return NImageFormat.fromHandle(handle);
        }
        
        protected NImageFormat fromHandleWithObject(final HNObject hItem, final NObject owner) {
            throw new UnsupportedOperationException();
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rValue = new HNObjectByReference();
            NResult.check(NImageFormat.LIBRARY.NImageFormatGetFormat(index, rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NImageFormat.LIBRARY.NImageFormatGetFormatCount(rValue));
            return rValue.getValue();
        }
    }
    
    interface NImageFormatLibrary extends NLibrary
    {
        int NImageFormatSelect(final WString p0, final int p1, final HNObjectByReference p2);
        
        int NImageFormatSelectReaderFromFile(final WString p0, final int p1, final HNObjectByReference p2);
        
        int NImageFormatSelectReaderFromMemory(final Buffer p0, final NativeSize p1, final int p2, final HNObjectByReference p3);
        
        int NImageFormatGetTiff(final HNObjectByReference p0);
        
        int NImageFormatGetJpeg(final HNObjectByReference p0);
        
        int NImageFormatGetPng(final HNObjectByReference p0);
        
        int NImageFormatGetWsq(final HNObjectByReference p0);
        
        int NImageFormatGetJpeg2K(final HNObjectByReference p0);
        
        int NImageFormatGetBmp(final HNObjectByReference p0);
        
        int NImageFormatGetIHead(final HNObjectByReference p0);
        
        int NImageFormatOpenReaderFromFile(final HNObject p0, final WString p1, final int p2, final HNObjectByReference p3);
        
        int NImageFormatOpenReaderFromMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final HNObjectByReference p4);
        
        int NImageFormatCreateInfo(final HNObject p0, final HNObject p1, final int p2, final HNObjectByReference p3);
        
        int NImageFormatOpenWriterToFile(final HNObject p0, final WString p1, final int p2, final HNObjectByReference p3);
        
        int NImageFormatOpenWriterToMemory(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NImageFormatGetFormatCount(final IntByReference p0);
        
        int NImageFormatGetFormat(final int p0, final HNObjectByReference p1);
        
        int NImageFormatGetNameEx(final HNObject p0, final Pointer p1, final int p2);
        
        int NImageFormatGetDefaultFileExtensionEx(final HNObject p0, final Pointer p1, final int p2);
        
        int NImageFormatGetFileFilterEx(final HNObject p0, final Pointer p1, final int p2);
        
        int NImageFormatCanRead(final HNObject p0, final BooleanByReference p1);
        
        int NImageFormatCanWrite(final HNObject p0, final BooleanByReference p1);
        
        int NImageFormatCanWriteMultiple(final HNObject p0, final BooleanByReference p1);
    }
}
