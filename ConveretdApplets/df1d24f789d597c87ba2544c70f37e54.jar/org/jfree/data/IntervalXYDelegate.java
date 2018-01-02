// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.PublicCloneable;
import java.io.Serializable;

class IntervalXYDelegate implements DomainInfo, Serializable, Cloneable, PublicCloneable
{
    private static final long serialVersionUID = -685166711639592857L;
    private XYDataset dataset;
    private boolean autoWidth;
    private double intervalPositionFactor;
    private double intervalWidth;
    private double autoIntervalWidth;
    private double lowerBound;
    private double upperBound;
    
    public IntervalXYDelegate(final XYDataset dataset) {
        this(dataset, true);
    }
    
    public IntervalXYDelegate(final XYDataset dataset, final boolean autoWidth) {
        this.autoWidth = autoWidth;
        this.dataset = dataset;
        this.intervalPositionFactor = 0.5;
        this.autoWidth = autoWidth;
        this.autoIntervalWidth = Double.POSITIVE_INFINITY;
        this.intervalWidth = 1.0;
    }
    
    public boolean isAutoWidth() {
        return this.autoWidth;
    }
    
    public void setAutoWidth(final boolean b) {
        this.autoWidth = b;
    }
    
    public double getIntervalPositionFactor() {
        return this.intervalPositionFactor;
    }
    
    public void setIntervalPositionFactor(final double d) {
        if (d < 0.0 || 1.0 < d) {
            return;
        }
        this.intervalPositionFactor = d;
    }
    
    public void setIntervalWidth(final double d) {
        this.intervalWidth = d;
    }
    
    public double getIntervalWidth() {
        if (this.isAutoWidth() && !Double.isInfinite(this.autoIntervalWidth)) {
            return this.autoIntervalWidth;
        }
        return this.intervalWidth;
    }
    
    public Number getStartXValue(final int series, final int item) {
        Number startX = null;
        final Number x = this.dataset.getXValue(series, item);
        if (x != null) {
            startX = new Double(x.doubleValue() - this.getIntervalPositionFactor() * this.getIntervalWidth());
        }
        return startX;
    }
    
    public Number getEndXValue(final int series, final int item) {
        Number endX = null;
        final Number x = this.dataset.getXValue(series, item);
        if (x != null) {
            endX = new Double(x.doubleValue() + (1.0 - this.getIntervalPositionFactor()) * this.getIntervalWidth());
        }
        return endX;
    }
    
    public Range getDomainRange() {
        Range range = DatasetUtilities.iterateDomainExtent(this.dataset);
        if (this.dataset.getSeriesCount() == 1 && this.dataset.getItemCount(0) == 1) {
            range = new Range(range.getLowerBound() - this.getIntervalWidth(), range.getUpperBound() + this.getIntervalWidth());
        }
        return range;
    }
    
    public Number getMaximumDomainValue() {
        return new Double(this.getDomainRange().getUpperBound());
    }
    
    public Number getMinimumDomainValue() {
        return new Double(this.getDomainRange().getLowerBound());
    }
    
    public void itemAdded(final int series, final int item) {
        final double x = this.dataset.getXValue(series, item).doubleValue();
        if (item > 0) {
            final double before = this.dataset.getXValue(series, item - 1).doubleValue();
            final double delta = x - before;
            if (delta < this.autoIntervalWidth) {
                this.autoIntervalWidth = delta;
                this.lowerBound = before;
                this.upperBound = x;
            }
        }
        if (item + 1 < this.dataset.getItemCount(series)) {
            final double after = this.dataset.getXValue(series, item + 1).doubleValue();
            final double delta = after - x;
            if (delta < this.autoIntervalWidth) {
                this.autoIntervalWidth = delta;
                this.lowerBound = x;
                this.upperBound = after;
            }
        }
    }
    
    public void itemRemoved(final double x) {
        if (x == this.lowerBound || x == this.upperBound) {
            this.recalculateIntervalWidth();
        }
    }
    
    private void recalculateIntervalWidth() {
        this.autoIntervalWidth = Double.POSITIVE_INFINITY;
        for (int series = 0, seriesCount = this.dataset.getSeriesCount(); series < seriesCount; ++series) {
            this.calculateSeries(series);
        }
    }
    
    private void calculateSeries(final int series) {
        final int totalCount = this.dataset.getItemCount(series);
        for (int item = 1, itemCount = totalCount; item < itemCount; ++item) {
            final double lower = this.dataset.getXValue(series, item - 1).doubleValue();
            final double upper = this.dataset.getXValue(series, item).doubleValue();
            final double delta = upper - lower;
            if (delta < this.autoIntervalWidth) {
                this.autoIntervalWidth = delta;
                this.lowerBound = lower;
                this.upperBound = upper;
            }
        }
    }
    
    public void seriesAdded(final int series) {
        this.calculateSeries(series);
    }
    
    public void seriesRemoved() {
        this.recalculateIntervalWidth();
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
