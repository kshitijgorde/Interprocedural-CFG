// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.data.Schema;

class SignFunction extends DoubleFunction
{
    public SignFunction() {
        super(1);
    }
    
    public String getName() {
        return "SIGN";
    }
    
    public Class getType(final Schema schema) {
        return Integer.TYPE;
    }
    
    public double getDouble(final Tuple tuple) {
        return this.getInt(tuple);
    }
    
    public int getInt(final Tuple tuple) {
        if (this.paramCount() == 1) {
            final double double1 = this.param(0).getDouble(tuple);
            return (double1 < 0.0) ? -1 : ((double1 == 0.0) ? false : true);
        }
        this.missingParams();
        return Integer.MIN_VALUE;
    }
}
