// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import org.jfree.util.ObjectUtils;
import org.jfree.data.Range;
import java.util.Iterator;
import java.util.Collections;
import org.jfree.data.Dataset;
import org.jfree.data.DatasetChangeEvent;
import org.jfree.data.SeriesChangeListener;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;
import org.jfree.data.DomainInfo;
import org.jfree.data.IntervalXYDataset;
import org.jfree.data.AbstractIntervalXYDataset;

public class TimeSeriesCollection extends AbstractIntervalXYDataset implements IntervalXYDataset, DomainInfo, Serializable
{
    public static final int START = 0;
    public static final int MIDDLE = 1;
    public static final int END = 2;
    private List data;
    private Calendar workingCalendar;
    private TimePeriodAnchor xPosition;
    private boolean domainIsPointsInTime;
    
    public TimeSeriesCollection() {
        this(null, TimeZone.getDefault());
    }
    
    public TimeSeriesCollection(final TimeZone zone) {
        this(null, zone);
    }
    
    public TimeSeriesCollection(final TimeSeries series) {
        this(series, TimeZone.getDefault());
    }
    
    public TimeSeriesCollection(final TimeSeries series, final TimeZone zone) {
        this.data = new ArrayList();
        this.workingCalendar = Calendar.getInstance(zone);
        this.xPosition = TimePeriodAnchor.START;
        this.domainIsPointsInTime = true;
        if (series != null) {
            this.data.add(series);
            series.addChangeListener(this);
        }
    }
    
    public int getPosition() {
        int result = 1;
        final TimePeriodAnchor anchor = this.getXPosition();
        if (anchor == TimePeriodAnchor.START) {
            result = 0;
        }
        else if (anchor == TimePeriodAnchor.MIDDLE) {
            result = 1;
        }
        else if (anchor == TimePeriodAnchor.END) {
            result = 2;
        }
        return result;
    }
    
    public void setPosition(final int position) {
        if (position == 0) {
            this.setXPosition(TimePeriodAnchor.START);
        }
        else if (position == 1) {
            this.setXPosition(TimePeriodAnchor.MIDDLE);
        }
        else if (position == 2) {
            this.setXPosition(TimePeriodAnchor.END);
        }
    }
    
    public TimePeriodAnchor getXPosition() {
        return this.xPosition;
    }
    
    public void setXPosition(final TimePeriodAnchor anchor) {
        this.xPosition = anchor;
        this.notifyListeners(new DatasetChangeEvent(this, this));
    }
    
    public boolean getDomainIsPointsInTime() {
        return this.domainIsPointsInTime;
    }
    
    public void setDomainIsPointsInTime(final boolean flag) {
        this.domainIsPointsInTime = flag;
    }
    
    public List getSeries() {
        return Collections.unmodifiableList((List<?>)this.data);
    }
    
    public int getSeriesCount() {
        return this.data.size();
    }
    
    public TimeSeries getSeries(final int series) {
        if (series < 0 || series > this.getSeriesCount()) {
            throw new IllegalArgumentException("TimeSeriesDataset.getSeries(...): index outside valid range.");
        }
        final TimeSeries ts = this.data.get(series);
        return ts;
    }
    
    public TimeSeries getSeries(final String name) {
        TimeSeries result = null;
        for (final TimeSeries series : this.data) {
            final String n = series.getName();
            if (n != null && n.equals(name)) {
                result = series;
            }
        }
        return result;
    }
    
    public String getSeriesName(final int series) {
        return this.getSeries(series).getName();
    }
    
    public void addSeries(final TimeSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("TimeSeriesDataset.addSeries(...): cannot add null series.");
        }
        this.data.add(series);
        series.addChangeListener(this);
        this.fireDatasetChanged();
    }
    
    public void removeSeries(final TimeSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("TimeSeriesDataset.addSeries(...): cannot remove null series.");
        }
        this.data.remove(series);
        series.removeChangeListener(this);
        this.fireDatasetChanged();
    }
    
    public void removeSeries(final int index) {
        final TimeSeries series = this.getSeries(index);
        if (series != null) {
            this.removeSeries(series);
        }
    }
    
    public void removeAllSeries() {
        for (int i = 0; i < this.data.size(); ++i) {
            final TimeSeries series = this.data.get(i);
            series.removeChangeListener(this);
        }
        this.data.clear();
        this.fireDatasetChanged();
    }
    
    public int getItemCount(final int series) {
        return this.getSeries(series).getItemCount();
    }
    
    public Number getXValue(final int series, final int item) {
        final TimeSeries ts = this.data.get(series);
        final TimeSeriesDataItem dp = ts.getDataItem(item);
        final RegularTimePeriod period = dp.getPeriod();
        return new Long(this.getX(period));
    }
    
    public int[] getSurroundingItems(final int series, final long milliseconds) {
        final int[] result = { -1, -1 };
        final TimeSeries timeSeries = this.getSeries(series);
        for (int i = 0; i < timeSeries.getItemCount(); ++i) {
            final Number x = this.getXValue(series, i);
            final long m = x.longValue();
            if (m <= milliseconds) {
                result[0] = i;
            }
            if (m >= milliseconds) {
                result[1] = i;
                break;
            }
        }
        return result;
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
    
    public Number getStartXValue(final int series, final int item) {
        final TimeSeries ts = this.data.get(series);
        final TimeSeriesDataItem dp = ts.getDataItem(item);
        return new Long(dp.getPeriod().getFirstMillisecond(this.workingCalendar));
    }
    
    public Number getEndXValue(final int series, final int item) {
        final TimeSeries ts = this.data.get(series);
        final TimeSeriesDataItem dp = ts.getDataItem(item);
        return new Long(dp.getPeriod().getLastMillisecond(this.workingCalendar));
    }
    
    public Number getYValue(final int series, final int item) {
        final TimeSeries ts = this.data.get(series);
        final TimeSeriesDataItem dp = ts.getDataItem(item);
        return dp.getValue();
    }
    
    public Number getStartYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    public Number getEndYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    public Number getMinimumDomainValue() {
        final Range r = this.getDomainRange();
        return new Double(r.getLowerBound());
    }
    
    public Number getMaximumDomainValue() {
        final Range r = this.getDomainRange();
        return new Double(r.getUpperBound());
    }
    
    public Range getDomainRange() {
        Range result = null;
        for (final TimeSeries series : this.data) {
            final int count = series.getItemCount();
            if (count > 0) {
                final RegularTimePeriod start = series.getTimePeriod(0);
                final RegularTimePeriod end = series.getTimePeriod(count - 1);
                Range temp;
                if (this.domainIsPointsInTime) {
                    temp = new Range(this.getX(start), this.getX(end));
                }
                else {
                    temp = new Range(start.getFirstMillisecond(this.workingCalendar), end.getLastMillisecond(this.workingCalendar));
                }
                result = Range.combine(result, temp);
            }
        }
        return result;
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeSeriesCollection)) {
            return false;
        }
        final TimeSeriesCollection tsc = (TimeSeriesCollection)obj;
        return ObjectUtils.equal(this.data, tsc.data) && this.xPosition == tsc.xPosition && this.domainIsPointsInTime == tsc.domainIsPointsInTime;
    }
    
    public int hashCode() {
        int result = this.data.hashCode();
        result = 29 * result + ((this.workingCalendar != null) ? this.workingCalendar.hashCode() : 0);
        result = 29 * result + ((this.xPosition != null) ? this.xPosition.hashCode() : 0);
        result = 29 * result + (this.domainIsPointsInTime ? 1 : 0);
        return result;
    }
}
