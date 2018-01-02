// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

public class Pair implements Comparable<Pair>
{
    private static final int PAIR_BUFFERED = 1;
    private static final int PAIR_REMOVED = 2;
    private static final int PAIR_FINAL = 4;
    public Object userData;
    public int proxyId1;
    public int proxyId2;
    public int status;
    public int next;
    
    public Pair() {
    }
    
    public Pair(final Pair other) {
        this.userData = other.userData;
        this.proxyId1 = other.proxyId1;
        this.proxyId2 = other.proxyId2;
        this.status = other.status;
    }
    
    public void setBuffered() {
        this.status |= 0x1;
    }
    
    public void clearBuffered() {
        this.status &= 0xFFFFFFFE;
    }
    
    public boolean isBuffered() {
        return (this.status & 0x1) == 0x1;
    }
    
    public void clearRemoved() {
        this.status &= 0xFFFFFFFD;
    }
    
    public void setRemoved() {
        this.status |= 0x2;
    }
    
    public boolean isRemoved() {
        return (this.status & 0x2) == 0x2;
    }
    
    public void setFinal() {
        this.status |= 0x4;
    }
    
    public boolean isFinal() {
        return (this.status & 0x4) == 0x4;
    }
    
    public int compareTo(final Pair p) {
        return this.proxyId1 - p.proxyId1;
    }
}
