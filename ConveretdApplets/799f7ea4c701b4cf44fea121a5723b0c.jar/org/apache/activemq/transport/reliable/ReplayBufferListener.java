// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.reliable;

public interface ReplayBufferListener
{
    void onBufferDiscarded(final int p0, final Object p1);
}
