// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface JMSEndpointStats extends Stats
{
    CountStatistic getMessageCount();
    
    CountStatistic getPendingMessageCount();
    
    CountStatistic getExpiredMessageCount();
    
    TimeStatistic getMessageWaitTime();
}
