// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store;

import org.apache.activemq.usage.MemoryUsage;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.command.MessageId;
import java.util.concurrent.Future;
import java.io.IOException;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.Service;

public interface MessageStore extends Service
{
    void addMessage(final ConnectionContext p0, final Message p1) throws IOException;
    
    Future<Object> asyncAddQueueMessage(final ConnectionContext p0, final Message p1) throws IOException;
    
    Future<Object> asyncAddTopicMessage(final ConnectionContext p0, final Message p1) throws IOException;
    
    Message getMessage(final MessageId p0) throws IOException;
    
    void removeMessage(final ConnectionContext p0, final MessageAck p1) throws IOException;
    
    void removeAsyncMessage(final ConnectionContext p0, final MessageAck p1) throws IOException;
    
    void removeAllMessages(final ConnectionContext p0) throws IOException;
    
    void recover(final MessageRecoveryListener p0) throws Exception;
    
    ActiveMQDestination getDestination();
    
    void setMemoryUsage(final MemoryUsage p0);
    
    int getMessageCount() throws IOException;
    
    void resetBatching();
    
    void recoverNextMessages(final int p0, final MessageRecoveryListener p1) throws Exception;
    
    void dispose(final ConnectionContext p0);
    
    void setBatch(final MessageId p0) throws Exception;
    
    boolean isEmpty() throws Exception;
    
    void setPrioritizedMessages(final boolean p0);
    
    boolean isPrioritizedMessages();
}
