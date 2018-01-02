// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.xy;

import org.jfree.util.ObjectUtilities;
import org.jfree.data.general.SeriesChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class XYIntervalSeriesCollection extends AbstractIntervalXYDataset implements IntervalXYDataset, Serializable
{
    private List data;
    
    public XYIntervalSeriesCollection() {
        this.data = new ArrayList();
    }
    
    public void addSeries(final XYIntervalSeries series) {
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
    
    public XYIntervalSeries getSeries(final int series) {
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
        final XYIntervalSeries s = this.data.get(series);
        return s.getX(item);
    }
    
    public double getStartXValue(final int series, final int item) {
        final XYIntervalSeries s = this.data.get(series);
        return s.getXLowValue(item);
    }
    
    public double getEndXValue(final int series, final int item) {
        final XYIntervalSeries s = this.data.get(series);
        return s.getXHighValue(item);
    }
    
    public double getYValue(final int series, final int item) {
        final XYIntervalSeries s = this.data.get(series);
        return s.getYValue(item);
    }
    
    public double getStartYValue(final int series, final int item) {
        final XYIntervalSeries s = this.data.get(series);
        return s.getYLowValue(item);
    }
    
    public double getEndYValue(final int series, final int item) {
        final XYIntervalSeries s = this.data.get(series);
        return s.getYHighValue(item);
    }
    
    public Number getY(final int series, final int item) {
        return new Double(this.getYValue(series, item));
    }
    
    public Number getStartX(final int series, final int item) {
        return new Double(this.getStartXValue(series, item));
    }
    
    public Number getEndX(final int series, final int item) {
        return new Double(this.getEndXValue(series, item));
    }
    
    public Number getStartY(final int series, final int item) {
        return new Double(this.getStartYValue(series, item));
    }
    
    public Number getEndY(final int series, final int item) {
        return new Double(this.getEndYValue(series, item));
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYIntervalSeriesCollection)) {
            return false;
        }
        final XYIntervalSeriesCollection that = (XYIntervalSeriesCollection)obj;
        return ObjectUtilities.equal(this.data, that.data);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final XYIntervalSeriesCollection clone = (XYIntervalSeriesCollection)super.clone();
        final int seriesCount = this.getSeriesCount();
        clone.data = new ArrayList(seriesCount);
        for (int i = 0; i < this.data.size(); ++i) {
            clone.data.set(i, this.getSeries(i).clone());
        }
        return clone;
    }
}
