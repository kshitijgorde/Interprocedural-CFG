// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.Arrays;
import java.util.Date;

public class OHLCDataset extends AbstractXYDataset implements HighLowDataset
{
    private String name;
    private OHLCDataItem[] data;
    
    public OHLCDataset(final String name, final OHLCDataItem[] data) {
        this.name = name;
        this.data = data;
    }
    
    public String getSeriesName(final int series) {
        return this.name;
    }
    
    public Number getXValue(final int series, final int item) {
        return new Long(this.data[item].getDate().getTime());
    }
    
    public Date getXDate(final int series, final int item) {
        return this.data[item].getDate();
    }
    
    public Number getYValue(final int series, final int item) {
        return this.getCloseValue(series, item);
    }
    
    public Number getHighValue(final int series, final int item) {
        return this.data[item].getHigh();
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
        return this.data[item].getLow();
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
        return this.data[item].getOpen();
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
        return this.data[item].getClose();
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
        return this.data[item].getVolume();
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
        return this.data.length;
    }
    
    public void sortDataByDate() {
        Arrays.sort(this.data);
    }
}
