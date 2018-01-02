// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.data.Schema;

public class ObjectLiteral extends Literal
{
    private final Object m_value;
    
    public ObjectLiteral(final Object value) {
        this.m_value = value;
    }
    
    public Class getType(final Schema schema) {
        return (this.m_value == null) ? Object.class : this.m_value.getClass();
    }
    
    public Object get(final Tuple tuple) {
        return this.m_value;
    }
    
    public String toString() {
        if (this.m_value == null) {
            return "NULL";
        }
        return "'" + this.m_value.toString() + "'";
    }
}
