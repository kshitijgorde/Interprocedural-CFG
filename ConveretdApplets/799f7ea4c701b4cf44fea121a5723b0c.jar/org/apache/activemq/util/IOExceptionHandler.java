// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import java.io.IOException;
import org.apache.activemq.broker.BrokerServiceAware;

public interface IOExceptionHandler extends BrokerServiceAware
{
    void handle(final IOException p0);
}
