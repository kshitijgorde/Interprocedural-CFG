// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.ObjectUtils;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DefaultTableXYDataset extends AbstractIntervalXYDataset implements TableXYDataset, IntervalXYDataset, DomainInfo
{
    private List data;
    private HashSet xPoints;
    private boolean propagateEvents;
    private boolean autoPrune;
    private IntervalXYDelegate intervalDelegate;
    
    public DefaultTableXYDataset() {
        this(false);
    }
    
    public DefaultTableXYDataset(final boolean autoPrune) {
        this.data = null;
        this.xPoints = null;
        this.propagateEvents = true;
        this.autoPrune = false;
        this.autoPrune = autoPrune;
        this.data = new ArrayList();
        this.xPoints = new HashSet();
        this.intervalDelegate = new IntervalXYDelegate(this, false);
    }
    
    public DefaultTableXYDataset(final XYSeries series) {
        this.data = null;
        this.xPoints = null;
        this.propagateEvents = true;
        this.autoPrune = false;
        this.data = new ArrayList();
        this.xPoints = new HashSet();
        if (series != null) {
            if (series.getAllowDuplicateXValues()) {
                throw new IllegalArgumentException("Cannot accept a series that allows duplicate values. Use XYSeries(seriesName, false) constructor.");
            }
            this.updateXPoints(series);
            this.data.add(series);
            series.addChangeListener(this);
        }
        this.autoPrune = false;
    }
    
    public boolean isAutoPrune() {
        return this.autoPrune;
    }
    
    public void addSeries(final XYSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Null 'series' argument.");
        }
        if (series.getAllowDuplicateXValues()) {
            throw new IllegalArgumentException("Cannot accept XY Series that allow duplicate values.  Use XYSeries(seriesName, false) constructor.");
        }
        this.updateXPoints(series);
        this.data.add(series);
        series.addChangeListener(this);
        this.fireDatasetChanged();
    }
    
    private void updateXPoints(final XYSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Null 'series' not permitted.");
        }
        final HashSet seriesXPoints = new HashSet();
        final boolean savedState = this.propagateEvents;
        this.propagateEvents = false;
        for (int itemNo = 0; itemNo < series.getItemCount(); ++itemNo) {
            final Number xValue = series.getXValue(itemNo);
            seriesXPoints.add(xValue);
            if (!this.xPoints.contains(xValue)) {
                this.xPoints.add(xValue);
                for (int seriesNo = 0; seriesNo < this.data.size(); ++seriesNo) {
                    final XYSeries dataSeries = this.data.get(seriesNo);
                    if (!dataSeries.equals(series)) {
                        dataSeries.add(xValue, null);
                    }
                }
            }
        }
        for (final Number xPoint : this.xPoints) {
            if (!seriesXPoints.contains(xPoint)) {
                series.add(xPoint, null);
            }
        }
        this.propagateEvents = savedState;
    }
    
    public void updateXPoints() {
        this.propagateEvents = false;
        for (int s = 0; s < this.data.size(); ++s) {
            this.updateXPoints(this.data.get(s));
        }
        if (this.autoPrune) {
            this.prune();
        }
        this.propagateEvents = true;
    }
    
    public int getSeriesCount() {
        return this.data.size();
    }
    
    public int getItemCount() {
        if (this.xPoints == null) {
            return 0;
        }
        return this.xPoints.size();
    }
    
    public XYSeries getSeries(final int series) {
        if (series < 0 || series > this.getSeriesCount()) {
            throw new IllegalArgumentException("XYSeriesCollection.getSeries(...): index outside valid range.");
        }
        return this.data.get(series);
    }
    
    public String getSeriesName(final int series) {
        return this.getSeries(series).getName();
    }
    
    public int getItemCount(final int series) {
        return this.getSeries(series).getItemCount();
    }
    
    public Number getXValue(final int series, final int item) {
        final XYSeries s = this.data.get(series);
        final XYDataItem dataItem = s.getDataItem(item);
        return dataItem.getX();
    }
    
    public Number getStartXValue(final int series, final int item) {
        return this.intervalDelegate.getStartXValue(series, item);
    }
    
    public Number getEndXValue(final int series, final int item) {
        return this.intervalDelegate.getEndXValue(series, item);
    }
    
    public Number getYValue(final int series, final int index) {
        final XYSeries ts = this.data.get(series);
        final XYDataItem dataItem = ts.getDataItem(index);
        return dataItem.getY();
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
        this.xPoints.clear();
        this.intervalDelegate.seriesRemoved();
        this.fireDatasetChanged();
    }
    
    public void removeSeries(final XYSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Null 'series' argument.");
        }
        if (this.data.contains(series)) {
            series.removeChangeListener(this);
            this.data.remove(series);
            if (this.data.size() == 0) {
                this.xPoints.clear();
            }
            this.intervalDelegate.seriesRemoved();
            this.fireDatasetChanged();
        }
    }
    
    public void removeSeries(final int series) {
        if (series < 0 || series > this.getSeriesCount()) {
            throw new IllegalArgumentException("XYSeriesCollection.removeSeries(...): index outside valid range.");
        }
        final XYSeries s = this.data.get(series);
        s.removeChangeListener(this);
        this.data.remove(series);
        if (this.data.size() == 0) {
            this.xPoints.clear();
        }
        else if (this.autoPrune) {
            this.prune();
        }
        this.intervalDelegate.seriesRemoved();
        this.fireDatasetChanged();
    }
    
    public void removeAllValuesForX(final Number x) {
        if (x == null) {
            throw new IllegalArgumentException("Null 'x' argument.");
        }
        final boolean savedState = this.propagateEvents;
        this.propagateEvents = false;
        for (int s = 0; s < this.data.size(); ++s) {
            final XYSeries series = this.data.get(s);
            series.remove(x);
        }
        this.propagateEvents = savedState;
        this.xPoints.remove(x);
        this.intervalDelegate.seriesRemoved();
        this.fireDatasetChanged();
    }
    
    protected boolean canPrune(final Number x) {
        for (int s = 0; s < this.data.size(); ++s) {
            final XYSeries series = this.data.get(s);
            if (series.getYValue(series.indexOf(x)) != null) {
                return false;
            }
        }
        return true;
    }
    
    public void prune() {
        final HashSet hs = (HashSet)this.xPoints.clone();
        for (final Number x : hs) {
            if (this.canPrune(x)) {
                this.removeAllValuesForX(x);
            }
        }
    }
    
    public void seriesChanged(final SeriesChangeEvent event) {
        if (this.propagateEvents) {
            this.updateXPoints();
            this.fireDatasetChanged();
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof DefaultTableXYDataset) {
            final DefaultTableXYDataset c = (DefaultTableXYDataset)obj;
            return ObjectUtils.equal(this.data, c.data);
        }
        return false;
    }
    
    public int hashCode() {
        int result = (this.data != null) ? this.data.hashCode() : 0;
        result = 29 * result + ((this.xPoints != null) ? this.xPoints.hashCode() : 0);
        result = 29 * result + (this.propagateEvents ? 1 : 0);
        result = 29 * result + (this.autoPrune ? 1 : 0);
        return result;
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
