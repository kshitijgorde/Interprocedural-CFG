// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.EventListener;

public interface ServiceListener extends EventListener
{
    void serviceAdded(final ServiceEvent p0);
    
    void serviceRemoved(final ServiceEvent p0);
    
    void serviceResolved(final ServiceEvent p0);
}
