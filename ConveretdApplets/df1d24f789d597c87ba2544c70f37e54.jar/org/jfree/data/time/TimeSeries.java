// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import org.jfree.data.SeriesException;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.jfree.data.Series;

public class TimeSeries extends Series implements Cloneable, Serializable
{
    protected static final String DEFAULT_DOMAIN_DESCRIPTION = "Time";
    protected static final String DEFAULT_RANGE_DESCRIPTION = "Value";
    private String domain;
    private String range;
    protected Class timePeriodClass;
    protected List data;
    private int maximumItemCount;
    private int historyCount;
    static /* synthetic */ Class class$org$jfree$data$time$Day;
    
    public TimeSeries(final String name) {
        this(name, "Time", "Value", (TimeSeries.class$org$jfree$data$time$Day == null) ? (TimeSeries.class$org$jfree$data$time$Day = class$("org.jfree.data.time.Day")) : TimeSeries.class$org$jfree$data$time$Day);
    }
    
    public TimeSeries(final String name, final Class timePeriodClass) {
        this(name, "Time", "Value", timePeriodClass);
    }
    
    public TimeSeries(final String name, final String domain, final String range, final Class timePeriodClass) {
        super(name);
        this.domain = domain;
        this.range = range;
        this.timePeriodClass = timePeriodClass;
        this.data = new ArrayList();
        this.maximumItemCount = Integer.MAX_VALUE;
        this.historyCount = 0;
    }
    
    public String getDomainDescription() {
        return this.domain;
    }
    
    public void setDomainDescription(final String description) {
        final String old = this.domain;
        this.firePropertyChange("Domain", old, this.domain = description);
    }
    
    public String getRangeDescription() {
        return this.range;
    }
    
    public void setRangeDescription(final String description) {
        final String old = this.range;
        this.firePropertyChange("Range", old, this.range = description);
    }
    
    public int getItemCount() {
        return this.data.size();
    }
    
    public List getItems() {
        return Collections.unmodifiableList((List<?>)this.data);
    }
    
    public int getMaximumItemCount() {
        return this.maximumItemCount;
    }
    
    public void setMaximumItemCount(final int maximum) {
        this.maximumItemCount = maximum;
        while (this.data.size() > this.maximumItemCount) {
            this.data.remove(0);
        }
    }
    
    public int getHistoryCount() {
        return this.historyCount;
    }
    
    public void setHistoryCount(final int periods) {
        this.historyCount = periods;
    }
    
    public Class getTimePeriodClass() {
        return this.timePeriodClass;
    }
    
    public TimeSeriesDataItem getDataItem(final int index) {
        return this.data.get(index);
    }
    
    public TimeSeriesDataItem getDataItem(final RegularTimePeriod period) {
        if (period == null) {
            throw new IllegalArgumentException("Null 'period' argument");
        }
        final TimeSeriesDataItem dummy = new TimeSeriesDataItem(period, new Integer(0));
        final int index = Collections.binarySearch(this.data, dummy);
        if (index >= 0) {
            return this.data.get(index);
        }
        return null;
    }
    
    public RegularTimePeriod getTimePeriod(final int index) {
        return this.getDataItem(index).getPeriod();
    }
    
    public RegularTimePeriod getNextTimePeriod() {
        final RegularTimePeriod last = this.getTimePeriod(this.getItemCount() - 1);
        return last.next();
    }
    
    public Collection getTimePeriods() {
        final Collection result = new ArrayList();
        for (int i = 0; i < this.getItemCount(); ++i) {
            result.add(this.getTimePeriod(i));
        }
        return result;
    }
    
    public Collection getTimePeriodsUniqueToOtherSeries(final TimeSeries series) {
        final Collection result = new ArrayList();
        for (int i = 0; i < series.getItemCount(); ++i) {
            final RegularTimePeriod period = series.getTimePeriod(i);
            final int index = this.getIndex(period);
            if (index < 0) {
                result.add(period);
            }
        }
        return result;
    }
    
    public int getIndex(final RegularTimePeriod period) {
        if (period == null) {
            throw new IllegalArgumentException("Null 'period' argument.");
        }
        final TimeSeriesDataItem dummy = new TimeSeriesDataItem(period, new Integer(0));
        final int index = Collections.binarySearch(this.data, dummy);
        return index;
    }
    
    public Number getValue(final int index) {
        return this.getDataItem(index).getValue();
    }
    
    public Number getValue(final RegularTimePeriod period) {
        final int index = this.getIndex(period);
        if (index >= 0) {
            return this.getValue(index);
        }
        return null;
    }
    
    public void add(final TimeSeriesDataItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Null 'item' argument.");
        }
        if (!item.getPeriod().getClass().equals(this.timePeriodClass)) {
            String message = "TimeSeries.add(): you are trying to add data where the time ";
            message = message + "period class is " + item.getPeriod().getClass().getName() + ", ";
            message = message + "but the TimeSeries is expecting an instance of " + this.timePeriodClass.getName() + ".";
            throw new SeriesException(message);
        }
        final int index = Collections.binarySearch(this.data, item);
        if (index < 0) {
            this.data.add(-index - 1, item);
            if (this.getItemCount() > this.maximumItemCount) {
                this.data.remove(0);
            }
            this.ageHistoryCountItems();
            this.fireSeriesChanged();
            return;
        }
        String message2 = "TimeSeries.add(): you are attempting to add an observation for ";
        message2 = message2 + "the time period " + item.getPeriod().toString() + " but the ";
        message2 += "series already contains an observation for that time period.  ";
        message2 += "Duplicates are not permitted.  Try using the addOrUpdate() method.";
        throw new SeriesException(message2);
    }
    
    public void add(final RegularTimePeriod period, final double value) {
        final TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
        this.add(item);
    }
    
    public void add(final RegularTimePeriod period, final Number value) {
        final TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
        this.add(item);
    }
    
    public void update(final RegularTimePeriod period, final Number value) {
        final TimeSeriesDataItem temp = new TimeSeriesDataItem(period, value);
        final int index = Collections.binarySearch(this.data, temp);
        if (index >= 0) {
            final TimeSeriesDataItem pair = this.data.get(index);
            pair.setValue(value);
            this.fireSeriesChanged();
            return;
        }
        throw new SeriesException("TimeSeries.update(TimePeriod, Number):  period does not exist.");
    }
    
    public void update(final int index, final Number value) {
        final TimeSeriesDataItem item = this.getDataItem(index);
        item.setValue(value);
        this.fireSeriesChanged();
    }
    
    public TimeSeries addAndOrUpdate(final TimeSeries series) {
        final TimeSeries overwritten = new TimeSeries("Overwritten values from: " + this.getName());
        for (int i = 0; i < series.getItemCount(); ++i) {
            final TimeSeriesDataItem pair = series.getDataItem(i);
            final TimeSeriesDataItem oldPair = this.addOrUpdate(pair.getPeriod(), pair.getValue());
            if (oldPair != null) {
                try {
                    overwritten.add(oldPair);
                }
                catch (SeriesException e) {
                    System.err.println("TimeSeries.addAndOrUpdate(series): unable to add data to overwritten series.");
                }
            }
        }
        return overwritten;
    }
    
    public TimeSeriesDataItem addOrUpdate(final RegularTimePeriod period, final double value) {
        return this.addOrUpdate(period, new Double(value));
    }
    
    public TimeSeriesDataItem addOrUpdate(final RegularTimePeriod period, final Number value) {
        if (period == null) {
            throw new IllegalArgumentException("Null 'period' argument.");
        }
        TimeSeriesDataItem overwritten = null;
        final TimeSeriesDataItem key = new TimeSeriesDataItem(period, value);
        final int index = Collections.binarySearch(this.data, key);
        if (index >= 0) {
            final TimeSeriesDataItem existing = this.data.get(index);
            overwritten = (TimeSeriesDataItem)existing.clone();
            existing.setValue(value);
            this.ageHistoryCountItems();
            this.fireSeriesChanged();
        }
        else {
            this.data.add(-index - 1, new TimeSeriesDataItem(period, value));
            this.ageHistoryCountItems();
            this.fireSeriesChanged();
        }
        return overwritten;
    }
    
    public void ageHistoryCountItems() {
        if (this.getItemCount() > 1 && this.historyCount > 0) {
            final long latest = this.getTimePeriod(this.getItemCount() - 1).getSerialIndex();
            while (latest - this.getTimePeriod(0).getSerialIndex() >= this.historyCount) {
                this.data.remove(0);
            }
        }
    }
    
    public void ageHistoryCountItems(final long latest) {
        if (this.getItemCount() > 1 && this.historyCount > 0) {
            while (latest - this.getTimePeriod(0).getSerialIndex() >= this.historyCount) {
                this.data.remove(0);
            }
        }
    }
    
    public void clear() {
        if (this.data.size() > 0) {
            this.data.clear();
            this.fireSeriesChanged();
        }
    }
    
    public void delete(final RegularTimePeriod period) {
        final int index = this.getIndex(period);
        this.data.remove(index);
        this.fireSeriesChanged();
    }
    
    public void delete(final int start, final int end) {
        for (int i = 0; i <= end - start; ++i) {
            this.data.remove(start);
        }
        this.fireSeriesChanged();
    }
    
    public Object clone() throws CloneNotSupportedException {
        final Object clone = this.createCopy(0, this.getItemCount() - 1);
        return clone;
    }
    
    public TimeSeries createCopy(final int start, final int end) throws CloneNotSupportedException {
        final TimeSeries copy = (TimeSeries)super.clone();
        copy.data = new ArrayList();
        if (this.data.size() > 0) {
            for (int index = start; index <= end; ++index) {
                final TimeSeriesDataItem item = this.data.get(index);
                final TimeSeriesDataItem clone = (TimeSeriesDataItem)item.clone();
                try {
                    copy.add(clone);
                }
                catch (SeriesException e) {
                    System.err.println("TimeSeries.createCopy(): unable to add cloned data item.");
                }
            }
        }
        return copy;
    }
    
    public TimeSeries createCopy(final RegularTimePeriod start, final RegularTimePeriod end) throws CloneNotSupportedException {
        int startIndex = this.getIndex(start);
        if (startIndex < 0) {
            startIndex = -(startIndex + 1);
        }
        int endIndex = this.getIndex(end);
        if (endIndex < 0) {
            endIndex = -(endIndex + 1);
            --endIndex;
        }
        final TimeSeries result = this.createCopy(startIndex, endIndex);
        return result;
    }
    
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof TimeSeries) || !super.equals(object)) {
            return false;
        }
        final TimeSeries s = (TimeSeries)object;
        if (!this.getDomainDescription().equals(s.getDomainDescription())) {
            return false;
        }
        if (!this.getRangeDescription().equals(s.getRangeDescription())) {
            return false;
        }
        if (!this.getClass().equals(s.getClass())) {
            return false;
        }
        if (this.getHistoryCount() != s.getHistoryCount()) {
            return false;
        }
        if (this.getMaximumItemCount() != s.getMaximumItemCount()) {
            return false;
        }
        final int count = this.getItemCount();
        if (count != s.getItemCount()) {
            return false;
        }
        for (int i = 0; i < count; ++i) {
            if (!this.getDataItem(i).equals(s.getDataItem(i))) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        int result = (this.domain != null) ? this.domain.hashCode() : 0;
        result = 29 * result + ((this.range != null) ? this.range.hashCode() : 0);
        result = 29 * result + ((this.timePeriodClass != null) ? this.timePeriodClass.hashCode() : 0);
        result = 29 * result + this.data.hashCode();
        result = 29 * result + this.maximumItemCount;
        result = 29 * result + this.historyCount;
        return result;
    }
    
    public TimeSeriesDataItem getDataPair(final int index) {
        return this.getDataItem(index);
    }
    
    public TimeSeriesDataItem getDataPair(final RegularTimePeriod period) {
        return this.getDataItem(period);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
