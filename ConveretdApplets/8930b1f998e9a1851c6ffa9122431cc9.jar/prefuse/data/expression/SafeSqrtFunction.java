// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.MathLib;
import prefuse.data.Tuple;

class SafeSqrtFunction extends DoubleFunction
{
    public SafeSqrtFunction() {
        super(2);
    }
    
    public String getName() {
        return "SAFESQRT";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return MathLib.safeSqrt(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
