// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io.sql;

import java.sql.Timestamp;
import java.sql.Time;
import java.sql.Ref;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.sql.ResultSet;
import prefuse.data.Table;

public class DefaultSQLDataHandler implements SQLDataHandler
{
    private boolean m_ignoreUnknownTypes;
    
    public DefaultSQLDataHandler() {
        this(true);
    }
    
    public DefaultSQLDataHandler(final boolean ignoreUnknownTypes) {
        this.m_ignoreUnknownTypes = ignoreUnknownTypes;
    }
    
    public void setIgnoreUnknownTypes(final boolean ignoreUnknownTypes) {
        this.m_ignoreUnknownTypes = ignoreUnknownTypes;
    }
    
    public boolean isIgnoreUnknownTypes() {
        return this.m_ignoreUnknownTypes;
    }
    
    public void process(final Table table, final int n, final ResultSet set, final int n2) throws SQLException {
        final ResultSetMetaData metaData = set.getMetaData();
        final String columnName = metaData.getColumnName(n2);
        switch (metaData.getColumnType(n2)) {
            case 2003: {
                table.set(n, columnName, set.getArray(n2));
                break;
            }
            case -5: {
                table.setLong(n, columnName, set.getLong(n2));
                break;
            }
            case -4:
            case -3:
            case -2: {
                table.set(n, columnName, set.getBytes(n2));
                break;
            }
            case -7:
            case 16: {
                table.setBoolean(n, columnName, set.getBoolean(n2));
                break;
            }
            case 2004: {
                table.set(n, columnName, set.getBlob(n2));
                break;
            }
            case -1:
            case 1:
            case 12: {
                table.setString(n, columnName, set.getString(n2));
                break;
            }
            case 2005: {
                table.set(n, columnName, set.getClob(n2));
                break;
            }
            case 91: {
                table.setDate(n, columnName, set.getDate(n2));
                break;
            }
            case 2:
            case 3:
            case 6:
            case 8: {
                table.setDouble(n, columnName, set.getDouble(n2));
                break;
            }
            case -6:
            case 4:
            case 5: {
                table.setInt(n, columnName, set.getInt(n2));
                break;
            }
            case 2000: {
                table.set(n, columnName, set.getObject(n2));
                break;
            }
            case 7: {
                table.setFloat(n, columnName, set.getFloat(n2));
                break;
            }
            case 2006: {
                table.set(n, columnName, set.getRef(n2));
                break;
            }
            case 92: {
                table.setDate(n, columnName, set.getTime(n2));
                break;
            }
            case 93: {
                table.setDate(n, columnName, set.getTimestamp(n2));
                break;
            }
            default: {
                if (!this.m_ignoreUnknownTypes) {
                    table.set(n, columnName, set.getObject(n2));
                    break;
                }
                break;
            }
        }
    }
    
    public Class getDataType(final String s, final int n) {
        switch (n) {
            case 2003: {
                return Array.class;
            }
            case -5: {
                return Long.TYPE;
            }
            case -4:
            case -3:
            case -2: {
                return byte[].class;
            }
            case -7:
            case 16: {
                return Boolean.TYPE;
            }
            case 2004: {
                return Blob.class;
            }
            case -1:
            case 1:
            case 12: {
                return String.class;
            }
            case 2005: {
                return Clob.class;
            }
            case 91: {
                return java.sql.Date.class;
            }
            case 2:
            case 3: {
                return BigDecimal.class;
            }
            case 6:
            case 8: {
                return Double.TYPE;
            }
            case -6:
            case 4:
            case 5: {
                return Integer.TYPE;
            }
            case 2000: {
                return Object.class;
            }
            case 7: {
                return Float.TYPE;
            }
            case 2006: {
                return Ref.class;
            }
            case 92: {
                return Time.class;
            }
            case 93: {
                return Timestamp.class;
            }
            default: {
                if (!this.m_ignoreUnknownTypes) {
                    return Object.class;
                }
                return null;
            }
        }
    }
}
