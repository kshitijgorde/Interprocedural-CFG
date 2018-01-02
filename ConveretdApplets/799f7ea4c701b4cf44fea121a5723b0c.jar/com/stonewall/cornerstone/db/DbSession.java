// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import java.util.List;

public interface DbSession
{
    boolean beginTransaction() throws DbException;
    
    void commit() throws DbException;
    
    void rollback();
    
    void close();
    
    void setAutoCommit(final boolean p0);
    
    boolean getAutoCommit();
    
    void setTransaction(final Transaction p0);
    
    Transaction getTransaction();
    
    void setDatabase(final String p0) throws DbException;
    
    void setDatabase(final DB p0) throws DbException;
    
    List<String> getDatabases() throws DbException;
    
    void createDatabase(final String p0) throws DbException;
    
    void dropDatabase(final String p0) throws DbException;
    
    DbStatement createStatement() throws DbException;
    
    DbStatement createStatement(final String p0) throws DbException;
    
    QueryBuilder queryBuilder() throws DbException;
    
    QueryBuilder queryBuilder(final String p0) throws DbException;
    
    void execute(final String p0, final String... p1) throws DbException;
    
    String database();
    
    public enum DB
    {
        main("main", 0), 
        latest("latest", 1), 
        quartz("quartz", 2);
        
        private DB(final String s, final int n) {
        }
    }
}
