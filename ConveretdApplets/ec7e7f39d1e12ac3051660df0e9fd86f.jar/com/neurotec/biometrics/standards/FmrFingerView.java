// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NArrayCollection;
import com.neurotec.util.NSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.biometrics.NFRecord;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.util.NCollection;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class FmrFingerView extends NObject
{
    static final FmrFingerViewLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    public static final int MAX_DIMENSION = 16383;
    public static final int MAX_MINUTIA_COUNT = 255;
    public static final int MAX_CORE_COUNT = 15;
    public static final int MAX_DELTA_COUNT = 15;
    public static final int FLAG_SKIP_RIDGE_COUNTS = 65536;
    public static final int FLAG_SKIP_SINGULAR_POINTS = 131072;
    public static final int FLAG_PROCESS_ALL_EXTENDED_DATA = 16777216;
    public static final int FLAG_SKIP_NEUROTEC_FIELDS = 1073741824;
    public static final int FLAG_OLD_CONVERT = 536870912;
    public static final int FLAG_USE_NEUROTEC_FIELDS = Integer.MIN_VALUE;
    private MinutiaCollection minutiae;
    private MinutiaFourNeighborsCollection minutiaeFourNeighbors;
    private MinutiaEightNeighborsCollection minutiaeEightNeighbors;
    private CoreCollection cores;
    private DeltaCollection deltas;
    
    FmrFingerView(final HNObject handle) {
        super(handle, FmrFingerView.NATIVE_TYPE, false);
        this.minutiae = new MinutiaCollection(this);
        this.minutiaeFourNeighbors = new MinutiaFourNeighborsCollection(this, this.minutiae);
        this.minutiaeEightNeighbors = new MinutiaEightNeighborsCollection(this, this.minutiae);
        this.cores = new CoreCollection(this);
        this.deltas = new DeltaCollection(this);
    }
    
    static void checkStandard(final BdifStandard standard) {
    }
    
    static double angleToDouble(final byte value, final BdifStandard standard) {
        checkStandard(standard);
        return 6.283185307179586 - value * 3.141592653589793 / ((standard == BdifStandard.ANSI) ? 90 : 128);
    }
    
    static byte angleFromDouble(double value, final BdifStandard standard) {
        checkStandard(standard);
        value -= Math.floor(value / 6.283185307179586) * 6.283185307179586;
        value = Math.round((6.283185307179586 - value) * ((standard == BdifStandard.ANSI) ? 90 : 128) / 3.141592653589793);
        return (byte)((int)value % ((standard == BdifStandard.ANSI) ? 180 : 256));
    }
    
    static double angleToDoubleN(final int value, final BdifStandard standard) {
        checkStandard(standard);
        return (value < 0 || value >= ((standard == BdifStandard.ANSI) ? 180 : 256)) ? Double.NaN : (6.283185307179586 - value * 3.141592653589793 / ((standard == BdifStandard.ANSI) ? 90 : 128));
    }
    
    static int angleFromDoubleN(double value, final BdifStandard standard) {
        checkStandard(standard);
        if (Double.isNaN(value)) {
            return -1;
        }
        value -= Math.floor(value / 6.283185307179586) * 6.283185307179586;
        value = Math.round((6.283185307179586 - value) * ((standard == BdifStandard.ANSI) ? 90 : 128) / 3.141592653589793);
        return (int)value % ((standard == BdifStandard.ANSI) ? 180 : 256);
    }
    
    static String angleToString(final int value, final BdifStandard standard) {
        checkStandard(standard);
        return (value < -1 || value >= 180) ? String.valueOf(value) : ((value == -1) ? "None" : (String.valueOf(Math.round(angleToDouble((byte)value, standard) * 180.0 / 3.141592653589793 * 10.0) / 10L) + '°'));
    }
    
    public static int getMaxSize(final BdifStandard standard, final int minutiaCount, final boolean hasFourNeighborRidgeCounts, final boolean hasEightNeighborRidgeCounts, final int coreWithAngleCount, final int coreCount, final int deltaWithAngleCount, final int deltaCount) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetMaxSize(standard.getValue(), minutiaCount, hasFourNeighborRidgeCounts, hasEightNeighborRidgeCounts, coreWithAngleCount, coreCount, deltaWithAngleCount, deltaCount, rValue));
        return rValue.getValue().intValue();
    }
    
    public static int getMaxSize(final BdifStandard standard, final int minutiaCount, final boolean hasFourNeighborRidgeCounts, final boolean hasEightNeighborRidgeCounts, final int coreWithAngleCount, final int coreCount, final int deltaWithAngleCount, final int deltaCount, final boolean hasNeurotecCurvatures, final boolean hasNeurotecGs, final int neurotecBOWidth, final int neurotecBOHeight) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetMaxSizeEx(standard.getValue(), minutiaCount, hasFourNeighborRidgeCounts, hasEightNeighborRidgeCounts, coreWithAngleCount, coreCount, deltaWithAngleCount, deltaCount, hasNeurotecCurvatures, hasNeurotecGs, neurotecBOWidth, neurotecBOHeight, rValue));
        return rValue.getValue().intValue();
    }
    
    public NFRecord toNFRecord() {
        return this.toNFRecord(0);
    }
    
    public NFRecord toNFRecord(final int flags) {
        NFRecord value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewToNFRecord(this.getHandle(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NFRecord.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public FmrFinger getOwner() {
        return (FmrFinger)super.getOwner();
    }
    
    public BdifFPPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetFingerPosition(this.getHandle(), rValue));
        return BdifFPPosition.get(rValue.getValue());
    }
    
    public BdifFPImpressionType getImpressionType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetImpressionType(this.getHandle(), rValue));
        return BdifFPImpressionType.get(rValue.getValue());
    }
    
    public void setImpressionType(final BdifFPImpressionType value) {
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetImpressionType(this.getHandle(), value.getValue()));
    }
    
    public int getViewCount() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetViewCount(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getViewNumber() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetViewNumber(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public short getFingerQuality() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetFingerQuality(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setFingerQuality(final short value) {
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetFingerQuality(this.getHandle(), value));
    }
    
    public boolean isHasFourNeighborRidgeCounts() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewHasFourNeighborRidgeCounts(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setHasFourNeighborRidgeCounts(final boolean value) {
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetHasFourNeighborRidgeCounts(this.getHandle(), value));
    }
    
    public boolean isHasEightNeighborRidgeCounts() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewHasEightNeighborRidgeCounts(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setHasEightNeighborRidgeCounts(final boolean value) {
        NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetHasEightNeighborRidgeCounts(this.getHandle(), value));
    }
    
    public MinutiaCollection getMinutiae() {
        return this.minutiae;
    }
    
    public MinutiaFourNeighborsCollection getMinutiaeFourNeighbors() {
        return this.minutiaeFourNeighbors;
    }
    
    public MinutiaEightNeighborsCollection getMinutiaeEightNeighbors() {
        return this.minutiaeEightNeighbors;
    }
    
    public CoreCollection getCores() {
        return this.cores;
    }
    
    public DeltaCollection getDeltas() {
        return this.deltas;
    }
    
    protected void dispose(final boolean disposing) {
        this.minutiae = null;
        this.minutiaeFourNeighbors = null;
        this.minutiaeEightNeighbors = null;
        this.cores = null;
        this.deltas = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (FmrFingerViewLibrary)Native.loadLibrary("NBiometricStandards", FmrFingerViewLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(FmrFingerView.LIBRARY.FmrFingerViewTypeOf());
    }
    
    public final class MinutiaCollection extends NSimpleCollection<FmrMinutia>
    {
        protected MinutiaCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        protected void addNative(final FmrMinutia value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewAddMinutia(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final FmrMinutia value) {
            throw new UnsupportedOperationException();
        }
        
        protected FmrMinutia[] getAllNative() {
            final int size = this.sizeNative();
            final FmrMinutia.FmrMinutiaData[] structures = (FmrMinutia.FmrMinutiaData[])new FmrMinutia.FmrMinutiaData().toArray(size);
            final FmrMinutia[] values = new FmrMinutia[size];
            FmrFingerView.LIBRARY.FmrFingerViewGetMinutiaeEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new FmrMinutia(structures[i]);
            }
            return values;
        }
        
        protected FmrMinutia getNative(final int index) {
            final FmrMinutia value = new FmrMinutia();
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetMinutia(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final FmrMinutia value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewInsertMinutia(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final FmrMinutia value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetMinutia(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewClearMinutiae(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetMinutiaCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetMinutiaCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewRemoveMinutia(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final FmrMinutia value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetMinutiaCapacity(this.getOwner().getHandle(), value));
        }
    }
    
    public final class MinutiaFourNeighborsCollection extends NArrayCollection<BdifFPMinutiaNeighbor, FmrMinutia>
    {
        protected MinutiaFourNeighborsCollection(final NObject owner, final NCollection<FmrMinutia> baseCollection) {
            super(owner, baseCollection, false, true, false, false);
        }
        
        protected void addNative(final int baseIndex, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void addNative(final int baseIndex, final int index, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected int addWithIndexNative(final int baseIndex, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void clearNative(final int baseIndex) {
            throw new UnsupportedOperationException();
        }
        
        protected BdifFPMinutiaNeighbor[] getAllNative(final int baseIndex) {
            final int size = this.sizeNative(baseIndex);
            final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData[] structures = (BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData[])new BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData().toArray(size);
            final BdifFPMinutiaNeighbor[] values = new BdifFPMinutiaNeighbor[size];
            FmrFingerView.LIBRARY.FmrFingerViewGetMinutiaFourNeighborsEx(this.getOwner().getHandle(), baseIndex, structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new BdifFPMinutiaNeighbor(structures[i]);
            }
            return values;
        }
        
        protected BdifFPMinutiaNeighbor getNative(final int baseIndex, final int index) {
            final BdifFPMinutiaNeighbor value = new BdifFPMinutiaNeighbor();
            FmrFingerView.LIBRARY.FmrFingerViewGetMinutiaFourNeighbor(this.getOwner().getHandle(), baseIndex, index, value.getData());
            return value;
        }
        
        protected void removeNative(final int baseIndex, final int index) {
            throw new UnsupportedOperationException();
        }
        
        protected int removeNative(final int baseIndex, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int baseIndex, final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setNative(final int baseIndex, final int index, final BdifFPMinutiaNeighbor value) {
            FmrFingerView.LIBRARY.FmrFingerViewSetMinutiaFourNeighbor(this.getOwner().getHandle(), baseIndex, index, value.getData());
        }
        
        protected int sizeNative(final int baseIndex) {
            return 4;
        }
    }
    
    public final class MinutiaEightNeighborsCollection extends NArrayCollection<BdifFPMinutiaNeighbor, FmrMinutia>
    {
        protected MinutiaEightNeighborsCollection(final NObject owner, final NCollection<FmrMinutia> baseCollection) {
            super(owner, baseCollection, false, true, false, false);
        }
        
        protected void addNative(final int baseIndex, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void addNative(final int baseIndex, final int index, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected int addWithIndexNative(final int baseIndex, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void clearNative(final int baseIndex) {
            throw new UnsupportedOperationException();
        }
        
        protected BdifFPMinutiaNeighbor[] getAllNative(final int baseIndex) {
            final int size = this.sizeNative(baseIndex);
            final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData[] structures = (BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData[])new BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData().toArray(size);
            final BdifFPMinutiaNeighbor[] values = new BdifFPMinutiaNeighbor[size];
            FmrFingerView.LIBRARY.FmrFingerViewGetMinutiaEightNeighborsEx(this.getOwner().getHandle(), baseIndex, structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new BdifFPMinutiaNeighbor(structures[i]);
            }
            return values;
        }
        
        protected BdifFPMinutiaNeighbor getNative(final int baseIndex, final int index) {
            final BdifFPMinutiaNeighbor value = new BdifFPMinutiaNeighbor();
            FmrFingerView.LIBRARY.FmrFingerViewGetMinutiaEightNeighbor(this.getOwner().getHandle(), baseIndex, index, value.getData());
            return value;
        }
        
        protected void removeNative(final int baseIndex, final int index) {
            throw new UnsupportedOperationException();
        }
        
        protected int removeNative(final int baseIndex, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int baseIndex, final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setNative(final int baseIndex, final int index, final BdifFPMinutiaNeighbor value) {
            FmrFingerView.LIBRARY.FmrFingerViewSetMinutiaEightNeighbor(this.getOwner().getHandle(), baseIndex, index, value.getData());
        }
        
        protected int sizeNative(final int baseIndex) {
            return 8;
        }
    }
    
    public final class CoreCollection extends NSimpleCollection<FmrCore>
    {
        protected CoreCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        protected void addNative(final FmrCore value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewAddCore(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final FmrCore value) {
            throw new UnsupportedOperationException();
        }
        
        protected FmrCore[] getAllNative() {
            final int size = this.sizeNative();
            final FmrCore.FmrCoreData[] structures = (FmrCore.FmrCoreData[])new FmrCore.FmrCoreData().toArray(size);
            final FmrCore[] values = new FmrCore[size];
            FmrFingerView.LIBRARY.FmrFingerViewGetCoresEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new FmrCore(structures[i]);
            }
            return values;
        }
        
        protected FmrCore getNative(final int index) {
            final FmrCore value = new FmrCore();
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetCore(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final FmrCore value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewInsertCore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final FmrCore value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetCore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewClearCores(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetCoreCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetCoreCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewRemoveCore(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final FmrCore value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetCoreCapacity(this.getOwner().getHandle(), value));
        }
    }
    
    public final class DeltaCollection extends NSimpleCollection<FmrDelta>
    {
        protected DeltaCollection(final NObject owner) {
            super(owner, false, false, false);
        }
        
        protected void addNative(final FmrDelta value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewAddDelta(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final FmrDelta value) {
            throw new UnsupportedOperationException();
        }
        
        protected FmrDelta[] getAllNative() {
            final int size = this.sizeNative();
            final FmrDelta.FmrDeltaData[] structures = (FmrDelta.FmrDeltaData[])new FmrDelta.FmrDeltaData().toArray(size);
            final FmrDelta[] values = new FmrDelta[size];
            FmrFingerView.LIBRARY.FmrFingerViewGetDeltasEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new FmrDelta(structures[i]);
            }
            return values;
        }
        
        protected FmrDelta getNative(final int index) {
            final FmrDelta value = new FmrDelta();
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetDelta(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final FmrDelta value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewInsertDelta(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final FmrDelta value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetDelta(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewClearDeltas(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetDeltaCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewGetDeltaCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewRemoveDelta(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final FmrDelta value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(FmrFingerView.LIBRARY.FmrFingerViewSetDeltaCapacity(this.getOwner().getHandle(), value));
        }
    }
    
    interface FmrFingerViewLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType FmrFingerViewTypeOf();
        
        int FmrFingerViewGetMaxSize(final int p0, final int p1, final boolean p2, final boolean p3, final int p4, final int p5, final int p6, final int p7, final NativeSizeByReference p8);
        
        int FmrFingerViewGetMaxSizeEx(final int p0, final int p1, final boolean p2, final boolean p3, final int p4, final int p5, final int p6, final int p7, final boolean p8, final boolean p9, final int p10, final int p11, final NativeSizeByReference p12);
        
        int FmrFingerViewGetMinutiaCount(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewGetMinutia(final HNObject p0, final int p1, final FmrMinutia.FmrMinutiaData p2);
        
        int FmrFingerViewSetMinutia(final HNObject p0, final int p1, final FmrMinutia.FmrMinutiaData p2);
        
        int FmrFingerViewGetMinutiaeEx(final HNObject p0, final FmrMinutia.FmrMinutiaData[] p1, final int p2);
        
        int FmrFingerViewGetMinutiaCapacity(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewSetMinutiaCapacity(final HNObject p0, final int p1);
        
        int FmrFingerViewAddMinutia(final HNObject p0, final FmrMinutia.FmrMinutiaData p1);
        
        int FmrFingerViewInsertMinutia(final HNObject p0, final int p1, final FmrMinutia.FmrMinutiaData p2);
        
        int FmrFingerViewRemoveMinutia(final HNObject p0, final int p1);
        
        int FmrFingerViewClearMinutiae(final HNObject p0);
        
        int FmrFingerViewGetMinutiaFourNeighbor(final HNObject p0, final int p1, final int p2, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData p3);
        
        int FmrFingerViewSetMinutiaFourNeighbor(final HNObject p0, final int p1, final int p2, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData p3);
        
        int FmrFingerViewGetMinutiaFourNeighborsEx(final HNObject p0, final int p1, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData[] p2, final int p3);
        
        int FmrFingerViewGetMinutiaEightNeighbor(final HNObject p0, final int p1, final int p2, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData p3);
        
        int FmrFingerViewSetMinutiaEightNeighbor(final HNObject p0, final int p1, final int p2, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData p3);
        
        int FmrFingerViewGetMinutiaEightNeighborsEx(final HNObject p0, final int p1, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData[] p2, final int p3);
        
        int FmrFingerViewGetCoreCount(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewGetCore(final HNObject p0, final int p1, final FmrCore.FmrCoreData p2);
        
        int FmrFingerViewSetCore(final HNObject p0, final int p1, final FmrCore.FmrCoreData p2);
        
        int FmrFingerViewGetCoresEx(final HNObject p0, final FmrCore.FmrCoreData[] p1, final int p2);
        
        int FmrFingerViewGetCoreCapacity(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewSetCoreCapacity(final HNObject p0, final int p1);
        
        int FmrFingerViewAddCore(final HNObject p0, final FmrCore.FmrCoreData p1);
        
        int FmrFingerViewInsertCore(final HNObject p0, final int p1, final FmrCore.FmrCoreData p2);
        
        int FmrFingerViewRemoveCore(final HNObject p0, final int p1);
        
        int FmrFingerViewClearCores(final HNObject p0);
        
        int FmrFingerViewGetDeltaCount(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewGetDelta(final HNObject p0, final int p1, final FmrDelta.FmrDeltaData p2);
        
        int FmrFingerViewSetDelta(final HNObject p0, final int p1, final FmrDelta.FmrDeltaData p2);
        
        int FmrFingerViewGetDeltasEx(final HNObject p0, final FmrDelta.FmrDeltaData[] p1, final int p2);
        
        int FmrFingerViewGetDeltaCapacity(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewSetDeltaCapacity(final HNObject p0, final int p1);
        
        int FmrFingerViewAddDelta(final HNObject p0, final FmrDelta.FmrDeltaData p1);
        
        int FmrFingerViewInsertDelta(final HNObject p0, final int p1, final FmrDelta.FmrDeltaData p2);
        
        int FmrFingerViewRemoveDelta(final HNObject p0, final int p1);
        
        int FmrFingerViewClearDeltas(final HNObject p0);
        
        int FmrFingerViewToNFRecord(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FmrFingerViewGetFingerPosition(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewGetImpressionType(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewSetImpressionType(final HNObject p0, final int p1);
        
        int FmrFingerViewGetViewCount(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewGetViewNumber(final HNObject p0, final IntByReference p1);
        
        int FmrFingerViewGetFingerQuality(final HNObject p0, final ShortByReference p1);
        
        int FmrFingerViewSetFingerQuality(final HNObject p0, final short p1);
        
        int FmrFingerViewHasFourNeighborRidgeCounts(final HNObject p0, final BooleanByReference p1);
        
        int FmrFingerViewSetHasFourNeighborRidgeCounts(final HNObject p0, final boolean p1);
        
        int FmrFingerViewHasEightNeighborRidgeCounts(final HNObject p0, final BooleanByReference p1);
        
        int FmrFingerViewSetHasEightNeighborRidgeCounts(final HNObject p0, final boolean p1);
    }
}
