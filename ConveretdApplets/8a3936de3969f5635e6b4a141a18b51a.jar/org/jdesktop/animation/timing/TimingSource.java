// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.animation.timing;

import java.util.ArrayList;

public abstract class TimingSource
{
    public TimingSource() {
        new ArrayList();
    }
    
    public abstract void start();
    
    public abstract void stop();
    
    public abstract void setResolution(final int p0);
    
    public abstract void setStartDelay(final int p0);
}
