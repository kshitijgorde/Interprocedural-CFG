// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class ModFunction extends DoubleFunction
{
    public ModFunction() {
        super(2);
    }
    
    public String getName() {
        return "MOD";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 2) {
            return this.param(0).getDouble(tuple) % this.param(1).getDouble(tuple);
        }
        this.missingParams();
        return Double.NaN;
    }
}
