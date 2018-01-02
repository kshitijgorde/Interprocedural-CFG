// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db.mysql;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import javax.sql.ConnectionEvent;
import com.stonewall.cornerstone.db.Profiler;
import com.stonewall.cornerstone.utility.Stopwatch;
import java.util.HashSet;
import com.stonewall.cornerstone.db.DbException;
import org.xmodel.log.Log;
import java.util.concurrent.locks.ReentrantLock;
import javax.sql.PooledConnection;
import java.util.Set;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import javax.sql.ConnectionEventListener;

public class ConnectionFactory implements ConnectionEventListener
{
    private MysqlConnectionPoolDataSource ds;
    private final Set<PooledConnection> freeList;
    private final Set<PooledConnection> busyList;
    private static final ReentrantLock lock;
    private static ConnectionFactory inst;
    static final String DefaultName = "main";
    static final int retryDelay = 10000;
    static final Log log;
    
    static {
        lock = new ReentrantLock();
        ConnectionFactory.inst = null;
        log = Log.getLog(ConnectionFactory.class);
    }
    
    public static ConnectionFactory getInstance() throws DbException {
        ConnectionFactory.lock.lock();
        try {
            if (ConnectionFactory.inst == null) {
                ConnectionFactory.inst = new ConnectionFactory();
            }
            return ConnectionFactory.inst;
        }
        finally {
            ConnectionFactory.lock.unlock();
        }
    }
    
    private ConnectionFactory() throws DbException {
        this.ds = null;
        this.freeList = new HashSet<PooledConnection>();
        this.busyList = new HashSet<PooledConnection>();
    }
    
    public Connection getConnection() throws DbException {
        Connection result = null;
        final Stopwatch stopwatch = new Stopwatch();
        ConnectionFactory.lock.lock();
        try {
            ConnectionFactory.log.debug("Get MySQL connection");
            stopwatch.start();
            result = this.nextAvailable();
            stopwatch.stop();
            Profiler.getInstance().sessionOpened(stopwatch.duration());
        }
        finally {
            ConnectionFactory.lock.unlock();
        }
        ConnectionFactory.lock.unlock();
        return result;
    }
    
    @Override
    public void connectionClosed(final ConnectionEvent event) {
        ConnectionFactory.lock.lock();
        try {
            final PooledConnection pc = (PooledConnection)event.getSource();
            this.busyList.remove(pc);
            this.freeList.add(pc);
        }
        catch (Exception e) {
            ConnectionFactory.log.error("closed", e);
            return;
        }
        finally {
            ConnectionFactory.lock.unlock();
        }
        ConnectionFactory.lock.unlock();
    }
    
    @Override
    public void connectionErrorOccurred(final ConnectionEvent event) {
        ConnectionFactory.lock.lock();
        try {
            ConnectionFactory.log.error(event.getSQLException());
            final PooledConnection c = (PooledConnection)event.getSource();
            this.busyList.remove(c);
            c.close();
        }
        catch (Exception e) {
            ConnectionFactory.log.debug("connection-error", e);
            return;
        }
        finally {
            ConnectionFactory.lock.unlock();
        }
        ConnectionFactory.lock.unlock();
    }
    
    private Connection nextAvailable() throws DbException {
        PooledConnection result = null;
        ConnectionFactory.lock.lock();
        try {
            result = this.nextValid();
            if (result == null) {
                result = this.createConnection();
            }
            this.freeList.remove(result);
            this.busyList.add(result);
            final java.sql.Connection connection = result.getConnection();
            return new Connection(connection);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
        finally {
            ConnectionFactory.lock.unlock();
        }
    }
    
    private PooledConnection nextValid() throws SQLException {
        PooledConnection result = null;
        final List<PooledConnection> invalid = new ArrayList<PooledConnection>();
        for (final PooledConnection pc : this.freeList) {
            try {
                this.validate(pc);
                result = pc;
                break;
            }
            catch (Exception e) {
                invalid.add(pc);
            }
        }
        this.freeList.removeAll(invalid);
        return result;
    }
    
    private void validate(final PooledConnection c) throws Exception {
        if (c.getConnection() == null) {
            ConnectionFactory.log.info("Pooled connection - invalidated");
            throw new IllegalStateException();
        }
    }
    
    private PooledConnection createConnection() throws DbException {
        PooledConnection result = null;
        int i = 0;
        while (true) {
            try {
                ConnectionFactory.log.debug("Create MySQL (pooled) connection");
                if (this.ds == null) {
                    this.open();
                }
                result = this.addConnections();
            }
            catch (Exception e) {
                this.close();
                ConnectionFactory.log.error("Connect attempt #" + i + " failed", e);
                this.sleep(10000);
                ++i;
                continue;
            }
            break;
        }
        return result;
    }
    
    private void sleep(final int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (Exception e) {
            ConnectionFactory.log.debug(e);
        }
    }
    
    private void close() {
        try {
            this.ds = null;
            for (final PooledConnection c : this.busyList) {
                c.removeConnectionEventListener(this);
                c.close();
            }
            for (final PooledConnection c : this.freeList) {
                c.removeConnectionEventListener(this);
                c.close();
            }
            this.freeList.clear();
            this.busyList.clear();
        }
        catch (Exception e) {
            ConnectionFactory.log.error("closing", e);
        }
    }
    
    private void open() throws Exception {
        (this.ds = new MysqlConnectionPoolDataSource()).setServerName(getHostname());
        this.ds.setPort(Integer.parseInt(getPort()));
        this.ds.setUser(getUserid());
        this.ds.setPassword(getPassword());
    }
    
    private PooledConnection addConnections() throws Exception {
        PooledConnection result = null;
        for (int n = 0; n < 25; ++n) {
            result = this.ds.getPooledConnection();
            result.addConnectionEventListener(this);
            this.freeList.add(result);
            ConnectionFactory.log.debug("Pooled connection - created");
        }
        return result;
    }
    
    public static String getDatabaseName() {
        return System.getProperty("cornerstone.db.name", "main");
    }
    
    public static String getUserid() {
        return System.getProperty("cornerstone.db.username");
    }
    
    public static String getPassword() {
        return System.getProperty("cornerstone.db.password");
    }
    
    public static String getHostname() {
        return System.getProperty("cornerstone.db.hostname");
    }
    
    public static String getPort() {
        return System.getProperty("cornerstone.db.port");
    }
}
