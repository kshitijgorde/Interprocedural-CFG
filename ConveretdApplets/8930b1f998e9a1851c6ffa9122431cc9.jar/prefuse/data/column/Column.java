// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import java.util.Date;
import prefuse.data.DataTypeException;
import prefuse.data.event.ColumnListener;
import prefuse.data.parser.DataParser;

public interface Column
{
    int getRowCount();
    
    void setMaximumRow(final int p0);
    
    boolean isReadOnly();
    
    void setReadOnly(final boolean p0);
    
    boolean isCellEditable(final int p0);
    
    Class getColumnType();
    
    DataParser getParser();
    
    void setParser(final DataParser p0);
    
    void addColumnListener(final ColumnListener p0);
    
    void removeColumnListener(final ColumnListener p0);
    
    Object getDefaultValue();
    
    void revertToDefault(final int p0);
    
    boolean canGet(final Class p0);
    
    boolean canSet(final Class p0);
    
    Object get(final int p0);
    
    void set(final Object p0, final int p1) throws DataTypeException;
    
    boolean canGetInt();
    
    boolean canSetInt();
    
    int getInt(final int p0) throws DataTypeException;
    
    void setInt(final int p0, final int p1) throws DataTypeException;
    
    boolean canGetLong();
    
    boolean canSetLong();
    
    long getLong(final int p0) throws DataTypeException;
    
    void setLong(final long p0, final int p1) throws DataTypeException;
    
    boolean canGetFloat();
    
    boolean canSetFloat();
    
    float getFloat(final int p0) throws DataTypeException;
    
    void setFloat(final float p0, final int p1) throws DataTypeException;
    
    boolean canGetDouble();
    
    boolean canSetDouble();
    
    double getDouble(final int p0) throws DataTypeException;
    
    void setDouble(final double p0, final int p1) throws DataTypeException;
    
    boolean canGetBoolean();
    
    boolean canSetBoolean();
    
    boolean getBoolean(final int p0) throws DataTypeException;
    
    void setBoolean(final boolean p0, final int p1) throws DataTypeException;
    
    boolean canGetString();
    
    boolean canSetString();
    
    String getString(final int p0) throws DataTypeException;
    
    void setString(final String p0, final int p1) throws DataTypeException;
    
    boolean canGetDate();
    
    boolean canSetDate();
    
    Date getDate(final int p0) throws DataTypeException;
    
    void setDate(final Date p0, final int p1) throws DataTypeException;
}
