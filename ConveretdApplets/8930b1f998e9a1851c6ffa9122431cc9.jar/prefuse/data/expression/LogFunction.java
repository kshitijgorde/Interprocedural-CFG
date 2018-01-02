// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class LogFunction extends DoubleFunction
{
    public LogFunction() {
        super(2);
    }
    
    public String getName() {
        return "LOG";
    }
    
    public double getDouble(final Tuple tuple) {
        final int paramCount = this.paramCount();
        if (paramCount == 2) {
            return Math.log(this.param(1).getDouble(tuple)) / Math.log(this.param(0).getDouble(tuple));
        }
        if (paramCount == 1) {
            return Math.log(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
