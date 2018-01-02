// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

class WindDataItem implements Comparable
{
    private Number x;
    private Number windDir;
    private Number windForce;
    
    public WindDataItem(final Number x, final Number windDir, final Number windForce) {
        this.x = x;
        this.windDir = windDir;
        this.windForce = windForce;
    }
    
    public Number getX() {
        return this.x;
    }
    
    public Number getWindDirection() {
        return this.windDir;
    }
    
    public Number getWindForce() {
        return this.windForce;
    }
    
    public int compareTo(final Object object) {
        if (!(object instanceof WindDataItem)) {
            throw new ClassCastException("WindDataItem.compareTo(error)");
        }
        final WindDataItem item = (WindDataItem)object;
        if (this.x.doubleValue() > item.x.doubleValue()) {
            return 1;
        }
        if (this.x.equals(item.x)) {
            return 0;
        }
        return -1;
    }
}
