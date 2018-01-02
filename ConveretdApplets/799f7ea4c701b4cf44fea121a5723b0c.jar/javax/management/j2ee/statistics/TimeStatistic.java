// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface TimeStatistic extends Statistic
{
    long getCount();
    
    long getMaxTime();
    
    long getMinTime();
    
    long getTotalTime();
}
