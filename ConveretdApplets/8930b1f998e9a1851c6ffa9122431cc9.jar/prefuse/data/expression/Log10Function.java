// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.MathLib;
import prefuse.data.Tuple;

class Log10Function extends DoubleFunction
{
    public Log10Function() {
        super(1);
    }
    
    public String getName() {
        return "LOG10";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return MathLib.log10(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
