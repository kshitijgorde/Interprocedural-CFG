// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface JTAStats extends Stats
{
    CountStatistic getActiveCount();
    
    CountStatistic getCommittedCount();
    
    CountStatistic getRolledbackCount();
}
