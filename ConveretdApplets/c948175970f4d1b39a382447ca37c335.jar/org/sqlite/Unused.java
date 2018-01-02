// 
// Decompiled by Procyon v0.5.30
// 

package org.sqlite;

import java.sql.Timestamp;
import java.sql.Time;
import java.sql.Date;
import java.util.Map;
import java.net.URL;
import java.sql.Ref;
import java.sql.Clob;
import java.io.Reader;
import java.sql.Blob;
import java.math.BigDecimal;
import java.io.InputStream;
import java.sql.Array;
import java.sql.SQLException;

abstract class Unused
{
    private SQLException unused() {
        return new SQLException("not implemented by SQLite JDBC driver");
    }
    
    public boolean execute(final String s, final int[] array) throws SQLException {
        throw this.unused();
    }
    
    public boolean execute(final String s, final String[] array) throws SQLException {
        throw this.unused();
    }
    
    public int executeUpdate(final String s, final int n) throws SQLException {
        throw this.unused();
    }
    
    public int executeUpdate(final String s, final int[] array) throws SQLException {
        throw this.unused();
    }
    
    public int executeUpdate(final String s, final String[] array) throws SQLException {
        throw this.unused();
    }
    
    public boolean execute(final String s, final int n) throws SQLException {
        throw this.unused();
    }
    
    public void setArray(final int n, final Array array) throws SQLException {
        throw this.unused();
    }
    
    public void setAsciiStream(final int n, final InputStream inputStream, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public void setBigDecimal(final int n, final BigDecimal bigDecimal) throws SQLException {
        throw this.unused();
    }
    
    public void setBinaryStream(final int n, final InputStream inputStream, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public void setBlob(final int n, final Blob blob) throws SQLException {
        throw this.unused();
    }
    
    public void setCharacterStream(final int n, final Reader reader, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public void setClob(final int n, final Clob clob) throws SQLException {
        throw this.unused();
    }
    
    public void setRef(final int n, final Ref ref) throws SQLException {
        throw this.unused();
    }
    
    public void setUnicodeStream(final int n, final InputStream inputStream, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public void setURL(final int n, final URL url) throws SQLException {
        throw this.unused();
    }
    
    public Array getArray(final int n) throws SQLException {
        throw this.unused();
    }
    
    public Array getArray(final String s) throws SQLException {
        throw this.unused();
    }
    
    public InputStream getAsciiStream(final int n) throws SQLException {
        throw this.unused();
    }
    
    public InputStream getAsciiStream(final String s) throws SQLException {
        throw this.unused();
    }
    
    public BigDecimal getBigDecimal(final int n) throws SQLException {
        throw this.unused();
    }
    
    public BigDecimal getBigDecimal(final int n, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public BigDecimal getBigDecimal(final String s) throws SQLException {
        throw this.unused();
    }
    
    public BigDecimal getBigDecimal(final String s, final int n) throws SQLException {
        throw this.unused();
    }
    
    public InputStream getBinaryStream(final int n) throws SQLException {
        throw this.unused();
    }
    
    public InputStream getBinaryStream(final String s) throws SQLException {
        throw this.unused();
    }
    
    public Blob getBlob(final int n) throws SQLException {
        throw this.unused();
    }
    
    public Blob getBlob(final String s) throws SQLException {
        throw this.unused();
    }
    
    public Reader getCharacterStream(final int n) throws SQLException {
        throw this.unused();
    }
    
    public Reader getCharacterStream(final String s) throws SQLException {
        throw this.unused();
    }
    
    public Clob getClob(final int n) throws SQLException {
        throw this.unused();
    }
    
    public Clob getClob(final String s) throws SQLException {
        throw this.unused();
    }
    
    public Object getObject(final int n, final Map map) throws SQLException {
        throw this.unused();
    }
    
    public Object getObject(final String s, final Map map) throws SQLException {
        throw this.unused();
    }
    
    public Ref getRef(final int n) throws SQLException {
        throw this.unused();
    }
    
    public Ref getRef(final String s) throws SQLException {
        throw this.unused();
    }
    
    public InputStream getUnicodeStream(final int n) throws SQLException {
        throw this.unused();
    }
    
    public InputStream getUnicodeStream(final String s) throws SQLException {
        throw this.unused();
    }
    
    public URL getURL(final int n) throws SQLException {
        throw this.unused();
    }
    
    public URL getURL(final String s) throws SQLException {
        throw this.unused();
    }
    
    public void insertRow() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public void moveToCurrentRow() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public void moveToInsertRow() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public boolean last() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public boolean previous() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public boolean relative(final int n) throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public boolean absolute(final int n) throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public void afterLast() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public void beforeFirst() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public boolean first() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY");
    }
    
    public void cancelRowUpdates() throws SQLException {
        throw this.unused();
    }
    
    public void deleteRow() throws SQLException {
        throw this.unused();
    }
    
    public void updateArray(final int n, final Array array) throws SQLException {
        throw this.unused();
    }
    
    public void updateArray(final String s, final Array array) throws SQLException {
        throw this.unused();
    }
    
    public void updateAsciiStream(final int n, final InputStream inputStream, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public void updateAsciiStream(final String s, final InputStream inputStream, final int n) throws SQLException {
        throw this.unused();
    }
    
    public void updateBigDecimal(final int n, final BigDecimal bigDecimal) throws SQLException {
        throw this.unused();
    }
    
    public void updateBigDecimal(final String s, final BigDecimal bigDecimal) throws SQLException {
        throw this.unused();
    }
    
    public void updateBinaryStream(final int n, final InputStream inputStream, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public void updateBinaryStream(final String s, final InputStream inputStream, final int n) throws SQLException {
        throw this.unused();
    }
    
    public void updateBlob(final int n, final Blob blob) throws SQLException {
        throw this.unused();
    }
    
    public void updateBlob(final String s, final Blob blob) throws SQLException {
        throw this.unused();
    }
    
    public void updateBoolean(final int n, final boolean b) throws SQLException {
        throw this.unused();
    }
    
    public void updateBoolean(final String s, final boolean b) throws SQLException {
        throw this.unused();
    }
    
    public void updateByte(final int n, final byte b) throws SQLException {
        throw this.unused();
    }
    
    public void updateByte(final String s, final byte b) throws SQLException {
        throw this.unused();
    }
    
    public void updateBytes(final int n, final byte[] array) throws SQLException {
        throw this.unused();
    }
    
    public void updateBytes(final String s, final byte[] array) throws SQLException {
        throw this.unused();
    }
    
    public void updateCharacterStream(final int n, final Reader reader, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public void updateCharacterStream(final String s, final Reader reader, final int n) throws SQLException {
        throw this.unused();
    }
    
    public void updateClob(final int n, final Clob clob) throws SQLException {
        throw this.unused();
    }
    
    public void updateClob(final String s, final Clob clob) throws SQLException {
        throw this.unused();
    }
    
    public void updateDate(final int n, final Date date) throws SQLException {
        throw this.unused();
    }
    
    public void updateDate(final String s, final Date date) throws SQLException {
        throw this.unused();
    }
    
    public void updateDouble(final int n, final double n2) throws SQLException {
        throw this.unused();
    }
    
    public void updateDouble(final String s, final double n) throws SQLException {
        throw this.unused();
    }
    
    public void updateFloat(final int n, final float n2) throws SQLException {
        throw this.unused();
    }
    
    public void updateFloat(final String s, final float n) throws SQLException {
        throw this.unused();
    }
    
    public void updateInt(final int n, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public void updateInt(final String s, final int n) throws SQLException {
        throw this.unused();
    }
    
    public void updateLong(final int n, final long n2) throws SQLException {
        throw this.unused();
    }
    
    public void updateLong(final String s, final long n) throws SQLException {
        throw this.unused();
    }
    
    public void updateNull(final int n) throws SQLException {
        throw this.unused();
    }
    
    public void updateNull(final String s) throws SQLException {
        throw this.unused();
    }
    
    public void updateObject(final int n, final Object o) throws SQLException {
        throw this.unused();
    }
    
    public void updateObject(final int n, final Object o, final int n2) throws SQLException {
        throw this.unused();
    }
    
    public void updateObject(final String s, final Object o) throws SQLException {
        throw this.unused();
    }
    
    public void updateObject(final String s, final Object o, final int n) throws SQLException {
        throw this.unused();
    }
    
    public void updateRef(final int n, final Ref ref) throws SQLException {
        throw this.unused();
    }
    
    public void updateRef(final String s, final Ref ref) throws SQLException {
        throw this.unused();
    }
    
    public void updateRow() throws SQLException {
        throw this.unused();
    }
    
    public void updateShort(final int n, final short n2) throws SQLException {
        throw this.unused();
    }
    
    public void updateShort(final String s, final short n) throws SQLException {
        throw this.unused();
    }
    
    public void updateString(final int n, final String s) throws SQLException {
        throw this.unused();
    }
    
    public void updateString(final String s, final String s2) throws SQLException {
        throw this.unused();
    }
    
    public void updateTime(final int n, final Time time) throws SQLException {
        throw this.unused();
    }
    
    public void updateTime(final String s, final Time time) throws SQLException {
        throw this.unused();
    }
    
    public void updateTimestamp(final int n, final Timestamp timestamp) throws SQLException {
        throw this.unused();
    }
    
    public void updateTimestamp(final String s, final Timestamp timestamp) throws SQLException {
        throw this.unused();
    }
    
    public void refreshRow() throws SQLException {
        throw this.unused();
    }
}
