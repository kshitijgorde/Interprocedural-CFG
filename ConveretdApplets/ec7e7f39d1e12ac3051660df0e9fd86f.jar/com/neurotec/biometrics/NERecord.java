// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public class NERecord extends NObject implements Cloneable
{
    static final NERecordLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NERecord(final HNObject handle, final boolean claimHandle) {
        super(handle, NERecord.NATIVE_TYPE, claimHandle);
    }
    
    public NERecord(final short width, final short height) {
        this(width, height, 0);
    }
    
    public NERecord(final short width, final short height, final int flags) {
        this(create(width, height, flags), true);
    }
    
    public NERecord(final ByteBuffer buffer) {
        this(create(buffer, 0), true);
    }
    
    public NERecord(final ByteBuffer buffer, final int flags) {
        this(create(buffer, flags), true);
    }
    
    private static HNObject create(final short width, final short height, final int flags) {
        if (width < 0) {
            throw new IllegalArgumentException("width less than zero");
        }
        if (height < 0) {
            throw new IllegalArgumentException("height less than zero");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NERecord.LIBRARY.NERecordCreate(width, height, flags, rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NERecord.LIBRARY.NERecordCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    public static void check(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NResult.check(NERecord.LIBRARY.NERecordCheck(buffer, new NativeSize((long)buffer.remaining())));
    }
    
    public static short getWidth(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetWidthMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static short getHeight(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetHeightMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static NEPosition getPosition(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final IntByReference rValue = new IntByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetPositionMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return NEPosition.get(rValue.getValue());
    }
    
    public static byte getQuality(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetQualityMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static short getCbeffProductType(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetCbeffProductTypeMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static NERecord fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NERecord fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NERecord value = new NERecord(handle, false);
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
        NResult.check(NERecord.LIBRARY.NERecordGetSize(this.getHandle(), flags, rValue));
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
        NResult.check(NERecord.LIBRARY.NERecordSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public final short getWidth() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetWidth(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final short getHeight() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetHeight(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final NEPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetPosition(this.getHandle(), rValue));
        return NEPosition.get(rValue.getValue());
    }
    
    public final void setPosition(final NEPosition value) {
        NResult.check(NERecord.LIBRARY.NERecordSetPosition(this.getHandle(), value.getValue()));
    }
    
    public final byte getQuality() {
        final ByteByReference rValue = new ByteByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetQuality(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setQuality(final byte value) {
        NResult.check(NERecord.LIBRARY.NERecordSetQuality(this.getHandle(), value));
    }
    
    public final short getCbeffProductType() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NERecord.LIBRARY.NERecordGetCbeffProductType(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setCbeffProductType(final short type) {
        if (type < 0) {
            throw new IllegalArgumentException("type is less than zero");
        }
        NResult.check(NERecord.LIBRARY.NERecordSetCbeffProductType(this.getHandle(), type));
    }
    
    public final Object clone() throws CloneNotSupportedException {
        NERecord value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(NERecord.LIBRARY.NERecordClone(this.getHandle(), rhValue));
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
        LIBRARY = (NERecordLibrary)Native.loadLibrary("NBiometrics", NERecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NERecord.LIBRARY.NERecordTypeOf());
    }
    
    interface NERecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NERecordTypeOf();
        
        int NERecordCheck(final Buffer p0, final NativeSize p1);
        
        int NERecordGetWidthMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NERecordGetHeightMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NERecordGetPositionMem(final Buffer p0, final NativeSize p1, final IntByReference p2);
        
        int NERecordGetQualityMem(final Buffer p0, final NativeSize p1, final ByteByReference p2);
        
        int NERecordGetCbeffProductTypeMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NERecordCreate(final short p0, final short p1, final int p2, final HNObjectByReference p3);
        
        int NERecordCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final NativeSizeByReference p3, final HNObjectByReference p4);
        
        int NERecordClone(final HNObject p0, final HNObjectByReference p1);
        
        int NERecordGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int NERecordSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int NERecordGetWidth(final HNObject p0, final ShortByReference p1);
        
        int NERecordGetHeight(final HNObject p0, final ShortByReference p1);
        
        int NERecordGetPosition(final HNObject p0, final IntByReference p1);
        
        int NERecordSetPosition(final HNObject p0, final int p1);
        
        int NERecordGetQuality(final HNObject p0, final ByteByReference p1);
        
        int NERecordSetQuality(final HNObject p0, final byte p1);
        
        int NERecordGetCbeffProductType(final HNObject p0, final ShortByReference p1);
        
        int NERecordSetCbeffProductType(final HNObject p0, final short p1);
    }
}
