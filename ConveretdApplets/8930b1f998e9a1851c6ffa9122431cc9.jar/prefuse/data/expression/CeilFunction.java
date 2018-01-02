// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class CeilFunction extends DoubleFunction
{
    public CeilFunction() {
        super(1);
    }
    
    public String getName() {
        return "CEIL";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.ceil(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
    
    public int getInt(final Tuple tuple) {
        return (int)this.getDouble(tuple);
    }
}
