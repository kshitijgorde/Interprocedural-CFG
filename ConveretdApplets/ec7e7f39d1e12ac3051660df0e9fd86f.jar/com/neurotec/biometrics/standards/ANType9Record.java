// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NCollection;
import com.neurotec.biometrics.standards.util.ANRecordArrayCollection;
import com.neurotec.biometrics.standards.util.ANRecordSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.WString;
import com.neurotec.lang.NObject;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.biometrics.NFRecord;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType9Record extends ANAsciiRecord
{
    static final ANType9RecordLibrary LIBRARY;
    public static final int FIELD_IMP = 3;
    public static final int FIELD_FMT = 4;
    public static final int FIELD_OFR = 5;
    public static final int FIELD_FGP = 6;
    public static final int FIELD_FPC = 7;
    public static final int FIELD_CRP = 8;
    public static final int FIELD_DLT = 9;
    public static final int FIELD_MIN = 10;
    public static final int FIELD_RDG = 11;
    public static final int FIELD_MRC = 12;
    public static final int MAX_FINGERPRINT_X = 4999;
    public static final int MAX_FINGERPRINT_Y = 4999;
    public static final int MAX_PALMPRINT_X = 13999;
    public static final int MAX_PALMPRINT_Y = 20999;
    public static final int MINUTIA_QUALITY_MANUAL = 0;
    public static final int MINUTIA_QUALITY_NOT_AVAILABLE = 1;
    public static final int MINUTIA_QUALITY_BEST = 2;
    public static final int MINUTIA_QUALITY_WORST = 63;
    public static final int FIELD_VENDOR_DEFINED_FEATURES_FROM = 13;
    public static final int FIELD_VENDOR_DEFINED_FEATURES_TO = 999999999;
    public static final int FIELD_STANDARD_FORMAT_FEATURES_FROM = 5;
    public static final int FIELD_STANDARD_FORMAT_FEATURES_TO = 12;
    public static final int FIELD_ALL_FROM = 1;
    public static final int FIELD_ALL_TO = 4;
    private PositionCollection positions;
    private PatternClassCollection patternClasses;
    private MinutiaCollection minutiae;
    private MinutiaNeighborsCollection minutiaeNeighbors;
    private CoreCollection cores;
    private DeltaCollection deltas;
    public static final NNativeType NATIVE_TYPE;
    
    ANType9Record(final HNObject handle) {
        super(handle, ANType9Record.NATIVE_TYPE);
        this.positions = new PositionCollection(this);
        this.patternClasses = new PatternClassCollection(this);
        this.cores = new CoreCollection(this);
        this.deltas = new DeltaCollection(this);
        this.minutiae = new MinutiaCollection(this);
        this.minutiaeNeighbors = new MinutiaNeighborsCollection(this, this.minutiae);
    }
    
    public NFRecord toNFRecord() {
        return this.toNFRecord(0);
    }
    
    public NFRecord toNFRecord(final int flags) {
        NFRecord value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANType9Record.LIBRARY.ANType9RecordToNFRecord(this.getHandle(), flags, rhValue));
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
    
    public void setOfrs(final String name, final ANFPMinutiaeMethod method, final String equipment) {
        NResult.check(ANType9Record.LIBRARY.ANType9RecordSetOfrs(this.getHandle(), new WString(name), method.getValue(), new WString(equipment)));
        this.refresh();
    }
    
    public BdifFPImpressionType getImpressionType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType9Record.LIBRARY.ANType9RecordGetImpressionType(this.getHandle(), rValue));
        return BdifFPImpressionType.get(rValue.getValue());
    }
    
    public void setImpressionType(final BdifFPImpressionType type) {
        NResult.check(ANType9Record.LIBRARY.ANType9RecordSetImpressionType(this.getHandle(), type.getValue()));
    }
    
    public boolean isMinutiaFormat() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANType9Record.LIBRARY.ANType9RecordGetMinutiaeFormat(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public boolean isHasMinutiae() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANType9Record.LIBRARY.ANType9RecordHasMinutiae(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public boolean isHasMinutiaeRidgeCounts() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANType9Record.LIBRARY.ANType9RecordHasMinutiaeRidgeCounts(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public boolean isHasMinutiaeRidgeCountsIndicator() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANType9Record.LIBRARY.ANType9RecordHasMinutiaeRidgeCountsIndicator(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public String getOfrsName() {
        final int pLen = ANType9Record.LIBRARY.ANType9RecordGetOfrsNameEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType9Record.LIBRARY.ANType9RecordGetOfrsNameEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public ANFPMinutiaeMethod getOfrsMethod() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType9Record.LIBRARY.ANType9RecordGetOfrsMethod(this.getHandle(), rValue));
        return ANFPMinutiaeMethod.get(rValue.getValue());
    }
    
    public String getOfrsEquipment() {
        final int pLen = ANType9Record.LIBRARY.ANType9RecordGetOfrsEquipmentEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType9Record.LIBRARY.ANType9RecordGetOfrsEquipmentEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public PositionCollection getPositions() {
        return this.positions;
    }
    
    public PatternClassCollection getPatternClasses() {
        return this.patternClasses;
    }
    
    public MinutiaCollection getMinutiae() {
        return this.minutiae;
    }
    
    public MinutiaNeighborsCollection getMinutiaeNeighbors() {
        return this.minutiaeNeighbors;
    }
    
    public CoreCollection getCores() {
        return this.cores;
    }
    
    public DeltaCollection getDeltas() {
        return this.deltas;
    }
    
    static {
        LIBRARY = (ANType9RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType9RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType9Record.LIBRARY.ANType9RecordTypeOf());
    }
    
    public final class PositionCollection extends ANRecordSimpleCollection<BdifFPPosition>
    {
        protected PositionCollection(final ANType9Record owner) {
            super(owner, 6, false, false, false);
        }
        
        protected void addNative(final BdifFPPosition value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordAddPosition(this.getOwner().getHandle(), value.getValue()));
        }
        
        protected int addWithIndexNative(final BdifFPPosition value) {
            throw new UnsupportedOperationException();
        }
        
        protected BdifFPPosition[] getAllNative() {
            final int size = this.sizeNative();
            final BdifFPPosition[] values = new BdifFPPosition[size];
            final int[] data = new int[size];
            ANType9Record.LIBRARY.ANType9RecordGetPositionsEx(this.getOwner().getHandle(), data, size);
            for (int i = 0; i < data.length; ++i) {
                values[i] = BdifFPPosition.get(i);
            }
            return values;
        }
        
        protected BdifFPPosition getNative(final int index) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordGetPosition(this.getOwner().getHandle(), index, rValue));
            return BdifFPPosition.get(rValue.getValue());
        }
        
        protected void addNative(final int index, final BdifFPPosition value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordInsertPosition(this.getOwner().getHandle(), index, value.getValue()));
        }
        
        protected void setNative(final int index, final BdifFPPosition value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordSetPosition(this.getOwner().getHandle(), index, value.getValue()));
        }
        
        protected void clearNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordGetPositionCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordRemovePosition(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final BdifFPPosition value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class PatternClassCollection extends ANRecordSimpleCollection<ANFPatternClass>
    {
        protected PatternClassCollection(final ANType9Record owner) {
            super(owner, 7, false, false, false);
        }
        
        protected void addNative(final ANFPatternClass value) {
            if (value.isStandard()) {
                NResult.check(ANType9Record.LIBRARY.ANType9RecordAddStandardPatternClass(this.getOwner().getHandle(), value.getStandardValue().getValue()));
            }
            else {
                NResult.check(ANType9Record.LIBRARY.ANType9RecordAddVendorPatternClass(this.getOwner().getHandle(), new WString(value.getVendorValue())));
            }
        }
        
        protected int addWithIndexNative(final ANFPatternClass value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFPatternClass[] getAllNative() {
            throw new UnsupportedOperationException();
        }
        
        protected ANFPatternClass getNative(final int index) {
            final BooleanByReference rIsStandard = new BooleanByReference();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordIsPatternClassStandard(this.getOwner().getHandle(), index, rIsStandard));
            if (rIsStandard.getValue()) {
                final IntByReference rValue = new IntByReference();
                NResult.check(ANType9Record.LIBRARY.ANType9RecordGetStandardPatternClass(this.getOwner().getHandle(), index, rValue));
                return ANFPatternClass.getStandard(BdifFPatternClass.get(rValue.getValue()));
            }
            final int pLen = ANType9Record.LIBRARY.ANType9RecordGetVendorPatternClassEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            String vendor;
            try {
                if (NResult.isFailed(ANType9Record.LIBRARY.ANType9RecordGetVendorPatternClassEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                vendor = pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
            return ANFPatternClass.getVendor(vendor);
        }
        
        protected void addNative(final int index, final ANFPatternClass value) {
            if (value.isStandard()) {
                NResult.check(ANType9Record.LIBRARY.ANType9RecordAddStandardPatternClass(this.getOwner().getHandle(), value.getStandardValue().getValue()));
            }
            else {
                NResult.check(ANType9Record.LIBRARY.ANType9RecordAddVendorPatternClass(this.getOwner().getHandle(), new WString(value.getVendorValue())));
            }
        }
        
        protected void setNative(final int index, final ANFPatternClass value) {
            if (value.isStandard()) {
                NResult.check(ANType9Record.LIBRARY.ANType9RecordAddStandardPatternClass(this.getOwner().getHandle(), value.getStandardValue().getValue()));
            }
            else {
                NResult.check(ANType9Record.LIBRARY.ANType9RecordAddVendorPatternClass(this.getOwner().getHandle(), new WString(value.getVendorValue())));
            }
        }
        
        protected void clearNative() {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordClearPatternClasses(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordGetPatternClassCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordRemovePatternClass(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFPatternClass value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
        
        public boolean add(final BdifFPatternClass value) {
            return this.add(ANFPatternClass.getStandard(value));
        }
        
        public boolean add(final String value) {
            return this.add(ANFPatternClass.getVendor(value));
        }
        
        public void add(final int index, final BdifFPatternClass value) {
            this.add(index, ANFPatternClass.getStandard(value));
        }
        
        public void add(final int index, final String value) {
            this.add(index, ANFPatternClass.getVendor(value));
        }
    }
    
    public final class MinutiaCollection extends ANRecordSimpleCollection<ANFPMinutia>
    {
        protected MinutiaCollection(final ANType9Record owner) {
            super(owner, 12, false, false, false);
        }
        
        protected void addNative(final ANFPMinutia value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordAddMinutia(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final ANFPMinutia value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFPMinutia[] getAllNative() {
            final int size = this.sizeNative();
            final ANFPMinutia.ANFPMinutiaData[] structures = (ANFPMinutia.ANFPMinutiaData[])new ANFPMinutia.ANFPMinutiaData().toArray(size);
            final ANFPMinutia[] values = new ANFPMinutia[size];
            ANType9Record.LIBRARY.ANType9RecordGetMinutiaeEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFPMinutia(structures[i]);
            }
            return values;
        }
        
        protected ANFPMinutia getNative(final int index) {
            final ANFPMinutia value = new ANFPMinutia();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordGetMinutia(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final ANFPMinutia value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordInsertMinutia(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final ANFPMinutia value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordSetMinutia(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordClearMinutiae(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordGetMinutiaCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordRemoveMinutia(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFPMinutia value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class CoreCollection extends ANRecordSimpleCollection<ANFCore>
    {
        protected CoreCollection(final ANType9Record owner) {
            super(owner, 8, false, false, false);
        }
        
        protected void addNative(final ANFCore value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordAddCore(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final ANFCore value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFCore[] getAllNative() {
            final int size = this.sizeNative();
            final ANFCore.ANFCoreData[] structures = (ANFCore.ANFCoreData[])new ANFCore.ANFCoreData().toArray(size);
            final ANFCore[] values = new ANFCore[size];
            ANType9Record.LIBRARY.ANType9RecordGetCoresEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFCore(structures[i]);
            }
            return values;
        }
        
        protected ANFCore getNative(final int index) {
            final ANFCore value = new ANFCore();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordGetCore(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final ANFCore value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordInsertCore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final ANFCore value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordSetCore(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordClearCores(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordGetCoreCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordRemoveCore(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFCore value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class DeltaCollection extends ANRecordSimpleCollection<ANFDelta>
    {
        protected DeltaCollection(final ANType9Record owner) {
            super(owner, 9, false, false, false);
        }
        
        protected void addNative(final ANFDelta value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordAddDelta(this.getOwner().getHandle(), value.getData()));
        }
        
        protected void addNative(final int index, final ANFDelta value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordInsertDelta(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected int addWithIndexNative(final ANFDelta value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFDelta[] getAllNative() {
            final int size = this.sizeNative();
            final ANFDelta.ANFDeltaData[] structures = (ANFDelta.ANFDeltaData[])new ANFDelta.ANFDeltaData().toArray(size);
            final ANFDelta[] values = new ANFDelta[size];
            ANType9Record.LIBRARY.ANType9RecordGetDeltasEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFDelta(structures[i]);
            }
            return values;
        }
        
        protected ANFDelta getNative(final int index) {
            final ANFDelta value = new ANFDelta();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordGetDelta(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void setNative(final int index, final ANFDelta value) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordSetDelta(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordClearDeltas(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType9Record.LIBRARY.ANType9RecordGetDeltaCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType9Record.LIBRARY.ANType9RecordRemoveDelta(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFDelta value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class MinutiaNeighborsCollection extends ANRecordArrayCollection<BdifFPMinutiaNeighbor, ANFPMinutia>
    {
        protected MinutiaNeighborsCollection(final ANType9Record owner, final MinutiaCollection minutiaCollection) {
            super(owner, minutiaCollection, false, true, false, false);
        }
        
        protected void addNative(final int baseIndex, final BdifFPMinutiaNeighbor value) {
            ANType9Record.LIBRARY.ANType9RecordAddMinutiaNeighbor(this.getOwner().getHandle(), baseIndex, value.getData());
        }
        
        protected void addNative(final int baseIndex, final int index, final BdifFPMinutiaNeighbor value) {
            ANType9Record.LIBRARY.ANType9RecordInsertMinutiaNeighbor(this.getOwner().getHandle(), baseIndex, index, value.getData());
        }
        
        protected int addWithIndexNative(final int baseIndex, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void clearNative(final int baseIndex) {
            ANType9Record.LIBRARY.ANType9RecordClearMinutiaNeighbors(this.getOwner().getHandle(), baseIndex);
        }
        
        protected BdifFPMinutiaNeighbor[] getAllNative(final int baseIndex) {
            final int size = this.sizeNative(baseIndex);
            final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData[] structures = (BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData[])new BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData().toArray(size);
            final BdifFPMinutiaNeighbor[] values = new BdifFPMinutiaNeighbor[size];
            ANType9Record.LIBRARY.ANType9RecordGetMinutiaNeighborsEx(this.getOwner().getHandle(), baseIndex, structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new BdifFPMinutiaNeighbor(structures[i]);
            }
            return values;
        }
        
        protected BdifFPMinutiaNeighbor getNative(final int baseIndex, final int index) {
            final BdifFPMinutiaNeighbor value = new BdifFPMinutiaNeighbor();
            ANType9Record.LIBRARY.ANType9RecordGetMinutiaNeighbor(this.getOwner().getHandle(), baseIndex, index, value.getData());
            return value;
        }
        
        protected void removeNative(final int baseIndex, final int index) {
            ANType9Record.LIBRARY.ANType9RecordRemoveMinutiaNeighbor(this.getOwner().getHandle(), baseIndex, index);
        }
        
        protected int removeNative(final int baseIndex, final BdifFPMinutiaNeighbor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int baseIndex, final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setNative(final int baseIndex, final int index, final BdifFPMinutiaNeighbor value) {
            ANType9Record.LIBRARY.ANType9RecordSetMinutiaNeighbor(this.getOwner().getHandle(), baseIndex, index, value.getData());
        }
        
        protected int sizeNative(final int baseIndex) {
            final IntByReference rValue = new IntByReference();
            ANType9Record.LIBRARY.ANType9RecordGetMinutiaNeighborCount(this.getOwner().getHandle(), baseIndex, rValue);
            return rValue.getValue();
        }
    }
    
    interface ANType9RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType9RecordTypeOf();
        
        int ANType9RecordGetPositionCount(final HNObject p0, final IntByReference p1);
        
        int ANType9RecordGetPosition(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANType9RecordGetPositionsEx(final HNObject p0, final int[] p1, final int p2);
        
        int ANType9RecordSetPosition(final HNObject p0, final int p1, final int p2);
        
        int ANType9RecordAddPosition(final HNObject p0, final int p1);
        
        int ANType9RecordInsertPosition(final HNObject p0, final int p1, final int p2);
        
        int ANType9RecordRemovePosition(final HNObject p0, final int p1);
        
        int ANType9RecordGetPatternClassCount(final HNObject p0, final IntByReference p1);
        
        int ANType9RecordIsPatternClassStandard(final HNObject p0, final int p1, final BooleanByReference p2);
        
        int ANType9RecordGetStandardPatternClass(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANType9RecordSetStandardPatternClass(final HNObject p0, final int p1, final int p2);
        
        int ANType9RecordGetVendorPatternClassEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANType9RecordSetVendorPatternClass(final HNObject p0, final int p1, final WString p2);
        
        int ANType9RecordAddStandardPatternClass(final HNObject p0, final int p1);
        
        int ANType9RecordAddVendorPatternClass(final HNObject p0, final WString p1);
        
        int ANType9RecordInsertVendorPatternClass(final HNObject p0, final int p1, final WString p2);
        
        int ANType9RecordInsertStandardPatternClass(final HNObject p0, final int p1, final int p2);
        
        int ANType9RecordRemovePatternClass(final HNObject p0, final int p1);
        
        int ANType9RecordClearPatternClasses(final HNObject p0);
        
        int ANType9RecordGetCoreCount(final HNObject p0, final IntByReference p1);
        
        int ANType9RecordGetCore(final HNObject p0, final int p1, final ANFCore.ANFCoreData p2);
        
        int ANType9RecordSetCore(final HNObject p0, final int p1, final ANFCore.ANFCoreData p2);
        
        int ANType9RecordGetCoresEx(final HNObject p0, final ANFCore.ANFCoreData[] p1, final int p2);
        
        int ANType9RecordAddCore(final HNObject p0, final ANFCore.ANFCoreData p1);
        
        int ANType9RecordInsertCore(final HNObject p0, final int p1, final ANFCore.ANFCoreData p2);
        
        int ANType9RecordRemoveCore(final HNObject p0, final int p1);
        
        int ANType9RecordClearCores(final HNObject p0);
        
        int ANType9RecordGetDeltaCount(final HNObject p0, final IntByReference p1);
        
        int ANType9RecordGetDelta(final HNObject p0, final int p1, final ANFDelta.ANFDeltaData p2);
        
        int ANType9RecordSetDelta(final HNObject p0, final int p1, final ANFDelta.ANFDeltaData p2);
        
        int ANType9RecordGetDeltasEx(final HNObject p0, final ANFDelta.ANFDeltaData[] p1, final int p2);
        
        int ANType9RecordAddDelta(final HNObject p0, final ANFDelta.ANFDeltaData p1);
        
        int ANType9RecordInsertDelta(final HNObject p0, final int p1, final ANFDelta.ANFDeltaData p2);
        
        int ANType9RecordRemoveDelta(final HNObject p0, final int p1);
        
        int ANType9RecordClearDeltas(final HNObject p0);
        
        int ANType9RecordGetMinutiaCount(final HNObject p0, final IntByReference p1);
        
        int ANType9RecordGetMinutia(final HNObject p0, final int p1, final ANFPMinutia.ANFPMinutiaData p2);
        
        int ANType9RecordSetMinutia(final HNObject p0, final int p1, final ANFPMinutia.ANFPMinutiaData p2);
        
        int ANType9RecordGetMinutiaeEx(final HNObject p0, final ANFPMinutia.ANFPMinutiaData[] p1, final int p2);
        
        int ANType9RecordAddMinutia(final HNObject p0, final ANFPMinutia.ANFPMinutiaData p1);
        
        int ANType9RecordInsertMinutia(final HNObject p0, final int p1, final ANFPMinutia.ANFPMinutiaData p2);
        
        int ANType9RecordRemoveMinutia(final HNObject p0, final int p1);
        
        int ANType9RecordClearMinutiae(final HNObject p0);
        
        int ANType9RecordGetMinutiaNeighborCount(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANType9RecordGetMinutiaNeighbor(final HNObject p0, final int p1, final int p2, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData p3);
        
        int ANType9RecordSetMinutiaNeighbor(final HNObject p0, final int p1, final int p2, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData p3);
        
        int ANType9RecordGetMinutiaNeighborsEx(final HNObject p0, final int p1, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData[] p2, final int p3);
        
        int ANType9RecordAddMinutiaNeighbor(final HNObject p0, final int p1, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData p2);
        
        int ANType9RecordInsertMinutiaNeighbor(final HNObject p0, final int p1, final int p2, final BdifFPMinutiaNeighbor.BdifFPMinutiaNeighborData p3);
        
        int ANType9RecordRemoveMinutiaNeighbor(final HNObject p0, final int p1, final int p2);
        
        int ANType9RecordClearMinutiaNeighbors(final HNObject p0, final int p1);
        
        int ANType9RecordToNFRecord(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int ANType9RecordGetImpressionType(final HNObject p0, final IntByReference p1);
        
        int ANType9RecordSetImpressionType(final HNObject p0, final int p1);
        
        int ANType9RecordGetMinutiaeFormat(final HNObject p0, final BooleanByReference p1);
        
        int ANType9RecordHasMinutiae(final HNObject p0, final BooleanByReference p1);
        
        int ANType9RecordHasMinutiaeRidgeCounts(final HNObject p0, final BooleanByReference p1);
        
        int ANType9RecordHasMinutiaeRidgeCountsIndicator(final HNObject p0, final BooleanByReference p1);
        
        int ANType9RecordGetOfrsNameEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType9RecordGetOfrsMethod(final HNObject p0, final IntByReference p1);
        
        int ANType9RecordGetOfrsEquipmentEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType9RecordSetOfrs(final HNObject p0, final WString p1, final int p2, final WString p3);
    }
}
