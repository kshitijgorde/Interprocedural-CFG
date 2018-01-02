// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.awt.NPointData;
import com.neurotec.util.NCollection;
import java.awt.Point;
import com.neurotec.biometrics.standards.util.ANRecordArrayCollection;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NObject;
import com.neurotec.biometrics.standards.util.ANRecordSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.BooleanByReference;
import java.util.Date;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType14Record extends ANFPImageAsciiBinaryRecord
{
    static final ANType14RecordLibrary LIBRARY;
    public static final int FIELD_FCD = 5;
    public static final int FIELD_PPD = 14;
    public static final int FIELD_AMP = 18;
    public static final int FIELD_SEG = 21;
    public static final int FIELD_NQM = 22;
    public static final int FIELD_SQM = 23;
    public static final int FIELD_FQM = 24;
    public static final int FIELD_ASEG = 25;
    public static final int MAX_AMPUTATION_COUNT = 4;
    public static final int MAX_NIST_QUALITY_METRIC_COUNT = 4;
    public static final int MAX_ALTERNATE_SEGMENT_COUNT = 4;
    public static final int MIN_ALTERNATE_SEGMENT_VERTEX_COUNT = 3;
    public static final int MAX_ALTERNATE_SEGMENT_VERTEX_COUNT = 99;
    public static final int NIST_QUALITY_METRIC_SCORE_EXCELLENT = 1;
    public static final int NIST_QUALITY_METRIC_SCORE_VERY_GOOD = 2;
    public static final int NIST_QUALITY_METRIC_SCORE_GOOD = 3;
    public static final int NIST_QUALITY_METRIC_SCORE_FAIR = 4;
    public static final int NIST_QUALITY_METRIC_SCORE_POOR = 5;
    public static final int NIST_QUALITY_METRIC_SCORE_NOT_AVAILABLE = 254;
    public static final int NIST_QUALITY_METRIC_SCORE_FAILED = 255;
    private AmputationCollection amputations;
    private SegmentCollection segments;
    private NistQualityMetricCollection nistQualityMetrics;
    private SegmentationQualityMetricCollection segmentationQualityMetrics;
    private AlternateSegmentCollection alternateSegments;
    private AlternateSegmentVerticesCollection alternateSegmentsVertices;
    public static final NNativeType NATIVE_TYPE;
    
    ANType14Record(final HNObject handle) {
        super(handle, ANType14Record.NATIVE_TYPE);
        this.amputations = new AmputationCollection(this);
        this.segments = new SegmentCollection(this);
        this.nistQualityMetrics = new NistQualityMetricCollection(this);
        this.segmentationQualityMetrics = new SegmentationQualityMetricCollection(this);
        this.alternateSegments = new AlternateSegmentCollection(this);
        this.alternateSegmentsVertices = new AlternateSegmentVerticesCollection(this, this.alternateSegments);
    }
    
    public Date getFingerprintCaptureDate() {
        return this.getDate();
    }
    
    public void setFingerprintCaptureDate(final Date value) {
        this.setDate(value);
    }
    
    public ANFPositionDescriptor getPrintPositionDescriptor() {
        final BooleanByReference rValue = new BooleanByReference();
        final ANFPositionDescriptor value = new ANFPositionDescriptor();
        NResult.check(ANType14Record.LIBRARY.ANType14RecordGetPrintPositionDescriptor(this.getHandle(), value.getData(), rValue));
        return rValue.getValue() ? value : null;
    }
    
    public void setPrintPositionDescriptor(final ANFPositionDescriptor value) {
        if (value == null || value.getData() == null) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordSetPrintPositionDescriptor(this.getHandle(), null));
        }
        else {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordSetPrintPositionDescriptor(this.getHandle(), value.getData()));
        }
        this.refresh();
    }
    
    public AmputationCollection getAmputations() {
        this.check();
        return this.amputations;
    }
    
    public SegmentCollection getSegments() {
        this.check();
        return this.segments;
    }
    
    public NistQualityMetricCollection getNistQualityMetrics() {
        this.check();
        return this.nistQualityMetrics;
    }
    
    public SegmentationQualityMetricCollection getSegmentationQualityMetrics() {
        this.check();
        return this.segmentationQualityMetrics;
    }
    
    public AlternateSegmentCollection getAlternateSegments() {
        this.check();
        return this.alternateSegments;
    }
    
    public AlternateSegmentVerticesCollection getAlternateSegmentsVertices() {
        this.check();
        return this.alternateSegmentsVertices;
    }
    
    static {
        LIBRARY = (ANType14RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType14RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType14Record.LIBRARY.ANType14RecordTypeOf());
    }
    
    public final class AmputationCollection extends ANRecordSimpleCollection<ANFAmputation>
    {
        protected AmputationCollection(final ANType14Record owner) {
            super(owner, 18, false, false, false);
        }
        
        protected void addNative(final ANFAmputation value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordAddAmputation(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final ANFAmputation value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFAmputation[] getAllNative() {
            final int size = this.sizeNative();
            final ANFAmputation.ANFAmputationData[] structures = (ANFAmputation.ANFAmputationData[])new ANFAmputation.ANFAmputationData().toArray(size);
            final ANFAmputation[] values = new ANFAmputation[size];
            ANType14Record.LIBRARY.ANType14RecordGetAmputationsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFAmputation(structures[i]);
            }
            return values;
        }
        
        protected ANFAmputation getNative(final int index) {
            final ANFAmputation value = new ANFAmputation();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetAmputation(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final ANFAmputation value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordInsertAmputation(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final ANFAmputation value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordSetAmputation(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordClearAmputations(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetAmputationCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordRemoveAmputation(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFAmputation value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class SegmentCollection extends ANRecordSimpleCollection<ANFSegment>
    {
        protected SegmentCollection(final ANType14Record owner) {
            super(owner, 21, false, false, false);
        }
        
        protected void addNative(final ANFSegment value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordAddSegment(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final ANFSegment value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFSegment[] getAllNative() {
            final int size = this.sizeNative();
            final ANFSegment.ANFSegmentData[] structures = (ANFSegment.ANFSegmentData[])new ANFSegment.ANFSegmentData().toArray(size);
            final ANFSegment[] values = new ANFSegment[size];
            ANType14Record.LIBRARY.ANType14RecordGetSegmentsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFSegment(structures[i]);
            }
            return values;
        }
        
        protected ANFSegment getNative(final int index) {
            final ANFSegment value = new ANFSegment();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetSegment(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final ANFSegment value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordInsertSegment(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final ANFSegment value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordSetSegment(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordClearSegments(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetSegmentCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordRemoveSegment(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFSegment value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class NistQualityMetricCollection extends ANRecordSimpleCollection<ANNistQualityMetric>
    {
        protected NistQualityMetricCollection(final ANType14Record owner) {
            super(owner, 22, false, false, false);
        }
        
        protected void addNative(final ANNistQualityMetric value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordAddNistQualityMetric(this.getOwner().getHandle(), value.getData()));
        }
        
        protected void addNative(final int index, final ANNistQualityMetric value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordInsertNistQualityMetric(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected int addWithIndexNative(final ANNistQualityMetric value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANNistQualityMetric[] getAllNative() {
            final int size = this.sizeNative();
            final ANNistQualityMetric.ANNistQualityMetricData[] structures = (ANNistQualityMetric.ANNistQualityMetricData[])new ANNistQualityMetric.ANNistQualityMetricData().toArray(size);
            final ANNistQualityMetric[] values = new ANNistQualityMetric[size];
            ANType14Record.LIBRARY.ANType14RecordGetNistQualityMetricsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANNistQualityMetric(structures[i]);
            }
            return values;
        }
        
        protected ANNistQualityMetric getNative(final int index) {
            final ANNistQualityMetric value = new ANNistQualityMetric();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetNistQualityMetric(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void setNative(final int index, final ANNistQualityMetric value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordSetNistQualityMetric(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordClearNistQualityMetrics(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetNistQualityMetricCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordRemoveNistQualityMetric(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANNistQualityMetric value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class SegmentationQualityMetricCollection extends ANRecordSimpleCollection<ANFPQualityMetric>
    {
        protected SegmentationQualityMetricCollection(final ANType14Record owner) {
            super(owner, 23, false, false, false);
        }
        
        protected void addNative(final ANFPQualityMetric value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordAddSegmentationQualityMetric(this.getOwner().getHandle(), value.getData()));
        }
        
        protected void addNative(final int index, final ANFPQualityMetric value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordInsertSegmentationQualityMetric(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected int addWithIndexNative(final ANFPQualityMetric value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFPQualityMetric[] getAllNative() {
            final int size = this.sizeNative();
            final ANFPQualityMetric.ANFPQualityMetricData[] structures = (ANFPQualityMetric.ANFPQualityMetricData[])new ANFPQualityMetric.ANFPQualityMetricData().toArray(size);
            final ANFPQualityMetric[] values = new ANFPQualityMetric[size];
            ANType14Record.LIBRARY.ANType14RecordGetSegmentationQualityMetricsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFPQualityMetric(structures[i]);
            }
            return values;
        }
        
        protected ANFPQualityMetric getNative(final int index) {
            final ANFPQualityMetric value = new ANFPQualityMetric();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetSegmentationQualityMetric(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void setNative(final int index, final ANFPQualityMetric value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordSetSegmentationQualityMetric(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordClearSegmentationQualityMetrics(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetSegmentationQualityMetricCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordRemoveSegmentationQualityMetric(this.getOwner().getHandle(), index));
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
    
    public final class AlternateSegmentCollection extends ANRecordSimpleCollection<ANFAlternateSegment>
    {
        protected AlternateSegmentCollection(final ANType14Record owner) {
            super(owner, 25, false, false, false);
        }
        
        protected void addNative(final ANFAlternateSegment value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordAddAlternateSegment(this.getOwner().getHandle(), value.getData()));
        }
        
        protected void addNative(final int index, final ANFAlternateSegment value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordInsertAlternateSegment(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected int addWithIndexNative(final ANFAlternateSegment value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFAlternateSegment[] getAllNative() {
            final int size = this.sizeNative();
            final ANFAlternateSegment.ANFAlternateSegmentData[] structures = (ANFAlternateSegment.ANFAlternateSegmentData[])new ANFAlternateSegment.ANFAlternateSegmentData().toArray(size);
            final ANFAlternateSegment[] values = new ANFAlternateSegment[size];
            ANType14Record.LIBRARY.ANType14RecordGetAlternateSegmentsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFAlternateSegment(structures[i]);
            }
            return values;
        }
        
        protected ANFAlternateSegment getNative(final int index) {
            final ANFAlternateSegment value = new ANFAlternateSegment();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetAlternateSegment(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void setNative(final int index, final ANFAlternateSegment value) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordSetAlternateSegment(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordClearAlternateSegments(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType14Record.LIBRARY.ANType14RecordGetAlternateSegmentCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType14Record.LIBRARY.ANType14RecordRemoveAlternateSegment(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFAlternateSegment value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    public final class AlternateSegmentVerticesCollection extends ANRecordArrayCollection<Point, ANFAlternateSegment>
    {
        protected AlternateSegmentVerticesCollection(final ANType14Record owner, final AlternateSegmentCollection segmentCollection) {
            super(owner, segmentCollection, false, true, false, false);
        }
        
        protected void addNative(final int baseIndex, final Point value) {
            ANType14Record.LIBRARY.ANType14RecordAddAlternateSegmentVertex(this.getOwner().getHandle(), baseIndex, new NPointData((int)value.getX(), (int)value.getY()));
        }
        
        protected void addNative(final int baseIndex, final int index, final Point value) {
            ANType14Record.LIBRARY.ANType14RecordInsertAlternateSegmentVertex(this.getOwner().getHandle(), baseIndex, index, new NPointData(value.x, value.y));
        }
        
        protected int addWithIndexNative(final int baseIndex, final Point value) {
            throw new UnsupportedOperationException();
        }
        
        protected void clearNative(final int baseIndex) {
            ANType14Record.LIBRARY.ANType14RecordClearAlternateSegmentVertices(this.getOwner().getHandle(), baseIndex);
        }
        
        protected Point[] getAllNative(final int baseIndex) {
            final int size = this.sizeNative(baseIndex);
            final NPointData[] structures = (NPointData[])new NPointData().toArray(size);
            final Point[] values = new Point[size];
            ANType14Record.LIBRARY.ANType14RecordGetAlternateSegmentVerticesEx(this.getOwner().getHandle(), baseIndex, structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new Point(structures[i].x, structures[i].y);
            }
            return values;
        }
        
        protected Point getNative(final int baseIndex, final int index) {
            final NPointData value = new NPointData();
            ANType14Record.LIBRARY.ANType14RecordGetAlternateSegmentVertex(this.getOwner().getHandle(), baseIndex, index, value);
            return new Point(value.x, value.y);
        }
        
        protected void removeNative(final int baseIndex, final int index) {
            ANType14Record.LIBRARY.ANType14RecordRemoveAlternateSegmentVertex(this.getOwner().getHandle(), baseIndex, index);
        }
        
        protected int removeNative(final int baseIndex, final Point value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int baseIndex, final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setNative(final int baseIndex, final int index, final Point value) {
            ANType14Record.LIBRARY.ANType14RecordSetAlternateSegmentVertex(this.getOwner().getHandle(), baseIndex, index, new NPointData(value.x, value.y));
        }
        
        protected int sizeNative(final int baseIndex) {
            final IntByReference rValue = new IntByReference();
            ANType14Record.LIBRARY.ANType14RecordGetAlternateSegmentVertexCount(this.getOwner().getHandle(), baseIndex, rValue);
            return rValue.getValue();
        }
    }
    
    interface ANType14RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType14RecordTypeOf();
        
        int ANType14RecordGetPrintPositionDescriptor(final HNObject p0, final ANFPositionDescriptor.ANFPositionDescriptorData p1, final BooleanByReference p2);
        
        int ANType14RecordSetPrintPositionDescriptor(final HNObject p0, final ANFPositionDescriptor.ANFPositionDescriptorData p1);
        
        int ANType14RecordGetAmputationCount(final HNObject p0, final IntByReference p1);
        
        int ANType14RecordGetAmputation(final HNObject p0, final int p1, final ANFAmputation.ANFAmputationData p2);
        
        int ANType14RecordGetAmputationsEx(final HNObject p0, final ANFAmputation.ANFAmputationData[] p1, final int p2);
        
        int ANType14RecordSetAmputation(final HNObject p0, final int p1, final ANFAmputation.ANFAmputationData p2);
        
        int ANType14RecordAddAmputation(final HNObject p0, final ANFAmputation.ANFAmputationData p1);
        
        int ANType14RecordInsertAmputation(final HNObject p0, final int p1, final ANFAmputation.ANFAmputationData p2);
        
        int ANType14RecordRemoveAmputation(final HNObject p0, final int p1);
        
        int ANType14RecordClearAmputations(final HNObject p0);
        
        int ANType14RecordGetSegmentCount(final HNObject p0, final IntByReference p1);
        
        int ANType14RecordGetSegment(final HNObject p0, final int p1, final ANFSegment.ANFSegmentData p2);
        
        int ANType14RecordGetSegmentsEx(final HNObject p0, final ANFSegment.ANFSegmentData[] p1, final int p2);
        
        int ANType14RecordSetSegment(final HNObject p0, final int p1, final ANFSegment.ANFSegmentData p2);
        
        int ANType14RecordAddSegment(final HNObject p0, final ANFSegment.ANFSegmentData p1);
        
        int ANType14RecordInsertSegment(final HNObject p0, final int p1, final ANFSegment.ANFSegmentData p2);
        
        int ANType14RecordRemoveSegment(final HNObject p0, final int p1);
        
        int ANType14RecordClearSegments(final HNObject p0);
        
        int ANType14RecordGetNistQualityMetricCount(final HNObject p0, final IntByReference p1);
        
        int ANType14RecordGetNistQualityMetric(final HNObject p0, final int p1, final ANNistQualityMetric.ANNistQualityMetricData p2);
        
        int ANType14RecordGetNistQualityMetricsEx(final HNObject p0, final ANNistQualityMetric.ANNistQualityMetricData[] p1, final int p2);
        
        int ANType14RecordSetNistQualityMetric(final HNObject p0, final int p1, final ANNistQualityMetric.ANNistQualityMetricData p2);
        
        int ANType14RecordAddNistQualityMetric(final HNObject p0, final ANNistQualityMetric.ANNistQualityMetricData p1);
        
        int ANType14RecordInsertNistQualityMetric(final HNObject p0, final int p1, final ANNistQualityMetric.ANNistQualityMetricData p2);
        
        int ANType14RecordRemoveNistQualityMetric(final HNObject p0, final int p1);
        
        int ANType14RecordClearNistQualityMetrics(final HNObject p0);
        
        int ANType14RecordGetSegmentationQualityMetricCount(final HNObject p0, final IntByReference p1);
        
        int ANType14RecordGetSegmentationQualityMetric(final HNObject p0, final int p1, final ANFPQualityMetric.ANFPQualityMetricData p2);
        
        int ANType14RecordGetSegmentationQualityMetricsEx(final HNObject p0, final ANFPQualityMetric.ANFPQualityMetricData[] p1, final int p2);
        
        int ANType14RecordSetSegmentationQualityMetric(final HNObject p0, final int p1, final ANFPQualityMetric.ANFPQualityMetricData p2);
        
        int ANType14RecordAddSegmentationQualityMetric(final HNObject p0, final ANFPQualityMetric.ANFPQualityMetricData p1);
        
        int ANType14RecordInsertSegmentationQualityMetric(final HNObject p0, final int p1, final ANFPQualityMetric.ANFPQualityMetricData p2);
        
        int ANType14RecordRemoveSegmentationQualityMetric(final HNObject p0, final int p1);
        
        int ANType14RecordClearSegmentationQualityMetrics(final HNObject p0);
        
        int ANType14RecordGetAlternateSegmentCount(final HNObject p0, final IntByReference p1);
        
        int ANType14RecordGetAlternateSegment(final HNObject p0, final int p1, final ANFAlternateSegment.ANFAlternateSegmentData p2);
        
        int ANType14RecordGetAlternateSegmentsEx(final HNObject p0, final ANFAlternateSegment.ANFAlternateSegmentData[] p1, final int p2);
        
        int ANType14RecordSetAlternateSegment(final HNObject p0, final int p1, final ANFAlternateSegment.ANFAlternateSegmentData p2);
        
        int ANType14RecordAddAlternateSegment(final HNObject p0, final ANFAlternateSegment.ANFAlternateSegmentData p1);
        
        int ANType14RecordInsertAlternateSegment(final HNObject p0, final int p1, final ANFAlternateSegment.ANFAlternateSegmentData p2);
        
        int ANType14RecordRemoveAlternateSegment(final HNObject p0, final int p1);
        
        int ANType14RecordClearAlternateSegments(final HNObject p0);
        
        int ANType14RecordGetAlternateSegmentVertexCount(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANType14RecordGetAlternateSegmentVertex(final HNObject p0, final int p1, final int p2, final NPointData p3);
        
        int ANType14RecordGetAlternateSegmentVerticesEx(final HNObject p0, final int p1, final NPointData[] p2, final int p3);
        
        int ANType14RecordSetAlternateSegmentVertex(final HNObject p0, final int p1, final int p2, final NPointData p3);
        
        int ANType14RecordAddAlternateSegmentVertex(final HNObject p0, final int p1, final NPointData p2);
        
        int ANType14RecordInsertAlternateSegmentVertex(final HNObject p0, final int p1, final int p2, final NPointData p3);
        
        int ANType14RecordRemoveAlternateSegmentVertex(final HNObject p0, final int p1, final int p2);
        
        int ANType14RecordClearAlternateSegmentVertices(final HNObject p0, final int p1);
    }
}
