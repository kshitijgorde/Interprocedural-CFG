// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee.statistics;

public interface JDBCStats extends Stats
{
    JDBCConnectionStats[] getConnections();
    
    JDBCConnectionPoolStats[] getConnectionPools();
}
