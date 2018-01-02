// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface JDBCConnectionStats extends Stats
{
    String getJdbcDataSource();
    
    TimeStatistic getWaitTime();
    
    TimeStatistic getUseTime();
}
