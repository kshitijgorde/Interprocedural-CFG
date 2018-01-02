// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import java.util.Date;
import java.util.List;
import org.xmodel.IModelObject;
import java.util.Collection;
import org.xmodel.log.Log;

public class BasicStatement
{
    protected long start;
    protected long timeout;
    protected final QueryBuilder queryBuilder;
    protected static final Log log;
    
    static {
        log = Log.getLog(BasicStatement.class);
    }
    
    protected BasicStatement() {
        this.start = 0L;
        this.timeout = 0L;
        this.queryBuilder = new QueryBuilder();
    }
    
    protected BasicStatement(final QueryBuilder queryBuilder) {
        this.start = 0L;
        this.timeout = 0L;
        this.queryBuilder = queryBuilder;
    }
    
    public void addFunction(final String func) {
        this.queryBuilder.functions.add(func);
    }
    
    public void setName(final String name) {
        this.queryBuilder.setName(name);
    }
    
    public void setContent(final String content) {
        this.queryBuilder.setContent(content);
    }
    
    public void set(final String name, final String value) {
        this.queryBuilder.set(name, value);
    }
    
    public void set(final String name, final Collection<String> value) {
        this.queryBuilder.set(name, value);
    }
    
    public void set(final String name, final int value) {
        this.queryBuilder.set(name, value);
    }
    
    public void set(final String name, final long value) {
        this.queryBuilder.set(name, value);
    }
    
    public void set(final String name, final boolean value) {
        this.queryBuilder.set(name, value);
    }
    
    public void set(final String name, final String value, final boolean encrypt) {
        this.queryBuilder.set(name, value, encrypt);
    }
    
    public void set(final String name, final IModelObject value) {
        this.queryBuilder.set(name, value);
    }
    
    public void set(final String name, final List<IModelObject> value) {
        this.queryBuilder.set(name, value);
    }
    
    public void setString(final String name, final String value) {
        this.queryBuilder.setString(name, value);
    }
    
    public void setString(final String name, final String value, final boolean encrypt) {
        this.queryBuilder.setString(name, value, encrypt);
    }
    
    public void setString(final String name, final Collection<String> value) {
        this.queryBuilder.setString(name, value);
    }
    
    public void setString(final String name, final Collection<String> value, final boolean encrypt) {
        this.queryBuilder.setString(name, value, encrypt);
    }
    
    public void setString(final String name, final IModelObject value) {
        this.queryBuilder.setString(name, value);
    }
    
    public void setString(final String name, final Date value) {
        this.queryBuilder.setString(name, value);
    }
    
    public String date() {
        return this.queryBuilder.date();
    }
    
    public String time() {
        return this.queryBuilder.time();
    }
    
    public String dateTime() {
        return this.queryBuilder.dateTime();
    }
    
    @Override
    public String toString() {
        return this.queryBuilder.toString();
    }
    
    public void setTimeout(final int timeout) {
        this.timeout = timeout;
    }
    
    public String extractName() {
        return this.queryBuilder.extractName();
    }
    
    public QueryBuilder queryBuilder() {
        return this.queryBuilder;
    }
}
