// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.xy;

import org.jfree.data.ComparableObjectItem;
import org.jfree.data.ComparableObjectSeries;

public class VectorSeries extends ComparableObjectSeries
{
    public VectorSeries(final Comparable key) {
        this(key, false, true);
    }
    
    public VectorSeries(final Comparable key, final boolean autoSort, final boolean allowDuplicateXValues) {
        super(key, autoSort, allowDuplicateXValues);
    }
    
    public void add(final double x, final double y, final double deltaX, final double deltaY) {
        super.add(new VectorDataItem(x, y, deltaX, deltaY), true);
    }
    
    public ComparableObjectItem remove(final int index) {
        final VectorDataItem result = this.data.remove(index);
        this.fireSeriesChanged();
        return result;
    }
    
    public void clear() {
        if (this.data.size() > 0) {
            this.data.clear();
            this.fireSeriesChanged();
        }
    }
    
    public double getXValue(final int index) {
        final VectorDataItem item = (VectorDataItem)this.getDataItem(index);
        return item.getXValue();
    }
    
    public double getYValue(final int index) {
        final VectorDataItem item = (VectorDataItem)this.getDataItem(index);
        return item.getYValue();
    }
    
    public double getVectorXValue(final int index) {
        final VectorDataItem item = (VectorDataItem)this.getDataItem(index);
        return item.getVectorX();
    }
    
    public double getVectorYValue(final int index) {
        final VectorDataItem item = (VectorDataItem)this.getDataItem(index);
        return item.getVectorY();
    }
    
    public ComparableObjectItem getDataItem(final int index) {
        return super.getDataItem(index);
    }
}
