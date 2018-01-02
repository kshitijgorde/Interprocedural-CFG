// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface JCAConnectionStats extends Stats
{
    String getConnectionFactory();
    
    String getManagedConnectionFactory();
    
    TimeStatistic getWaitTime();
    
    TimeStatistic getUseTime();
}
