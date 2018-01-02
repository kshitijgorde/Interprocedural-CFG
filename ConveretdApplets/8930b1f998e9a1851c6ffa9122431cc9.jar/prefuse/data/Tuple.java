// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

import java.util.Date;

public interface Tuple
{
    Schema getSchema();
    
    Table getTable();
    
    int getRow();
    
    boolean isValid();
    
    Class getColumnType(final String p0);
    
    Class getColumnType(final int p0);
    
    int getColumnIndex(final String p0);
    
    int getColumnCount();
    
    String getColumnName(final int p0);
    
    boolean canGet(final String p0, final Class p1);
    
    boolean canSet(final String p0, final Class p1);
    
    Object get(final String p0);
    
    void set(final String p0, final Object p1);
    
    Object get(final int p0);
    
    void set(final int p0, final Object p1);
    
    Object getDefault(final String p0);
    
    void revertToDefault(final String p0);
    
    boolean canGetInt(final String p0);
    
    boolean canSetInt(final String p0);
    
    int getInt(final String p0);
    
    void setInt(final String p0, final int p1);
    
    int getInt(final int p0);
    
    void setInt(final int p0, final int p1);
    
    boolean canGetLong(final String p0);
    
    boolean canSetLong(final String p0);
    
    long getLong(final String p0);
    
    void setLong(final String p0, final long p1);
    
    long getLong(final int p0);
    
    void setLong(final int p0, final long p1);
    
    boolean canGetFloat(final String p0);
    
    boolean canSetFloat(final String p0);
    
    float getFloat(final String p0);
    
    void setFloat(final String p0, final float p1);
    
    float getFloat(final int p0);
    
    void setFloat(final int p0, final float p1);
    
    boolean canGetDouble(final String p0);
    
    boolean canSetDouble(final String p0);
    
    double getDouble(final String p0);
    
    void setDouble(final String p0, final double p1);
    
    double getDouble(final int p0);
    
    void setDouble(final int p0, final double p1);
    
    boolean canGetBoolean(final String p0);
    
    boolean canSetBoolean(final String p0);
    
    boolean getBoolean(final String p0);
    
    void setBoolean(final String p0, final boolean p1);
    
    boolean getBoolean(final int p0);
    
    void setBoolean(final int p0, final boolean p1);
    
    boolean canGetString(final String p0);
    
    boolean canSetString(final String p0);
    
    String getString(final String p0);
    
    void setString(final String p0, final String p1);
    
    String getString(final int p0);
    
    void setString(final int p0, final String p1);
    
    boolean canGetDate(final String p0);
    
    boolean canSetDate(final String p0);
    
    Date getDate(final String p0);
    
    void setDate(final String p0, final Date p1);
    
    Date getDate(final int p0);
    
    void setDate(final int p0, final Date p1);
}
