// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db.mysql;

import com.stonewall.cornerstone.utility.SecureDocument;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.utility.Encrypted;
import java.util.Calendar;
import java.util.Date;
import com.stonewall.cornerstone.db.DbException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.stonewall.cornerstone.db.Profiler;
import org.xmodel.log.Log;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import com.stonewall.cornerstone.db.DbResultSet;

public class ResultSet implements DbResultSet
{
    private final java.sql.ResultSet resultSet;
    static final TimeZone timezone;
    static final GregorianCalendar gc;
    static final Log log;
    
    static {
        timezone = TimeZone.getTimeZone("GMT");
        gc = new GregorianCalendar(ResultSet.timezone);
        log = Log.getLog(Connection.class);
    }
    
    public ResultSet(final java.sql.ResultSet resultSet) {
        this.resultSet = resultSet;
    }
    
    @Override
    public boolean next() {
        boolean result = false;
        try {
            final long start = System.currentTimeMillis();
            result = this.resultSet.next();
            final long end = System.currentTimeMillis();
            Profiler.getInstance().resultGet("next", end - start);
        }
        catch (Exception e) {
            ResultSet.log.debug(this.resultSet, e);
        }
        return result;
    }
    
    @Override
    public InputStream getInputStream() throws DbException {
        InputStream istr = null;
        final String s = this.getString();
        if (s != null) {
            istr = new ByteArrayInputStream(s.getBytes());
        }
        return istr;
    }
    
    @Override
    public void close() {
        try {
            this.resultSet.close();
        }
        catch (Exception e) {
            ResultSet.log.debug(this.resultSet, e);
        }
    }
    
    @Override
    public boolean getBoolean() throws DbException {
        try {
            return this.resultSet.getBoolean(1);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public int getInteger() throws DbException {
        try {
            return this.resultSet.getInt(1);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public long getLong() throws DbException {
        try {
            return this.resultSet.getLong(1);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public double getDouble() throws DbException {
        try {
            return this.resultSet.getDouble(1);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public float getFloat() throws DbException {
        try {
            return this.resultSet.getFloat(1);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public Date getDate() throws DbException {
        try {
            return this.resultSet.getTimestamp(1, ResultSet.gc);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public String getString() throws DbException {
        try {
            return this.resultSet.getString(1);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public String getString(final boolean decrypt) throws DbException {
        String value = this.getString();
        if (value != null) {
            value = Encrypted.decrypt(value);
        }
        return value;
    }
    
    @Override
    public IModelObject getElement() throws DbException {
        try {
            final long start = System.currentTimeMillis();
            final InputStream istr = this.getInputStream();
            if (istr != null) {
                final ModelBuilder builder = new ModelBuilder();
                final IModelObject root = builder.buildModel(istr);
                final SecureDocument sd = new SecureDocument(root);
                sd.decrypt();
                final long end = System.currentTimeMillis();
                Profiler.getInstance().resultGet("element", end - start);
                return root;
            }
        }
        catch (Exception e) {
            throw new DbException(e);
        }
        return null;
    }
    
    @Override
    public InputStream getInputStream(final int column) throws DbException {
        InputStream istr = null;
        final String s = this.getString(column);
        if (s != null && s.length() > 0) {
            istr = new ByteArrayInputStream(s.getBytes());
        }
        return istr;
    }
    
    @Override
    public boolean getBoolean(final int column) throws DbException {
        try {
            return this.resultSet.getBoolean(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public int getInteger(final int column) throws DbException {
        try {
            return this.resultSet.getInt(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public long getLong(final int column) throws DbException {
        try {
            return this.resultSet.getLong(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public double getDouble(final int column) throws DbException {
        try {
            return this.resultSet.getDouble(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public float getFloat(final int column) throws DbException {
        try {
            return this.resultSet.getFloat(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public Date getDate(final int column) throws DbException {
        try {
            return this.resultSet.getTimestamp(column, ResultSet.gc);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public String getString(final int column) throws DbException {
        try {
            return this.resultSet.getString(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public String getString(final int column, final boolean decrypt) throws DbException {
        String value = this.getString(column);
        if (value != null) {
            value = Encrypted.decrypt(value);
        }
        return value;
    }
    
    @Override
    public IModelObject getElement(final int column) throws DbException {
        try {
            final long start = System.currentTimeMillis();
            final InputStream istr = this.getInputStream(column);
            if (istr != null) {
                final ModelBuilder builder = new ModelBuilder();
                final IModelObject root = builder.buildModel(istr);
                final SecureDocument sd = new SecureDocument(root);
                sd.decrypt();
                final long end = System.currentTimeMillis();
                Profiler.getInstance().resultGet("element", end - start);
                return root;
            }
        }
        catch (Exception e) {
            ResultSet.log.error(this.getString(column), e);
            throw new DbException(e);
        }
        return null;
    }
    
    @Override
    public InputStream getInputStream(final String column) throws DbException {
        InputStream istr = null;
        final String s = this.getString(column);
        if (s != null && s.length() > 0) {
            istr = new ByteArrayInputStream(s.getBytes());
        }
        return istr;
    }
    
    @Override
    public boolean getBoolean(final String column) throws DbException {
        try {
            return this.resultSet.getBoolean(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public int getInteger(final String column) throws DbException {
        try {
            return this.resultSet.getInt(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public long getLong(final String column) throws DbException {
        try {
            return this.resultSet.getLong(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public double getDouble(final String column) throws DbException {
        try {
            return this.resultSet.getDouble(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public float getFloat(final String column) throws DbException {
        try {
            return this.resultSet.getFloat(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public Date getDate(final String column) throws DbException {
        try {
            return this.resultSet.getTimestamp(column, ResultSet.gc);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public String getString(final String column) throws DbException {
        try {
            return this.resultSet.getString(column);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public String getString(final String column, final boolean decrypt) throws DbException {
        String value = this.getString(column);
        if (value != null) {
            value = Encrypted.decrypt(value);
        }
        return value;
    }
    
    @Override
    public IModelObject getElement(final String column) throws DbException {
        try {
            final long start = System.currentTimeMillis();
            final InputStream istr = this.getInputStream(column);
            if (istr != null) {
                final ModelBuilder builder = new ModelBuilder();
                final IModelObject root = builder.buildModel(istr);
                final SecureDocument sd = new SecureDocument(root);
                sd.decrypt();
                final long end = System.currentTimeMillis();
                Profiler.getInstance().resultGet("element", end - start);
                return root;
            }
        }
        catch (Exception e) {
            ResultSet.log.error(this.getString(column), e);
            throw new DbException(e);
        }
        return null;
    }
}
