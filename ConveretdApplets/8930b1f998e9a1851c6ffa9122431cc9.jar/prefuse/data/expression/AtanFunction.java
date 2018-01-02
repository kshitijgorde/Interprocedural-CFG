// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class AtanFunction extends DoubleFunction
{
    public AtanFunction() {
        super(1);
    }
    
    public String getName() {
        return "ATAN";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.atan(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
