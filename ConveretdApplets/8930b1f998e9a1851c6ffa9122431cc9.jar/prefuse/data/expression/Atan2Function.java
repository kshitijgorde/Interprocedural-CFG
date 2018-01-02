// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class Atan2Function extends DoubleFunction
{
    public Atan2Function() {
        super(2);
    }
    
    public String getName() {
        return "ATAN2";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 2) {
            return Math.atan2(this.param(0).getDouble(tuple), this.param(1).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
