// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.xy;

import org.jfree.data.ComparableObjectItem;
import org.jfree.data.ComparableObjectSeries;

public class XIntervalSeries extends ComparableObjectSeries
{
    public XIntervalSeries(final Comparable key) {
        this(key, true, true);
    }
    
    public XIntervalSeries(final Comparable key, final boolean autoSort, final boolean allowDuplicateXValues) {
        super(key, autoSort, allowDuplicateXValues);
    }
    
    public void add(final double x, final double xLow, final double xHigh, final double y) {
        super.add(new XIntervalDataItem(x, xLow, xHigh, y), true);
    }
    
    public Number getX(final int index) {
        final XIntervalDataItem item = (XIntervalDataItem)this.getDataItem(index);
        return item.getX();
    }
    
    public double getYValue(final int index) {
        final XIntervalDataItem item = (XIntervalDataItem)this.getDataItem(index);
        return item.getYValue();
    }
    
    public ComparableObjectItem getDataItem(final int index) {
        return super.getDataItem(index);
    }
}
