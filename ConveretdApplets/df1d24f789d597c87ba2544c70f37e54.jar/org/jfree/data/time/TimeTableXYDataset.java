// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import java.util.List;
import org.jfree.data.Range;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Calendar;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.TableXYDataset;
import org.jfree.data.DomainInfo;
import org.jfree.data.IntervalXYDataset;
import org.jfree.data.AbstractIntervalXYDataset;

public class TimeTableXYDataset extends AbstractIntervalXYDataset implements IntervalXYDataset, DomainInfo, TableXYDataset
{
    private DefaultKeyedValues2D values;
    private boolean domainIsPointsInTime;
    private Calendar workingCalendar;
    private TimePeriodAnchor xPosition;
    
    public TimeTableXYDataset() {
        this(TimeZone.getDefault(), Locale.getDefault());
    }
    
    public TimeTableXYDataset(final TimeZone zone) {
        this(zone, Locale.getDefault());
    }
    
    public TimeTableXYDataset(final TimeZone zone, final Locale locale) {
        this.values = new DefaultKeyedValues2D(true);
        this.workingCalendar = Calendar.getInstance(zone, locale);
        this.xPosition = TimePeriodAnchor.START;
    }
    
    public void add(final RegularTimePeriod period, final double y, final String seriesName) {
        this.add(period, new Double(y), seriesName, true);
    }
    
    public void add(final RegularTimePeriod period, final Number y, final String seriesName, final boolean notify) {
        this.values.addValue(y, period, seriesName);
        if (notify) {
            this.fireDatasetChanged();
        }
    }
    
    public void remove(final RegularTimePeriod period, final String seriesName) {
        this.remove(period, seriesName, true);
    }
    
    public void remove(final RegularTimePeriod period, final String seriesName, final boolean notify) {
        this.values.removeValue(period, seriesName);
        if (notify) {
            this.fireDatasetChanged();
        }
    }
    
    public int getItemCount() {
        return this.values.getRowCount();
    }
    
    public int getItemCount(final int series) {
        return this.getItemCount();
    }
    
    public int getSeriesCount() {
        return this.values.getColumnCount();
    }
    
    public String getSeriesName(final int series) {
        return this.values.getColumnKey(series).toString();
    }
    
    public Number getXValue(final int series, final int item) {
        final RegularTimePeriod period = (RegularTimePeriod)this.values.getRowKey(item);
        return new Long(this.getX(period));
    }
    
    public Number getStartXValue(final int series, final int item) {
        final RegularTimePeriod period = (RegularTimePeriod)this.values.getRowKey(item);
        return new Long(period.getFirstMillisecond(this.workingCalendar));
    }
    
    public Number getEndXValue(final int series, final int item) {
        final RegularTimePeriod period = (RegularTimePeriod)this.values.getRowKey(item);
        return new Long(period.getLastMillisecond(this.workingCalendar));
    }
    
    public Number getYValue(final int series, final int item) {
        return this.values.getValue(item, series);
    }
    
    public Number getStartYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    public Number getEndYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    private long getX(final RegularTimePeriod period) {
        long result = 0L;
        if (this.xPosition == TimePeriodAnchor.START) {
            result = period.getFirstMillisecond(this.workingCalendar);
        }
        else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
            result = period.getMiddleMillisecond(this.workingCalendar);
        }
        else if (this.xPosition == TimePeriodAnchor.END) {
            result = period.getLastMillisecond(this.workingCalendar);
        }
        return result;
    }
    
    public Number getMinimumDomainValue() {
        return new Double(this.getDomainRange().getLowerBound());
    }
    
    public Number getMaximumDomainValue() {
        return new Double(this.getDomainRange().getUpperBound());
    }
    
    public Range getDomainRange() {
        final List keys = this.values.getRowKeys();
        if (keys.isEmpty()) {
            return null;
        }
        final RegularTimePeriod first = keys.get(0);
        final RegularTimePeriod last = keys.get(keys.size() - 1);
        if (this.domainIsPointsInTime) {
            return new Range(this.getX(first), this.getX(last));
        }
        return new Range(first.getFirstMillisecond(this.workingCalendar), last.getLastMillisecond(this.workingCalendar));
    }
    
    public boolean getDomainIsPointsInTime() {
        return this.domainIsPointsInTime;
    }
    
    public void setDomainIsPointsInTime(final boolean flag) {
        this.domainIsPointsInTime = flag;
    }
}
