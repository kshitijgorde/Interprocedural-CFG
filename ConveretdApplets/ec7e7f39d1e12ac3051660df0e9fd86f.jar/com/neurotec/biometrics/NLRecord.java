// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.ByteByReference;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public class NLRecord extends NObject implements Cloneable
{
    static final NLRecordLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NLRecord(final HNObject handle, final boolean claimHandle) {
        super(handle, NLRecord.NATIVE_TYPE, claimHandle);
    }
    
    public NLRecord() {
        this(0);
    }
    
    public NLRecord(final int flags) {
        this(create(flags), true);
    }
    
    public NLRecord(final ByteBuffer buffer) {
        this(create(buffer, 0), true);
    }
    
    public NLRecord(final ByteBuffer buffer, final int flags) {
        this(create(buffer, flags), true);
    }
    
    private static HNObject create(final int flags) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NLRecord.LIBRARY.NLRecordCreate(flags, rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NLRecord.LIBRARY.NLRecordCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    public static void check(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NResult.check(NLRecord.LIBRARY.NLRecordCheck(buffer, new NativeSize((long)buffer.remaining())));
    }
    
    public static byte getQuality(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NLRecord.LIBRARY.NLRecordGetQualityMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static NLRecord fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NLRecord fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NLRecord value = new NLRecord(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public final int getSize() {
        return this.getSize(0);
    }
    
    public final int getSize(final int flags) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NLRecord.LIBRARY.NLRecordGetSize(this.getHandle(), flags, rValue));
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
            throw new RuntimeException("Packed and calculated size of NERecord do not match");
        }
        buffer.flip();
        return buffer;
    }
    
    public int save(final ByteBuffer buffer) {
        return this.save(buffer, 0);
    }
    
    public int save(final ByteBuffer buffer, final int flags) {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NLRecord.LIBRARY.NLRecordSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public final short getQuality() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NLRecord.LIBRARY.NLRecordGetQuality(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setQuality(final short quality) {
        if (quality < 0 || quality > 255) {
            throw new IllegalArgumentException("quality must be in range [0..255].");
        }
        NResult.check(NLRecord.LIBRARY.NLRecordSetQuality(this.getHandle(), quality));
    }
    
    public final short getCbeffProductType() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NLRecord.LIBRARY.NLRecordGetCbeffProductType(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setCbeffProductType(final short type) {
        if (type < 0) {
            throw new IllegalArgumentException("type is less than zero");
        }
        NResult.check(NLRecord.LIBRARY.NLRecordSetCbeffProductType(this.getHandle(), type));
    }
    
    public final Object clone() throws CloneNotSupportedException {
        NLRecord value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(NLRecord.LIBRARY.NLRecordClone(this.getHandle(), rhValue));
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
        LIBRARY = (NLRecordLibrary)Native.loadLibrary("NBiometrics", NLRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NLRecord.LIBRARY.NLRecordTypeOf());
    }
    
    interface NLRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NLRecordTypeOf();
        
        int NLRecordCheck(final Buffer p0, final NativeSize p1);
        
        int NLRecordGetQualityMem(final Buffer p0, final NativeSize p1, final ByteByReference p2);
        
        int NLRecordCreate(final int p0, final HNObjectByReference p1);
        
        int NLRecordCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final NativeSizeByReference p3, final HNObjectByReference p4);
        
        int NLRecordClone(final HNObject p0, final HNObjectByReference p1);
        
        int NLRecordGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int NLRecordSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int NLRecordGetQuality(final HNObject p0, final ShortByReference p1);
        
        int NLRecordSetQuality(final HNObject p0, final short p1);
        
        int NLRecordGetCbeffProductType(final HNObject p0, final ShortByReference p1);
        
        int NLRecordSetCbeffProductType(final HNObject p0, final short p1);
    }
}
