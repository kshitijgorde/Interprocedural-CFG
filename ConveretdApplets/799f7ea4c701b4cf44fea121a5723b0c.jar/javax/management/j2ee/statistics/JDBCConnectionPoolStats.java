// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface JDBCConnectionPoolStats extends JDBCConnectionStats
{
    CountStatistic getCreateCount();
    
    CountStatistic getCloseCount();
    
    BoundedRangeStatistic getPoolSize();
    
    BoundedRangeStatistic getFreePoolSize();
    
    RangeStatistic getWaitingThreadCount();
}
