// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.MathLib;
import prefuse.data.Tuple;

class SafeLog10Function extends DoubleFunction
{
    public SafeLog10Function() {
        super(2);
    }
    
    public String getName() {
        return "SAFELOG10";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return MathLib.safeLog10(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
