// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NObjectCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.BooleanByReference;
import java.nio.Buffer;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.jna.ptr.NativeSize;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NFTemplate extends NObject implements Cloneable
{
    private RecordCollection records;
    static final NFTemplateLibrary LIBRARY;
    public static final int MAX_RECORD_COUNT = 255;
    public static final int PROCESS_FIRST_RECORD_ONLY = 256;
    public static final NNativeType NATIVE_TYPE;
    
    private NFTemplate(final HNObject handle, final boolean claimHandle) {
        super(handle, NFTemplate.NATIVE_TYPE, claimHandle);
        this.records = new RecordCollection(this);
    }
    
    public NFTemplate() {
        this(false, 0);
    }
    
    public NFTemplate(final int flags) {
        this(false, flags);
    }
    
    public NFTemplate(final boolean isPalm) {
        this(isPalm, 0);
    }
    
    public NFTemplate(final boolean isPalm, final int flags) {
        this(create(isPalm, flags), true);
    }
    
    public NFTemplate(final ByteBuffer buffer) {
        this(create(buffer, 0), true);
    }
    
    public NFTemplate(final ByteBuffer buffer, final int flags) {
        this(create(buffer, flags), true);
    }
    
    public static int calculateSize(final int[] recordSizes) {
        return calculateSize(false, recordSizes);
    }
    
    public static int calculateSize(final boolean isPalm, final int[] recordSizes) {
        if (recordSizes == null) {
            throw new NullPointerException("recordSizes");
        }
        final int recordCount = recordSizes.length;
        final NativeSize[] recSizes = new NativeSize[recordCount];
        for (int i = 0; i != recordCount; ++i) {
            if (recordSizes[i] < 0) {
                throw new IllegalArgumentException("One of recordSizes is less than zero");
            }
            recSizes[i] = new NativeSize((long)i);
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NFTemplate.LIBRARY.NFTemplateCalculateSize(isPalm, recordCount, recSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public static void check(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NResult.check(NFTemplate.LIBRARY.NFTemplateCheck(buffer.slice(), new NativeSize((long)buffer.remaining())));
    }
    
    public static boolean isPalmTemplate(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NFTemplate.LIBRARY.NFTemplateIsPalmMem(buffer.slice(), new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static int getRecordCount(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final IntByReference rValue = new IntByReference();
        NResult.check(NFTemplate.LIBRARY.NFTemplateGetRecordCountMem(buffer.slice(), new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static NFTemplate fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NFTemplate fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NFTemplate value = new NFTemplate(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private static HNObject create(final boolean isPalm, final int flags) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NFTemplate.LIBRARY.NFTemplateCreateEx(isPalm, flags, rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NFTemplate.LIBRARY.NFTemplateCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    public int getSize() {
        return this.getSize(0);
    }
    
    public int getSize(final int flags) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NFTemplate.LIBRARY.NFTemplateGetSize(this.getHandle(), flags, rValue));
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
            throw new RuntimeException("Packed and calculated size of NFTemplate do not match");
        }
        buffer.flip();
        return buffer;
    }
    
    public int save(final ByteBuffer buffer) {
        return this.save(buffer, 0);
    }
    
    public int save(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NFTemplate.LIBRARY.NFTemplateSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public boolean isPalm() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NFTemplate.LIBRARY.NFTemplateIsPalm(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public RecordCollection getRecords() {
        this.check();
        return this.records;
    }
    
    protected void dispose(final boolean disposing) {
        if (disposing) {
            this.records.disposeItems();
        }
        this.records = null;
        super.dispose(disposing);
    }
    
    public Object clone() throws CloneNotSupportedException {
        NFTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(NFTemplate.LIBRARY.NFTemplateClone(this.getHandle(), rhValue));
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
        LIBRARY = (NFTemplateLibrary)Native.loadLibrary("NBiometrics", NFTemplateLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NFTemplate.LIBRARY.NFTemplateTypeOf());
    }
    
    public final class RecordCollection extends NObjectCollection<NFRecord>
    {
        RecordCollection(final NObject owner) {
            super(owner, false);
        }
        
        public NFRecord add(final short width, final short height, final short horzResolution, final short vertResolution) {
            return this.add(width, height, horzResolution, vertResolution, 0);
        }
        
        public NFRecord add(final short width, final short height, final short horzResolution, final short vertResolution, final int flags) {
            if (width < 0) {
                throw new IllegalArgumentException("width less than zero");
            }
            if (height < 0) {
                throw new IllegalArgumentException("height less than zero");
            }
            if (horzResolution < 0) {
                throw new IllegalArgumentException("horzResolution less than zero");
            }
            if (vertResolution < 0) {
                throw new IllegalArgumentException("vertResolution less than zero");
            }
            NFRecord value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(NFTemplate.LIBRARY.NFTemplateAddRecord(this.getOwner().getHandle(), width, height, horzResolution, vertResolution, flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    NFTemplate.LIBRARY.NFTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public NFRecord add(final ByteBuffer buffer) {
            return this.add(buffer, 0);
        }
        
        public NFRecord add(final ByteBuffer buffer, final int flags) {
            if (buffer == null) {
                throw new NullPointerException("buffer");
            }
            NFRecord value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            final NativeSizeByReference rSize = new NativeSizeByReference();
            NResult.check(NFTemplate.LIBRARY.NFTemplateAddRecordFromMemoryEx(this.getOwner().getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    NFTemplate.LIBRARY.NFTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
                buffer.position(buffer.position() + rSize.getValue().intValue());
            }
            return value;
        }
        
        public NFRecord addCopy(final NFRecord srcRecord) {
            if (srcRecord == null) {
                throw new NullPointerException("srcRecord");
            }
            NFRecord value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(NFTemplate.LIBRARY.NFTemplateAddRecordCopy(this.getOwner().getHandle(), srcRecord.getHandle(), rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    NFTemplate.LIBRARY.NFTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        protected void clearNative() {
            NResult.check(NFTemplate.LIBRARY.NFTemplateClearRecords(this.getOwner().getHandle()));
        }
        
        protected NFRecord fromHandleNative(final HNObject handle) {
            return NFRecord.fromHandle(handle, false);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(NFTemplate.LIBRARY.NFTemplateGetRecord(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFTemplate.LIBRARY.NFTemplateGetRecordCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(NFTemplate.LIBRARY.NFTemplateSetRecordCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFTemplate.LIBRARY.NFTemplateGetRecordCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(NFTemplate.LIBRARY.NFTemplateRemoveRecord(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface NFTemplateLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NFTemplateTypeOf();
        
        int NFTemplateCalculateSize(final boolean p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int NFTemplateCheck(final Buffer p0, final NativeSize p1);
        
        int NFTemplateIsPalmMem(final Buffer p0, final NativeSize p1, final BooleanByReference p2);
        
        int NFTemplateGetRecordCountMem(final Buffer p0, final NativeSize p1, final IntByReference p2);
        
        int NFTemplateCreateEx(final boolean p0, final int p1, final HNObjectByReference p2);
        
        int NFTemplateCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final NativeSizeByReference p3, final HNObjectByReference p4);
        
        int NFTemplateClone(final HNObject p0, final HNObjectByReference p1);
        
        int NFTemplateGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int NFTemplateSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int NFTemplateIsPalm(final HNObject p0, final BooleanByReference p1);
        
        int NFTemplateGetRecordCount(final HNObject p0, final IntByReference p1);
        
        int NFTemplateGetRecord(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NFTemplateGetRecordCapacity(final HNObject p0, final IntByReference p1);
        
        int NFTemplateSetRecordCapacity(final HNObject p0, final int p1);
        
        int NFTemplateAddRecord(final HNObject p0, final short p1, final short p2, final short p3, final short p4, final int p5, final HNObjectByReference p6);
        
        int NFTemplateAddRecordFromMemoryEx(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int NFTemplateAddRecordCopy(final HNObject p0, final HNObject p1, final HNObjectByReference p2);
        
        int NFTemplateRemoveRecord(final HNObject p0, final int p1);
        
        int NFTemplateClearRecords(final HNObject p0);
    }
}
