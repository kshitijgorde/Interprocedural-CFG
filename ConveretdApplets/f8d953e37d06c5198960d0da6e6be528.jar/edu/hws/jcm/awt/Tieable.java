// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.io.Serializable;

public interface Tieable extends Serializable
{
    long getSerialNumber();
    
    void sync(final Tie p0, final Tieable p1);
}
