// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.util;

import javax.jms.TextMessage;

public interface CommandHandler
{
    void processCommand(final TextMessage p0, final TextMessage p1) throws Exception;
}
