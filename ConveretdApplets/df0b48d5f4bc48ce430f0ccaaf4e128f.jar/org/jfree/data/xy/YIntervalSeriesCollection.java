// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.xy;

import java.util.Collection;
import org.jfree.util.ObjectUtilities;
import org.jfree.data.general.SeriesChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class YIntervalSeriesCollection extends AbstractIntervalXYDataset implements IntervalXYDataset, Serializable
{
    private List data;
    
    public YIntervalSeriesCollection() {
        this.data = new ArrayList();
    }
    
    public void addSeries(final YIntervalSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Null 'series' argument.");
        }
        this.data.add(series);
        series.addChangeListener(this);
        this.fireDatasetChanged();
    }
    
    public int getSeriesCount() {
        return this.data.size();
    }
    
    public YIntervalSeries getSeries(final int series) {
        if (series < 0 || series >= this.getSeriesCount()) {
            throw new IllegalArgumentException("Series index out of bounds");
        }
        return this.data.get(series);
    }
    
    public Comparable getSeriesKey(final int series) {
        return this.getSeries(series).getKey();
    }
    
    public int getItemCount(final int series) {
        return this.getSeries(series).getItemCount();
    }
    
    public Number getX(final int series, final int item) {
        final YIntervalSeries s = this.data.get(series);
        return s.getX(item);
    }
    
    public double getYValue(final int series, final int item) {
        final YIntervalSeries s = this.data.get(series);
        return s.getYValue(item);
    }
    
    public double getStartYValue(final int series, final int item) {
        final YIntervalSeries s = this.data.get(series);
        return s.getYLowValue(item);
    }
    
    public double getEndYValue(final int series, final int item) {
        final YIntervalSeries s = this.data.get(series);
        return s.getYHighValue(item);
    }
    
    public Number getY(final int series, final int item) {
        final YIntervalSeries s = this.data.get(series);
        return new Double(s.getYValue(item));
    }
    
    public Number getStartX(final int series, final int item) {
        return this.getX(series, item);
    }
    
    public Number getEndX(final int series, final int item) {
        return this.getX(series, item);
    }
    
    public Number getStartY(final int series, final int item) {
        final YIntervalSeries s = this.data.get(series);
        return new Double(s.getYLowValue(item));
    }
    
    public Number getEndY(final int series, final int item) {
        final YIntervalSeries s = this.data.get(series);
        return new Double(s.getYHighValue(item));
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof YIntervalSeriesCollection)) {
            return false;
        }
        final YIntervalSeriesCollection that = (YIntervalSeriesCollection)obj;
        return ObjectUtilities.equal(this.data, that.data);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final YIntervalSeriesCollection clone = (YIntervalSeriesCollection)super.clone();
        clone.data = (List)ObjectUtilities.deepClone(this.data);
        return clone;
    }
}
