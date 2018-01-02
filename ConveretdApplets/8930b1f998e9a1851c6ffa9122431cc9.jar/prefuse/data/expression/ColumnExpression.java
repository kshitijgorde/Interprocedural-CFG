// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.data.Schema;

public class ColumnExpression extends AbstractExpression implements Predicate
{
    protected final String m_field;
    
    public ColumnExpression(final String field) {
        this.m_field = field;
    }
    
    public String getColumnName() {
        return this.m_field;
    }
    
    public Class getType(final Schema schema) {
        return schema.getColumnType(this.m_field);
    }
    
    public Object get(final Tuple tuple) {
        return tuple.get(this.m_field);
    }
    
    public int getInt(final Tuple tuple) {
        return tuple.getInt(this.m_field);
    }
    
    public long getLong(final Tuple tuple) {
        return tuple.getLong(this.m_field);
    }
    
    public float getFloat(final Tuple tuple) {
        return tuple.getFloat(this.m_field);
    }
    
    public double getDouble(final Tuple tuple) {
        return tuple.getDouble(this.m_field);
    }
    
    public boolean getBoolean(final Tuple tuple) {
        return tuple.getBoolean(this.m_field);
    }
    
    public String toString() {
        return "[" + this.m_field + "]";
    }
}
