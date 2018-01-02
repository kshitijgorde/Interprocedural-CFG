// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

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
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NLTemplate extends NObject implements Cloneable
{
    private RecordCollection records;
    static final NLTemplateLibrary LIBRARY;
    public static final int MAX_RECORD_COUNT = 255;
    public static final int PROCESS_FIRST_RECORD_ONLY = 256;
    public static final NNativeType NATIVE_TYPE;
    
    private NLTemplate(final HNObject handle, final boolean claimHandle) {
        super(handle, NLTemplate.NATIVE_TYPE, claimHandle);
        this.records = new RecordCollection(this);
    }
    
    public NLTemplate() {
        this(0);
    }
    
    public NLTemplate(final int flags) {
        this(create(flags), true);
    }
    
    public NLTemplate(final ByteBuffer buffer) {
        this(create(buffer, 0), true);
    }
    
    public NLTemplate(final ByteBuffer buffer, final int flags) {
        this(create(buffer, flags), true);
    }
    
    private static HNObject create(final int flags) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NLTemplate.LIBRARY.NLTemplateCreateEx(flags, rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NLTemplate.LIBRARY.NLTemplateCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    public static int calculateSize(final int[] recordSizes) {
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
        NResult.check(NLTemplate.LIBRARY.NLTemplateCalculateSize(recordCount, recSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public static void check(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NResult.check(NLTemplate.LIBRARY.NLTemplateCheck(buffer.slice(), new NativeSize((long)buffer.remaining())));
    }
    
    public static int getRecordCount(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final IntByReference rValue = new IntByReference();
        NResult.check(NLTemplate.LIBRARY.NLTemplateGetRecordCountMem(buffer.slice(), new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static NLTemplate fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NLTemplate fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NLTemplate value = new NLTemplate(handle, false);
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
        NResult.check(NLTemplate.LIBRARY.NLTemplateGetSize(this.getHandle(), flags, rValue));
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
            throw new RuntimeException("Packed and calculated size of NLTemplate do not match");
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
        NResult.check(NLTemplate.LIBRARY.NLTemplateSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public RecordCollection getRecords() {
        this.check();
        return this.records;
    }
    
    public Object clone() throws CloneNotSupportedException {
        NLTemplate value = null;
        HNObject rValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(NLTemplate.LIBRARY.NLTemplateClone(this.getHandle(), rhValue));
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
            this.records.disposeItems();
        }
        this.records = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (NLTemplateLibrary)Native.loadLibrary("NBiometrics", NLTemplateLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NLTemplate.LIBRARY.NLTemplateTypeOf());
    }
    
    public final class RecordCollection extends NObjectCollection<NLRecord>
    {
        RecordCollection(final NObject owner) {
            super(owner, false);
        }
        
        public NLRecord add() {
            return this.add(0);
        }
        
        public NLRecord add(final int flags) {
            NLRecord value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(NLTemplate.LIBRARY.NLTemplateAddRecord(this.getOwner().getHandle(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    NLTemplate.LIBRARY.NLTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public NLRecord add(final ByteBuffer buffer) {
            return this.add(buffer, 0);
        }
        
        public NLRecord add(final ByteBuffer buffer, final int flags) {
            if (buffer == null) {
                throw new NullPointerException("buffer");
            }
            NLRecord value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            final NativeSizeByReference rSize = new NativeSizeByReference();
            NResult.check(NLTemplate.LIBRARY.NLTemplateAddRecordFromMemoryEx(this.getOwner().getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    NLTemplate.LIBRARY.NLTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
                buffer.position(buffer.position() + rSize.getValue().intValue());
            }
            return value;
        }
        
        public NLRecord addCopy(final NLRecord srcRecord) {
            if (srcRecord == null) {
                throw new NullPointerException("srcRecord");
            }
            NLRecord value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(NLTemplate.LIBRARY.NLTemplateAddRecordCopy(this.getOwner().getHandle(), srcRecord.getHandle(), rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    NLTemplate.LIBRARY.NLTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        protected void clearNative() {
            NResult.check(NLTemplate.LIBRARY.NLTemplateClearRecords(this.getOwner().getHandle()));
        }
        
        protected NLRecord fromHandleNative(final HNObject handle) {
            return NLRecord.fromHandle(handle, false);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(NLTemplate.LIBRARY.NLTemplateGetRecord(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NLTemplate.LIBRARY.NLTemplateGetRecordCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(NLTemplate.LIBRARY.NLTemplateSetRecordCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NLTemplate.LIBRARY.NLTemplateGetRecordCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(NLTemplate.LIBRARY.NLTemplateRemoveRecord(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface NLTemplateLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NLTemplateTypeOf();
        
        int NLTemplateCalculateSize(final int p0, final NativeSize[] p1, final NativeSizeByReference p2);
        
        int NLTemplateCheck(final Buffer p0, final NativeSize p1);
        
        int NLTemplateGetRecordCountMem(final Buffer p0, final NativeSize p1, final IntByReference p2);
        
        int NLTemplateCreateEx(final int p0, final HNObjectByReference p1);
        
        int NLTemplateCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final NativeSizeByReference p3, final HNObjectByReference p4);
        
        int NLTemplateClone(final HNObject p0, final HNObjectByReference p1);
        
        int NLTemplateGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int NLTemplateSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int NLTemplateGetRecordCount(final HNObject p0, final IntByReference p1);
        
        int NLTemplateGetRecord(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NLTemplateGetRecordCapacity(final HNObject p0, final IntByReference p1);
        
        int NLTemplateSetRecordCapacity(final HNObject p0, final int p1);
        
        int NLTemplateAddRecord(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NLTemplateAddRecordFromMemoryEx(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int NLTemplateAddRecordCopy(final HNObject p0, final HNObject p1, final HNObjectByReference p2);
        
        int NLTemplateRemoveRecord(final HNObject p0, final int p1);
        
        int NLTemplateClearRecords(final HNObject p0);
    }
}
