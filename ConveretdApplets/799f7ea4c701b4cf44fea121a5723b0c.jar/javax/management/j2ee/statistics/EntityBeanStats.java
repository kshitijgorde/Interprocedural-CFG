// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface EntityBeanStats extends EJBStats
{
    RangeStatistic getReadyCount();
    
    RangeStatistic getPooledCount();
}
