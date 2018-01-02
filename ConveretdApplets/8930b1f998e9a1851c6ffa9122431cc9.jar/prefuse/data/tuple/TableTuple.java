// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.tuple;

import java.util.Date;
import prefuse.data.Schema;
import prefuse.data.Graph;
import prefuse.data.Table;
import prefuse.data.Tuple;

public class TableTuple implements Tuple
{
    protected Table m_table;
    protected int m_row;
    
    protected void init(final Table table, final Graph graph, final int n) {
        this.m_table = table;
        this.m_row = (this.m_table.isValidRow(n) ? n : -1);
    }
    
    public Schema getSchema() {
        return this.m_table.getSchema();
    }
    
    public Table getTable() {
        return this.m_table;
    }
    
    public int getRow() {
        return this.m_row;
    }
    
    public boolean isValid() {
        return this.m_row != -1;
    }
    
    void invalidate() {
        this.m_row = -1;
    }
    
    private void validityCheck() {
        if (this.m_row == -1) {
            throw new IllegalStateException("This tuple is no longer valid. It has been deleted from its table");
        }
    }
    
    public Class getColumnType(final String s) {
        return this.m_table.getColumnType(s);
    }
    
    public Class getColumnType(final int n) {
        return this.m_table.getColumnType(n);
    }
    
    public int getColumnIndex(final String s) {
        return this.m_table.getColumnNumber(s);
    }
    
    public int getColumnCount() {
        return this.m_table.getColumnCount();
    }
    
    public String getColumnName(final int n) {
        return this.m_table.getColumnName(n);
    }
    
    public boolean canGet(final String s, final Class clazz) {
        return this.m_table.canGet(s, clazz);
    }
    
    public boolean canSet(final String s, final Class clazz) {
        return this.m_table.canSet(s, clazz);
    }
    
    public final Object get(final String s) {
        this.validityCheck();
        return this.m_table.get(this.m_row, s);
    }
    
    public final void set(final String s, final Object o) {
        this.validityCheck();
        this.m_table.set(this.m_row, s, o);
    }
    
    public final Object get(final int n) {
        this.validityCheck();
        return this.m_table.get(this.m_row, n);
    }
    
    public final void set(final int n, final Object o) {
        this.validityCheck();
        this.m_table.set(this.m_row, n, o);
    }
    
    public Object getDefault(final String s) {
        this.validityCheck();
        return this.m_table.getDefault(s);
    }
    
    public void revertToDefault(final String s) {
        this.validityCheck();
        this.m_table.revertToDefault(this.m_row, s);
    }
    
    public final boolean canGetInt(final String s) {
        return this.m_table.canGetInt(s);
    }
    
    public final boolean canSetInt(final String s) {
        return this.m_table.canSetInt(s);
    }
    
    public final int getInt(final String s) {
        this.validityCheck();
        return this.m_table.getInt(this.m_row, s);
    }
    
    public final void setInt(final String s, final int n) {
        this.validityCheck();
        this.m_table.setInt(this.m_row, s, n);
    }
    
    public final int getInt(final int n) {
        this.validityCheck();
        return this.m_table.getInt(this.m_row, n);
    }
    
    public final void setInt(final int n, final int n2) {
        this.validityCheck();
        this.m_table.setInt(this.m_row, n, n2);
    }
    
    public final boolean canGetLong(final String s) {
        return this.m_table.canGetLong(s);
    }
    
    public final boolean canSetLong(final String s) {
        return this.m_table.canSetLong(s);
    }
    
    public final long getLong(final String s) {
        this.validityCheck();
        return this.m_table.getLong(this.m_row, s);
    }
    
    public final void setLong(final String s, final long n) {
        this.validityCheck();
        this.m_table.setLong(this.m_row, s, n);
    }
    
    public final long getLong(final int n) {
        this.validityCheck();
        return this.m_table.getLong(this.m_row, n);
    }
    
    public final void setLong(final int n, final long n2) {
        this.validityCheck();
        this.m_table.setLong(this.m_row, n, n2);
    }
    
    public final boolean canGetFloat(final String s) {
        return this.m_table.canGetFloat(s);
    }
    
    public final boolean canSetFloat(final String s) {
        return this.m_table.canSetFloat(s);
    }
    
    public final float getFloat(final String s) {
        this.validityCheck();
        return this.m_table.getFloat(this.m_row, s);
    }
    
    public final void setFloat(final String s, final float n) {
        this.validityCheck();
        this.m_table.setFloat(this.m_row, s, n);
    }
    
    public final float getFloat(final int n) {
        this.validityCheck();
        return this.m_table.getFloat(this.m_row, n);
    }
    
    public final void setFloat(final int n, final float n2) {
        this.validityCheck();
        this.m_table.setFloat(this.m_row, n, n2);
    }
    
    public final boolean canGetDouble(final String s) {
        return this.m_table.canGetDouble(s);
    }
    
    public final boolean canSetDouble(final String s) {
        return this.m_table.canSetDouble(s);
    }
    
    public final double getDouble(final String s) {
        this.validityCheck();
        return this.m_table.getDouble(this.m_row, s);
    }
    
    public final void setDouble(final String s, final double n) {
        this.validityCheck();
        this.m_table.setDouble(this.m_row, s, n);
    }
    
    public final double getDouble(final int n) {
        this.validityCheck();
        return this.m_table.getDouble(this.m_row, n);
    }
    
    public final void setDouble(final int n, final double n2) {
        this.validityCheck();
        this.m_table.setDouble(this.m_row, n, n2);
    }
    
    public final boolean canGetBoolean(final String s) {
        return this.m_table.canGetBoolean(s);
    }
    
    public final boolean canSetBoolean(final String s) {
        return this.m_table.canSetBoolean(s);
    }
    
    public final boolean getBoolean(final String s) {
        this.validityCheck();
        return this.m_table.getBoolean(this.m_row, s);
    }
    
    public final void setBoolean(final String s, final boolean b) {
        this.validityCheck();
        this.m_table.setBoolean(this.m_row, s, b);
    }
    
    public final boolean getBoolean(final int n) {
        this.validityCheck();
        return this.m_table.getBoolean(this.m_row, n);
    }
    
    public final void setBoolean(final int n, final boolean b) {
        this.validityCheck();
        this.m_table.setBoolean(this.m_row, n, b);
    }
    
    public final boolean canGetString(final String s) {
        return this.m_table.canGetString(s);
    }
    
    public final boolean canSetString(final String s) {
        return this.m_table.canSetString(s);
    }
    
    public final String getString(final String s) {
        this.validityCheck();
        return this.m_table.getString(this.m_row, s);
    }
    
    public final void setString(final String s, final String s2) {
        this.validityCheck();
        this.m_table.setString(this.m_row, s, s2);
    }
    
    public final String getString(final int n) {
        this.validityCheck();
        return this.m_table.getString(this.m_row, n);
    }
    
    public final void setString(final int n, final String s) {
        this.validityCheck();
        this.m_table.setString(this.m_row, n, s);
    }
    
    public final boolean canGetDate(final String s) {
        return this.m_table.canGetDate(s);
    }
    
    public final boolean canSetDate(final String s) {
        return this.m_table.canSetDate(s);
    }
    
    public final Date getDate(final String s) {
        this.validityCheck();
        return this.m_table.getDate(this.m_row, s);
    }
    
    public final void setDate(final String s, final Date date) {
        this.validityCheck();
        this.m_table.setDate(this.m_row, s, date);
    }
    
    public final Date getDate(final int n) {
        this.validityCheck();
        return this.m_table.getDate(this.m_row, n);
    }
    
    public final void setDate(final int n, final Date date) {
        this.validityCheck();
        this.m_table.setDate(this.m_row, n, date);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Tuple[");
        for (int i = 0; i < this.getColumnCount(); ++i) {
            if (i > 0) {
                sb.append(',');
            }
            try {
                sb.append(this.get(i).toString());
            }
            catch (Exception ex) {
                sb.append("?");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
