// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import org.apache.activemq.command.ActiveMQMessage;

public class ActiveMQMessageUtils
{
    public static int getWireSize(final ActiveMQMessage message) {
        int size = 0;
        if (message.getMarshalledProperties() != null) {
            size += message.getMarshalledProperties().getLength();
        }
        if (message.getContent() != null) {
            size += message.getContent().getLength();
        }
        return size;
    }
}
