// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public class XYBarDataset extends AbstractIntervalXYDataset implements IntervalXYDataset, DatasetChangeListener
{
    private XYDataset underlying;
    private double barWidth;
    
    public XYBarDataset(final XYDataset underlying, final double barWidth) {
        (this.underlying = underlying).addChangeListener(this);
        this.barWidth = barWidth;
    }
    
    public int getSeriesCount() {
        return this.underlying.getSeriesCount();
    }
    
    public String getSeriesName(final int series) {
        return this.underlying.getSeriesName(series);
    }
    
    public int getItemCount(final int series) {
        return this.underlying.getItemCount(series);
    }
    
    public Number getXValue(final int series, final int item) {
        return this.underlying.getXValue(series, item);
    }
    
    public Number getYValue(final int series, final int item) {
        return this.underlying.getYValue(series, item);
    }
    
    public Number getStartXValue(final int series, final int item) {
        Number result = null;
        final Number xnum = this.underlying.getXValue(series, item);
        if (xnum != null) {
            result = new Double(xnum.doubleValue() - this.barWidth / 2.0);
        }
        return result;
    }
    
    public Number getEndXValue(final int series, final int item) {
        Number result = null;
        final Number xnum = this.underlying.getXValue(series, item);
        if (xnum != null) {
            result = new Double(xnum.doubleValue() + this.barWidth / 2.0);
        }
        return result;
    }
    
    public Number getStartYValue(final int series, final int item) {
        return this.underlying.getYValue(series, item);
    }
    
    public Number getEndYValue(final int series, final int item) {
        return this.underlying.getYValue(series, item);
    }
    
    public void datasetChanged(final DatasetChangeEvent event) {
        this.notifyListeners(event);
    }
}
