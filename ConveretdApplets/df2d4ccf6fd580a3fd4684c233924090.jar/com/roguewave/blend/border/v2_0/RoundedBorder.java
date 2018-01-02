// 
// Decompiled by Procyon v0.5.30
// 

package com.roguewave.blend.border.v2_0;

public abstract class RoundedBorder extends Border
{
    int m_nPercentRounded;
    
    public void setPercentRounded(final int nPercentRounded) {
        this.m_nPercentRounded = nPercentRounded;
    }
    
    public int getPercentRounded() {
        return this.m_nPercentRounded;
    }
    
    public int getArcLength(final int n) {
        return n * this.m_nPercentRounded / 100;
    }
    
    public void copyFrom(final Border border) {
        if (border instanceof RoundedBorder) {
            this.m_nPercentRounded = ((RoundedBorder)border).m_nPercentRounded;
        }
        super.copyFrom(border);
    }
}
