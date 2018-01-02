// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class DegreesFunction extends DoubleFunction
{
    public DegreesFunction() {
        super(1);
    }
    
    public String getName() {
        return "DEGREES";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.toDegrees(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
