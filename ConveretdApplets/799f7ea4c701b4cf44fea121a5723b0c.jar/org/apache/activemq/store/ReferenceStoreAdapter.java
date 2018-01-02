// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store;

import org.apache.activemq.command.SubscriptionInfo;
import org.apache.activemq.store.amq.AMQTx;
import org.apache.activemq.command.TransactionId;
import java.util.Map;
import java.util.Set;
import org.apache.activemq.command.ActiveMQTopic;
import java.io.IOException;
import org.apache.activemq.command.ActiveMQQueue;

public interface ReferenceStoreAdapter extends PersistenceAdapter
{
    ReferenceStore createQueueReferenceStore(final ActiveMQQueue p0) throws IOException;
    
    TopicReferenceStore createTopicReferenceStore(final ActiveMQTopic p0) throws IOException;
    
    Set<Integer> getReferenceFileIdsInUse() throws IOException;
    
    boolean isStoreValid();
    
    void clearMessages() throws IOException;
    
    void recoverState() throws IOException;
    
    void savePreparedState(final Map<TransactionId, AMQTx> p0) throws IOException;
    
    Map<TransactionId, AMQTx> retrievePreparedState() throws IOException;
    
    long getMaxDataFileLength();
    
    void setMaxDataFileLength(final long p0);
    
    void recoverSubscription(final SubscriptionInfo p0) throws IOException;
}
