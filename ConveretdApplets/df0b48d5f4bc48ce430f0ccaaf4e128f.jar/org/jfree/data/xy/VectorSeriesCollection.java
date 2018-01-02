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

public class VectorSeriesCollection extends AbstractXYDataset implements VectorXYDataset, Serializable
{
    private List data;
    
    public VectorSeriesCollection() {
        this.data = new ArrayList();
    }
    
    public void addSeries(final VectorSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Null 'series' argument.");
        }
        this.data.add(series);
        series.addChangeListener(this);
        this.fireDatasetChanged();
    }
    
    public boolean removeSeries(final VectorSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Null 'series' argument.");
        }
        final boolean removed = this.data.remove(series);
        if (removed) {
            series.removeChangeListener(this);
            this.fireDatasetChanged();
        }
        return removed;
    }
    
    public void removeAllSeries() {
        for (int i = 0; i < this.data.size(); ++i) {
            final VectorSeries series = this.data.get(i);
            series.removeChangeListener(this);
        }
        this.data.clear();
        this.fireDatasetChanged();
    }
    
    public int getSeriesCount() {
        return this.data.size();
    }
    
    public VectorSeries getSeries(final int series) {
        if (series < 0 || series >= this.getSeriesCount()) {
            throw new IllegalArgumentException("Series index out of bounds");
        }
        return this.data.get(series);
    }
    
    public Comparable getSeriesKey(final int series) {
        return this.getSeries(series).getKey();
    }
    
    public int indexOf(final VectorSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Null 'series' argument.");
        }
        return this.data.indexOf(series);
    }
    
    public int getItemCount(final int series) {
        return this.getSeries(series).getItemCount();
    }
    
    public double getXValue(final int series, final int item) {
        final VectorSeries s = this.data.get(series);
        final VectorDataItem di = (VectorDataItem)s.getDataItem(item);
        return di.getXValue();
    }
    
    public Number getX(final int series, final int item) {
        return new Double(this.getXValue(series, item));
    }
    
    public double getYValue(final int series, final int item) {
        final VectorSeries s = this.data.get(series);
        final VectorDataItem di = (VectorDataItem)s.getDataItem(item);
        return di.getYValue();
    }
    
    public Number getY(final int series, final int item) {
        return new Double(this.getYValue(series, item));
    }
    
    public Vector getVector(final int series, final int item) {
        final VectorSeries s = this.data.get(series);
        final VectorDataItem di = (VectorDataItem)s.getDataItem(item);
        return di.getVector();
    }
    
    public double getVectorXValue(final int series, final int item) {
        final VectorSeries s = this.data.get(series);
        final VectorDataItem di = (VectorDataItem)s.getDataItem(item);
        return di.getVectorX();
    }
    
    public double getVectorYValue(final int series, final int item) {
        final VectorSeries s = this.data.get(series);
        final VectorDataItem di = (VectorDataItem)s.getDataItem(item);
        return di.getVectorY();
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VectorSeriesCollection)) {
            return false;
        }
        final VectorSeriesCollection that = (VectorSeriesCollection)obj;
        return ObjectUtilities.equal(this.data, that.data);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final VectorSeriesCollection clone = (VectorSeriesCollection)super.clone();
        clone.data = (List)ObjectUtilities.deepClone(this.data);
        return clone;
    }
}
