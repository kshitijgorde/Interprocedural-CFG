// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NObjectFlatCollection;
import com.neurotec.util.NObjectCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.biometrics.NTemplate;
import java.util.Iterator;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.biometrics.NFTemplate;
import com.neurotec.biometrics.NFRecord;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class FMRecord extends NObject implements Cloneable
{
    private FingerCollection fingers;
    private FingerViewCollection fingerViews;
    static final FMRecordLibrary LIBRARY;
    public static final int MAX_FACE_IMAGE_COUNT = 32767;
    public static final int FLAG_PROCESS_FIRST_FACE_IMAGE_ONLY = 256;
    public static final NNativeType NATIVE_TYPE;
    
    private FMRecord(final HNObject handle, final boolean claimHandle) {
        super(handle, FMRecord.NATIVE_TYPE, claimHandle);
        this.fingerViews = new FingerViewCollection(this);
        this.fingers = new FingerCollection(this);
    }
    
    public FMRecord(final short sizeX, final short sizeY, final short resolutionX, final short resolutionY, final BdifStandard standard) {
        this(create(sizeX, sizeY, resolutionX, resolutionY, 0, standard), true);
    }
    
    public FMRecord(final short sizeX, final short sizeY, final short resolutionX, final short resolutionY, final int flags, final BdifStandard standard) {
        this(create(sizeX, sizeY, resolutionX, resolutionY, flags, standard), true);
    }
    
    public FMRecord(final ByteBuffer buffer, final BdifStandard standard) {
        this(create(buffer, 0, standard), true);
    }
    
    public FMRecord(final ByteBuffer buffer, final int flags, final BdifStandard standard) {
        this(create(buffer, flags, standard), true);
    }
    
    public FMRecord(final FMRecord srcRecord, final BdifStandard standard) {
        this(create(srcRecord, 0, standard), true);
    }
    
    public FMRecord(final FMRecord srcRecord, final int flags, final BdifStandard standard) {
        this(create(srcRecord, flags, standard), true);
    }
    
    public FMRecord(final NFRecord nfRecord, final BdifStandard standard) {
        this(create(nfRecord, 0, standard), true);
    }
    
    public FMRecord(final NFRecord nfRecord, final int flags, final BdifStandard standard) {
        this(create(nfRecord, flags, standard), true);
    }
    
    public FMRecord(final NFTemplate nfTemplate, final BdifStandard standard) {
        this(create(nfTemplate, 0, standard), true);
    }
    
    public FMRecord(final NFTemplate nfTemplate, final int flags, final BdifStandard standard) {
        this(create(nfTemplate, flags, standard), true);
    }
    
    private static HNObject create(final short sizeX, final short sizeY, final short resolutionX, final short resolutionY, final int flags, final BdifStandard standard) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordCreate(sizeX, sizeY, resolutionX, resolutionY, flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags, final BdifStandard standard) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, standard.getValue(), rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    private static HNObject create(final FMRecord srcRecord, final int flags, final BdifStandard standard) {
        if (srcRecord == null) {
            throw new NullPointerException("srcRecord");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordCreateFromFMRecord(srcRecord.getHandle(), flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final NFRecord nfRecord, final int flags, final BdifStandard standard) {
        if (nfRecord == null) {
            throw new NullPointerException("nfRecord");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordCreateFromNFRecord(nfRecord.getHandle(), flags, standard.getValue(), rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final NFTemplate nfTemplate, final int flags, final BdifStandard standard) {
        if (nfTemplate == null) {
            throw new NullPointerException("nfTemplate");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordCreateFromNFTemplate(nfTemplate.getHandle(), flags, standard.getValue(), rhValue));
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
        NResult.check(FMRecord.LIBRARY.FMRecordCalculateSize(standard.getValue(), fingerViewCount, finViewSizes, rValue));
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
        NResult.check(FMRecord.LIBRARY.FMRecordCalculateSizeWithFingers(standard.getValue(), fingerCount, finSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public static FMRecord fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static FMRecord fromHandle(final HNObject handle, final boolean ownsHandle) {
        final FMRecord value = new FMRecord(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private int getFingerViewsStartIndex(final FmrFinger finger) {
        int index = 0;
        for (final FmrFinger f : this.fingers) {
            if (f == finger) {
                break;
            }
            index += f.getFingerViews().size();
        }
        return index;
    }
    
    void pnInsertFingerView(final FmrFinger finger, final int index, final FmrFingerView value) {
        this.fingerViews.onInsert(this.getFingerViewsStartIndex(finger) + index, value);
    }
    
    void onRemoveFingerViewRange(final FmrFinger finger, final int index, final int count) {
        this.fingerViews.onRemoveRange(this.getFingerViewsStartIndex(finger) + index, count);
    }
    
    public int getSize() {
        return this.getSize(0);
    }
    
    public int getSize(final int flags) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordGetSize(this.getHandle(), flags, rValue));
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
            throw new RuntimeException("Packed and calculated size of FMRecord do not match");
        }
        buffer.flip();
        return buffer;
    }
    
    public int save(final ByteBuffer buffer) {
        return this.save(buffer, 0);
    }
    
    public int save(final ByteBuffer buffer, final int flags) {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public NFTemplate toNFTemplate() {
        return this.toNFTemplate(0);
    }
    
    public NFTemplate toNFTemplate(final int flags) {
        NFTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordToNFTemplate(this.getHandle(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NFTemplate.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public NTemplate toNTemplate() {
        return this.toNTemplate(0);
    }
    
    public NTemplate toNTemplate(final int flags) {
        NTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordToNTemplate(this.getHandle(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NTemplate.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public BdifStandard getStandard() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordGetStandard(this.getHandle(), rValue));
        return BdifStandard.get(rValue.getValue());
    }
    
    public int getCbeffProductId() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordGetCbeffProductId(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setCbeffProductId(final int value) {
        NResult.check(FMRecord.LIBRARY.FMRecordSetCbeffProductId(this.getHandle(), value));
    }
    
    public FmrCaptureEquipmentCompliance getCaptureEquipmentCompliance() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordGetCaptureEquipmentCompliance(this.getHandle(), rValue));
        return FmrCaptureEquipmentCompliance.get(rValue.getValue());
    }
    
    public void setCaptureEquipmentCompliance(final FmrCaptureEquipmentCompliance value) {
        NResult.check(FMRecord.LIBRARY.FMRecordSetCaptureEquipmentCompliance(this.getHandle(), value.getValue()));
    }
    
    public short getCaptureEquipmentId() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordGetCaptureEquipmentId(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setCaptureEquipmentId(final short value) {
        NResult.check(FMRecord.LIBRARY.FMRecordSetCaptureEquipmentId(this.getHandle(), value));
    }
    
    public short getSizeX() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordGetSizeX(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getSizeY() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordGetSizeY(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getResolutionX() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordGetResolutionX(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getResolutionY() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FMRecord.LIBRARY.FMRecordGetResolutionY(this.getHandle(), rValue));
        return rValue.getValue();
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
        FMRecord value = null;
        HNObject rValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(FMRecord.LIBRARY.FMRecordClone(this.getHandle(), rhValue));
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
        LIBRARY = (FMRecordLibrary)Native.loadLibrary("NBiometricStandards", FMRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(FMRecord.LIBRARY.FMRecordTypeOf());
    }
    
    public final class FingerCollection extends NObjectCollection<FmrFinger>
    {
        FingerCollection(final NObject owner) {
            super(owner, false);
            final int count = this.size();
            int fingerViewsStartIndex = 0;
            for (int i = 0; i < count; ++i) {
                final FmrFinger finger = this.get(i);
                this.fillFingerViews(fingerViewsStartIndex, finger);
                fingerViewsStartIndex += finger.getFingerViews().size();
            }
        }
        
        private void fillFingerViews(final int fingerViewsStartIndex, final FmrFinger item) {
            for (int fingerViewsCount = item.getFingerViews().size(), i = 0; i < fingerViewsCount; ++i) {
                ((FMRecord)this.getOwner()).getFingerViews().onInsert(fingerViewsStartIndex + i, item.getFingerViews().get(i));
            }
        }
        
        protected void onInsertItem(final int index, final FmrFinger item) {
            super.onInsertItem(index, item);
            this.fillFingerViews(((FMRecord)this.getOwner()).getFingerViewsStartIndex(item), item);
        }
        
        protected void onRemoveItemRange(final int index, final int count) {
            for (int i = count - 1; i >= 0; --i) {
                final FmrFinger finger = this.get(index + i);
                final int fingerViewsStartIndex = ((FMRecord)this.getOwner()).getFingerViewsStartIndex(finger);
                final int fingerViewsCount = finger.getFingerViews().size();
                ((FMRecord)this.getOwner()).fingerViews.onRemoveRange(fingerViewsStartIndex, fingerViewsCount);
            }
            super.onRemoveItemRange(index, count);
        }
        
        public FmrFinger add(final BdifFPPosition fingerPosition) {
            return this.add(fingerPosition, 0);
        }
        
        public FmrFinger add(final BdifFPPosition fingerPosition, final int flags) {
            FmrFinger value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FMRecord.LIBRARY.FMRecordAddFinger(this.getOwner().getHandle(), fingerPosition.getValue(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    FMRecord.LIBRARY.FMRecordRemoveFinger(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        protected void clearNative() {
            NResult.check(FMRecord.LIBRARY.FMRecordClearFingers(this.getOwner().getHandle()));
        }
        
        protected FmrFinger fromHandleNative(final HNObject handle) {
            return new FmrFinger(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FMRecord.LIBRARY.FMRecordGetFinger(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FMRecord.LIBRARY.FMRecordGetFingerCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(FMRecord.LIBRARY.FMRecordSetFingerCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FMRecord.LIBRARY.FMRecordGetFingerCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FMRecord.LIBRARY.FMRecordRemoveFinger(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    public final class FingerViewCollection extends NObjectFlatCollection<FmrFingerView>
    {
        FingerViewCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        void onInsert(final int index, final FmrFingerView value) {
            this.insertItem(index, value);
        }
        
        void onRemoveRange(final int index, final int count) {
            this.removeItemRange(index, count);
        }
        
        protected void onRemoveItemRange(final int index, final int count) {
            if (count != 0) {
                int i = count - 1;
                FmrFingerView lastFingerView = this.get(index + i);
                FmrFinger lastFinger = lastFingerView.getOwner();
                int lastIndex = lastFinger.getFingerViews().indexOf(lastFingerView);
                while (i >= -1) {
                    final FmrFingerView theFingerView = (i == -1) ? null : this.get(index + i);
                    final FmrFinger theFinger = (theFingerView == null) ? null : theFingerView.getOwner();
                    if (theFinger != lastFinger) {
                        final FmrFingerView firstFingerView = this.get(index + i + 1);
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
            NResult.check(FMRecord.LIBRARY.FMRecordClearFingers(this.getOwner().getHandle()));
        }
        
        protected FmrFingerView fromHandleNative(final HNObject handle) {
            throw new UnsupportedOperationException();
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FMRecord.LIBRARY.FMRecordGetFingerView(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FMRecord.LIBRARY.FMRecordGetFingerViewCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FMRecord.LIBRARY.FMRecordRemoveFingerView(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
        
        protected void addNative(final FmrFingerView value) {
            throw new UnsupportedOperationException();
        }
        
        protected void addNative(final int index, final FmrFingerView value) {
            throw new UnsupportedOperationException();
        }
    }
    
    interface FMRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType FMRecordTypeOf();
        
        int FMRecordCalculateSize(final int p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int FMRecordCalculateSizeWithFingers(final int p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int FMRecordCreate(final short p0, final short p1, final short p2, final short p3, final int p4, final int p5, final HNObjectByReference p6);
        
        int FMRecordCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int FMRecordCreateFromFMRecord(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int FMRecordCreateFromNFRecord(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int FMRecordCreateFromNFTemplate(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int FMRecordClone(final HNObject p0, final HNObjectByReference p1);
        
        int FMRecordGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int FMRecordSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int FMRecordToNFTemplate(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FMRecordToNTemplate(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FMRecordGetStandard(final HNObject p0, final IntByReference p1);
        
        int FMRecordGetCbeffProductId(final HNObject p0, final IntByReference p1);
        
        int FMRecordSetCbeffProductId(final HNObject p0, final int p1);
        
        int FMRecordGetCaptureEquipmentCompliance(final HNObject p0, final IntByReference p1);
        
        int FMRecordSetCaptureEquipmentCompliance(final HNObject p0, final int p1);
        
        int FMRecordGetCaptureEquipmentId(final HNObject p0, final ShortByReference p1);
        
        int FMRecordSetCaptureEquipmentId(final HNObject p0, final short p1);
        
        int FMRecordGetSizeX(final HNObject p0, final ShortByReference p1);
        
        int FMRecordGetSizeY(final HNObject p0, final ShortByReference p1);
        
        int FMRecordGetResolutionX(final HNObject p0, final ShortByReference p1);
        
        int FMRecordGetResolutionY(final HNObject p0, final ShortByReference p1);
        
        int FMRecordGetFingerCount(final HNObject p0, final IntByReference p1);
        
        int FMRecordGetFinger(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FMRecordGetFingerCapacity(final HNObject p0, final IntByReference p1);
        
        int FMRecordSetFingerCapacity(final HNObject p0, final int p1);
        
        int FMRecordAddFinger(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int FMRecordRemoveFinger(final HNObject p0, final int p1);
        
        int FMRecordClearFingers(final HNObject p0);
        
        int FMRecordGetFingerViewCount(final HNObject p0, final IntByReference p1);
        
        int FMRecordGetFingerView(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FMRecordRemoveFingerView(final HNObject p0, final int p1);
        
        int FMRecordClearFingerViews(final HNObject p0);
    }
}
