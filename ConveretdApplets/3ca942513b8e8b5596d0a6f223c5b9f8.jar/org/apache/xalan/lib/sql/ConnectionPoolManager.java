// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import org.apache.xalan.res.XSLMessages;
import java.util.Hashtable;

public class ConnectionPoolManager
{
    private static Hashtable m_poolTable;
    
    public ConnectionPoolManager() {
        this.init();
    }
    
    private synchronized void init() {
        if (ConnectionPoolManager.m_poolTable == null) {
            ConnectionPoolManager.m_poolTable = new Hashtable();
        }
    }
    
    public synchronized void registerPool(final String name, final ConnectionPool pool) {
        if (ConnectionPoolManager.m_poolTable.containsKey(name)) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_POOL_EXISTS", null));
        }
        ConnectionPoolManager.m_poolTable.put(name, pool);
    }
    
    public synchronized void removePool(final String name) {
        final ConnectionPool pool = this.getPool(name);
        if (null != pool) {
            pool.setPoolEnabled(false);
            if (!pool.hasActiveConnections()) {
                ConnectionPoolManager.m_poolTable.remove(name);
            }
        }
    }
    
    public synchronized ConnectionPool getPool(final String name) {
        return ConnectionPoolManager.m_poolTable.get(name);
    }
    
    static {
        ConnectionPoolManager.m_poolTable = null;
    }
}
