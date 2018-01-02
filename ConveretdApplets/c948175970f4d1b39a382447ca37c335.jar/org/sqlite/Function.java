// 
// Decompiled by Procyon v0.5.30
// 

package org.sqlite;

import java.sql.SQLException;
import java.sql.Connection;

public abstract class Function
{
    private Conn conn;
    private DB db;
    long context;
    long value;
    int args;
    
    public Function() {
        this.context = 0L;
        this.value = 0L;
        this.args = 0;
    }
    
    public static final void create(final Connection connection, final String s, final Function function) throws SQLException {
        if (connection == null || !(connection instanceof Conn)) {
            throw new SQLException("connection must be to an SQLite db");
        }
        if (connection.isClosed()) {
            throw new SQLException("connection closed");
        }
        function.conn = (Conn)connection;
        function.db = function.conn.db();
        if (s == null || s.length() > 255) {
            throw new SQLException("invalid function name: '" + s + "'");
        }
        if (function.db.create_function(s, function) != 0) {
            throw new SQLException("error creating function");
        }
    }
    
    public static final void destroy(final Connection connection, final String s) throws SQLException {
        if (connection == null || !(connection instanceof Conn)) {
            throw new SQLException("connection must be to an SQLite db");
        }
        ((Conn)connection).db().destroy_function(s);
    }
    
    protected abstract void xFunc() throws SQLException;
    
    protected final synchronized int args() throws SQLException {
        this.checkContext();
        return this.args;
    }
    
    protected final synchronized void result(final byte[] array) throws SQLException {
        this.checkContext();
        this.db.result_blob(this.context, array);
    }
    
    protected final synchronized void result(final double n) throws SQLException {
        this.checkContext();
        this.db.result_double(this.context, n);
    }
    
    protected final synchronized void result(final int n) throws SQLException {
        this.checkContext();
        this.db.result_int(this.context, n);
    }
    
    protected final synchronized void result(final long n) throws SQLException {
        this.checkContext();
        this.db.result_long(this.context, n);
    }
    
    protected final synchronized void result() throws SQLException {
        this.checkContext();
        this.db.result_null(this.context);
    }
    
    protected final synchronized void result(final String s) throws SQLException {
        this.checkContext();
        this.db.result_text(this.context, s);
    }
    
    protected final synchronized void error(final String s) throws SQLException {
        this.checkContext();
        this.db.result_error(this.context, s);
    }
    
    protected final synchronized int value_bytes(final int n) throws SQLException {
        this.checkValue(n);
        return this.db.value_bytes(this, n);
    }
    
    protected final synchronized String value_text(final int n) throws SQLException {
        this.checkValue(n);
        return this.db.value_text(this, n);
    }
    
    protected final synchronized byte[] value_blob(final int n) throws SQLException {
        this.checkValue(n);
        return this.db.value_blob(this, n);
    }
    
    protected final synchronized double value_double(final int n) throws SQLException {
        this.checkValue(n);
        return this.db.value_double(this, n);
    }
    
    protected final synchronized int value_int(final int n) throws SQLException {
        this.checkValue(n);
        return this.db.value_int(this, n);
    }
    
    protected final synchronized long value_long(final int n) throws SQLException {
        this.checkValue(n);
        return this.db.value_long(this, n);
    }
    
    protected final synchronized int value_type(final int n) throws SQLException {
        this.checkValue(n);
        return this.db.value_type(this, n);
    }
    
    private void checkContext() throws SQLException {
        if (this.conn == null || this.conn.db() == null || this.context == 0L) {
            throw new SQLException("no context, not allowed to read value");
        }
    }
    
    private void checkValue(final int n) throws SQLException {
        if (this.conn == null || this.conn.db() == null || this.value == 0L) {
            throw new SQLException("not in value access state");
        }
        if (n >= this.args) {
            throw new SQLException("arg " + n + " out bounds [0," + this.args + ")");
        }
    }
    
    public abstract static class Aggregate extends Function implements Cloneable
    {
        protected final void xFunc() {
        }
        
        protected abstract void xStep() throws SQLException;
        
        protected abstract void xFinal() throws SQLException;
        
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
