// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.EventListener;

public interface ServiceTypeListener extends EventListener
{
    void serviceTypeAdded(final ServiceEvent p0);
}
