// 
// Decompiled by Procyon v0.5.30
// 

package org.sqlite;

import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.Calendar;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;

final class RS extends Unused implements ResultSet, ResultSetMetaData, Codes
{
    private final Stmt stmt;
    private final DB db;
    boolean open;
    int maxRows;
    String[] cols;
    String[] colsMeta;
    boolean[][] meta;
    private int limitRows;
    private int row;
    private int lastCol;
    
    RS(final Stmt stmt) {
        this.open = false;
        this.cols = null;
        this.colsMeta = null;
        this.meta = null;
        this.row = 1;
        this.stmt = stmt;
        this.db = stmt.db;
    }
    
    boolean isOpen() {
        return this.open;
    }
    
    void checkOpen() throws SQLException {
        if (!this.open) {
            throw new SQLException("ResultSet closed");
        }
    }
    
    private int checkCol(int n) throws SQLException {
        if (this.colsMeta == null) {
            throw new IllegalStateException("SQLite JDBC: inconsistent internal state");
        }
        if (n < 1 || n > this.colsMeta.length) {
            throw new SQLException("column " + n + " out of bounds [1," + this.colsMeta.length + "]");
        }
        return --n;
    }
    
    private int markCol(int lastCol) throws SQLException {
        this.checkOpen();
        this.checkCol(lastCol);
        this.lastCol = lastCol;
        return --lastCol;
    }
    
    private void checkMeta() throws SQLException {
        this.checkCol(1);
        if (this.meta == null) {
            this.meta = this.db.column_metadata(this.stmt.pointer);
        }
    }
    
    public boolean isClosed() throws SQLException {
        return !this.open;
    }
    
    public void close() throws SQLException {
        this.cols = null;
        this.colsMeta = null;
        this.meta = null;
        this.open = false;
        this.limitRows = 0;
        this.row = 1;
        this.lastCol = -1;
        if (this.stmt == null) {
            return;
        }
        if (this.stmt != null && this.stmt.pointer != 0L) {
            this.db.reset(this.stmt.pointer);
        }
    }
    
    public int findColumn(final String s) throws SQLException {
        this.checkOpen();
        int n = -1;
        for (int i = 0; i < this.cols.length; ++i) {
            if (s.equalsIgnoreCase(this.cols[i]) || (this.cols[i].toUpperCase().endsWith(s.toUpperCase()) && this.cols[i].charAt(this.cols[i].length() - s.length()) == '.')) {
                if (n != -1) {
                    throw new SQLException("ambiguous column: '" + s + "'");
                }
                n = i;
            }
        }
        if (n == -1) {
            throw new SQLException("no such column: '" + s + "'");
        }
        return n + 1;
    }
    
    public boolean next() throws SQLException {
        if (!this.open) {
            return false;
        }
        this.lastCol = -1;
        if (this.row == 1) {
            ++this.row;
            return true;
        }
        if (this.maxRows != 0 && this.row > this.maxRows) {
            return false;
        }
        if (this.limitRows != 0 && this.row >= this.limitRows) {
            return false;
        }
        switch (this.db.step(this.stmt.pointer)) {
            case 101: {
                this.close();
                return false;
            }
            case 100: {
                ++this.row;
                return true;
            }
            case 5: {
                throw new SQLException("database locked");
            }
            default: {
                this.db.throwex();
                return false;
            }
        }
    }
    
    public int getType() throws SQLException {
        return 1003;
    }
    
    public int getFetchSize() throws SQLException {
        return this.limitRows;
    }
    
    public void setFetchSize(final int limitRows) throws SQLException {
        if (0 > limitRows || (this.maxRows != 0 && limitRows > this.maxRows)) {
            throw new SQLException("fetch size " + limitRows + " out of bounds " + this.maxRows);
        }
        this.limitRows = limitRows;
    }
    
    public int getFetchDirection() throws SQLException {
        this.checkOpen();
        return 1000;
    }
    
    public void setFetchDirection(final int n) throws SQLException {
        this.checkOpen();
        if (n != 1000) {
            throw new SQLException("only FETCH_FORWARD direction supported");
        }
    }
    
    public boolean isAfterLast() throws SQLException {
        return !this.open;
    }
    
    public boolean isBeforeFirst() throws SQLException {
        return this.open && this.row == 1;
    }
    
    public boolean isFirst() throws SQLException {
        return this.row == 2;
    }
    
    public boolean isLast() throws SQLException {
        throw new SQLException("function not yet implemented for SQLite");
    }
    
    protected void finalize() throws SQLException {
        this.close();
    }
    
    public int getRow() throws SQLException {
        return this.row;
    }
    
    public boolean wasNull() throws SQLException {
        return this.db.column_type(this.stmt.pointer, this.markCol(this.lastCol)) == 5;
    }
    
    public boolean getBoolean(final int n) throws SQLException {
        return this.getInt(n) != 0;
    }
    
    public boolean getBoolean(final String s) throws SQLException {
        return this.getBoolean(this.findColumn(s));
    }
    
    public byte getByte(final int n) throws SQLException {
        return (byte)this.getInt(n);
    }
    
    public byte getByte(final String s) throws SQLException {
        return this.getByte(this.findColumn(s));
    }
    
    public byte[] getBytes(final int n) throws SQLException {
        return this.db.column_blob(this.stmt.pointer, this.markCol(n));
    }
    
    public byte[] getBytes(final String s) throws SQLException {
        return this.getBytes(this.findColumn(s));
    }
    
    public Date getDate(final int n) throws SQLException {
        if (this.db.column_type(this.stmt.pointer, this.markCol(n)) == 5) {
            return null;
        }
        return new Date(this.db.column_long(this.stmt.pointer, this.markCol(n)));
    }
    
    public Date getDate(final int n, final Calendar calendar) throws SQLException {
        if (this.db.column_type(this.stmt.pointer, this.markCol(n)) == 5) {
            return null;
        }
        if (calendar == null) {
            return this.getDate(n);
        }
        calendar.setTimeInMillis(this.db.column_long(this.stmt.pointer, this.markCol(n)));
        return new Date(calendar.getTime().getTime());
    }
    
    public Date getDate(final String s) throws SQLException {
        return this.getDate(this.findColumn(s), Calendar.getInstance());
    }
    
    public Date getDate(final String s, final Calendar calendar) throws SQLException {
        return this.getDate(this.findColumn(s), calendar);
    }
    
    public double getDouble(final int n) throws SQLException {
        if (this.db.column_type(this.stmt.pointer, this.markCol(n)) == 5) {
            return 0.0;
        }
        return this.db.column_double(this.stmt.pointer, this.markCol(n));
    }
    
    public double getDouble(final String s) throws SQLException {
        return this.getDouble(this.findColumn(s));
    }
    
    public float getFloat(final int n) throws SQLException {
        if (this.db.column_type(this.stmt.pointer, this.markCol(n)) == 5) {
            return 0.0f;
        }
        return (float)this.db.column_double(this.stmt.pointer, this.markCol(n));
    }
    
    public float getFloat(final String s) throws SQLException {
        return this.getFloat(this.findColumn(s));
    }
    
    public int getInt(final int n) throws SQLException {
        return this.db.column_int(this.stmt.pointer, this.markCol(n));
    }
    
    public int getInt(final String s) throws SQLException {
        return this.getInt(this.findColumn(s));
    }
    
    public long getLong(final int n) throws SQLException {
        return this.db.column_long(this.stmt.pointer, this.markCol(n));
    }
    
    public long getLong(final String s) throws SQLException {
        return this.getLong(this.findColumn(s));
    }
    
    public short getShort(final int n) throws SQLException {
        return (short)this.getInt(n);
    }
    
    public short getShort(final String s) throws SQLException {
        return this.getShort(this.findColumn(s));
    }
    
    public String getString(final int n) throws SQLException {
        return this.db.column_text(this.stmt.pointer, this.markCol(n));
    }
    
    public String getString(final String s) throws SQLException {
        return this.getString(this.findColumn(s));
    }
    
    public Time getTime(final int n) throws SQLException {
        if (this.db.column_type(this.stmt.pointer, this.markCol(n)) == 5) {
            return null;
        }
        return new Time(this.db.column_long(this.stmt.pointer, this.markCol(n)));
    }
    
    public Time getTime(final int n, final Calendar calendar) throws SQLException {
        if (calendar == null) {
            return this.getTime(n);
        }
        if (this.db.column_type(this.stmt.pointer, this.markCol(n)) == 5) {
            return null;
        }
        calendar.setTimeInMillis(this.db.column_long(this.stmt.pointer, this.markCol(n)));
        return new Time(calendar.getTime().getTime());
    }
    
    public Time getTime(final String s) throws SQLException {
        return this.getTime(this.findColumn(s));
    }
    
    public Time getTime(final String s, final Calendar calendar) throws SQLException {
        return this.getTime(this.findColumn(s), calendar);
    }
    
    public Timestamp getTimestamp(final int n) throws SQLException {
        if (this.db.column_type(this.stmt.pointer, this.markCol(n)) == 5) {
            return null;
        }
        return new Timestamp(this.db.column_long(this.stmt.pointer, this.markCol(n)));
    }
    
    public Timestamp getTimestamp(final int n, final Calendar calendar) throws SQLException {
        if (calendar == null) {
            return this.getTimestamp(n);
        }
        if (this.db.column_type(this.stmt.pointer, this.markCol(n)) == 5) {
            return null;
        }
        calendar.setTimeInMillis(this.db.column_long(this.stmt.pointer, this.markCol(n)));
        return new Timestamp(calendar.getTime().getTime());
    }
    
    public Timestamp getTimestamp(final String s) throws SQLException {
        return this.getTimestamp(this.findColumn(s));
    }
    
    public Timestamp getTimestamp(final String s, final Calendar calendar) throws SQLException {
        return this.getTimestamp(this.findColumn(s), calendar);
    }
    
    public Object getObject(final int n) throws SQLException {
        switch (this.db.column_type(this.stmt.pointer, this.checkCol(n))) {
            case 1: {
                final long long1 = this.getLong(n);
                if (long1 > 2147483647L || long1 < -2147483648L) {
                    return new Long(long1);
                }
                return new Integer((int)long1);
            }
            case 2: {
                return new Double(this.getDouble(n));
            }
            case 4: {
                return this.getBytes(n);
            }
            case 5: {
                return null;
            }
            default: {
                return this.getString(n);
            }
        }
    }
    
    public Object getObject(final String s) throws SQLException {
        return this.getObject(this.findColumn(s));
    }
    
    public Statement getStatement() {
        return this.stmt;
    }
    
    public String getCursorName() throws SQLException {
        return null;
    }
    
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }
    
    public void clearWarnings() throws SQLException {
    }
    
    public ResultSetMetaData getMetaData() throws SQLException {
        return this;
    }
    
    public String getCatalogName(final int n) throws SQLException {
        return this.db.column_table_name(this.stmt.pointer, this.checkCol(n));
    }
    
    public String getColumnClassName(final int n) throws SQLException {
        this.checkCol(n);
        return "java.lang.Object";
    }
    
    public int getColumnCount() throws SQLException {
        this.checkCol(1);
        return this.colsMeta.length;
    }
    
    public int getColumnDisplaySize(final int n) throws SQLException {
        return Integer.MAX_VALUE;
    }
    
    public String getColumnLabel(final int n) throws SQLException {
        return this.getColumnName(n);
    }
    
    public String getColumnName(final int n) throws SQLException {
        return this.db.column_name(this.stmt.pointer, this.checkCol(n));
    }
    
    public int getColumnType(final int n) throws SQLException {
        switch (this.db.column_type(this.stmt.pointer, this.checkCol(n))) {
            case 1: {
                return 4;
            }
            case 2: {
                return 6;
            }
            case 4: {
                return 2004;
            }
            case 5: {
                return 0;
            }
            default: {
                return 12;
            }
        }
    }
    
    public String getColumnTypeName(final int n) throws SQLException {
        switch (this.db.column_type(this.stmt.pointer, this.checkCol(n))) {
            case 1: {
                return "integer";
            }
            case 2: {
                return "float";
            }
            case 4: {
                return "blob";
            }
            case 5: {
                return "null";
            }
            default: {
                return "text";
            }
        }
    }
    
    public int getPrecision(final int n) throws SQLException {
        return 0;
    }
    
    public int getScale(final int n) throws SQLException {
        return 0;
    }
    
    public String getSchemaName(final int n) throws SQLException {
        return "";
    }
    
    public String getTableName(final int n) throws SQLException {
        return this.db.column_table_name(this.stmt.pointer, this.checkCol(n));
    }
    
    public int isNullable(final int n) throws SQLException {
        this.checkMeta();
        return this.meta[this.checkCol(n)][1] ? 0 : 1;
    }
    
    public boolean isAutoIncrement(final int n) throws SQLException {
        this.checkMeta();
        return this.meta[this.checkCol(n)][2];
    }
    
    public boolean isCaseSensitive(final int n) throws SQLException {
        return true;
    }
    
    public boolean isCurrency(final int n) throws SQLException {
        return false;
    }
    
    public boolean isDefinitelyWritable(final int n) throws SQLException {
        return true;
    }
    
    public boolean isReadOnly(final int n) throws SQLException {
        return false;
    }
    
    public boolean isSearchable(final int n) throws SQLException {
        return true;
    }
    
    public boolean isSigned(final int n) throws SQLException {
        return false;
    }
    
    public boolean isWritable(final int n) throws SQLException {
        return true;
    }
    
    public int getConcurrency() throws SQLException {
        return 1007;
    }
    
    public boolean rowDeleted() throws SQLException {
        return false;
    }
    
    public boolean rowInserted() throws SQLException {
        return false;
    }
    
    public boolean rowUpdated() throws SQLException {
        return false;
    }
}
