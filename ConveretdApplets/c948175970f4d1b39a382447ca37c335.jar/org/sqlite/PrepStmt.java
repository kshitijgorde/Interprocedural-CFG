// 
// Decompiled by Procyon v0.5.30
// 

package org.sqlite;

import java.sql.ResultSetMetaData;
import java.util.Calendar;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;

final class PrepStmt extends Stmt implements PreparedStatement, ParameterMetaData, Codes
{
    private int columnCount;
    private int paramCount;
    
    PrepStmt(final Conn conn, final String sql) throws SQLException {
        super(conn);
        this.sql = sql;
        this.db.prepare(this);
        this.rs.colsMeta = this.db.column_names(this.pointer);
        this.columnCount = this.db.column_count(this.pointer);
        this.paramCount = this.db.bind_parameter_count(this.pointer);
        this.batch = new Object[this.paramCount];
        this.batchPos = 0;
    }
    
    public void clearParameters() throws SQLException {
        this.checkOpen();
        this.db.reset(this.pointer);
        this.clearBatch();
    }
    
    protected void finalize() throws SQLException {
        this.close();
    }
    
    public boolean execute() throws SQLException {
        this.checkOpen();
        this.rs.close();
        this.db.reset(this.pointer);
        this.resultsWaiting = this.db.execute(this, this.batch);
        return this.columnCount != 0;
    }
    
    public ResultSet executeQuery() throws SQLException {
        this.checkOpen();
        if (this.columnCount == 0) {
            throw new SQLException("query does not return results");
        }
        this.rs.close();
        this.db.reset(this.pointer);
        this.resultsWaiting = this.db.execute(this, this.batch);
        return this.getResultSet();
    }
    
    public int executeUpdate() throws SQLException {
        this.checkOpen();
        if (this.columnCount != 0) {
            throw new SQLException("query returns results");
        }
        this.rs.close();
        this.db.reset(this.pointer);
        return this.db.executeUpdate(this, this.batch);
    }
    
    public int[] executeBatch() throws SQLException {
        if (this.batchPos == 0) {
            return new int[0];
        }
        try {
            return this.db.executeBatch(this.pointer, this.batchPos / this.paramCount, this.batch);
        }
        finally {
            this.clearBatch();
        }
    }
    
    public int getUpdateCount() throws SQLException {
        this.checkOpen();
        if (this.pointer == 0L || this.resultsWaiting) {
            return -1;
        }
        return this.db.changes();
    }
    
    public void addBatch() throws SQLException {
        this.checkOpen();
        this.batchPos += this.paramCount;
        if (this.batchPos + this.paramCount > this.batch.length) {
            final Object[] batch = new Object[this.batch.length * 2];
            System.arraycopy(this.batch, 0, batch, 0, this.batch.length);
            this.batch = batch;
        }
        System.arraycopy(this.batch, this.batchPos - this.paramCount, this.batch, this.batchPos, this.paramCount);
    }
    
    public ParameterMetaData getParameterMetaData() {
        return this;
    }
    
    public int getParameterCount() throws SQLException {
        this.checkOpen();
        return this.paramCount;
    }
    
    public String getParameterClassName(final int n) throws SQLException {
        this.checkOpen();
        return "java.lang.String";
    }
    
    public String getParameterTypeName(final int n) {
        return "VARCHAR";
    }
    
    public int getParameterType(final int n) {
        return 12;
    }
    
    public int getParameterMode(final int n) {
        return 1;
    }
    
    public int getPrecision(final int n) {
        return 0;
    }
    
    public int getScale(final int n) {
        return 0;
    }
    
    public int isNullable(final int n) {
        return 1;
    }
    
    public boolean isSigned(final int n) {
        return true;
    }
    
    public Statement getStatement() {
        return this;
    }
    
    private void batch(final int n, final Object o) throws SQLException {
        this.checkOpen();
        if (this.batch == null) {
            this.batch = new Object[this.paramCount];
        }
        this.batch[this.batchPos + n - 1] = o;
    }
    
    public void setBoolean(final int n, final boolean b) throws SQLException {
        this.setInt(n, b ? 1 : 0);
    }
    
    public void setByte(final int n, final byte b) throws SQLException {
        this.setInt(n, b);
    }
    
    public void setBytes(final int n, final byte[] array) throws SQLException {
        this.batch(n, array);
    }
    
    public void setDouble(final int n, final double n2) throws SQLException {
        this.batch(n, new Double(n2));
    }
    
    public void setFloat(final int n, final float n2) throws SQLException {
        this.setDouble(n, n2);
    }
    
    public void setInt(final int n, final int n2) throws SQLException {
        this.batch(n, new Integer(n2));
    }
    
    public void setLong(final int n, final long n2) throws SQLException {
        this.batch(n, new Long(n2));
    }
    
    public void setNull(final int n, final int n2) throws SQLException {
        this.setNull(n, n2, null);
    }
    
    public void setNull(final int n, final int n2, final String s) throws SQLException {
        this.batch(n, null);
    }
    
    public void setObject(final int n, final Object o) throws SQLException {
        if (o == null) {
            this.batch(n, null);
        }
        else if (o instanceof Date) {
            this.batch(n, new Long(((Date)o).getTime()));
        }
        else if (o instanceof java.sql.Date) {
            this.batch(n, new Long(((java.sql.Date)o).getTime()));
        }
        else if (o instanceof Time) {
            this.batch(n, new Long(((Time)o).getTime()));
        }
        else if (o instanceof Timestamp) {
            this.batch(n, new Long(((Timestamp)o).getTime()));
        }
        else if (o instanceof Long) {
            this.batch(n, o);
        }
        else if (o instanceof Integer) {
            this.batch(n, o);
        }
        else if (o instanceof Float) {
            this.batch(n, o);
        }
        else if (o instanceof Double) {
            this.batch(n, o);
        }
        else {
            this.batch(n, o.toString());
        }
    }
    
    public void setObject(final int n, final Object o, final int n2) throws SQLException {
        this.setObject(n, o);
    }
    
    public void setObject(final int n, final Object o, final int n2, final int n3) throws SQLException {
        this.setObject(n, o);
    }
    
    public void setShort(final int n, final short n2) throws SQLException {
        this.setInt(n, n2);
    }
    
    public void setString(final int n, final String s) throws SQLException {
        this.batch(n, s);
    }
    
    public void setDate(final int n, final java.sql.Date date) throws SQLException {
        this.setObject(n, date);
    }
    
    public void setDate(final int n, final java.sql.Date date, final Calendar calendar) throws SQLException {
        this.setObject(n, date);
    }
    
    public void setTime(final int n, final Time time) throws SQLException {
        this.setObject(n, time);
    }
    
    public void setTime(final int n, final Time time, final Calendar calendar) throws SQLException {
        this.setObject(n, time);
    }
    
    public void setTimestamp(final int n, final Timestamp timestamp) throws SQLException {
        this.setObject(n, timestamp);
    }
    
    public void setTimestamp(final int n, final Timestamp timestamp, final Calendar calendar) throws SQLException {
        this.setObject(n, timestamp);
    }
    
    public ResultSetMetaData getMetaData() throws SQLException {
        this.checkOpen();
        return this.rs;
    }
    
    public boolean execute(final String s) throws SQLException {
        throw this.unused();
    }
    
    public int executeUpdate(final String s) throws SQLException {
        throw this.unused();
    }
    
    public ResultSet executeQuery(final String s) throws SQLException {
        throw this.unused();
    }
    
    public void addBatch(final String s) throws SQLException {
        throw this.unused();
    }
    
    private SQLException unused() {
        return new SQLException("not supported by PreparedStatment");
    }
}
