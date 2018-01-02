// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NObjectFlatCollection;
import com.neurotec.util.NObjectCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.util.UUIDData;
import java.util.UUID;
import com.sun.jna.WString;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.IntByReference;
import java.util.Iterator;
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

public final class IIRecord extends NObject implements Cloneable
{
    private IrisCollection irises;
    private IrisImageCollection irisImages;
    static final IIRecordLibrary LIBRARY;
    public static final int MAX_IRIS_IMAGE_COUNT = 65534;
    public static final NNativeType NATIVE_TYPE;
    
    private IIRecord(final HNObject handle, final boolean claimHandle) {
        super(handle, IIRecord.NATIVE_TYPE, claimHandle);
        this.irisImages = new IrisImageCollection(this);
        this.irises = new IrisCollection(this);
    }
    
    public IIRecord(final IirImageFormat imageFormat, final short rawImageWidth, final short rawImageHeight, final byte intensityDepth, final BdifStandard standard) {
        this(create(imageFormat, rawImageWidth, rawImageHeight, intensityDepth, 0, standard), true);
    }
    
    public IIRecord(final IirImageFormat imageFormat, final short rawImageWidth, final short rawImageHeight, final byte intensityDepth, final int flags, final BdifStandard standard) {
        this(create(imageFormat, rawImageWidth, rawImageHeight, intensityDepth, flags, standard), true);
    }
    
    public IIRecord(final ByteBuffer buffer, final BdifStandard standard) {
        this(create(buffer, 0, standard), true);
    }
    
    public IIRecord(final ByteBuffer buffer, final int flags, final BdifStandard standard) {
        this(create(buffer, flags, standard), true);
    }
    
    public IIRecord(final IIRecord srcRecord, final BdifStandard standard) {
        this(create(srcRecord, 0, standard), true);
    }
    
    public IIRecord(final IIRecord srcRecord, final int flags, final BdifStandard standard) {
        this(create(srcRecord, flags, standard), true);
    }
    
    public IIRecord(final NImage nImage, final IirImageFormat imageFormat, final BdifEyePosition irisPosition, final BdifStandard standard) {
        this(create(nImage, imageFormat, irisPosition, 0, standard), true);
    }
    
    public IIRecord(final NImage nImage, final IirImageFormat imageFormat, final BdifEyePosition irisPosition, final int flags, final BdifStandard standard) {
        this(create(nImage, imageFormat, irisPosition, flags, standard), true);
    }
    
    private static HNObject create(final IirImageFormat imageFormat, final short rawImageWidth, final short rawImageHeight, final byte intensityDepth, final int flags, final BdifStandard standard) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordCreate(imageFormat.getValue(), rawImageWidth, rawImageHeight, intensityDepth, flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags, final BdifStandard standard) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, standard.getValue(), rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    private static HNObject create(final IIRecord srcRecord, final int flags, final BdifStandard standard) {
        if (srcRecord == null) {
            throw new NullPointerException("srcRecord");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordCreateFromIIRecord(srcRecord.getHandle(), flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final NImage nImage, final IirImageFormat imageFormat, final BdifEyePosition irisPosition, final int flags, final BdifStandard standard) {
        if (nImage == null) {
            throw new NullPointerException("nImage");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordCreateFromNImage(nImage.getHandle(), imageFormat.getValue(), irisPosition.getValue(), flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    public static int calculateSize(final BdifStandard standard, int[] irisSizes) {
        if (irisSizes == null) {
            irisSizes = new int[0];
        }
        final int irisCount = irisSizes.length;
        final NativeSize[] irImagesSizes = new NativeSize[irisCount];
        for (int i = 0; i != irisCount; ++i) {
            if (irisSizes[i] < 0) {
                throw new IllegalArgumentException("One of irisSizes is less than zero");
            }
            irImagesSizes[i] = new NativeSize((long)i);
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordCalculateSize(standard.getValue(), irisCount, irImagesSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public static IIRecord fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static IIRecord fromHandle(final HNObject handle, final boolean ownsHandle) {
        final IIRecord value = new IIRecord(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private int getIrisImagesStartIndex(final IirIris iris) {
        int index = 0;
        for (final IirIris i : this.irises) {
            if (i == iris) {
                break;
            }
            index += i.getIrisImages().size();
        }
        return index;
    }
    
    void onInsertIrisImage(final IirIris iris, final int index, final IirIrisImage value) {
        this.irisImages.onInsert(this.getIrisImagesStartIndex(iris) + index, value);
    }
    
    void onRemoveIrisImageRange(final IirIris iris, final int index, final int count) {
        this.irisImages.onRemoveRange(this.getIrisImagesStartIndex(iris) + index, count);
    }
    
    public int getSize() {
        return this.getSize(0);
    }
    
    public int getSize(final int flags) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetSize(this.getHandle(), flags, rValue));
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
            throw new RuntimeException("Packed and calculated size of IIRecord do not match");
        }
        buffer.flip();
        return buffer;
    }
    
    public int save(final ByteBuffer buffer) {
        return this.save(buffer, 0);
    }
    
    public int save(final ByteBuffer buffer, final int flags) {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public BdifStandard getStandard() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetStandard(this.getHandle(), rValue));
        return BdifStandard.get(rValue.getValue());
    }
    
    public int getCbeffProductId() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetCbeffProductId(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setCbeffProductId(final int value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetCbeffProductId(this.getHandle(), value));
    }
    
    public short getCaptureDeviceId() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetCaptureDeviceId(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setCaptureDeviceId(final short value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetCaptureDeviceId(this.getHandle(), value));
    }
    
    public BdifIrisOrientation getIrisHorzOrientation() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetIrisHorzOrientation(this.getHandle(), rValue));
        return BdifIrisOrientation.get(rValue.getValue());
    }
    
    public void setIrisHorzOrientation(final BdifIrisOrientation value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetIrisHorzOrientation(this.getHandle(), value.getValue()));
    }
    
    public BdifIrisOrientation getIrisVertOrientation() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetIrisVertOrientation(this.getHandle(), rValue));
        return BdifIrisOrientation.get(rValue.getValue());
    }
    
    public void setIrisVertOrientation(final BdifIrisOrientation value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetIrisVertOrientation(this.getHandle(), value.getValue()));
    }
    
    public BdifIrisScanType getIrisScanType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetIrisScanType(this.getHandle(), rValue));
        return BdifIrisScanType.get(rValue.getValue());
    }
    
    public void setIrisScanType(final BdifIrisScanType value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetIrisScanType(this.getHandle(), value.getValue()));
    }
    
    public IirIrisOcclusions getIrisOcclusions() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetIrisOcclusions(this.getHandle(), rValue));
        return IirIrisOcclusions.get(rValue.getValue());
    }
    
    public void setIrisOcclusions(final IirIrisOcclusions value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetIrisOcclusions(this.getHandle(), value.getValue()));
    }
    
    public IirIrisOcclusionFilling getIrisOcclusionFilling() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetIrisOcclusionFilling(this.getHandle(), rValue));
        return IirIrisOcclusionFilling.get(rValue.getValue());
    }
    
    public void setIrisOcclusionFilling(final IirIrisOcclusionFilling value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetIrisOcclusionFilling(this.getHandle(), value.getValue()));
    }
    
    public IirIrisBoundary getIrisBoundaryExtraction() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetIrisBoundaryExtraction(this.getHandle(), rValue));
        return IirIrisBoundary.get(rValue.getValue());
    }
    
    public void setIrisBoundaryExtraction(final IirIrisBoundary value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetIrisBoundaryExtraction(this.getHandle(), value.getValue()));
    }
    
    public short getIrisDiameter() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetIrisDiameter(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setIrisDiameter(final short value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetIrisDiameter(this.getHandle(), value));
    }
    
    public IirImageFormat getImageFormat() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetImageFormat(this.getHandle(), rValue));
        return IirImageFormat.get(rValue.getValue());
    }
    
    public short getRawImageWidth() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetRawImageWidth(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getRawImageHeight() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetRawImageHeight(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public byte getIntensityDepth() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetIntensityDepth(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public IirImageTransformation getImageTransformation() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IIRecord.LIBRARY.IIRecordGetImageTransformation(this.getHandle(), rValue));
        return IirImageTransformation.get(rValue.getValue());
    }
    
    public void setImageTransformation(final IirImageTransformation value) {
        NResult.check(IIRecord.LIBRARY.IIRecordSetImageTransformation(this.getHandle(), value.getValue()));
    }
    
    public String getDeviceUniqueIdentifier() {
        final int mLen = IIRecord.LIBRARY.IIRecordGetDeviceUniqueIdentifierEx(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(IIRecord.LIBRARY.IIRecordGetDeviceUniqueIdentifierEx(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setDeviceUniqueIdentifier(final String value) {
        final WString wValue = new WString(value);
        NResult.check(IIRecord.LIBRARY.IIRecordSetDeviceUniqueIdentifier(this.getHandle(), wValue));
    }
    
    public UUID getUuid() {
        final UUIDData rValue = new UUIDData();
        NResult.check(IIRecord.LIBRARY.IIRecordGetGuid(this.getHandle(), rValue));
        return new UUID(rValue.leastSigBits, rValue.mostSigBits);
    }
    
    public void setUuid(final UUID value) {
        final UUIDData rValue = new UUIDData(value.getLeastSignificantBits(), value.getMostSignificantBits());
        NResult.check(IIRecord.LIBRARY.IIRecordSetGuid(this.getHandle(), rValue));
    }
    
    public IrisCollection getIrises() {
        this.check();
        return this.irises;
    }
    
    public IrisImageCollection getIrisImages() {
        this.check();
        return this.irisImages;
    }
    
    public Object clone() throws CloneNotSupportedException {
        IIRecord value = null;
        HNObject rValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(IIRecord.LIBRARY.IIRecordClone(this.getHandle(), rhValue));
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
            this.irises.disposeItems();
            this.irisImages.disposeItems();
        }
        this.irises = null;
        this.irisImages = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (IIRecordLibrary)Native.loadLibrary("NBiometricStandards", IIRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(IIRecord.LIBRARY.IIRecordTypeOf());
    }
    
    public final class IrisCollection extends NObjectCollection<IirIris>
    {
        IrisCollection(final IIRecord owner) {
            super(owner, false);
            final int count = this.size();
            int irisImagesStartIndex = 0;
            for (int i = 0; i < count; ++i) {
                final IirIris iris = this.get(i);
                this.fillIrisImages(irisImagesStartIndex, iris);
                irisImagesStartIndex += iris.getIrisImages().size();
            }
        }
        
        private void fillIrisImages(final int irisImagesStartIndex, final IirIris item) {
            for (int irisImagesCount = item.getIrisImages().size(), i = 0; i < irisImagesCount; ++i) {
                final IirIrisImage image = item.getIrisImages().get(i);
                ((IIRecord)this.getOwner()).getIrisImages().onInsert(irisImagesStartIndex + i, image);
            }
        }
        
        protected void onInsertItem(final int index, final IirIris item) {
            super.onInsertItem(index, item);
            this.fillIrisImages(((IIRecord)this.getOwner()).getIrisImagesStartIndex(item), item);
        }
        
        protected void onRemoveItemRange(final int index, final int count) {
            for (int i = count - 1; i >= 0; --i) {
                final IirIris iris = this.get(index + i);
                final int irisImagesStartIndex = ((IIRecord)this.getOwner()).getIrisImagesStartIndex(iris);
                final int irisImagesCount = iris.getIrisImages().size();
                ((IIRecord)this.getOwner()).irisImages.onRemoveRange(irisImagesStartIndex, irisImagesCount);
            }
            super.onRemoveItemRange(index, count);
        }
        
        public IirIris add(final BdifEyePosition irisPosition) {
            return this.add(irisPosition, 0);
        }
        
        public IirIris add(final BdifEyePosition irisPosition, final int flags) {
            IirIris value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(IIRecord.LIBRARY.IIRecordAddIris(this.getOwner().getHandle(), irisPosition.getValue(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    IIRecord.LIBRARY.IIRecordRemoveIris(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public IirIris add(final BdifEyePosition irisPosition, final NImage nImage) {
            return this.add(irisPosition, nImage, 0);
        }
        
        public IirIris add(final BdifEyePosition irisPosition, final NImage nImage, final int flags) {
            IirIris value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(IIRecord.LIBRARY.IIRecordAddIrisFromNImage(this.getOwner().getHandle(), irisPosition.getValue(), nImage.getHandle(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    IIRecord.LIBRARY.IIRecordRemoveIris(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        protected void clearNative() {
            NResult.check(IIRecord.LIBRARY.IIRecordClearIrises(this.getOwner().getHandle()));
        }
        
        protected IirIris fromHandleNative(final HNObject handle) {
            return new IirIris(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(IIRecord.LIBRARY.IIRecordGetIris(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(IIRecord.LIBRARY.IIRecordGetIrisCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(IIRecord.LIBRARY.IIRecordSetIrisCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(IIRecord.LIBRARY.IIRecordGetIrisCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(IIRecord.LIBRARY.IIRecordRemoveIris(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    public final class IrisImageCollection extends NObjectFlatCollection<IirIrisImage>
    {
        IrisImageCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        void onInsert(final int index, final IirIrisImage value) {
            this.insertItem(index, value);
        }
        
        void onRemoveRange(final int index, final int count) {
            this.removeItemRange(index, count);
        }
        
        protected void onRemoveItemRange(final int index, final int count) {
            if (count != 0) {
                int i = count - 1;
                IirIrisImage lastIrisImage = this.get(index + i);
                IirIris lastIris = lastIrisImage.getOwner();
                int lastIndex = lastIris.getIrisImages().indexOf(lastIrisImage);
                while (i >= -1) {
                    final IirIrisImage theIrisImage = (i == -1) ? null : this.get(index + i);
                    final IirIris theIris = (theIrisImage == null) ? null : theIrisImage.getOwner();
                    if (theIris != lastIris) {
                        final IirIrisImage firstIrisImage = this.get(index + i + 1);
                        final int firstIndex = lastIris.getIrisImages().indexOf(firstIrisImage);
                        lastIris.getIrisImages().onRemoveRange(firstIndex, lastIndex - firstIndex + 1);
                        if (theIris != null) {
                            lastIrisImage = theIrisImage;
                            lastIris = theIris;
                            lastIndex = lastIris.getIrisImages().indexOf(lastIrisImage);
                        }
                    }
                    --i;
                }
            }
            super.onRemoveItemRange(index, count);
        }
        
        protected void clearNative() {
            NResult.check(IIRecord.LIBRARY.IIRecordClearIrisImages(this.getOwner().getHandle()));
        }
        
        protected IirIrisImage fromHandleNative(final HNObject handle) {
            throw new UnsupportedOperationException();
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(IIRecord.LIBRARY.IIRecordGetIrisImage(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(IIRecord.LIBRARY.IIRecordGetIrisImageCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(IIRecord.LIBRARY.IIRecordRemoveIrisImage(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
        
        protected void addNative(final IirIrisImage value) {
            throw new UnsupportedOperationException();
        }
        
        protected void addNative(final int index, final IirIrisImage value) {
            throw new UnsupportedOperationException();
        }
    }
    
    interface IIRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType IIRecordTypeOf();
        
        int IIRecordCalculateSize(final int p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int IIRecordCreate(final int p0, final short p1, final short p2, final byte p3, final int p4, final int p5, final HNObjectByReference p6);
        
        int IIRecordCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int IIRecordCreateFromIIRecord(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int IIRecordCreateFromNImage(final HNObject p0, final int p1, final int p2, final int p3, final int p4, final HNObjectByReference p5);
        
        int IIRecordClone(final HNObject p0, final HNObjectByReference p1);
        
        int IIRecordGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int IIRecordSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int IIRecordGetStandard(final HNObject p0, final IntByReference p1);
        
        int IIRecordGetCbeffProductId(final HNObject p0, final IntByReference p1);
        
        int IIRecordSetCbeffProductId(final HNObject p0, final int p1);
        
        int IIRecordGetCaptureDeviceId(final HNObject p0, final ShortByReference p1);
        
        int IIRecordSetCaptureDeviceId(final HNObject p0, final short p1);
        
        int IIRecordGetIrisHorzOrientation(final HNObject p0, final IntByReference p1);
        
        int IIRecordSetIrisHorzOrientation(final HNObject p0, final int p1);
        
        int IIRecordGetIrisVertOrientation(final HNObject p0, final IntByReference p1);
        
        int IIRecordSetIrisVertOrientation(final HNObject p0, final int p1);
        
        int IIRecordGetIrisScanType(final HNObject p0, final IntByReference p1);
        
        int IIRecordSetIrisScanType(final HNObject p0, final int p1);
        
        int IIRecordGetIrisOcclusions(final HNObject p0, final IntByReference p1);
        
        int IIRecordSetIrisOcclusions(final HNObject p0, final int p1);
        
        int IIRecordGetIrisOcclusionFilling(final HNObject p0, final IntByReference p1);
        
        int IIRecordSetIrisOcclusionFilling(final HNObject p0, final int p1);
        
        int IIRecordGetIrisBoundaryExtraction(final HNObject p0, final IntByReference p1);
        
        int IIRecordSetIrisBoundaryExtraction(final HNObject p0, final int p1);
        
        int IIRecordGetIrisDiameter(final HNObject p0, final ShortByReference p1);
        
        int IIRecordSetIrisDiameter(final HNObject p0, final short p1);
        
        int IIRecordGetImageFormat(final HNObject p0, final IntByReference p1);
        
        int IIRecordGetRawImageWidth(final HNObject p0, final ShortByReference p1);
        
        int IIRecordGetRawImageHeight(final HNObject p0, final ShortByReference p1);
        
        int IIRecordGetIntensityDepth(final HNObject p0, final ByteByReference p1);
        
        int IIRecordGetImageTransformation(final HNObject p0, final IntByReference p1);
        
        int IIRecordSetImageTransformation(final HNObject p0, final int p1);
        
        int IIRecordGetDeviceUniqueIdentifierEx(final HNObject p0, final Pointer p1, final int p2);
        
        int IIRecordSetDeviceUniqueIdentifier(final HNObject p0, final WString p1);
        
        int IIRecordGetGuid(final HNObject p0, final UUIDData p1);
        
        int IIRecordSetGuid(final HNObject p0, final UUIDData p1);
        
        int IIRecordAddIris(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int IIRecordAddIrisFromNImage(final HNObject p0, final int p1, final HNObject p2, final int p3, final HNObjectByReference p4);
        
        int IIRecordGetIrisCount(final HNObject p0, final IntByReference p1);
        
        int IIRecordGetIris(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int IIRecordGetIrisCapacity(final HNObject p0, final IntByReference p1);
        
        int IIRecordSetIrisCapacity(final HNObject p0, final int p1);
        
        int IIRecordRemoveIris(final HNObject p0, final int p1);
        
        int IIRecordClearIrises(final HNObject p0);
        
        int IIRecordGetIrisImageCount(final HNObject p0, final IntByReference p1);
        
        int IIRecordGetIrisImage(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int IIRecordRemoveIrisImage(final HNObject p0, final int p1);
        
        int IIRecordClearIrisImages(final HNObject p0);
    }
}
