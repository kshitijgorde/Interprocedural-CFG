// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class RoundFunction extends DoubleFunction
{
    public RoundFunction() {
        super(1);
    }
    
    public String getName() {
        return "ROUND";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.round(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
    
    public int getInt(final Tuple tuple) {
        return (int)this.getDouble(tuple);
    }
}
