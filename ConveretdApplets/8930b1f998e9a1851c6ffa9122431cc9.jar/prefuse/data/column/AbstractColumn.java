// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import java.util.Date;
import prefuse.data.parser.DataParseException;
import prefuse.data.DataTypeException;
import prefuse.util.TypeLib;
import prefuse.data.event.ColumnListener;
import prefuse.data.parser.ObjectParser;
import prefuse.data.parser.ParserFactory;
import prefuse.util.collections.CopyOnWriteArrayList;
import prefuse.data.parser.DataParser;

public abstract class AbstractColumn implements Column
{
    protected final Class m_columnType;
    protected DataParser m_parser;
    protected Object m_defaultValue;
    protected boolean m_readOnly;
    protected CopyOnWriteArrayList m_listeners;
    
    public AbstractColumn() {
        this(Object.class, null);
    }
    
    public AbstractColumn(final Class clazz) {
        this(clazz, null);
    }
    
    public AbstractColumn(final Class columnType, final Object defaultValue) {
        this.m_columnType = columnType;
        final DataParser parser = ParserFactory.getDefaultFactory().getParser(columnType);
        this.m_parser = ((parser == null) ? new ObjectParser() : parser);
        this.setDefaultValue(defaultValue);
        this.m_readOnly = false;
        this.m_listeners = new CopyOnWriteArrayList();
    }
    
    public boolean isReadOnly() {
        return this.m_readOnly;
    }
    
    public void setReadOnly(final boolean readOnly) {
        this.m_readOnly = readOnly;
    }
    
    public boolean isCellEditable(final int n) {
        return !this.m_readOnly;
    }
    
    public Class getColumnType() {
        return this.m_columnType;
    }
    
    public DataParser getParser() {
        return this.m_parser;
    }
    
    public void setParser(final DataParser parser) {
        if (!this.m_columnType.isAssignableFrom(parser.getType())) {
            throw new IllegalArgumentException("Parser type (" + parser.getType().getName() + ") incompatible with" + " this column's data type (" + this.m_columnType.getName() + ")");
        }
        this.m_parser = parser;
    }
    
    public void addColumnListener(final ColumnListener columnListener) {
        this.m_listeners.add(columnListener);
    }
    
    public void removeColumnListener(final ColumnListener columnListener) {
        this.m_listeners.remove(columnListener);
    }
    
    protected final void fireColumnEvent(final int n, final int n2, final int n3) {
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ColumnListener)array[i]).columnChanged(this, n, n2, n3);
        }
    }
    
    protected final void fireColumnEvent(final int n, final int n2) {
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ColumnListener)array[i]).columnChanged(this, n, n2);
        }
    }
    
    protected final void fireColumnEvent(final int n, final long n2) {
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ColumnListener)array[i]).columnChanged(this, n, n2);
        }
    }
    
    protected final void fireColumnEvent(final int n, final float n2) {
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ColumnListener)array[i]).columnChanged(this, n, n2);
        }
    }
    
    protected final void fireColumnEvent(final int n, final double n2) {
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ColumnListener)array[i]).columnChanged(this, n, n2);
        }
    }
    
    protected final void fireColumnEvent(final int n, final boolean b) {
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ColumnListener)array[i]).columnChanged(this, n, b);
        }
    }
    
    protected final void fireColumnEvent(final int n, final Object o) {
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ColumnListener)array[i]).columnChanged(this, n, o);
        }
    }
    
    public Object getDefaultValue() {
        return this.m_defaultValue;
    }
    
    public void setDefaultValue(final Object defaultValue) {
        final boolean primitive = this.m_columnType.isPrimitive();
        if (defaultValue != null && ((!primitive && !this.m_columnType.isInstance(defaultValue)) || (primitive && !TypeLib.isWrapperInstance(this.m_columnType, defaultValue)))) {
            throw new IllegalArgumentException("Default value is not of type " + this.m_columnType.getName());
        }
        this.m_defaultValue = defaultValue;
    }
    
    public void revertToDefault(final int n) {
        this.set(this.m_defaultValue, n);
    }
    
    public boolean canGet(final Class clazz) {
        if (clazz == null) {
            return false;
        }
        if (this.m_columnType.isPrimitive()) {
            return clazz.isAssignableFrom(this.m_columnType) || (TypeLib.isNumericType(this.m_columnType) && TypeLib.isNumericType(clazz)) || clazz.isAssignableFrom(TypeLib.getWrapperType(this.m_columnType)) || clazz.isAssignableFrom(String.class);
        }
        return clazz.isAssignableFrom(this.m_columnType);
    }
    
    public boolean canSet(final Class clazz) {
        if (clazz == null) {
            return false;
        }
        if (this.m_columnType.isPrimitive()) {
            return this.m_columnType.isAssignableFrom(clazz) || TypeLib.getWrapperType(this.m_columnType).isAssignableFrom(clazz) || String.class.isAssignableFrom(clazz);
        }
        return this.m_columnType.isAssignableFrom(clazz);
    }
    
    public boolean canGetInt() {
        return this.canGet(Integer.TYPE);
    }
    
    public boolean canSetInt() {
        return this.canSet(Integer.TYPE);
    }
    
    public int getInt(final int n) throws DataTypeException {
        if (this.canGetInt()) {
            return (int)this.get(n);
        }
        throw new DataTypeException(Integer.TYPE);
    }
    
    public void setInt(final int n, final int n2) throws DataTypeException {
        if (this.canSetInt()) {
            this.set(new Integer(n), n2);
            return;
        }
        throw new DataTypeException(Integer.TYPE);
    }
    
    public boolean canGetLong() {
        return this.canGet(Long.TYPE);
    }
    
    public boolean canSetLong() {
        return this.canSet(Long.TYPE);
    }
    
    public long getLong(final int n) throws DataTypeException {
        if (this.canGetLong()) {
            return (long)this.get(n);
        }
        throw new DataTypeException(Long.TYPE);
    }
    
    public void setLong(final long n, final int n2) throws DataTypeException {
        if (this.canSetLong()) {
            this.set(new Long(n), n2);
            return;
        }
        throw new DataTypeException(Long.TYPE);
    }
    
    public boolean canGetFloat() {
        return this.canGet(Float.TYPE);
    }
    
    public boolean canSetFloat() {
        return this.canSet(Float.TYPE);
    }
    
    public float getFloat(final int n) throws DataTypeException {
        if (this.canGetFloat()) {
            return (float)this.get(n);
        }
        throw new DataTypeException(Float.TYPE);
    }
    
    public void setFloat(final float n, final int n2) throws DataTypeException {
        if (this.canSetFloat()) {
            this.set(new Float(n), n2);
            return;
        }
        throw new DataTypeException(Float.TYPE);
    }
    
    public boolean canGetDouble() {
        return this.canGet(Double.TYPE);
    }
    
    public boolean canSetDouble() {
        return this.canSet(Double.TYPE);
    }
    
    public double getDouble(final int n) throws DataTypeException {
        if (this.canGetDouble()) {
            return (double)this.get(n);
        }
        throw new DataTypeException(Double.TYPE);
    }
    
    public void setDouble(final double n, final int n2) throws DataTypeException {
        if (this.canSetDouble()) {
            this.set(new Double(n), n2);
            return;
        }
        throw new DataTypeException(Double.TYPE);
    }
    
    public boolean canGetBoolean() {
        return this.canGet(Boolean.TYPE);
    }
    
    public boolean canSetBoolean() {
        return this.canSet(Boolean.TYPE);
    }
    
    public boolean getBoolean(final int n) throws DataTypeException {
        if (this.canGetBoolean()) {
            return (boolean)this.get(n);
        }
        throw new DataTypeException(Boolean.TYPE);
    }
    
    public void setBoolean(final boolean b, final int n) throws DataTypeException {
        if (this.canSetBoolean()) {
            this.set(new Boolean(b), n);
            return;
        }
        throw new DataTypeException(Boolean.TYPE);
    }
    
    public boolean canGetString() {
        return true;
    }
    
    public boolean canSetString() {
        return this.m_parser != null && !(this.m_parser instanceof ObjectParser);
    }
    
    public String getString(final int n) throws DataTypeException {
        if (this.canGetString()) {
            return this.m_parser.format(this.get(n));
        }
        throw new DataTypeException(String.class);
    }
    
    public void setString(final String s, final int n) throws DataTypeException {
        try {
            this.set(this.m_parser.parse(s), n);
        }
        catch (DataParseException ex) {
            throw new DataTypeException(ex);
        }
    }
    
    public boolean canGetDate() {
        return this.canGet(Date.class);
    }
    
    public boolean canSetDate() {
        return this.canSet(Date.class);
    }
    
    public Date getDate(final int n) throws DataTypeException {
        if (this.canGetDate()) {
            return (Date)this.get(n);
        }
        throw new DataTypeException(Date.class);
    }
    
    public void setDate(final Date date, final int n) throws DataTypeException {
        this.set(date, n);
    }
}
