// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.xy;

import java.util.Arrays;
import java.util.Date;

public class DefaultHighLowDataset extends AbstractXYDataset implements OHLCDataset
{
    private Comparable seriesKey;
    private Date[] date;
    private Number[] high;
    private Number[] low;
    private Number[] open;
    private Number[] close;
    private Number[] volume;
    
    public DefaultHighLowDataset(final Comparable seriesKey, final Date[] date, final double[] high, final double[] low, final double[] open, final double[] close, final double[] volume) {
        if (seriesKey == null) {
            throw new IllegalArgumentException("Null 'series' argument.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Null 'date' argument.");
        }
        this.seriesKey = seriesKey;
        this.date = date;
        this.high = createNumberArray(high);
        this.low = createNumberArray(low);
        this.open = createNumberArray(open);
        this.close = createNumberArray(close);
        this.volume = createNumberArray(volume);
    }
    
    public Comparable getSeriesKey(final int series) {
        return this.seriesKey;
    }
    
    public Number getX(final int series, final int item) {
        return new Long(this.date[item].getTime());
    }
    
    public Date getXDate(final int series, final int item) {
        return this.date[item];
    }
    
    public Number getY(final int series, final int item) {
        return this.getClose(series, item);
    }
    
    public Number getHigh(final int series, final int item) {
        return this.high[item];
    }
    
    public double getHighValue(final int series, final int item) {
        double result = Double.NaN;
        final Number high = this.getHigh(series, item);
        if (high != null) {
            result = high.doubleValue();
        }
        return result;
    }
    
    public Number getLow(final int series, final int item) {
        return this.low[item];
    }
    
    public double getLowValue(final int series, final int item) {
        double result = Double.NaN;
        final Number low = this.getLow(series, item);
        if (low != null) {
            result = low.doubleValue();
        }
        return result;
    }
    
    public Number getOpen(final int series, final int item) {
        return this.open[item];
    }
    
    public double getOpenValue(final int series, final int item) {
        double result = Double.NaN;
        final Number open = this.getOpen(series, item);
        if (open != null) {
            result = open.doubleValue();
        }
        return result;
    }
    
    public Number getClose(final int series, final int item) {
        return this.close[item];
    }
    
    public double getCloseValue(final int series, final int item) {
        double result = Double.NaN;
        final Number close = this.getClose(series, item);
        if (close != null) {
            result = close.doubleValue();
        }
        return result;
    }
    
    public Number getVolume(final int series, final int item) {
        return this.volume[item];
    }
    
    public double getVolumeValue(final int series, final int item) {
        double result = Double.NaN;
        final Number volume = this.getVolume(series, item);
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
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultHighLowDataset)) {
            return false;
        }
        final DefaultHighLowDataset that = (DefaultHighLowDataset)obj;
        return this.seriesKey.equals(that.seriesKey) && Arrays.equals(this.date, that.date) && Arrays.equals(this.open, that.open) && Arrays.equals(this.high, that.high) && Arrays.equals(this.low, that.low) && Arrays.equals(this.close, that.close) && Arrays.equals(this.volume, that.volume);
    }
    
    public static Number[] createNumberArray(final double[] data) {
        final Number[] result = new Number[data.length];
        for (int i = 0; i < data.length; ++i) {
            result[i] = new Double(data[i]);
        }
        return result;
    }
}
