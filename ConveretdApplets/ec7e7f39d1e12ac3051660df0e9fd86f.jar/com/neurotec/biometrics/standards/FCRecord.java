// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NObjectCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.IntByReference;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.images.NImage;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class FCRecord extends NObject implements Cloneable
{
    private FaceImageCollection faceImages;
    static final FCRecordLibrary LIBRARY;
    public static final int MAX_FACE_IMAGE_COUNT = 32767;
    public static final int FLAG_PROCESS_FIRST_FACE_IMAGE_ONLY = 256;
    public static final NNativeType NATIVE_TYPE;
    
    private FCRecord(final HNObject handle, final boolean claimHandle) {
        super(handle, FCRecord.NATIVE_TYPE, claimHandle);
        this.faceImages = new FaceImageCollection(this);
    }
    
    public FCRecord(final BdifStandard standard) {
        this(create(0, standard), true);
    }
    
    public FCRecord(final int flags, final BdifStandard standard) {
        this(create(flags, standard), true);
    }
    
    public FCRecord(final ByteBuffer buffer, final BdifStandard standard) {
        this(create(buffer, 0, standard), true);
    }
    
    public FCRecord(final ByteBuffer buffer, final int flags, final BdifStandard standard) {
        this(create(buffer, flags, standard), true);
    }
    
    public FCRecord(final FCRecord srcRecord, final int flags, final BdifStandard standard) {
        this(create(srcRecord, flags, standard), true);
    }
    
    public FCRecord(final NImage nImage, final FcrFaceImageType faceImageType, final FcrImageDataType imageDataType, final BdifStandard standard) {
        this(create(nImage, faceImageType, imageDataType, 0, standard), true);
    }
    
    public FCRecord(final NImage nImage, final FcrFaceImageType faceImageType, final FcrImageDataType imageDataType, final int flags, final BdifStandard standard) {
        this(create(nImage, faceImageType, imageDataType, flags, standard), true);
    }
    
    private static HNObject create(final int flags, final BdifStandard standard) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FCRecord.LIBRARY.FCRecordCreate(flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags, final BdifStandard standard) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(FCRecord.LIBRARY.FCRecordCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, standard.getValue(), rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    private static HNObject create(final FCRecord srcRecord, final int flags, final BdifStandard standard) {
        if (srcRecord == null) {
            throw new NullPointerException("srcRecord");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FCRecord.LIBRARY.FCRecordCreateFromFCRecord(srcRecord.getHandle(), flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final NImage nImage, final FcrFaceImageType faceImageType, final FcrImageDataType imageDataType, final int flags, final BdifStandard standard) {
        if (nImage == null) {
            throw new NullPointerException("nImage");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FCRecord.LIBRARY.FCRecordCreateFromNImage(nImage.getHandle(), faceImageType.getValue(), imageDataType.getValue(), flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    public static int calculateSize(final BdifStandard standard, int[] faceImageSizes) {
        if (faceImageSizes == null) {
            faceImageSizes = new int[0];
        }
        final int faceImageCount = faceImageSizes.length;
        final NativeSize[] faceImgSizes = new NativeSize[faceImageCount];
        for (int i = 0; i != faceImageCount; ++i) {
            if (faceImageSizes[i] < 0) {
                throw new IllegalArgumentException("One of faceImageSizes is less than zero");
            }
            faceImgSizes[i] = new NativeSize((long)i);
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FCRecord.LIBRARY.FCRecordCalculateSize(standard.getValue(), faceImageCount, faceImgSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public static FCRecord fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static FCRecord fromHandle(final HNObject handle, final boolean ownsHandle) {
        final FCRecord value = new FCRecord(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public int getSize() {
        return this.getSize(0);
    }
    
    public int getSize(final int flags) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FCRecord.LIBRARY.FCRecordGetSize(this.getHandle(), flags, rValue));
        return rValue.getValue().intValue();
    }
    
    public ByteBuffer save() {
        return this.save(0);
    }
    
    public ByteBuffer save(final int flags) {
        final int size = this.getSize(flags);
        final ByteBuffer buffer = ByteBuffer.allocateDirect(size);
        buffer.limit(size);
        if (this.save(buffer, flags) != size) {
            throw new RuntimeException("Packed and calculated size of FCRecord do not match");
        }
        buffer.flip();
        return buffer;
    }
    
    public int save(final ByteBuffer buffer) {
        return this.save(buffer, 0);
    }
    
    public int save(final ByteBuffer buffer, final int flags) {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(FCRecord.LIBRARY.FCRecordSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public BdifStandard getStandard() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FCRecord.LIBRARY.FCRecordGetStandard(this.getHandle(), rValue));
        return BdifStandard.get(rValue.getValue());
    }
    
    public FaceImageCollection getFaceImages() {
        this.check();
        return this.faceImages;
    }
    
    public Object clone() throws CloneNotSupportedException {
        FCRecord value = null;
        HNObject rValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(FCRecord.LIBRARY.FCRecordClone(this.getHandle(), rhValue));
            rValue = rhValue.getValue();
            value = fromHandle(rValue);
        }
        finally {
            if (value == null) {
                NObject.free(rValue);
            }
        }
        return value;
    }
    
    protected void dispose(final boolean disposing) {
        if (disposing) {
            this.faceImages.disposeItems();
        }
        this.faceImages = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (FCRecordLibrary)Native.loadLibrary("NBiometricStandards", FCRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(FCRecord.LIBRARY.FCRecordTypeOf());
    }
    
    public final class FaceImageCollection extends NObjectCollection<FcrFaceImage>
    {
        FaceImageCollection(final NObject owner) {
            super(owner, false);
        }
        
        public FcrFaceImage add(final FcrFaceImageType faceImageType, final FcrImageDataType imageDataType, final short width, final short height, final FcrImageColorSpace imageColorSpace, final byte vendorImageColorSpace, final ByteBuffer imageData) {
            return this.add(faceImageType, imageDataType, width, height, imageColorSpace, vendorImageColorSpace, imageData, 0);
        }
        
        public FcrFaceImage add(final FcrFaceImageType faceImageType, final FcrImageDataType imageDataType, final short width, final short height, final FcrImageColorSpace imageColorSpace, final byte vendorImageColorSpace, final ByteBuffer imageData, final int flags) {
            if (imageData == null) {
                throw new NullPointerException("imageData");
            }
            FcrFaceImage value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FCRecord.LIBRARY.FCRecordAddFaceImage(this.getOwner().getHandle(), faceImageType.getValue(), imageDataType.getValue(), width, height, imageColorSpace.getValue(), vendorImageColorSpace, imageData.slice(), new NativeSize((long)imageData.remaining()), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    FCRecord.LIBRARY.FCRecordRemoveFaceImage(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public FcrFaceImage add(final FcrFaceImageType faceImageType, final FcrImageDataType imageDataType, final NImage nImage, final int flags) {
            if (nImage == null) {
                throw new NullPointerException("nImage");
            }
            FcrFaceImage value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FCRecord.LIBRARY.FCRecordAddFaceImageFromNImage(this.getOwner().getHandle(), nImage.getHandle(), faceImageType.getValue(), imageDataType.getValue(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    FCRecord.LIBRARY.FCRecordRemoveFaceImage(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public FcrFaceImage add(final FcrFaceImageType faceImageType, final FcrImageDataType imageDataType, final NImage nImage) {
            return this.add(faceImageType, imageDataType, nImage, 0);
        }
        
        protected void clearNative() {
            NResult.check(FCRecord.LIBRARY.FCRecordClearFaceImages(this.getOwner().getHandle()));
        }
        
        protected FcrFaceImage fromHandleNative(final HNObject handle) {
            return new FcrFaceImage(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FCRecord.LIBRARY.FCRecordGetFaceImage(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FCRecord.LIBRARY.FCRecordGetFaceImageCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(FCRecord.LIBRARY.FCRecordSetFaceImageCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FCRecord.LIBRARY.FCRecordGetFaceImageCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FCRecord.LIBRARY.FCRecordRemoveFaceImage(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface FCRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType FCRecordTypeOf();
        
        int FCRecordCalculateSize(final int p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int FCRecordCreate(final int p0, final int p1, final HNObjectByReference p2);
        
        int FCRecordCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int FCRecordCreateFromFCRecord(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int FCRecordCreateFromNImage(final HNObject p0, final int p1, final int p2, final int p3, final int p4, final HNObjectByReference p5);
        
        int FCRecordClone(final HNObject p0, final HNObjectByReference p1);
        
        int FCRecordGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int FCRecordSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int FCRecordGetStandard(final HNObject p0, final IntByReference p1);
        
        int FCRecordGetFaceImageCount(final HNObject p0, final IntByReference p1);
        
        int FCRecordGetFaceImage(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FCRecordGetFaceImageCapacity(final HNObject p0, final IntByReference p1);
        
        int FCRecordSetFaceImageCapacity(final HNObject p0, final int p1);
        
        int FCRecordAddFaceImage(final HNObject p0, final int p1, final int p2, final short p3, final short p4, final int p5, final byte p6, final Buffer p7, final NativeSize p8, final int p9, final HNObjectByReference p10);
        
        int FCRecordAddFaceImageFromNImage(final HNObject p0, final HNObject p1, final int p2, final int p3, final int p4, final HNObjectByReference p5);
        
        int FCRecordRemoveFaceImage(final HNObject p0, final int p1);
        
        int FCRecordClearFaceImages(final HNObject p0);
    }
}
