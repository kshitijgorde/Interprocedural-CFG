// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.data.Schema;

abstract class IntFunction extends FunctionExpression
{
    protected IntFunction(final int n) {
        super(n);
    }
    
    public Class getType(final Schema schema) {
        return Integer.TYPE;
    }
    
    public Object get(final Tuple tuple) {
        return new Integer(this.getInt(tuple));
    }
    
    public long getLong(final Tuple tuple) {
        return this.getInt(tuple);
    }
    
    public float getFloat(final Tuple tuple) {
        return this.getFloat(tuple);
    }
    
    public double getDouble(final Tuple tuple) {
        return this.getInt(tuple);
    }
}
