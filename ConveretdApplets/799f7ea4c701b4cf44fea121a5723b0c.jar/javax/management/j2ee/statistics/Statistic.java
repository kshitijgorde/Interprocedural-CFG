// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface Statistic
{
    String getName();
    
    String getUnit();
    
    String getDescription();
    
    long getStartTime();
    
    long getLastSampleTime();
}
