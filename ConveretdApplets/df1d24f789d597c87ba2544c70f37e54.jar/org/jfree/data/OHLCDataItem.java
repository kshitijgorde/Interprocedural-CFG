// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.Date;

public class OHLCDataItem implements Comparable
{
    private Date date;
    private Number open;
    private Number high;
    private Number low;
    private Number close;
    private Number volume;
    
    public OHLCDataItem(final Date date, final double open, final double high, final double low, final double close, final double volume) {
        this.date = date;
        this.open = new Double(open);
        this.high = new Double(high);
        this.low = new Double(low);
        this.close = new Double(close);
        this.volume = new Double(volume);
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public Number getOpen() {
        return this.open;
    }
    
    public Number getHigh() {
        return this.high;
    }
    
    public Number getLow() {
        return this.low;
    }
    
    public Number getClose() {
        return this.close;
    }
    
    public Number getVolume() {
        return this.volume;
    }
    
    public int compareTo(final Object object) {
        if (object instanceof OHLCDataItem) {
            final OHLCDataItem item = (OHLCDataItem)object;
            return this.date.compareTo(item.date);
        }
        throw new ClassCastException("OHLCDataItem.compareTo(...).");
    }
}
