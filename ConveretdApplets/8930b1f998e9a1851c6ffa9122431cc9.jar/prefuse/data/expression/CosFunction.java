// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class CosFunction extends DoubleFunction
{
    public CosFunction() {
        super(1);
    }
    
    public String getName() {
        return "COS";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.cos(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
