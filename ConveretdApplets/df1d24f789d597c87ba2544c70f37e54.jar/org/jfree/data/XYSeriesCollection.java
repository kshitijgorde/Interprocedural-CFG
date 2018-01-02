// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.ObjectUtils;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class XYSeriesCollection extends AbstractIntervalXYDataset implements IntervalXYDataset, DomainInfo, Serializable
{
    private List data;
    private IntervalXYDelegate intervalDelegate;
    
    public XYSeriesCollection() {
        this(null);
    }
    
    public XYSeriesCollection(final XYSeries series) {
        this.data = new ArrayList();
        this.intervalDelegate = new IntervalXYDelegate(this, false);
        if (series != null) {
            this.data.add(series);
            series.addChangeListener(this);
        }
    }
    
    public double getIntervalWidth() {
        return this.intervalDelegate.getIntervalWidth();
    }
    
    public void setIntervalWidth(final double width) {
        this.intervalDelegate.setIntervalWidth(width);
        this.fireDatasetChanged();
    }
    
    public double getIntervalPositionFactor() {
        return this.intervalDelegate.getIntervalPositionFactor();
    }
    
    public void setIntervalPositionFactor(final double factor) {
        this.intervalDelegate.setIntervalPositionFactor(factor);
        this.fireDatasetChanged();
    }
    
    public boolean isAutoWidth() {
        return this.intervalDelegate.isAutoWidth();
    }
    
    public void setAutoWidth(final boolean b) {
        this.intervalDelegate.setAutoWidth(b);
        this.fireDatasetChanged();
    }
    
    public void addSeries(final XYSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("XYSeriesCollection.addSeries(...): cannot add null series.");
        }
        this.data.add(series);
        this.intervalDelegate.seriesAdded(this.data.size() - 1);
        series.addChangeListener(this);
        this.fireDatasetChanged();
    }
    
    public int getSeriesCount() {
        return this.data.size();
    }
    
    public List getSeries() {
        return Collections.unmodifiableList((List<?>)this.data);
    }
    
    public XYSeries getSeries(final int series) {
        if (series < 0 || series > this.getSeriesCount()) {
            throw new IllegalArgumentException("XYSeriesCollection.getSeries(...): index outside valid range.");
        }
        final XYSeries ts = this.data.get(series);
        return ts;
    }
    
    public String getSeriesName(final int series) {
        return this.getSeries(series).getName();
    }
    
    public int getItemCount(final int series) {
        return this.getSeries(series).getItemCount();
    }
    
    public Number getXValue(final int series, final int item) {
        final XYSeries ts = this.data.get(series);
        final XYDataItem xyItem = ts.getDataItem(item);
        return xyItem.getX();
    }
    
    public Number getStartXValue(final int series, final int item) {
        return this.intervalDelegate.getStartXValue(series, item);
    }
    
    public Number getEndXValue(final int series, final int item) {
        return this.intervalDelegate.getEndXValue(series, item);
    }
    
    public Number getYValue(final int series, final int index) {
        final XYSeries ts = this.data.get(series);
        final XYDataItem xyItem = ts.getDataItem(index);
        return xyItem.getY();
    }
    
    public Number getStartYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    public Number getEndYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    public void removeAllSeries() {
        for (int i = 0; i < this.data.size(); ++i) {
            final XYSeries series = this.data.get(i);
            series.removeChangeListener(this);
        }
        this.data.clear();
        this.intervalDelegate.seriesRemoved();
        this.fireDatasetChanged();
    }
    
    public void removeSeries(final XYSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("XYSeriesCollection.removeSeries(...): cannot remove null series.");
        }
        if (this.data.contains(series)) {
            series.removeChangeListener(this);
            this.data.remove(series);
            this.intervalDelegate.seriesRemoved();
            this.fireDatasetChanged();
        }
    }
    
    public void removeSeries(final int series) {
        if (series < 0 || series > this.getSeriesCount()) {
            throw new IllegalArgumentException("XYSeriesCollection.removeSeries(...): index outside valid range.");
        }
        final XYSeries ts = this.data.get(series);
        ts.removeChangeListener(this);
        this.data.remove(series);
        this.intervalDelegate.seriesRemoved();
        this.fireDatasetChanged();
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof XYSeriesCollection) {
            final XYSeriesCollection c = (XYSeriesCollection)obj;
            return ObjectUtils.equal(this.data, c.data);
        }
        return false;
    }
    
    public int hashCode() {
        return (this.data != null) ? this.data.hashCode() : 0;
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
}
