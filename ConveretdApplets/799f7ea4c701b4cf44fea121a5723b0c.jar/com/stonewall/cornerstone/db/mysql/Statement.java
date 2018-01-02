// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db.mysql;

import com.stonewall.cornerstone.db.LockingException;
import com.stonewall.cornerstone.db.DuplicateKeyException;
import java.sql.SQLWarning;
import com.stonewall.cornerstone.db.DbException;
import java.sql.SQLException;
import com.stonewall.cornerstone.db.Profiler;
import com.stonewall.cornerstone.utility.Stopwatch;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.BasicStatement;

public class Statement extends BasicStatement implements DbStatement
{
    static final int Retries = 25;
    static final int Deadlock = 1213;
    static final int LockWait = 1205;
    static final int DuplicateKey = 1062;
    private final java.sql.Statement statement;
    
    public Statement(final java.sql.Statement statement) {
        super(new QueryBuilder());
        this.statement = statement;
    }
    
    public Statement(final java.sql.Statement statement, final String content) {
        super(new QueryBuilder(content));
        this.statement = statement;
    }
    
    @Override
    public DbResultSet execute() throws DbException {
        int retry = 25;
        final String query = this.toString();
        java.sql.ResultSet resultSet = null;
        final Stopwatch timer = new Stopwatch();
        while (retry > 0) {
            try {
                Statement.log.debug(query);
                timer.start();
                this.statement.executeUpdate(query);
                resultSet = this.statement.getResultSet();
                timer.stop();
                Profiler.getInstance().executed(this, timer.duration());
                return (resultSet == null) ? null : new ResultSet(resultSet);
            }
            catch (SQLException se) {
                Statement.log.error(query, se);
                this.evaluate(se, --retry);
            }
            catch (Exception e) {
                Statement.log.error(query, e);
                throw new DbException(e);
            }
        }
        return null;
    }
    
    @Override
    public void close() {
        try {
            this.statement.close();
        }
        catch (Exception e) {
            Statement.log.debug(this.statement, e);
        }
    }
    
    @Override
    public int getWarnings() {
        int result = 0;
        try {
            final SQLWarning warnings = this.statement.getConnection().getWarnings();
            while (warnings != null) {
                ++result;
                Statement.log.warn(warnings.getMessage());
            }
        }
        catch (Exception e) {
            Statement.log.error("warnings", e);
        }
        return result;
    }
    
    @Override
    public void set(final Mode mode) {
    }
    
    @Override
    public void setTimeout(final int value) {
        throw new UnsupportedOperationException();
    }
    
    private void evaluate(final SQLException e, final int retry) throws DbException {
        switch (e.getErrorCode()) {
            case 1062: {
                throw new DuplicateKeyException(e);
            }
            case 1205: {
                if (retry > 0) {
                    break;
                }
                Statement.log.error("** Retries exhausted **");
                throw new LockingException(LockingException.Type.Timeout, e);
            }
            case 1213: {
                if (retry > 0) {
                    break;
                }
                Statement.log.error("** Retries exhausted **");
                throw new LockingException(LockingException.Type.Deadlock, e);
            }
            default: {
                throw new DbException(e, e.getErrorCode());
            }
        }
    }
}
