// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import java.util.Iterator;
import org.jfree.data.Range;
import org.jfree.data.SeriesChangeListener;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.List;
import java.io.Serializable;
import org.jfree.data.DomainInfo;
import org.jfree.data.IntervalXYDataset;
import org.jfree.data.AbstractIntervalXYDataset;

public class TimePeriodValuesCollection extends AbstractIntervalXYDataset implements IntervalXYDataset, DomainInfo, Serializable
{
    public static final int START = 0;
    public static final int MIDDLE = 1;
    public static final int END = 2;
    private List data;
    private TimePeriodAnchor xPosition;
    private boolean domainIsPointsInTime;
    
    public TimePeriodValuesCollection() {
        this((TimePeriodValues)null);
    }
    
    public TimePeriodValuesCollection(final TimeZone zone) {
        this(null, zone);
    }
    
    public TimePeriodValuesCollection(final TimePeriodValues series) {
        this.data = new ArrayList();
        this.xPosition = TimePeriodAnchor.MIDDLE;
        this.domainIsPointsInTime = true;
        if (series != null) {
            this.data.add(series);
            series.addChangeListener(this);
        }
    }
    
    public TimePeriodValuesCollection(final TimePeriodValues series, final TimeZone zone) {
        this(series);
    }
    
    public int getPosition() {
        final TimePeriodAnchor anchor = this.getXPosition();
        if (anchor == TimePeriodAnchor.START) {
            return 0;
        }
        if (anchor == TimePeriodAnchor.MIDDLE) {
            return 1;
        }
        if (anchor == TimePeriodAnchor.END) {
            return 2;
        }
        return 1;
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
    
    public void setXPosition(final TimePeriodAnchor position) {
        this.xPosition = position;
    }
    
    public boolean getDomainIsPointsInTime() {
        return this.domainIsPointsInTime;
    }
    
    public void setDomainIsPointsInTime(final boolean flag) {
        this.domainIsPointsInTime = flag;
    }
    
    public int getSeriesCount() {
        return this.data.size();
    }
    
    public TimePeriodValues getSeries(final int series) {
        if (series < 0 || series > this.getSeriesCount()) {
            throw new IllegalArgumentException("TimePeriodValuesCollection.getSeries(...): index outside valid range.");
        }
        final TimePeriodValues ts = this.data.get(series);
        return ts;
    }
    
    public String getSeriesName(final int series) {
        return this.getSeries(series).getName();
    }
    
    public void addSeries(final TimePeriodValues series) {
        if (series == null) {
            throw new IllegalArgumentException("TimePeriodValuesCollection.addSeries(...): cannot add null series.");
        }
        this.data.add(series);
        series.addChangeListener(this);
        this.fireDatasetChanged();
    }
    
    public void removeSeries(final TimePeriodValues series) {
        if (series == null) {
            throw new IllegalArgumentException("TimePeriodValuesCollection.addSeries(...): cannot add null series.");
        }
        this.data.remove(series);
        series.removeChangeListener(this);
        this.fireDatasetChanged();
    }
    
    public void removeSeries(final int index) {
        final TimePeriodValues series = this.getSeries(index);
        if (series != null) {
            this.removeSeries(series);
        }
    }
    
    public int getItemCount(final int series) {
        return this.getSeries(series).getItemCount();
    }
    
    public Number getXValue(final int series, final int item) {
        final TimePeriodValues ts = this.data.get(series);
        final TimePeriodValue dp = ts.getDataItem(item);
        final TimePeriod period = dp.getPeriod();
        return new Long(this.getX(period));
    }
    
    private long getX(final TimePeriod period) {
        if (this.xPosition == TimePeriodAnchor.START) {
            return period.getStart().getTime();
        }
        if (this.xPosition == TimePeriodAnchor.MIDDLE) {
            return period.getStart().getTime() / 2L + period.getEnd().getTime() / 2L;
        }
        if (this.xPosition == TimePeriodAnchor.END) {
            return period.getEnd().getTime();
        }
        throw new IllegalStateException("TimePeriodValuesCollection.getX(...).");
    }
    
    public Number getStartXValue(final int series, final int item) {
        final TimePeriodValues ts = this.data.get(series);
        final TimePeriodValue dp = ts.getDataItem(item);
        return new Long(dp.getPeriod().getStart().getTime());
    }
    
    public Number getEndXValue(final int series, final int item) {
        final TimePeriodValues ts = this.data.get(series);
        final TimePeriodValue dp = ts.getDataItem(item);
        return new Long(dp.getPeriod().getEnd().getTime());
    }
    
    public Number getYValue(final int series, final int item) {
        final TimePeriodValues ts = this.data.get(series);
        final TimePeriodValue dp = ts.getDataItem(item);
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
        Range temp = null;
        for (final TimePeriodValues series : this.data) {
            final int count = series.getItemCount();
            if (count > 0) {
                final TimePeriod start = series.getTimePeriod(series.getMinStartIndex());
                final TimePeriod end = series.getTimePeriod(series.getMaxEndIndex());
                if (this.domainIsPointsInTime) {
                    if (this.xPosition == TimePeriodAnchor.START) {
                        final TimePeriod maxStart = series.getTimePeriod(series.getMaxStartIndex());
                        temp = new Range(start.getStart().getTime(), maxStart.getStart().getTime());
                    }
                    else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
                        final TimePeriod minMiddle = series.getTimePeriod(series.getMinMiddleIndex());
                        final long s1 = minMiddle.getStart().getTime();
                        final long e1 = minMiddle.getEnd().getTime();
                        final TimePeriod maxMiddle = series.getTimePeriod(series.getMaxMiddleIndex());
                        final long s2 = maxMiddle.getStart().getTime();
                        final long e2 = maxMiddle.getEnd().getTime();
                        temp = new Range(s1 + (e1 - s1) / 2L, s2 + (e2 - s2) / 2L);
                    }
                    else if (this.xPosition == TimePeriodAnchor.END) {
                        final TimePeriod minEnd = series.getTimePeriod(series.getMinEndIndex());
                        temp = new Range(minEnd.getEnd().getTime(), end.getEnd().getTime());
                    }
                }
                else {
                    temp = new Range(start.getStart().getTime(), end.getEnd().getTime());
                }
                result = Range.combine(result, temp);
            }
        }
        return result;
    }
}
