// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.ObjectUtils;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class MatrixSeriesCollection extends AbstractXYZDataset implements XYZDataset, Serializable
{
    private List seriesList;
    
    public MatrixSeriesCollection() {
        this(null);
    }
    
    public MatrixSeriesCollection(final MatrixSeries series) {
        this.seriesList = new ArrayList();
        if (series != null) {
            this.seriesList.add(series);
            series.addChangeListener(this);
        }
    }
    
    public int getItemCount(final int seriesIndex) {
        return this.getSeries(seriesIndex).getItemCount();
    }
    
    public MatrixSeries getSeries(final int seriesIndex) {
        if (seriesIndex < 0 || seriesIndex > this.getSeriesCount()) {
            throw new IllegalArgumentException("MatrixSeriesCollection.getSeries(...): index outside valid range.");
        }
        final MatrixSeries series = this.seriesList.get(seriesIndex);
        return series;
    }
    
    public int getSeriesCount() {
        return this.seriesList.size();
    }
    
    public String getSeriesName(final int seriesIndex) {
        return this.getSeries(seriesIndex).getName();
    }
    
    public Number getXValue(final int seriesIndex, final int itemIndex) {
        final MatrixSeries series = this.seriesList.get(seriesIndex);
        final int x = series.getItemColumn(itemIndex);
        return new Integer(x);
    }
    
    public Number getYValue(final int seriesIndex, final int itemIndex) {
        final MatrixSeries series = this.seriesList.get(seriesIndex);
        final int y = series.getItemRow(itemIndex);
        return new Integer(y);
    }
    
    public Number getZValue(final int seriesIndex, final int itemIndex) {
        final MatrixSeries series = this.seriesList.get(seriesIndex);
        final Number z = series.getItem(itemIndex);
        return z;
    }
    
    public void addSeries(final MatrixSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("MatrixSeriesCollection.addSeries(...): cannot add null series.");
        }
        this.seriesList.add(series);
        series.addChangeListener(this);
        this.fireDatasetChanged();
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof MatrixSeriesCollection) {
            final MatrixSeriesCollection c = (MatrixSeriesCollection)obj;
            return ObjectUtils.equal(this.seriesList, c.seriesList);
        }
        return false;
    }
    
    public int hashCode() {
        return (this.seriesList != null) ? this.seriesList.hashCode() : 0;
    }
    
    public void removeAllSeries() {
        for (int i = 0; i < this.seriesList.size(); ++i) {
            final MatrixSeries series = this.seriesList.get(i);
            series.removeChangeListener(this);
        }
        this.seriesList.clear();
        this.fireDatasetChanged();
    }
    
    public void removeSeries(final MatrixSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("MatrixSeriesCollection.removeSeries(...): cannot remove null series.");
        }
        if (this.seriesList.contains(series)) {
            series.removeChangeListener(this);
            this.seriesList.remove(series);
            this.fireDatasetChanged();
        }
    }
    
    public void removeSeries(final int seriesIndex) {
        if (seriesIndex < 0 || seriesIndex > this.getSeriesCount()) {
            throw new IllegalArgumentException("MatrixSeriesCollection.removeSeries(...): index outside valid range.");
        }
        final MatrixSeries series = this.seriesList.get(seriesIndex);
        series.removeChangeListener(this);
        this.seriesList.remove(seriesIndex);
        this.fireDatasetChanged();
    }
}
