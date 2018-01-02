// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import java.util.Date;
import java.util.ConcurrentModificationException;
import prefuse.data.Table;
import prefuse.util.collections.IntIterator;

public class TableIterator extends IntIterator
{
    private Table m_table;
    private IntIterator m_rows;
    private int m_modCount;
    protected int m_cur;
    
    public TableIterator(final Table table, final IntIterator rows) {
        this.m_cur = -1;
        this.m_table = table;
        this.m_rows = rows;
        this.m_modCount = table.getModificationCount();
    }
    
    public int nextInt() {
        if (this.m_modCount != this.m_table.getModificationCount()) {
            throw new ConcurrentModificationException();
        }
        return this.m_cur = this.m_rows.nextInt();
    }
    
    public boolean hasNext() {
        return this.m_rows.hasNext();
    }
    
    public void remove() {
        if (this.m_table.removeRow(this.m_cur)) {
            this.modify();
        }
    }
    
    protected void modify() {
        ++this.m_modCount;
        this.m_cur = -1;
    }
    
    public final boolean canGet(final String s, final Class clazz) {
        return this.m_table.canGet(s, clazz);
    }
    
    public final boolean canSet(final String s, final Class clazz) {
        return this.m_table.canSet(s, clazz);
    }
    
    public final Object get(final String s) {
        return this.m_table.get(this.m_cur, s);
    }
    
    public final void set(final String s, final Object o) {
        ++this.m_modCount;
        this.m_table.set(this.m_cur, s, o);
    }
    
    public final boolean canGetInt(final String s) {
        return this.m_table.canGetInt(s);
    }
    
    public final boolean canSetInt(final String s) {
        return this.m_table.canSetInt(s);
    }
    
    public final int getInt(final String s) {
        return this.m_table.getInt(this.m_cur, s);
    }
    
    public final void setInt(final String s, final int n) {
        ++this.m_modCount;
        this.m_table.setInt(this.m_cur, s, n);
    }
    
    public final boolean canGetLong(final String s) {
        return this.m_table.canGetLong(s);
    }
    
    public final boolean canSetLong(final String s) {
        return this.m_table.canSetLong(s);
    }
    
    public final long getLong(final String s) {
        return this.m_table.getLong(this.m_cur, s);
    }
    
    public final void setLong(final String s, final long n) {
        ++this.m_modCount;
        this.m_table.setLong(this.m_cur, s, n);
    }
    
    public final boolean canGetFloat(final String s) {
        return this.m_table.canGetFloat(s);
    }
    
    public final boolean canSetFloat(final String s) {
        return this.m_table.canSetFloat(s);
    }
    
    public final float getFloat(final String s) {
        return this.m_table.getFloat(this.m_cur, s);
    }
    
    public final void setFloat(final String s, final float n) {
        ++this.m_modCount;
        this.m_table.setFloat(this.m_cur, s, n);
    }
    
    public final boolean canGetDouble(final String s) {
        return this.m_table.canGetDouble(s);
    }
    
    public final boolean canSetDouble(final String s) {
        return this.m_table.canSetDouble(s);
    }
    
    public final double getDouble(final String s) {
        return this.m_table.getDouble(this.m_cur, s);
    }
    
    public final void setDouble(final String s, final double n) {
        ++this.m_modCount;
        this.m_table.setDouble(this.m_cur, s, n);
    }
    
    public final boolean canGetBoolean(final String s) {
        return this.m_table.canGetBoolean(s);
    }
    
    public final boolean canSetBoolean(final String s) {
        return this.m_table.canSetBoolean(s);
    }
    
    public final boolean getBoolean(final String s) {
        return this.m_table.getBoolean(this.m_cur, s);
    }
    
    public final void setBoolean(final String s, final boolean b) {
        ++this.m_modCount;
        this.m_table.setBoolean(this.m_cur, s, b);
    }
    
    public final boolean canGetString(final String s) {
        return this.m_table.canGetString(s);
    }
    
    public final boolean canSetString(final String s) {
        return this.m_table.canSetString(s);
    }
    
    public final String getString(final String s) {
        return this.m_table.getString(this.m_cur, s);
    }
    
    public final void setString(final String s, final String s2) {
        ++this.m_modCount;
        this.m_table.setString(this.m_cur, s, s2);
    }
    
    public final boolean canGetDate(final String s) {
        return this.m_table.canGetDate(s);
    }
    
    public final boolean canSetDate(final String s) {
        return this.m_table.canSetDate(s);
    }
    
    public final Date getDate(final String s) {
        return this.m_table.getDate(this.m_cur, s);
    }
    
    public final void setDate(final String s, final Date date) {
        ++this.m_modCount;
        this.m_table.setDate(this.m_cur, s, date);
    }
}
