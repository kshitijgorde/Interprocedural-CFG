// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class CotFunction extends DoubleFunction
{
    public CotFunction() {
        super(1);
    }
    
    public String getName() {
        return "COT";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return 1.0 / Math.tan(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
