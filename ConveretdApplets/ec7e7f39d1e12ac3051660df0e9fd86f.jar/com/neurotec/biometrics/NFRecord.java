// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NArrayCollection;
import com.neurotec.util.NSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.util.EnumSet;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import java.nio.ByteBuffer;
import com.neurotec.util.NCollection;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public class NFRecord extends NObject implements Cloneable
{
    static final NFRecordLibrary LIBRARY;
    public static final int RESOLUTION = 500;
    public static final int MAX_FINGER_DIMENSION = 2047;
    public static final int MAX_FINGER_MINUTIA_COUNT = 255;
    public static final int MAX_FINGER_CORE_COUNT = 15;
    public static final int MAX_FINGER_DELTA_COUNT = 15;
    public static final int MAX_FINGER_DOUBLE_CORE_COUNT = 15;
    public static final int MAX_PALM_DIMENSION = 16383;
    public static final int MAX_PALM_MINUTIA_COUNT = 65535;
    public static final int MAX_PALM_CORE_COUNT = 255;
    public static final int MAX_PALM_DELTA_COUNT = 255;
    public static final int MAX_PALM_DOUBLE_CORE_COUNT = 255;
    public static final int FLAG_SKIP_RIDGE_COUNTS = 65536;
    public static final int FLAG_SKIP_SINGULAR_POINTS = 131072;
    public static final int FLAG_SKIP_BLOCKED_ORIENTS = 262144;
    public static final int FLAG_SAVE_BLOCKED_ORIENTS = 262144;
    public static final int FLAG_ALLOW_OUT_OF_BOUNDS_FEATURES = 524288;
    public static final int FLAG_SKIP_QUALITIES = 1048576;
    public static final int FLAG_SKIP_CURVATURES = 2097152;
    public static final int FLAG_SKIP_GS = 4194304;
    public static final int FLAG_SAVE_V2 = 536870912;
    public static final int FLAG_SAVE_V3 = 805306368;
    private MinutiaCollection minutiae;
    private MinutiaNeighborsCollection minutiaeNeighbors;
    private CoreCollection cores;
    private DeltaCollection deltas;
    private DoubleCoreCollection doubleCores;
    public static final NNativeType NATIVE_TYPE;
    
    private NFRecord(final HNObject handle, final boolean claimHandle) {
        super(handle, NFRecord.NATIVE_TYPE, claimHandle);
        this.minutiae = new MinutiaCollection(this);
        this.minutiaeNeighbors = new MinutiaNeighborsCollection(this, this.minutiae);
        this.cores = new CoreCollection(this);
        this.deltas = new DeltaCollection(this);
        this.doubleCores = new DoubleCoreCollection(this);
    }
    
    public NFRecord(final short width, final short height, final short horzResolution, final short vertResolution) {
        this(false, width, height, horzResolution, vertResolution, 0);
    }
    
    public NFRecord(final short width, final short height, final short horzResolution, final short vertResolution, final int flags) {
        this(false, width, height, horzResolution, vertResolution, flags);
    }
    
    public NFRecord(final boolean isPalm, final short width, final short height, final short horzResolution, final short vertResolution) {
        this(isPalm, width, height, horzResolution, vertResolution, 0);
    }
    
    public NFRecord(final boolean isPalm, final short width, final short height, final short horzResolution, final short vertResolution, final int flags) {
        this(create(isPalm, width, height, horzResolution, vertResolution, flags), true);
    }
    
    public NFRecord(final ByteBuffer buffer) {
        this(create(buffer, 0), true);
    }
    
    public NFRecord(final ByteBuffer buffer, final int flags) {
        this(create(buffer, flags), true);
    }
    
    private static HNObject create(final boolean isPalm, final short width, final short height, final short horzResolution, final short vertResolution, final int flags) {
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
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordCreateEx(isPalm, width, height, horzResolution, vertResolution, flags, rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    static double angleToDouble(final byte value) {
        return value * 3.141592653589793 / 128.0;
    }
    
    static byte angleFromDouble(double value) {
        value -= Math.floor(value / 6.283185307179586) * 6.283185307179586;
        return (byte)Math.round(value * 128.0 / 3.141592653589793);
    }
    
    static double angleToDoubleN(final int value) {
        return (value < 0 || value >= 256) ? Double.NaN : (value * 3.141592653589793 / 128.0);
    }
    
    static int angleFromDoubleN(double value) {
        if (Double.isNaN(value)) {
            return -1;
        }
        value -= Math.floor(value / 6.283185307179586) * 6.283185307179586;
        return (byte)Math.round(value * 128.0 / 3.141592653589793);
    }
    
    static String angleToString(final int value) {
        return (value < -1 || value >= 256) ? String.valueOf(value) : ((value == -1) ? "None" : (String.valueOf(Math.round(angleToDouble((byte)value) * 180.0 / 3.141592653589793 * 10.0) / 10L) + '°'));
    }
    
    public static int getMaxSize(final int version, final boolean isPalm, final NFMinutiaFormat minutiaFormat, final int minutiaCount, final NFRidgeCountsType ridgeCountsType, final int coreCount, final int deltaCount, final int doubleCoreCount, final int boWidth, final int boHeight) {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetMaxSize(version, isPalm, minutiaFormat.getValue(), minutiaCount, ridgeCountsType.getValue(), coreCount, deltaCount, doubleCoreCount, boWidth, boHeight, rSize));
        return rSize.getValue().intValue();
    }
    
    public static int getMaxSize(final NFMinutiaFormat minutiaFormat, final int minutiaCount, final NFRidgeCountsType ridgeCountsType, final int coreCount, final int deltaCount, final int doubleCoreCount) {
        return getMaxSize(0, false, minutiaFormat, minutiaCount, ridgeCountsType, coreCount, deltaCount, doubleCoreCount, 0, 0);
    }
    
    public static int getMaxSizeV1(final NFMinutiaFormat minutiaFormat, final int minutiaCount, final int coreCount, final int deltaCount, final int doubleCoreCount) {
        return getMaxSizeV1(minutiaFormat, minutiaCount, coreCount, deltaCount, doubleCoreCount, 0, 0);
    }
    
    public static int getMaxSizeV1(final NFMinutiaFormat minutiaFormat, final int minutiaCount, final int coreCount, final int deltaCount, final int doubleCoreCount, final int boWidth, final int boHeight) {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetMaxSizeV1(minutiaFormat.getValue(), minutiaCount, coreCount, deltaCount, doubleCoreCount, boWidth, boHeight, rSize));
        return rSize.getValue().intValue();
    }
    
    public static void check(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NResult.check(NFRecord.LIBRARY.NFRecordCheck(buffer, new NativeSize((long)buffer.remaining())));
    }
    
    public static short getWidth(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetWidthMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static short getHeight(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetHeightMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static short getHorzResolution(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetHorzResolutionMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static short getVertResolution(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetVertResolutionMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static NEPosition getPosition(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final IntByReference rValue = new IntByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetPositionMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return NEPosition.get(rValue.getValue());
    }
    
    public static NFImpressionType getImpressionType(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final IntByReference rValue = new IntByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetImpressionTypeMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return NFImpressionType.get(rValue.getValue());
    }
    
    public static NFImpressionType getPatternClass(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final IntByReference rValue = new IntByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetPatternClassMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return NFImpressionType.get(rValue.getValue());
    }
    
    public static short getQuality(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetQualityMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static short getG(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetGMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static short getCbeffProductType(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetCbeffProductTypeMem(buffer, new NativeSize((long)buffer.remaining()), rValue));
        return rValue.getValue();
    }
    
    public static NFRecord fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NFRecord fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NFRecord value = new NFRecord(handle, false);
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
        NResult.check(NFRecord.LIBRARY.NFRecordGetSize(this.getHandle(), flags, rValue));
        return rValue.getValue().intValue();
    }
    
    public int getSizeV1() {
        return this.getSizeV1(0);
    }
    
    public int getSizeV1(final int flags) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetSizeV1(this.getHandle(), flags, rValue));
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
        NResult.check(NFRecord.LIBRARY.NFRecordSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public ByteBuffer saveV1() {
        return this.save(0);
    }
    
    public ByteBuffer saveV1(final int flags) {
        final int size = this.getSize(flags);
        final ByteBuffer buffer = ByteBuffer.allocateDirect(size);
        buffer.limit(size);
        if (this.save(buffer, flags) != size) {
            throw new RuntimeException("Packed and calculated size of NERecord do not match");
        }
        buffer.flip();
        return buffer;
    }
    
    public int saveV1(final ByteBuffer buffer) {
        return this.save(buffer, 0);
    }
    
    public int saveV1(final ByteBuffer buffer, final int flags) {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordSaveToMemoryV1(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public final void sortMinutiae(final NFMinutiaOrder order) {
        NResult.check(NFRecord.LIBRARY.NFRecordSortMinutiae(this.getHandle(), order.getValue()));
    }
    
    public final void truncateMinutiaeByQuality(final byte threshold, final int maxCount) {
        NResult.check(NFRecord.LIBRARY.NFRecordTruncateMinutiaeByQuality(this.getHandle(), threshold, maxCount));
    }
    
    public final void truncateMinutiae(final int maxCount) {
        NResult.check(NFRecord.LIBRARY.NFRecordTruncateMinutiae(this.getHandle(), maxCount));
    }
    
    public final short getWidth() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetWidth(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final short getHeight() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetHeight(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final short getHorzResolution() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetHorzResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final short getVertResolution() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetVertResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final NFPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetPosition(this.getHandle(), rValue));
        return NFPosition.get(rValue.getValue());
    }
    
    public final void setPosition(final NFPosition position) {
        NResult.check(NFRecord.LIBRARY.NFRecordSetPosition(this.getHandle(), position.getValue()));
    }
    
    public final NFImpressionType getImpressionType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetImpressionType(this.getHandle(), rValue));
        return NFImpressionType.get(rValue.getValue());
    }
    
    public final void setImpressionType(final NFImpressionType type) {
        NResult.check(NFRecord.LIBRARY.NFRecordSetImpressionType(this.getHandle(), type.getValue()));
    }
    
    public final NFPatternClass getPatternClass() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetPatternClass(this.getHandle(), rValue));
        return NFPatternClass.get(rValue.getValue());
    }
    
    public final void setPatternClass(final NFPatternClass type) {
        NResult.check(NFRecord.LIBRARY.NFRecordSetPatternClass(this.getHandle(), type.getValue()));
    }
    
    public final short getQuality() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetQuality(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setQuality(final short quality) {
        NResult.check(NFRecord.LIBRARY.NFRecordSetQuality(this.getHandle(), quality));
    }
    
    public final short getG() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetG(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setG(final short value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("g must be in range [0..255].");
        }
        NResult.check(NFRecord.LIBRARY.NFRecordSetG(this.getHandle(), value));
    }
    
    public final short getCbeffProductType() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetCbeffProductType(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setCbeffProductType(final short type) {
        if (type < 0) {
            throw new IllegalArgumentException("type is less than zero");
        }
        NResult.check(NFRecord.LIBRARY.NFRecordSetCbeffProductType(this.getHandle(), type));
    }
    
    public final NFRidgeCountsType getRidgeCountsType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetRidgeCountsType(this.getHandle(), rValue));
        return NFRidgeCountsType.get(rValue.getValue());
    }
    
    public final void setRidgeCountsType(final NFRidgeCountsType value) {
        NResult.check(NFRecord.LIBRARY.NFRecordSetRidgeCountsType(this.getHandle(), value.getValue()));
    }
    
    public final EnumSet<NFMinutiaFormat> getMinutiaFormat() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NFRecord.LIBRARY.NFRecordGetMinutiaFormat(this.getHandle(), rValue));
        return NFMinutiaFormat.getSet(rValue.getValue());
    }
    
    public final void setMinutiaFormat(final EnumSet<NFMinutiaFormat> format) {
        NResult.check(NFRecord.LIBRARY.NFRecordSetMinutiaFormat(this.getHandle(), NFMinutiaFormat.getValue(format)));
    }
    
    public final MinutiaCollection getMinutiae() {
        return this.minutiae;
    }
    
    public final MinutiaNeighborsCollection getMinutiaeNeighbors() {
        return this.minutiaeNeighbors;
    }
    
    public final CoreCollection getCores() {
        return this.cores;
    }
    
    public final DeltaCollection getDeltas() {
        return this.deltas;
    }
    
    public final DoubleCoreCollection getDoubleCores() {
        return this.doubleCores;
    }
    
    public final Object clone() throws CloneNotSupportedException {
        NFRecord value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(NFRecord.LIBRARY.NFRecordClone(this.getHandle(), rhValue));
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
        LIBRARY = (NFRecordLibrary)Native.loadLibrary("NBiometrics", NFRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NFRecord.LIBRARY.NFRecordTypeOf());
    }
    
    public final class MinutiaCollection extends NSimpleCollection<NFMinutia>
    {
        protected MinutiaCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        protected void addNative(final NFMinutia value) {
            NResult.check(NFRecord.LIBRARY.NFRecordAddMinutia(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final NFMinutia value) {
            throw new UnsupportedOperationException();
        }
        
        protected NFMinutia[] getAllNative() {
            final int size = this.sizeNative();
            final NFMinutia.NFMinutiaData[] structures = (NFMinutia.NFMinutiaData[])new NFMinutia.NFMinutiaData().toArray(size);
            final NFMinutia[] values = new NFMinutia[size];
            NFRecord.LIBRARY.NFRecordGetMinutiaeEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new NFMinutia(structures[i]);
            }
            return values;
        }
        
        protected NFMinutia getNative(final int index) {
            final NFMinutia value = new NFMinutia();
            NResult.check(NFRecord.LIBRARY.NFRecordGetMinutia(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final NFMinutia value) {
            NResult.check(NFRecord.LIBRARY.NFRecordInsertMinutia(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final NFMinutia value) {
            NResult.check(NFRecord.LIBRARY.NFRecordSetMinutia(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(NFRecord.LIBRARY.NFRecordClearMinutiae(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFRecord.LIBRARY.NFRecordGetMinutiaCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFRecord.LIBRARY.NFRecordGetMinutiaCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(NFRecord.LIBRARY.NFRecordRemoveMinutia(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final NFMinutia value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(NFRecord.LIBRARY.NFRecordSetMinutiaCapacity(this.getOwner().getHandle(), value));
        }
    }
    
    public final class CoreCollection extends NSimpleCollection<NFCore>
    {
        protected CoreCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        protected void addNative(final NFCore value) {
            NResult.check(NFRecord.LIBRARY.NFRecordAddCore(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final NFCore value) {
            throw new UnsupportedOperationException();
        }
        
        protected NFCore[] getAllNative() {
            final int size = this.sizeNative();
            final NFCore.NFCoreData[] structures = (NFCore.NFCoreData[])new NFCore.NFCoreData().toArray(size);
            final NFCore[] values = new NFCore[size];
            NFRecord.LIBRARY.NFRecordGetCoresEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new NFCore(structures[i]);
            }
            return values;
        }
        
        protected NFCore getNative(final int index) {
            final NFCore value = new NFCore();
            NResult.check(NFRecord.LIBRARY.NFRecordGetCore(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final NFCore value) {
            NResult.check(NFRecord.LIBRARY.NFRecordInsertCore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final NFCore value) {
            NResult.check(NFRecord.LIBRARY.NFRecordSetCore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(NFRecord.LIBRARY.NFRecordClearCores(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFRecord.LIBRARY.NFRecordGetCoreCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFRecord.LIBRARY.NFRecordGetCoreCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(NFRecord.LIBRARY.NFRecordRemoveCore(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final NFCore value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(NFRecord.LIBRARY.NFRecordSetCoreCapacity(this.getOwner().getHandle(), value));
        }
    }
    
    public final class DeltaCollection extends NSimpleCollection<NFDelta>
    {
        protected DeltaCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        protected void addNative(final NFDelta value) {
            NResult.check(NFRecord.LIBRARY.NFRecordAddDelta(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final NFDelta value) {
            throw new UnsupportedOperationException();
        }
        
        protected NFDelta[] getAllNative() {
            final int size = this.sizeNative();
            final NFDelta.NFDeltaData[] structures = (NFDelta.NFDeltaData[])new NFDelta.NFDeltaData().toArray(size);
            final NFDelta[] values = new NFDelta[size];
            NFRecord.LIBRARY.NFRecordGetDeltasEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new NFDelta(structures[i]);
            }
            return values;
        }
        
        protected NFDelta getNative(final int index) {
            final NFDelta value = new NFDelta();
            NResult.check(NFRecord.LIBRARY.NFRecordGetDelta(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final NFDelta value) {
            NResult.check(NFRecord.LIBRARY.NFRecordInsertDelta(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final NFDelta value) {
            NResult.check(NFRecord.LIBRARY.NFRecordSetDelta(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(NFRecord.LIBRARY.NFRecordClearDeltas(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFRecord.LIBRARY.NFRecordGetDeltaCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFRecord.LIBRARY.NFRecordGetDeltaCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(NFRecord.LIBRARY.NFRecordRemoveDelta(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final NFDelta value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(NFRecord.LIBRARY.NFRecordSetDeltaCapacity(this.getOwner().getHandle(), value));
        }
    }
    
    public final class DoubleCoreCollection extends NSimpleCollection<NFDoubleCore>
    {
        protected DoubleCoreCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        protected void addNative(final NFDoubleCore value) {
            NResult.check(NFRecord.LIBRARY.NFRecordAddDoubleCore(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final NFDoubleCore value) {
            throw new UnsupportedOperationException();
        }
        
        protected NFDoubleCore[] getAllNative() {
            final int size = this.sizeNative();
            final NFDoubleCore.NFDoubleCoreData[] structures = (NFDoubleCore.NFDoubleCoreData[])new NFDoubleCore.NFDoubleCoreData().toArray(size);
            final NFDoubleCore[] values = new NFDoubleCore[size];
            NFRecord.LIBRARY.NFRecordGetDoubleCoresEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new NFDoubleCore(structures[i]);
            }
            return values;
        }
        
        protected NFDoubleCore getNative(final int index) {
            final NFDoubleCore value = new NFDoubleCore();
            NResult.check(NFRecord.LIBRARY.NFRecordGetDoubleCore(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final NFDoubleCore value) {
            NResult.check(NFRecord.LIBRARY.NFRecordInsertDoubleCore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final NFDoubleCore value) {
            NResult.check(NFRecord.LIBRARY.NFRecordSetDoubleCore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(NFRecord.LIBRARY.NFRecordClearDoubleCores(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFRecord.LIBRARY.NFRecordGetDoubleCoreCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NFRecord.LIBRARY.NFRecordGetDoubleCoreCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(NFRecord.LIBRARY.NFRecordRemoveDoubleCore(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final NFDoubleCore value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(NFRecord.LIBRARY.NFRecordSetDoubleCoreCapacity(this.getOwner().getHandle(), value));
        }
    }
    
    public final class MinutiaNeighborsCollection extends NArrayCollection<NFMinutiaNeighbor, NFMinutia>
    {
        protected MinutiaNeighborsCollection(final NObject owner, final NCollection<NFMinutia> baseCollection) {
            super(owner, baseCollection, false, true, false, false);
        }
        
        protected void addNative(final int baseIndex, final NFMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void addNative(final int baseIndex, final int index, final NFMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected int addWithIndexNative(final int baseIndex, final NFMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void clearNative(final int baseIndex) {
            throw new UnsupportedOperationException();
        }
        
        protected NFMinutiaNeighbor[] getAllNative(final int baseIndex) {
            final int size = this.sizeNative(baseIndex);
            final NFMinutiaNeighbor.NFMinutiaNeighborData[] structures = (NFMinutiaNeighbor.NFMinutiaNeighborData[])new NFMinutiaNeighbor.NFMinutiaNeighborData().toArray(size);
            final NFMinutiaNeighbor[] values = new NFMinutiaNeighbor[size];
            NFRecord.LIBRARY.NFRecordGetMinutiaNeighborsEx(this.getOwner().getHandle(), baseIndex, structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new NFMinutiaNeighbor(structures[i]);
            }
            return values;
        }
        
        protected NFMinutiaNeighbor getNative(final int baseIndex, final int index) {
            final NFMinutiaNeighbor value = new NFMinutiaNeighbor();
            NFRecord.LIBRARY.NFRecordGetMinutiaNeighbor(this.getOwner().getHandle(), baseIndex, index, value.getData());
            return value;
        }
        
        protected void removeNative(final int baseIndex, final int index) {
            throw new UnsupportedOperationException();
        }
        
        protected int removeNative(final int baseIndex, final NFMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int baseIndex, final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setNative(final int baseIndex, final int index, final NFMinutiaNeighbor value) {
            NFRecord.LIBRARY.NFRecordSetMinutiaNeighbor(this.getOwner().getHandle(), baseIndex, index, value.getData());
        }
        
        protected int sizeNative(final int baseIndex) {
            final IntByReference rValue = new IntByReference();
            NFRecord.LIBRARY.NFRecordGetMinutiaNeighborCount(this.getOwner().getHandle(), baseIndex, rValue);
            return rValue.getValue();
        }
    }
    
    interface NFRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NFRecordTypeOf();
        
        int NFRecordGetMaxSize(final int p0, final boolean p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final NativeSizeByReference p10);
        
        int NFRecordGetMaxSizeV1(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final NativeSizeByReference p7);
        
        int NFRecordCheck(final ByteBuffer p0, final NativeSize p1);
        
        int NFRecordGetWidthMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NFRecordGetHeightMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NFRecordGetHorzResolutionMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NFRecordGetVertResolutionMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NFRecordGetPositionMem(final Buffer p0, final NativeSize p1, final IntByReference p2);
        
        int NFRecordGetImpressionTypeMem(final Buffer p0, final NativeSize p1, final IntByReference p2);
        
        int NFRecordGetPatternClassMem(final Buffer p0, final NativeSize p1, final IntByReference p2);
        
        int NFRecordGetQualityMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NFRecordGetGMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NFRecordGetCbeffProductTypeMem(final Buffer p0, final NativeSize p1, final ShortByReference p2);
        
        int NFRecordCreateEx(final boolean p0, final short p1, final short p2, final short p3, final short p4, final int p5, final HNObjectByReference p6);
        
        int NFRecordCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final NativeSizeByReference p3, final HNObjectByReference p4);
        
        int NFRecordClone(final HNObject p0, final HNObjectByReference p1);
        
        int NFRecordGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int NFRecordGetSizeV1(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int NFRecordSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int NFRecordSaveToMemoryV1(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
        
        int NFRecordSortMinutiae(final HNObject p0, final int p1);
        
        int NFRecordTruncateMinutiaeByQuality(final HNObject p0, final byte p1, final int p2);
        
        int NFRecordTruncateMinutiae(final HNObject p0, final int p1);
        
        int NFRecordGetWidth(final HNObject p0, final ShortByReference p1);
        
        int NFRecordGetHeight(final HNObject p0, final ShortByReference p1);
        
        int NFRecordGetHorzResolution(final HNObject p0, final ShortByReference p1);
        
        int NFRecordGetVertResolution(final HNObject p0, final ShortByReference p1);
        
        int NFRecordGetPosition(final HNObject p0, final IntByReference p1);
        
        int NFRecordSetPosition(final HNObject p0, final int p1);
        
        int NFRecordGetImpressionType(final HNObject p0, final IntByReference p1);
        
        int NFRecordSetImpressionType(final HNObject p0, final int p1);
        
        int NFRecordGetPatternClass(final HNObject p0, final IntByReference p1);
        
        int NFRecordSetPatternClass(final HNObject p0, final int p1);
        
        int NFRecordGetQuality(final HNObject p0, final ShortByReference p1);
        
        int NFRecordSetQuality(final HNObject p0, final short p1);
        
        int NFRecordGetG(final HNObject p0, final ShortByReference p1);
        
        int NFRecordSetG(final HNObject p0, final short p1);
        
        int NFRecordGetCbeffProductType(final HNObject p0, final ShortByReference p1);
        
        int NFRecordSetCbeffProductType(final HNObject p0, final short p1);
        
        int NFRecordGetRidgeCountsType(final HNObject p0, final IntByReference p1);
        
        int NFRecordSetRidgeCountsType(final HNObject p0, final int p1);
        
        int NFRecordGetMinutiaFormat(final HNObject p0, final IntByReference p1);
        
        int NFRecordSetMinutiaFormat(final HNObject p0, final int p1);
        
        int NFRecordGetMinutiaCount(final HNObject p0, final IntByReference p1);
        
        int NFRecordGetMinutiaCapacity(final HNObject p0, final IntByReference p1);
        
        int NFRecordSetMinutiaCapacity(final HNObject p0, final int p1);
        
        int NFRecordGetMinutia(final HNObject p0, final int p1, final NFMinutia.NFMinutiaData p2);
        
        int NFRecordGetMinutiaeEx(final HNObject p0, final NFMinutia.NFMinutiaData[] p1, final int p2);
        
        int NFRecordSetMinutia(final HNObject p0, final int p1, final NFMinutia.NFMinutiaData p2);
        
        int NFRecordAddMinutia(final HNObject p0, final NFMinutia.NFMinutiaData p1);
        
        int NFRecordInsertMinutia(final HNObject p0, final int p1, final NFMinutia.NFMinutiaData p2);
        
        int NFRecordRemoveMinutia(final HNObject p0, final int p1);
        
        int NFRecordClearMinutiae(final HNObject p0);
        
        int NFRecordGetMinutiaNeighborCount(final HNObject p0, final int p1, final IntByReference p2);
        
        int NFRecordGetMinutiaNeighbor(final HNObject p0, final int p1, final int p2, final NFMinutiaNeighbor.NFMinutiaNeighborData p3);
        
        int NFRecordGetMinutiaNeighborsEx(final HNObject p0, final int p1, final NFMinutiaNeighbor.NFMinutiaNeighborData[] p2, final int p3);
        
        int NFRecordSetMinutiaNeighbor(final HNObject p0, final int p1, final int p2, final NFMinutiaNeighbor.NFMinutiaNeighborData p3);
        
        int NFRecordGetCoreCount(final HNObject p0, final IntByReference p1);
        
        int NFRecordGetCoreCapacity(final HNObject p0, final IntByReference p1);
        
        int NFRecordSetCoreCapacity(final HNObject p0, final int p1);
        
        int NFRecordGetCore(final HNObject p0, final int p1, final NFCore.NFCoreData p2);
        
        int NFRecordGetCoresEx(final HNObject p0, final NFCore.NFCoreData[] p1, final int p2);
        
        int NFRecordSetCore(final HNObject p0, final int p1, final NFCore.NFCoreData p2);
        
        int NFRecordAddCore(final HNObject p0, final NFCore.NFCoreData p1);
        
        int NFRecordInsertCore(final HNObject p0, final int p1, final NFCore.NFCoreData p2);
        
        int NFRecordRemoveCore(final HNObject p0, final int p1);
        
        int NFRecordClearCores(final HNObject p0);
        
        int NFRecordGetDeltaCount(final HNObject p0, final IntByReference p1);
        
        int NFRecordGetDelta(final HNObject p0, final int p1, final NFDelta.NFDeltaData p2);
        
        int NFRecordSetDelta(final HNObject p0, final int p1, final NFDelta.NFDeltaData p2);
        
        int NFRecordGetDeltasEx(final HNObject p0, final NFDelta.NFDeltaData[] p1, final int p2);
        
        int NFRecordGetDeltaCapacity(final HNObject p0, final IntByReference p1);
        
        int NFRecordSetDeltaCapacity(final HNObject p0, final int p1);
        
        int NFRecordAddDelta(final HNObject p0, final NFDelta.NFDeltaData p1);
        
        int NFRecordInsertDelta(final HNObject p0, final int p1, final NFDelta.NFDeltaData p2);
        
        int NFRecordRemoveDelta(final HNObject p0, final int p1);
        
        int NFRecordClearDeltas(final HNObject p0);
        
        int NFRecordGetDoubleCoreCount(final HNObject p0, final IntByReference p1);
        
        int NFRecordGetDoubleCore(final HNObject p0, final int p1, final NFDoubleCore.NFDoubleCoreData p2);
        
        int NFRecordSetDoubleCore(final HNObject p0, final int p1, final NFDoubleCore.NFDoubleCoreData p2);
        
        int NFRecordGetDoubleCoresEx(final HNObject p0, final NFDoubleCore.NFDoubleCoreData[] p1, final int p2);
        
        int NFRecordGetDoubleCoreCapacity(final HNObject p0, final IntByReference p1);
        
        int NFRecordSetDoubleCoreCapacity(final HNObject p0, final int p1);
        
        int NFRecordAddDoubleCore(final HNObject p0, final NFDoubleCore.NFDoubleCoreData p1);
        
        int NFRecordInsertDoubleCore(final HNObject p0, final int p1, final NFDoubleCore.NFDoubleCoreData p2);
        
        int NFRecordRemoveDoubleCore(final HNObject p0, final int p1);
        
        int NFRecordClearDoubleCores(final HNObject p0);
    }
}
