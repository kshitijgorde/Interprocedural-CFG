// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import java.util.Properties;
import java.sql.SQLException;
import java.sql.Connection;

public interface ConnectionPool
{
    boolean isEnabled();
    
    void setDriver(final String p0);
    
    void setURL(final String p0);
    
    void freeUnused();
    
    boolean hasActiveConnections();
    
    void setPassword(final String p0);
    
    void setUser(final String p0);
    
    void setMinConnections(final int p0);
    
    boolean testConnection();
    
    Connection getConnection() throws SQLException;
    
    void releaseConnection(final Connection p0) throws SQLException;
    
    void releaseConnectionOnError(final Connection p0) throws SQLException;
    
    void setPoolEnabled(final boolean p0);
    
    void setProtocol(final Properties p0);
}
