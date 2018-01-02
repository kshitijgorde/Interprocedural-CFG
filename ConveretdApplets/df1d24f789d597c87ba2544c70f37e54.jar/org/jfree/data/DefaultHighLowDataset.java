// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.Date;

public class DefaultHighLowDataset extends AbstractXYDataset implements HighLowDataset
{
    private String seriesName;
    private Date[] date;
    private Number[] high;
    private Number[] low;
    private Number[] open;
    private Number[] close;
    private Number[] volume;
    
    public DefaultHighLowDataset(final String seriesName, final Date[] date, final double[] high, final double[] low, final double[] open, final double[] close, final double[] volume) {
        this.seriesName = seriesName;
        this.date = date;
        this.high = createNumberArray(high);
        this.low = createNumberArray(low);
        this.open = createNumberArray(open);
        this.close = createNumberArray(close);
        this.volume = createNumberArray(volume);
    }
    
    public String getSeriesName(final int i) {
        return this.seriesName;
    }
    
    public Number getXValue(final int series, final int item) {
        return new Long(this.date[item].getTime());
    }
    
    public Date getXDate(final int series, final int item) {
        return this.date[item];
    }
    
    public Number getYValue(final int series, final int item) {
        return this.getCloseValue(series, item);
    }
    
    public Number getHighValue(final int series, final int item) {
        return this.high[item];
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
        return this.low[item];
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
        return this.open[item];
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
        return this.close[item];
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
        return this.volume[item];
    }
    
    public double getVolume(final int series, final int item) {
        double result = Double.NaN;
        final Number volume = this.getVolumeValue(series, item);
        if (volume != null) {
            result = volume.doubleValue();
        }
        return result;
    }
    
    public int getSeriesCount() {
        return 1;
    }
    
    public int getItemCount(final int series) {
        return this.date.length;
    }
    
    public static Number[] createNumberArray(final double[] data) {
        final Number[] result = new Number[data.length];
        for (int i = 0; i < data.length; ++i) {
            result[i] = new Double(data[i]);
        }
        return result;
    }
}
