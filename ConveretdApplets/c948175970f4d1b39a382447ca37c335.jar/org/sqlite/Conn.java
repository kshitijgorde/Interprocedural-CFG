// 
// Decompiled by Procyon v0.5.30
// 

package org.sqlite;

import java.sql.Struct;
import java.sql.Savepoint;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.Map;
import java.io.File;
import java.sql.SQLException;
import java.sql.Connection;

class Conn implements Connection
{
    private final String url;
    private final boolean readOnly;
    private DB db;
    private MetaData meta;
    private boolean autoCommit;
    private int timeout;
    
    public Conn(final String s, final String s2, final boolean b) throws SQLException {
        this(s, s2);
        this.db.shared_cache(b);
    }
    
    public Conn(final String url, String absolutePath) throws SQLException {
        this.db = null;
        this.meta = null;
        this.autoCommit = true;
        this.timeout = 0;
        boolean readOnly = false;
        if (!":memory:".equals(absolutePath)) {
            final File absoluteFile = new File(absolutePath).getAbsoluteFile();
            File parentFile = absoluteFile.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                for (File parentFile2 = parentFile; parentFile2 != null && !parentFile2.exists(); parentFile2 = parentFile2.getParentFile()) {
                    parentFile = parentFile2;
                }
                throw new SQLException("path to '" + absolutePath + "': '" + parentFile + "' does not exist");
            }
            try {
                if (!absoluteFile.exists() && absoluteFile.createNewFile()) {
                    absoluteFile.delete();
                }
            }
            catch (Exception ex) {
                throw new SQLException("opening db: '" + absolutePath + "': " + ex.getMessage());
            }
            absolutePath = absoluteFile.getAbsolutePath();
            if (absoluteFile.exists()) {
                readOnly = !absoluteFile.canWrite();
            }
        }
        this.readOnly = readOnly;
        try {
            final Class<?> forName = Class.forName("org.sqlite.NativeDB");
            if (forName.getDeclaredMethod("load", (Class[])null).invoke(null, (Object[])null)) {
                this.db = (DB)forName.newInstance();
            }
        }
        catch (Exception ex2) {}
        if (this.db == null) {
            try {
                this.db = (DB)Class.forName("org.sqlite.NestedDB").newInstance();
            }
            catch (Exception ex3) {
                throw new SQLException("no SQLite library found");
            }
        }
        this.url = url;
        this.db.open(this, absolutePath);
        this.setTimeout(3000);
    }
    
    int getTimeout() {
        return this.timeout;
    }
    
    void setTimeout(final int timeout) throws SQLException {
        this.timeout = timeout;
        this.db.busy_timeout(timeout);
    }
    
    String url() {
        return this.url;
    }
    
    String libversion() throws SQLException {
        return this.db.libversion();
    }
    
    DB db() {
        return this.db;
    }
    
    private void checkOpen() throws SQLException {
        if (this.db == null) {
            throw new SQLException("database connection closed");
        }
    }
    
    private void checkCursor(final int n, final int n2, final int n3) throws SQLException {
        if (n != 1003) {
            throw new SQLException("SQLite only supports TYPE_FORWARD_ONLY cursors");
        }
        if (n2 != 1007) {
            throw new SQLException("SQLite only supports CONCUR_READ_ONLY cursors");
        }
        if (n3 != 2) {
            throw new SQLException("SQLite only supports closing cursors at commit");
        }
    }
    
    public void finalize() throws SQLException {
        this.close();
    }
    
    public void close() throws SQLException {
        if (this.db == null) {
            return;
        }
        if (this.meta != null) {
            this.meta.close();
        }
        this.db.close();
        this.db = null;
    }
    
    public boolean isClosed() throws SQLException {
        return this.db == null;
    }
    
    public String getCatalog() throws SQLException {
        this.checkOpen();
        return null;
    }
    
    public void setCatalog(final String s) throws SQLException {
        this.checkOpen();
    }
    
    public int getHoldability() throws SQLException {
        this.checkOpen();
        return 2;
    }
    
    public void setHoldability(final int n) throws SQLException {
        this.checkOpen();
        if (n != 2) {
            throw new SQLException("SQLite only supports CLOSE_CURSORS_AT_COMMIT");
        }
    }
    
    public int getTransactionIsolation() {
        return 8;
    }
    
    public void setTransactionIsolation(final int n) throws SQLException {
        if (n != 8) {
            throw new SQLException("SQLite supports only TRANSACTION_SERIALIZABLE");
        }
    }
    
    public Map getTypeMap() throws SQLException {
        throw new SQLException("not yet implemented");
    }
    
    public void setTypeMap(final Map map) throws SQLException {
        throw new SQLException("not yet implemented");
    }
    
    public boolean isReadOnly() throws SQLException {
        return this.readOnly;
    }
    
    public void setReadOnly(final boolean b) throws SQLException {
    }
    
    public DatabaseMetaData getMetaData() {
        if (this.meta == null) {
            this.meta = new MetaData(this);
        }
        return this.meta;
    }
    
    public String nativeSQL(final String s) {
        return s;
    }
    
    public void clearWarnings() throws SQLException {
    }
    
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }
    
    public boolean getAutoCommit() throws SQLException {
        this.checkOpen();
        return this.autoCommit;
    }
    
    public void setAutoCommit(final boolean autoCommit) throws SQLException {
        this.checkOpen();
        if (this.autoCommit == autoCommit) {
            return;
        }
        this.autoCommit = autoCommit;
        this.db.exec(this.autoCommit ? "commit;" : "begin;");
    }
    
    public void commit() throws SQLException {
        this.checkOpen();
        if (this.autoCommit) {
            throw new SQLException("database in auto-commit mode");
        }
        this.db.exec("commit;");
        this.db.exec("begin;");
    }
    
    public void rollback() throws SQLException {
        this.checkOpen();
        if (this.autoCommit) {
            throw new SQLException("database in auto-commit mode");
        }
        this.db.exec("rollback;");
        this.db.exec("begin;");
    }
    
    public Statement createStatement() throws SQLException {
        return this.createStatement(1003, 1007, 2);
    }
    
    public Statement createStatement(final int n, final int n2) throws SQLException {
        return this.createStatement(n, n2, 2);
    }
    
    public Statement createStatement(final int n, final int n2, final int n3) throws SQLException {
        this.checkCursor(n, n2, n3);
        return new Stmt(this);
    }
    
    public CallableStatement prepareCall(final String s) throws SQLException {
        return this.prepareCall(s, 1003, 1007, 2);
    }
    
    public CallableStatement prepareCall(final String s, final int n, final int n2) throws SQLException {
        return this.prepareCall(s, n, n2, 2);
    }
    
    public CallableStatement prepareCall(final String s, final int n, final int n2, final int n3) throws SQLException {
        throw new SQLException("SQLite does not support Stored Procedures");
    }
    
    public PreparedStatement prepareStatement(final String s) throws SQLException {
        return this.prepareStatement(s, 1003, 1007);
    }
    
    public PreparedStatement prepareStatement(final String s, final int n) throws SQLException {
        throw new SQLException("NYI");
    }
    
    public PreparedStatement prepareStatement(final String s, final int[] array) throws SQLException {
        throw new SQLException("NYI");
    }
    
    public PreparedStatement prepareStatement(final String s, final String[] array) throws SQLException {
        throw new SQLException("NYI");
    }
    
    public PreparedStatement prepareStatement(final String s, final int n, final int n2) throws SQLException {
        return this.prepareStatement(s, n, n2, 2);
    }
    
    public PreparedStatement prepareStatement(final String s, final int n, final int n2, final int n3) throws SQLException {
        this.checkCursor(n, n2, n3);
        return new PrepStmt(this, s);
    }
    
    String getDriverVersion() {
        if (this.db != null) {
            final String name = this.db.getClass().getName();
            if (name.indexOf("NestedDB") >= 0) {
                return "pure";
            }
            if (name.indexOf("NativeDB") >= 0) {
                return "native";
            }
        }
        return "unloaded";
    }
    
    public Savepoint setSavepoint() throws SQLException {
        throw new SQLException("unsupported by SQLite: savepoints");
    }
    
    public Savepoint setSavepoint(final String s) throws SQLException {
        throw new SQLException("unsupported by SQLite: savepoints");
    }
    
    public void releaseSavepoint(final Savepoint savepoint) throws SQLException {
        throw new SQLException("unsupported by SQLite: savepoints");
    }
    
    public void rollback(final Savepoint savepoint) throws SQLException {
        throw new SQLException("unsupported by SQLite: savepoints");
    }
    
    public Struct createStruct(final String s, final Object[] array) throws SQLException {
        throw new SQLException("unsupported by SQLite");
    }
}
