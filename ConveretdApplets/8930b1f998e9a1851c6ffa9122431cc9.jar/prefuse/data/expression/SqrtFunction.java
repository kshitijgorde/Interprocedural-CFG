// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class SqrtFunction extends DoubleFunction
{
    public SqrtFunction() {
        super(1);
    }
    
    public String getName() {
        return "SQRT";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.sqrt(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
