// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NObjectFlatCollection;
import com.neurotec.util.NObjectCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
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

public final class FIRecord extends NObject implements Cloneable
{
    private FingerCollection fingers;
    private FingerViewCollection fingerViews;
    static final FIRecordLibrary LIBRARY;
    public static final int MAX_FACE_IMAGE_COUNT = 32767;
    public static final int FLAG_PROCESS_FIRST_FACE_IMAGE_ONLY = 256;
    public static final NNativeType NATIVE_TYPE;
    
    private FIRecord(final HNObject handle, final boolean claimHandle) {
        super(handle, FIRecord.NATIVE_TYPE, claimHandle);
        this.fingerViews = new FingerViewCollection(this);
        this.fingers = new FingerCollection(this);
    }
    
    public FIRecord(final short imageAcquisitionLevel, final BdifScaleUnits scaleUnits, final short horzScanResolution, final short vertScanResolution, final short horzImageResolution, final short vertImageResolution, final byte pixelDepth, final FirImageCompressionAlgorithm imageCompressionAlgorithm, final BdifStandard standard) {
        this(create(imageAcquisitionLevel, scaleUnits, horzScanResolution, vertScanResolution, horzImageResolution, vertImageResolution, pixelDepth, imageCompressionAlgorithm, 0, standard), true);
    }
    
    public FIRecord(final short imageAcquisitionLevel, final BdifScaleUnits scaleUnits, final short horzScanResolution, final short vertScanResolution, final short horzImageResolution, final short vertImageResolution, final byte pixelDepth, final FirImageCompressionAlgorithm imageCompressionAlgorithm, final int flags, final BdifStandard standard) {
        this(create(imageAcquisitionLevel, scaleUnits, horzScanResolution, vertScanResolution, horzImageResolution, vertImageResolution, pixelDepth, imageCompressionAlgorithm, flags, standard), true);
    }
    
    public FIRecord(final ByteBuffer buffer, final BdifStandard standard) {
        this(create(buffer, 0, standard), true);
    }
    
    public FIRecord(final ByteBuffer buffer, final int flags, final BdifStandard standard) {
        this(create(buffer, flags, standard), true);
    }
    
    public FIRecord(final FIRecord srcRecord, final BdifStandard standard) {
        this(create(srcRecord, 0, standard), true);
    }
    
    public FIRecord(final FIRecord srcRecord, final int flags, final BdifStandard standard) {
        this(create(srcRecord, flags, standard), true);
    }
    
    public FIRecord(final NImage nImage, final short imageAcquisitionLevel, final BdifScaleUnits scaleUnits, final short horzScanResolution, final short vertScanResolution, final byte pixelDepth, final FirImageCompressionAlgorithm imageCompressionAlgorithm, final BdifFPPosition fingerPosition, final BdifStandard standard) {
        this(create(nImage, imageAcquisitionLevel, scaleUnits, horzScanResolution, vertScanResolution, pixelDepth, imageCompressionAlgorithm, fingerPosition, 0, standard), true);
    }
    
    public FIRecord(final NImage nImage, final short imageAcquisitionLevel, final BdifScaleUnits scaleUnits, final short horzScanResolution, final short vertScanResolution, final byte pixelDepth, final FirImageCompressionAlgorithm imageCompressionAlgorithm, final BdifFPPosition fingerPosition, final int flags, final BdifStandard standard) {
        this(create(nImage, imageAcquisitionLevel, scaleUnits, horzScanResolution, vertScanResolution, pixelDepth, imageCompressionAlgorithm, fingerPosition, flags, standard), true);
    }
    
    private static HNObject create(final short imageAcquisitionLevel, final BdifScaleUnits scaleUnits, final short horzScanResolution, final short vertScanResolution, final short horzImageResolution, final short vertImageResolution, final byte pixelDepth, final FirImageCompressionAlgorithm imageCompressionAlgorithm, final int flags, final BdifStandard standard) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordCreate(imageAcquisitionLevel, scaleUnits.getValue(), horzScanResolution, vertScanResolution, horzImageResolution, vertImageResolution, pixelDepth, imageCompressionAlgorithm.getValue(), flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags, final BdifStandard standard) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, standard.getValue(), rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    private static HNObject create(final FIRecord srcRecord, final int flags, final BdifStandard standard) {
        if (srcRecord == null) {
            throw new NullPointerException("srcRecord");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordCreateFromFIRecord(srcRecord.getHandle(), flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final NImage nImage, final short imageAcquisitionLevel, final BdifScaleUnits scaleUnits, final short horzScanResolution, final short vertScanResolution, final byte pixelDepth, final FirImageCompressionAlgorithm imageCompressionAlgorithm, final BdifFPPosition fingerPosition, final int flags, final BdifStandard standard) {
        if (nImage == null) {
            throw new NullPointerException("nImage");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordCreateFromNImage(nImage.getHandle(), imageAcquisitionLevel, scaleUnits.getValue(), horzScanResolution, vertScanResolution, pixelDepth, imageCompressionAlgorithm.getValue(), fingerPosition.getValue(), flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    public static int calculateSize(final BdifStandard standard, int[] fingerViewSizes) {
        if (fingerViewSizes == null) {
            fingerViewSizes = new int[0];
        }
        final int fingerViewCount = fingerViewSizes.length;
        final NativeSize[] finViewSizes = new NativeSize[fingerViewCount];
        for (int i = 0; i != fingerViewCount; ++i) {
            if (fingerViewSizes[i] < 0) {
                throw new IllegalArgumentException("One of fingerViewSizes is less than zero");
            }
            finViewSizes[i] = new NativeSize((long)i);
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordCalculateSize(standard.getValue(), fingerViewCount, finViewSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public static int calculateSizeWithFingers(final BdifStandard standard, int[] fingerSizes) {
        if (fingerSizes == null) {
            fingerSizes = new int[0];
        }
        final int fingerCount = fingerSizes.length;
        final NativeSize[] finSizes = new NativeSize[fingerCount];
        for (int i = 0; i != fingerCount; ++i) {
            if (fingerSizes[i] < 0) {
                throw new IllegalArgumentException("One of fingerSizes is less than zero");
            }
            finSizes[i] = new NativeSize((long)i);
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordCalculateSizeWithFingers(standard.getValue(), fingerCount, finSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public static FIRecord fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static FIRecord fromHandle(final HNObject handle, final boolean ownsHandle) {
        final FIRecord value = new FIRecord(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private int getFingerViewsStartIndex(final FirFinger finger) {
        int index = 0;
        for (final FirFinger f : this.fingers) {
            if (f == finger) {
                break;
            }
            index += f.getFingerViews().size();
        }
        return index;
    }
    
    public int getSize() {
        return this.getSize(0);
    }
    
    public int getSize(final int flags) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetSize(this.getHandle(), flags, rValue));
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
            throw new RuntimeException("Packed and calculated size of FIRecord do not match");
        }
        buffer.flip();
        return buffer;
    }
    
    public int save(final ByteBuffer buffer) {
        return this.save(buffer, 0);
    }
    
    public int save(final ByteBuffer buffer, final int flags) {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public BdifStandard getStandard() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetStandard(this.getHandle(), rValue));
        return BdifStandard.get(rValue.getValue());
    }
    
    public int getCbeffProductId() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetCbeffProductId(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setCbeffProductId(final int value) {
        NResult.check(FIRecord.LIBRARY.FIRecordSetCbeffProductId(this.getHandle(), value));
    }
    
    public short getCaptureDeviceId() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetCaptureDeviceId(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setCaptureDeviceId(final short value) {
        NResult.check(FIRecord.LIBRARY.FIRecordSetCaptureDeviceId(this.getHandle(), value));
    }
    
    public short getImageAcquisitionLevel() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetImageAcquisitionLevel(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public BdifScaleUnits getScaleUnits() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetScaleUnits(this.getHandle(), rValue));
        return BdifScaleUnits.get(rValue.getValue());
    }
    
    public short getHorzScanResolution() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetHorzScanResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getVertScanResolution() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetVertScanResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getHorzImageResolution() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetHorzImageResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getVertImageResolution() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetVertImageResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public byte getPixelDepth() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetPixelDepth(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public FirImageCompressionAlgorithm getImageCompressionAlgorithm() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FIRecord.LIBRARY.FIRecordGetImageCompressionAlgorithm(this.getHandle(), rValue));
        return FirImageCompressionAlgorithm.get(rValue.getValue());
    }
    
    public FingerCollection getFingers() {
        this.check();
        return this.fingers;
    }
    
    public FingerViewCollection getFingerViews() {
        this.check();
        return this.fingerViews;
    }
    
    public Object clone() throws CloneNotSupportedException {
        FIRecord value = null;
        HNObject rValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(FIRecord.LIBRARY.FIRecordClone(this.getHandle(), rhValue));
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
            this.fingers.disposeItems();
        }
        this.fingers = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (FIRecordLibrary)Native.loadLibrary("NBiometricStandards", FIRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(FIRecord.LIBRARY.FIRecordTypeOf());
    }
    
    public final class FingerCollection extends NObjectCollection<FirFinger>
    {
        FingerCollection(final FIRecord owner) {
            super(owner, false);
            final int count = this.size();
            int fingerViewsStartIndex = 0;
            for (int i = 0; i < count; ++i) {
                final FirFinger finger = this.get(i);
                this.fillFingerViews(fingerViewsStartIndex, finger);
                fingerViewsStartIndex += finger.getFingerViews().size();
            }
        }
        
        private void fillFingerViews(final int fingerViewsStartIndex, final FirFinger item) {
            for (int fingerViewsCount = item.getFingerViews().size(), i = 0; i < fingerViewsCount; ++i) {
                ((FIRecord)this.getOwner()).getFingerViews().onInsert(fingerViewsStartIndex + i, item.getFingerViews().get(i));
            }
        }
        
        protected void onInsertItem(final int index, final FirFinger item) {
            super.onInsertItem(index, item);
            this.fillFingerViews(((FIRecord)this.getOwner()).getFingerViewsStartIndex(item), item);
        }
        
        protected void onRemoveItemRange(final int index, final int count) {
            for (int i = count - 1; i >= 0; --i) {
                final FirFinger finger = this.get(index + i);
                final int fingerViewsStartIndex = ((FIRecord)this.getOwner()).getFingerViewsStartIndex(finger);
                final int fingerViewsCount = finger.getFingerViews().size();
                ((FIRecord)this.getOwner()).fingerViews.onRemoveRange(fingerViewsStartIndex, fingerViewsCount);
            }
            super.onRemoveItemRange(index, count);
        }
        
        public FirFinger add(final BdifFPPosition fingerPosition) {
            return this.add(fingerPosition, 0);
        }
        
        public FirFinger add(final BdifFPPosition fingerPosition, final int flags) {
            FirFinger value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FIRecord.LIBRARY.FIRecordAddFinger(this.getOwner().getHandle(), fingerPosition.getValue(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    FIRecord.LIBRARY.FIRecordRemoveFinger(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public FirFinger add(final BdifFPPosition fingerPosition, final NImage nImage) {
            return this.add(fingerPosition, nImage, 0);
        }
        
        public FirFinger add(final BdifFPPosition fingerPosition, final NImage nImage, final int flags) {
            FirFinger value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FIRecord.LIBRARY.FIRecordAddFingerFromNImage(this.getOwner().getHandle(), fingerPosition.getValue(), nImage.getHandle(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    FIRecord.LIBRARY.FIRecordRemoveFinger(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        protected void clearNative() {
            NResult.check(FIRecord.LIBRARY.FIRecordClearFingers(this.getOwner().getHandle()));
        }
        
        protected FirFinger fromHandleNative(final HNObject handle) {
            return new FirFinger(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FIRecord.LIBRARY.FIRecordGetFinger(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FIRecord.LIBRARY.FIRecordGetFingerCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(FIRecord.LIBRARY.FIRecordSetFingerCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FIRecord.LIBRARY.FIRecordGetFingerCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FIRecord.LIBRARY.FIRecordRemoveFinger(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    public final class FingerViewCollection extends NObjectFlatCollection<FirFingerView>
    {
        FingerViewCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        void onInsert(final int index, final FirFingerView value) {
            this.insertItem(index, value);
        }
        
        void onRemoveRange(final int index, final int count) {
            this.removeItemRange(index, count);
        }
        
        protected void onRemoveItemRange(final int index, final int count) {
            if (count != 0) {
                int i = count - 1;
                FirFingerView lastFingerView = this.get(index + i);
                FirFinger lastFinger = lastFingerView.getOwner();
                int lastIndex = lastFinger.getFingerViews().indexOf(lastFingerView);
                while (i >= -1) {
                    final FirFingerView theFingerView = (i == -1) ? null : this.get(index + i);
                    final FirFinger theFinger = (theFingerView == null) ? null : theFingerView.getOwner();
                    if (theFinger != lastFinger) {
                        final FirFingerView firstFingerView = this.get(index + i + 1);
                        final int firstIndex = lastFinger.getFingerViews().indexOf(firstFingerView);
                        lastFinger.getFingerViews().onRemoveRange(firstIndex, lastIndex - firstIndex + 1);
                        if (theFinger != null) {
                            lastFingerView = theFingerView;
                            lastFinger = theFinger;
                            lastIndex = lastFinger.getFingerViews().indexOf(lastFingerView);
                        }
                    }
                    --i;
                }
            }
            super.onRemoveItemRange(index, count);
        }
        
        protected void clearNative() {
            NResult.check(FIRecord.LIBRARY.FIRecordClearFingers(this.getOwner().getHandle()));
        }
        
        protected FirFingerView fromHandleNative(final HNObject handle) {
            throw new UnsupportedOperationException();
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FIRecord.LIBRARY.FIRecordGetFingerView(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FIRecord.LIBRARY.FIRecordGetFingerViewCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FIRecord.LIBRARY.FIRecordRemoveFingerView(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
        
        protected void addNative(final FirFingerView value) {
            throw new UnsupportedOperationException();
        }
        
        protected void addNative(final int index, final FirFingerView value) {
            throw new UnsupportedOperationException();
        }
    }
    
    interface FIRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType FIRecordTypeOf();
        
        int FIRecordCalculateSize(final int p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int FIRecordCalculateSizeWithFingers(final int p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int FIRecordCreate(final short p0, final int p1, final short p2, final short p3, final short p4, final short p5, final byte p6, final int p7, final int p8, final int p9, final HNObjectByReference p10);
        
        int FIRecordCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int FIRecordCreateFromFIRecord(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int FIRecordCreateFromNImage(final HNObject p0, final short p1, final int p2, final short p3, final short p4, final byte p5, final int p6, final int p7, final int p8, final int p9, final HNObjectByReference p10);
        
        int FIRecordClone(final HNObject p0, final HNObjectByReference p1);
        
        int FIRecordGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int FIRecordSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int FIRecordGetStandard(final HNObject p0, final IntByReference p1);
        
        int FIRecordGetCbeffProductId(final HNObject p0, final IntByReference p1);
        
        int FIRecordSetCbeffProductId(final HNObject p0, final int p1);
        
        int FIRecordGetCaptureDeviceId(final HNObject p0, final ShortByReference p1);
        
        int FIRecordSetCaptureDeviceId(final HNObject p0, final short p1);
        
        int FIRecordGetImageAcquisitionLevel(final HNObject p0, final ShortByReference p1);
        
        int FIRecordGetScaleUnits(final HNObject p0, final IntByReference p1);
        
        int FIRecordGetHorzScanResolution(final HNObject p0, final ShortByReference p1);
        
        int FIRecordGetVertScanResolution(final HNObject p0, final ShortByReference p1);
        
        int FIRecordGetHorzImageResolution(final HNObject p0, final ShortByReference p1);
        
        int FIRecordGetVertImageResolution(final HNObject p0, final ShortByReference p1);
        
        int FIRecordGetPixelDepth(final HNObject p0, final ByteByReference p1);
        
        int FIRecordGetImageCompressionAlgorithm(final HNObject p0, final IntByReference p1);
        
        int FIRecordGetFingerCount(final HNObject p0, final IntByReference p1);
        
        int FIRecordGetFinger(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FIRecordGetFingerCapacity(final HNObject p0, final IntByReference p1);
        
        int FIRecordSetFingerCapacity(final HNObject p0, final int p1);
        
        int FIRecordAddFinger(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int FIRecordAddFingerFromNImage(final HNObject p0, final int p1, final HNObject p2, final int p3, final HNObjectByReference p4);
        
        int FIRecordRemoveFinger(final HNObject p0, final int p1);
        
        int FIRecordClearFingers(final HNObject p0);
        
        int FIRecordGetFingerViewCount(final HNObject p0, final IntByReference p1);
        
        int FIRecordGetFingerView(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FIRecordRemoveFingerView(final HNObject p0, final int p1);
        
        int FIRecordClearFingerViews(final HNObject p0);
    }
}
