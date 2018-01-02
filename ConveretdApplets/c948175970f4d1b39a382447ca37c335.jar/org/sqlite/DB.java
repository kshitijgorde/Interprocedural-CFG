// 
// Decompiled by Procyon v0.5.30
// 

package org.sqlite;

import java.sql.BatchUpdateException;
import java.util.Iterator;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

abstract class DB implements Codes
{
    Conn conn;
    long begin;
    long commit;
    private Map stmts;
    
    DB() {
        this.conn = null;
        this.begin = 0L;
        this.commit = 0L;
        this.stmts = new Hashtable();
    }
    
    abstract void interrupt() throws SQLException;
    
    abstract void busy_timeout(final int p0) throws SQLException;
    
    abstract String errmsg() throws SQLException;
    
    abstract String libversion() throws SQLException;
    
    abstract int changes() throws SQLException;
    
    abstract int shared_cache(final boolean p0) throws SQLException;
    
    final synchronized void exec(final String s) throws SQLException {
        long prepare = 0L;
        try {
            prepare = this.prepare(s);
            switch (this.step(prepare)) {
                case 101: {
                    this.ensureAutoCommit();
                }
                case 100: {}
                default: {
                    this.throwex();
                    break;
                }
            }
        }
        finally {
            this.finalize(prepare);
        }
    }
    
    final synchronized void open(final Conn conn, final String s) throws SQLException {
        this.conn = conn;
        this._open(s);
    }
    
    final synchronized void close() throws SQLException {
        synchronized (this.stmts) {
            final Iterator<Map.Entry<K, Stmt>> iterator = this.stmts.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<K, Stmt> entry = iterator.next();
                final Stmt stmt = entry.getValue();
                this.finalize(entry.getKey());
                if (stmt != null) {
                    stmt.pointer = 0L;
                }
                iterator.remove();
            }
        }
        this.free_functions();
        if (this.begin != 0L) {
            this.finalize(this.begin);
            this.begin = 0L;
        }
        if (this.commit != 0L) {
            this.finalize(this.commit);
            this.commit = 0L;
        }
        this._close();
    }
    
    final synchronized void prepare(final Stmt stmt) throws SQLException {
        if (stmt.pointer != 0L) {
            this.finalize(stmt);
        }
        stmt.pointer = this.prepare(stmt.sql);
        this.stmts.put(new Long(stmt.pointer), stmt);
    }
    
    final synchronized int finalize(final Stmt stmt) throws SQLException {
        if (stmt.pointer == 0L) {
            return 0;
        }
        int finalize = 1;
        try {
            finalize = this.finalize(stmt.pointer);
        }
        finally {
            this.stmts.remove(new Long(stmt.pointer));
            stmt.pointer = 0L;
        }
        return finalize;
    }
    
    protected abstract void _open(final String p0) throws SQLException;
    
    protected abstract void _close() throws SQLException;
    
    protected abstract long prepare(final String p0) throws SQLException;
    
    protected abstract int finalize(final long p0) throws SQLException;
    
    protected abstract int step(final long p0) throws SQLException;
    
    protected abstract int reset(final long p0) throws SQLException;
    
    abstract int clear_bindings(final long p0) throws SQLException;
    
    abstract int bind_parameter_count(final long p0) throws SQLException;
    
    abstract int column_count(final long p0) throws SQLException;
    
    abstract int column_type(final long p0, final int p1) throws SQLException;
    
    abstract String column_decltype(final long p0, final int p1) throws SQLException;
    
    abstract String column_table_name(final long p0, final int p1) throws SQLException;
    
    abstract String column_name(final long p0, final int p1) throws SQLException;
    
    abstract String column_text(final long p0, final int p1) throws SQLException;
    
    abstract byte[] column_blob(final long p0, final int p1) throws SQLException;
    
    abstract double column_double(final long p0, final int p1) throws SQLException;
    
    abstract long column_long(final long p0, final int p1) throws SQLException;
    
    abstract int column_int(final long p0, final int p1) throws SQLException;
    
    abstract int bind_null(final long p0, final int p1) throws SQLException;
    
    abstract int bind_int(final long p0, final int p1, final int p2) throws SQLException;
    
    abstract int bind_long(final long p0, final int p1, final long p2) throws SQLException;
    
    abstract int bind_double(final long p0, final int p1, final double p2) throws SQLException;
    
    abstract int bind_text(final long p0, final int p1, final String p2) throws SQLException;
    
    abstract int bind_blob(final long p0, final int p1, final byte[] p2) throws SQLException;
    
    abstract void result_null(final long p0) throws SQLException;
    
    abstract void result_text(final long p0, final String p1) throws SQLException;
    
    abstract void result_blob(final long p0, final byte[] p1) throws SQLException;
    
    abstract void result_double(final long p0, final double p1) throws SQLException;
    
    abstract void result_long(final long p0, final long p1) throws SQLException;
    
    abstract void result_int(final long p0, final int p1) throws SQLException;
    
    abstract void result_error(final long p0, final String p1) throws SQLException;
    
    abstract int value_bytes(final Function p0, final int p1) throws SQLException;
    
    abstract String value_text(final Function p0, final int p1) throws SQLException;
    
    abstract byte[] value_blob(final Function p0, final int p1) throws SQLException;
    
    abstract double value_double(final Function p0, final int p1) throws SQLException;
    
    abstract long value_long(final Function p0, final int p1) throws SQLException;
    
    abstract int value_int(final Function p0, final int p1) throws SQLException;
    
    abstract int value_type(final Function p0, final int p1) throws SQLException;
    
    abstract int create_function(final String p0, final Function p1) throws SQLException;
    
    abstract int destroy_function(final String p0) throws SQLException;
    
    abstract void free_functions() throws SQLException;
    
    abstract boolean[][] column_metadata(final long p0) throws SQLException;
    
    final synchronized String[] column_names(final long n) throws SQLException {
        final String[] array = new String[this.column_count(n)];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.column_name(n, i);
        }
        return array;
    }
    
    final synchronized int sqlbind(final long n, int n2, final Object o) throws SQLException {
        ++n2;
        if (o == null) {
            return this.bind_null(n, n2);
        }
        if (o instanceof Integer) {
            return this.bind_int(n, n2, (int)o);
        }
        if (o instanceof Long) {
            return this.bind_long(n, n2, (long)o);
        }
        if (o instanceof Double) {
            return this.bind_double(n, n2, (double)o);
        }
        if (o instanceof String) {
            return this.bind_text(n, n2, (String)o);
        }
        if (o instanceof byte[]) {
            return this.bind_blob(n, n2, (byte[])o);
        }
        throw new SQLException("unexpected param type: " + o.getClass());
    }
    
    final synchronized int[] executeBatch(final long n, final int n2, final Object[] array) throws SQLException {
        if (n2 < 1) {
            throw new SQLException("count (" + n2 + ") < 1");
        }
        final int bind_parameter_count = this.bind_parameter_count(n);
        final int[] array2 = new int[n2];
        try {
            for (int i = 0; i < n2; ++i) {
                this.reset(n);
                for (int j = 0; j < bind_parameter_count; ++j) {
                    if (this.sqlbind(n, j, array[i * bind_parameter_count + j]) != 0) {
                        this.throwex();
                    }
                }
                final int step = this.step(n);
                if (step != 101) {
                    this.reset(n);
                    if (step == 100) {
                        throw new BatchUpdateException("batch entry " + i + ": query returns results", array2);
                    }
                    this.throwex();
                }
                array2[i] = this.changes();
            }
        }
        finally {
            this.ensureAutoCommit();
        }
        this.reset(n);
        return array2;
    }
    
    final synchronized boolean execute(final Stmt stmt, final Object[] array) throws SQLException {
        if (array != null) {
            final int bind_parameter_count = this.bind_parameter_count(stmt.pointer);
            if (bind_parameter_count != array.length) {
                throw new SQLException("assertion failure: param count (" + bind_parameter_count + ") != value count (" + array.length + ")");
            }
            for (int i = 0; i < bind_parameter_count; ++i) {
                if (this.sqlbind(stmt.pointer, i, array[i]) != 0) {
                    this.throwex();
                }
            }
        }
        switch (this.step(stmt.pointer)) {
            case 101: {
                this.reset(stmt.pointer);
                this.ensureAutoCommit();
                return false;
            }
            case 100: {
                return true;
            }
            case 5:
            case 6: {
                throw new SQLException("database locked");
            }
            case 21: {
                throw new SQLException(this.errmsg());
            }
            default: {
                this.finalize(stmt);
                throw new SQLException(this.errmsg());
            }
        }
    }
    
    final synchronized int executeUpdate(final Stmt stmt, final Object[] array) throws SQLException {
        if (this.execute(stmt, array)) {
            throw new SQLException("query returns results");
        }
        this.reset(stmt.pointer);
        return this.changes();
    }
    
    final void throwex() throws SQLException {
        throw new SQLException(this.errmsg());
    }
    
    final void ensureAutoCommit() throws SQLException {
    }
}
