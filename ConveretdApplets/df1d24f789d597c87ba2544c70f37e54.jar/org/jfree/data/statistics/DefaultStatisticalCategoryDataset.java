// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.statistics;

import java.util.List;
import org.jfree.data.MeanAndStandardDeviation;
import org.jfree.data.Range;
import org.jfree.data.KeyedObjects2D;
import org.jfree.data.RangeInfo;
import org.jfree.data.AbstractDataset;

public class DefaultStatisticalCategoryDataset extends AbstractDataset implements StatisticalCategoryDataset, RangeInfo
{
    private KeyedObjects2D data;
    private Number minimumRangeValue;
    private Number maximumRangeValue;
    private Range valueRange;
    
    public DefaultStatisticalCategoryDataset() {
        this.data = new KeyedObjects2D();
        this.minimumRangeValue = new Double(0.0);
        this.maximumRangeValue = new Double(0.0);
        this.valueRange = new Range(0.0, 0.0);
    }
    
    public Number getMeanValue(final int row, final int column) {
        Number result = null;
        final MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(row, column);
        if (masd != null) {
            result = masd.getMean();
        }
        return result;
    }
    
    public Number getValue(final int row, final int column) {
        return this.getMeanValue(row, column);
    }
    
    public Number getValue(final Comparable rowKey, final Comparable columnKey) {
        return this.getMeanValue(rowKey, columnKey);
    }
    
    public Number getMeanValue(final Comparable rowKey, final Comparable columnKey) {
        Number result = null;
        final MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(rowKey, columnKey);
        if (masd != null) {
            result = masd.getMean();
        }
        return result;
    }
    
    public Number getStdDevValue(final int row, final int column) {
        Number result = null;
        final MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(row, column);
        if (masd != null) {
            result = masd.getStandardDeviation();
        }
        return result;
    }
    
    public Number getStdDevValue(final Comparable rowKey, final Comparable columnKey) {
        Number result = null;
        final MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(rowKey, columnKey);
        if (masd != null) {
            result = masd.getStandardDeviation();
        }
        return result;
    }
    
    public int getColumnIndex(final Comparable key) {
        return this.data.getColumnIndex(key);
    }
    
    public Comparable getColumnKey(final int column) {
        return this.data.getColumnKey(column);
    }
    
    public List getColumnKeys() {
        return this.data.getColumnKeys();
    }
    
    public int getRowIndex(final Comparable key) {
        return this.data.getRowIndex(key);
    }
    
    public Comparable getRowKey(final int row) {
        return this.data.getRowKey(row);
    }
    
    public List getRowKeys() {
        return this.data.getRowKeys();
    }
    
    public int getRowCount() {
        return this.data.getRowCount();
    }
    
    public int getColumnCount() {
        return this.data.getColumnCount();
    }
    
    public void add(final double mean, final double standardDeviation, final Comparable rowKey, final Comparable columnKey) {
        final MeanAndStandardDeviation item = new MeanAndStandardDeviation(new Double(mean), new Double(standardDeviation));
        this.data.addObject(item, rowKey, columnKey);
        if (mean + standardDeviation > this.maximumRangeValue.doubleValue()) {
            this.maximumRangeValue = new Double(mean + standardDeviation);
            this.valueRange = new Range(this.minimumRangeValue.doubleValue(), this.maximumRangeValue.doubleValue());
        }
        if (mean - standardDeviation < this.minimumRangeValue.doubleValue()) {
            this.minimumRangeValue = new Double(mean - standardDeviation);
            this.valueRange = new Range(this.minimumRangeValue.doubleValue(), this.maximumRangeValue.doubleValue());
        }
        this.fireDatasetChanged();
    }
    
    public Number getMinimumRangeValue() {
        return this.minimumRangeValue;
    }
    
    public Number getMaximumRangeValue() {
        return this.maximumRangeValue;
    }
    
    public Range getValueRange() {
        return this.valueRange;
    }
}
