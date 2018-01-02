// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io.sql;

import prefuse.data.Schema;
import prefuse.data.util.Index;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import prefuse.data.io.DataIOException;
import prefuse.data.Table;
import java.sql.Statement;
import java.sql.Connection;
import java.util.logging.Logger;

public class DatabaseDataSource
{
    private static final Logger s_logger;
    protected Connection m_conn;
    protected Statement m_stmt;
    protected SQLDataHandler m_handler;
    
    DatabaseDataSource(final Connection conn, final SQLDataHandler handler) {
        this.m_conn = conn;
        this.m_handler = handler;
    }
    
    public synchronized Table getData(final String s) throws DataIOException {
        return this.getData(null, s, null);
    }
    
    public synchronized Table getData(final String s, final String s2) throws DataIOException {
        return this.getData(null, s, s2);
    }
    
    public synchronized Table getData(final Table table, final String s) throws DataIOException {
        return this.getData(table, s, null);
    }
    
    public synchronized Table getData(final Table table, final String s, final String s2) throws DataIOException {
        return this.getData(table, s, s2, null);
    }
    
    public synchronized Table getData(final Table table, final String s, final String s2, final Object o) throws DataIOException {
        ResultSet executeQuery;
        try {
            executeQuery = this.executeQuery(s);
        }
        catch (SQLException ex) {
            throw new DataIOException(ex);
        }
        return this.process(table, executeQuery, s2, o);
    }
    
    public void loadData(final Table table, final String s) {
        this.loadData(table, s, null, null, null);
    }
    
    public void loadData(final Table table, final String s, final String s2) {
        this.loadData(table, s, s2, null, null);
    }
    
    public void loadData(final Table table, final String s, final Object o) {
        this.loadData(table, s, null, o, null);
    }
    
    public void loadData(final Table table, final String s, final String s2, final Object o) {
        this.loadData(table, s, s2, o, null);
    }
    
    public void loadData(final Table table, final String s, final String s2, final Object o, final DataSourceWorker.Listener listener) {
        DataSourceWorker.submit(new DataSourceWorker.Entry(this, table, s, s2, o, listener));
    }
    
    private ResultSet executeQuery(final String s) throws SQLException {
        if (this.m_stmt == null) {
            this.m_stmt = this.m_conn.createStatement();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        DatabaseDataSource.s_logger.info("Issuing query: " + s);
        final ResultSet executeQuery = this.m_stmt.executeQuery(s);
        final long n = System.currentTimeMillis() - currentTimeMillis;
        DatabaseDataSource.s_logger.info("External query processing completed: " + n / 1000L + "." + n % 1000L + " seconds.");
        return executeQuery;
    }
    
    protected Table process(Table instantiate, final ResultSet set, final String s, Object o) throws DataIOException {
        int n = 0;
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            final ResultSetMetaData metaData = set.getMetaData();
            final int columnCount = metaData.getColumnCount();
            if (instantiate == null) {
                instantiate = this.getSchema(metaData, this.m_handler).instantiate();
                if (s != null) {
                    try {
                        instantiate.index(s);
                        DatabaseDataSource.s_logger.info("Indexed field: " + s);
                    }
                    catch (Exception ex2) {
                        DatabaseDataSource.s_logger.warning("Error indexing field: " + s);
                    }
                }
            }
            o = ((o == null) ? instantiate : o);
            while (set.next()) {
                synchronized (o) {
                    int n2 = this.getExistingRow(instantiate, set, s);
                    if (n2 < 0) {
                        n2 = instantiate.addRow();
                    }
                    for (int i = 1; i <= columnCount; ++i) {
                        this.m_handler.process(instantiate, n2, set, i);
                    }
                }
                ++n;
            }
        }
        catch (SQLException ex) {
            throw new DataIOException(ex);
        }
        final long n3 = System.currentTimeMillis() - currentTimeMillis;
        DatabaseDataSource.s_logger.info("Internal query processing completed: " + n + " rows, " + n3 / 1000L + "." + n3 % 1000L + " seconds.");
        return instantiate;
    }
    
    protected int getExistingRow(final Table table, final ResultSet set, final String s) throws SQLException {
        if (s == null) {
            return -1;
        }
        final Class columnType = table.getColumnType(s);
        if (columnType == null) {
            return -1;
        }
        final Index index = table.index(s);
        if (columnType == Integer.TYPE) {
            return index.get(set.getInt(s));
        }
        if (columnType == Long.TYPE) {
            return index.get(set.getLong(s));
        }
        if (columnType == Float.TYPE) {
            return index.get(set.getFloat(s));
        }
        if (columnType == Double.TYPE) {
            return index.get(set.getDouble(s));
        }
        if (!columnType.isPrimitive()) {
            return index.get(set.getObject(s));
        }
        return -1;
    }
    
    public Schema getSchema(final ResultSetMetaData resultSetMetaData, final SQLDataHandler sqlDataHandler) throws SQLException {
        final int columnCount = resultSetMetaData.getColumnCount();
        final Schema schema = new Schema(columnCount);
        for (int i = 1; i <= columnCount; ++i) {
            final String columnName = resultSetMetaData.getColumnName(i);
            final Class dataType = sqlDataHandler.getDataType(columnName, resultSetMetaData.getColumnType(i));
            if (dataType != null) {
                schema.addColumn(columnName, dataType);
            }
        }
        return schema;
    }
    
    static {
        s_logger = Logger.getLogger(DatabaseDataSource.class.getName());
    }
}
