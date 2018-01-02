// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time.ohlc;

import java.util.Collection;
import org.jfree.util.ObjectUtilities;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.general.SeriesChangeListener;
import java.util.ArrayList;
import org.jfree.data.time.TimePeriodAnchor;
import java.util.List;
import java.io.Serializable;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.AbstractXYDataset;

public class OHLCSeriesCollection extends AbstractXYDataset implements OHLCDataset, Serializable
{
    private List data;
    private TimePeriodAnchor xPosition;
    
    public OHLCSeriesCollection() {
        this.xPosition = TimePeriodAnchor.MIDDLE;
        this.data = new ArrayList();
    }
    
    public void addSeries(final OHLCSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("Null 'series' argument.");
        }
        this.data.add(series);
        series.addChangeListener(this);
        this.fireDatasetChanged();
    }
    
    public int getSeriesCount() {
        return this.data.size();
    }
    
    public OHLCSeries getSeries(final int series) {
        if (series < 0 || series >= this.getSeriesCount()) {
            throw new IllegalArgumentException("Series index out of bounds");
        }
        return this.data.get(series);
    }
    
    public Comparable getSeriesKey(final int series) {
        return this.getSeries(series).getKey();
    }
    
    public int getItemCount(final int series) {
        return this.getSeries(series).getItemCount();
    }
    
    protected synchronized long getX(final RegularTimePeriod period) {
        long result = 0L;
        if (this.xPosition == TimePeriodAnchor.START) {
            result = period.getFirstMillisecond();
        }
        else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
            result = period.getMiddleMillisecond();
        }
        else if (this.xPosition == TimePeriodAnchor.END) {
            result = period.getLastMillisecond();
        }
        return result;
    }
    
    public double getXValue(final int series, final int item) {
        final OHLCSeries s = this.data.get(series);
        final OHLCItem di = (OHLCItem)s.getDataItem(item);
        final RegularTimePeriod period = di.getPeriod();
        return this.getX(period);
    }
    
    public Number getX(final int series, final int item) {
        return new Double(this.getXValue(series, item));
    }
    
    public Number getY(final int series, final int item) {
        final OHLCSeries s = this.data.get(series);
        final OHLCItem di = (OHLCItem)s.getDataItem(item);
        return new Double(di.getYValue());
    }
    
    public double getOpenValue(final int series, final int item) {
        final OHLCSeries s = this.data.get(series);
        final OHLCItem di = (OHLCItem)s.getDataItem(item);
        return di.getOpenValue();
    }
    
    public Number getOpen(final int series, final int item) {
        return new Double(this.getOpenValue(series, item));
    }
    
    public double getCloseValue(final int series, final int item) {
        final OHLCSeries s = this.data.get(series);
        final OHLCItem di = (OHLCItem)s.getDataItem(item);
        return di.getCloseValue();
    }
    
    public Number getClose(final int series, final int item) {
        return new Double(this.getCloseValue(series, item));
    }
    
    public double getHighValue(final int series, final int item) {
        final OHLCSeries s = this.data.get(series);
        final OHLCItem di = (OHLCItem)s.getDataItem(item);
        return di.getHighValue();
    }
    
    public Number getHigh(final int series, final int item) {
        return new Double(this.getHighValue(series, item));
    }
    
    public double getLowValue(final int series, final int item) {
        final OHLCSeries s = this.data.get(series);
        final OHLCItem di = (OHLCItem)s.getDataItem(item);
        return di.getLowValue();
    }
    
    public Number getLow(final int series, final int item) {
        return new Double(this.getLowValue(series, item));
    }
    
    public Number getVolume(final int series, final int item) {
        return null;
    }
    
    public double getVolumeValue(final int series, final int item) {
        return Double.NaN;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OHLCSeriesCollection)) {
            return false;
        }
        final OHLCSeriesCollection that = (OHLCSeriesCollection)obj;
        return ObjectUtilities.equal(this.data, that.data);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final OHLCSeriesCollection clone = (OHLCSeriesCollection)super.clone();
        clone.data = (List)ObjectUtilities.deepClone(this.data);
        return clone;
    }
}
