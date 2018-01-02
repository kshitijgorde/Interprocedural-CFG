// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.scheduler.quartz;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Connection;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.db.mysql.ConnectionFactory;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;

public class QuartzDataSource implements DataSource
{
    private MysqlDataSource ds;
    private static final long serialVersionUID = 1L;
    
    public QuartzDataSource() {
        (this.ds = new MysqlDataSource()).setServerName(ConnectionFactory.getHostname());
        this.ds.setPort(Integer.parseInt(ConnectionFactory.getPort()));
        this.ds.setUser(ConnectionFactory.getUserid());
        this.ds.setPassword(ConnectionFactory.getPassword());
        this.ds.setDatabaseName(DbSession.DB.quartz.name());
    }
    
    public String getName() {
        return "quartzDS";
    }
    
    @Override
    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
    
    @Override
    public Connection getConnection(final String username, final String password) throws SQLException {
        return this.ds.getConnection(username, password);
    }
    
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return this.ds.getLogWriter();
    }
    
    @Override
    public int getLoginTimeout() throws SQLException {
        return this.ds.getLoginTimeout();
    }
    
    @Override
    public void setLogWriter(final PrintWriter out) throws SQLException {
        this.ds.setLogWriter(out);
    }
    
    @Override
    public void setLoginTimeout(final int seconds) throws SQLException {
        this.ds.setLoginTimeout(seconds);
    }
    
    @Override
    public boolean isWrapperFor(final Class<?> arg0) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public <T> T unwrap(final Class<T> arg0) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
