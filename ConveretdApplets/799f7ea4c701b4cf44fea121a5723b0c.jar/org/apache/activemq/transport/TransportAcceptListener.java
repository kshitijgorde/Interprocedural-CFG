// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport;

public interface TransportAcceptListener
{
    void onAccept(final Transport p0);
    
    void onAcceptError(final Exception p0);
}
