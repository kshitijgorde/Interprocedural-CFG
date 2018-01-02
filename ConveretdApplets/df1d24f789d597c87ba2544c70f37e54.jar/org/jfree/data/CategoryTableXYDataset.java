// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public class CategoryTableXYDataset extends AbstractIntervalXYDataset implements TableXYDataset, IntervalXYDataset, DomainInfo
{
    private DefaultKeyedValues2D values;
    private IntervalXYDelegate intervalDelegate;
    
    public CategoryTableXYDataset() {
        this.values = new DefaultKeyedValues2D(true);
        this.intervalDelegate = new IntervalXYDelegate(this);
    }
    
    public void add(final double x, final double y, final String seriesName) {
        this.add(new Double(x), new Double(y), seriesName, true);
    }
    
    public void add(final Number x, final Number y, final String seriesName, final boolean notify) {
        this.values.addValue(y, (Comparable)x, seriesName);
        final int series = this.values.getColumnIndex(seriesName);
        final int item = this.values.getRowIndex((Comparable)x);
        this.intervalDelegate.itemAdded(series, item);
        if (notify) {
            this.fireDatasetChanged();
        }
    }
    
    public void remove(final double x, final String seriesName) {
        this.remove(new Double(x), seriesName, true);
    }
    
    public void remove(final Number x, final String seriesName, final boolean notify) {
        this.values.removeValue((Comparable)x, seriesName);
        this.intervalDelegate.itemRemoved(x.doubleValue());
        if (notify) {
            this.fireDatasetChanged();
        }
    }
    
    public int getSeriesCount() {
        return this.values.getColumnCount();
    }
    
    public String getSeriesName(final int series) {
        return this.values.getColumnKey(series).toString();
    }
    
    public int getItemCount() {
        return this.values.getRowCount();
    }
    
    public int getItemCount(final int series) {
        return this.getItemCount();
    }
    
    public Number getXValue(final int series, final int item) {
        return (Number)this.values.getRowKey(item);
    }
    
    public Number getStartXValue(final int series, final int item) {
        return this.intervalDelegate.getStartXValue(series, item);
    }
    
    public Number getEndXValue(final int series, final int item) {
        return this.intervalDelegate.getEndXValue(series, item);
    }
    
    public Number getYValue(final int series, final int item) {
        return this.values.getValue(item, series);
    }
    
    public Number getStartYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    public Number getEndYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    public Range getDomainRange() {
        return this.intervalDelegate.getDomainRange();
    }
    
    public Number getMaximumDomainValue() {
        return this.intervalDelegate.getMaximumDomainValue();
    }
    
    public Number getMinimumDomainValue() {
        return this.intervalDelegate.getMinimumDomainValue();
    }
    
    public double getIntervalPositionFactor() {
        return this.intervalDelegate.getIntervalPositionFactor();
    }
    
    public void setIntervalPositionFactor(final double d) {
        this.intervalDelegate.setIntervalPositionFactor(d);
        this.fireDatasetChanged();
    }
    
    public double getIntervalWidth() {
        return this.intervalDelegate.getIntervalWidth();
    }
    
    public void setIntervalWidth(final double d) {
        this.intervalDelegate.setIntervalWidth(d);
        this.fireDatasetChanged();
    }
    
    public boolean isAutoWidth() {
        return this.intervalDelegate.isAutoWidth();
    }
    
    public void setAutoWidth(final boolean b) {
        this.intervalDelegate.setAutoWidth(b);
        this.fireDatasetChanged();
    }
}
