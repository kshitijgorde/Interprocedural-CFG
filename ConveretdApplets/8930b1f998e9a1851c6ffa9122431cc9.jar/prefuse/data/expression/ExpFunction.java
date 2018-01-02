// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class ExpFunction extends DoubleFunction
{
    public ExpFunction() {
        super(1);
    }
    
    public String getName() {
        return "EXP";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.exp(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
