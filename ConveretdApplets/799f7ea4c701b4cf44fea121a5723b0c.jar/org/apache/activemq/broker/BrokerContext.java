// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker;

import java.util.Map;

public interface BrokerContext
{
    Object getBean(final String p0);
    
    Map getBeansOfType(final Class p0);
}
