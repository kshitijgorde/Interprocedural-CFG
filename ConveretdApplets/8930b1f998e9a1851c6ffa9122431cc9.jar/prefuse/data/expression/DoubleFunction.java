// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.data.Schema;

abstract class DoubleFunction extends FunctionExpression
{
    protected DoubleFunction(final int n) {
        super(n);
    }
    
    public Class getType(final Schema schema) {
        return Double.TYPE;
    }
    
    public Object get(final Tuple tuple) {
        return new Double(this.getDouble(tuple));
    }
    
    public int getInt(final Tuple tuple) {
        return (int)this.getDouble(tuple);
    }
    
    public long getLong(final Tuple tuple) {
        return (long)this.getDouble(tuple);
    }
    
    public float getFloat(final Tuple tuple) {
        return (float)this.getDouble(tuple);
    }
}
