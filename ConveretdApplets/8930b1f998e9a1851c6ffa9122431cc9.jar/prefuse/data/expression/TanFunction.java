// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class TanFunction extends DoubleFunction
{
    public TanFunction() {
        super(1);
    }
    
    public String getName() {
        return "TAN";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.tan(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
