// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.activity;

import java.util.EventListener;

public interface ActivityListener extends EventListener
{
    void activityScheduled(final Activity p0);
    
    void activityStarted(final Activity p0);
    
    void activityStepped(final Activity p0);
    
    void activityFinished(final Activity p0);
    
    void activityCancelled(final Activity p0);
}
