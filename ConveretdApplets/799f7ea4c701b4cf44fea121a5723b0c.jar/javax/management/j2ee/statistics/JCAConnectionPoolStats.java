// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface JCAConnectionPoolStats extends JCAConnectionStats
{
    CountStatistic getCloseCount();
    
    CountStatistic getCreateCount();
    
    BoundedRangeStatistic getFreePoolSize();
    
    BoundedRangeStatistic getPoolSize();
    
    RangeStatistic getWaitingThreadCount();
}
