// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external.sql;

import java.sql.SQLClientInfoException;
import java.util.Properties;
import java.sql.Struct;
import java.sql.SQLXML;
import java.sql.NClob;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.Array;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.util.Map;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class JDCConnection implements Connection
{
    private JDCConnectionPool B;
    private Connection C;
    private boolean A;
    private long D;
    
    public JDCConnection(final Connection c, final JDCConnectionPool b) {
        this.C = c;
        this.B = b;
        this.A = false;
        this.D = 0L;
    }
    
    public synchronized boolean lease() {
        if (this.A) {
            return false;
        }
        this.A = true;
        this.D = System.currentTimeMillis();
        return true;
    }
    
    public boolean validate() {
        try {
            this.C.getMetaData();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public boolean inUse() {
        return this.A;
    }
    
    public long getLastUse() {
        return this.D;
    }
    
    @Override
    public void close() throws SQLException {
        this.B.returnConnection(this);
    }
    
    protected void expireLease() {
        this.A = false;
    }
    
    protected Connection getConnection() {
        return this.C;
    }
    
    @Override
    public PreparedStatement prepareStatement(final String s) throws SQLException {
        return this.C.prepareStatement(s);
    }
    
    @Override
    public PreparedStatement prepareStatement(final String s, final int n, final int n2, final int n3) throws SQLException {
        return this.C.prepareStatement(s, n, n2, n3);
    }
    
    @Override
    public PreparedStatement prepareStatement(final String s, final int n, final int n2) throws SQLException {
        return this.C.prepareStatement(s, n, n2);
    }
    
    @Override
    public PreparedStatement prepareStatement(final String s, final int n) throws SQLException {
        return this.C.prepareStatement(s, n);
    }
    
    @Override
    public PreparedStatement prepareStatement(final String s, final int[] array) throws SQLException {
        return this.C.prepareStatement(s, array);
    }
    
    @Override
    public PreparedStatement prepareStatement(final String s, final String[] array) throws SQLException {
        return this.C.prepareStatement(s, array);
    }
    
    @Override
    public CallableStatement prepareCall(final String s) throws SQLException {
        return this.C.prepareCall(s);
    }
    
    @Override
    public CallableStatement prepareCall(final String s, final int n, final int n2, final int n3) throws SQLException {
        return this.C.prepareCall(s, n, n2, n3);
    }
    
    @Override
    public CallableStatement prepareCall(final String s, final int n, final int n2) throws SQLException {
        return this.C.prepareCall(s, n, n2);
    }
    
    @Override
    public Statement createStatement() throws SQLException {
        return this.C.createStatement();
    }
    
    @Override
    public Statement createStatement(final int n, final int n2, final int n3) throws SQLException {
        return this.C.createStatement(n, n2, n3);
    }
    
    @Override
    public Statement createStatement(final int n, final int n2) throws SQLException {
        return this.C.createStatement(n, n2);
    }
    
    @Override
    public String nativeSQL(final String s) throws SQLException {
        return this.C.nativeSQL(s);
    }
    
    @Override
    public void setAutoCommit(final boolean autoCommit) throws SQLException {
        this.C.setAutoCommit(autoCommit);
    }
    
    @Override
    public boolean getAutoCommit() throws SQLException {
        return this.C.getAutoCommit();
    }
    
    @Override
    public void commit() throws SQLException {
        this.C.commit();
    }
    
    @Override
    public void rollback() throws SQLException {
        this.C.rollback();
    }
    
    @Override
    public boolean isClosed() throws SQLException {
        return this.C.isClosed();
    }
    
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return this.C.getMetaData();
    }
    
    @Override
    public int getHoldability() throws SQLException {
        return this.C.getHoldability();
    }
    
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return this.C.getTypeMap();
    }
    
    @Override
    public void releaseSavepoint(final Savepoint savepoint) throws SQLException {
        this.C.releaseSavepoint(savepoint);
    }
    
    @Override
    public void rollback(final Savepoint savepoint) throws SQLException {
        this.C.rollback(savepoint);
    }
    
    @Override
    public void setHoldability(final int holdability) throws SQLException {
        this.C.setHoldability(holdability);
    }
    
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return this.C.setSavepoint();
    }
    
    @Override
    public Savepoint setSavepoint(final String savepoint) throws SQLException {
        return this.C.setSavepoint(savepoint);
    }
    
    @Override
    public void setTypeMap(final Map<String, Class<?>> typeMap) throws SQLException {
        this.C.setTypeMap(typeMap);
    }
    
    @Override
    public void setReadOnly(final boolean readOnly) throws SQLException {
        this.C.setReadOnly(readOnly);
    }
    
    @Override
    public boolean isReadOnly() throws SQLException {
        return this.C.isReadOnly();
    }
    
    @Override
    public void setCatalog(final String catalog) throws SQLException {
        this.C.setCatalog(catalog);
    }
    
    @Override
    public String getCatalog() throws SQLException {
        return this.C.getCatalog();
    }
    
    @Override
    public void setTransactionIsolation(final int transactionIsolation) throws SQLException {
        this.C.setTransactionIsolation(transactionIsolation);
    }
    
    @Override
    public int getTransactionIsolation() throws SQLException {
        return this.C.getTransactionIsolation();
    }
    
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return this.C.getWarnings();
    }
    
    @Override
    public void clearWarnings() throws SQLException {
        this.C.clearWarnings();
    }
    
    @Override
    public Array createArrayOf(final String s, final Object[] array) throws SQLException {
        return this.C.createArrayOf(s, array);
    }
    
    @Override
    public Blob createBlob() throws SQLException {
        return this.C.createBlob();
    }
    
    @Override
    public Clob createClob() throws SQLException {
        return this.C.createClob();
    }
    
    @Override
    public NClob createNClob() throws SQLException {
        return this.C.createNClob();
    }
    
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return this.C.createSQLXML();
    }
    
    @Override
    public Struct createStruct(final String s, final Object[] array) throws SQLException {
        return this.C.createStruct(s, array);
    }
    
    @Override
    public Properties getClientInfo() throws SQLException {
        return this.C.getClientInfo();
    }
    
    @Override
    public String getClientInfo(final String s) throws SQLException {
        return this.C.getClientInfo(s);
    }
    
    @Override
    public boolean isValid(final int n) throws SQLException {
        return this.C.isValid(n);
    }
    
    @Override
    public void setClientInfo(final Properties clientInfo) throws SQLClientInfoException {
        this.C.setClientInfo(clientInfo);
    }
    
    @Override
    public void setClientInfo(final String s, final String s2) throws SQLClientInfoException {
        this.C.setClientInfo(s, s2);
    }
    
    @Override
    public boolean isWrapperFor(final Class<?> clazz) throws SQLException {
        return this.C.isWrapperFor(clazz);
    }
    
    @Override
    public <T> T unwrap(final Class<T> clazz) throws SQLException {
        return this.C.unwrap(clazz);
    }
}
