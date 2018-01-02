// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class AcosFunction extends DoubleFunction
{
    public AcosFunction() {
        super(1);
    }
    
    public String getName() {
        return "ACOS";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.acos(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
