// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import java.util.Date;
import java.util.List;
import org.xmodel.IModelObject;
import java.util.Collection;

public interface DbStatement
{
    void addFunction(final String p0);
    
    DbResultSet execute() throws DbException;
    
    void close();
    
    void setContent(final String p0);
    
    void set(final String p0, final String p1);
    
    void set(final String p0, final Collection<String> p1);
    
    void set(final String p0, final int p1);
    
    void set(final String p0, final long p1);
    
    void set(final String p0, final boolean p1);
    
    void set(final String p0, final String p1, final boolean p2);
    
    void set(final String p0, final IModelObject p1);
    
    void set(final String p0, final List<IModelObject> p1);
    
    void setString(final String p0, final String p1);
    
    void setString(final String p0, final String p1, final boolean p2);
    
    void setString(final String p0, final Collection<String> p1);
    
    void setString(final String p0, final Collection<String> p1, final boolean p2);
    
    void setString(final String p0, final IModelObject p1);
    
    void setString(final String p0, final Date p1);
    
    String date();
    
    String dateTime();
    
    String time();
    
    void set(final Mode p0);
    
    void setTimeout(final int p0);
    
    int getWarnings();
    
    QueryBuilder queryBuilder();
    
    public enum Mode
    {
        Query("Query", 0), 
        Update("Update", 1);
        
        private Mode(final String s, final int n) {
        }
    }
}
