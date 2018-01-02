// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.lang.NObject;
import com.neurotec.biometrics.standards.util.ANRecordSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public abstract class ANFPImageAsciiBinaryRecord extends ANImageAsciiBinaryRecord
{
    static final ANFPImageAsciiBinaryRecordLibrary LIBRARY;
    public static final int FIELD_IMP = 3;
    public static final int FIELD_FGP = 13;
    public static final int FIELD_PD = 14;
    public static final int FIELD_PPC = 15;
    public static final int MAX_POSITION_COUNT = 6;
    public static final int MAX_PRINT_POSITION_COUNT = 12;
    public static final NNativeType NATIVE_TYPE;
    private PositionCollection positions;
    private PrintPositionCollection printPositions;
    private QualityMetricCollection qualityMetrics;
    
    ANFPImageAsciiBinaryRecord(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
        this.positions = new PositionCollection(this);
        this.printPositions = new PrintPositionCollection(this);
        this.qualityMetrics = new QualityMetricCollection(this);
    }
    
    public final BdifFPImpressionType getImpressionType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetImpressionType(this.getHandle(), rValue));
        return BdifFPImpressionType.get(rValue.getValue());
    }
    
    public final void setImpressionType(final BdifFPImpressionType value) {
        NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordSetImpressionType(this.getHandle(), value.getValue()));
    }
    
    public final PositionCollection getPositions() {
        this.check();
        return this.positions;
    }
    
    public final PrintPositionCollection getPrintPositions() {
        this.check();
        return this.printPositions;
    }
    
    public final QualityMetricCollection getQualityMetrics() {
        this.check();
        return this.qualityMetrics;
    }
    
    static {
        LIBRARY = (ANFPImageAsciiBinaryRecordLibrary)Native.loadLibrary("NBiometricStandards", ANFPImageAsciiBinaryRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordTypeOf());
    }
    
    public final class PositionCollection extends ANRecordSimpleCollection<BdifFPPosition>
    {
        protected PositionCollection(final ANFPImageAsciiBinaryRecord owner) {
            super(owner, 13, false, false, false);
        }
        
        protected void addNative(final BdifFPPosition value) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordAddPosition(this.getOwner().getHandle(), value.getValue()));
        }
        
        protected int addWithIndexNative(final BdifFPPosition value) {
            throw new UnsupportedOperationException();
        }
        
        protected BdifFPPosition[] getAllNative() {
            final int size = this.sizeNative();
            final BdifFPPosition[] values = new BdifFPPosition[size];
            final int[] data = new int[size];
            ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetPositionsEx(this.getOwner().getHandle(), data, size);
            for (int i = 0; i < data.length; ++i) {
                values[i] = BdifFPPosition.get(i);
            }
            return values;
        }
        
        protected BdifFPPosition getNative(final int index) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetPosition(this.getOwner().getHandle(), index, rValue));
            return BdifFPPosition.get(rValue.getValue());
        }
        
        protected void addNative(final int index, final BdifFPPosition value) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordInsertPosition(this.getOwner().getHandle(), index, value.getValue()));
        }
        
        protected void setNative(final int index, final BdifFPPosition value) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordSetPosition(this.getOwner().getHandle(), index, value.getValue()));
        }
        
        protected void clearNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetPositionCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordRemovePosition(this.getOwner().getHandle(), index));
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
    
    public final class PrintPositionCollection extends ANRecordSimpleCollection<ANFPrintPosition>
    {
        protected PrintPositionCollection(final ANFPImageAsciiBinaryRecord owner) {
            super(owner, 15, false, false, false);
        }
        
        protected void addNative(final ANFPrintPosition value) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordAddPrintPosition(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final ANFPrintPosition value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFPrintPosition[] getAllNative() {
            final int size = this.sizeNative();
            final ANFPrintPosition.ANFPrintPositionData[] structures = (ANFPrintPosition.ANFPrintPositionData[])new ANFPrintPosition.ANFPrintPositionData().toArray(size);
            final ANFPrintPosition[] values = new ANFPrintPosition[size];
            ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetPrintPositionsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFPrintPosition(structures[i]);
            }
            return values;
        }
        
        protected ANFPrintPosition getNative(final int index) {
            final ANFPrintPosition value = new ANFPrintPosition();
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetPrintPosition(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final ANFPrintPosition value) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordInsertPrintPosition(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final ANFPrintPosition value) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordSetPrintPosition(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordClearPrintPositions(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetPrintPositionCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordRemovePrintPosition(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFPrintPosition value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class QualityMetricCollection extends ANRecordSimpleCollection<ANFPQualityMetric>
    {
        protected QualityMetricCollection(final ANFPImageAsciiBinaryRecord owner) {
            super(owner, 24, false, false, false);
        }
        
        protected void addNative(final ANFPQualityMetric value) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordAddQualityMetric(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final ANFPQualityMetric value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFPQualityMetric[] getAllNative() {
            final int size = this.sizeNative();
            final ANFPQualityMetric.ANFPQualityMetricData[] structures = (ANFPQualityMetric.ANFPQualityMetricData[])new ANFPQualityMetric.ANFPQualityMetricData().toArray(size);
            final ANFPQualityMetric[] values = new ANFPQualityMetric[size];
            ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetQualityMetricsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFPQualityMetric(structures[i]);
            }
            return values;
        }
        
        protected ANFPQualityMetric getNative(final int index) {
            final ANFPQualityMetric value = new ANFPQualityMetric();
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetQualityMetric(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final ANFPQualityMetric value) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordInsertQualityMetric(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final ANFPQualityMetric value) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordSetQualityMetric(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordClearQualityMetrics(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordGetQualityMetricCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANFPImageAsciiBinaryRecord.LIBRARY.ANFPImageAsciiBinaryRecordRemoveQualityMetric(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFPQualityMetric value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    interface ANFPImageAsciiBinaryRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANFPImageAsciiBinaryRecordTypeOf();
        
        int ANFPImageAsciiBinaryRecordGetPositionCount(final HNObject p0, final IntByReference p1);
        
        int ANFPImageAsciiBinaryRecordGetPosition(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANFPImageAsciiBinaryRecordGetPositionsEx(final HNObject p0, final int[] p1, final int p2);
        
        int ANFPImageAsciiBinaryRecordSetPosition(final HNObject p0, final int p1, final int p2);
        
        int ANFPImageAsciiBinaryRecordAddPosition(final HNObject p0, final int p1);
        
        int ANFPImageAsciiBinaryRecordInsertPosition(final HNObject p0, final int p1, final int p2);
        
        int ANFPImageAsciiBinaryRecordRemovePosition(final HNObject p0, final int p1);
        
        int ANFPImageAsciiBinaryRecordGetPrintPositionCount(final HNObject p0, final IntByReference p1);
        
        int ANFPImageAsciiBinaryRecordGetPrintPosition(final HNObject p0, final int p1, final ANFPrintPosition.ANFPrintPositionData p2);
        
        int ANFPImageAsciiBinaryRecordGetPrintPositionsEx(final HNObject p0, final ANFPrintPosition.ANFPrintPositionData[] p1, final int p2);
        
        int ANFPImageAsciiBinaryRecordSetPrintPosition(final HNObject p0, final int p1, final ANFPrintPosition.ANFPrintPositionData p2);
        
        int ANFPImageAsciiBinaryRecordAddPrintPosition(final HNObject p0, final ANFPrintPosition.ANFPrintPositionData p1);
        
        int ANFPImageAsciiBinaryRecordInsertPrintPosition(final HNObject p0, final int p1, final ANFPrintPosition.ANFPrintPositionData p2);
        
        int ANFPImageAsciiBinaryRecordRemovePrintPosition(final HNObject p0, final int p1);
        
        int ANFPImageAsciiBinaryRecordClearPrintPositions(final HNObject p0);
        
        int ANFPImageAsciiBinaryRecordGetQualityMetricCount(final HNObject p0, final IntByReference p1);
        
        int ANFPImageAsciiBinaryRecordGetQualityMetric(final HNObject p0, final int p1, final ANFPQualityMetric.ANFPQualityMetricData p2);
        
        int ANFPImageAsciiBinaryRecordGetQualityMetricsEx(final HNObject p0, final ANFPQualityMetric.ANFPQualityMetricData[] p1, final int p2);
        
        int ANFPImageAsciiBinaryRecordSetQualityMetric(final HNObject p0, final int p1, final ANFPQualityMetric.ANFPQualityMetricData p2);
        
        int ANFPImageAsciiBinaryRecordAddQualityMetric(final HNObject p0, final ANFPQualityMetric.ANFPQualityMetricData p1);
        
        int ANFPImageAsciiBinaryRecordInsertQualityMetric(final HNObject p0, final int p1, final ANFPQualityMetric.ANFPQualityMetricData p2);
        
        int ANFPImageAsciiBinaryRecordRemoveQualityMetric(final HNObject p0, final int p1);
        
        int ANFPImageAsciiBinaryRecordClearQualityMetrics(final HNObject p0);
        
        int ANFPImageAsciiBinaryRecordGetImpressionType(final HNObject p0, final IntByReference p1);
        
        int ANFPImageAsciiBinaryRecordSetImpressionType(final HNObject p0, final int p1);
    }
}
