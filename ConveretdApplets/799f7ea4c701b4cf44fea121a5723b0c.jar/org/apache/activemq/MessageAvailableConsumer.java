// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq;

import javax.jms.MessageConsumer;

public interface MessageAvailableConsumer extends MessageConsumer
{
    void setAvailableListener(final MessageAvailableListener p0);
    
    MessageAvailableListener getAvailableListener();
}
