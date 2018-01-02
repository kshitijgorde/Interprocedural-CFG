// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

public interface CommunicationAction
{
    void actionOnViewPortChanged(final double p0, final double p1, final double p2);
    
    void actionOnPanoSwitching(final int p0, final double p1, final double p2, final double p3);
    
    void actionOnPanoSwitched(final int p0);
    
    void pathStateChanged(final int p0, final int p1);
}
