// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.data.Schema;
import prefuse.util.TypeLib;

public class NumericLiteral extends Literal
{
    private final Number m_number;
    private final Class m_type;
    
    public NumericLiteral(final int n) {
        this.m_number = new Integer(n);
        this.m_type = Integer.TYPE;
    }
    
    public NumericLiteral(final long n) {
        this.m_number = new Long(n);
        this.m_type = Long.TYPE;
    }
    
    public NumericLiteral(final float n) {
        this.m_number = new Float(n);
        this.m_type = Float.TYPE;
    }
    
    public NumericLiteral(final double n) {
        this.m_number = new Double(n);
        this.m_type = Double.TYPE;
    }
    
    public NumericLiteral(final Object o) {
        if (o instanceof Number) {
            this.m_number = (Number)o;
            this.m_type = TypeLib.getPrimitiveType(this.m_number.getClass());
            return;
        }
        throw new IllegalArgumentException("Invalid type!");
    }
    
    public Class getType(final Schema schema) {
        return this.m_type;
    }
    
    public Object get(final Tuple tuple) {
        return this.m_number;
    }
    
    public int getInt(final Tuple tuple) {
        return this.m_number.intValue();
    }
    
    public long getLong(final Tuple tuple) {
        return this.m_number.longValue();
    }
    
    public float getFloat(final Tuple tuple) {
        return this.m_number.floatValue();
    }
    
    public double getDouble(final Tuple tuple) {
        return this.m_number.doubleValue();
    }
    
    public String toString() {
        return this.m_number.toString();
    }
}
