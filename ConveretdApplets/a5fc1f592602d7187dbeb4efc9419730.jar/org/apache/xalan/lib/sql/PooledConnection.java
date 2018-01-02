// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import java.sql.SQLException;
import java.sql.Connection;

public class PooledConnection
{
    private Connection connection;
    private boolean inuse;
    
    public PooledConnection(final Connection value) {
        this.connection = null;
        this.inuse = false;
        if (value != null) {
            this.connection = value;
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }
    
    public void setInUse(final boolean value) {
        this.inuse = value;
    }
    
    public boolean inUse() {
        return this.inuse;
    }
    
    public void close() {
        try {
            this.connection.close();
        }
        catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
}
