// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

public class Bound
{
    public int value;
    int proxyId;
    int stabbingCount;
    
    public Bound() {
        this.value = 0;
        this.proxyId = 0;
        this.stabbingCount = 0;
    }
    
    public Bound(final Bound b) {
        this.value = b.value;
        this.proxyId = b.proxyId;
        this.stabbingCount = b.stabbingCount;
    }
    
    public void set(final Bound b) {
        this.value = b.value;
        this.proxyId = b.proxyId;
        this.stabbingCount = b.stabbingCount;
    }
    
    boolean isLower() {
        return (this.value & 0x1) == 0x0;
    }
    
    boolean isUpper() {
        return (this.value & 0x1) == 0x1;
    }
    
    public String toString() {
        String ret = "Bound variable:\n";
        ret = String.valueOf(ret) + "value: " + this.value + "\n";
        ret = String.valueOf(ret) + "proxyId: " + this.proxyId + "\n";
        ret = String.valueOf(ret) + "stabbing count: " + this.stabbingCount + "\n";
        return ret;
    }
}
