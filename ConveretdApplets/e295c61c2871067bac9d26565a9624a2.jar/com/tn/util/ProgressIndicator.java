// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.util;

public interface ProgressIndicator
{
    void setProgress(final float p0);
    
    void setProgress(final int p0, final int p1);
    
    void setProgressText(final String p0);
    
    void setShowPercent(final boolean p0);
    
    void setVisible(final boolean p0);
}
