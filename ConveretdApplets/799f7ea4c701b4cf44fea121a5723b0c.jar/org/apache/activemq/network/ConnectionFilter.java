// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import java.net.URI;

public interface ConnectionFilter
{
    boolean connectTo(final URI p0);
}
