// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import java.util.Hashtable;
import java.sql.DriverManager;
import org.apache.xalan.res.XSLMessages;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Properties;
import java.sql.Driver;

public class DefaultConnectionPool implements ConnectionPool
{
    private Driver m_Driver;
    private static final boolean DEBUG = false;
    private String m_driver;
    private String m_url;
    private int m_PoolMinSize;
    private Properties m_ConnectionProtocol;
    private Vector m_pool;
    private boolean m_IsActive;
    
    public DefaultConnectionPool() {
        this.m_Driver = null;
        this.m_driver = new String("");
        this.m_url = new String("");
        this.m_PoolMinSize = 1;
        this.m_ConnectionProtocol = new Properties();
        this.m_pool = new Vector();
        this.m_IsActive = false;
    }
    
    public boolean isEnabled() {
        return this.m_IsActive;
    }
    
    public void setDriver(final String d) {
        this.m_driver = d;
    }
    
    public void setURL(final String url) {
        this.m_url = url;
    }
    
    public void freeUnused() {
        for (int x = 0; x < this.m_pool.size(); ++x) {
            final PooledConnection pcon = this.m_pool.elementAt(x);
            if (!pcon.inUse()) {
                pcon.close();
            }
        }
    }
    
    public boolean hasActiveConnections() {
        return this.m_pool.size() > 0;
    }
    
    public void setPassword(final String p) {
        ((Hashtable<String, String>)this.m_ConnectionProtocol).put("password", p);
    }
    
    public void setUser(final String u) {
        ((Hashtable<String, String>)this.m_ConnectionProtocol).put("user", u);
    }
    
    public void setProtocol(final Properties p) {
        final Enumeration e = p.keys();
        while (e.hasMoreElements()) {
            final String key = e.nextElement();
            ((Hashtable<String, String>)this.m_ConnectionProtocol).put(key, p.getProperty(key));
        }
    }
    
    public void setMinConnections(final int n) {
        this.m_PoolMinSize = n;
    }
    
    public boolean testConnection() {
        try {
            final Connection conn = this.getConnection();
            if (conn == null) {
                return false;
            }
            this.releaseConnection(conn);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public synchronized Connection getConnection() throws IllegalArgumentException, SQLException {
        PooledConnection pcon = null;
        if (this.m_pool.size() < this.m_PoolMinSize) {
            this.initializePool();
        }
        for (int x = 0; x < this.m_pool.size(); ++x) {
            pcon = this.m_pool.elementAt(x);
            if (!pcon.inUse()) {
                pcon.setInUse(true);
                return pcon.getConnection();
            }
        }
        final Connection con = this.createConnection();
        pcon = new PooledConnection(con);
        pcon.setInUse(true);
        this.m_pool.addElement(pcon);
        return pcon.getConnection();
    }
    
    public synchronized void releaseConnection(final Connection con) throws SQLException {
        int x = 0;
        while (x < this.m_pool.size()) {
            final PooledConnection pcon = this.m_pool.elementAt(x);
            if (pcon.getConnection() == con) {
                if (!this.isEnabled()) {
                    con.close();
                    this.m_pool.removeElementAt(x);
                    break;
                }
                pcon.setInUse(false);
                break;
            }
            else {
                ++x;
            }
        }
    }
    
    public synchronized void releaseConnectionOnError(final Connection con) throws SQLException {
        for (int x = 0; x < this.m_pool.size(); ++x) {
            final PooledConnection pcon = this.m_pool.elementAt(x);
            if (pcon.getConnection() == con) {
                con.close();
                this.m_pool.removeElementAt(x);
                break;
            }
        }
    }
    
    private Connection createConnection() throws SQLException {
        Connection con = null;
        con = this.m_Driver.connect(this.m_url, this.m_ConnectionProtocol);
        return con;
    }
    
    public synchronized void initializePool() throws IllegalArgumentException, SQLException {
        if (this.m_driver == null) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_NO_DRIVER_NAME_SPECIFIED", null));
        }
        if (this.m_url == null) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_NO_URL_SPECIFIED", null));
        }
        if (this.m_PoolMinSize < 1) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_POOLSIZE_LESS_THAN_ONE", null));
        }
        try {
            DriverManager.registerDriver(this.m_Driver = (Driver)ObjectFactory.newInstance(this.m_driver, ObjectFactory.findClassLoader(), true));
        }
        catch (ObjectFactory.ConfigurationError e) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_INVALID_DRIVER_NAME", null));
        }
        catch (Exception e2) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_INVALID_DRIVER_NAME", null));
        }
        if (!this.m_IsActive) {
            return;
        }
        do {
            final Connection con = this.createConnection();
            if (con != null) {
                final PooledConnection pcon = new PooledConnection(con);
                this.addConnection(pcon);
            }
        } while (this.m_pool.size() < this.m_PoolMinSize);
    }
    
    private void addConnection(final PooledConnection value) {
        this.m_pool.addElement(value);
    }
    
    protected void finalize() throws Throwable {
        for (int x = 0; x < this.m_pool.size(); ++x) {
            final PooledConnection pcon = this.m_pool.elementAt(x);
            if (!pcon.inUse()) {
                pcon.close();
            }
            else {
                try {
                    Thread.sleep(30000L);
                    pcon.close();
                }
                catch (InterruptedException ex) {}
            }
        }
        super.finalize();
    }
    
    public void setPoolEnabled(final boolean flag) {
        if (!(this.m_IsActive = flag)) {
            this.freeUnused();
        }
    }
}
