// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class AsinFunction extends DoubleFunction
{
    public AsinFunction() {
        super(1);
    }
    
    public String getName() {
        return "ASIN";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.asin(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
