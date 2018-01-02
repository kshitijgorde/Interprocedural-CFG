// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db.mysql;

import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.Profiler;
import com.stonewall.cornerstone.utility.Stopwatch;
import com.stonewall.cornerstone.db.DbException;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.utility.StackTrace;
import com.stonewall.cornerstone.db.Transaction;
import com.stonewall.cornerstone.db.DbSession;

public class Connection implements DbSession
{
    private final java.sql.Connection connection;
    private boolean open;
    private Transaction transaction;
    private final StackTrace stackTrace;
    static final Log log;
    
    static {
        log = Log.getLog(Connection.class);
    }
    
    public Connection(final java.sql.Connection connection) {
        this.open = true;
        this.transaction = null;
        this.connection = connection;
        this.setAutoCommit(true);
        this.stackTrace = getTrace();
        Connection.log.debug("(JDBC) session ID: " + this.sessionId());
    }
    
    @Override
    protected void finalize() {
        if (this.open) {
            Connection.log.warn("Leak detected: connection finalized without close()\n" + this.context());
        }
    }
    
    @Override
    public boolean beginTransaction() throws DbException {
        this.setAutoCommit(false);
        return true;
    }
    
    @Override
    public void commit() throws DbException {
        if (this.getAutoCommit()) {
            Connection.log.warn("Automatic commit [enabled] - commit() ignored");
            return;
        }
        if (this.transacted()) {
            Connection.log.warn("Transacted - commit() ignored");
            return;
        }
        try {
            final Stopwatch timer = new Stopwatch();
            timer.start();
            this.connection.commit();
            timer.stop();
            Profiler.getInstance().sessionCommitted(timer.duration());
            Connection.log.debug("Transaction committed");
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public void rollback() {
        if (this.getAutoCommit()) {
            Connection.log.warn("Automatic commit [enabled] - rollback() ignored");
            return;
        }
        if (this.transacted()) {
            Connection.log.warn("Transacted - rollback() ignored");
            return;
        }
        try {
            this.connection.rollback();
            Connection.log.debug("Transaction rolled-back");
        }
        catch (Exception e) {
            Connection.log.debug(this.connection, e);
        }
    }
    
    @Override
    public void close() {
        if (!this.open) {
            Connection.log.warn("Duplicate close() - ignored");
            return;
        }
        if (this.transacted()) {
            Connection.log.warn("Transacted - close() ignored");
            return;
        }
        this.open = false;
        try {
            final long start = System.currentTimeMillis();
            this.connection.close();
            final long end = System.currentTimeMillis();
            Profiler.getInstance().sessionClosed(end - start);
        }
        catch (Exception e) {
            Connection.log.warn(this.context(), e);
        }
    }
    
    @Override
    public void setAutoCommit(final boolean flag) {
        try {
            this.connection.setAutoCommit(flag);
        }
        catch (Exception e) {
            Connection.log.debug(this.connection, e);
        }
    }
    
    @Override
    public void setTransaction(final Transaction tx) {
        this.transaction = tx;
    }
    
    @Override
    public Transaction getTransaction() {
        return this.transaction;
    }
    
    @Override
    public boolean getAutoCommit() {
        final boolean result = true;
        try {
            return this.connection.getAutoCommit();
        }
        catch (Exception e) {
            Connection.log.debug(this.connection, e);
            return result;
        }
    }
    
    @Override
    public void setDatabase(final String name) throws DbException {
        this.execute("use $0", name);
    }
    
    @Override
    public void setDatabase(final DB name) throws DbException {
        this.execute("use $0", name.name());
    }
    
    @Override
    public List<String> getDatabases() throws DbException {
        DbStatement stmt = null;
        final List<String> list = new ArrayList<String>();
        final String query = "for $d in tig:list-databases()\nreturn string($d/tig:database-descriptor/tig:name)\n";
        try {
            stmt = this.createStatement("for $d in tig:list-databases()\nreturn string($d/tig:database-descriptor/tig:name)\n");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(result.getString());
            }
        }
        finally {
            stmt.close();
        }
        stmt.close();
        return list;
    }
    
    @Override
    public void createDatabase(final String name) throws DbException {
        this.execute("create database $0", name);
        Connection.log.info("Database: " + name + " created.");
    }
    
    @Override
    public void dropDatabase(final String name) throws DbException {
        this.execute("drop database if exists $0", name);
        Connection.log.info("Database: " + name + " deleted.");
    }
    
    @Override
    public DbStatement createStatement() throws DbException {
        try {
            final java.sql.Statement stmt = this.connection.createStatement();
            return new Statement(stmt);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public DbStatement createStatement(final String content) throws DbException {
        try {
            final java.sql.Statement stmt = this.connection.createStatement();
            return new Statement(stmt, content);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public QueryBuilder queryBuilder() {
        return new QueryBuilder();
    }
    
    @Override
    public QueryBuilder queryBuilder(final String content) {
        return new QueryBuilder(content);
    }
    
    @Override
    public String database() {
        return System.getProperty("cornerstone.db.name", "main");
    }
    
    @Override
    public void execute(final String sql, final String... arg) throws DbException {
        final DbStatement stmt = this.createStatement(sql);
        try {
            stmt.set(DbStatement.Mode.Update);
            for (int i = 0; i < arg.length; ++i) {
                stmt.set(String.valueOf(i), arg[i]);
            }
            stmt.execute();
        }
        catch (Exception e) {
            throw new DbException(e);
        }
        finally {
            stmt.close();
        }
        stmt.close();
    }
    
    String context() {
        final StringBuilder sb = new StringBuilder();
        sb.append("(JDBC) session: ");
        sb.append(this.sessionId());
        sb.append("\nLocation: ");
        sb.append((this.stackTrace != null) ? this.stackTrace : "trace not enabled.");
        return sb.toString();
    }
    
    String sessionId() {
        return "";
    }
    
    boolean transacted() {
        return this.transaction != null;
    }
    
    private static StackTrace getTrace() {
        final StackTrace result = null;
        final boolean enabled = Profiler.getInstance().getEnabled();
        if (enabled) {
            return new StackTrace(1).filter("com\\.stonewall");
        }
        return result;
    }
}
