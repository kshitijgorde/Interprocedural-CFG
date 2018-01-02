// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public class SubSeriesDataset extends AbstractIntervalXYDataset implements HighLowDataset, SignalsDataset, IntervalXYDataset, CombinationDataset
{
    private SeriesDataset parent;
    private int[] map;
    
    public SubSeriesDataset(final SeriesDataset parent, final int[] map) {
        this.parent = null;
        this.parent = parent;
        this.map = map;
    }
    
    public SubSeriesDataset(final SeriesDataset parent, final int series) {
        this(parent, new int[] { series });
    }
    
    public Number getHighValue(final int series, final int item) {
        return ((HighLowDataset)this.parent).getHighValue(this.map[series], item);
    }
    
    public double getHigh(final int series, final int item) {
        double result = Double.NaN;
        final Number high = this.getHighValue(series, item);
        if (high != null) {
            result = high.doubleValue();
        }
        return result;
    }
    
    public Number getLowValue(final int series, final int item) {
        return ((HighLowDataset)this.parent).getLowValue(this.map[series], item);
    }
    
    public double getLow(final int series, final int item) {
        double result = Double.NaN;
        final Number low = this.getLowValue(series, item);
        if (low != null) {
            result = low.doubleValue();
        }
        return result;
    }
    
    public Number getOpenValue(final int series, final int item) {
        return ((HighLowDataset)this.parent).getOpenValue(this.map[series], item);
    }
    
    public double getOpen(final int series, final int item) {
        double result = Double.NaN;
        final Number open = this.getOpenValue(series, item);
        if (open != null) {
            result = open.doubleValue();
        }
        return result;
    }
    
    public Number getCloseValue(final int series, final int item) {
        return ((HighLowDataset)this.parent).getCloseValue(this.map[series], item);
    }
    
    public double getClose(final int series, final int item) {
        double result = Double.NaN;
        final Number close = this.getCloseValue(series, item);
        if (close != null) {
            result = close.doubleValue();
        }
        return result;
    }
    
    public Number getVolumeValue(final int series, final int item) {
        return ((HighLowDataset)this.parent).getVolumeValue(this.map[series], item);
    }
    
    public double getVolume(final int series, final int item) {
        double result = Double.NaN;
        final Number volume = this.getVolumeValue(series, item);
        if (volume != null) {
            result = volume.doubleValue();
        }
        return result;
    }
    
    public Number getXValue(final int series, final int item) {
        return ((XYDataset)this.parent).getXValue(this.map[series], item);
    }
    
    public Number getYValue(final int series, final int item) {
        return ((XYDataset)this.parent).getYValue(this.map[series], item);
    }
    
    public int getItemCount(final int series) {
        return ((XYDataset)this.parent).getItemCount(this.map[series]);
    }
    
    public int getSeriesCount() {
        return this.map.length;
    }
    
    public String getSeriesName(final int series) {
        return this.parent.getSeriesName(this.map[series]);
    }
    
    public Number getStartXValue(final int series, final int item) {
        if (this.parent instanceof IntervalXYDataset) {
            return ((IntervalXYDataset)this.parent).getStartXValue(this.map[series], item);
        }
        return this.getXValue(series, item);
    }
    
    public Number getEndXValue(final int series, final int item) {
        if (this.parent instanceof IntervalXYDataset) {
            return ((IntervalXYDataset)this.parent).getEndXValue(this.map[series], item);
        }
        return this.getXValue(series, item);
    }
    
    public Number getStartYValue(final int series, final int item) {
        if (this.parent instanceof IntervalXYDataset) {
            return ((IntervalXYDataset)this.parent).getStartYValue(this.map[series], item);
        }
        return this.getYValue(series, item);
    }
    
    public Number getEndYValue(final int series, final int item) {
        if (this.parent instanceof IntervalXYDataset) {
            return ((IntervalXYDataset)this.parent).getEndYValue(this.map[series], item);
        }
        return this.getYValue(series, item);
    }
    
    public int getType(final int series, final int item) {
        if (this.parent instanceof SignalsDataset) {
            return ((SignalsDataset)this.parent).getType(this.map[series], item);
        }
        return this.getYValue(series, item).intValue();
    }
    
    public double getLevel(final int series, final int item) {
        if (this.parent instanceof SignalsDataset) {
            return ((SignalsDataset)this.parent).getLevel(this.map[series], item);
        }
        return this.getYValue(series, item).doubleValue();
    }
    
    public SeriesDataset getParent() {
        return this.parent;
    }
    
    public int[] getMap() {
        return this.map.clone();
    }
}
