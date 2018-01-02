// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface JMSSessionStats extends Stats
{
    JMSProducerStats[] getProducers();
    
    JMSConsumerStats[] getConsumers();
    
    CountStatistic getMessageCount();
    
    CountStatistic getPendingMessageCount();
    
    CountStatistic getExpiredMessageCount();
    
    TimeStatistic getMessageWaitTime();
    
    CountStatistic getDurableSubscriptionCount();
}
