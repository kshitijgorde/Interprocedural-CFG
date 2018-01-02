// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface RangeStatistic extends Statistic
{
    long getHighWaterMark();
    
    long getLowWaterMark();
    
    long getCurrent();
}
