// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class PowFunction extends DoubleFunction
{
    public PowFunction() {
        super(1);
    }
    
    public String getName() {
        return "POW";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 2) {
            return Math.pow(this.param(0).getDouble(tuple), this.param(1).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
