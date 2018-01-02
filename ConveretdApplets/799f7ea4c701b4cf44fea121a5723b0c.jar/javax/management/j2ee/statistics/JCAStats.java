// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface JCAStats extends Stats
{
    JCAConnectionStats[] getConnections();
    
    JCAConnectionPoolStats[] getConnectionPools();
}
