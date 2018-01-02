// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.ObjectUtilities;
import org.jfree.data.general.SeriesException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.jfree.data.general.Series;

public class ComparableObjectSeries extends Series implements Cloneable, Serializable
{
    protected List data;
    private int maximumItemCount;
    private boolean autoSort;
    private boolean allowDuplicateXValues;
    
    public ComparableObjectSeries(final Comparable key) {
        this(key, true, true);
    }
    
    public ComparableObjectSeries(final Comparable key, final boolean autoSort, final boolean allowDuplicateXValues) {
        super(key);
        this.maximumItemCount = Integer.MAX_VALUE;
        this.data = new ArrayList();
        this.autoSort = autoSort;
        this.allowDuplicateXValues = allowDuplicateXValues;
    }
    
    public boolean getAutoSort() {
        return this.autoSort;
    }
    
    public boolean getAllowDuplicateXValues() {
        return this.allowDuplicateXValues;
    }
    
    public int getItemCount() {
        return this.data.size();
    }
    
    public int getMaximumItemCount() {
        return this.maximumItemCount;
    }
    
    public void setMaximumItemCount(final int maximum) {
        this.maximumItemCount = maximum;
        boolean dataRemoved = false;
        while (this.data.size() > maximum) {
            this.data.remove(0);
            dataRemoved = true;
        }
        if (dataRemoved) {
            this.fireSeriesChanged();
        }
    }
    
    protected void add(final Comparable x, final Object y) {
        this.add(x, y, true);
    }
    
    protected void add(final Comparable x, final Object y, final boolean notify) {
        final ComparableObjectItem item = new ComparableObjectItem(x, y);
        this.add(item, notify);
    }
    
    protected void add(final ComparableObjectItem item, final boolean notify) {
        if (item == null) {
            throw new IllegalArgumentException("Null 'item' argument.");
        }
        if (this.autoSort) {
            int index = Collections.binarySearch(this.data, item);
            if (index < 0) {
                this.data.add(-index - 1, item);
            }
            else {
                if (!this.allowDuplicateXValues) {
                    throw new SeriesException("X-value already exists.");
                }
                for (int size = this.data.size(); index < size && item.compareTo(this.data.get(index)) == 0; ++index) {}
                if (index < this.data.size()) {
                    this.data.add(index, item);
                }
                else {
                    this.data.add(item);
                }
            }
        }
        else {
            if (!this.allowDuplicateXValues) {
                final int index = this.indexOf(item.getComparable());
                if (index >= 0) {
                    throw new SeriesException("X-value already exists.");
                }
            }
            this.data.add(item);
        }
        if (this.getItemCount() > this.maximumItemCount) {
            this.data.remove(0);
        }
        if (notify) {
            this.fireSeriesChanged();
        }
    }
    
    public int indexOf(final Comparable x) {
        if (this.autoSort) {
            return Collections.binarySearch(this.data, new ComparableObjectItem(x, null));
        }
        for (int i = 0; i < this.data.size(); ++i) {
            final ComparableObjectItem item = this.data.get(i);
            if (item.getComparable().equals(x)) {
                return i;
            }
        }
        return -1;
    }
    
    protected void update(final Comparable x, final Object y) {
        final int index = this.indexOf(x);
        if (index < 0) {
            throw new SeriesException("No observation for x = " + x);
        }
        final ComparableObjectItem item = this.getDataItem(index);
        item.setObject(y);
        this.fireSeriesChanged();
    }
    
    protected void updateByIndex(final int index, final Object y) {
        final ComparableObjectItem item = this.getDataItem(index);
        item.setObject(y);
        this.fireSeriesChanged();
    }
    
    protected ComparableObjectItem getDataItem(final int index) {
        return this.data.get(index);
    }
    
    protected void delete(final int start, final int end) {
        for (int i = start; i <= end; ++i) {
            this.data.remove(start);
        }
        this.fireSeriesChanged();
    }
    
    protected void clear() {
        if (this.data.size() > 0) {
            this.data.clear();
            this.fireSeriesChanged();
        }
    }
    
    protected ComparableObjectItem remove(final int index) {
        final ComparableObjectItem result = this.data.remove(index);
        this.fireSeriesChanged();
        return result;
    }
    
    public ComparableObjectItem remove(final Comparable x) {
        return this.remove(this.indexOf(x));
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComparableObjectSeries)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final ComparableObjectSeries that = (ComparableObjectSeries)obj;
        return this.maximumItemCount == that.maximumItemCount && this.autoSort == that.autoSort && this.allowDuplicateXValues == that.allowDuplicateXValues && ObjectUtilities.equal(this.data, that.data);
    }
    
    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + ((this.data != null) ? this.data.hashCode() : 0);
        result = 29 * result + this.maximumItemCount;
        result = 29 * result + (this.autoSort ? 1 : 0);
        result = 29 * result + (this.allowDuplicateXValues ? 1 : 0);
        return result;
    }
}
