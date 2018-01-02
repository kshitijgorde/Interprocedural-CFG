// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class AbsFunction extends DoubleFunction
{
    public AbsFunction() {
        super(1);
    }
    
    public String getName() {
        return "ABS";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.abs(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
