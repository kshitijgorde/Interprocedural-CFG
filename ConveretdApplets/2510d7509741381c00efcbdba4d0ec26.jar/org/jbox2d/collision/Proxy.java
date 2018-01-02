// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

public class Proxy
{
    public int[] lowerBounds;
    public int[] upperBounds;
    int overlapCount;
    int timeStamp;
    int categoryBits;
    int maskBits;
    int groupIndex;
    Object userData;
    
    public Proxy() {
        this.lowerBounds = new int[2];
        this.upperBounds = new int[2];
        this.lowerBounds[0] = (this.lowerBounds[1] = 0);
        this.upperBounds[0] = (this.upperBounds[1] = 0);
        this.overlapCount = Integer.MAX_VALUE;
        this.timeStamp = 0;
    }
    
    int getNext() {
        return this.lowerBounds[0];
    }
    
    void setNext(final int next) {
        this.lowerBounds[0] = next;
    }
    
    public boolean isValid() {
        return this.overlapCount != Integer.MAX_VALUE;
    }
}
