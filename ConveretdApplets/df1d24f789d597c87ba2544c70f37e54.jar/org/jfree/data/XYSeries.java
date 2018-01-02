// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.ObjectUtils;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class XYSeries extends Series implements Cloneable, Serializable
{
    protected List data;
    private int maximumItemCount;
    private boolean autoSort;
    private boolean allowDuplicateXValues;
    
    public XYSeries(final String name) {
        this(name, true, true);
    }
    
    public XYSeries(final String name, final boolean autoSort, final boolean allowDuplicateXValues) {
        super(name);
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
    
    public List getItems() {
        return Collections.unmodifiableList((List<?>)this.data);
    }
    
    public int getMaximumItemCount() {
        return this.maximumItemCount;
    }
    
    public void setMaximumItemCount(final int maximum) {
        this.maximumItemCount = maximum;
    }
    
    public void add(final XYDataItem item) {
        this.add(item, true);
    }
    
    public void add(final XYDataItem item, final boolean notify) {
        if (item == null) {
            throw new IllegalArgumentException("Null 'item' argument.");
        }
        final int index = Collections.binarySearch(this.data, item);
        if (index < 0) {
            if (this.autoSort) {
                this.data.add(-index - 1, item);
            }
            else {
                this.data.add(item);
            }
            if (this.getItemCount() > this.maximumItemCount) {
                this.data.remove(0);
            }
        }
        else {
            if (!this.allowDuplicateXValues) {
                throw new SeriesException("XYSeries.add(...): x-value already exists.");
            }
            if (this.autoSort) {
                this.data.add(index, item);
            }
            else {
                this.data.add(item);
            }
            if (this.getItemCount() > this.maximumItemCount) {
                this.data.remove(0);
            }
        }
        if (notify) {
            this.fireSeriesChanged();
        }
    }
    
    public void add(final double x, final double y) {
        this.add(new Double(x), new Double(y), true);
    }
    
    public void add(final double x, final double y, final boolean notify) {
        this.add(new Double(x), new Double(y), notify);
    }
    
    public void add(final double x, final Number y) {
        this.add(new Double(x), y);
    }
    
    public void add(final double x, final Number y, final boolean notify) {
        this.add(new Double(x), y, notify);
    }
    
    public void add(final Number x, final Number y) {
        this.add(x, y, true);
    }
    
    public void add(final Number x, final Number y, final boolean notify) {
        if (x == null) {
            throw new IllegalArgumentException("Null 'x' argument.");
        }
        final XYDataItem item = new XYDataItem(x, y);
        this.add(item, notify);
    }
    
    public void delete(final int start, final int end) {
        for (int i = start; i <= end; ++i) {
            this.data.remove(start);
        }
        this.fireSeriesChanged();
    }
    
    public XYDataItem remove(final int index) {
        return this.data.remove(index);
    }
    
    public XYDataItem remove(final Number x) {
        return this.remove(this.indexOf(x));
    }
    
    public void clear() {
        if (this.data.size() > 0) {
            this.data.clear();
            this.fireSeriesChanged();
        }
    }
    
    public XYDataItem getDataItem(final int index) {
        return this.data.get(index);
    }
    
    public Number getXValue(final int index) {
        return this.getDataItem(index).getX();
    }
    
    public Number getYValue(final int index) {
        return this.getDataItem(index).getY();
    }
    
    public void update(final int index, final Number y) {
        final XYDataItem item = this.getDataItem(index);
        item.setY(y);
        this.fireSeriesChanged();
    }
    
    public int indexOf(final Number x) {
        return Collections.binarySearch(this.data, new XYDataItem(x, null));
    }
    
    public Object clone() throws CloneNotSupportedException {
        final Object clone = this.createCopy(0, this.getItemCount() - 1);
        return clone;
    }
    
    public XYSeries createCopy(final int start, final int end) throws CloneNotSupportedException {
        final XYSeries copy = (XYSeries)super.clone();
        copy.data = new ArrayList();
        if (this.data.size() > 0) {
            for (int index = start; index <= end; ++index) {
                final XYDataItem item = this.data.get(index);
                final XYDataItem clone = (XYDataItem)item.clone();
                try {
                    copy.add(clone);
                }
                catch (SeriesException e) {
                    System.err.println("XYSeries.createCopy(): unable to add cloned data pair.");
                }
            }
        }
        return copy;
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!super.equals(object)) {
            return false;
        }
        if (!(object instanceof XYSeries)) {
            return false;
        }
        final XYSeries s = (XYSeries)object;
        return this.maximumItemCount == s.maximumItemCount && this.autoSort == s.autoSort && this.allowDuplicateXValues == s.allowDuplicateXValues && ObjectUtils.equal(this.data, s.data);
    }
    
    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + ((this.data != null) ? this.data.hashCode() : 0);
        result = 29 * result + this.maximumItemCount;
        result = 29 * result + (this.autoSort ? 1 : 0);
        result = 29 * result + (this.allowDuplicateXValues ? 1 : 0);
        return result;
    }
    
    public XYSeries(final String name, final boolean allowDuplicateXValues) {
        this(name, true, allowDuplicateXValues);
    }
    
    public XYDataPair getDataPair(final int index) {
        return this.data.get(index);
    }
}
