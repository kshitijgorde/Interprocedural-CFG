// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import org.xmodel.IModelObject;
import java.util.Date;
import java.io.InputStream;

public interface DbResultSet
{
    boolean next();
    
    InputStream getInputStream() throws DbException;
    
    void close();
    
    boolean getBoolean() throws DbException;
    
    int getInteger() throws DbException;
    
    long getLong() throws DbException;
    
    double getDouble() throws DbException;
    
    float getFloat() throws DbException;
    
    Date getDate() throws DbException;
    
    String getString() throws DbException;
    
    String getString(final boolean p0) throws DbException;
    
    IModelObject getElement() throws DbException;
    
    InputStream getInputStream(final String p0) throws DbException;
    
    boolean getBoolean(final String p0) throws DbException;
    
    int getInteger(final String p0) throws DbException;
    
    long getLong(final String p0) throws DbException;
    
    double getDouble(final String p0) throws DbException;
    
    float getFloat(final String p0) throws DbException;
    
    Date getDate(final String p0) throws DbException;
    
    String getString(final String p0) throws DbException;
    
    String getString(final String p0, final boolean p1) throws DbException;
    
    IModelObject getElement(final String p0) throws DbException;
    
    InputStream getInputStream(final int p0) throws DbException;
    
    boolean getBoolean(final int p0) throws DbException;
    
    int getInteger(final int p0) throws DbException;
    
    long getLong(final int p0) throws DbException;
    
    double getDouble(final int p0) throws DbException;
    
    float getFloat(final int p0) throws DbException;
    
    Date getDate(final int p0) throws DbException;
    
    String getString(final int p0) throws DbException;
    
    String getString(final int p0, final boolean p1) throws DbException;
    
    IModelObject getElement(final int p0) throws DbException;
}
