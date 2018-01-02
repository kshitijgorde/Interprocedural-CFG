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

public class XIntervalSeriesCollection extends AbstractIntervalXYDataset implements IntervalXYDataset, Serializable
{
    private List data;
    
    public XIntervalSeriesCollection() {
        this.data = new ArrayList();
    }
    
    public void addSeries(final XIntervalSeries series) {
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
    
    public XIntervalSeries getSeries(final int series) {
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
        final XIntervalSeries s = this.data.get(series);
        final XIntervalDataItem di = (XIntervalDataItem)s.getDataItem(item);
        return di.getX();
    }
    
    public Number getY(final int series, final int item) {
        final XIntervalSeries s = this.data.get(series);
        final XIntervalDataItem di = (XIntervalDataItem)s.getDataItem(item);
        return new Double(di.getYValue());
    }
    
    public Number getStartX(final int series, final int item) {
        final XIntervalSeries s = this.data.get(series);
        final XIntervalDataItem di = (XIntervalDataItem)s.getDataItem(item);
        return new Double(di.getXLowValue());
    }
    
    public Number getEndX(final int series, final int item) {
        final XIntervalSeries s = this.data.get(series);
        final XIntervalDataItem di = (XIntervalDataItem)s.getDataItem(item);
        return new Double(di.getXHighValue());
    }
    
    public Number getStartY(final int series, final int item) {
        return this.getY(series, item);
    }
    
    public Number getEndY(final int series, final int item) {
        return this.getY(series, item);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XIntervalSeriesCollection)) {
            return false;
        }
        final XIntervalSeriesCollection that = (XIntervalSeriesCollection)obj;
        return ObjectUtilities.equal(this.data, that.data);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final XIntervalSeriesCollection clone = (XIntervalSeriesCollection)super.clone();
        clone.data = (List)ObjectUtilities.deepClone(this.data);
        return clone;
    }
}
